package sparkcore

import org.apache.spark.sql.SparkSession

/**
 * typed操作
 */
object UnTypedOperation {
  
  case class Employee(name: String, age: Long, depId: Long, gender: String, salary: Long)
  case class Department(id: Long, name: String)

  def main(args: Array[String]) {
    val spark = SparkSession
        .builder()
        .appName("UnTypedOperation")
        .master("local") 
        .config("spark.sql.warehouse.dir", "C:\\Users\\adimn\\Desktop\\sparksql")
        .getOrCreate()
    
    import spark.implicits._
    
    val employee = spark.read.json("C:\\Users\\adimn\\Desktop\\sparksql\\employee.json")

    val department = spark.read.json("C:\\Users\\adimn\\Desktop\\sparksql\\department.json")

    employee.where("age>20 ")
      .join(department,$"depId"===$"id")
      .groupBy(department("name"),employee("gender"))
      .avg("age")
      .show()

  }
  
}