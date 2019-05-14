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
      //设置spark sql 的元数据仓库的目录
      .config("spark.sql.warehouse.dir","C:\\Users\\adimn\\Desktop\\sparksql")
      .getOrCreate()
    val sc = spark.sparkContext
    import  spark.implicits._
    //读取json文件，构造一个untyped 弱类型的dataFrame
    val people = spark.read.json("C:\\Users\\adimn\\Desktop\\sparksql\\people.json")
    people.show()
    people.printSchema()
    people.select("name").show()
    //使用表达式，scala的语法，用$做前缀
    people.select($"name",$"age"+1).show()
    people.filter($"age">21).show()

    people.groupBy("age").count().show()

    //基于dataFrame创建临时表
    people.createOrReplaceTempView("mypeople")
    //使用sql就可以创建视图的
    val sqlDf = spark.sql("select * from mypeople")
    sqlDf.show()




    val caseClassDs = Seq(AngPerson("ang",15)).toDF()
    caseClassDs.show()

    val peopleDs = spark.read.json("C:\\Users\\adimn\\Desktop\\sparksql\\people.json").as[AngPerson]
    println("以下是格式化的：")
    peopleDs.show()
  }
  //定义一个case class
  case class AngPerson(name:String,age:Long)
}
