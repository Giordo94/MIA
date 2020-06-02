package Model.Entity

sealed trait Character {
  val stats: Statistics

  def damage(amount: Int): Unit

  def heal(amount: Int): Unit
}

case class CharacterImpl() extends Character {
  implicit def intToStat(i: Int): Stat = new StatImpl(10)

  override val stats: Statistics = StatisticsImpl(hp = 10, mp = 10, exp = 0)

  override def damage(amount: Int): Unit = stats.hp ++ amount

  override def heal(amount: Int): Unit = stats.hp -- amount
}
