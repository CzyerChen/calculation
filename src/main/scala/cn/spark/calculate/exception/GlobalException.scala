package cn.spark.calculate.exception

import scala.language.implicitConversions

/**
  * 自定义异常类
  */
class GlobalException extends RuntimeException {

    private final val serialVersionUID: Long = 1L

    private var cm: CodeMsg = _

    def this(cm: CodeMsg) {
        this()
        this.cm = cm
    }

    def getCm: CodeMsg = cm

    override def toString: String = cm.toString()

} 