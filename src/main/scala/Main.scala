import scala.io.Source;
import scala.collection.immutable.Vector;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ArrayBuffer;

import scala.io.BufferedSource

def getInput(day: Long, mock: Boolean): BufferedSource = {
  return Source
    .fromFile(
      s"/home/:>)/programmierung/scala/aoc/src/resources/day${day}${if (mock) { "mock" }
        else { "" }}.txt"
    )
}

def printVec(vec: Vector[Vector[Char]]): Unit = {
  vec.foreach(x => {
    println(x);
  })
}

def isDigit(char: Char): Boolean = {
  return char >= '0' && char <= '9';
}

def extractNumbers(s: String): List[Long] = {
  var retList: List[Long] = List();
  var currNumber = "";
  s.foreach(x => {
    if (x >= '0' && x <= '9') {
      currNumber += x;
    } else {
      if (currNumber != "") {
        retList = retList ::: List(currNumber.toLong);
      }
      currNumber = "";
    }
  })
  if (currNumber != "") {
    retList = retList ::: List(currNumber.toLong);
  }
  return retList;
}
