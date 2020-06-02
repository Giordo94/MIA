package Model.Entity

sealed trait Stat {
  var value: Int

  val max: Int

  val min: Int

  def ++(qnt:Int): Unit

  def --(qnt:Int): Unit
}

case class StatImpl(override var value: Int,
                    override val max: Int,
                    override val min: Int) extends Stat {
  def this(value: Int) = this(value, value, 0)

  override def ++(qnt: Int): Unit = value = if(value+qnt > max) max else value+qnt

  override def --(qnt: Int): Unit = value = if(value-qnt < min) min else value-min
}

sealed trait Statistics {
  val hp: Stat
  val mp: Stat
  val exp: Stat
}

case class StatisticsImpl(override val hp: Stat,
                          override val mp: Stat,
                          override val exp: Stat) extends Statistics{
}
