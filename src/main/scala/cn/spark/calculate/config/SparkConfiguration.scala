package cn.spark.calculate.config

import org.apache.spark.serializer.KryoSerializer
import org.apache.spark.sql.SparkSession
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.{Bean, Configuration}

/**
  * Desciption：Spark配置初始化类
  *
  * @author Claire.Chen
  * @create_time 2018 -12 - 24 10:08
  */
@Configuration
class SparkConfiguration {
  @Autowired
  @transient
  var sparkInfo: SparkInfo = _

  @Value("${sparkstreaming.kafka.duration}")
  var duation: Int = 0

  /**
    * 初始化sparkSession对象
    * @return
    */
  @Bean
  def getSparkSession: SparkSession = {
    var session = SparkSession.builder()
      .appName(sparkInfo.getAppName)
      .master(sparkInfo.getMaster)
      .config("spark.driver.memory", sparkInfo.getDriverMemory)
      .config("spark.worker.memory", sparkInfo.getWorkerMemory)
      .config("spark.executor.memory", sparkInfo.getExecutorMemory)
      .config("spark.serializer", classOf[KryoSerializer].getName)
      .getOrCreate()
    session
  }

  /**
    * 初始化SparkStreaming对象
    * @param sparkSession
    * @return
    */
  @Bean
  @ConditionalOnMissingBean(classOf[SparkSession])
  def getSparkStreaming(@Autowired sparkSession: SparkSession): StreamingContext = {
    val ssc = new StreamingContext(sparkSession.sparkContext, Seconds(duation))
    ssc
  }

}




