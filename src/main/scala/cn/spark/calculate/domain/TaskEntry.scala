package cn.spark.calculate.domain

import java.util.Date

import scala.beans.BeanProperty

class TaskEntry {
  @BeanProperty
  var id: String = _

  @BeanProperty
  var planId: String = _

  @BeanProperty
  var taskName: String = _

  @BeanProperty
  var measureType: Int = _

  @BeanProperty
  var measureId: String = _

  @BeanProperty
  var tableName: String = _

  @BeanProperty
  var condition: String = _

  @BeanProperty
  var operation: String = _

  @BeanProperty
  var columnName: String = _

  @BeanProperty
  var calculateTime: String = _

  @BeanProperty
  var createTime: Date = _

  @BeanProperty
  var level: Int = _

  @BeanProperty
  var formula: String = _

  @BeanProperty
  var operationIds: String = _

  @BeanProperty
  var state: Int = _


  override def toString: String = {
    "TaskEntry [id=" + id +
      ", planId=" + planId +
      ", taskName=" + taskName +
      ", measureType=" + measureType +
      ", measureId=" + measureId +
      ", tableName=" + tableName +
      ", condition=" + condition+
      ", operation=" + operation+
      ", columnName=" + columnName+
      ", calculateTime=" + calculateTime+
      ", createTime=" + createTime+
      ", level=" + level+
      ", formula=" + formula+
      ", operationIds=" + operationIds+
      ", state=" + state+
      "]"
  }

}
