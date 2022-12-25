package com.alterationx10.vm

import indigo.shared.datatypes.RGBA
import indigo.shared.scenegraph.Shape.Circle
import indigoextras.ui.HitArea

final case class Checker(
    color: RGBA,
    background: Circle,
    hitbox: HitArea
)
