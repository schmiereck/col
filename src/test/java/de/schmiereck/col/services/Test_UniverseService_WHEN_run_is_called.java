package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.UniverseService.printCells;
import static de.schmiereck.col.services.UniverseService.readCell;
import static de.schmiereck.col.services.UniverseService.readCellState;
import static de.schmiereck.col.services.UniverseService.run;
import static de.schmiereck.col.services.UniverseService.run2;
import static de.schmiereck.col.services.UniverseService.setStatePos;
import static org.junit.jupiter.api.Assertions.*;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.EngineService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test_UniverseService_WHEN_run_is_called {

   @BeforeEach
   void setUp() {
   }

   @Test
   void GIVEN_level_0_single_pos_state_THEN_state_is_populated_to_level_1() {
      // Arrange
      final int universeSize = 12;

      final Engine level0Engine = CreateEngineService.createLevel0staticEngine();
      final Engine level1Engine = CreateEngineService.createLevel1staticEngine();

      final Engine[] engine2Arr = new Engine[2];
      engine2Arr[0] = level0Engine;
      engine2Arr[1] = level1Engine;

      final Universe universe = new Universe(engine2Arr, universeSize);

      // ... ( 0/ 0) ( 1/ 1) ( 0/ 0) ...
      setStatePos(universe, 6, 0,  1);

      // Act
      printCells(universe, 0);
      run(universe);
      printCells(universe, 1);

      // Assert
      // 1/2: ( 0/ 0    0/ 0) ( 0/ 0   ... ( 0/ 0    0/ 0)
      // 1/2:         ( 6/-1    6/ 0)  ...   0/ 0) ( 1/ 0    1/ 1)
      assertEquals(nulState, readCellState(universe, 6, 0, 0));

      assertEquals(posState, readCellState(universe, 6, 1, 0));
      assertEquals(nulState, readCellState(universe, 7, 1, 0));
   }

   @Test
   void GIVEN_level_0_pos_and_neg_states_THEN_state_is_populated_to_level_1_and_state_is_annuled_in_one_cell() {
      // Arrange
      final int universeSize = 12;

      final Engine level0Engine = CreateEngineService.createLevel0staticEngine();
      final Engine level1Engine = CreateEngineService.createLevel1staticEngine();

      final Engine[] engine2Arr = new Engine[2];
      engine2Arr[0] = level0Engine;
      engine2Arr[1] = level1Engine;

      final Universe universe = new Universe(engine2Arr, universeSize);

      // 0/1: ( 1/ 1) ( 2/-1) ( 0/ 0) ...
      setStatePos(universe, 0, 0, 1);  // 1
      setStatePos(universe, 1, 0, 2);  //

      // Act
      printCells(universe, 0);
      run(universe);
      printCells(universe, 1);

      // Assert
      // 1/2: ...   0/ 0) ( 0/ 0    0/ 0) ( 3/ 1    3/ 0) ( 0/ 0  ...
      // 1/2: ... ( 0/ 0    0/ 0) ( 1/ 0    1/ 1) ( 0/ 0    0/ 0) ...
      assertEquals(nulState, readCellState(universe, -1, 1, 0));
      assertEquals(posState, readCellState(universe, -1, 1, 1));
      assertEquals(negState, readCellState(universe, 1, 1, 0));
      assertEquals(nulState, readCellState(universe, 1, 1, 1));
   }

   @Test
   void GIVEN_level_012dyn_pos_states_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 12;

      // Engine Level 0:
      final Engine level0Engine = CreateEngineService.createLevel0staticEngine();

      // Engine Level 1 (dynamic):
      final Engine level1dynamicEngine = CreateEngineService.createLevel1dynamicEngine();

      // Engine Level 2 (dynamic):
      final Engine level2dynamicEngine = CreateEngineService.createLevel2dynamicEngine();

      final Engine[] engine3Arr = new Engine[3];
      engine3Arr[0] = level0Engine;
      engine3Arr[1] = level1dynamicEngine;
      engine3Arr[2] = level2dynamicEngine;

      final Universe universe = new Universe(engine3Arr, universeSize);

      setStatePos(universe, 6, 2, 1);  // lev2dyn 1: ( 1| 1| 0    1| 1| 0    1| 1| 1)

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0);
      run2(universe);
      printCells(universe, 1);

      // Assert
      assertEquals(posState, readCellState(universe, 7, 2, 0));
   }
}