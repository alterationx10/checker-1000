package com.alterationx10

import com.alterationx10.vm.BoardViewModel
import com.alterationx10.vm.Tile
import indigo.IndigoLogger.*
import indigo.*
import indigo.scenes.*
import indigo.shared.scenegraph.Shape.Box
import indigoextras.geometry.Polygon
import indigoextras.geometry.Vertex
import indigoextras.ui.HitArea

import scala.scalajs.js.annotation.JSExportTopLevel

import events.*
import GameConstants.*

@JSExportTopLevel("IndigoGame")
object Checker1000 extends IndigoGame[Unit, StartupData, Unit, BoardViewModel]:

  def initialScene(bootData: Unit): Option[SceneName] =
    None

  def scenes(
      bootData: Unit
  ): NonEmptyList[Scene[StartupData, Unit, BoardViewModel]] =
    NonEmptyList(GameScene)

  val eventFilters: EventFilters =
    EventFilters.Permissive

  def boot(flags: Map[String, String]): Outcome[BootResult[Unit]] =
    Outcome(
      BootResult
        .noData(
          GameConfig.default
            .withViewport(GameConstants.gameWidth, GameConstants.gameHeight)
        )
    )

  def initialModel(startupData: StartupData): Outcome[Unit] =
    Outcome(())

  def initialViewModel(
      startupData: StartupData,
      model: Unit
  ): Outcome[BoardViewModel] =
    Outcome(BoardViewModel(tiles = startupData.genTiles))

  def setup(
      bootData: Unit,
      assetCollection: AssetCollection,
      dice: Dice
  ): Outcome[Startup[StartupData]] =
    Outcome(Startup.Success(StartupData()))

  def updateModel(
      context: FrameContext[StartupData],
      model: Unit
  ): GlobalEvent => Outcome[Unit] = {
    case e @ MouseEvent.Click(p) =>
      consoleLog(s"clicked $p")
      Outcome(model)
    case _ => Outcome(model)
  }
  def updateViewModel(
      context: FrameContext[StartupData],
      model: Unit,
      viewModel: BoardViewModel
  ): GlobalEvent => Outcome[BoardViewModel] = {
    case l: Log =>
      consoleLog(l.message)
      Outcome(viewModel)
    case m @ MouseEvent.Click(p) =>
      viewModel.update(context.mouse)
    case _ =>
      Outcome(viewModel)
  }
  def present(
      context: FrameContext[StartupData],
      model: Unit,
      viewModel: BoardViewModel
  ): Outcome[SceneUpdateFragment] =
    Outcome(SceneUpdateFragment.empty)
