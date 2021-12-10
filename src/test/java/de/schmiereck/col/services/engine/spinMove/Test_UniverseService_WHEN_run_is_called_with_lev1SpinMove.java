package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.RunTestUtils.runTestNextMeta;
import static de.schmiereck.col.services.RunTestUtils.runTestNextStateMeta;
import static de.schmiereck.col.services.RunTestUtils.runTestNextUpStateMeta;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.readCell;
import static de.schmiereck.col.services.UniverseUtils.readCellState;
import static de.schmiereck.col.services.UniverseUtils.setStatePos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.STAYa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_u0_p1;
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

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 8; cnt++) {
         runTestNextMeta(universe, cnt);
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
      assertEquals(LEFTa_u0_p1, readCell(universe, 3, 0).statePos);
      assertEquals(NULL_u0_u0, readCell(universe, 4, 0).statePos);
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

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt < 8; cnt++) {
         runTestNextMeta(universe, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }
/*
WRONG 35| 0| 0
  -------  ----------  ----------  ----------  ----------  ----------  ---------- : runCalcNextMetaState
   4/0/0:             >   0| 0| 0     0| 0| 0 >   0| 0| 0     0| 0| 0 >  42| 0| 0    42| 0| 0
   4/0/1: > 294| 0| 0   294| 0| 0 >   0| 0| 0     0| 0| 0 >   6| 6| 0     6| 6| 1
  -------  ----------  ----------  ----------  ----------  ----------  ---------- : runCalcNextMetaState
   5/0/0:             > 245| 0| 0   245| 0| 0 >   0| 0| 0     0| 0| 0 >   5| 5| 1     5| 5| 0
   5/0/1: >  35| 0| 0    35| 0| 0 >   0| 0| 0     0| 0| 0 >   0| 0| 0     0| 0| 0
  -------  ----------  ----------  ----------  ----------  ----------  ---------- : runCalcNextMetaState
   6/0/0:             > 294| 0| 0   294| 0| 0 >   0| 0| 0     0| 0| 0 >   6| 6| 0     6| 6| 1
   6/0/1: >  42| 0| 0    42| 0| 0 >   0| 0| 0     0| 0| 0 >   0| 0| 0     0| 0| 0
  -------  ----------  ----------  ----------  ----------  ----------  ---------- : runCalcNextMetaState

 */
      // Assert
      assertEquals(nulState, readCellState(universe, 0, 0, 0));
      assertEquals(posState, readCellState(universe, 1, 0, 0));
      assertEquals(nulState, readCellState(universe, 1, 0, 1));
      assertEquals(nulState, readCellState(universe, 2, 0, 0));
      assertEquals(nulState, readCellState(universe, 3, 0, 0));
      assertEquals(nulState, readCellState(universe, 4, 0, 0));
      assertEquals(nulState, readCellState(universe, 5, 0, 0));

      assertEquals(NULL_u0_u0, readCell(universe, 0, 0).statePos);
      assertEquals(RIGHTa_p1_u0, readCell(universe, 1, 0).statePos);
      assertEquals(NULL_u0_u0, readCell(universe, 2, 0).statePos);
      assertEquals(NULL_u0_u0, readCell(universe, 3, 0).statePos);
   }
}
