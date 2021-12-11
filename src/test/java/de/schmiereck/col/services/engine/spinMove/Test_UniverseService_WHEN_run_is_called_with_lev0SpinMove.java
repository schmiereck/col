package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.RunTestUtils.runTestNextUpStateMeta;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.readCell;
import static de.schmiereck.col.services.UniverseUtils.readCellState;
import static de.schmiereck.col.services.UniverseUtils.setStatePos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTb_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.NULL_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTb_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.STAYa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.STAYb_p1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Event;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.UniverseService;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_run_is_called_with_lev0SpinMove {

   @Test
   void GIVEN_lev0move_state_STAY_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      //final Engine level1Engine = CreateLevel1DynamicMoveEngineService.createLevel1DynamicMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;
      //engineArr[1] = level1Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      final Event event = new Event(null);
      //setStatePos(universe, 2, STAY_p1);
      setStatePos(universe, 2, STAYa_p1, event);

      UniverseService.calcInitialMetaStates(universe);
      //CreateLevel0DynamicMoveEngineService.initLevelUpOutputMetaStates(level0Engine, level1Engine);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 3; cnt++) {
         runTestNextUpStateMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, 1, 0, 0));
      assertEquals(posState, readCellState(universe, 2, 0, 0));
      assertEquals(nulState, readCellState(universe, 3, 0, 0));
      assertEquals(NULL_u0, readCell(universe, 1, 0).statePos);
      assertEquals(STAYb_p1, readCell(universe, 2, 0).statePos);
      assertEquals(NULL_u0, readCell(universe, 3, 0).statePos);

      assertEquals(event, readCell(universe, 2, 0).event);
   }

   @Test
   void GIVEN_lev0move_state_LEFT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      //final Engine level1Engine = CreateLevel1DynamicMoveEngineService.createLevel1DynamicMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;
      //engineArr[1] = level1Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      final Event event = new Event(null);
      setStatePos(universe, 2, LEFTa_p1, event);

      UniverseService.calcInitialMetaStates(universe);
      //CreateLevel0DynamicMoveEngineService.initLevelUpOutputMetaStates(level0Engine, level1Engine);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 2; cnt++) {
         runTestNextUpStateMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, -1, 0, 0));
      assertEquals(posState, readCellState(universe, 0, 0, 0));
      assertEquals(nulState, readCellState(universe, 1, 0, 0));
      assertEquals(NULL_u0, readCell(universe, -1, 0).statePos);
      assertEquals(LEFTb_p1, readCell(universe, 0, 0).statePos);
      assertEquals(NULL_u0, readCell(universe, 1, 0).statePos);

      assertEquals(event, readCell(universe, 0, 0).event);
   }

   @Test
   void GIVEN_lev0move_state_RIGHT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      //final Engine level1Engine = CreateLevel1DynamicMoveEngineService.createLevel1DynamicMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;
      //engineArr[1] = level1Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      setStatePos(universe, 2, RIGHTa_p1);

      UniverseService.calcInitialMetaStates(universe);
      //CreateLevel0DynamicMoveEngineService.initLevelUpOutputMetaStates(level0Engine, level1Engine);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 2; cnt++) {
         runTestNextUpStateMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, 3, 0, 0));
      assertEquals(posState, readCellState(universe, 4, 0, 0));
      assertEquals(nulState, readCellState(universe, 5, 0, 0));
      assertEquals(NULL_u0, readCell(universe, 3, 0).statePos);
      assertEquals(RIGHTb_p1, readCell(universe, 4, 0).statePos);
      assertEquals(NULL_u0, readCell(universe, 5, 0).statePos);
   }
}
