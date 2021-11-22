package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.CreateLevel0EngineService.LEFT2_p1;
import static de.schmiereck.col.services.CreateLevel0EngineService.LEFT_p1;
import static de.schmiereck.col.services.CreateLevel0EngineService.NULL_u0;
import static de.schmiereck.col.services.CreateLevel0EngineService.RIGHT2_p1;
import static de.schmiereck.col.services.CreateLevel0EngineService.RIGHT_p1;
import static de.schmiereck.col.services.CreateLevel0EngineService.STAY_n1;
import static de.schmiereck.col.services.CreateLevel0EngineService.STAY_p1;
import static de.schmiereck.col.services.UniverseService.runCalcNextMetaState;
import static de.schmiereck.col.services.UniverseService.runCalcNextState;
import static de.schmiereck.col.services.UniverseService.runLevelDown;
import static de.schmiereck.col.services.UniverseService.runLevelUp;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.readCell;
import static de.schmiereck.col.services.UniverseUtils.readCellState;
import static de.schmiereck.col.services.UniverseUtils.setStatePos;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Universe;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_run_lev0move_is_called {

   @Test
   void GIVEN_lev0move_state_STAY_p1_next_STAY_n1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0EngineService.createLevel0moveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      //setStatePos(universe, 2, 0, STAY_p1);
      setStatePos(universe, 0, 0, STAY_p1);
      setStatePos(universe, 1, 0, STAY_n1);

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 3; cnt++) {
         runTestNextUpStateDownMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, 0, 0, 0));
      assertEquals(nulState, readCellState(universe, 1, 0, 0));
      assertEquals(NULL_u0, readCell(universe, 0, 0).statePos);
      assertEquals(NULL_u0, readCell(universe, 1, 0).statePos);
   }

   @Test
   void GIVEN_lev0move_state_RIGHT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0EngineService.createLevel0moveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      //setStatePos(universe, 2, 0, STAY_p1);
      setStatePos(universe, 0, 0, RIGHT_p1);

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 3; cnt++) {
         runTestNextUpStateDownMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, 2, 0, 0));
      assertEquals(posState, readCellState(universe, 3, 0, 0));
      assertEquals(nulState, readCellState(universe, 4, 0, 0));
      assertEquals(NULL_u0, readCell(universe, 2, 0).statePos);
      assertEquals(RIGHT2_p1, readCell(universe, 3, 0).statePos);
      assertEquals(NULL_u0, readCell(universe, 4, 0).statePos);
   }

   @Test
   void GIVEN_lev0move_state_LEFT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0EngineService.createLevel0moveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      setStatePos(universe, 3, 0, LEFT_p1);

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 3; cnt++) {
         runTestNextUpStateDownMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, -1, 0, 0));
      assertEquals(posState, readCellState(universe, 0, 0, 0));
      assertEquals(nulState, readCellState(universe, 1, 0, 0));
      assertEquals(NULL_u0, readCell(universe, -1, 0).statePos);
      assertEquals(LEFT2_p1, readCell(universe, 0, 0).statePos);
      assertEquals(NULL_u0, readCell(universe, 1, 0).statePos);
   }

   public static void runTestNextUpStateDownMeta(final Universe universe, final int cnt) {
      runLevelUp(universe);
      printCells(universe, cnt, "runLevelUp");
      runCalcNextState(universe);
      printCells(universe, cnt, "runCalcNextState");
      runLevelDown(universe);
      printCells(universe, cnt, "runLevelDown");
      runCalcNextMetaState(universe);
      printCells(universe, cnt, "runCalcNextMetaState");
   }
}
