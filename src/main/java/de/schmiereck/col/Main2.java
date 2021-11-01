package de.schmiereck.col;

import java.util.Arrays;
import java.util.Optional;

public class Main2 {

   static class State {
      State[] inputStates;

      State(final int size) {
         this.inputStates = new State[size];
      }
      State(final int size, final State ... state) {
         this.inputStates = new State[size];
         for (int pos = 0; pos < state.length; pos++) {
            this.inputStates[pos] = state[pos];
         }
      }
   }

   static class Engine {
      final int cellSize;
      State[] inputStateArr;
      int[] outputStatePosArr;

      Engine(final int cellSize) {
         this.cellSize = cellSize;
         final int size = (int)Math.pow(3, cellSize);
         this.inputStateArr = new State[size];
         this.outputStatePosArr = new int[size];
      }

      void setState(final int pos, final State inputState, final int outputStatePos) {
         this.inputStateArr[pos] = inputState;
         this.outputStatePosArr[pos] = outputStatePos;
      }

   }

   static class Cell {
      int stateLevel;
      int statePos;
      Cell[] metaCellArr;
   }
   public static class LevelCell {
      final Cell[] metaCellArr;

      public LevelCell(final int size) {
         this.metaCellArr = new Cell[size];
      }
   }
   public static class Level {
      final Engine engine;
      final LevelCell[] levelCellArr;

      public Level(final Engine engine, final int levelSize) {
         this.engine = engine;
         this.levelCellArr = new LevelCell[levelSize];
      }
   }
   /**
    * <code>
    *    00000 33333 66666 99999
    *   .0 11111 44444 7777. 0000.
    *   .111 22222 55555 88888 11.
    *                              ^
    *    0-0 2-2 4-4 6-6 8-8 0-0   cell[].metaCellArr[0]
    *   .1 1-1 3-3 5-5 7-7 9-9 1-. cell[].metaCellArr[1]
    *                              ^
    *    0 1 2 3 4 5 6 7 8 9 0 1   cell[]
    * </code>
    */
   public static class Universe {
      final Level[] levelArr;

      public Universe(final Engine[] engineArr, final int levelSize) {
         final int levelCount = engineArr.length;
         this.levelArr = new Level[levelCount];

         for (int levelPos = 0; levelPos < levelCount; levelPos++) {
            final Engine engine = engineArr[levelPos];

            final Level level = new Level(engine, levelSize);
            this.levelArr[levelPos] = level;

            for (int levelCellPos = 0; levelCellPos < levelSize; levelCellPos++) {
               final LevelCell levelCell = new LevelCell(engine.cellSize);

               level.levelCellArr[levelCellPos] = levelCell;
            }

            for (int levelCellPos = 0; levelCellPos < levelSize; levelCellPos++) {
               final Cell newCell = new Cell();

               for (int metaCellPos = 0; metaCellPos < engine.cellSize; metaCellPos++) {
                  level.levelCellArr[calcCellPos(levelCellPos + metaCellPos)].metaCellArr[metaCellPos] = newCell;
               }
            }
         }
      }
   }

   public static int universeSize = 12;

   final static State nul0State = new State(0);
   final static State pos0State = new State(0);
   final static State neg0State = new State(0);

