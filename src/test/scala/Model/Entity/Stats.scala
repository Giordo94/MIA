package Model.Entity

import org.scalatest._

class Stats extends FlatSpec{
  "A Stat" should "not exceed it's max value when incremented" in {
    val value = 10
    val stat = new StatImpl(value)
    stat ++ value
    assert(stat.value equals value)
  }
  it should "not decrease under it's min value" in {
    val value = 10
    val stat = new StatImpl(value)
    stat -- value*2
    assert(stat.value equals 0)
  }
  it should "be negative only if in it's range" in {
    val value = 10
    val stat = StatImpl(10, 20, -10)
    stat -- (15)
    assert(stat.value > -10)
    assert(stat.value < 0)
  }
}
