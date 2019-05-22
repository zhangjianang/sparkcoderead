package scalaconn

import java.util.concurrent.{ArrayBlockingQueue, TimeUnit}

import org.apache.spark.storage.StreamBlockId

import scala.collection.mutable.ArrayBuffer

/**
  * Created by adimn on 2019/5/16.
  */
class MyNewClass(
                  name:String ,
                  age:Int
                ){
 def this(name:String)={
   this(name,10)
   println("name is :"+name +", age is :"+age)
 }
  def other()={
    println(" other func not call")
  }
  println("class process")
}
private case class Block(id: StreamBlockId, buffer: ArrayBuffer[Any])


object MyNewClass{
  println("in object process")

  def main(args: Array[String]) {
      val m1 = new MyNewClass("ang")
//      val m2 = new MyNewClass("super",100)
      val blocksForPushing =new ArrayBlockingQueue[Block](10)
    Option(blocksForPushing.poll(100, TimeUnit.MILLISECONDS)) match {
      case Some(block) => println(block)
      case None => println("nothing happen")
    }

  }
}
