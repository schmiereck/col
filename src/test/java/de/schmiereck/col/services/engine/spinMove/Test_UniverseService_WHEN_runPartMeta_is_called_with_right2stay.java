package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.FieldEngine.l0EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l2EnginePos;
import static de.schmiereck.col.services.UniverseService.runCalcNextMetaState2;
import static de.schmiereck.col.services.UniverseService.runCalcNextPart;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.setMetaStatePos;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.CreateEngineService.writeMetaState;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.LEFTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.NULL_u0_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_p1_u0_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SNULL_u0_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_p1_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_u0_p1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_runPartMeta_is_called_with_right2stay {

   private Engine level0Engine;
   private Engine level1Engine;
   private Engine level2Engine;
   private Engine level1StayEngine;

   private FieldEngine fieldEngine;
   private Universe universe;

   @BeforeEach
   void setup() {
      final int universeSize = 6;

      this.level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      this.level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();
      this.level2Engine = CreateLevel2SpinMoveEngineService.createLevel2SpinMoveEngine();
      this.level1StayEngine = CreateLevel1StayEngineService.createLevel1StayEngine();

      final Engine[] engineArr = new Engine[4];
      engineArr[l0EnginePos] = this.level0Engine;
      engineArr[l1EnginePos] = this.level1Engine;
      engineArr[l2EnginePos] = this.level2Engine;
      engineArr[l1StayEnginePos] = this.level1StayEngine;

      this.fieldEngine = new FieldEngine(engineArr);
      this.universe = new Universe(fieldEngine, universeSize);
   }

   @Test
   void GIVEN_pos0_2RIGHT100nn_to_1STAY01_run_THEN_state_reflected() {
      // Arrange

      //              0   1   2   3   4
      //                  -   -
      //                      -   S          b
      //    x -   -   -
      //    x     -   -   -
      //    x         R   -   -              a
      // =>
      //    x     -   -
      //    x         R   -                  a
      //                      L              c
      final Part aPart = setMetaStatePos(universe, 0,  l2EnginePos, metaPos(level2Engine, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0));
      final Part bPart = setMetaStatePos(universe, 2,  l1StayEnginePos, metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0));

      universe.use_levelUp = false;

      CreateNextPartArr.createNextPartArrA(universe);

      // Act 0
      printCells(universe, aPart, 0, "initial aPart");
      printCells(universe, bPart, 0, "initial bPart");

      runCalcNextPart(universe);
      printCells(universe, 0, "runCalcNextPart");

      // Assert 0
      assertEquals(3, universe.partList.size());

      assertEquals(0, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level1Engine, RIGHTa_p1_u0, NULL_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);

      assertEquals(2, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(l1StayEnginePos, universe.partList.get(1).enginePos);
      assertEquals(metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0), universe.partList.get(1).hyperCell.metaStatePos);

      assertEquals(2, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(2).enginePos);
      assertEquals(metaPos(level0Engine, LEFTa_p1), universe.partList.get(2).hyperCell.metaStatePos);

      // Act 1
      runCalcNextMetaState2(universe);
      printCells(universe, 1, "runCalcNextMetaState2");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 1
      assertEquals(4, universe.partList.size());

      assertEquals(1, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level0Engine, RIGHTa_p1), universe.partList.get(0).hyperCell.metaStatePos);

      assertEquals(4, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(l1StayEnginePos, universe.partList.get(1).enginePos);
      assertEquals(metaPos(level1StayEngine, SNULL_u0_u0, SSTAY_p1_u0), universe.partList.get(1).hyperCell.metaStatePos);

      assertEquals(1, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(2).enginePos);
      assertEquals(metaPos(level1Engine, LEFTa_p1), universe.partList.get(2).hyperCell.metaStatePos);

      assertEquals(2, universe.partList.get(3).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(3).enginePos);
      assertEquals(metaPos(level1Engine, LEFTa_p1), universe.partList.get(3).hyperCell.metaStatePos);

      // Act 2
      runCalcNextMetaState2(universe);
      printCells(universe, 2, "runCalcNextMetaState2");
      runCalcNextPart(universe);
      printCells(universe, 2, "runCalcNextPart");

      // Assert 2
      assertEquals(4, universe.partList.size());

      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level0Engine, LEFTa_p1), universe.partList.get(0).hyperCell.metaStatePos);

      assertEquals(2, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(l1StayEnginePos, universe.partList.get(1).enginePos);
      assertEquals(metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0), universe.partList.get(1).hyperCell.metaStatePos);

      assertEquals(0, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(2).enginePos);
      assertEquals(metaPos(level1Engine, LEFTa_p1), universe.partList.get(2).hyperCell.metaStatePos);

      assertEquals(1, universe.partList.get(3).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(3).enginePos);
      assertEquals(metaPos(level1Engine, LEFTa_p1), universe.partList.get(3).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_pos3_2RIGHTn100n_to_pos4_1STAY01_run_THEN_state_reflected() {
      // Arrange

      //              3   4   5   6   4
      //              -   -
      //                  -   S          b
      //    x -   -   -
      //    x     R   -   -
      //    x         -   -   -              a
      // =>
      //    x -   -                          a
      //    x     R   -
      //                  L                  c
      final Part aPart = setMetaStatePos(universe, 3,  l2EnginePos, metaPos(level2Engine, NULL_u0_u0_u0, RIGHTa_p1_u0_u0, NULL_u0_u0_u0));
      final Part bPart = setMetaStatePos(universe, 4,  l1StayEnginePos, metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0));

      universe.use_levelUp = false;

      CreateNextPartArr.createNextPartArrA(universe);

      // Act 0
      printCells(universe, aPart, 0, "initial aPart");
      printCells(universe, bPart, 0, "initial bPart");

      runCalcNextPart(universe);
      printCells(universe, 0, "runCalcNextPart");

      // Assert 0
      assertEquals(3, universe.partList.size());

      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level1Engine, RIGHTa_p1_u0, NULL_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);

      assertEquals(4, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(l1StayEnginePos, universe.partList.get(1).enginePos);
      assertEquals(metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0), universe.partList.get(1).hyperCell.metaStatePos);

      assertEquals(4, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(2).enginePos);
      assertEquals(metaPos(level0Engine, LEFTa_p1), universe.partList.get(2).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_pos3_2RIGHTn100n_to_pos0_1STAYn10_run_THEN_state_reflected() {
      // Arrange
      final int universeSize = 12;
      this.universe = new Universe(fieldEngine, universeSize);

      //              3   4   5   0   1
      //                      S   -
      //                          -   -  b
      //    x -   -   -
      //    x     R   -   -
      //    x         -   -   -              a
      // =>
      //    x -   -                          a
      //    x     R   -
      //                  L                  c
      // TODO diff a - b == b - a
      final Part aPart = setMetaStatePos(universe, 9,  l2EnginePos, metaPos(level2Engine, NULL_u0_u0_u0, RIGHTa_p1_u0_u0, NULL_u0_u0_u0));
      final Part bPart = setMetaStatePos(universe, 0,  l1StayEnginePos, metaPos(level1StayEngine, SNULL_u0_u0, SSTAY_p1_u0));

      universe.use_levelUp = false;

      CreateNextPartArr.createNextPartArrA(universe);

      // Act 0
      printCells(universe, aPart, 0, "initial aPart");
      printCells(universe, bPart, 0, "initial bPart");

      runCalcNextPart(universe);
      printCells(universe, 0, "runCalcNextPart");

      // Assert 0
      assertEquals(3, universe.partList.size());

      assertEquals(8, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level1Engine, RIGHTa_p1_u0, NULL_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);

      assertEquals(0, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(l1StayEnginePos, universe.partList.get(1).enginePos);
      assertEquals(metaPos(level1StayEngine, SNULL_u0_u0, SSTAY_p1_u0), universe.partList.get(1).hyperCell.metaStatePos);

      assertEquals(10, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(2).enginePos);
      assertEquals(metaPos(level0Engine, LEFTa_p1), universe.partList.get(2).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_2RIGHT_to_1STAY10_run_THEN_state_reflected() {
      // Arrange

      final Part aPart = setMetaStatePos(universe, 0,  l2EnginePos, metaPos(level2Engine, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0));
      //universe.partList.add(aPart);
      final Part bPart = setMetaStatePos(universe, 2,  l1StayEnginePos, metaPos(level1StayEngine, SSTAY_p1_u0, SNULL_u0_u0));
      //universe.partList.add(bPart);

      CreateNextPartArr.createNextPartArrX(universe, l1StayEnginePos);

      // Act 0
      printCells(universe, aPart, 0, "initial aPart");
      printCells(universe, bPart, 0, "initial bPart");
      for (int cnt = 0; cnt <= 0; cnt++) {
         //runTestNextPartMeta(universe, aPart, cnt);

         //runTestNextPart(universe, aPart, cnt);
         runCalcNextPart(universe);
         printCells(universe, aPart, cnt, "runCalcNextPart");

         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      printCells(universe, 0, "result");

      // Assert 0
      assertEquals(3, universe.partList.size());

      assertEquals(0, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level0Engine, RIGHTa_p1), universe.partList.get(0).hyperCell.metaStatePos);

      assertEquals(2, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(l1StayEnginePos, universe.partList.get(1).enginePos);
      assertEquals(metaPos(level1StayEngine, SSTAY_p1_u0, SNULL_u0_u0), universe.partList.get(1).hyperCell.metaStatePos);

      assertEquals(0, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(2).enginePos);
      assertEquals(metaPos(level1Engine, LEFTa_u0_p1, NULL_u0_u0), universe.partList.get(2).hyperCell.metaStatePos);

      final Part cPart = universe.partList.get(2);

      // Act 1
      runCalcNextMetaState2(universe);
      printCells(universe, 1, "runCalcNextMetaState2");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 1
      assertEquals(3, universe.partList.size());

      assertEquals(1, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level0Engine, RIGHTa_p1), universe.partList.get(0).hyperCell.metaStatePos);

      assertEquals(2, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(l1StayEnginePos, universe.partList.get(1).enginePos);
      assertEquals(metaPos(level1StayEngine, SNULL_u0_u0, SSTAY_u0_p1), universe.partList.get(1).hyperCell.metaStatePos);

      assertEquals(0, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(2).enginePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1), universe.partList.get(2).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_pos0_2x_0LEFT_ab_run_THEN_state_combined() {
      // Arrange
      final Part aPart = setMetaStatePos(universe, 0, l0EnginePos, metaPos(level0Engine, LEFTa_p1));
      final Part bPart = setMetaStatePos(universe, 1, l0EnginePos, metaPos(level0Engine, LEFTa_p1));
      bPart.parentPart = aPart;

      CreateNextPartArr.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 0
      assertEquals(1, universe.partList.size());

      assertEquals(0, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level1Engine, LEFTa_p1_u0, NULL_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_pos1_2x_0LEFT_ab_run_THEN_state_combined() {
      // Arrange
      final Part aPart = setMetaStatePos(universe, 1, l0EnginePos, metaPos(level0Engine, LEFTa_p1));
      final Part bPart = setMetaStatePos(universe, 2, l0EnginePos, metaPos(level0Engine, LEFTa_p1));
      bPart.parentPart = aPart;

      CreateNextPartArr.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 0
      assertEquals(1, universe.partList.size());

      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, LEFTa_p1_u0), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_pos2_2x_0LEFT_ab_run_THEN_state_combined() {
      // Arrange
      final Part aPart = setMetaStatePos(universe, 2, l0EnginePos, metaPos(level0Engine, LEFTa_p1));
      final Part bPart = setMetaStatePos(universe, 3, l0EnginePos, metaPos(level0Engine, LEFTa_p1));
      bPart.parentPart = aPart;

      CreateNextPartArr.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runCalcNextPart(universe);
      printCells(universe, 0, "runCalcNextPart");

      // Assert 0
      assertEquals(1, universe.partList.size());

      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level1Engine, LEFTa_p1_u0, NULL_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_pos0_2x_0LEFT_ba_run_THEN_state_combined() {
      // Arrange
      final Part aPart = setMetaStatePos(universe, 0, l0EnginePos, metaPos(level0Engine, LEFTa_p1));
      final Part bPart = setMetaStatePos(universe, -1, l0EnginePos, metaPos(level0Engine, LEFTa_p1));
      bPart.parentPart = aPart;

      CreateNextPartArr.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 0
      assertEquals(1, universe.partList.size());

      assertEquals(0, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_pos1_2x_1LEFT_ba_run_THEN_state_combined() {
      // Arrange
      final Part aPart = setMetaStatePos(universe, 1, l0EnginePos, metaPos(level0Engine, LEFTa_p1));
      //universe.partList.add(aPart);
      final Part bPart = setMetaStatePos(universe, 0, l0EnginePos, metaPos(level0Engine, LEFTa_p1));
      //universe.partList.add(bPart);
      bPart.parentPart = aPart;

      CreateNextPartArr.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 0
      assertEquals(1, universe.partList.size());

      assertEquals(0, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level1Engine, LEFTa_u0_p1, NULL_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_state_pos2_2LEFT_pos0_1LEFT_ba_run_THEN_state_combined() {
      // Arrange
      final Part aPart = setMetaStatePos(universe, 2, l1EnginePos, metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1));
      final Part bPart = setMetaStatePos(universe, 0, l0EnginePos, metaPos(level0Engine, LEFTa_p1));
      bPart.parentPart = aPart;

      CreateNextPartArr.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 0
      assertEquals(1, universe.partList.size());

      assertEquals(0, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l2EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level2Engine, LEFTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_state_pos3_2LEFT_pos1_1LEFT_ba_run_THEN_state_combined() {
      // Arrange
      final Part aPart = setMetaStatePos(universe, 2, l1EnginePos, metaPos(level1Engine, LEFTa_u0_p1, NULL_u0_u0));
      final Part bPart = setMetaStatePos(universe, 1, l0EnginePos, metaPos(level0Engine, LEFTa_p1));
      bPart.parentPart = aPart;

      CreateNextPartArr.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 0
      assertEquals(1, universe.partList.size());

      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l2EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level2Engine, NULL_u0_u0_u0, NULL_u0_u0_u0, LEFTa_u0_u0_p1), universe.partList.get(0).hyperCell.metaStatePos);
   }
}