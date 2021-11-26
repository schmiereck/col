package de.schmiereck.col.services.engine.stat;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.UniverseService.runNextUSDS;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.readCellState;
import static de.schmiereck.col.services.UniverseUtils.setStatePos;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Universe;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_run_is_called_with_stat {

   @Test
   void GIVEN_lev1static_run_level_0_single_pos_state_THEN_state_is_populated_to_level_1_and_back_to_0() {
      // Arrange
      final int universeSize = 12;

      final Engine level0Engine = CreateLevel0StaticEngineService.createLevel0staticEngine();
      final Engine level1Engine = CreateLevel1StaticEngineService.createLevel1staticEngine();

      final Engine[] engine2Arr = new Engine[2];
      engine2Arr[0] = level0Engine;
      engine2Arr[1] = level1Engine;

      final Universe universe = new Universe(engine2Arr, universeSize);

      // 0/0/0: ... ( 0/ 0) ( 1/ 1) ( 0/ 0) ...
      setStatePos(universe, 6, 0,  1);

      // Act
      printCells(universe, 0);
      runNextUSDS(universe);
      printCells(universe, 1);

      // Assert
      // 0/0/0: ... ( 0/ 0) ( 1/ 1) ( 0/ 0) ...
      assertEquals(nulState, readCellState(universe, 5, 0, 0));
      assertEquals(posState, readCellState(universe, 6, 0, 0));
      assertEquals(nulState, readCellState(universe, 7, 0, 0));
   }

   @Test
   void GIVEN_lev1static_run_level_0_pos_and_neg_states_THEN_state_is_populated_to_level_1_and_state_is_annuled_in_one_cell() {
      // Arrange
      final int universeSize = 12;

      final Engine level0Engine = CreateLevel0StaticEngineService.createLevel0staticEngine();
      final Engine level1Engine = CreateLevel1StaticEngineService.createLevel1staticEngine();

      final Engine[] engine2Arr = new Engine[2];
      engine2Arr[0] = level0Engine;
      engine2Arr[1] = level1Engine;

      final Universe universe = new Universe(engine2Arr, universeSize);

      // 0/1: ( 1/ 1) ( 2/-1) ( 0/ 0) ...
      setStatePos(universe, 0, 0, 1);  // 1
      setStatePos(universe, 1, 0, 2);  //

      // Act
      printCells(universe, 0);
      runNextUSDS(universe);
      printCells(universe, 1);

      // Assert
      // 1/2: ...   0/ 0) ( 0/ 0    0/ 0) ( 3/ 1    3/ 0) ( 0/ 0  ...
      // 1/2: ... ( 0/ 0    0/ 0) ( 1/ 0    1/ 1) ( 0/ 0    0/ 0) ...
      assertEquals(nulState, readCellState(universe, -1, 1, 0));
      assertEquals(posState, readCellState(universe, -1, 1, 1));
      assertEquals(negState, readCellState(universe, 1, 1, 0));
      assertEquals(nulState, readCellState(universe, 1, 1, 1));
   }
}