   public static void main(String[] args) {
      //----------------------------------------------------------------------------------------------------------------
      // Engine Level 1:
      final State nul_1State = new State(1);
      nul_1State.inputStates[0] = nul0State;

      final State pos_1State = new State(1);
      pos_1State.inputStates[0] = pos0State;

      final State neg_1State = new State(1);
      neg_1State.inputStates[0] = neg0State;

      final Engine level1Engine = new Engine(1);
      level1Engine.setState(0, nul_1State, 0);
      level1Engine.setState(1, pos_1State, 1);
      level1Engine.setState(2, neg_1State, 2);

      //----------------------------------------------------------------------------------------------------------------
      // Engine Level 2:
      final State nul_nul_2State = new State(2);
      nul_nul_2State.inputStates[0] = nul0State;
      nul_nul_2State.inputStates[1] = nul0State;

      final State nul_pos_2State = new State(2);
      nul_pos_2State.inputStates[0] = nul0State;
      nul_pos_2State.inputStates[1] = pos0State;

      final State nul_neg_2State = new State(2);
      nul_neg_2State.inputStates[0] = nul0State;
      nul_neg_2State.inputStates[1] = neg0State;

      final State pos_nul_2State = new State(2);
      pos_nul_2State.inputStates[0] = pos0State;
      pos_nul_2State.inputStates[1] = nul0State;

      final State pos_pos_2State = new State(2);
      pos_pos_2State.inputStates[0] = pos0State;
      pos_pos_2State.inputStates[1] = pos0State;

      final State pos_neg_2State = new State(2);
      pos_neg_2State.inputStates[0] = pos0State;
      pos_neg_2State.inputStates[1] = neg0State;

      final State neg_nul_2State = new State(2);
      neg_nul_2State.inputStates[0] = neg0State;
      neg_nul_2State.inputStates[1] = nul0State;

      final State neg_pos_2State = new State(2);
      neg_pos_2State.inputStates[0] = neg0State;
      neg_pos_2State.inputStates[1] = pos0State;

      final State neg_neg_2State = new State(2);
      neg_neg_2State.inputStates[0] = neg0State;
      neg_neg_2State.inputStates[1] = neg0State;

      final Engine level2Engine = new Engine(2);
      level2Engine.setState(0, nul_nul_2State, 0);
      level2Engine.setState(1, nul_pos_2State, 1);
      level2Engine.setState(2, nul_neg_2State, 2);
      level2Engine.setState(3, pos_nul_2State, 3);
      level2Engine.setState(4, pos_pos_2State, 4);
      level2Engine.setState(5, pos_neg_2State, 0);
      level2Engine.setState(6, neg_nul_2State, 6);
      level2Engine.setState(7, neg_pos_2State, 0);
      level2Engine.setState(8, neg_neg_2State, 8);

      //----------------------------------------------------------------------------------------------------------------
      // Engine Level 3:
      final State nul_nul_nul_3State = new State(3, nul0State, nul0State, nul0State);

      final Engine level3Engine = new Engine(3);
      //    0   0   0   =>  0   0   0     0   0   0
      level3Engine.setState(0, nul_nul_nul_3State, 0);
      //    0   0   1   =>  0   0   1     0   1   0
      level3Engine.setState(1, new State(3, nul0State, nul0State, pos0State), 1);
      //    0   0  -1  =>   0   0  -1     0  -1   0
      level3Engine.setState(2, new State(3, nul0State, nul0State, neg0State), 2);
      //    0   1   0   =>  0   1   0     0   1   0
      level3Engine.setState(3, new State(3, nul0State, pos0State, nul0State), 3);
      //    0   1   1   =>  0   1   1     1   1   0
      level3Engine.setState(4, new State(3, nul0State, pos0State, pos0State), 4);
      //    0   1  -1  =>   0   0   0     0   0   0
      level3Engine.setState(5, new State(3, nul0State, pos0State, neg0State), 0);
      //    0  -1   0   =>  0  -1   0     0  -1   0
      level3Engine.setState(6, new State(3, nul0State, neg0State, nul0State), 6);
      //    0  -1   1   =>  0   0   0     0   0   0
      level3Engine.setState(7, new State(3, nul0State, neg0State, nul0State), 7);
      //    0  -1  -1  =>   0  -1  -1    -1  -1   0
      level3Engine.setState(8, new State(3, nul0State, neg0State, neg0State), 8);
      //
      //    1   0   0   =>  1   0   0     0   1   0
      level3Engine.setState(9, new State(3, pos0State, nul0State, nul0State), 9);
      //    1   0   1   =>  1   0   1     1   0   1
      level3Engine.setState(10, new State(3, pos0State, nul0State, pos0State), 10);
      //    1   0  -1  =>   1   0  -1     0   0   0
      level3Engine.setState(11, new State(3, pos0State, nul0State, neg0State), 11);
      //    1   1   0   =>  1   1   0     0   1   1
      level3Engine.setState(12, new State(3, pos0State, pos0State, nul0State), 12);
      //    1   1   1   =>  1   1   1     1   1   1
      level3Engine.setState(13, new State(3, pos0State, pos0State, pos0State), 13);
      //    1   1  -1  =>   1   0   0     1   0   0
      level3Engine.setState(14, new State(3, pos0State, pos0State, neg0State), 9);
      //    1  -1   0   =>  0   0   0     0   0   0
      level3Engine.setState(15, new State(3, pos0State, neg0State, nul0State), 0);
      //    1  -1   1   =>  1  -1   1     0   1   0
      level3Engine.setState(16, new State(3, pos0State, neg0State, pos0State), 16);
      //    1  -1  -1  =>   0   0  -1     0   0  -1
      level3Engine.setState(17, new State(3, pos0State, neg0State, neg0State), 2);
      //
      //   -1   0   0   => -1   0   0     0  -1   0
      level3Engine.setState(18, new State(3, neg0State, nul0State, nul0State), 18);
      //   -1   0   1   => -1   0   1     0   0   0
      level3Engine.setState(19, new State(3, neg0State, nul0State, pos0State), 19);
      //   -1   0  -1  =>  -1   0  -1    -1   0  -1
      level3Engine.setState(20, new State(3, neg0State, nul0State, neg0State), 20);
      //   -1   1   0   =>  0   0   0     0   0   0
      level3Engine.setState(21, new State(3, neg0State, pos0State, nul0State), 0);
      //   -1   1   1   =>  0   0   1     0   0   1
      level3Engine.setState(22, new State(3, neg0State, pos0State, pos0State), 1);
      //   -1   1  -1  =>  -1   1  -1    -1   1  -1
      level3Engine.setState(23, new State(3, neg0State, pos0State, neg0State), 23);
      //   -1  -1   0   => -1  -1   0     0  -1  -1
      level3Engine.setState(24, new State(3, neg0State, neg0State, nul0State), 24);
      //   -1  -1   1   => -1   0   0    -1   0   0
      level3Engine.setState(25, new State(3, neg0State, neg0State, pos0State), 18);
      //   -1  -1  -1  =>  -1  -1  -1    -1  -1  -1
      level3Engine.setState(26, new State(3, neg0State, neg0State, neg0State), 26);

      //----------------------------------------------------------------------------------------------------------------
      // Engine Level 4:
      final State nul_nul_nul_nul_4State = new State(4);
      nul_nul_nul_nul_4State.inputStates[0] = nul0State;
      nul_nul_nul_nul_4State.inputStates[1] = nul0State;
      nul_nul_nul_nul_4State.inputStates[2] = nul0State;
      nul_nul_nul_nul_4State.inputStates[3] = nul0State;

      final Engine level4Engine = new Engine(4);
      level4Engine.setState(0, nul_nul_nul_nul_4State, 0);

      //----------------------------------------------------------------------------------------------------------------
      final Engine[] engine2Arr = new Engine[2];
      engine2Arr[0] = level1Engine;
      engine2Arr[1] = level2Engine;

      final Engine[] engine3Arr = new Engine[3];
      engine3Arr[0] = level1Engine;
      engine3Arr[1] = level2Engine;
      engine3Arr[2] = level3Engine;

      final Engine[] engineArr = engine3Arr;    // !!!! TEST !!!!

      final Universe universe = new Universe(engineArr, universeSize);

      //----------------------------------------------------------------------------------------------------------------
      setStatePos(universe, universeSize / 2, 1);
      setStatePos(universe, 0, 1);
      setStatePos(universe, 1, 2);

      for (int cnt = 0; cnt < 3; cnt++) {
         printCells(universe, engineArr, cnt);

         run(engineArr, universe);
      }
   }

