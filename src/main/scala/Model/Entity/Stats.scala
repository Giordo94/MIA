package Model.Entity

import scala.collection.immutable.HashMap

sealed trait Stat {
  var value: Int

  val max: Int

  val min: Int
  //increment stat
  def ++(qnt:Int): Unit
  //decrement stat
  def --(qnt:Int): Unit
}

case class StatImpl(override var value: Int,
                    override val max: Int,
                    override val min: Int) extends Stat {
  def this(value: Int) = this(value, value, 0)

  override def ++(qnt: Int): Unit = value = if(value+qnt > max) max else value+qnt

  override def --(qnt: Int): Unit = value = if(value-qnt < min) min else value-qnt
}

sealed trait ComposedStat {
  protected var statMap:Map[Stat,Double]
  def value(): Int
  //add
  def ++(stat: Stat, weight: Double): Unit
  //remove
  def --(stat: Stat): Unit
  //modify
  def -+(stat: Stat, weight: Double): Unit
}

case class ComposedStatImpl() extends ComposedStat {
  override protected var statMap: Map[Stat, Double] = HashMap.empty[Stat, Double]

  override def value(): Int = statMap.map(kv => kv._1.value * kv._2).sum.toInt

  override def ++(stat: Stat, weight: Double): Unit = statMap = statMap + (stat -> weight)

  override def --(stat: Stat): Unit = statMap = statMap - stat

  override def -+(stat: Stat, weight: Double): Unit = if(statMap.contains(stat)) ++(stat, weight)
}

sealed trait Statistics {
  val hp: Stat
  val mp: Stat
  val experience: Stat
  val strength:Stat
  val physicalDamage: ComposedStat
}

case class StatisticsImpl(override val hp: Stat,
                          override val mp: Stat,
                          override val experience: Stat,
                          override val strength: Stat) extends Statistics{
  override val physicalDamage: ComposedStat = ComposedStatImpl()
  physicalDamage ++ (strength,0.5)
}
