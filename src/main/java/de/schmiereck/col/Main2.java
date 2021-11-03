package de.schmiereck.col;

import static de.schmiereck.col.services.UniverseService.printCells;
import static de.schmiereck.col.services.UniverseService.readCell;
import static de.schmiereck.col.services.UniverseService.run;
import static de.schmiereck.col.services.UniverseService.setStatePos;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.EngineService;

public class Main2 {

   public static int universeSize = 12;

   public static void main(String[] args) {
      //----------------------------------------------------------------------------------------------------------------
      // Engine Level 0:
      final Engine level0Engine = EngineService.createLevel0staticEngine();

      // Engine Level 1:
      final Engine level1Engine = EngineService.createLevel1staticEngine();

      // Engine Level 2 (static):
      final Engine level2staticEngine = EngineService.createLevel2staticEngine();

      // Engine Level 2 (dynamic):
      final Engine level2dynamicEngine = EngineService.createLevel2dynamicEngine();

      // Engine Level 3:
      final Engine level3staticEngine = EngineService.createLevel3staticEngine();

      //----------------------------------------------------------------------------------------------------------------
      final Engine[] engine2Arr = new Engine[2];
      engine2Arr[0] = level0Engine;
      engine2Arr[1] = level1Engine;

      final Engine[] engine3Arr = new Engine[3];
      engine3Arr[0] = level0Engine;
      engine3Arr[1] = level1Engine;
      //engine3Arr[2] = level2staticEngine;
      engine3Arr[2] = level2dynamicEngine;

      final Engine[] engineArr = engine3Arr;    // !!!! TEST !!!!

      final Universe universe = new Universe(engineArr, universeSize);

      //----------------------------------------------------------------------------------------------------------------
      /*
      setStatePos(universe, universeSize / 2, 0,  1);
      setStatePos(universe, 0, 0, 1);
      setStatePos(universe, 1, 0, 2);
      */
      // engine3Arr:
      setStatePos(universe, 6, 2, 0,  9);
      setStatePos(universe, 4, 2, 0,  1);
      setStatePos(universe, 5, 2, 0,  3);
      //setStatePos(universe, 5, 1, 0,  1);

      //----------------------------------------------------------------------------------------------------------------
      for (int cnt = 0; cnt < 3; cnt++) {
         printCells(universe, cnt);

         run(universe);
      }
   }
}
