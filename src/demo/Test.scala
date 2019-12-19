package demo

import scala.collection.mutable.ArrayBuffer

/**
  * author : fanzhe 
  * email : fansy1990@foxmail.com
  * date : 2019/12/19 PM10:06.
  */
object Test {
//  val N = 3
  def getMore(sub: ArrayBuffer[String], N:Int) :ArrayBuffer[String]= {

    val tmp = ArrayBuffer[String]()

    1.to(N).foreach(x => tmp.appendAll(sub.map(y => y.replaceFirst("_N","_"+x))))

    tmp
  }

  def main(args: Array[String]): Unit = {
    val attributes = Array("AAAAA","BBBBBBBB"
      ,"CCCCCCCCCab_N","CCCCCCCCCac_N","CCCCCCCCCad_N"
      ,"DDDDDDDDab_N","DDDDDDDDac_N","DDDDDDDDad_N","DDDDDDDDae_N"
    ,"EEE")


    attributes.foreach(println(_))
    println("====================================")
    hand(attributes,3).foreach(println(_))
    println("====================================")

    hand(attributes,4).foreach(println(_))

  }

  def hand(attributes:Array[String],N:Int): ArrayBuffer[String] ={
    val all = ArrayBuffer[String]()

    val sub = ArrayBuffer[String]()

    var previous = attributes.find(_.contains("_N")).getOrElse("").substring(0,5)

    for(attri <- attributes){
      if(attri.contains("_N")){
        if(attri.substring(0,5)!= previous) {
          all.append(getMore(sub,N): _*)
          sub.clear()
        }
        sub.append(attri)
        previous = attri.substring(0,5)
      }else{
        if(sub.length >0){
          all.append(getMore(sub,N):_*)
          sub.clear()
        }
        all.append(attri)
      }
    }
    all
  }
}
