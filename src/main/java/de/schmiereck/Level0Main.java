package de.schmiereck;

import static de.schmiereck.col.services.engine.dynaMove.CreateLevel0DynamicMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel0DynamicMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.UniverseService.runNextUSDM;
import static de.schmiereck.col.services.UniverseUtils.printCellsMinimal;
import static de.schmiereck.col.services.UniverseUtils.setStatePos;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.engine.dynaMove.CreateLevel0DynamicMoveEngineService;
import de.schmiereck.col.services.UniverseService;

public class Level0Main {

   public static int universeSize = 12*2;

   public static void main(String[] args) {
      //----------------------------------------------------------------------------------------------------------------
      // Engine Level 0:
      final Engine level0Engine = CreateLevel0DynamicMoveEngineService.createLevel0DynamicMoveEngine();

      //----------------------------------------------------------------------------------------------------------------
      final Engine[] engineArr = new Engine[1];
      engineArr[0] = level0Engine;

      final Universe universe = new Universe(engineArr, universeSize);

         for (int cellPos = 0; cellPos < universeSize/1; cellPos += 6) {
            //setStatePos(universe, cellPos + 0, 0, 0, STAY_p1);
            setStatePos(universe, cellPos + 4, 0, 0, LEFTa_p1);
            setStatePos(universe, cellPos + 2, 0, 0, RIGHTa_p1);
      }
      UniverseService.calcInitialMetaStates(universe);

      //----------------------------------------------------------------------------------------------------------------
      for (int cnt = 0; cnt < 6*2*8; cnt++) {
         printCellsMinimal(universe, cnt);
         runNextUSDM(universe);
      }
   }
}
