package cn.spark.calculate.service

/**
  * Desciption:zookeeper 维护direct kafka 的offset
  *
  * @author Claire.Chen
  * @create_time 2018 -12 - 25 10:44
  */
class KafkaOffsetProcessor {


  /**
    * 为每个主题初始化偏移量
    * @param topic
    */
  def initKafkaOffset(topic :String):Unit={

  }

  /**
    * 获取并更新偏移量
    * @param message
    * @param offsetRange
    * @return
    */
  def getAndUpdateKafkaOffset(message :InputDStream[String],offsetRange: Array[OffsetRange]):DStream[String]={
   null
  }


}
