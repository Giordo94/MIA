package Model.Entity

sealed trait Skill {
  var experience: Int
  var expNextLvl: Int
  var lvl: Int
  def ++(exp: Int): Unit
  def --(exp: Int): Unit
}

case class SkillImpl() extends Skill{
  override var lvl: Int = 0

  override var experience: Int = 0

  override var expNextLvl: Int = 10

  override def ++(exp: Int): Unit = {
    experience = exp + experience
    while(experience >= expNextLvl){
      experience = experience - expNextLvl
      expNextLvl = (expNextLvl * 1.1).toInt
      lvl = lvl + 1
    }
  }

  override def --(exp: Int): Unit = {
    experience = experience - exp
    if(experience < 0)
      experience = 0
  }
}

case class SkillSet() {
  val chopping: Skill = SkillImpl()

  val mining: Skill = SkillImpl()

  val digging: Skill = SkillImpl()

  val strength: Skill = SkillImpl()
}