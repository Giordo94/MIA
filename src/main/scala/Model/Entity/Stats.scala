package Model.Entity

sealed trait Stat {
  val value:Int

  def ++(qnt:Int):Stat

  def --(qnt:Int):Stat
}

case class StatImpl(override val value: Int) extends Stat {
  override def ++(qnt: Int): Stat = StatImpl(value+qnt)

  override def --(qnt: Int): Stat = StatImpl(value-qnt)
}

sealed trait Statistics {
  val hp:Stat
  val maxHp:Stat
  def incHp(amount:Int):Statistics
  def decHp(amount:Int):Statistics
}

case class StatisticsImpl(override val hp: Stat,
                          override val maxHp: Stat) extends Statistics{
  override def incHp(amount: Int): Statistics = {
    val tmp = hp ++ amount
    StatisticsImpl(if(tmp.value>maxHp.value) maxHp else tmp, maxHp)
  }

  override def decHp(amount: Int): Statistics = {
    val tmp = hp -- amount
    StatisticsImpl(if(tmp.value<0) StatImpl(0) else tmp, maxHp)
  }
}
