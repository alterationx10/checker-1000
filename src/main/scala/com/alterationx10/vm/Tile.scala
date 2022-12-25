package com.alterationx10.vm

import indigo.shared.scenegraph.Shape.Box
import indigoextras.ui.HitArea

final case class Tile(
    checker: Option[Checker],
    background: Box,
    hitbox: HitArea
)
