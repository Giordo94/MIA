package Model.World

sealed trait Map {
  val tiles: List[Tile]
  def interact(): Unit
}

class MapImpl extends Map{
  override val tiles: List[Tile] = List.empty

  override def interact(): Unit = {}
}
