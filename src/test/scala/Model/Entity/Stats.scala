package Model.Entity

import org.scalatest._

class Stats extends FlatSpec{
  "A Stat" should "not exceed it's max value when incremented" in {
    val value = 10
    val stat = new StatImpl(value)
    stat ++ value
    assert(stat.value equals value)
  }
  it should "have the value assigned" in {
    val value = 10
    val stat = StatImpl(value,value*2,-value)
    assert(stat.value == value)
    assert(stat.max == 20)
    assert(stat.min == -10)
  }
  it should "have the default max and min value" in {
    val value = 10
    val stat = new StatImpl(value)
    assert(stat.max == value)
    assert(stat.min == 0)
  }
  it should "not decrease under it's min value" in {
    val value = 10
    val stat = new StatImpl(value)
    stat -- (value*2)
    assert(stat.value == 0)
  }
  it should "be negative only if in it's range" in {
    val value = 10
    val stat = StatImpl(value, value*2, -value)
    stat -- 15
    assert(stat.value == -5)
    assert(stat.max == 20)
    assert(stat.min == -10)
  }
}

class ComposedStats extends FlatSpec {
  "A composed stat" should "throw error if non stats added" in {
    assert(ComposedStatImpl().value() == 0)
  }
  it should "add new stats" in {
    val comp = ComposedStatImpl()
    comp ++ (new StatImpl(10), 0.5)
    assert(comp.value() == 5)
    comp ++ (new StatImpl(10),0.3)
    assert(comp.value() == 8)
  }
}
