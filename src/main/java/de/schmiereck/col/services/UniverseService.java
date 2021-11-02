package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.neg0State;
import static de.schmiereck.col.model.State.nul0State;
import static de.schmiereck.col.model.State.pos0State;

import de.schmiereck.col.model.Cell;
import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Level;
import de.schmiereck.col.model.State;
import de.schmiereck.col.model.Universe;

public class UniverseService {

   public static void setStatePos(final Universe universe, final int cellPos, final int levelPos, final int statePos) {
      setStatePos(universe, cellPos, levelPos, 0, statePos);
   }

   public static void setStatePos(final Universe universe, final int cellPos, final int levelPos, final int metaCellPos, final int statePos) {
      universe.levelArr[levelPos].levelCellArr[cellPos].metaCellArr[metaCellPos].statePos = statePos;
   }

   public static State readCellState(final Universe universe, final int cellPos, final int levelPos, final int inputStatePos) {
      final Engine levelEngine = universe.engineArr[levelPos];
      return levelEngine.inputStateArr[readCell(universe, cellPos, levelPos).statePos].inputStates[inputStatePos];
   }

   public static Cell readCell(final Universe universe, final int cellPos, final int levelPos) {
      return readCell(universe, cellPos, levelPos, 0);
   }

   public static Cell readCell(final Universe universe, final int cellPos, final int levelPos, final int metaCellPos) {
      return universe.levelArr[levelPos].levelCellArr[calcCellPos(universe, cellPos)].metaCellArr[metaCellPos];
   }

   public static int readCellSize(final Universe universe, final int levelPos) {
      return universe.levelArr[levelPos].engine.cellSize;
   }

   public static int calcCellPos(final Universe universe, final int pos) {
      final int retPos;
      if (pos >= universe.universeSize) {
         retPos = pos - universe.universeSize;
      } else {
         if (pos < 0) {
            retPos = pos + universe.universeSize;
         } else {
            retPos = pos;
         }
      }
      return retPos;
   }

   public static void printCells(final Universe universe, final int cnt) {
      final Engine[] engineArr = universe.engineArr;
      for (int levelNr = engineArr.length; levelNr >= 1; levelNr--) {
         final int levelPos = levelNr - 1;
         for (int levelShift = 0; levelShift < levelNr; levelShift++) {
            System.out.printf("%4d/%1d:%s ", cnt, levelNr, " ".repeat((levelShift) * 8));
            for (int pos = 0; pos < universe.universeSize; pos += levelNr) {
               //final Main2.Cell cell = cellArr[genPos][pos];
               final Cell cell = readCell(universe, pos + levelShift, levelPos);
               //if (cell.block) {
               //   System.out.printf("(**/**/**) ");
               //} else {
               final Engine engine = engineArr[levelPos];
               final State state = engine.inputStateArr[cell.statePos];
               System.out.print("(");
               for (int statePos = 0; statePos < state.inputStates.length; statePos++) {
                  final State inputState = state.inputStates[statePos];
                  final int value;
                  if (inputState == neg0State) {
                     value = -1;
                  } else {
                     if (inputState == nul0State) {
                        value = 0;
                     } else {
                        if (inputState == pos0State) {
                           value = 1;
                        } else {
                           value = 99;
                        }
                     }
                  }
                  if (statePos > 0) {
                     System.out.print("   ");
                  }
                  System.out.printf("%2d/%2d", cell.statePos, value);
               }
               System.out.print(") ");
               //}
            }
            System.out.println();
         }
      }
      System.out.print("  ----- ");
      System.out.println("------- ".repeat(universe.universeSize));
   }

   public static void run(final Universe universe) {
      runLevelUp(universe);
      runCalcNextState(universe);
      runLevelDown(universe);
      runCalcNextState(universe);
   }

   private static void runCalcNextState(final Universe universe) {
      final Engine[] engineArr = universe.engineArr;
      for (int levelPos = 0; levelPos < engineArr.length; levelPos++) {
         final Engine engine = engineArr[levelPos];

         for (int cellPos = 0; cellPos < universe.universeSize; cellPos++) {
            final Cell sourceCell = readCell(universe, cellPos, levelPos);
            final int outputStatePos = engine.outputStatePosArr[sourceCell.statePos];

            final Cell targetCell = readCell(universe, cellPos, levelPos);
            targetCell.statePos = outputStatePos;
         }
      }
   }