   private static void printCells(final Universe universe, final Engine[] engineArr, final int cnt) {
      for (int levelNr = engineArr.length; levelNr >= 1; levelNr--) {
         final int levelPos = levelNr - 1;
         for (int levelShift = 0; levelShift < levelNr; levelShift++) {
            System.out.printf("%4d/%1d:%s ", cnt, levelNr, " ".repeat((levelShift) * 8));
            for (int pos = 0; pos < universeSize; pos += levelNr) {
               //final Main2.Cell cell = cellArr[genPos][pos];
               final Main2.Cell cell = readCell(universe, pos + levelShift, levelPos);
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
      System.out.println("------- ".repeat(universeSize));
   }

   private static void run(final Engine[] engineArr, final Universe universe) {
      runLevelUp(engineArr, universe);
      runCalcNextState(engineArr, universe);
      runLevelDown(engineArr, universe);
   }

   private static void runCalcNextState(final Engine[] engineArr, final Universe universe) {
      for (int levelPos = 0; levelPos < engineArr.length; levelPos++) {
         final Engine engine = engineArr[levelPos];

         for (int cellPos = 0; cellPos < universeSize; cellPos++) {
            final Cell sourceCell = readCell(universe, cellPos, levelPos);
            final int outputStatePos = engine.outputStatePosArr[sourceCell.statePos];

            final Cell targetCell = readCell(universe, cellPos, levelPos);
            targetCell.statePos = outputStatePos;
         }
      }
   }

   private static void runLevelUp(final Engine[] engineArr, final Universe universe) {
      for (int levelPos = engineArr.length - 2; levelPos >= 0; levelPos--) {

         for (int cellPos = 0; cellPos < universeSize; cellPos++) {
            final int cellSize = readCellSize(universe, levelPos);
            final int targetCellSize = readCellSize(universe, levelPos + 1);

            final State equalState = calcEqualMetaStateValues(universe, levelPos, cellPos, cellSize);
            final State targetEqualState = calcEqualMetaStateValues(universe, levelPos + 1, cellPos, targetCellSize);

            if ((equalState != null) && (equalState != nul0State) && (targetEqualState == nul0State)) {
               // Level 1 -> 2:

               // Status aller Zellen in Level 1 auf 0 setzen
               calcNewEqualState(engineArr, universe, levelPos, cellPos, nul0State);

               // Status aller Zellen in Level 2 auf equalState setzen
               calcNewEqualState(engineArr, universe, levelPos + 1, cellPos, equalState);
            }
         }
      }
   }

   private static void runLevelDown(final Engine[] engineArr, final Universe universe) {
      for (int levelPos = 1; levelPos < engineArr.length; levelPos++) {

         for (int cellPos = 0; cellPos < universeSize; cellPos++) {
            final int cellSize = readCellSize(universe, levelPos);
            final int targetCellSize = readCellSize(universe, levelPos - 1);

            final State equalState = calcEqualMetaStateValues(universe, levelPos, cellPos, cellSize);
            final State targetEqualState = calcEqualMetaStateValues(universe, levelPos - 1, cellPos, targetCellSize);

            if ((equalState != null) && (equalState != nul0State) && (targetEqualState == nul0State)) {
               // Level 2 -> 1:

               // Status aller Zellen in Level 2 auf 0 setzen
               calcNewEqualState(engineArr, universe, levelPos, cellPos, nul0State);

               // Status aller Zellen in Level 1 auf equalState setzen
               calcNewEqualState(engineArr, universe, levelPos - 1, cellPos, equalState);
            }
         }
      }
   }

   private static void calcNewEqualState(final Engine[] engineArr, final Universe universe, final int levelPos, final int cellPos, final State newState) {
      final int cellSizeInLevel = engineArr[levelPos].cellSize;

      for (int pos = 0; pos < cellSizeInLevel; pos++) {
         final Main2.Cell metaCell = readCell(universe, cellPos, levelPos, pos);
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

   private static void setStatePos(final Universe universe, final int cellPos, final int statePos) {
      universe.levelArr[0].levelCellArr[cellPos].metaCellArr[0].statePos = statePos;
   }

   private static int calcCellPos(final int pos) {
      final int retPos;
      if (pos >= universeSize) {
         retPos = pos - universeSize;
      } else {
         if (pos < 0) {
            retPos = pos + universeSize;
         } else {
            retPos = pos;
         }
      }
      return retPos;
   }

   private static Cell readCell(final Universe universe, final int pos, final int levelPos) {
      return readCell(universe, pos, levelPos, 0);
   }

   private static Cell readCell(final Universe universe, final int pos, final int levelPos, final int metaCellPos) {
      return universe.levelArr[levelPos].levelCellArr[calcCellPos(pos)].metaCellArr[metaCellPos];
   }

   private static int readCellSize(final Universe universe, final int levelPos) {
      return universe.levelArr[levelPos].engine.cellSize;
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
