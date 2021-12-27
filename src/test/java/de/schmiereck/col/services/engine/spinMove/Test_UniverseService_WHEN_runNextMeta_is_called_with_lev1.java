package de.schmiereck.col.services.engine.spinMove;

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
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_runNextMeta_is_called_with_lev1 {

   @Test
   void GIVEN_lev1spinMove_state_STAY_p1_u0_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level1Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 2,  0, //metaPos(level1Engine, STAYa_p1_u0, NULL_u0_u0));
              new int[] { metaPos(level1Engine, STAYa_p1_u0, NULL_u0_u0), metaPos(level1Engine, LEFTa_p1_u0, NULL_u0_u0), metaPos(level1Engine, RIGHTa_p1_u0, NULL_u0_u0) },
              new int[] { Max_Probability, 0, 0 });

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt <= 2; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).enginePos);
      //assertEquals(metaPos(level1Engine, NULL_u0_u0, STAYa_u0_p1), universe.partList.get(0).hyperCell.metaStatePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, STAYa_u0_p1), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_lev1spinMove_state_STAY_u0_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level1Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 2,  0, //metaPos(level1Engine, STAYa_u0_p1, NULL_u0_u0));
              new int[] { metaPos(level1Engine, STAYa_u0_p1, NULL_u0_u0), metaPos(level1Engine, LEFTa_u0_p1, NULL_u0_u0), metaPos(level1Engine, RIGHTa_u0_p1, NULL_u0_u0) },
              new int[] { Max_Probability, 0, 0 });

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt <= 2; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(4, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).enginePos);
      //assertEquals(metaPos(level1Engine, NULL_u0_u0, STAYa_p1_u0), universe.partList.get(0).hyperCell.metaStatePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, STAYa_p1_u0), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_lev1spinMove_state_LEFT_u0_p1_NULL_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level1Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 0,  0, //metaPos(level1Engine, LEFTa_u0_p1, NULL_u0_u0));
              new int[] { metaPos(level1Engine, STAYa_u0_p1, NULL_u0_u0), metaPos(level1Engine, LEFTa_u0_p1, NULL_u0_u0), metaPos(level1Engine, RIGHTa_u0_p1, NULL_u0_u0) },
              new int[] { 0, Max_Probability, 0 });

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt < 8; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(4, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).enginePos);
      //assertEquals(metaPos(level1Engine, LEFTa_u0_p1, NULL_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);
      assertEquals(metaPos(level1Engine, LEFTa_u0_p1, NULL_u0_u0), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_lev1spinMove_state_LEFT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level1Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 0,  0, //metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1));
              new int[] { metaPos(level1Engine, NULL_u0_u0, STAYa_u0_p1), metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1), metaPos(level1Engine, NULL_u0_u0, RIGHTa_u0_p1) },
              new int[] { 0, Max_Probability, 0 });

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt < 8; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(4, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).enginePos);
      //assertEquals(metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1), universe.partList.get(0).hyperCell.metaStatePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_lev1spinMove_state_RIGHT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level1Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 0, 0, //metaPos(level1Engine, NULL_u0_u0, RIGHTa_u0_p1));
              new int[] { metaPos(level1Engine, NULL_u0_u0, STAYa_u0_p1), metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1), metaPos(level1Engine, NULL_u0_u0, RIGHTa_u0_p1) },
              new int[] { 0, 0, Max_Probability });

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt < 8; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).enginePos);
      //assertEquals(metaPos(level1Engine, NULL_u0_u0, RIGHTa_u0_p1), universe.partList.get(0).hyperCell.metaStatePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, RIGHTa_u0_p1), calcDirMetaStatePos(universe, 0));
   }
}
