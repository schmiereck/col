package de.schmiereck.col;

import static de.schmiereck.col.services.UniverseService.runCalcNextMetaState2;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.setMetaStatePos;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.UniverseService;
import de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService;
import de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService;

public class Main2 {

   public static int universeSize = 12*2;

   public static void main(String[] args) {
      //----------------------------------------------------------------------------------------------------------------
      // Engine Level 0:
      final Engine level0Engine = CreateLevel0SpinMoveEngineService.createLevel0SpinMoveEngine();

      // Engine Level 1 (static):
      //final Engine level1staticEngine = CreateEngineService.createLevel1staticEngine();

      // Engine Level 1 (dynamic):
      //final Engine level1dynamicEngine = CreateEngineService.createLevel1dynamicEngine();

      // Engine Level 1 (move):
      final Engine level1moveEngine = CreateLevel1SpinMoveEngineService.createLevel1SpinMoveEngine();

      // Engine Level 2 (static):
      //final Engine level2staticEngine = CreateEngineService.createLevel2staticEngine();

      // Engine Level 2 (dynamic):
      //final Engine level2dynamicEngine = CreateEngineService.createLevel2dynamicEngine();

      // Engine Level 3 (static):
      //final Engine level3staticEngine = CreateEngineService.createLevel3staticEngine();

      //----------------------------------------------------------------------------------------------------------------
      final Engine[] engine2Arr = new Engine[2];
      engine2Arr[0] = level0Engine;
      engine2Arr[1] = level1moveEngine;
/*
      final Engine[] engine3Arr = new Engine[3];
      engine3Arr[0] = level0Engine;
      engine3Arr[1] = level1dynamicEngine;
      engine3Arr[2] = level2dynamicEngine;
*/
      final Engine[] engineArr = engine2Arr;    // !!!! TEST !!!!
      final FieldEngine fieldEngine = new FieldEngine(engineArr);
      final Universe universe = new Universe(fieldEngine, universeSize);

/*
      for (int metaCellPos = 0; metaCellPos < 3; metaCellPos++) {
         for (int cellPos = 0; cellPos < universeSize/1; cellPos += 6) {
            setStatePos(universe, cellPos + 0, 2, metaCellPos, 10);   // l2dyn 10: 1, 0, 1
            setStatePos(universe, cellPos + 3, 2, metaCellPos, 2);   // l2dyn  2: 0, 1, 0
         }
      }

      setStatePos(universe, 6, 2, 0,  17);   // l2dyn 17: 1, 1, 1
      setStatePos(universe, 7, 2, 0,  17);   // l2dyn 17: 1, 1, 1
*/
      /*
      //setStatePos(universe, 2, 2, 0,  3);   // l2dyn 3: 1, 0, 0
      setStatePos(universe, 12, 2, 0,  1);   // l2dyn 1: 0, 0, 1
      */
         for (int cellPos = 0; cellPos < universeSize/1; cellPos += 4) {
            setMetaStatePos(universe, cellPos + 0, 1, 3);   // l1mov 3:0,1
            setMetaStatePos(universe, cellPos + 2, 1, 5);   // l1mov 5:1,0
         }
      final Part part = setMetaStatePos(universe, 2, 1, 5);   // l1mov 5:1,0

      //----------------------------------------------------------------------------------------------------------------
      for (int cnt = 0; cnt < 6*2*8; cnt++) {
         //runTest1(universe, cnt);
         //runTest2(universe, cnt);

         //printCellsMinimal(universe, part, cnt);

         //runTest3(universe, cnt, false);
         //runTest4(universe, cnt, false);
         //runTest5(universe, cnt, false);
         runTestNextM(universe, part, cnt, false);
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
}
