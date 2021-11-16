package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.StateUtils.convertToDebugString;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.MetaState;
import de.schmiereck.col.model.State;
import de.schmiereck.larray.LarrayInt;

public class CreateEngineService {

   public static Engine createLevel0staticEngine() {
      final State nul_0State = new State(1);
      nul_0State.inputStateArr[0] = nulState;

      final State pos_0State = new State(1);
      pos_0State.inputStateArr[0] = posState;

      final State neg_0State = new State(1);
      neg_0State.inputStateArr[0] = negState;

      final Engine level1Engine = new Engine(1);

      level1Engine.setState(0, nul_0State, 0);
      level1Engine.setState(1, pos_0State, 1);
      level1Engine.setState(2, neg_0State, 2);

      return level1Engine;
   }

   public static Engine createLevel1staticEngine() {
      final State nul_nul_1State = new State(2);
      nul_nul_1State.inputStateArr[0] = nulState;
      nul_nul_1State.inputStateArr[1] = nulState;

      final State nul_pos_1State = new State(2);
      nul_pos_1State.inputStateArr[0] = nulState;
      nul_pos_1State.inputStateArr[1] = posState;

      final State nul_neg_1State = new State(2);
      nul_neg_1State.inputStateArr[0] = nulState;
      nul_neg_1State.inputStateArr[1] = negState;

      final State pos_nul_1State = new State(2);
      pos_nul_1State.inputStateArr[0] = posState;
      pos_nul_1State.inputStateArr[1] = nulState;

      final State pos_pos_1State = new State(2);
      pos_pos_1State.inputStateArr[0] = posState;
      pos_pos_1State.inputStateArr[1] = posState;

      final State pos_neg_1State = new State(2);
      pos_neg_1State.inputStateArr[0] = posState;
      pos_neg_1State.inputStateArr[1] = negState;

      final State neg_nul_1State = new State(2);
      neg_nul_1State.inputStateArr[0] = negState;
      neg_nul_1State.inputStateArr[1] = nulState;

      final State neg_pos_1State = new State(2);
      neg_pos_1State.inputStateArr[0] = negState;
      neg_pos_1State.inputStateArr[1] = posState;

      final State neg_neg_1State = new State(2);
      neg_neg_1State.inputStateArr[0] = negState;
      neg_neg_1State.inputStateArr[1] = negState;

      final Engine level1Engine = new Engine(2);

      level1Engine.setState(0, nul_nul_1State, 0);
      level1Engine.setState(1, nul_pos_1State, 1);
      level1Engine.setState(2, nul_neg_1State, 2);
      level1Engine.setState(3, pos_nul_1State, 3);
      level1Engine.setState(4, pos_pos_1State, 4);
      level1Engine.setState(5, pos_neg_1State, 0);
      level1Engine.setState(6, neg_nul_1State, 6);
      level1Engine.setState(7, neg_pos_1State, 0);
      level1Engine.setState(8, neg_neg_1State, 8);

      return level1Engine;
   }

