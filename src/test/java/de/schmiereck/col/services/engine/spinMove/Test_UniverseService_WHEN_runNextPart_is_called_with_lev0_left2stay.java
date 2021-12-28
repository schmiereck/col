package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.FieldEngine.MaxEngineSize;
import static de.schmiereck.col.model.FieldEngine.l0EnginePos;
import static de.schmiereck.col.model.FieldEngine.l0StayEnginePos;
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
import static de.schmiereck.col.services.engine.stay.CreateLevel0StayEngineService.SNULL_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel0StayEngineService.SSTAY_p1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.engine.stay.CreateLevel0StayEngineService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test_UniverseService_WHEN_runNextPart_is_called_with_lev0_left2stay {

   private Engine level0Engine;
   private Engine level0StayEngine;

   private FieldEngine fieldEngine;
   private Universe universe;

   @BeforeEach
   void setup() {
      final int universeSize = 6;

      this.level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      this.level0StayEngine = CreateLevel0StayEngineService.createLevel0StayEngine();

      final Engine[] engineArr = new Engine[MaxEngineSize];
      engineArr[l0EnginePos] = this.level0Engine;
      engineArr[l0StayEnginePos] = this.level0StayEngine;

      this.fieldEngine = new FieldEngine(engineArr);
      this.universe = new Universe(fieldEngine, universeSize);
   }

   @Test
   void GIVEN_state_0LEFT1_to_0STAY1_run_THEN_state_reflected() {
      // Arrange
      final Part aPart = setMetaStatePos(universe, 4, l0EnginePos, //metaPos(level0Engine, RIGHTa_p1, NULL_u0));
              new int[] { metaPos(level0Engine, STAYa_p1, NULL_u0), metaPos(level0Engine, LEFTa_p1, NULL_u0), metaPos(level0Engine, RIGHTa_p1, NULL_u0) },
              new int[] { 0, Max_Probability, 0 });
      final Part bPart = setMetaStatePos(universe, 2,  l0StayEnginePos, //metaPos(level0StayEngine, SSTAY_p1, SNULL_u0));
              new int[] { metaPos(level0StayEngine, SSTAY_p1, SNULL_u0), metaPos(level0StayEngine, SSTAY_p1, SNULL_u0), metaPos(level0StayEngine, SSTAY_p1, SNULL_u0) },
              new int[] { Max_Probability, 0, 0 });

      universe.use_levelUp = false;

      CreateNextPartArr.createNextPartArrA(universe);

      // Act 0
      printCells(universe,0, "initial");
      runTestNextMetaPart(universe, 0);

      // Assert 0
      assertEquals(3, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level0Engine, RIGHTa_p1, NULL_u0), calcDirMetaStatePos(universe, 0));

      // Act 1
      runTestNextMetaPart(universe, 1);

      // Assert 1
      assertEquals(4, universe.partList.get(0).hyperCell.cellPos);
      assertEquals(0, universe.partList.get(0).enginePos);
      assertEquals(metaPos(level0Engine, RIGHTa_p1, NULL_u0), calcDirMetaStatePos(universe, 0));
   }
}
