package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.UniverseService.calcMetaStatePosByStatePosForNeighbours;
import static de.schmiereck.col.services.UniverseService.calcNextStatePosByMetaStatePos;
import static de.schmiereck.col.services.UniverseService.runCalcNextMetaState;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.readCell;
import static de.schmiereck.col.services.UniverseUtils.readCellState;
import static de.schmiereck.col.services.UniverseUtils.readCellStatePos;
import static de.schmiereck.col.services.UniverseUtils.setStatePos;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.engine.CreateEngineService;
import de.schmiereck.col.services.engine.dyna.CreateLevel1DynamicEngineService;
import de.schmiereck.col.services.engine.dyna.CreateLevel2DynamicEngineService;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_runCalcNextMetaState_is_called {

   @Test
   void GIVEN_level1dyn_with_state1_THEN_state_like_expected() {
      // Arrange
      final int universeSize = 12;

      final Engine level1dynamicEngine = CreateLevel1DynamicEngineService.createLevel1dynamicEngine();

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

      final Engine level1dynamicEngine = CreateLevel1DynamicEngineService.createLevel1dynamicEngine();

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
      runCalcNextMetaState(universe);
      printCells(universe, 2);
      runCalcNextMetaState(universe);
      printCells(universe, 2);

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

   @Test
   void GIVEN_level_012dyn_run2_2x_pos_states_in_different_directions_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 12;

      // Engine Level 2 (dynamic):
      final Engine level2dynamicEngine = CreateLevel2DynamicEngineService.createLevel2dynamicEngine();

      final Engine[] engine3Arr = new Engine[1];
      engine3Arr[0] = level2dynamicEngine;

      final Universe universe = new Universe(engine3Arr, universeSize);

      setStatePos(universe, 3, 0, 0,  3);   // l2dyn 3: 1, 0, 0
      setStatePos(universe, 6, 0, 0,  1);   // l2dyn 1: 0, 0, 1

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0);
      runCalcNextMetaState(universe);
      printCells(universe, 1);
      runCalcNextMetaState(universe);
      printCells(universe, 1);
      runCalcNextMetaState(universe);
      printCells(universe, 1);
      runCalcNextMetaState(universe);
      printCells(universe, 1);

      // Assert
      assertEquals(3, readCellStatePos(universe, 3, 0, 0), "meta state should be 3, 4, 1, 2, 3");
      assertEquals(1, readCellStatePos(universe, 6, 0, 0), "meta state should be 1, 2, 3, 4, 1");

      assertEquals(posState, readCellState(universe, 3, 0, 0), "meta state should be 3, 4, 1, 2, 3");
      assertEquals(posState, readCellState(universe, 6, 0, 2), "meta state should be 1, 2, 3, 4, 1");
   }

   @Test
   void GIVEN_level2dynamicEngine_with_3_states_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      // Engine Level 2 (dynamic):
      final Engine level2dynamicEngine = new Engine(3, 3);

      level2dynamicEngine.setState( 0, new State(3, nulState, nulState, nulState), 1);
      level2dynamicEngine.setState( 1, new State(3, nulState, nulState, nulState), 2);
      level2dynamicEngine.setState( 2, new State(3, nulState, nulState, nulState), 0);

      CreateEngineService.initMetaStateArr(level2dynamicEngine);
      CreateEngineService.initOutputMetaStatePos(level2dynamicEngine);

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level2dynamicEngine;

      final Universe universe = new Universe(engineArr, universeSize);

      //setStatePos(universe, 2, 0, 0,  1);   // l2dyn 3: 0, 0, 0
      //setStatePos(universe, 6, 0, 0,  2);   // l2dyn 1: 0, 0, 0
      setStatePos(universe, 3, 0, 0,  1);   // l2dyn 1: 0, 0, 0

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0, "initial");
      calcNextStatePosByMetaStatePos(universe.engineArr[0], universe.levelArr[0], 2);
      calcMetaStatePosByStatePosForNeighbours(universe.engineArr[0], universe.levelArr[0], 2);
      printCells(universe, 1);
      calcNextStatePosByMetaStatePos(universe.engineArr[0], universe.levelArr[0], 3);
      calcMetaStatePosByStatePosForNeighbours(universe.engineArr[0], universe.levelArr[0], 3);
      printCells(universe, 2);
      calcNextStatePosByMetaStatePos(universe.engineArr[0], universe.levelArr[0], 3);
      calcMetaStatePosByStatePosForNeighbours(universe.engineArr[0], universe.levelArr[0], 3);
      printCells(universe, 3);
      /*
      printCells(universe, 0);
      calcNextStatePosByMetaStatePos(universe.engineArr[0], universe.levelArr[0], 2);
      calcMetaStatePosByStatePosForNeighbours(universe.engineArr[0], universe.levelArr[0], 2);
      printCells(universe, 1);
      calcNextStatePosByMetaStatePos(universe.engineArr[0], universe.levelArr[0], 2);
      calcMetaStatePosByStatePosForNeighbours(universe.engineArr[0], universe.levelArr[0], 2);
      printCells(universe, 2);
      calcNextStatePosByMetaStatePos(universe.engineArr[0], universe.levelArr[0], 2);
      calcMetaStatePosByStatePosForNeighbours(universe.engineArr[0], universe.levelArr[0], 2);
      printCells(universe, 3);
       */
      /*
      printCells(universe, 0);
      runCalcNextMetaState(universe);
      printCells(universe, 1);
      runCalcNextMetaState(universe);
      printCells(universe, 1);
      runCalcNextMetaState(universe);
      printCells(universe, 1);
      runCalcNextMetaState(universe);
      printCells(universe, 1);
      */

      // Assert
      {   final int msPos = 0;
         assertEquals(0, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[0], "inputMetaStatePosArr Pos0 " + msPos + " should be other.");
         assertEquals(0, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[1], "inputMetaStatePosArr Pos1 " + msPos + " should be other.");
         assertEquals(0, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[2], "inputMetaStatePosArr Pos2 " + msPos + " should be other.");
         assertEquals(1, level2dynamicEngine.metaStateArr[msPos].outputMetaStatePos, "outputMetaStatePos Pos " + msPos + " should be other.");
      }
      {   final int msPos = 1;
         assertEquals(1, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[0], "inputMetaStatePosArr Pos0 " + msPos + " should be other.");
         assertEquals(0, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[1], "inputMetaStatePosArr Pos1 " + msPos + " should be other.");
         assertEquals(0, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[2], "inputMetaStatePosArr Pos2 " + msPos + " should be other.");
         assertEquals(2, level2dynamicEngine.metaStateArr[msPos].outputMetaStatePos, "outputMetaStatePos Pos " + msPos + " should be other.");
      }
      {   final int msPos = 2;
         assertEquals(2, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[0], "inputMetaStatePosArr Pos0 " + msPos + " should be other.");
         assertEquals(0, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[1], "inputMetaStatePosArr Pos1 " + msPos + " should be other.");
         assertEquals(0, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[2], "inputMetaStatePosArr Pos2 " + msPos + " should be other.");
         assertEquals(0, level2dynamicEngine.metaStateArr[msPos].outputMetaStatePos, "outputMetaStatePos Pos " + msPos + " should be other.");
      }
      // - - - - - - - - - - - - -
      {   final int msPos = 3;
         assertEquals(0, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[0], "inputMetaStatePosArr Pos0 " + msPos + " should be other.");
         assertEquals(1, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[1], "inputMetaStatePosArr Pos1 " + msPos + " should be other.");
         assertEquals(0, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[2], "inputMetaStatePosArr Pos2 " + msPos + " should be other.");
         assertEquals(0, level2dynamicEngine.metaStateArr[msPos].outputMetaStatePos, "outputMetaStatePos Pos " + msPos + " should be other.");
      }
      {   final int msPos = 4;
         assertEquals(1, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[0], "inputMetaStatePosArr Pos0 " + msPos + " should be other.");
         assertEquals(1, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[1], "inputMetaStatePosArr Pos1 " + msPos + " should be other.");
         assertEquals(0, level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[2], "inputMetaStatePosArr Pos2 " + msPos + " should be other.");
         assertEquals(0, level2dynamicEngine.metaStateArr[msPos].outputMetaStatePos, "outputMetaStatePos Pos " + msPos + " should be other.");
      }
      assertEquals(1, readCellStatePos(universe, 3, 0, 0));
   }
}
