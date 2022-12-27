package com.alterationx10

import com.alterationx10.vm.Checker
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
    val yEven  = (p.y / tileSize) % 2 == 0
    val xEven  = (p.x / tileSize) % 2 == 0
    val isRed  = (yEven && xEven) || (!yEven && !xEven)
    val center = p + Point(p.x + tileSize / 2, p.y + tileSize / 2)
    val checkerColor = RGBA.fromHexString {
      if p.y / tileSize < 4 then C64.Brown.hex else C64.LightGray.hex
    }
    val color: RGBA = RGBA.fromHexString {
      if isRed then C64.Red.hex else C64.Black.hex
    }
    Tile(
      checker = Some(
        Checker(
          color = checkerColor,
          background = Shape.Circle(
            p + Point(tileSize / 2),
            tileSize / 3,
            Fill.Color(checkerColor)
          ),
          hitbox = HitArea(
            Polygon.fromRectangle(Rectangle(p.x, p.y, tileSize, tileSize))
          ).withClickActions(
            Log(s"Checker (${p.x / tileSize},${p.y / tileSize}) was clicked!")
          )
        )
      ).filter(_ => !isRed && Set(0, 1, 6, 7).contains(p.y / tileSize)),
      background = Shape
        .Box(
          Rectangle(p.x, p.y, tileSize, tileSize),
          Fill.Color(color)
        )
        .withDepth(Depth.far),
      hitbox = HitArea(
        Polygon.fromRectangle(Rectangle(p.x, p.y, tileSize, tileSize))
      ).withClickActions(
        Log(s"Tile (${p.x / tileSize},${p.y / tileSize}) was clicked!")
      )
    )

  }
}
