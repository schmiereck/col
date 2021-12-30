package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.FieldEngine.l0EnginePos;
import static de.schmiereck.col.model.FieldEngine.l0StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l1EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l2EnginePos;
import static de.schmiereck.col.model.HyperCell.Max_Probability;
import static de.schmiereck.col.services.RunTestUtils.calcDirMetaStatePos;
import static de.schmiereck.col.services.RunTestUtils.runTestNextMetaPart;
import static de.schmiereck.col.services.UniverseService.runCalcNextPart;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.setMetaStatePos;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.LEFTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.NULL_u0_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.STAYa_u0_u0_p1;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SNULL_u0_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_p1_u0;
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

public class Test_UniverseService_WHEN_runNextMetaPart_is_called_with_lev2_left2stay {

   private Engine level0Engine;
   private Engine level1Engine;
   private Engine level2Engine;
   private Engine level0StayEngine;
   private Engine level1StayEngine;

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

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      this.universe = new Universe(fieldEngine, universeSize);
   }

   @Test
   void GIVEN_state_2LEFT_to_1STAY01_run_THEN_state_reflected() {
      // Arrange

      //              0   1   2   3   4
      //          -   -
      //              -   S                  b
      //    x             -   -   -
      //    x                 -   -   L      a
      //    x                     -   -   -
      // =>
      //    x                     -   L      a
      //    x                         -   -
      //                      R              c
      final Part aPart = setMetaStatePos(universe, 3,  l2EnginePos, //metaPos(level2Engine, NULL_u0_u0_u0, LEFTa_u0_u0_p1, NULL_u0_u0_u0));
              new int[] { metaPos(level2Engine, NULL_u0_u0_u0, STAYa_u0_u0_p1, NULL_u0_u0_u0), metaPos(level2Engine, NULL_u0_u0_u0, LEFTa_u0_u0_p1, NULL_u0_u0_u0), metaPos(level2Engine, NULL_u0_u0_u0, RIGHTa_u0_u0_p1, NULL_u0_u0_u0) },
              new int[] { 0, Max_Probability, 0 });
      final Part bPart = setMetaStatePos(universe, 0,  l1StayEnginePos, //metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0));
              new int[] { metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0), metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0), metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0) },
              new int[] { Max_Probability, 0, 0 });

      universe.use_levelUp = false;

      NextPartCreateService.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runCalcNextPart(universe);
      printCells(universe, 0, "runCalcNextPart");

      // Assert 0
      assertEquals(3, universe.partList.size());

      assertEquals(4, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l1EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level1Engine, NULL_u0_u0, LEFTa_u0_p1), calcDirMetaStatePos(universe, 0));

      assertEquals(0, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(l1StayEnginePos, universe.partList.get(1).enginePos);
      assertEquals(metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0), calcDirMetaStatePos(universe, 1));

      assertEquals(2, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(2).enginePos);
      assertEquals(metaPos(level0Engine, RIGHTa_p1), calcDirMetaStatePos(universe, 2));

      // Act 1
      runTestNextMetaPart(universe, 1);

      // Assert 1
      assertEquals(4, universe.partList.size());

      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level0Engine, LEFTa_p1), calcDirMetaStatePos(universe, 0));

      assertEquals(2, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(l1StayEnginePos, universe.partList.get(1).enginePos);
      assertEquals(metaPos(level1StayEngine, SNULL_u0_u0, SSTAY_p1_u0), calcDirMetaStatePos(universe, 1));

      assertEquals(3, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(2).enginePos);
      assertEquals(metaPos(level1Engine, RIGHTa_p1), calcDirMetaStatePos(universe, 2));

      assertEquals(2, universe.partList.get(3).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(3).enginePos);
      assertEquals(metaPos(level1Engine, RIGHTa_p1), calcDirMetaStatePos(universe, 3));

      // Act 2
      runTestNextMetaPart(universe, 2);

      // Assert 2
      assertEquals(4, universe.partList.size());

      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level0Engine, RIGHTa_p1), calcDirMetaStatePos(universe, 0));

      assertEquals(0, universe.partList.get(1).hyperCell.cellPos);
      assertEquals(l1StayEnginePos, universe.partList.get(1).enginePos);
      assertEquals(metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0), calcDirMetaStatePos(universe, 1));

      assertEquals(4, universe.partList.get(2).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(2).enginePos);
      assertEquals(metaPos(level1Engine, RIGHTa_p1), calcDirMetaStatePos(universe, 2));

      assertEquals(3, universe.partList.get(3).hyperCell.cellPos);
      assertEquals(l0EnginePos, universe.partList.get(3).enginePos);
      assertEquals(metaPos(level1Engine, RIGHTa_p1), calcDirMetaStatePos(universe, 3));
   }
}
