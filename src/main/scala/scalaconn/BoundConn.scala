package scalaconn

/**
  * Created by adimn on 2019/5/23.
  */
class BoundConn {

}

object BoundConn{

  def upperBound={
    val p1 = new Person("ang")
    val s1 = new MyStudent("qing")

    p1.makeFriend(s1)

    val d1 = new Dog("wangcai","male")

    val party1 = new Party[Person]

    party1.play(p1,s1)
  }

  def getIdCard[T >:Father](p:T)={
    if(p.getClass == classOf[Father]){
      println("you can have card")
    }else if( p.getClass == classOf[Child]){
      println("child can't have card")
    }
  }

  def checkCard={
    val f1 = new Father("Potter")
    val ch1 = new Child("Harry")
    getIdCard(f1)
    getIdCard(ch1)
  }

  implicit def dog2person(dog:Object):Person = {
    if(dog.getClass == classOf[Dog]){
      val _dog = dog.asInstanceOf[Dog]
      new Person(_dog.name)
    }else{
      Nil.asInstanceOf[Person]
    }
  }

  def viewBound = {
    val  p2 = new Party2[Person]
    val p1 = new Person("lili")
    val d1 = new Dog("qiang","male")
    p2.play(p1,d1)
  }
}

class Person(val name:String){
  def sayHello()={
    println("my name is :"+ name)
  }
  def makeFriend(person: Person)={
    println(name +" is make friends with "+person.name )
  }
}

class Party[T <: Person]{
  def play(p1:T,p2:T)={
    p1.makeFriend(p2)
  }
}

class Party2[T <% Person]{
  def play(p1:T,p2:T): Unit ={
    p1.makeFriend(p2)
  }
}

class MyStudent(name:String) extends Person(name)

class Dog(val name:String,val gender:String){
  def sayHello={
    println("wang! i am "+ name)
  }
  def makeFriend(person: Person)={
    println(name +" is make friends with "+person.name )
  }
}


class Father(name:String) extends Person(name)

class Child(name:String) extends Father(name)


object Main extends App{

//   上边界问题
  BoundConn.upperBound

  // 下边界
  BoundConn.checkCard

//  view Bounds
BoundConn.viewBound
}


