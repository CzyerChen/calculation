package cn.spark.calculate.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

import scala.beans.BeanProperty

/**
  * Desciption:获取数据连接信息，共spark连接使用
  *
  * @author Claire.Chen
  * @create_time 2018 -12 - 25 10:10
  */
@Configuration
class JdbcConnectionInfo {
  @BeanProperty
  @Value("${spring.datasource.url}")
  var url: String = _

  @BeanProperty
  @Value("${spring.datasource.username}")
  var userName: String = _

  @BeanProperty
  @Value("${spring.datasource.password}")
  var password: String = _

  @BeanProperty
  @Value("${spring.datasource.driver-class-name}")
  var driverClassName: String = _

}
