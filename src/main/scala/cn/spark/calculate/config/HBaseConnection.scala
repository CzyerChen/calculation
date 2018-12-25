package cn.spark.calculate.config

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{HBaseAdmin, HTable}
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.spark.sql.SparkSession
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.context.annotation.{Bean, Configuration}


/**
  * Desciption ： HBase连接类
  *
  * @author Claire.Chen
  * @create_time 2018 -12 - 24 10:32
  */
@Configuration
class HBaseConnection() {
  @Value("${hbase.zookeeper.quorum}")
  val zookeeperQuorum: String = null
  @Value("${hbase.table1.name}")
  val htableName1:String  =null

  @Autowired
  var sparkSession:SparkSession =_

  /**
    * HBaseAdmin对象的注入
    * @return
    */
  @Bean
  def getHBaseAdmin: HBaseAdmin = {
    var conf = HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", zookeeperQuorum)
    new HBaseAdmin(conf)
  }

  /**
    *  HTable对象的注入
    * @return
  */
  @Bean
  def getHTable: HTable = {
    var conf = HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", zookeeperQuorum)
    conf.set(TableInputFormat.INPUT_TABLE,htableName1)
    new HTable(conf,htableName1)

  }



}