   public static Engine createLevel1dynamicEngine() {
      final Engine level1dynamicEngine = new Engine(2, 9);

      // 0    0   0   =>   0
      level1dynamicEngine.setState( 0, new State(2, nulState, nulState), 0);
      // 1    0   1   =>   2
      level1dynamicEngine.setState( 1, new State(2, nulState, posState), 2);
      // 2    1   0   =>   1
      level1dynamicEngine.setState( 2, new State(2, posState, nulState), 1);
      // 3    0  -1   =>   4
      level1dynamicEngine.setState( 3, new State(2, nulState, negState), 4);
      // 4   -1   0   =>   3
      level1dynamicEngine.setState( 4, new State(2, negState, nulState), 3);
      // 5    1   1   =>   5
      level1dynamicEngine.setState( 5, new State(2, posState, posState), 5);
      // 6    1  -1   =>   0
      level1dynamicEngine.setState( 6, new State(2, posState, negState), 0);
      // 7   -1   1   =>   0
      level1dynamicEngine.setState( 7, new State(2, negState, posState), 0);
      // 8   -1  -1   =>   8
      level1dynamicEngine.setState( 8, new State(2, negState, negState), 8);

      //0+x:
      // 0  =>  0
      //    0        0   0   =>   0        0   0
      //    0    0   0       =>   0    0   0
      //level1dynamicEngine.metaStateArr[0] = new MetaState(0, level1dynamicEngine.getInputState(0), level1dynamicEngine.getInputState(0));
      // 1  =>  1
      //    1        0   1   =>   1        0   1
      //    0    0   0       =>   0    0   0
      //level1dynamicEngine.metaStateArr[0] = new MetaState(1, level1dynamicEngine.getInputState(1), level1dynamicEngine.getInputState(0));
      // 2  =>  9
      //     2        1   0   =>   0        0   0
      //     0    0   0       =>   1    0   1

      initMetaStateArr(level1dynamicEngine);
      initOutputMetaStatePos(level1dynamicEngine);

      //for (int l1Pos = 0; l1Pos < level1dynamicEngine.inputStateArr.length; l1Pos++) {
      //   for (int l2Pos = 0; l2Pos < level1dynamicEngine.inputStateArr.length; l2Pos++) {
      //      level1dynamicEngine.inputMetaStatePosToMetaStateArr[l1Pos][l2Pos];
      //   }
      //}

      return level1dynamicEngine;
   }

   public static Engine createLevel1moveEngine() {
      final Engine level1moveEngine = new Engine(2, 8);

      // 0    0   0   =>   0
      level1moveEngine.setState( 0, new State(2, nulState, nulState), 0);
      // stay:
      // 1    0   1   =>   1
      level1moveEngine.setState( 1, new State(2, nulState, posState), 1);
      // 2    1   0   =>   2
      level1moveEngine.setState( 2, new State(2, posState, nulState), 2);
      // left:
      // 3    0   1   =>   4
      level1moveEngine.setState( 3, new State(2, nulState, posState), 4);
      // 4    1   0   =>   4
      level1moveEngine.setState( 4, new State(2, posState, nulState), 4);
      // right:
      // 5    1   0   =>   6
      level1moveEngine.setState( 5, new State(2, posState, nulState), 6);
      // 6    0   1   =>   6
      level1moveEngine.setState( 6, new State(2, nulState, posState), 6);

      // 7    1   1   =>   7
      level1moveEngine.setState( 7, new State(2, posState, posState), 7);

      initMetaStateArr(level1moveEngine);
      //initOutputMetaStatePos(level1dynamicEngine);

      final Engine e = level1moveEngine;

      //0+x:
      // 0  =>  0
      //    0    0   0       =>   0    0   0
      //    0        0   0   =>   0        0   0
      level1moveEngine.metaStateArr[metaPos(e, 0, 0)] = new MetaState(metaPos(e, 0, 0), 0, 0);

      // 0,5  =>  0,5
      //    5    1   0       =>   5    1   0
      //    0        0   0   =>   0        0   0
      level1moveEngine.metaStateArr[metaPos(e, 0, 5)] = new MetaState(metaPos(e, 0, 5), 0, 5);
      // 6,0  =>  6,0
      //    0    0   0       =>   0    0   0
      //    6        0   1   =>   6        0   1
      level1moveEngine.metaStateArr[metaPos(e, 6, 0)] = new MetaState(metaPos(e, 6, 0), 6, 0);
      // 5,0  =>  5,0
      //    0    0   0       =>   6    0   1
      //    5        1   0   =>   0        0   0
      level1moveEngine.metaStateArr[metaPos(e, 5, 0)] = new MetaState(metaPos(e, 5, 0), 5, 0);

      // 0,6  =>  5,0
      //    6    0   1       =>   0    0   0
      //    0        0   0   =>   5        1   0
      level1moveEngine.metaStateArr[metaPos(e, 0, 6)] = new MetaState(metaPos(e, 5, 0), 0, 6);

      return level1moveEngine;
   }

