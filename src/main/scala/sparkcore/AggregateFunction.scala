package sparkcore

import org.apache.spark.sql.SparkSession

object AggregateFunction {
  
  def main(args: Array[String]) {
    val spark = SparkSession
        .builder()
        .appName("AggregateFunction") 
        .master("local") 
        .config("spark.sql.warehouse.dir", "C:\\Users\\adimn\\Desktop\\sparksql")
        .getOrCreate()
    
    import spark.implicits._

    //想要使用聚合函数需要引入这段个function，否则无法找到
    import org.apache.spark.sql.functions._
    
    val employee = spark.read.json("C:\\Users\\adimn\\Desktop\\sparksql\\employee.json")
    val department = spark.read.json("C:\\Users\\adimn\\Desktop\\sparksql\\department.json")
    
//    employee
//        .join(department, $"depId" === $"id")
//        .groupBy(department("name"))
//        .agg(avg(employee("salary")), sum(employee("salary")), max(employee("salary")), min(employee("salary")), count(employee("name")), countDistinct(employee("name")))
//        .show()

    // collect_list，就是将一个分组内，指定字段的值都收集到一起，不去重
    // collect_set，同上，但是唯一的区别是，会去重


    // collect_list和collect_set，都用于将同一个分组内的指定字段的值串起来，变成一个数组
    // 常用于行转列
    // 比如说
    // depId=1, employee=leo
    // depId=1, employee=jack
    // depId=1, employees=[leo, jack]

    employee
      .groupBy(employee("depId"))
      .agg(collect_set(employee("name")), collect_list(employee("name")))
        .show()
//      .collect()
//      .foreach(println(_))
  }
  
}