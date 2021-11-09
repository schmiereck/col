package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.UniverseService.printCells;
import static de.schmiereck.col.services.UniverseService.readCell;
import static de.schmiereck.col.services.UniverseService.readCellState;
import static de.schmiereck.col.services.UniverseService.readCellStatePos;
import static de.schmiereck.col.services.UniverseService.runCalcNextMetaState;
import static de.schmiereck.col.services.UniverseService.runCalcNextState;
import static de.schmiereck.col.services.UniverseService.setStatePos;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Universe;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_runCalcNextMetaState_is_called {

   @Test
   void GIVEN_level1dyn_with_state1_THEN_state_like_expected() {
      // Arrange
      final int universeSize = 12;

      final Engine level1dynamicEngine = CreateEngineService.createLevel1dynamicEngine();

      final Engine[] engine1Arr = new Engine[1];
      engine1Arr[0] = level1dynamicEngine;

      final Universe universe = new Universe(engine1Arr, universeSize);

      //                  0          1          2          3          4          5          6          7          8          9         10         11
      //   0/0/0: ( 1| 1| 0    1| 1| 1) ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0) ( 9| 0| 0    9| 0| 0) ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0)
      //   0/0/1:            ( 9| 0| 0    9| 0| 0) ( 0| 0| 0    0| 0| 0) ( 1| 1| 0    1| 1| 1) ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0)
      setStatePos(universe, 0, 0, 0,  1); // 1: 0, 1
      setStatePos(universe, 5, 0, 0,  1); // 1: 0, 1

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0);
      //runCalcNextState(universe);
      runCalcNextMetaState(universe);
      printCells(universe, 1);

      // Assert
      //                  0          1          2          3          4          5          6          7          8          9         10         11
      //   1/0/0: ( 0| 0| 0    0| 0| 0) (18| 0| 0   18| 0| 0) ( 0| 0| 0    0| 0| 0) ( 2| 2| 1    2| 2| 0) ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0)
      //   1/0/1:            ( 2| 2| 1    2| 2| 0) ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0) (18| 0| 0   18| 0| 0) ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0)
      assertEquals(0, readCellStatePos(universe, -1, 0, 0));
      assertEquals(0, readCellStatePos(universe, 0, 0, 0));
      assertEquals(2, readCellStatePos(universe, 1, 0, 0));
      assertEquals(0, readCellStatePos(universe, 4, 0, 0));
      assertEquals(0, readCellStatePos(universe, 5, 0, 0));
      assertEquals(2, readCellStatePos(universe, 6, 0, 0));
      assertEquals(0, readCellStatePos(universe, 7, 0, 0));
      assertEquals(0, readCellStatePos(universe, 8, 0, 0));
      assertEquals(0, readCell(universe, -1, 0, 0).metaStatePos);
      assertEquals(0, readCell(universe, 0, 0, 0).metaStatePos);
      assertEquals(2, readCell(universe, 1, 0, 0).metaStatePos);
      assertEquals(18, readCell(universe, 2, 0, 0).metaStatePos);
      assertEquals(0, readCell(universe, 3, 0, 0).metaStatePos);
      assertEquals(0, readCell(universe, 4, 0, 0).metaStatePos);
      assertEquals(0, readCell(universe, 5, 0, 0).metaStatePos);
      assertEquals(2, readCell(universe, 6, 0, 0).metaStatePos);
      assertEquals(18, readCell(universe, 7, 0, 0).metaStatePos);
      assertEquals(0, readCell(universe, 8, 0, 0).metaStatePos);
      assertEquals(posState, readCellState(universe, 6, 0, 0));
      assertEquals(nulState, readCellState(universe, 6, 0, 1));
      assertEquals(nulState, readCellState(universe, 7, 0, 0));
      assertEquals(nulState, readCellState(universe, 7, 0, 1));
   }

   @Test
   void GIVEN_level1dyn_with_state2_THEN_state_like_expected() {
      // Arrange
      final int universeSize = 12;

      final Engine level1dynamicEngine = CreateEngineService.createLevel1dynamicEngine();

      final Engine[] engine1Arr = new Engine[1];
      engine1Arr[0] = level1dynamicEngine;

      final Universe universe = new Universe(engine1Arr, universeSize);

      //                  0          1          2          3          4          5          6          7          8          9         10         11
      //   0/0/0: ( 2| 2| 1    2| 2| 0) ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0) (18| 0| 0   18| 0| 0) ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0)
      //   0/0/1:            (18| 0| 0   18| 0| 0) ( 0| 0| 0    0| 0| 0) ( 2| 2| 1    2| 2| 0) ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0)
      setStatePos(universe, 0, 0, 0,  2); // 2: 1, 0
      setStatePos(universe, 5, 0, 0,  2); // 2: 1, 0

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0);
      //runCalcNextState(universe);
      runCalcNextMetaState(universe);
      printCells(universe, 1);

      // Assert
      //                  0          1          2          3          4          5          6          7          8          9         10         11
      //   1/0/0: ( 9| 0| 0    9| 0| 0) ( 0| 0| 0    0| 0| 0) ( 1| 1| 0    1| 1| 1) ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0)
      //   1/0/1:            ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0) ( 9| 0| 0    9| 0| 0) ( 0| 0| 0    0| 0| 0) ( 0| 0| 0    0| 0| 0) ( 1| 1| 0    1| 1| 1)
      assertEquals(1, readCellStatePos(universe, -1, 0, 0));
      assertEquals(0, readCellStatePos(universe, 0, 0, 0));
      assertEquals(1, readCellStatePos(universe, 4, 0, 0));
      assertEquals(0, readCellStatePos(universe, 5, 0, 0));
      assertEquals(9, readCell(universe, 0, 0, 0).metaStatePos);
      assertEquals(0, readCell(universe, 1, 0, 0).metaStatePos);
      assertEquals(1, readCell(universe, 4, 0, 0).metaStatePos);
      assertEquals(9, readCell(universe, 5, 0, 0).metaStatePos);
      assertEquals(0, readCell(universe, 6, 0, 0).metaStatePos);
      assertEquals(nulState, readCellState(universe, 5, 0, 0));
      assertEquals(nulState, readCellState(universe, 5, 0, 1));
      assertEquals(nulState, readCellState(universe, 4, 0, 0));
      assertEquals(posState, readCellState(universe, 4, 0, 1));
   }
}
