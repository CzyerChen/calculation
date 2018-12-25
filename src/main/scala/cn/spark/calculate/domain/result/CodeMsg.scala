package cn.spark.calculate.domain.result

import scala.beans.BeanProperty
import scala.language.implicitConversions

class CodeMsg() {

    @BeanProperty
    var code: Integer = _

    @BeanProperty
    var msg: String = _

    def fillArgs(args: AnyRef*): CodeMsg = {
        val code = this.code
        val message = String.format(this.msg, args)
        return new CodeMsg(code, message)
    }

    private def this(code: Integer, msg: String) {
        this()
        this.code = code
        this.msg = msg
    }

    override def toString(): String = {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]"
    }
}

//TODO:修改为国际化
object CodeMsg {
    val SUCCESS = new CodeMsg(0, "success")
    val SERVER_ERROR = new CodeMsg(500100, "服务端异常")
    val BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s")
    val REQUEST_ILLEGAL = new CodeMsg(500102, "请求非法")
}