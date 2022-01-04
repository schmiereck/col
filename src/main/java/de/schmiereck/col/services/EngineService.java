package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.NULL_pos;

import de.schmiereck.col.model.Cell;
import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.LevelCell;
import de.schmiereck.col.model.MetaState;
import de.schmiereck.col.model.MetaStateList;
import de.schmiereck.col.model.State;

public class EngineService {

   private static int searchMetaStatePos(final MetaStateList engineMetaStateList, final MetaState searchedMetaState) {
      return searchMetaStatePos(engineMetaStateList, searchedMetaState.inputMetaStatePosArr);
   }

   private static int searchMetaStatePos(final MetaStateList engineMetaStateList, final int searchedInputMetaStatePosArr[]) {
      for (int msPos = 0; msPos < engineMetaStateList.size(); msPos++) {
         final MetaState metaState = engineMetaStateList.get(msPos);
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
      final int size = calcMetaStateSize(engine);
      final int inputMetaStatePosArr[] = new int[size];

      for (int pos = 0; pos < searchedLevelCell.metaCellArr.length; pos++) {
         final Cell cell = searchedLevelCell.metaCellArr[pos];

         inputMetaStatePosArr[pos] = cell.statePos;
      }
      return searchMetaStatePos(engine.metaStateList, inputMetaStatePosArr);
   }

   public static int calcMetaStateSize(final Engine engine) {
      return engine.cellSize == 1 ? 2 : (engine.cellSize);
   }

   public static int searchStatePosWithNewStateOnPos(final Engine engine, final Cell metaCell, final int statePosOfSearchedNewState, final State searchedNewState) {
      int retStatePos = NULL_pos;
      final State givenMetaCellState = engine.inputStateArr[metaCell.statePos];

      for (int engineInputStatePos = 0; engineInputStatePos < engine.inputStateArr.length; engineInputStatePos++) {
         final State engineState = engine.inputStateArr[engineInputStatePos];

         boolean allEqual = checkAllStatesAreEqual(engineState, givenMetaCellState, statePosOfSearchedNewState, searchedNewState);
         if (allEqual) {
            retStatePos = engineInputStatePos;
            break;
         }
      }
      return retStatePos;
   }

   private static boolean checkAllStatesAreEqual(final State engineState, final State givenMetaCellState, final int statePosOfSearchedNewState, final State searchedNewState) {
      boolean allEqual = true;
      for (int statePos = 0; statePos < engineState.inputStateArr.length; statePos++) {
         final State inputState = engineState.inputStateArr[statePos];
         if (statePos == statePosOfSearchedNewState) {
            if (inputState != searchedNewState) {
               allEqual = false;
               break;
            }
         } else {
            final State givenMetaCellStateInputState = givenMetaCellState.inputStateArr[statePos];
            if (inputState != givenMetaCellStateInputState) {
               allEqual = false;
               break;
            }
         }
      }
      return allEqual;
   }

   private static int searchForStatePos(final Engine l0Engine, final State inputState0) {
      int retStatePos = -1;

      for (int statePos = 0; statePos < l0Engine.inputStateArr.length; statePos++) {
         final State state = l0Engine.inputStateArr[statePos];
         if ((state.inputStateArr[0] == inputState0)) {
            retStatePos = statePos;
            break;
         }
      }
      if (retStatePos == -1) {
         throw new RuntimeException("Do not found given inputs in given engine.");
      }
      return retStatePos;
   }

   private static int searchForStatePos(final Engine engine, final State inputState0, final State inputState1) {
      int retStatePos = -1;

      for (int statePos = 0; statePos < engine.inputStateArr.length; statePos++) {
         final State state = engine.inputStateArr[statePos];
         if ((state.inputStateArr[0] == inputState0) && (state.inputStateArr[1] == inputState1)) {
            retStatePos = statePos;
            break;
         }
      }
      if (retStatePos == -1) {
         throw new RuntimeException("Do not found given inputs in given engine.");
      }
      return retStatePos;
   }

   public static int calcMetaStatePosByLevelCell(final Engine engine, final LevelCell levelCell) {
      int metaStatePos = NULL_pos;
      for (int metaPos = 0; metaPos < levelCell.metaCellArr.length; metaPos++) {
         metaStatePos += levelCell.metaCellArr[metaPos].statePos * Math.pow(engine.inputStateArr.length, metaPos);
      }
      return metaStatePos;
   }

   public static MetaState readMetaState(final Engine engine, final Cell cell) {
      return engine.metaStateList.get(cell.metaStatePos);
   }
}
