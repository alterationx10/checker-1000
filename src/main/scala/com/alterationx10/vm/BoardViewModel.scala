package com.alterationx10.vm

import indigo.shared.Outcome
import indigo.shared.collections.Batch
import indigo.shared.input.Mouse

final case class BoardViewModel(
    tiles: Batch[Tile]
) {
  def update(mouse: Mouse): Outcome[BoardViewModel] = {
    val _tiles = tiles
      .map(t => t.hitbox.update(mouse).map(h => t.copy(hitbox = h)))
      .sequence
    _tiles.map(t => BoardViewModel(t))
  }
}
