package Model.World

sealed trait Tile {
  val walkable:Boolean

  val spriteId:Int

  //val position:(Int,Int)
}
case class TileImpl(override val spriteId: Int,
                    override val walkable: Boolean) extends Tile {
}
