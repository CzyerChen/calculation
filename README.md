### 1. 工程内容
1) 本文结合了springboot与scala编程，很好地简化了scala编程的难度
2) 使用了springboot “约定优于配置”的特点，进行bean的管理
3) 本文包含hbase的连接 、spark对象注入、sparkstreaming对象的注入、kafka的配置、jdbc的配置

### 2.依赖版本
- jdk 1.8
- maven 3
- springboot 1.5.2
- scala 2.11
- mybatis 1.3.1
- postgresql 42.2.4
- spark 2.2.0
- hbase 1.4.8
- fastjson 1.2.47
- druid 1.1.2

-----------------------------------------------------------

### 2.工程包名介绍
- cn.spark.calculate.properties
     自定义配置文件类
- cn.spark.calculate.common
     项目通用类
- cn.spark.calculate.config
     项目配置类
- cn.spark.calculate.constant
     项目常量类
- cn.spark.calculate.controller
     接口类
- cn.spark.calculate.domain
     项目实体类
- cn.spark.calculate.exception
     自定义统一异常处理类
- cn.spark.calculate.mapper
     数据交互类(mybatis-mapper)
- cn.spark.calculate.repository
     接口类
- cn.spark.calculate.service
     具体业务类
- cn.spark.calculate.runner
     运行类
- cn.spark.calculate.util
     工具类

-----------------------------------------------------------
### 3.待解决问题列表
- spring boot与scala编程适应程度,还能有哪些方面的融合
- hbase连接问题，用现有的原生还是使用shc和phoenix的框架，各有什么优缺点
   
-----------------------------------------------------------
### 4.出现的问题
- springboot中依赖的日志版本和spark-core中的slf4j-log4j12冲突，需要降低springboot版本
- scala不支持PropertySource+@ConfigurationProperties 进行前缀方式的注入，需要使用java方式注入