   public static int metaPos(final Engine engine, final int ... inputMetaStatePosArr) {
      int metaStatePos = 0;
      for (int metaPos = 0; metaPos < inputMetaStatePosArr.length; metaPos++) {
         metaStatePos += inputMetaStatePosArr[metaPos] * Math.pow(engine.inputStateArr.length, metaPos);
      }
      return metaStatePos;
   }

   public static Engine createLevel2staticEngine() {
      final Engine level2staticEngine = new Engine(3);

      //    0   0   0   =>  0   0   0     0   0   0
      final State nul_nul_nul_2State = new State(3, nulState, nulState, nulState);
      level2staticEngine.setState(0, nul_nul_nul_2State, 0);
      //    0   0   1   =>  0   0   1     0   1   0
      level2staticEngine.setState(1, new State(3, nulState, nulState, posState), 1);
      //    0   0  -1  =>   0   0  -1     0  -1   0
      level2staticEngine.setState(2, new State(3, nulState, nulState, negState), 2);
      //    0   1   0   =>  0   1   0     0   1   0
      level2staticEngine.setState(3, new State(3, nulState, posState, nulState), 3);
      //    0   1   1   =>  0   1   1     1   1   0
      level2staticEngine.setState(4, new State(3, nulState, posState, posState), 4);
      //    0   1  -1  =>   0   0   0     0   0   0
      level2staticEngine.setState(5, new State(3, nulState, posState, negState), 0);
      //    0  -1   0   =>  0  -1   0     0  -1   0
      level2staticEngine.setState(6, new State(3, nulState, negState, nulState), 6);
      //    0  -1   1   =>  0   0   0     0   0   0
      level2staticEngine.setState(7, new State(3, nulState, negState, nulState), 7);
      //    0  -1  -1  =>   0  -1  -1    -1  -1   0
      level2staticEngine.setState(8, new State(3, nulState, negState, negState), 8);
      //
      //    1   0   0   =>  1   0   0     0   1   0
      level2staticEngine.setState(9, new State(3, posState, nulState, nulState), 9);
      //    1   0   1   =>  1   0   1     1   0   1
      level2staticEngine.setState(10, new State(3, posState, nulState, posState), 10);
      //    1   0  -1  =>   1   0  -1     0   0   0
      level2staticEngine.setState(11, new State(3, posState, nulState, negState), 11);
      //    1   1   0   =>  1   1   0     0   1   1
      level2staticEngine.setState(12, new State(3, posState, posState, nulState), 12);
      //    1   1   1   =>  1   1   1     1   1   1
      level2staticEngine.setState(13, new State(3, posState, posState, posState), 13);
      //    1   1  -1  =>   1   0   0     1   0   0
      level2staticEngine.setState(14, new State(3, posState, posState, negState), 9);
      //    1  -1   0   =>  0   0   0     0   0   0
      level2staticEngine.setState(15, new State(3, posState, negState, nulState), 0);
      //    1  -1   1   =>  1  -1   1     0   1   0
      level2staticEngine.setState(16, new State(3, posState, negState, posState), 16);
      //    1  -1  -1  =>   0   0  -1     0   0  -1
      level2staticEngine.setState(17, new State(3, posState, negState, negState), 2);
      //
      //   -1   0   0   => -1   0   0     0  -1   0
      level2staticEngine.setState(18, new State(3, negState, nulState, nulState), 18);
      //   -1   0   1   => -1   0   1     0   0   0
      level2staticEngine.setState(19, new State(3, negState, nulState, posState), 19);
      //   -1   0  -1  =>  -1   0  -1    -1   0  -1
      level2staticEngine.setState(20, new State(3, negState, nulState, negState), 20);
      //   -1   1   0   =>  0   0   0     0   0   0
      level2staticEngine.setState(21, new State(3, negState, posState, nulState), 0);
      //   -1   1   1   =>  0   0   1     0   0   1
      level2staticEngine.setState(22, new State(3, negState, posState, posState), 1);
      //   -1   1  -1  =>  -1   1  -1    -1   1  -1
      level2staticEngine.setState(23, new State(3, negState, posState, negState), 23);
      //   -1  -1   0   => -1  -1   0     0  -1  -1
      level2staticEngine.setState(24, new State(3, negState, negState, nulState), 24);
      //   -1  -1   1   => -1   0   0    -1   0   0
      level2staticEngine.setState(25, new State(3, negState, negState, posState), 18);
      //   -1  -1  -1  =>  -1  -1  -1    -1  -1  -1
      level2staticEngine.setState(26, new State(3, negState, negState, negState), 26);

      return level2staticEngine;
   }

