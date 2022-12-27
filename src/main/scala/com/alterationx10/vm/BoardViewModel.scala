package com.alterationx10.vm

import indigo.shared.Outcome
import indigo.shared.collections.Batch
import indigo.shared.input.Mouse
import indigoextras.ui.HitArea

final case class BoardViewModel(
    tiles: Batch[Tile]
) {
  def update(mouse: Mouse): Outcome[BoardViewModel] = {
    val updates = tiles.map { t =>
      for {
        th <- t.hitbox.update(mouse)
        ch <- Outcome.sequenceList(
          t.checker
            .map(
              _.hitbox.update(mouse).map(h => t.checker.map(_.copy(hitbox = h)))
            )
            .toList
        )
      } yield t.copy(
        hitbox = th,
        checker = ch.headOption.flatten
      )
    }
    Outcome.sequenceBatch(updates).map(t => BoardViewModel(t))
  }
}
