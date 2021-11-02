package de.schmiereck.col;

import static de.schmiereck.col.model.State.neg0State;
import static de.schmiereck.col.model.State.nul0State;
import static de.schmiereck.col.model.State.pos0State;
import static de.schmiereck.col.services.UniverseService.printCells;
import static de.schmiereck.col.services.UniverseService.readCellState;
import static de.schmiereck.col.services.UniverseService.runLevelUp;
import static de.schmiereck.col.services.UniverseService.setStatePos;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.EngineService;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_runLevelUp_is_called {

   @Test
   void GIVEN_level_3_all_pos_states_THEN_state_is_populated_to_level_2() {
      // Arrange
      final int universeSize = 12;

      final Engine level1Engine = EngineService.createLevel1staticEngine();
      final Engine level2Engine = EngineService.createLevel2staticEngine();
      final Engine level3staticEngine = EngineService.createLevel3staticEngine();
      final Engine level3dynamicEngine = EngineService.createLevel3dynamicEngine();

      final Engine[] engine3Arr = new Engine[3];
      engine3Arr[0] = level1Engine;
      engine3Arr[1] = level2Engine;
      engine3Arr[2] = level3staticEngine;
      //engine3Arr[2] = level3dynamicEngine;

      final Universe universe = new Universe(engine3Arr, universeSize);

      //             0       1       2       3       4       5       6       7       8       9      10      11
      //   0/3: ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0) ( 9/ 1    9/ 0    9/ 0) ( 0/ 0    0/ 0    0/ 0)
      //   0/3:         ( 0/ 0    0/ 0    0/ 0) ( 1/ 0    1/ 0    1/ 1) ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0)
      //   0/3:                 ( 0/ 0    0/ 0    0/ 0) ( 3/ 0    3/ 1    3/ 0) ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0)
      setStatePos(universe, 6, 2, 0,  9);
      setStatePos(universe, 4, 2, 0,  1);
      setStatePos(universe, 5, 2, 0,  3);
      //setStatePos(universe, 5, 1, 0,  1);

      // Act
      printCells(universe, 0);
      runLevelUp(universe);
      printCells(universe, 1);

      // Assert
      //             0       1       2       3       4       5       6       7       8       9      10      11
      //   1/3: ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0)
      //   1/3:         ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0)
      //   1/3:                 ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0)
      //   1/2: ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 3/ 1    3/ 0) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0)
      //   1/2:         ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 1/ 0    1/ 1) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0)
      assertEquals(pos0State, readCellState(universe, 6, 1, 0));
      assertEquals(nul0State, readCellState(universe, 6, 1, 1));
      assertEquals(nul0State, readCellState(universe, 5, 1, 0));
      assertEquals(pos0State, readCellState(universe, 5, 1, 1));

      assertEquals(nul0State, readCellState(universe, 6, 2, 0));
      assertEquals(nul0State, readCellState(universe, 4, 2, 2));
      assertEquals(nul0State, readCellState(universe, 5, 2, 1));
   }

   @Test
   void GIVEN_level_3_all_pos_states_and_level_2_one_pos_state_THEN_state_is_not_populated_to_level_2() {
      // Arrange
      final int universeSize = 12;

      final Engine level1Engine = EngineService.createLevel1staticEngine();
      final Engine level2Engine = EngineService.createLevel2staticEngine();
      final Engine level3staticEngine = EngineService.createLevel3staticEngine();
      final Engine level3dynamicEngine = EngineService.createLevel3dynamicEngine();

      final Engine[] engine3Arr = new Engine[3];
      engine3Arr[0] = level1Engine;
      engine3Arr[1] = level2Engine;
      engine3Arr[2] = level3staticEngine;
      //engine3Arr[2] = level3dynamicEngine;

      final Universe universe = new Universe(engine3Arr, universeSize);

      //             0       1       2       3       4       5       6       7       8       9      10      11
      //   0/3: ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0) ( 9/ 1    9/ 0    9/ 0) ( 0/ 0    0/ 0    0/ 0)
      //   0/3:         ( 0/ 0    0/ 0    0/ 0) ( 1/ 0    1/ 0    1/ 1) ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0)
      //   0/3:                 ( 0/ 0    0/ 0    0/ 0) ( 3/ 0    3/ 1    3/ 0) ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0)
      //   0/2: ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0)
      //   0/2:         ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 1/ 0    1/ 1) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0)
      setStatePos(universe, 6, 2, 0,  9);
      setStatePos(universe, 4, 2, 0,  1);
      setStatePos(universe, 5, 2, 0,  3);
      setStatePos(universe, 5, 1, 0,  1);

      // Act
      printCells(universe, 0);
      runLevelUp(universe);
      printCells(universe, 1);

      // Assert
      //             0       1       2       3       4       5       6       7       8       9      10      11
      //   1/3: ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0) ( 9/ 1    9/ 0    9/ 0) ( 0/ 0    0/ 0    0/ 0)
      //   1/3:         ( 0/ 0    0/ 0    0/ 0) ( 1/ 0    1/ 0    1/ 1) ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0)
      //   1/3:                 ( 0/ 0    0/ 0    0/ 0) ( 3/ 0    3/ 1    3/ 0) ( 0/ 0    0/ 0    0/ 0) ( 0/ 0    0/ 0    0/ 0)
      //   1/2: ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0)
      //   1/2:         ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 1/ 0    1/ 1) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0) ( 0/ 0    0/ 0)
      assertEquals(nul0State, readCellState(universe, 6, 1, 0));
      assertEquals(pos0State, readCellState(universe, 5, 1, 1));

      assertEquals(pos0State, readCellState(universe, 6, 2, 0));
      assertEquals(pos0State, readCellState(universe, 4, 2, 2));
      assertEquals(pos0State, readCellState(universe, 5, 2, 1));
   }
}
