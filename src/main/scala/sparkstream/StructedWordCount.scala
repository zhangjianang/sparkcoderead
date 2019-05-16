package sparkstream

import org.apache.spark.sql.SparkSession

/**
  * Created by adimn on 2019/5/15.
  */
class StructedWordCount {

}
object StructedWordCount{
  def main(args: Array[String]) {
    val spark = SparkSession
      .builder()
      .appName("structedWordCount")
      .master("local")
      .config("spark.sql.warehouse.dir", "C:\\Users\\adimn\\Desktop\\sparksql")
      .getOrCreate()
    import spark.implicits._

    val lines = spark
      .readStream
      .format("socket")
      .option("host","localhost")
      .option("port",9999)
      .load()

    val words = lines.as[String].flatMap( _.split(" "))
    val wordCount = words.groupBy("value").count()

    val query = wordCount.writeStream
      .outputMode("complete")
      .format("console")
      .start()

    query.awaitTermination()

  }
}
