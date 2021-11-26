package de.schmiereck.col.services.engine.dynaMove;

import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel0DynamicMoveEngineService.LEFTb_p1;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel0DynamicMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel0DynamicMoveEngineService.NULL_u0;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel0DynamicMoveEngineService.RIGHTb_p1;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel0DynamicMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel0DynamicMoveEngineService.STAY_n1;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel0DynamicMoveEngineService.STAY_p1;
import static de.schmiereck.col.services.RunTestUtils.runTestNextStateMeta;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.readCell;
import static de.schmiereck.col.services.UniverseUtils.readCellState;
import static de.schmiereck.col.services.UniverseUtils.setStatePos;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.UniverseService;
import de.schmiereck.col.services.engine.dynaMove.CreateLevel0DynamicMoveEngineService;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_runTestNextStateMeta_is_called_with_lev0DynaMove {

   @Test
   void GIVEN_lev0move_state_STAY_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0DynamicMoveEngineService.createLevel0DynamicMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      setStatePos(universe, 2, 0, STAY_p1);

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 3; cnt++) {
         runTestNextStateMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, 1, 0, 0));
      assertEquals(posState, readCellState(universe, 2, 0, 0));
      assertEquals(nulState, readCellState(universe, 3, 0, 0));
      assertEquals(NULL_u0, readCell(universe, 1, 0).statePos);
      assertEquals(STAY_p1, readCell(universe, 2, 0).statePos);
      assertEquals(NULL_u0, readCell(universe, 3, 0).statePos);
   }

   @Test
   void GIVEN_lev0move_state_STAY_p1_next_STAY_n1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0DynamicMoveEngineService.createLevel0DynamicMoveEngine();

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
         runTestNextStateMeta(universe, cnt);
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

      final Engine level0Engine = CreateLevel0DynamicMoveEngineService.createLevel0DynamicMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      //setStatePos(universe, 2, 0, STAY_p1);
      setStatePos(universe, 0, 0, RIGHTa_p1);

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 3; cnt++) {
         runTestNextStateMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, 2, 0, 0));
      assertEquals(posState, readCellState(universe, 3, 0, 0));
      assertEquals(nulState, readCellState(universe, 4, 0, 0));
      assertEquals(NULL_u0, readCell(universe, 2, 0).statePos);
      assertEquals(RIGHTb_p1, readCell(universe, 3, 0).statePos);
      assertEquals(NULL_u0, readCell(universe, 4, 0).statePos);
   }

   @Test
   void GIVEN_lev0move_state_LEFT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0DynamicMoveEngineService.createLevel0DynamicMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      setStatePos(universe, 3, 0, LEFTa_p1);

      UniverseService.calcInitialMetaStates(universe);

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 3; cnt++) {
         runTestNextStateMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, -1, 0, 0));
      assertEquals(posState, readCellState(universe, 0, 0, 0));
      assertEquals(nulState, readCellState(universe, 1, 0, 0));
      assertEquals(NULL_u0, readCell(universe, -1, 0).statePos);
      assertEquals(LEFTb_p1, readCell(universe, 0, 0).statePos);
      assertEquals(NULL_u0, readCell(universe, 1, 0).statePos);
   }
}
