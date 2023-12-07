import scala.io.Source;
import scala.collection.immutable.Vector;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ArrayBuffer;
import scala.io.BufferedSource

@main
def main() = {

  println(hoare1(25))
  var timeVec: Vector[Long] = Vector();
  var distVec: Vector[Long] = Vector();

  getInput(6, false)
    .getLines()
    .foreach(x => {
      val resultVec = extractNumbers(x).toVector;
      if (timeVec == Vector()) {
        timeVec = resultVec;
      } else {
        distVec = resultVec;
      }
    })
  println(timeVec);
  println(distVec);
  println(calcAllSolutions(timeVec, distVec))
}

def calcAllSolutions(timeVec: Vector[Long], distVec: Vector[Long]): Long = {
  var sum: Long = 1;
  for (i <- Range(0, timeVec.size)) {
    sum *= calcWinnableSolutions(timeVec(i), distVec(i));
  }
  return sum;
}

def calcDistance(timeToPress: Long, time: Long): Long = {
  val speed = timeToPress;
  val remainingTime = time - timeToPress
  return remainingTime * speed;
}
def calcWinnableSolutions(time: Long, maxDist: Long): Long = {
  var amountWinnable = 0;
  var firstFound = false;
  for (i <- Range.Long(0, time + 1, 1)) {
    if (calcDistance(i, time) > maxDist) {
      firstFound = true;
      amountWinnable += 1;
    } else {
      if (firstFound) {
        return amountWinnable;
      }
    }
  }
  return amountWinnable;
}

def hoare1(n: Int): Int = {
  var i = 0;
  var li = 0;
  var re = 0;
  if (n < 2) {
    i = n;
  } else {
    li = 0;
    re = n;

    while (li + 1 < re) {
      val m = (li + re) / 2;
      if ((m * m) <= n) {
        li = m;
      } else {
        re = m;
      }
    }
    i = li;
  }
  return i;
}

def getInput(day: Long, mock: Boolean): BufferedSource = {
  return Source
    .fromFile(
      s"/home/sd/programmierung/scala/aoc/src/resources/day${day}${if (mock) { "mock" }
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
