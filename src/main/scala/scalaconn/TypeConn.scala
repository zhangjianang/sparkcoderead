package scalaconn



/**
  * Created by adimn on 2019/5/23.
  */
class People(val name:String)

class Slary(val salry:Double){

  type Tax = Double => Double
  val sinlgeChild :Tax = x => x*0.95
  val personIncome :Tax = x => (x - 3000) /x * 0.2

  def getTax(tax:List[Tax]):Double = {
    var total = 0.0;
    for(i <- 0 until tax.size){
      total += tax(i)(salry)
    }
    total
  }
}

class TypeConn {
  //1、给类型起别名
  type Crowed = List[People]
  def getCount(crowed:Crowed)={
    println(crowed.size)
  }

//  2、给操作起别名
  type PeopleHasName =  ( Object*) => Boolean

  val checkHasName :PeopleHasName = (sth) => {
    sth match {
      case p :Seq[People] => println("has one name"); true
      case p :Seq[People] =>println(" has two name"); true
      case _ => println("has nothing"); false
    }
  }

}



object Main extends App{

  val t1 = new TypeConn
  t1.getCount(List(new People("ang"),new People("super")))

  t1.checkHasName(new People("first"),new People("second"))

  val s1 = new Slary(10000)

  println(s1.getTax(List(s1.sinlgeChild,s1.personIncome)))

}