   public static Engine createLevel2dynamicEngine() {
      final Engine level2dynamicEngine = new Engine(3, 33);

      level2dynamicEngine.setState( 0, new State(3, nulState, nulState, nulState), 0);
      level2dynamicEngine.setState( 1, new State(3, nulState, nulState, posState), 2);
      level2dynamicEngine.setState( 2, new State(3, nulState, posState, nulState), 3);
      level2dynamicEngine.setState( 3, new State(3, posState, nulState, nulState), 4);
      level2dynamicEngine.setState( 4, new State(3, nulState, posState, nulState), 1);

      level2dynamicEngine.setState( 5, new State(3, nulState, nulState, negState), 6);
      level2dynamicEngine.setState( 6, new State(3, nulState, negState, nulState), 7);
      level2dynamicEngine.setState( 7, new State(3, negState, nulState, nulState), 8);
      level2dynamicEngine.setState( 8, new State(3, nulState, negState, nulState), 5);

      level2dynamicEngine.setState( 9, new State(3, nulState, posState, posState), 10);
      level2dynamicEngine.setState(10, new State(3, posState, nulState, posState), 11);
      level2dynamicEngine.setState(11, new State(3, posState, posState, nulState), 12);
      level2dynamicEngine.setState(12, new State(3, posState, nulState, posState), 9);

      level2dynamicEngine.setState(13, new State(3, nulState, negState, negState), 14);
      level2dynamicEngine.setState(14, new State(3, negState, nulState, negState), 15);
      level2dynamicEngine.setState(15, new State(3, negState, negState, nulState), 16);
      level2dynamicEngine.setState(16, new State(3, negState, nulState, negState), 13);

      level2dynamicEngine.setState(17, new State(3, posState, posState, posState), 17);

      level2dynamicEngine.setState(18, new State(3, nulState, posState, negState), 0);
      level2dynamicEngine.setState(19, new State(3, nulState, negState, posState), 0);
      level2dynamicEngine.setState(20, new State(3, nulState, negState, nulState), 0);
      level2dynamicEngine.setState(21, new State(3, negState, posState, nulState), 0);

      level2dynamicEngine.setState(22, new State(3, posState, posState, negState), 3);
      level2dynamicEngine.setState(23, new State(3, negState, posState, posState), 1);
      level2dynamicEngine.setState(24, new State(3, posState, negState, negState), 5);
      level2dynamicEngine.setState(25, new State(3, negState, negState, posState), 7);

      level2dynamicEngine.setState(26, new State(3, posState, nulState, negState), 26);
      level2dynamicEngine.setState(27, new State(3, negState, nulState, posState), 27);
      level2dynamicEngine.setState(28, new State(3, posState, negState, posState), 28);
      level2dynamicEngine.setState(29, new State(3, negState, nulState, negState), 29);
      level2dynamicEngine.setState(30, new State(3, negState, posState, negState), 30);
      level2dynamicEngine.setState(31, new State(3, negState, negState, negState), 31);

      // Meta:
      level2dynamicEngine.setState(32, new State(3, posState, negState, nulState), 0);

      initMetaStateArr(level2dynamicEngine);
      initOutputMetaStatePos(level2dynamicEngine);

      return level2dynamicEngine;
   }

