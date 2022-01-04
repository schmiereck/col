package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.FieldEngine.MaxEngineSize;
import static de.schmiereck.col.model.FieldEngine.NPMS_L0_S1_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L1_S00_S01_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L1_S01_S00_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L1_S10_S00_Pos;
import static de.schmiereck.col.model.FieldEngine.l0EnginePos;
import static de.schmiereck.col.model.FieldEngine.l0StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l1EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l2EnginePos;
import static de.schmiereck.col.model.FieldEngine.l3EnginePos;
import static de.schmiereck.col.model.HyperCell.Max_Probability;
import static de.schmiereck.col.services.RunTestUtils.calcDirMetaStatePos;
import static de.schmiereck.col.services.RunTestUtils.runTestNextMeta2;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.setMetaStatePos;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcNextPartMetaStatePosArr;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.engine.stay.CreateLevel0StayEngineService;
import de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_runNextMeta_is_called_with_lev1 {

   private Engine level0Engine;
   private Engine level1Engine;
   private Engine level2Engine;
   private Engine level3Engine;
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
      this.level3Engine = CreateLevel3SpinMoveEngineService.createLevel3SpinMoveEngine();
      this.level0StayEngine = CreateLevel0StayEngineService.createLevel0StayEngine();
      this.level1StayEngine = CreateLevel1StayEngineService.createLevel1StayEngine();

      final Engine[] engineArr = new Engine[MaxEngineSize];
      engineArr[l0EnginePos] = this.level0Engine;
      engineArr[l1EnginePos] = this.level1Engine;
      engineArr[l2EnginePos] = this.level2Engine;
      engineArr[l3EnginePos] = this.level3Engine;
      engineArr[l0StayEnginePos] = this.level0StayEngine;
      engineArr[l1StayEnginePos] = this.level1StayEngine;

      this.fieldEngine = new FieldEngine(engineArr);
      this.universe = new Universe(fieldEngine, universeSize);
   }

   @Test
   void GIVEN_lev1spinMove_state_STAY_p1_u0_run_THEN_state_is_calculated() {
      // Arrange
      final Part part = setMetaStatePos(universe, 2,  l1EnginePos, //metaPos(level1Engine, STAYa_p1_u0, NULL_u0_u0));
              calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S10_S00_Pos),
              new int[] { Max_Probability, 0, 0 });

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt <= 2; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      //assertEquals(metaPos(level1Engine, NULL_u0_u0, STAYa_u0_p1), universe.partList.get(0).hyperCell.metaStatePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, STAYa_u0_p1), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_lev1spinMove_state_STAY_u0_p1_run_THEN_state_is_calculated() {
      // Arrange
      final Part part = setMetaStatePos(universe, 2,  l1EnginePos, //metaPos(level1Engine, STAYa_u0_p1, NULL_u0_u0));
              //new int[] { metaPos(level1Engine, STAYa_u0_p1, NULL_u0_u0), metaPos(level1Engine, LEFTa_u0_p1, NULL_u0_u0), metaPos(level1Engine, RIGHTa_u0_p1, NULL_u0_u0) },
              calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S01_S00_Pos),
              new int[] { Max_Probability, 0, 0 });

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt <= 2; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(4, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      //assertEquals(metaPos(level1Engine, NULL_u0_u0, STAYa_p1_u0), universe.partList.get(0).hyperCell.metaStatePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, STAYa_p1_u0), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_lev1spinMove_state_LEFT_u0_p1_NULL_run_THEN_state_is_calculated() {
      // Arrange
      final Part part = setMetaStatePos(universe, 0,  l1EnginePos, //metaPos(level1Engine, LEFTa_u0_p1, NULL_u0_u0));
              //new int[] { metaPos(level1Engine, STAYa_u0_p1, NULL_u0_u0), metaPos(level1Engine, LEFTa_u0_p1, NULL_u0_u0), metaPos(level1Engine, RIGHTa_u0_p1, NULL_u0_u0) },
              calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S01_S00_Pos),
              new int[] { 0, Max_Probability, 0 });

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt < 8; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(4, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      //assertEquals(metaPos(level1Engine, LEFTa_u0_p1, NULL_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);
      assertEquals(metaPos(level1Engine, LEFTa_u0_p1, NULL_u0_u0), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_lev1spinMove_state_LEFT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final Part part = setMetaStatePos(universe, 0,  l1EnginePos, //metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1));
              //new int[] { metaPos(level1Engine, NULL_u0_u0, STAYa_u0_p1), metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1), metaPos(level1Engine, NULL_u0_u0, RIGHTa_u0_p1) },
              calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S00_S01_Pos),
              new int[] { 0, Max_Probability, 0 });

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt < 8; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(4, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      //assertEquals(metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1), universe.partList.get(0).hyperCell.metaStatePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_lev1spinMove_state_RIGHT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final Part part = setMetaStatePos(universe, 0, l1EnginePos, //metaPos(level1Engine, NULL_u0_u0, RIGHTa_u0_p1));
              //new int[] { metaPos(level1Engine, NULL_u0_u0, STAYa_u0_p1), metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1), metaPos(level1Engine, NULL_u0_u0, RIGHTa_u0_p1) },
              calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S00_S01_Pos),
              new int[] { 0, 0, Max_Probability });

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt < 8; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      //assertEquals(metaPos(level1Engine, NULL_u0_u0, RIGHTa_u0_p1), universe.partList.get(0).hyperCell.metaStatePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, RIGHTa_u0_p1), calcDirMetaStatePos(universe, 0));
   }
}
