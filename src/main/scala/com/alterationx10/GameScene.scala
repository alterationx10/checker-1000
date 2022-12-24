package com.alterationx10

import indigo.*
import indigo.scenes.*

object GameScene extends Scene[Unit, Unit, Unit]:

  private val tileSize: Int = 64

  type SceneModel     = Unit
  type SceneViewModel = Unit

  val name: SceneName =
    SceneName("game")

  val modelLens: Lens[Unit, Unit] =
    Lens.keepLatest

  val viewModelLens: Lens[Unit, Unit] =
    Lens.keepLatest

  val eventFilters: EventFilters =
    EventFilters.Permissive

  val subSystems: Set[SubSystem] =
    Set()

  def updateModel(
      context: SceneContext[Unit],
      model: Unit
  ): GlobalEvent => Outcome[Unit] =
    _ => Outcome(model)

  def updateViewModel(
      context: SceneContext[Unit],
      model: Unit,
      viewModel: Unit
  ): GlobalEvent => Outcome[Unit] =
    _ => Outcome(viewModel)

  private def genTiles = for {
    x <- 0 to 7
    y <- 0 to 7
  } yield {
    val isShifted = y % 2 == 0
    val isBlack   = x % 2 != 0
    val offset    = if isShifted then 0 else tileSize
    if (isBlack) {
      Shape
        .Box(
          Rectangle(0, 0, tileSize, tileSize),
          Fill.Color(RGBA.fromHexString(C64.Black.hex))
        )
        .moveTo(x * tileSize - offset, y * tileSize)
    } else {
      Shape
        .Box(
          Rectangle(0, 0, tileSize, tileSize),
          Fill.Color(RGBA.fromHexString(C64.Red.hex))
        )
        .moveTo(x * tileSize + offset, y * tileSize)
    }
  }

  def present(
      context: SceneContext[Unit],
      model: Unit,
      viewModel: Unit
  ): Outcome[SceneUpdateFragment] =
    Outcome(
      SceneUpdateFragment(
        Batch.fromIndexedSeq(
          genTiles
        )
      )
    )
