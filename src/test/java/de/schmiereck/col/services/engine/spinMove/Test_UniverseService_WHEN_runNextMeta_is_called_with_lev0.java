package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.HyperCell.Max_Probability;
import static de.schmiereck.col.services.RunTestUtils.runTestNextMeta2;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.setMetaStatePos;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.NULL_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.STAYa_p1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Event;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.HyperCell;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_runNextMeta_is_called_with_lev0 {

   @Test
   void GIVEN_state_STAY_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      //final Engine level1Engine = CreateLevel1DynamicMoveEngineService.createLevel1DynamicMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;
      //engineArr[1] = level1Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Event event = new Event(null);
      //setStatePos(universe, 2, STAY_p1);
      //setStatePos(universe, 2, STAYa_p1, event);
      final Part part = setMetaStatePos(universe, 2, 0, metaPos(level0Engine, STAYa_p1, NULL_u0));

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt < 3; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level0Engine, STAYa_p1, NULL_u0), universe.partList.get(0).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_state_STAY_p1_run_THEN_state_is_calculated2() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      //final Engine level1Engine = CreateLevel1DynamicMoveEngineService.createLevel1DynamicMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;
      //engineArr[1] = level1Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Event event = new Event(null);
      //setStatePos(universe, 2, STAY_p1);
      //setStatePos(universe, 2, STAYa_p1, event);
      final Part part = setMetaStatePos(universe, 2, 0,
              new int[] { metaPos(level0Engine, STAYa_p1, NULL_u0), metaPos(level0Engine, LEFTa_p1, NULL_u0), metaPos(level0Engine, RIGHTa_p1, NULL_u0) },
              new int[] { Max_Probability, 0, 0 });

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt < 3; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level0Engine, STAYa_p1, NULL_u0), calcDirMetaStatePos(universe, 0));
   }

   public static int calcDirMetaStatePos(final Universe universe, final int partPos) {
      final HyperCell hyperCell = universe.partList.get(partPos).hyperCell;
      return hyperCell.dirMetaStatePosArr[hyperCell.dirProbability.lastProbabilityPos];
   }

   @Test
   void GIVEN_state_LEFT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      //final Engine level1Engine = CreateLevel1DynamicMoveEngineService.createLevel1DynamicMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;
      //engineArr[1] = level1Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Event event = new Event(null);
      //setStatePos(universe, 2, LEFTa_p1, event);
      //final Part part = setMetaStatePos(universe, 2, 0, metaPos(level0Engine, LEFTa_p1, NULL_u0));
      final Part part = setMetaStatePos(universe, 2, 0, //metaPos(level0Engine, LEFTa_p1, NULL_u0));
              new int[] { metaPos(level0Engine, STAYa_p1, NULL_u0), metaPos(level0Engine, LEFTa_p1, NULL_u0), metaPos(level0Engine, RIGHTa_p1, NULL_u0) },
              new int[] { 0, Max_Probability, 0 });

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt < 5; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).enginePos);
      //assertEquals(metaPos(level0Engine, LEFTa_p1, NULL_u0), universe.partList.get(0).hyperCell.metaStatePos);
      assertEquals(metaPos(level0Engine, LEFTa_p1, NULL_u0), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_state_RIGHT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      //final Engine level1Engine = CreateLevel1DynamicMoveEngineService.createLevel1DynamicMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;
      //engineArr[1] = level1Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 3, 0, //metaPos(level0Engine, RIGHTa_p1, NULL_u0));
              new int[] { metaPos(level0Engine, STAYa_p1, NULL_u0), metaPos(level0Engine, LEFTa_p1, NULL_u0), metaPos(level0Engine, RIGHTa_p1, NULL_u0) },
              new int[] { 0, 0, Max_Probability });

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt < 5; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level0Engine, RIGHTa_p1, NULL_u0), calcDirMetaStatePos(universe, 0));
   }

   @Test
   void GIVEN_state_RIGHT_p1_50per_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      //final Engine level1Engine = CreateLevel1DynamicMoveEngineService.createLevel1DynamicMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;
      //engineArr[1] = level1Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part part = setMetaStatePos(universe, 3, 0, //metaPos(level0Engine, RIGHTa_p1, NULL_u0));
              new int[] { metaPos(level0Engine, STAYa_p1, NULL_u0), metaPos(level0Engine, LEFTa_p1, NULL_u0), metaPos(level0Engine, RIGHTa_p1, NULL_u0) },
              new int[] { Max_Probability / 2, 0, Max_Probability / 2 });

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt < 11; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level0Engine, RIGHTa_p1, NULL_u0), calcDirMetaStatePos(universe, 0));
   }
}