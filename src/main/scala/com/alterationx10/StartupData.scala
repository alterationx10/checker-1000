package com.alterationx10

import com.alterationx10.vm.Tile
import indigo.shared.collections.*
import indigo.shared.datatypes.*
import indigo.shared.scenegraph.Shape
import indigoextras.geometry.*
import indigoextras.ui.*

import GameConstants.*
import events.*

final case class StartupData() {

  def genTiles: Batch[Tile] = tilePoints.map { p =>
    val yEven = (p.y / tileSize) % 2 == 0
    val xEven = (p.x / tileSize) % 2 == 0
    val isRed = (yEven && xEven) || (!yEven && !xEven)
    val color: RGBA = RGBA.fromHexString {
      if isRed then C64.Red.hex else C64.Black.hex
    }
    Tile(
      checker = None,
      background = Shape.Box(
        Rectangle(p.x, p.y, tileSize, tileSize),
        Fill.Color(color)
      ),
      hitbox = HitArea(
        Polygon.fromRectangle(Rectangle(p.x, p.y, tileSize, tileSize))
      ).withClickActions(
        Log(s"(${p.x / tileSize},${p.y / tileSize}) was clicked!")
      )
    )

  }
}
