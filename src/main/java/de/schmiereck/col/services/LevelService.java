package de.schmiereck.col.services;

import static de.schmiereck.col.services.UniverseUtils.readCellSize;

import de.schmiereck.col.model.Level;
import de.schmiereck.col.model.State;

public class LevelService {

   public static State calcEqualMetaStateValues(final Level level, final int cellPos) {
      State retState = null;
      final int cellSizeInLevel = readCellSize(level);

      for (int pos = 0; pos < cellSizeInLevel; pos++) {
         final int statePos = level.levelCellArr[cellPos].metaCellArr[pos].statePos;
         //if (statePos == 0) {
         //   retState = null;
         //   break;
         //}
         final State state = level.engine.inputStateArr[statePos].inputStateArr[pos];
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
