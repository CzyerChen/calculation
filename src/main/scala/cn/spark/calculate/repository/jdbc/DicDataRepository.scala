package cn.spark.calculate.repository.jdbc

import java.util.Properties

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * Desciption
  *
  * @author Claire.Chen
  * @create_time 2018 -12 - 25 9:46
  */
@Component
class DicDataRepository {
  @Autowired
  var jdbcConnectionInfo:JdbcConnectionInfo =_

  def listAllByTableName(sparkSession: SparkSession,tableName:String):DataFrame={
    val jdbsString = jdbcConnectionInfo.getUrl
    val property = new Properties()
    property.put("user", jdbcConnectionInfo.getUserName)
    property.put("password", jdbcConnectionInfo.getPassword)
    sparkSession.read.jdbc(jdbsString,tableName,property)
  }

}
