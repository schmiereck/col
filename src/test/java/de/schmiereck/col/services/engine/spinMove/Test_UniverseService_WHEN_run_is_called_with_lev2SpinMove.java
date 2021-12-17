package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.services.RunTestUtils.runTestNextMeta2;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.setMetaStatePos;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.LEFTa_p1_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.LEFTa_u0_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.LEFTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.NULL_u0_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_p1_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_u0_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.STAYa_p1_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.STAYa_u0_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.STAYa_u0_u0_p1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.UniverseService;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_run_is_called_with_lev2SpinMove {

   @Test
   void GIVEN_lev1spinMove_state_STAY_p1_u0_u0_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level2Engine = CreateLevel2SpinMoveEngineService.createLevel2SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level2Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 0,  0, metaPos(level2Engine, STAYa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0));

      UniverseService.calcInitialMetaStates(universe);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt <= 4; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(0, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level2Engine, NULL_u0_u0_u0, NULL_u0_u0_u0, STAYa_u0_u0_p1), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_STAY_u0_p1_u0_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level2Engine = CreateLevel2SpinMoveEngineService.createLevel2SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level2Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 0,  0, metaPos(level2Engine, STAYa_u0_p1_u0, NULL_u0_u0_u0, NULL_u0_u0_u0));

      UniverseService.calcInitialMetaStates(universe);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt <= 4; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level2Engine, NULL_u0_u0_u0, NULL_u0_u0_u0, STAYa_p1_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_STAY_u0_u0_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level2Engine = CreateLevel2SpinMoveEngineService.createLevel2SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level2Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 0,  0, metaPos(level2Engine, STAYa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0));

      UniverseService.calcInitialMetaStates(universe);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt <= 4; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level2Engine, NULL_u0_u0_u0, NULL_u0_u0_u0, STAYa_u0_p1_u0), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_LEFT_p1_u0_u0_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level2Engine = CreateLevel2SpinMoveEngineService.createLevel2SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level2Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 3,  0, metaPos(level2Engine, LEFTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0));

      UniverseService.calcInitialMetaStates(universe);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt <= 6; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level2Engine, NULL_u0_u0_u0, LEFTa_p1_u0_u0, NULL_u0_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_LEFT_u0_p1_u0_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level2Engine = CreateLevel2SpinMoveEngineService.createLevel2SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level2Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 3,  0, metaPos(level2Engine, LEFTa_u0_p1_u0, NULL_u0_u0_u0, NULL_u0_u0_u0));

      UniverseService.calcInitialMetaStates(universe);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt <= 6; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level2Engine, NULL_u0_u0_u0, LEFTa_u0_p1_u0, NULL_u0_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_LEFT_u0_u0_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level2Engine = CreateLevel2SpinMoveEngineService.createLevel2SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level2Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 3,  0, metaPos(level2Engine, LEFTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0));

      UniverseService.calcInitialMetaStates(universe);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt <= 6; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level2Engine, NULL_u0_u0_u0, LEFTa_u0_u0_p1, NULL_u0_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_RIGHT_p1_u0_u0_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level2Engine = CreateLevel2SpinMoveEngineService.createLevel2SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level2Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 0,  0, metaPos(level2Engine, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0));

      UniverseService.calcInitialMetaStates(universe);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt <= 6; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level2Engine, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_p1_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_RIGHT_u0_p1_u0_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level2Engine = CreateLevel2SpinMoveEngineService.createLevel2SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level2Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 0,  0, metaPos(level2Engine, RIGHTa_u0_p1_u0, NULL_u0_u0_u0, NULL_u0_u0_u0));

      UniverseService.calcInitialMetaStates(universe);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt <= 6; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level2Engine, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_u0_p1_u0), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_RIGHT_u0_u0_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level2Engine = CreateLevel2SpinMoveEngineService.createLevel2SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level2Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 0,  0, metaPos(level2Engine, RIGHTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0));

      UniverseService.calcInitialMetaStates(universe);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt <= 6; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level2Engine, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_u0_u0_p1), universe.partList.get(0).hyperCell.metaStatePos);
   }
}
