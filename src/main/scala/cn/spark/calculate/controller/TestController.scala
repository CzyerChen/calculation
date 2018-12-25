package cn.spark.calculate.controller

import java.util.function.Consumer
import cn.spark.calculate.dao.StuProfileRepository
import cn.spark.calculate.properties.SparkConfig
import org.apache.hadoop.hbase.{Cell, KeyValue}
import org.apache.hadoop.hbase.client.{HBaseAdmin, HTable}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.sql.SparkSession
import org.mapstruct.Qualifier
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._


@RestController
@RequestMapping(Array("/test"))
class TestController{
  private final val log = LoggerFactory.getLogger(classOf[TestController])

  @Autowired
  var hadmin:HBaseAdmin= _

  @Autowired
  var stuProfileRepository: StuProfileRepository =_

  @Autowired
  var sparkSession:SparkSession =_

  @GetMapping(Array("/get"))
  def testSpark(): Unit = {
    var sqlRDD = sparkSession.sql("select count(*) from stu_profile")
    var count= sqlRDD.count()
    log.info("count is {}",count)

  }

  @RequestMapping(value = Array("/{tableName}/{rowKey}"),method = Array(RequestMethod.GET))
  def testGet(@PathVariable tableName:String ,@PathVariable rowKey :String): Unit ={
    var names = hadmin.listTableNames()
    log.info(names.toString)
    var result = stuProfileRepository.getResult(tableName,rowKey)
    result.listCells().forEach(new Consumer[Cell] {
      override def accept(t: Cell): Unit = {
        System.out.println(Bytes.toString(t.getFamily))
        System.out.println(Bytes.toString(t.getQualifier))
        System.out.println(Bytes.toString(t.getValue))
        System.out.println(t.getTimestamp)
      }
    })

  }
}