   public static Engine createLevel3staticEngine() {
      final State nul_nul_nul_nul_3State = new State(4);
      nul_nul_nul_nul_3State.inputStateArr[0] = nulState;
      nul_nul_nul_nul_3State.inputStateArr[1] = nulState;
      nul_nul_nul_nul_3State.inputStateArr[2] = nulState;
      nul_nul_nul_nul_3State.inputStateArr[3] = nulState;

      final Engine level4staticEngine = new Engine(4);

      level4staticEngine.setState(0, nul_nul_nul_nul_3State, 0);

      return level4staticEngine;
   }

   public static Engine createAllPossibleEngines(final int cellSize) {
      final Engine engine = new Engine(cellSize, 9);

      engine.setState(31, new State(cellSize, negState, negState, negState), 31);

      return engine;
   }

   public static void initMetaStateArr(final Engine engine) {
      engine.metaStateArr = new MetaState[(int)Math.pow(engine.inputStateArr.length, engine.cellSize)];
      engine.inputMetaStatePosToMetaStateArr = new MetaState[(int)Math.pow(engine.inputStateArr.length, engine.cellSize)];

      int inputMetaStatePos = 0;
      final int[] inputMetaStatePosArr = new int[engine.cellSize];
      initMetaStatePos(engine, inputMetaStatePos, inputMetaStatePosArr, engine.cellSize - 1);
   }

   private static void initMetaStatePos(final Engine engine, final int inputMetaStatePos, final int[] inputMetaStatePosArr, final int metaPos) {
      final int arrPos = metaPos;
      for (int l2Pos = 0; l2Pos < engine.inputStateArr.length; l2Pos++) {
         inputMetaStatePosArr[arrPos] = l2Pos;

         if (metaPos > 0) {
            //final int p = inputMetaStatePos + engine.inputStateArr.length * l2Pos;
            final int p = inputMetaStatePos + l2Pos * (int)Math.pow(engine.inputStateArr.length, metaPos);
            initMetaStatePos(engine, p, inputMetaStatePosArr, metaPos - 1);
         } else {
            final int pos = inputMetaStatePos + l2Pos;
            final MetaState metaState = new MetaState(-1, inputMetaStatePosArr.clone());
            engine.metaStateArr[pos] = metaState;
            //final MetaState metaState = engine.metaStateArr[inputMetaStatePos + l2Pos];
            engine.inputMetaStatePosToMetaStateArr[pos] = metaState;
         }
      }
      inputMetaStatePosArr[arrPos] = 0;
   }

   public static void initOutputMetaStatePos(final Engine engine) {
      for (int msPos = 0; msPos < engine.metaStateArr.length; msPos++) {
         final MetaState metaState = engine.metaStateArr[msPos];
         initInputMetaState(engine, metaState);
      }
      for (int msPos = 0; msPos < engine.metaStateArr.length; msPos++) {
         final MetaState metaState = engine.metaStateArr[msPos];
         initOutputMetaState(engine, metaState);
      }
   }

