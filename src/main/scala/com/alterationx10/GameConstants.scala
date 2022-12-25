package com.alterationx10

import indigo.shared.collections.Batch
import indigo.shared.datatypes.Point

object GameConstants {

  val gameWidth: Int  = 1280
  val gameHeight: Int = 720

  val tileSize: Int = 64

  val tilePoints: Batch[Point] = Batch.fromIndexedSeq {
    for {
      x <- 0 to 7
      y <- 0 to 7
    } yield Point(x * tileSize, y * tileSize)
  }

}
