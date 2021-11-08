package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;

import de.schmiereck.col.model.Cell;
import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.LevelCell;
import de.schmiereck.col.model.MetaState;
import de.schmiereck.col.model.State;

public class EngineService {

   private static int searchMetaStatePos(final MetaState[] engineMetaStateArr, final MetaState searchedMetaState) {
      return searchMetaStatePos(engineMetaStateArr, searchedMetaState.inputMetaStatePosArr);
   }

   private static int searchMetaStatePos(final MetaState[] engineMetaStateArr, final int searchedInputMetaStatePosArr[]) {
      for (int msPos = 0; msPos < engineMetaStateArr.length; msPos++) {
         final MetaState metaState = engineMetaStateArr[msPos];
         boolean found = true;
         for (int statePos = 0; statePos < metaState.inputMetaStatePosArr.length; statePos++) {
            if (metaState.inputMetaStatePosArr[statePos] != searchedInputMetaStatePosArr[statePos]) {
               found = false;
               break;
            }
         }
         if (found) {
            return msPos;
         }
      }
      return -1;
   }

   public static int searchMetaStatePos(final Engine engine, final LevelCell searchedLevelCell) {
      final int inputMetaStatePosArr[] = new int[searchedLevelCell.metaCellArr.length];

      for (int pos = 0; pos < searchedLevelCell.metaCellArr.length; pos++) {
         final Cell cell = searchedLevelCell.metaCellArr[pos];

         inputMetaStatePosArr[pos] = cell.statePos;
      }
      return searchMetaStatePos(engine.metaStateArr, inputMetaStatePosArr);
   }
}
