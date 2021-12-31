package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.FieldEngine.MaxEngineSize;
import static de.schmiereck.col.model.FieldEngine.NPMS_L0_S1_Pos;
import static de.schmiereck.col.model.FieldEngine.l0EnginePos;
import static de.schmiereck.col.model.FieldEngine.l0StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l1EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l2EnginePos;
import static de.schmiereck.col.model.HyperCell.Max_Probability;
import static de.schmiereck.col.services.RunTestUtils.calcDirMetaStatePos;
import static de.schmiereck.col.services.RunTestUtils.runTestNextMetaPart;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.setMetaStatePos;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.NULL_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.STAYa_p1;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcNextPartMetaStatePosArr;
import static de.schmiereck.col.services.engine.stay.CreateLevel0StayEngineService.SNULL_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel0StayEngineService.SSTAY_p1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.engine.stay.CreateLevel0StayEngineService;
import de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_runNextPart_is_called_with_lev0_right2stay {

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

      final Engine[] engineArr = new Engine[MaxEngineSize];
      engineArr[l0EnginePos] = this.level0Engine;
      engineArr[l1EnginePos] = this.level1Engine;
      engineArr[l2EnginePos] = this.level2Engine;
      engineArr[l0StayEnginePos] = this.level0StayEngine;
      engineArr[l1StayEnginePos] = this.level1StayEngine;

      this.fieldEngine = new FieldEngine(engineArr);
      this.universe = new Universe(fieldEngine, universeSize);
   }

   @Test
   void GIVEN_state_0RIGHT1_to_0STAY1_run_THEN_state_reflected() {
      // Arrange
      final Part aPart = setMetaStatePos(universe, 2, l0EnginePos, //metaPos(level0Engine, RIGHTa_p1, NULL_u0));
              calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos),
              new int[] { 0, 0, Max_Probability });
      final Part bPart = setMetaStatePos(universe, 4,  l0StayEnginePos, //metaPos(level0StayEngine, SSTAY_p1, SNULL_u0));
              new int[] { metaPos(level0StayEngine, SSTAY_p1, SNULL_u0), metaPos(level0StayEngine, SSTAY_p1, SNULL_u0), metaPos(level0StayEngine, SSTAY_p1, SNULL_u0) },
              new int[] { Max_Probability, 0, 0 });

      universe.use_levelUp = false;

      NextPartCreateService.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runTestNextMetaPart(universe, 0);

      // Assert 0
      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).enginePos);
      //assertEquals(metaPos(level0Engine, LEFTa_p1, NULL_u0), universe.partList.get(0).hyperCell.metaStatePos);
      assertEquals(metaPos(level0Engine, LEFTa_p1, NULL_u0), calcDirMetaStatePos(universe, 0));

      // Act 1
      runTestNextMetaPart(universe, 1);

      // Assert 1
      assertEquals(2, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).enginePos);
      //assertEquals(metaPos(level0Engine, LEFTa_p1, NULL_u0), universe.partList.get(0).hyperCell.metaStatePos);
      assertEquals(metaPos(level0Engine, LEFTa_p1, NULL_u0), calcDirMetaStatePos(universe, 0));
   }
}
