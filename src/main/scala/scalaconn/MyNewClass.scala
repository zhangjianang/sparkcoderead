package scalaconn

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

object MyNewClass{
  println("in object process")

  def main(args: Array[String]) {
      val m1 = new MyNewClass("ang")
//      val m2 = new MyNewClass("super",100)
  }
}
