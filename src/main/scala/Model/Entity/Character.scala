package Model.Entity

sealed trait Character {
  val stats:Stats
  def damage(amount:Int):CharacterImpl
  def heal(amount:Int):CharacterImpl
}
case class CharacterImpl(override val stats: Stats) extends Character {
  override def damage(amount:Int):CharacterImpl = CharacterImpl(stats ++ amount)
  override def heal(amount:Int):CharacterImpl = CharacterImpl(stats -- amount)
}
