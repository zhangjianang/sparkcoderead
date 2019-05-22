package scalaconn
import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
/**
  * Created by adimn on 2019/5/22.
  */
class MyActor(var name:String) extends Actor{
  def this(){
    this("ang")
  }

  override def receive: Receive = {
    case info:String => println(info)
    case stu:Student => println(stu.name+"--"+stu.age)
      this.name = stu.name
    case re:Responde => println(re.info)
      re.reInfo ! this.name + "say: got info"

  }
}

//object MyActor {
//  def main(args: Array[String]) {
////    println("this is my actor")
//    val system = ActorSystem()
//    val ac = system.actorOf(Props[MyActor])
//    ac ! "hello!"
//  }
//}
case class Student(name:String,age:Int)
case class Responde(info:String,reInfo:ActorRef)

class MyRespond extends Actor{
  override def receive: Receive = {
    case info:String => println("in MyRespond: "+info)
  }
}

object Main extends App {
  val system = ActorSystem()
  val ac = system.actorOf(Props[MyActor])
  val re =system.actorOf(Props[MyRespond])
  ac ! "hello!"
  ac ! Student("ang",10)
  ac ! Responde("i am new",re)
}