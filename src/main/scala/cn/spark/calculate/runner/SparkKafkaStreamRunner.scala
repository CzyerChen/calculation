package cn.spark.calculate.runner

import java.beans.Transient

import org.apache.spark.sql.Row
import org.apache.spark.storage.StorageLevel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * Desciption
  *
  * @author Claire.Chen
  * @create_time 2018 -12 - 24 18:11
  */
@Component
class SparkKafkaStreamRunner extends Serializable with Runnable {

  @Autowired
  @Transient
  var kafkaInfo: KafkaInfo = _

  @Autowired
  @Transient
  var streamingContext: StreamingContext = _

  @Autowired
  @Transient
  var cacheManager:CacheManager =_

  override def run(): Unit = {
    var kafkaStream = initKafkaStreaming()

    kafkaStream.foreachRDD { rdd =>
      //获取该RDD对于的偏移量
      val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      //拿出对应的数据
      var levelRDD = rdd.map{ line =>
          var task = StringConverter.stringToBean(line.value(),classOf[TaskEntry])
          val level = task.getLevel
        (level,task)
      }
      var taskRDD = levelRDD.map(f => Row(f._2.getId,f._2))
      cacheManager.persistRDD(taskRDD,StorageLevel.MEMORY_ONLY)
      levelRDD.aggregateByKey()


      //异步更新偏移量到kafka中

      // some time later, after outputs have completed

      kafkaStream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
    }

    streamingContext.start()
    streamingContext.awaitTermination()
  }

  def initKafkaStreaming(): InputDStream[ConsumerRecord[String, String]] = {
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> kafkaInfo.getBrokerList,
      "group.id" -> kafkaInfo.getGroupId,
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "enable.auto.commit" -> (false: java.lang.Boolean),
      "auto.offset.reset" -> "earliest"
    )

    val topicsSet = kafkaInfo.getTopics.split(",").toSet
    //create Discretized Streams-----0-10中的写法
    val kafkaStream = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      //位置策略（可用的Executor上均匀分配分区）
      LocationStrategies.PreferConsistent,
      //消费策略（订阅固定的主题集合）
      ConsumerStrategies.Subscribe[String, String](topicsSet, kafkaParams))

    kafkaStream
  }
}
