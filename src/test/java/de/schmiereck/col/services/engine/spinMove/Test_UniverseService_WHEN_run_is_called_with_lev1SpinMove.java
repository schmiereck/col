package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.RunTestUtils.runTestNextStateMeta;
import static de.schmiereck.col.services.RunTestUtils.runTestNextUpStateMeta;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.printCellsMinimal;
import static de.schmiereck.col.services.UniverseUtils.readCell;
import static de.schmiereck.col.services.UniverseUtils.readCellState;
import static de.schmiereck.col.services.UniverseUtils.setStatePos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.NULL_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.STAYa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.STAYb_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTb_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTb_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTb_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTb_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYb_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYb_u0_p1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.UniverseService;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_run_is_called_with_lev1SpinMove {

   @Test
   void GIVEN_lev1spinMove_state_LEFT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level1Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      setStatePos(universe, 5, 0, LEFTa_u0_p1);

      UniverseService.calcInitialMetaStates(universe);

      UniverseService.use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 8; cnt++) {
         runTestNextStateMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, 0, 0, 0));
      assertEquals(nulState, readCellState(universe, 1, 0, 0));
      assertEquals(nulState, readCellState(universe, 2, 0, 0));
      assertEquals(nulState, readCellState(universe, 3, 0, 0));
      assertEquals(posState, readCellState(universe, 3, 0, 1));
      assertEquals(nulState, readCellState(universe, 4, 0, 0));
      assertEquals(nulState, readCellState(universe, 5, 0, 0));

      assertEquals(NULL_u0_u0, readCell(universe, 2, 0).statePos);
      assertEquals(LEFTb_u0_p1, readCell(universe, 3, 0).statePos);
      assertEquals(NULL_u0_u0, readCell(universe, 4, 0).statePos);
      assertEquals(NULL_u0_u0, readCell(universe, 5, 0).statePos);
   }

   @Test
   void GIVEN_lev1spinMove_2_state_LEFT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level1Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      setStatePos(universe, 0, 0, LEFTa_p1_u0);
      setStatePos(universe, 5, 0, LEFTa_u0_p1);

      UniverseService.calcInitialMetaStates(universe);

      UniverseService.use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 8; cnt++) {
         runTestNextStateMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, 0, 0, 0));
      assertEquals(nulState, readCellState(universe, 1, 0, 0));
      assertEquals(nulState, readCellState(universe, 2, 0, 0));
      assertEquals(nulState, readCellState(universe, 3, 0, 0));
      assertEquals(posState, readCellState(universe, 3, 0, 1));
      assertEquals(posState, readCellState(universe, 4, 0, 0));
      assertEquals(nulState, readCellState(universe, 5, 0, 0));

      assertEquals(NULL_u0_u0, readCell(universe, 2, 0).statePos);
      assertEquals(LEFTb_u0_p1, readCell(universe, 3, 0).statePos);
      assertEquals(LEFTb_p1_u0, readCell(universe, 4, 0).statePos);
      assertEquals(NULL_u0_u0, readCell(universe, 5, 0).statePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_RIGHT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level1Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      setStatePos(universe, 5, 0, RIGHTa_u0_p1);

      UniverseService.calcInitialMetaStates(universe);

      UniverseService.use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 8; cnt++) {
         runTestNextStateMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, 0, 0, 0));
      assertEquals(nulState, readCellState(universe, 1, 0, 0));
      assertEquals(posState, readCellState(universe, 1, 0, 1));
      assertEquals(nulState, readCellState(universe, 2, 0, 0));
      assertEquals(nulState, readCellState(universe, 3, 0, 0));
      assertEquals(nulState, readCellState(universe, 4, 0, 0));
      assertEquals(nulState, readCellState(universe, 5, 0, 0));

      assertEquals(NULL_u0_u0, readCell(universe, 0, 0).statePos);
      assertEquals(RIGHTb_u0_p1, readCell(universe, 1, 0).statePos);
      assertEquals(NULL_u0_u0, readCell(universe, 2, 0).statePos);
      assertEquals(NULL_u0_u0, readCell(universe, 3, 0).statePos);
   }

   @Test
   void GIVEN_lev0move_state_STAY_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();

      final Engine[] engineArr = new Engine[2];
      engineArr[0] = level0Engine;
      engineArr[1] = level1Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      //setStatePos(universe, 2, 0, STAY_p1);
      setStatePos(universe, 2, 0, STAYa_p1);

      UniverseService.calcInitialMetaStates(universe);
      CreateLevel0SpinMoveEngineService.initLevelUpOutputMetaStates(level0Engine, level1Engine);

      UniverseService.use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 3; cnt++) {
         runTestNextUpStateMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, 1, 0, 0));
      assertEquals(nulState, readCellState(universe, 2, 0, 0));
      assertEquals(nulState, readCellState(universe, 3, 0, 0));

      assertEquals(nulState, readCellState(universe, 1, 1, 0));
      assertEquals(posState, readCellState(universe, 1, 1, 1));
      assertEquals(posState, readCellState(universe, 2, 1, 0));
      assertEquals(nulState, readCellState(universe, 3, 1, 0));

      assertEquals(STAYa_u0_p1, readCell(universe, 1, 1).statePos);
      assertEquals(STAYb_p1_u0, readCell(universe, 2, 1).statePos);
      assertEquals(NULL_u0_u0, readCell(universe, 3, 1).statePos);
   }

   @Test
   void GIVEN_lev0move_state_LEFT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();

      final Engine[] engineArr = new Engine[2];
      engineArr[0] = level0Engine;
      engineArr[1] = level1Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      //setStatePos(universe, 2, 0, STAY_p1);
      setStatePos(universe, 2, 0, LEFTa_p1);

      UniverseService.calcInitialMetaStates(universe);
      CreateLevel0SpinMoveEngineService.initLevelUpOutputMetaStates(level0Engine, level1Engine);

      UniverseService.use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 6; cnt++) {
         runTestNextUpStateMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, 1, 0, 0));
      assertEquals(nulState, readCellState(universe, 2, 0, 0));
      assertEquals(nulState, readCellState(universe, 3, 0, 0));

      assertEquals(nulState, readCellState(universe, 0, 1, 0));
      assertEquals(nulState, readCellState(universe, 1, 1, 0));
      assertEquals(posState, readCellState(universe, 1, 1, 1));
      assertEquals(posState, readCellState(universe, 2, 1, 0));
      assertEquals(nulState, readCellState(universe, 3, 1, 0));
      assertEquals(nulState, readCellState(universe, 3, 1, 1));
      assertEquals(nulState, readCellState(universe, 4, 1, 0));
      assertEquals(nulState, readCellState(universe, 5, 1, 0));

      assertEquals(NULL_u0_u0, readCell(universe, 0, 1).statePos);
      assertEquals(LEFTb_u0_p1, readCell(universe, 1, 1).statePos);
      assertEquals(LEFTb_p1_u0, readCell(universe, 2, 1).statePos);
      assertEquals(NULL_u0_u0, readCell(universe, 3, 1).statePos);
      assertEquals(NULL_u0_u0, readCell(universe, 5, 1).statePos);
   }

   @Test
   void GIVEN_lev0move_state_RIGHT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();

      final Engine[] engineArr = new Engine[2];
      engineArr[0] = level0Engine;
      engineArr[1] = level1Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      //setStatePos(universe, 2, 0, STAY_p1);
      setStatePos(universe, 2, 0, RIGHTa_p1);

      UniverseService.calcInitialMetaStates(universe);
      CreateLevel0SpinMoveEngineService.initLevelUpOutputMetaStates(level0Engine, level1Engine);

      UniverseService.use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 8; cnt++) {
         runTestNextUpStateMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(nulState, readCellState(universe, 1, 0, 0));
      assertEquals(nulState, readCellState(universe, 2, 0, 0));
      assertEquals(nulState, readCellState(universe, 3, 0, 0));

      assertEquals(nulState, readCellState(universe, 2, 1, 0));
      assertEquals(nulState, readCellState(universe, 3, 1, 0));
      assertEquals(posState, readCellState(universe, 3, 1, 1));
      assertEquals(posState, readCellState(universe, 4, 1, 0));
      assertEquals(nulState, readCellState(universe, 5, 1, 0));
      assertEquals(nulState, readCellState(universe, 6, 1, 0));

      assertEquals(NULL_u0_u0, readCell(universe, 2, 1).statePos);
      assertEquals(RIGHTb_u0_p1, readCell(universe, 3, 1).statePos);
      assertEquals(RIGHTb_p1_u0, readCell(universe, 4, 1).statePos);
      assertEquals(NULL_u0_u0, readCell(universe, 5, 1).statePos);
   }

   @Test
   void GIVEN_lev0move_state_LEFT_STAY_RIGHT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 12;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();

      final Engine[] engineArr = new Engine[2];
      engineArr[0] = level0Engine;
      engineArr[1] = level1Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      setStatePos(universe, 2, 0, LEFTa_p1);
      setStatePos(universe, 3, 0, STAYa_p1);
      setStatePos(universe, 4, 0, RIGHTa_p1);

      UniverseService.calcInitialMetaStates(universe);
      CreateLevel0SpinMoveEngineService.initLevelUpOutputMetaStates(level0Engine, level1Engine);

      UniverseService.use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 12*2*2*2; cnt++) {
         runTestNextUpStateMeta(universe, cnt);
         //UniverseService.runNextUSM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(posState, readCellState(universe, 0, 1, 0));
      assertEquals(posState, readCellState(universe, 4, 1, 0));
      assertEquals(posState, readCellState(universe, 5, 1, 0));
      assertEquals(posState, readCellState(universe, 6, 1, 1));
      assertEquals(posState, readCellState(universe, 7, 1, 0));
      assertEquals(posState, readCellState(universe, 11, 1, 1));

      assertEquals(STAYa_p1_u0, readCell(universe, 0, 1).statePos);
      assertEquals(LEFTb_p1_u0, readCell(universe, 4, 1).statePos);
      assertEquals(RIGHTb_p1_u0, readCell(universe, 5, 1).statePos);
      assertEquals(LEFTa_u0_p1, readCell(universe, 6, 1).statePos);
      assertEquals(RIGHTa_p1_u0, readCell(universe, 7, 1).statePos);
      assertEquals(STAYb_u0_p1, readCell(universe, 11, 1).statePos);
   }
}
