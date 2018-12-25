package cn.spark.calculate.util

import com.alibaba.fastjson.JSON

/**
  * 数据转换类
  */
object StringConverter {

  def beanToString[T](value: T): String = {

    if (value == null)
      return null
    val clazz = value.getClass()
    if (clazz == classOf[Int] || clazz == classOf[Integer]) {
      "" + value
    } else if (clazz == classOf[String]) {
      value.asInstanceOf[String]
    } else if (clazz == classOf[Long] || clazz == classOf[Long]) {
      "" + value
    } else {
      JSON.toJSONString(value, false)
    }
  }

  def stringToBean[T](str: String, clazz: Class[T]): T = {
    if (str == null || str.length() <= 0 || clazz == null) {
      return null.asInstanceOf[T]
    }
    if (clazz == classOf[Int] || clazz == classOf[Integer]) {
      str.toInt.asInstanceOf[T]
    } else if (clazz == classOf[String]) {
      str.asInstanceOf[T]
    } else if (clazz == classOf[Long] || clazz == classOf[Long]) {
      str.toLong.asInstanceOf[T]
    } else {
      JSON.toJavaObject(JSON.parseObject(str), clazz)
    }
  }
}
