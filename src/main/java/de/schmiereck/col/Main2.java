package de.schmiereck.col;

import static de.schmiereck.col.model.FieldEngine.MaxEngineSize;
import static de.schmiereck.col.model.FieldEngine.NPMS_L1S_S01_S00_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L2_S100_S000_S000_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L3_S0000_S1000_S0000_S0000_Pos;
import static de.schmiereck.col.model.FieldEngine.l0EnginePos;
import static de.schmiereck.col.model.FieldEngine.l0StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l1EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l2EnginePos;
import static de.schmiereck.col.model.FieldEngine.l3EnginePos;
import static de.schmiereck.col.model.HyperCell.Max_Probability;
import static de.schmiereck.col.services.UniverseService.runCalcNextMetaState2;
import static de.schmiereck.col.services.UniverseService.runCalcNextPart;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.setMetaStatePos;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.NULL_u0_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_p1_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcNextPartMetaStatePosArr;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SNULL_u0_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_u0_p1;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService;
import de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService;
import de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService;
import de.schmiereck.col.services.engine.spinMove.CreateLevel3SpinMoveEngineService;
import de.schmiereck.col.services.engine.spinMove.NextPartCreateService;
import de.schmiereck.col.services.engine.stay.CreateLevel0StayEngineService;
import de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService;

public class Main2 {

   public static int universeSize = 1*2*3;

   public static void main(String[] args) {
      //----------------------------------------------------------------------------------------------------------------
      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();
      final Engine level1Engine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();
      final Engine level2Engine = CreateLevel2SpinMoveEngineService.createLevel2SpinMoveEngine();
      final Engine level3Engine = CreateLevel3SpinMoveEngineService.createLevel3SpinMoveEngine();
      final Engine level0StayEngine = CreateLevel0StayEngineService.createLevel0StayEngine();
      final Engine level1StayEngine = CreateLevel1StayEngineService.createLevel1StayEngine();

      //----------------------------------------------------------------------------------------------------------------
      final Engine[] engineArr = new Engine[MaxEngineSize];
      engineArr[l0EnginePos] = level0Engine;
      engineArr[l1EnginePos] = level1Engine;
      engineArr[l2EnginePos] = level2Engine;
      engineArr[l3EnginePos] = level3Engine;
      engineArr[l0StayEnginePos] = level0StayEngine;
      engineArr[l1StayEnginePos] = level1StayEngine;

      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

      //final Part aPart = setMetaStatePos(universe, 0,  l2EnginePos, metaPos(level2Engine, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0));
      final Part aPart = setMetaStatePos(universe, 0, l2EnginePos,
                                         calcNextPartMetaStatePosArr(fieldEngine, l2EnginePos, NPMS_L2_S100_S000_S000_Pos),
                                         new int[] { 0, 0, Max_Probability });
      //final Part bPart = setMetaStatePos(universe, 4,  l1StayEnginePos, metaPos(level1StayEngine, SSTAY_u0_p1, SNULL_u0_u0));
      final Part bPart = setMetaStatePos(universe, 4,  l1StayEnginePos,
                                         calcNextPartMetaStatePosArr(fieldEngine, l1StayEnginePos, NPMS_L1S_S01_S00_Pos),
                                         new int[] { Max_Probability, 0, 0 });

      universe.use_levelUp = true;

      NextPartCreateService.createNextPartArrA(universe);

      //----------------------------------------------------------------------------------------------------------------
      printCells(universe, 0, "initial");
      for (int cnt = 0; cnt <= 13; cnt++) {
         //printCellsMinimal(universe, part, cnt);

         runTestNextPM(universe, cnt, true);
      }
   }

   /**
    * Run Next Meta+State
    */
   private static void runTestNextM(final Universe universe, final Part part, final int cnt, final boolean doPrint) {
      //if (doPrint) printCells(universe, cnt);
      //runLevelUp(universe);

      if (doPrint) printCells(universe, part, cnt);
      runCalcNextMetaState2(universe);

      //if (doPrint) printCells(universe, cnt);
      //runLevelDown(universe);

      //if (doPrint) printCells(universe, cnt);
      //runCalcNextState(universe);
   }

   /**
    * Run Next Part+Meta
    */
   private static void runTestNextPM(final Universe universe, final int cnt, final boolean doPrint) {
      runCalcNextPart(universe);
      if (doPrint) printCells(universe, cnt, "runCalcNextPart");

      runCalcNextMetaState2(universe);
      if (doPrint) printCells(universe, cnt, "runCalcNextMetaState2");
   }
}
