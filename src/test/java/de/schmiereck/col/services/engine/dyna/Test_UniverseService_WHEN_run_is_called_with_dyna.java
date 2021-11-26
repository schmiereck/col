package de.schmiereck.col.services.engine.dyna;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.UniverseService.runNextUSDS;
import static de.schmiereck.col.services.UniverseService.runNextUMDS;
import static de.schmiereck.col.services.UniverseService.runCalcNextMetaState;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.readCell;
import static de.schmiereck.col.services.UniverseUtils.readCellState;
import static de.schmiereck.col.services.UniverseUtils.setStatePos;
import static org.junit.jupiter.api.Assertions.*;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.UniverseService;
import de.schmiereck.col.services.engine.dyna.CreateLevel1DynamicEngineService;
import de.schmiereck.col.services.engine.dyna.CreateLevel2DynamicEngineService;
import de.schmiereck.col.services.engine.stat.CreateLevel0StaticEngineService;
import de.schmiereck.col.services.engine.stat.CreateLevel1StaticEngineService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test_UniverseService_WHEN_run_is_called_with_dyna {

   @BeforeEach
   void setUp() {
   }

   @Test
   void GIVEN_level_012dyn_run2_pos_states_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 12;

      // Engine Level 0:
      final Engine level0Engine = CreateLevel0StaticEngineService.createLevel0staticEngine();

      // Engine Level 1 (dynamic):
      final Engine level1dynamicEngine = CreateLevel1DynamicEngineService.createLevel1dynamicEngine();

      // Engine Level 2 (dynamic):
      final Engine level2dynamicEngine = CreateLevel2DynamicEngineService.createLevel2dynamicEngine();

      final Engine[] engine3Arr = new Engine[3];
      engine3Arr[0] = level0Engine;
      engine3Arr[1] = level1dynamicEngine;
      engine3Arr[2] = level2dynamicEngine;

      final Universe universe = new Universe(engine3Arr, universeSize);

      setStatePos(universe, 6, 2, 1);  // lev2dyn 1: ( 1| 1| 0    1| 1| 0    1| 1| 1)

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0);
      runNextUMDS(universe);
      printCells(universe, 1);

      // Assert
      assertEquals(posState, readCellState(universe, 7, 2, 0));
   }

   @Test
   void GIVEN_level_012dyn_run2_2x_pos_states_in_different_directions_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 12;

      // Engine Level 0:
      final Engine level0Engine = CreateLevel0StaticEngineService.createLevel0staticEngine();

      // Engine Level 1 (dynamic):
      final Engine level1dynamicEngine = CreateLevel1DynamicEngineService.createLevel1dynamicEngine();

      // Engine Level 2 (dynamic):
      final Engine level2dynamicEngine = CreateLevel2DynamicEngineService.createLevel2dynamicEngine();

      final Engine[] engine3Arr = new Engine[3];
      engine3Arr[0] = level0Engine;
      engine3Arr[1] = level1dynamicEngine;
      engine3Arr[2] = level2dynamicEngine;

      final Universe universe = new Universe(engine3Arr, universeSize);

      setStatePos(universe, 2, 2, 0,  3);   // l2dyn 3: 1, 0, 0
      setStatePos(universe, 6, 2, 0,  1);   // l2dyn 1: 0, 0, 1

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0);
      runTest1(universe);
      printCells(universe, 1);
      runTest1(universe);
      printCells(universe, 1);
      runTest1(universe);
      printCells(universe, 1);
      runTest1(universe);
      printCells(universe, 1);

      // Assert
      assertEquals(posState, readCellState(universe, 2, 2, 0));
      assertEquals(posState, readCellState(universe, 6, 2, 2));
   }

   private void runTest1(final Universe universe) {
      //runLevelUp(universe);
      runCalcNextMetaState(universe);
      //runLevelDown(universe);
      //runCalcNextState(universe);
   }
}