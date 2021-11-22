package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.UniverseService.runCalcNextMetaState;
import static de.schmiereck.col.services.UniverseService.runCalcNextState;
import static de.schmiereck.col.services.UniverseService.runLevelDown;
import static de.schmiereck.col.services.UniverseService.runLevelUp;
import static de.schmiereck.col.services.UniverseService.runNextUSDM;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.printCellsMinimal;
import static de.schmiereck.col.services.UniverseUtils.readCell;
import static de.schmiereck.col.services.UniverseUtils.readCellState;
import static de.schmiereck.col.services.UniverseUtils.setStatePos;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Universe;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_run_lev1move_is_called {

   @Test
   void GIVEN_lev1move_state_5_run_THEN_state_is_calculated_to_right() {
      // Arrange
      final int universeSize = 12;

      final Engine level0Engine = CreateLevel0EngineService.createLevel0staticEngine();
      final Engine level1Engine = CreateEngineService.createLevel1moveEngine();

      final Engine[] engine2Arr = new Engine[2];
      engine2Arr[0] = level0Engine;
      engine2Arr[1] = level1Engine;

      final Universe universe = new Universe(engine2Arr, universeSize);

      //setStatePos(universe, 5, 1, 2);  // 1move: 2    1   0   =>   2
      setStatePos(universe, 5, 1, 5);  // 1move: 5    1   0   =>   6

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0, "initial");
      runTestNextUpStateDownMeta(universe, 0);
      runTestNextUpStateDownMeta(universe, 1);
      runTestNextUpStateDownMeta(universe, 2);
      runTestNextUpStateDownMeta(universe, 3);
      runTestNextUpStateDownMeta(universe, 4);
      runTestNextUpStateDownMeta(universe, 5);
      runTestNextUpStateDownMeta(universe, 6);
      runTestNextUpStateDownMeta(universe, 7);

      // Assert
      //   7/1/0:             >   5| 5| 1     5| 5| 0 >   0| 0| 0     0| 0| 0 >   ...
      //   7/1/1: >   0| 0| 0     0| 0| 0 >  40| 0| 0    40| 0| 0 >   0| 0| 0     0| 0| 0 >   ...
      assertEquals(posState, readCellState(universe, 1, 1, 0));
      assertEquals(5, readCell(universe, 1, 1).statePos);
   }

   @Test
   void GIVEN_lev1move_state_3_run_THEN_state_is_calculated_to_left() {
      // Arrange
      final int universeSize = 12;

      final Engine level0Engine = CreateLevel0EngineService.createLevel0staticEngine();
      final Engine level1Engine = CreateEngineService.createLevel1moveEngine();

      final Engine[] engine2Arr = new Engine[2];
      engine2Arr[0] = level0Engine;
      engine2Arr[1] = level1Engine;

      final Universe universe = new Universe(engine2Arr, universeSize);

      //setStatePos(universe, 5, 1, 2);  // 1move: 2    1   0   =>   2
      setStatePos(universe, 5, 1, 3);  // 1move: 3    0   1   =>   4

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0, "initial");
      runTestNextUpStateDownMeta(universe, 0);
      runTestNextUpStateDownMeta(universe, 1);
      runTestNextUpStateDownMeta(universe, 2);
      runTestNextUpStateDownMeta(universe, 3);
      runTestNextUpStateDownMeta(universe, 4);
      runTestNextUpStateDownMeta(universe, 5);
      runTestNextUpStateDownMeta(universe, 6);
      runTestNextUpStateDownMeta(universe, 7);

      // Assert
      assertEquals(posState, readCellState(universe, 9, 1, 1));
      assertEquals(3, readCell(universe, 9, 1).statePos);
   }

   @Test
   void GIVEN_lev1move_state_3_5_run_THEN_state_is_calculated_to_collision() {
      // Arrange
      final int universeSize = 12;

      final Engine level0Engine = CreateLevel0EngineService.createLevel0staticEngine();
      final Engine level1Engine = CreateEngineService.createLevel1moveEngine();

      final Engine[] engine2Arr = new Engine[2];
      engine2Arr[0] = level0Engine;
      engine2Arr[1] = level1Engine;

      final Universe universe = new Universe(engine2Arr, universeSize);

      setStatePos(universe, 3, 1, 5);  // 1move: 5    1   0   =>   6
      setStatePos(universe, 7, 1, 3);  // 1move: 3    0   1   =>   4

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 24; cnt++) {
         runTestNextUpStateDownMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(posState, readCellState(universe, 3, 1, 0));
      assertEquals(nulState, readCellState(universe, 3, 1, 1));
      assertEquals(5, readCell(universe, 3, 1).statePos);

      assertEquals(nulState, readCellState(universe, 7, 1, 0));
      assertEquals(posState, readCellState(universe, 7, 1, 1));
      assertEquals(3, readCell(universe, 7, 1).statePos);
   }

   @Test
   void GIVEN_lev1move_state_3_2_5_run_THEN_state_is_calculated_to_collision() {
      // Arrange
      final int universeSize = 12;

      final Engine level0Engine = CreateLevel0EngineService.createLevel0staticEngine();
      final Engine level1Engine = CreateEngineService.createLevel1moveEngine();

      final Engine[] engine2Arr = new Engine[2];
      engine2Arr[0] = level0Engine;
      engine2Arr[1] = level1Engine;

      final Universe universe = new Universe(engine2Arr, universeSize);

      setStatePos(universe, 2, 1, 5);  // 1move: 5    1   0   =>   6
      setStatePos(universe, 5, 1, 2);  // 1move: 2    1   0   =>   1
      setStatePos(universe, 8, 1, 3);  // 1move: 3    0   1   =>   4

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 24; cnt++) {
         runTestNextUpStateDownMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(posState, readCellState(universe, 3, 1, 0));
      assertEquals(nulState, readCellState(universe, 3, 1, 1));
      assertEquals(5, readCell(universe, 3, 1).statePos);

      assertEquals(posState, readCellState(universe, 5, 1, 0));
      assertEquals(nulState, readCellState(universe, 5, 1, 1));
      assertEquals(2, readCell(universe, 5, 1).statePos);

      assertEquals(nulState, readCellState(universe, 9, 1, 0));
      assertEquals(posState, readCellState(universe, 9, 1, 1));
      assertEquals(3, readCell(universe, 9, 1).statePos);
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
