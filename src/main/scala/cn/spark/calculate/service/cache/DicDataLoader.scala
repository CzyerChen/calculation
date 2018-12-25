package cn.spark.calculate.service.cache

import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Desciption
  *
  * @author Claire.Chen
  * @create_time 2018 -12 - 25 9:40
  */
@Service
class DicDataLoader {

  @Autowired
  var cacheManager:CacheManager =_
  @Autowired
  var dicDataRepository:DicDataRepository =_

  def loadDicData(sparkSession: SparkSession,tableName:String,level:StorageLevel):Unit={
    var df = dicDataRepository.listAllByTableName(sparkSession,tableName)
    if(level != null){
      df.persist(level)
    }
  }
}
