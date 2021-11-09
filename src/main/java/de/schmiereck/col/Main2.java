package de.schmiereck.col;

import static de.schmiereck.col.services.UniverseService.printCells;
import static de.schmiereck.col.services.UniverseService.run;
import static de.schmiereck.col.services.UniverseService.runCalcNextMetaState;
import static de.schmiereck.col.services.UniverseService.runCalcNextState;
import static de.schmiereck.col.services.UniverseService.runLevelDown;
import static de.schmiereck.col.services.UniverseService.runLevelUp;
import static de.schmiereck.col.services.UniverseService.setStatePos;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.CreateEngineService;
import de.schmiereck.col.services.UniverseService;

public class Main2 {

   public static int universeSize = 12;

   public static void main(String[] args) {
      //----------------------------------------------------------------------------------------------------------------
      // Engine Level 0:
      final Engine level0Engine = CreateEngineService.createLevel0staticEngine();

      // Engine Level 1:
      final Engine level1staticEngine = CreateEngineService.createLevel1staticEngine();

      // Engine Level 1:
      final Engine level1dynamicEngine = CreateEngineService.createLevel1dynamicEngine();

      // Engine Level 2 (static):
      final Engine level2staticEngine = CreateEngineService.createLevel2staticEngine();

      // Engine Level 2 (dynamic):
      final Engine level2dynamicEngine = CreateEngineService.createLevel2dynamicEngine();

      // Engine Level 3:
      final Engine level3staticEngine = CreateEngineService.createLevel3staticEngine();

      //----------------------------------------------------------------------------------------------------------------
      final Engine[] engine2Arr = new Engine[2];
      engine2Arr[0] = level0Engine;
      engine2Arr[1] = level1staticEngine;

      final Engine[] engine3Arr = new Engine[3];
      engine3Arr[0] = level0Engine;
      engine3Arr[1] = level1dynamicEngine;
      engine3Arr[2] = level2dynamicEngine;

      final Engine[] engineArr = engine3Arr;    // !!!! TEST !!!!

      final Universe universe = new Universe(engineArr, universeSize);

      //----------------------------------------------------------------------------------------------------------------
      // engine3Arr:

      for (int metaCellPos = 0; metaCellPos < 3; metaCellPos++) {
         for (int cellPos = 0; cellPos < universeSize; cellPos += 6) {
            setStatePos(universe, cellPos + 0, 2, metaCellPos, 10);   // l2dyn 10: 1, 0, 1
            setStatePos(universe, cellPos + 3, 2, metaCellPos, 2);   // l2dyn  2: 0, 1, 0
         }
      }
      setStatePos(universe, 6, 2, 0,  17);   // l2dyn 17: 1, 1, 1
      setStatePos(universe, 7, 2, 0,  17);   // l2dyn 17: 1, 1, 1

      UniverseService.calcInitialMetaStates(universe);

      //----------------------------------------------------------------------------------------------------------------
      for (int cnt = 0; cnt < 6*2; cnt++) {
         //runTest1(universe, cnt);
         //runTest2(universe, cnt);
         runTest3(universe, cnt);
      }
   }

   private static void runTest1(final Universe universe, final int cnt) {
      printCells(universe, cnt);
      run(universe);
   }

   private static void runTest2(final Universe universe, final int cnt) {
      printCells(universe, cnt);
      runLevelUp(universe);

      printCells(universe, cnt);
      runCalcNextState(universe);

      printCells(universe, cnt);
      runLevelDown(universe);

      printCells(universe, cnt);
      runCalcNextState(universe);
   }

   private static void runTest3(final Universe universe, final int cnt) {
      printCells(universe, cnt);
      runLevelUp(universe);

      printCells(universe, cnt);
      runCalcNextState(universe);

      printCells(universe, cnt);
      runLevelDown(universe);

      printCells(universe, cnt);
      runCalcNextMetaState(universe);
   }
}
