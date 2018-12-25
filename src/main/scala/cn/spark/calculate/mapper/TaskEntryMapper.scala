package cn.spark.calculate.mapper

import org.apache.ibatis.annotations.{Mapper, Select}

/**
  * Desciption：jdbc任务查询接口，使用mybatis相关知识进行查询
  *
  * @author Claire.Chen
  * @create_time 2018 -12 - 24 18:11
  */
trait TaskEntryMapper {
  @Select(Array(""))
  def listVo(): Unit

}
