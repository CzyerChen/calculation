package cn.spark.calculate.domain.result

import scala.beans.BeanProperty
import scala.language.implicitConversions

class Result[T]() {

    @BeanProperty
    var code: Integer = _

    @BeanProperty
    var msg: String = _

    @BeanProperty
    var data: T = _

    private def this(code: Integer, msg: String, data: T) {
        this()
        this.code = code
        this.msg = msg
        this.data = data
    }

    private def this(data: T) {
        this()
        this.code = 0
        this.msg = "success"
        this.data = data
    }

    private def this(msg: CodeMsg) {
        this()
        if (msg == null) {
            //TODO Scala这里不能写return
        }
        this.code = msg.getCode
        this.msg = msg.getMsg
    }

}

object Result {

    def success[T](data: T): Result[T] = {
        new Result[T](data)

    }

    def error[T](cm: CodeMsg): Result[T] = {
        new Result[T](cm)
    }
    //根据需要在这实现自己的重载方法
}