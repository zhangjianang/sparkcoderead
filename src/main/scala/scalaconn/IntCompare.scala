package scalaconn

/**
  * Created by adimn on 2019/5/23.
  */


class IntCompare(val age:Int) extends Comparable[IntCompare] {
  override def compareTo(o: IntCompare): Int = {
      if (age > o.age) {
        1
      } else if (age == o.age) {
        -1
      } else
        0
  }

  override def toString():String={
    age+""
  }
}


class MyCompair[T <: Comparable[T] ](val first:T, val second:T){
  def small = if(first.compareTo(second)>0) second else first
}

object Main extends App{
  val sth = new MyCompair("ang","super")
  println(sth.small )

  val first = new IntCompare(10)
  val second = new IntCompare(20)
  val sth2 = new MyCompair(first,second)
  println(sth2.small )

}
