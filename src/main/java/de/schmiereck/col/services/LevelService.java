package de.schmiereck.col.services;

import de.schmiereck.col.model.Level;
import de.schmiereck.col.model.State;

public class LevelService {

   public static State calcEqualMetaStateValues(final Level level, final int cellPos, final int cellSizeInLevel) {
      State retState = null;

      for (int pos = 0; pos < cellSizeInLevel; pos++) {
         final int statePos = level.levelCellArr[cellPos].metaCellArr[pos].statePos;
         //if (statePos == 0) {
         //   retState = null;
         //   break;
         //}
         final State state = level.engine.inputStateArr[statePos].inputStates[pos];
         if (retState == null) {
            retState = state;
         } else {
            if (retState != state) {
               retState = null;
               break;
            }
         }
      }
      return retState;
   }
}
