package cn.spark.calculate.repository.hbase

import java.util

import org.apache.commons.lang3.StringUtils
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client.{Get, HBaseAdmin, HTable, Result}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * Desciption：HBase数据处理执行器
  *
  * @author Claire.Chen
  * @create_time 2018 -12 - 24 14:51
  */
@Component
class HbaseExecutor {

  @Autowired
  var hadmin: HBaseAdmin = _
  @Autowired
  var htable1: HTable = _
  @Autowired
  var sparkSession: SparkSession = _
  @Autowired
  var cacheManager: CacheManager = _


  /**
    * hbase原生书写方式,此处用于灵活的操作
    *
    * @param tableName
    * @param rowKey
    * @throws
    * @return
    */
  @throws[Exception]
  def getResult(tableName: String, rowKey: String): Result = {
    val get = new Get(Bytes.toBytes(rowKey))
    val result = htable1.get(get)

    result
  }

  /**
    * 查询指定表格全部内容
    * 根据参数缓存数据
    *
    * @param htable
    * @param level
    * @return
    */
  def listAll(htable: HTable, level: StorageLevel): RDD[(ImmutableBytesWritable, Result)] = {
    if(htable == null)
      null

    var conf = htable.getConfiguration
    var hbaseRdd = executeHbaseQuery(conf)
    if(level!= null){
      cacheManager.persistHbaseTable(hbaseRdd,level)
    }
    hbaseRdd
  }

  /**
    * 查询指定列族的表格数据
    * 根据参数缓存数据
    *
    * @param htable
    * @param level
    * @param columnInfo
    */
  def getByColumnName(htable: HTable, level: StorageLevel, columnInfo: util.HashMap[String, String]): Unit = {
    if(htable == null)
      null

    var conf = htable.getConfiguration
    var hbaseRdd = executeHbaseQuery(setColumnFamilyInfo(conf,columnInfo))
    if(level != null){
      cacheManager.persistHbaseTable(hbaseRdd, level)
    }
    hbaseRdd
  }

  /**
    * 查询指定主键范围的表格数据
    * 根据参数缓存数据
    *
    * @param htable
    * @param level
    * @param startRow
    * @param endRow
    */
  def getWithRowkeyRange(htable: HTable, level: StorageLevel, startRow: String, endRow: String): Unit = {
    if(htable == null)
      null
    var conf = htable.getConfiguration
    var hbaseRdd = executeHbaseQuery(setColumnRange(conf,startRow,endRow))
    if(level != null){
      cacheManager.persistHbaseTable(hbaseRdd, level)
    }
    hbaseRdd
  }

  def queryByCondition(htable: HTable, level: StorageLevel, startRow: String, endRow: String,columnInfo: util.HashMap[String, String]): Unit ={
    if(htable == null)
      null

    var conf = htable.getConfiguration
    conf = setColumnRange(conf,startRow,endRow)
    conf = setColumnFamilyInfo(conf,columnInfo)
    var hbaseRdd = executeHbaseQuery(setColumnRange(conf,startRow,endRow))
    if(level != null){
      cacheManager.persistHbaseTable(hbaseRdd, level)
    }
    hbaseRdd
  }

  /*==================================================private method=====================================================*/
  /**
    * 查询执行
    * @param conf
    * @return
    */
  private def executeHbaseQuery(conf: Configuration): RDD[(ImmutableBytesWritable, Result)] = {
    val hbaseRdd = sparkSession.sparkContext.newAPIHadoopRDD(conf,
      classOf[TableInputFormat],
      classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable],
      classOf[org.apache.hadoop.hbase.client.Result])
    hbaseRdd
  }

  /**
    * 设置主键查询范围
    * @param conf
    * @param startRow
    * @param endRow
    * @return
    */
  private def setColumnRange(conf:Configuration,startRow: String, endRow: String):Configuration ={
    if (StringUtils.isNotBlank(startRow)) {
      conf.set(TableInputFormat.SCAN_ROW_START, startRow)
    }
    if (StringUtils.isNotBlank(endRow)) {
      conf.set(TableInputFormat.SCAN_ROW_STOP, endRow)
    }
    conf
  }

  /**
    * 设置列族信息
    * @param conf
    * @param columnInfo
    * @return
    */
  private  def setColumnFamilyInfo(conf:Configuration,columnInfo: util.HashMap[String, String])={
    if(columnInfo != null){
      var keySet = columnInfo.keySet()
      var key = null
      var columnsString = ""
      for (key <- keySet) {
        columnsString += key + ":" + columnInfo.get(key) + " "
      }
      if (StringUtils.isNotBlank(columnsString)) {
        conf.set(TableInputFormat.SCAN_COLUMNS, columnsString)
      }
    }
    conf
  }


}