   public static void initInputMetaState(final Engine engine, final MetaState searchedMetaState) {
      final State[] engineInputStateArr = engine.inputStateArr;
      final LarrayInt engineOutputStatePosArr = engine.outputStatePosArr;
      final MetaState[] engineMetaStateArr = engine.metaStateArr;

      // searchedMetaState:
      //       2  =>  9
      //           2        1   0   =>   0        0   0
      //           0    0   0       =>   1    0   1
      //                    ^== searchedMetaStateInputStateArr

      final State searchedMetaStateInputStateArr[] = new State[searchedMetaState.inputMetaStatePosArr.length];

      for (int pos = 0; pos < searchedMetaState.inputMetaStatePosArr.length; pos++) {
         searchedMetaStateInputStateArr[pos] = engineInputStateArr[searchedMetaState.inputMetaStatePosArr[pos]].inputStateArr[pos];
      }

      final int searchedMetaStateInputStatePos;
      final int searchedMetaStateOutputStatePos;
      final State searchedMetaStateOutputState;
      {
         searchedMetaStateInputStatePos = searchStatePos(engine, searchedMetaStateInputStateArr);
         if (searchedMetaStateInputStatePos == -1) {
            throw new RuntimeException(String.format("For Meta-State %s no input state found.", convertToDebugString(searchedMetaStateInputStateArr)));
         }
         searchedMetaStateOutputStatePos = engineOutputStatePosArr.get(searchedMetaStateInputStatePos);
         searchedMetaStateOutputState = engineInputStateArr[searchedMetaStateOutputStatePos];
      }

      //searchedMetaState.outputMetaStatePos = -1;

      for (int msPos = 0; msPos < engineMetaStateArr.length; msPos++) {
         final MetaState metaState = engineMetaStateArr[msPos];
         final boolean inputMetaStateFound = isInputMetaStateFound(engineInputStateArr, metaState, searchedMetaState, searchedMetaStateOutputState);
         if (inputMetaStateFound) {
            //searchedMetaState.outputMetaStatePos = msPos;
            searchedMetaState.metaStateInputStatePos = searchedMetaStateInputStatePos;
            //searchedMetaState.metaStateOutputStatePos = searchedMetaStateOutputStatePos;
            break;
         }
      }
   }

   private static boolean isInputMetaStateFound(final State[] engineInputStateArr,
                                                final MetaState metaState, final MetaState searchedMetaState,
                                                final State searchedMetaStateOutputState) {
      boolean inputMetaStateFound = true;
      for (int inputMetaStatePos = 0; inputMetaStatePos < metaState.inputMetaStatePosArr.length; inputMetaStatePos++) {
         final State metaStateInputState = engineInputStateArr[metaState.inputMetaStatePosArr[inputMetaStatePos]];
         boolean inputStateFound = true;
         for (int inputStatePos = 0; inputStatePos < metaStateInputState.inputStateArr.length; inputStatePos++) {
            final State inputState = metaStateInputState.inputStateArr[inputStatePos];
            final State searchedInputState;
            if (inputMetaStatePos == inputStatePos) {
               searchedInputState = searchedMetaStateOutputState.inputStateArr[inputMetaStatePos];
            } else {
               final State searchedMetaStateInputState = engineInputStateArr[searchedMetaState.inputMetaStatePosArr[inputMetaStatePos]];
               searchedInputState = searchedMetaStateInputState.inputStateArr[inputStatePos];
            }
            if (inputState != searchedInputState) {
               inputStateFound = false;
               break;
            }
         }
         if (inputStateFound == false) {
            inputMetaStateFound = false;
            break;
         }
      }
      return inputMetaStateFound;
   }

