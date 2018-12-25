package cn.spark.calculate.service.cache

import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row}
import org.apache.spark.storage.StorageLevel
import org.springframework.stereotype.Component

/**
  * Desciption
  *
  * @author Claire.Chen
  * @create_time 2018 -12 - 24 16:44
  */
@Component
class CacheManager {
  
  /**
    * 缓存表格RDD数据
    * @param tableDataRdd
    * @param storageLevel
    */
  def persistHbaseTable(tableDataRdd: RDD[(ImmutableBytesWritable,Result)],storageLevel: StorageLevel): Unit ={
    tableDataRdd.persist(storageLevel)
  }

  /**
    * 取消缓存表格RDD数据
    * @param tableDataRdd
    * @param storageLevel
    */
  def unPersistHbaseTable(tableDataRdd: RDD[(ImmutableBytesWritable,Result)],storageLevel: StorageLevel): Unit ={
    tableDataRdd.unpersist()
  }

  /**
    * 缓存dataframe数据
    * @param df
    * @param storageLevel
    */
  def persistDataFrame(df:DataFrame,storageLevel: StorageLevel): Unit ={
    df.persist(storageLevel)
  }

  /**
    * 取消缓存dataframe数据
    * @param df
    */
  def unpersistDataFrame(df:DataFrame):Unit={
    df.unpersist()
  }

  /**
    * 缓存rdd数据
    * @param rdd
    * @param storageLevel
    */
  def persistRDD(rdd:RDD[Row],storageLevel: StorageLevel): Unit ={
    rdd.persist(storageLevel)
  }

  /**
    * 取消缓存rdd数据
    * @param rdd
    */
  def unpersistDataFrame(rdd:RDD[Row]):Unit={
    rdd.unpersist()
  }


}
