/**
  * Created by adimn on 2019/5/8.
  */
package sparkcore

import org.apache.spark.sql.SparkSession

object SparkSessionTry{
  def main(args: Array[String]) {
    val spark = SparkSession
      .builder()
      .appName("sessionTry")
      .master("local")
      .config("spark.sql.warehouse.dir","C:\\Users\\adimn\\Desktop\\sparksql")
      .getOrCreate()
    val sc = spark.sparkContext
    import  spark.implicits._
    val people = spark.read.json("C:\\Users\\adimn\\Desktop\\sparksql\\people.json")
    people.show()
    people.printSchema()
  }

}
