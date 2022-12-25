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

import GameConstants.*

@JSExportTopLevel("IndigoGame")
object Checker1000 extends IndigoGame[Unit, Unit, Unit, BoardViewModel]:

  def initialScene(bootData: Unit): Option[SceneName] =
    None

  def scenes(bootData: Unit): NonEmptyList[Scene[Unit, Unit, BoardViewModel]] =
    NonEmptyList(GameScene)

  val eventFilters: EventFilters =
    EventFilters.Permissive

  def boot(flags: Map[String, String]): Outcome[BootResult[Unit]] =
    Outcome(
      BootResult
        .noData(
          GameConfig.default
            .withViewport(1280, 720)
        )
    )

  def initialModel(startupData: Unit): Outcome[Unit] =
    Outcome(())

  final case class Log(message: String) extends GlobalEvent
  def initialViewModel(
      startupData: Unit,
      model: Unit
  ): Outcome[BoardViewModel] = {

    // TODO this should be in startupData
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

    Outcome(BoardViewModel(tiles = genTiles))
  }

  def setup(
      bootData: Unit,
      assetCollection: AssetCollection,
      dice: Dice
  ): Outcome[Startup[Unit]] =
    Outcome(Startup.Success(()))

  def updateModel(
      context: FrameContext[Unit],
      model: Unit
  ): GlobalEvent => Outcome[Unit] = {
    case e @ MouseEvent.Click(p) =>
      consoleLog(s"clicked $p")
      Outcome(model)
    case _ => Outcome(model)
  }
  def updateViewModel(
      context: FrameContext[Unit],
      model: Unit,
      viewModel: BoardViewModel
  ): GlobalEvent => Outcome[BoardViewModel] = {
    case l: Log =>
      consoleLog(l.message)
      viewModel.update(context.mouse)
    case _ => viewModel.update(context.mouse)
  }
  def present(
      context: FrameContext[Unit],
      model: Unit,
      viewModel: BoardViewModel
  ): Outcome[SceneUpdateFragment] =
    Outcome(SceneUpdateFragment.empty)
