package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.services.RunTestUtils.runTestNextMeta2;
import static de.schmiereck.col.services.RunTestUtils.runTestNextPart;
import static de.schmiereck.col.services.RunTestUtils.runTestNextPartMeta;
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
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.LEFTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.NULL_u0_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_p1_u0_u0;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.NextPart;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.UniverseService;

import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_runPartMeta_is_called_with_lev1SpinMove {

   @Test
   void GIVEN_lev1spinMove_state_2RIGHT_to_1STAY01_run_THEN_state_reflected() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();
      final Engine level2Engine = CreateLevel2SpinMoveEngineService.createLevel2SpinMoveEngine();

      final Engine[] engineArr = new Engine[3];
      engineArr[0] = level0Engine;
      engineArr[1] = level1Engine;
      engineArr[2] = level2Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part aPart = setMetaStatePos(universe, 0,  2, metaPos(level2Engine, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0));
      //universe.partList.add(aPart);
      final Part bPart = setMetaStatePos(universe, 2,  1, metaPos(level1Engine, STAYa_u0_p1, NULL_u0_u0));
      //universe.partList.add(bPart);

      UniverseService.calcInitialMetaStates(universe);
      CreateNextPartArr.createNextPartArrA(universe);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

      // Act 0
      printCells(universe, aPart, 0, "initial aPart");
      printCells(universe, bPart, 0, "initial bPart");

      runCalcNextPart(universe);
      printCells(universe, 0, "runCalcNextPart");

      // Assert 0
      assertEquals(3, universe.partList.size());

      assertEquals(0, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(1, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level1Engine, RIGHTa_p1_u0, NULL_u0_u0), universe.partList.get(0).hyperCell.metaStatePos);

      assertEquals(2, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(1, universe.partList.get(1).levelPos);
      assertEquals(metaPos(level1Engine, STAYa_u0_p1, NULL_u0_u0), universe.partList.get(1).hyperCell.metaStatePos);

      assertEquals(2, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(2).levelPos);
      assertEquals(metaPos(level0Engine, LEFTa_p1), universe.partList.get(2).hyperCell.metaStatePos);

      // Act 1
      runCalcNextMetaState2(universe);
      printCells(universe, 1, "runCalcNextMetaState2");
      runCalcNextPart(universe);
      printCells(universe, 1, "runCalcNextPart");

      // Assert 1
      assertEquals(4, universe.partList.size());

      assertEquals(1, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level0Engine, RIGHTa_p1), universe.partList.get(0).hyperCell.metaStatePos);

      assertEquals(4, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(1, universe.partList.get(1).levelPos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, STAYa_p1_u0), universe.partList.get(1).hyperCell.metaStatePos);

      assertEquals(1, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(2).levelPos);
      assertEquals(metaPos(level1Engine, LEFTa_p1), universe.partList.get(2).hyperCell.metaStatePos);

      assertEquals(2, universe.partList.get(3).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(3).levelPos);
      assertEquals(metaPos(level1Engine, LEFTa_p1), universe.partList.get(3).hyperCell.metaStatePos);

      // Act 2
      runCalcNextMetaState2(universe);
      printCells(universe, 2, "runCalcNextMetaState2");
      runCalcNextPart(universe);
      printCells(universe, 2, "runCalcNextPart");

      // Assert 2
      assertEquals(4, universe.partList.size());

      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level0Engine, LEFTa_p1), universe.partList.get(0).hyperCell.metaStatePos);

      assertEquals(2, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(1, universe.partList.get(1).levelPos);
      assertEquals(metaPos(level1Engine, STAYa_u0_p1, NULL_u0_u0), universe.partList.get(1).hyperCell.metaStatePos);

      assertEquals(0, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(2).levelPos);
      assertEquals(metaPos(level1Engine, LEFTa_p1), universe.partList.get(2).hyperCell.metaStatePos);

      assertEquals(1, universe.partList.get(3).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(3).levelPos);
      assertEquals(metaPos(level1Engine, LEFTa_p1), universe.partList.get(3).hyperCell.metaStatePos);
   }

   @Test
   void GIVEN_lev1spinMove_state_2RIGHT_to_1STAY10_run_THEN_state_reflected() {
      // Arrange
      final int universeSize = 6;

      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();
      final Engine level2Engine = CreateLevel2SpinMoveEngineService.createLevel2SpinMoveEngine();

      final Engine[] engineArr = new Engine[3];
      engineArr[0] = level0Engine;
      engineArr[1] = level1Engine;
      engineArr[2] = level2Engine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      final Part aPart = setMetaStatePos(universe, 0,  2, metaPos(level2Engine, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0));
      //universe.partList.add(aPart);
      final Part bPart = setMetaStatePos(universe, 2,  1, metaPos(level1Engine, STAYa_p1_u0, NULL_u0_u0));
      //universe.partList.add(bPart);

      UniverseService.calcInitialMetaStates(universe);
      CreateNextPartArr.createNextPartArrX(universe);

      UniverseService.CONFIG_use_levelUpOutputMetaStatePos = true;

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
      assertEquals(0, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level0Engine, RIGHTa_p1), universe.partList.get(0).hyperCell.metaStatePos);

      assertEquals(2, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(1, universe.partList.get(1).levelPos);
      assertEquals(metaPos(level1Engine, STAYa_p1_u0, NULL_u0_u0), universe.partList.get(1).hyperCell.metaStatePos);

      assertEquals(0, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(1, universe.partList.get(2).levelPos);
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
      assertEquals(0, universe.partList.get(0).levelPos);
      assertEquals(metaPos(level0Engine, RIGHTa_p1), universe.partList.get(0).hyperCell.metaStatePos);

      assertEquals(2, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(1, universe.partList.get(1).levelPos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, STAYa_u0_p1), universe.partList.get(1).hyperCell.metaStatePos);

      assertEquals(0, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(1, universe.partList.get(2).levelPos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1), universe.partList.get(2).hyperCell.metaStatePos);
   }
}