   public static void initOutputMetaState(final Engine engine, final MetaState searchedMetaState) {
      final State[] engineInputStateArr = engine.inputStateArr;
      final LarrayInt engineOutputStatePosArr = engine.outputStatePosArr;
      final MetaState[] engineMetaStateArr = engine.metaStateArr;

      // searchedMetaState:
      //       2  =>  9
      //           2        1   0   =>   0        0   0
      //           0    0   0       =>   1    0   1
      //                    ^== searchedMetaStateInputStateArr

      final State searchedMetaStateInputStateArr[] = new State[searchedMetaState.inputMetaStatePosArr.length];

      for (int pos = 0; pos < searchedMetaState.inputMetaStatePosArr.length; pos++) {
         searchedMetaStateInputStateArr[pos] = engineInputStateArr[searchedMetaState.inputMetaStatePosArr[pos]].inputStateArr[pos];
      }

      final int searchedMetaStateInputStatePos = searchedMetaState.metaStateInputStatePos;
      //final int searchedMetaStateOutputStatePos = searchedMetaState.metaStateOutputStatePos;
      final int searchedMetaStateOutputStatePos = engineOutputStatePosArr.get(searchedMetaStateInputStatePos);
      final State searchedMetaStateOutputState = engineInputStateArr[searchedMetaStateOutputStatePos];
      final int searchedMetaStateOutputOutputStatePos = engineOutputStatePosArr.get(searchedMetaStateOutputStatePos);

      //searchedMetaState.outputMetaStatePos = -1;

      for (int msPos = 0; msPos < engineMetaStateArr.length; msPos++) {
         final MetaState metaState = engineMetaStateArr[msPos];

         final boolean inputMetaStateFound = isInputMetaStateFound(engineInputStateArr, metaState, searchedMetaState, searchedMetaStateOutputState);
         if (inputMetaStateFound) {
            if ((metaState.metaStateInputStatePos == searchedMetaStateOutputStatePos))// &&
               //(metaState.metaStateOutputStatePos == searchedMetaStateOutputOutputStatePos))
            {
               searchedMetaState.outputMetaStatePos = msPos;
               //searchedMetaState.metaStateInputStatePos = searchedMetaStateInputStatePos;
               //searchedMetaState.metaStateOutputStatePos = searchedMetaStateOutputStatePos;
               break;
            }
         }
      }
   }

   private static int searchStatePos(final Engine engine, final State searchedStateArr[]) {
      final State[] engineInputStateArr = engine.inputStateArr;

      int retInputStatePos = -1;

      for (int inputStatePos = 0; inputStatePos < engineInputStateArr.length; inputStatePos++) {
         final State inputState = engineInputStateArr[inputStatePos];
         if (compareInputStateArr(inputState, searchedStateArr)) {
            retInputStatePos = inputStatePos;
            // Gibt  es für diese Kombination noch keinen MetaState?
            if (checkMetaStateExistForMetaStateInputAndOutputState(engine, inputStatePos) == false) {
               break;
            }
         }
      }
      return retInputStatePos;
   }

   private static boolean compareInputStateArr(final State inputState, final State[] searchedStateArr) {
      boolean found = true;
      for (int statePos = 0; statePos < inputState.inputStateArr.length; statePos++) {
         if (inputState.inputStateArr[statePos] != searchedStateArr[statePos]) {
            found = false;
            break;
         }
      }
      return found;
   }

   private static boolean checkMetaStateExistForMetaStateInputAndOutputState(final Engine engine, final int searchedMetaStateInputStatePos) {
      final State[] engineInputStateArr = engine.inputStateArr;
      final LarrayInt engineOutputStatePosArr = engine.outputStatePosArr;
      final MetaState[] engineMetaStateArr = engine.metaStateArr;

      final int searchedMetaStateOutputStatePos = engineOutputStatePosArr.get(searchedMetaStateInputStatePos);
      //final State searchedMetaStateOutputState = engineInputStateArr[searchedMetaStateOutputStatePos];

      boolean found = false;

      for (int msPos = 0; msPos < engine.metaStateArr.length; msPos++) {
         final MetaState metaState = engine.metaStateArr[msPos];

         if ((metaState.metaStateInputStatePos == searchedMetaStateInputStatePos))// &&
             //(metaState.metaStateOutputStatePos == searchedMetaStateOutputStatePos))
         {
            found = true;
            break;
         }
      }
      return found;
   }

   public static int searchStatePos(final State[] engineInputStateArr, final State searchedState) {
      for (int inputStatePos = 0; inputStatePos < engineInputStateArr.length; inputStatePos++) {
         final State inputState = engineInputStateArr[inputStatePos];
         boolean found = true;
         for (int statePos = 0; statePos < searchedState.inputStateArr.length; statePos++) {
            if (inputState.inputStateArr[statePos] != searchedState.inputStateArr[statePos]) {
               found = false;
               break;
            }
         }
         if (found) {
            return inputStatePos;
         }
      }
      return -1;
   }
}
