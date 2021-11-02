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
      // Engine Level 1:
      final Engine level1Engine = EngineService.createLevel1staticEngine();

      // Engine Level 2:
      final Engine level2Engine = EngineService.createLevel2staticEngine();

      // Engine Level 3 (static):
      final Engine level3staticEngine = EngineService.createLevel3staticEngine();

      // Engine Level 3 (dynamic):
      final Engine level3dynamicEngine = EngineService.createLevel3dynamicEngine();

      // Engine Level 4:
      final Engine level4staticEngine = EngineService.createLevel4staticEngine();

      //----------------------------------------------------------------------------------------------------------------
      final Engine[] engine2Arr = new Engine[2];
      engine2Arr[0] = level1Engine;
      engine2Arr[1] = level2Engine;

      final Engine[] engine3Arr = new Engine[3];
      engine3Arr[0] = level1Engine;
      engine3Arr[1] = level2Engine;
      //engine3Arr[2] = level3staticEngine;
      engine3Arr[2] = level3dynamicEngine;

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
