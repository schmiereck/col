package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.FieldEngine.NPMS_L0_S1_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L1_S00_S10_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L1_S01_S00_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L1_S10_S00_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L2_S000_S000_S001_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L2_S001_S000_S000_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L2_S100_S000_S000_Pos;
import static de.schmiereck.col.model.FieldEngine.l0EnginePos;
import static de.schmiereck.col.model.FieldEngine.l0StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l1EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l2EnginePos;
import static de.schmiereck.col.model.HyperCell.Max_Probability;
import static de.schmiereck.col.services.RunTestUtils.calcDirMetaStatePos;
import static de.schmiereck.col.services.UniverseService.runCalcNextPart;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.setMetaStatePos;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.NULL_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.STAYa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.LEFTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.NULL_u0_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_p1_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.STAYa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcNextPartMetaStatePosArr;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SNULL_u0_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_u0_p1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.engine.stay.CreateLevel0StayEngineService;
import de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_runNextPart_is_called_with_lev2_left2stay {

   private Engine level0Engine;
   private Engine level1Engine;
   private Engine level2Engine;
   private Engine level0StayEngine;
   private Engine level1StayEngine;

   private FieldEngine fieldEngine;
   private Universe universe;

   @BeforeEach
   void setup() {
      final int universeSize = 6;

      this.level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      this.level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();
      this.level2Engine = CreateLevel2SpinMoveEngineService.createLevel2SpinMoveEngine();
      this.level0StayEngine = CreateLevel0StayEngineService.createLevel0StayEngine();
      this.level1StayEngine = CreateLevel1StayEngineService.createLevel1StayEngine();

      final Engine[] engineArr = new Engine[5];
      engineArr[l0EnginePos] = this.level0Engine;
      engineArr[l1EnginePos] = this.level1Engine;
      engineArr[l2EnginePos] = this.level2Engine;
      engineArr[l0StayEnginePos] = this.level0StayEngine;
      engineArr[l1StayEnginePos] = this.level1StayEngine;

      this.fieldEngine = new FieldEngine(engineArr);
      this.universe = new Universe(fieldEngine, universeSize);
   }

   @Test
   void GIVEN_pos6_2LEFT001_to_pos4_1STAY01_run_THEN_state_reflected() {
      // Arrange
      //              3   4   5   0   1   2
      //              -   -
      //                  -   S              b
      //    x             -   -   -
      //    x                 -   -   -
      //    x                     -   -   L  a
      // =>
      //    x                         -   L      a
      //    x                             -   -
      //                          R          c
      final Part aPart = setMetaStatePos(universe, 0,  l2EnginePos, //metaPos(level2Engine, LEFTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0));
              //new int[] { metaPos(level2Engine, STAYa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0), metaPos(level2Engine, LEFTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0), metaPos(level2Engine, RIGHTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0) },
              calcNextPartMetaStatePosArr(fieldEngine, l2EnginePos, NPMS_L2_S001_S000_S000_Pos),
              new int[] { 0, Max_Probability, 0 });
      final Part bPart = setMetaStatePos(universe, 4,  l1StayEnginePos, //metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0));
              //new int[] { metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0), metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0), metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0) },
              calcNextPartMetaStatePosArr(fieldEngine, l1StayEnginePos, NPMS_L1S_S01_S00_Pos),
              new int[] { Max_Probability, 0, 0 });

      universe.use_levelUp = false;

      NextPartCreateService.createNextPartArrA(universe);

      // Act 0
      printCells(universe, aPart, 0, "initial aPart");
      printCells(universe, bPart, 0, "initial bPart");

      runCalcNextPart(universe);
      printCells(universe, 0, "runCalcNextPart");

      // Assert 0
      assertEquals(3, universe.partList.size());

      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1), calcDirMetaStatePos(universe, 0));

      assertEquals(4, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(l1StayEnginePos, universe.partList.get(1).enginePos);
      assertEquals(metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0), calcDirMetaStatePos(universe, 1));

      assertEquals(0, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(2).enginePos);
      assertEquals(metaPos(level0Engine, RIGHTa_p1), calcDirMetaStatePos(universe, 2));
   }

   @Test
   void GIVEN_state_pos0_2x_0RIGHT_ab_run_THEN_state_combined() {
      // Arrange
      final Part aPart = setMetaStatePos(universe, 1, l0EnginePos, //metaPos(level0Engine, RIGHTa_p1));
              calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos),
              new int[] { 0, 0, Max_Probability });
      final Part bPart = setMetaStatePos(universe, 0, l0EnginePos, //metaPos(level0Engine, RIGHTa_p1));
              calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos),
              new int[] { 0, 0, Max_Probability });
      bPart.parentPart = aPart;

      NextPartCreateService.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 0
      assertEquals(1, universe.partList.size());

      assertEquals(0, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level1Engine, RIGHTa_u0_p1, NULL_u0_u0), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_state_pos1_2x_0RIGHT_ab_run_THEN_state_combined() {
      // Arrange
      final Part aPart = setMetaStatePos(universe, 2, l0EnginePos, //metaPos(level0Engine, RIGHTa_p1));
              calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos),
              new int[] { 0, 0, Max_Probability });
      final Part bPart = setMetaStatePos(universe, 1, l0EnginePos, //metaPos(level0Engine, RIGHTa_p1));
              calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos),
              new int[] { 0, 0, Max_Probability });
      bPart.parentPart = aPart;

      NextPartCreateService.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 0
      assertEquals(1, universe.partList.size());

      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, RIGHTa_u0_p1), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_state_pos0_2x_0RIGHT_ba_run_THEN_state_combined() {
      // Arrange
      final Part aPart = setMetaStatePos(universe, 0, l0EnginePos, //metaPos(level0Engine, RIGHTa_p1));
              calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos),
              new int[] { 0, 0, Max_Probability });
      final Part bPart = setMetaStatePos(universe, 1, l0EnginePos, //metaPos(level0Engine, RIGHTa_p1));
              calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos),
              new int[] { 0, 0, Max_Probability });
      bPart.parentPart = aPart;

      NextPartCreateService.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 0
      assertEquals(1, universe.partList.size());

      assertEquals(0, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level1Engine, RIGHTa_p1_u0, NULL_u0_u0), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_state_pos1_2x_0RIGHT_ba_run_THEN_state_combined() {
      // Arrange
      final Part aPart = setMetaStatePos(universe, 1, l0EnginePos, //metaPos(level0Engine, RIGHTa_p1));
              calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos),
              new int[] { 0, 0, Max_Probability });
      final Part bPart = setMetaStatePos(universe, 2, l0EnginePos, //metaPos(level0Engine, RIGHTa_p1));
              calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos),
              new int[] { 0, 0, Max_Probability });
      bPart.parentPart = aPart;

      NextPartCreateService.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 0
      assertEquals(1, universe.partList.size());

      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, RIGHTa_p1_u0), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_state_pos2_1RIGHT_pos3_0RIGHT_ba_run_THEN_state_combined() {
      // Arrange
      //              0A  1B  2A  3B  4A
      //              -           R          b
      //    x             R   -              a
      //    x                 -   -
      // =>
      //    x             R   -   -
      //    x                 -   -   -      a
      //    x                     -   -   -
      final Part aPart = setMetaStatePos(universe, 2, l1EnginePos, //metaPos(level1Engine, NULL_u0_u0, RIGHTa_p1_u0));
              //new int[] { metaPos(level1Engine, NULL_u0_u0, STAYa_p1_u0), metaPos(level1Engine, NULL_u0_u0, LEFTa_p1_u0), metaPos(level1Engine, NULL_u0_u0, RIGHTa_p1_u0) },
              calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S00_S10_Pos),
              new int[] { 0, 0, Max_Probability });
      final Part bPart = setMetaStatePos(universe, 3, l0EnginePos, //metaPos(level0Engine, RIGHTa_p1));
              calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos),
              new int[] { 0, 0, Max_Probability });
      bPart.parentPart = aPart;

      NextPartCreateService.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 0
      assertEquals(1, universe.partList.size());

      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l2EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level2Engine, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_p1_u0_u0), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_state_pos2_1RIGHT_pos4_0RIGHT_ba_run_THEN_state_combined() {
      // Arrange
      //              0A  1B  2A  3B  4A
      //                              R      b
      //    x             -   -
      //    x                 R   -          a
      // =>
      //    x             -   -   -
      //    x                 R   -   -      a
      //    x                     -   -   -
      final Part aPart = setMetaStatePos(universe, 2, l1EnginePos, //metaPos(level1Engine, RIGHTa_p1_u0, NULL_u0_u0));
              calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S10_S00_Pos),
              new int[] { 0, 0, Max_Probability });
      final Part bPart = setMetaStatePos(universe, 4, l0EnginePos, //metaPos(level0Engine, RIGHTa_p1));
              calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos),
              new int[] { 0, 0, Max_Probability });
      bPart.parentPart = aPart;

      NextPartCreateService.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 0
      assertEquals(1, universe.partList.size());

      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l2EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level2Engine, NULL_u0_u0_u0, RIGHTa_p1_u0_u0, NULL_u0_u0_u0), calcDirMetaStatePos(universe, 0));
   }
}
