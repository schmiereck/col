package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.services.RunTestUtils.runTestNextMeta2;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.setMetaStatePos;
import static de.schmiereck.col.services.UniverseUtils.setStatePos;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.NULL_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.STAYa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_u0_p1;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Event;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.UniverseService;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_run_is_called_with_lev0SpinMove {

   @Test
   void GIVEN_lev0move_state_STAY_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      //final Engine level1Engine = CreateLevel1DynamicMoveEngineService.createLevel1DynamicMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;
      //engineArr[1] = level1Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      final Event event = new Event(null);
      //setStatePos(universe, 2, STAY_p1);
      //setStatePos(universe, 2, STAYa_p1, event);
      final Part part = setMetaStatePos(universe, 2, 0, metaPos(level0Engine, STAYa_p1, NULL_u0));

      UniverseService.calcInitialMetaStates(universe);
      //CreateLevel0DynamicMoveEngineService.initLevelUpOutputMetaStates(level0Engine, level1Engine);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt < 3; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      //assertEquals(2, universe.partList.get(0).levelArr[0].hyperCell.cellPos);
      //assertEquals(metaPos(level1Engine, NULL_u0_u0, RIGHTa_u0_p1), universe.partList.get(0).levelArr[0].hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev0move_state_LEFT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      //final Engine level1Engine = CreateLevel1DynamicMoveEngineService.createLevel1DynamicMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;
      //engineArr[1] = level1Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      final Event event = new Event(null);
      //setStatePos(universe, 2, LEFTa_p1, event);
      final Part part = setMetaStatePos(universe, 2, 0, metaPos(level0Engine, LEFTa_p1, NULL_u0));

      UniverseService.calcInitialMetaStates(universe);
      //CreateLevel0DynamicMoveEngineService.initLevelUpOutputMetaStates(level0Engine, level1Engine);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt < 2; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      //assertEquals(2, universe.partList.get(0).levelArr[0].hyperCell.cellPos);
      //assertEquals(metaPos(level1Engine, NULL_u0_u0, RIGHTa_u0_p1), universe.partList.get(0).levelArr[0].hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev0move_state_RIGHT_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      //final Engine level1Engine = CreateLevel1DynamicMoveEngineService.createLevel1DynamicMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;
      //engineArr[1] = level1Engine;

      final Universe universe = new Universe(engineArr, universeSize);

      final Part part = setMetaStatePos(universe, 3, 0, metaPos(level0Engine, RIGHTa_p1, NULL_u0));

      UniverseService.calcInitialMetaStates(universe);
      //CreateLevel0DynamicMoveEngineService.initLevelUpOutputMetaStates(level0Engine, level1Engine);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, part, 0, "initial");
      for (int cnt = 0; cnt < 2; cnt++) {
         runTestNextMeta2(universe, part, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      //assertEquals(2, universe.partList.get(0).levelArr[0].hyperCell.cellPos);
      //assertEquals(metaPos(level1Engine, NULL_u0_u0, RIGHTa_u0_p1), universe.partList.get(0).levelArr[0].hyperCell.metaStatePos);
   }
}