   private static void runLevelDown(final Universe universe) {
      final Engine[] engineArr = universe.engineArr;
      for (int levelPos = engineArr.length - 2; levelPos >= 0; levelPos--) {
         final int targetLevelPos = levelPos + 1;

         for (int cellPos = 0; cellPos < universe.universeSize; cellPos++) {
            final int cellSize = readCellSize(universe, levelPos);
            final int targetCellSize = readCellSize(universe, targetLevelPos);

            final State equalState = calcEqualMetaStateValues(universe, levelPos, cellPos, cellSize);
            final State targetEqualState = calcEqualMetaStateValues(universe, targetLevelPos, cellPos, targetCellSize);

            if ((equalState != null) && (equalState != nul0State) && (targetEqualState == nul0State)) {
               // Level 1 -> 2:

               // Status aller Zellen in Level 1 auf 0 setzen
               calcNewEqualState(engineArr, universe, levelPos, cellPos, nul0State);

               // Status aller Zellen in Level 2 auf equalState setzen
               calcNewEqualState(engineArr, universe, targetLevelPos, cellPos, equalState);
            }
         }
      }
   }

   public static void runLevelUp(final Universe universe) {
      final Engine[] engineArr = universe.engineArr;
      for (int levelPos = 1; levelPos < engineArr.length; levelPos++) {
         final int targetLevelPos = levelPos - 1;

         for (int cellPos = 0; cellPos < universe.universeSize; cellPos++) {
            final int cellSize = readCellSize(universe, levelPos);
            final int targetCellSize = readCellSize(universe, targetLevelPos);

            final State equalState = calcEqualMetaStateValues(universe, levelPos, cellPos, cellSize);
            final State targetEqualState = calcEqualMetaStateValues(universe, targetLevelPos, cellPos, targetCellSize);

            if ((equalState != null) && (equalState != nul0State) && (targetEqualState == nul0State)) {
               // Level 2 -> 1:

               // Status aller Zellen in Level 2 auf 0 setzen
               calcNewEqualState(engineArr, universe, levelPos, cellPos, nul0State);

               // Status aller Zellen in Level 1 auf equalState setzen
               calcNewEqualState(engineArr, universe, targetLevelPos, cellPos, equalState);
            }
         }
      }
   }

   private static void calcNewEqualState(final Engine[] engineArr, final Universe universe, final int levelPos, final int cellPos, final State newState) {
      final int cellSizeInLevel = engineArr[levelPos].cellSize;

      for (int pos = 0; pos < cellSizeInLevel; pos++) {
         final Cell metaCell = readCell(universe, cellPos, levelPos, pos);
         metaCell.statePos = searchStatePosWithNewStateOnPos(engineArr[levelPos], metaCell, pos, newState);
      }
   }

   private static int searchStatePosWithNewStateOnPos(final Engine engine, final Cell metaCell, final int statePosOfSearchedNewState, final State searchedNewState) {
      int retStatePos = 0;
      final State searchedState = engine.inputStateArr[metaCell.statePos];

      for (int engineInputStatePos = 0; engineInputStatePos < engine.inputStateArr.length; engineInputStatePos++) {
         final State engineState = engine.inputStateArr[engineInputStatePos];

         boolean allEqual = checkAllStatesAreEqual(engineState, searchedState, statePosOfSearchedNewState, searchedNewState);
         if (allEqual) {
            retStatePos = engineInputStatePos;
            break;
         }
      }
      return retStatePos;
   }

   private static boolean checkAllStatesAreEqual(final State engineState, final State searchedState, final int statePosOfSearchedNewState, final State searchedNewState) {
      boolean allEqual = true;
      for (int statePos = 0; statePos < engineState.inputStates.length; statePos++) {
         final State inputState = engineState.inputStates[statePos];
         final State searchedInputState = searchedState.inputStates[statePos];
         if (statePos == statePosOfSearchedNewState) {
            if (inputState != searchedNewState) {
               allEqual = false;
               break;
            }
         } else {
            if (inputState != searchedInputState) {
               allEqual = false;
               break;
            }
         }
      }
      return allEqual;
   }

   private static State calcEqualMetaStateValues(final Universe universe, final int levelPos, final int cellPos, final int cellSizeInLevel) {
      State retState = null;
      final Level level = universe.levelArr[levelPos];

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

   private static int searchForStatePos(final Engine l0Engine, final State inputState0) {
      int retStatePos = -1;

      for (int statePos = 0; statePos < l0Engine.inputStateArr.length; statePos++) {
         final State state = l0Engine.inputStateArr[statePos];
         if ((state.inputStates[0] == inputState0)) {
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
         if ((state.inputStates[0] == inputState0) && (state.inputStates[1] == inputState1)) {
            retStatePos = statePos;
            break;
         }
      }
      if (retStatePos == -1) {
         throw new RuntimeException("Do not found given inputs in given engine.");
      }
      return retStatePos;
   }
}
