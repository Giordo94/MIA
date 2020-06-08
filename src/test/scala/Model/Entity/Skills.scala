package Model.Entity

import org.scalatest._

class Skills extends FlatSpec {
  "A Skill" should "start at lvl 0, exp 0 and nxtLvl 10" in {
    val skill: Skill = SkillImpl()
    assert(skill.lvl == 0)
    assert(skill.experience == 0)
    assert(skill.expNextLvl == 10)
  }
  it should "inc exp and gain levels" in {
    val skill: Skill = SkillImpl()
    skill ++ 15
    assert(skill.lvl == 1)
    assert(skill.experience == 5)
    assert(skill.expNextLvl == 11)
  }
  it should "gain more levels at once" in {
    val skill: Skill = SkillImpl()
    skill ++ 25
    assert(skill.lvl == 2)
    assert(skill.experience == 4)
    assert(skill.expNextLvl == 12)
  }
  it should "not lose levels" in {
    val skill: Skill = SkillImpl()
    skill -- 15
    assert(skill.lvl == 0)
    assert(skill.experience == 0)
    assert(skill.expNextLvl == 10)
    skill ++ 15
    skill -- 47
    assert(skill.lvl == 1)
    assert(skill.experience == 0)
    assert(skill.expNextLvl == 11)
  }
}