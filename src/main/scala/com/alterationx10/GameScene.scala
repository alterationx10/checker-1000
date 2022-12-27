package com.alterationx10

import com.alterationx10.vm.BoardViewModel
import indigo.*
import indigo.scenes.*
import indigo.shared.scenegraph.Shape.Box
import indigoextras.ui.HitArea

import GameConstants.*

object GameScene extends Scene[StartupData, Unit, BoardViewModel]:

  type SceneModel     = Unit
  type SceneViewModel = BoardViewModel

  val name: SceneName =
    SceneName("game")

  val modelLens: Lens[Unit, Unit] =
    Lens.keepLatest

  val viewModelLens: Lens[BoardViewModel, BoardViewModel] =
    Lens.keepLatest

  val eventFilters: EventFilters =
    EventFilters.Permissive

  val subSystems: Set[SubSystem] =
    Set()

  def updateModel(
      context: SceneContext[StartupData],
      model: Unit
  ): GlobalEvent => Outcome[Unit] =
    _ => Outcome(model)

  def updateViewModel(
      context: SceneContext[StartupData],
      model: Unit,
      viewModel: BoardViewModel
  ): GlobalEvent => Outcome[BoardViewModel] =
    _ => Outcome(viewModel)

  def present(
      context: SceneContext[StartupData],
      model: Unit,
      viewModel: BoardViewModel
  ): Outcome[SceneUpdateFragment] =
    Outcome(
      SceneUpdateFragment(
        viewModel.tiles.map(_.background) ++ Batch
          .sequenceOption(
            viewModel.tiles.map(_.checker.map(_.background))
          )
          .getOrElse(Batch.empty)
      )
    )
