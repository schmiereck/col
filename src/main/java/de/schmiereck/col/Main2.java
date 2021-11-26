package de.schmiereck.col;

import static de.schmiereck.col.services.UniverseService.runNextUSDS;
import static de.schmiereck.col.services.UniverseService.runCalcNextMetaState;
import static de.schmiereck.col.services.UniverseService.runCalcNextState;
import static de.schmiereck.col.services.UniverseService.runLevelUp;
import static de.schmiereck.col.services.UniverseService.runLevelDown;
import static de.schmiereck.col.services.UniverseUtils.printCells;
import static de.schmiereck.col.services.UniverseUtils.printCellsMinimal;
import static de.schmiereck.col.services.UniverseUtils.setStatePos;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.engine.dynaMove.CreateLevel1DynamicMoveEngineService;
import de.schmiereck.col.services.UniverseService;
import de.schmiereck.col.services.engine.stat.CreateLevel0StaticEngineService;

public class Main2 {

   public static int universeSize = 12*2;

   public static void main(String[] args) {
      //----------------------------------------------------------------------------------------------------------------
      // Engine Level 0:
      final Engine level0Engine = CreateLevel0StaticEngineService.createLevel0staticEngine();

      // Engine Level 1 (static):
      //final Engine level1staticEngine = CreateEngineService.createLevel1staticEngine();

      // Engine Level 1 (dynamic):
      //final Engine level1dynamicEngine = CreateEngineService.createLevel1dynamicEngine();

      // Engine Level 1 (move):
      final Engine level1moveEngine = CreateLevel1DynamicMoveEngineService.createLevel1DynamicMoveEngine();

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

      final Universe universe = new Universe(engineArr, universeSize);

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
      for (int metaCellPos = 0; metaCellPos < 2; metaCellPos++) {
         for (int cellPos = 0; cellPos < universeSize/1; cellPos += 4) {
            setStatePos(universe, cellPos + 0, 1, metaCellPos, 3);   // l1mov 3:0,1
            setStatePos(universe, cellPos + 2, 1, metaCellPos, 5);   // l1mov 5:1,0
         }
      }
      UniverseService.calcInitialMetaStates(universe);

      //----------------------------------------------------------------------------------------------------------------
      for (int cnt = 0; cnt < 6*2*8; cnt++) {
         //runTest1(universe, cnt);
         //runTest2(universe, cnt);
         printCellsMinimal(universe, cnt);
         //runTest3(universe, cnt, false);
         //runTest4(universe, cnt, false);
         //runTest5(universe, cnt, false);
         runTestNextMS(universe, cnt, false);
      }
   }

   private static void runTest1(final Universe universe, final int cnt) {
      printCells(universe, cnt);
      runNextUSDS(universe);
   }

   private static void runTest2(final Universe universe, final int cnt) {
      printCells(universe, cnt);
      runLevelDown(universe);

      printCells(universe, cnt);
      runCalcNextState(universe);

      printCells(universe, cnt);
      runLevelUp(universe);

      printCells(universe, cnt);
      runCalcNextState(universe);
   }

   private static void runTest3(final Universe universe, final int cnt, final boolean doPrint) {
      if (doPrint) printCells(universe, cnt);
      runLevelDown(universe);

      if (doPrint) printCells(universe, cnt);
      runCalcNextState(universe);

      if (doPrint) printCells(universe, cnt);
      runLevelUp(universe);

      if (doPrint) printCells(universe, cnt);
      runCalcNextMetaState(universe);
   }

   private static void runTest4(final Universe universe, final int cnt, final boolean doPrint) {
      if (doPrint) printCells(universe, cnt);
      runLevelDown(universe);

      if (doPrint) printCells(universe, cnt);
      runCalcNextMetaState(universe);

      if (doPrint) printCells(universe, cnt);
      runLevelUp(universe);

      if (doPrint) printCells(universe, cnt);
      runCalcNextState(universe);
   }

   private static void runTest5(final Universe universe, final int cnt, final boolean doPrint) {
      if (doPrint) printCells(universe, cnt);
      runLevelUp(universe);

      if (doPrint) printCells(universe, cnt);
      runCalcNextMetaState(universe);

      if (doPrint) printCells(universe, cnt);
      runLevelDown(universe);

      if (doPrint) printCells(universe, cnt);
      runCalcNextState(universe);
   }

   /**
    * Run Next Meta+State
    */
   private static void runTestNextMS(final Universe universe, final int cnt, final boolean doPrint) {
      //if (doPrint) printCells(universe, cnt);
      //runLevelUp(universe);

      if (doPrint) printCells(universe, cnt);
      runCalcNextMetaState(universe);

      //if (doPrint) printCells(universe, cnt);
      //runLevelDown(universe);

      if (doPrint) printCells(universe, cnt);
      runCalcNextState(universe);
   }
}
