package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.services.RunTestUtils.runTestNextMeta2;
import static de.schmiereck.col.services.RunTestUtils.runTestPartMeta;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.setMetaStatePos;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_u0_p1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.UniverseService;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_runPartMeta_is_called_with_lev1SpinMove {

   @Test
   void GIVEN_lev1spinMove_state_STAY_p1_run_THEN_state_is_calculated() {
      // Arrange
      final int universeSize = 6;

      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();

      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level1Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part aPart = setMetaStatePos(universe, 2,  0, metaPos(level1Engine, NULL_u0_u0, STAYa_u0_p1));
      final Part bPart = setMetaStatePos(universe, 4,  0, metaPos(level1Engine, STAYa_p1_u0, NULL_u0_u0));

      UniverseService.calcInitialMetaStates(universe);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act
      printCells(universe, aPart, 0, "initial");
      for (int cnt = 0; cnt < 8; cnt++) {
         runTestPartMeta(universe, bPart, cnt);
         //runNextUSDM(universe); printCellsMinimal(universe, cnt);
      }

      // Assert
      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, STAYa_u0_p1), universe.partList.get(0).hyperCell.metaStatePos);
   }
}
