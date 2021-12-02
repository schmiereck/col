package de.schmiereck.col.services.engine;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.services.EngineService.calcMetaStateSize;
import static de.schmiereck.col.services.StateUtils.convertToDebugString;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.MetaState;
import de.schmiereck.col.model.State;
import de.schmiereck.larray.LarrayInt;

/**
 * Wordings:
 * static/ stay:  stat: Just Stay-States, no Move-States (left, right).
 * move:          move: Stay- and Move-States (left, right).
 * dynamic:       dyna: Using oscillating moves (left to right and back) for stay.
 * spin:          spin: Using oscillating states (A to B and back) for stay.
 *
 * Level 0:       lev0: Size 1
 * Level 1:       lev1: Size 2
 * Level 2:       lev2: Size 3
 * Level 3:       lev3: Size 4
 */
public class CreateEngineService {

   public static void writeMetaState(final Engine engine,
                                     final int input0MetaStatePos, final int input1MetaStatePos,
                                     final int output0MetaStatePos, final int output1MetaStatePos) {
      engine.metaStateArr[metaPos(engine, input0MetaStatePos, input1MetaStatePos)].outputMetaStatePos = metaPos(engine, output0MetaStatePos, output1MetaStatePos);
   }

   public static void writeMetaState(final Engine engine,
                                     final int input0MetaStatePos, final int input1MetaStatePos, final int input2MetaStatePos,
                                     final int output0MetaStatePos, final int output1MetaStatePos, final int output2MetaStatePos) {
      engine.metaStateArr[metaPos(engine, input0MetaStatePos, input1MetaStatePos, input2MetaStatePos)].outputMetaStatePos = metaPos(engine, output0MetaStatePos, output1MetaStatePos, output2MetaStatePos);
   }

   public static void writeMetaState(final Engine engine,
                                     final int input0MetaStatePos, final int input1MetaStatePos, final int[] input2MetaStatePosArr,
                                     final int output0MetaStatePos, final int output1MetaStatePos) {
      writeMetaState(engine,
                     input0MetaStatePos, input1MetaStatePos, input2MetaStatePosArr,
                     output0MetaStatePos, output1MetaStatePos, false);
   }

   public static void writeMetaState(final Engine engine,
                                     final int input0MetaStatePos, final int input1MetaStatePos, final int[] input2MetaStatePosArr,
                                     final int output0MetaStatePos, final int output1MetaStatePos, final boolean levelDown) {
      for (final int input2MetaStatePos: input2MetaStatePosArr) {
         final MetaState metaState = engine.metaStateArr[metaPos(engine, input0MetaStatePos, input1MetaStatePos, input2MetaStatePos)];
         metaState.outputMetaStatePos = metaPos(engine, output0MetaStatePos, output1MetaStatePos, input2MetaStatePos);
         metaState.levelDown = levelDown;
      }
   }

   public static void writeMetaState(final Engine engine,
                                     final int input0MetaStatePos, final int input1MetaStatePos, final int[] input2MetaStatePosArr,
                                     final int output0MetaStatePos, final int output1MetaStatePos, final int[] output2MetaStatePosArr) {
      for (final int input2MetaStatePos: input2MetaStatePosArr) {
         for (final int output2MetaStatePos: output2MetaStatePosArr) {
            engine.metaStateArr[metaPos(engine, input0MetaStatePos, input1MetaStatePos, input2MetaStatePos)].outputMetaStatePos = metaPos(engine, output0MetaStatePos, output1MetaStatePos, output2MetaStatePos);
         }
      }
   }

   public static int metaPos(final Engine engine, final int ... inputMetaStatePosArr) {
      int metaStatePos = 0;
      for (int metaPos = 0; metaPos < inputMetaStatePosArr.length; metaPos++) {
         metaStatePos += inputMetaStatePosArr[metaPos] * Math.pow(engine.inputStateArr.length, metaPos);
      }
      return metaStatePos;
   }

   public static Engine createAllPossibleEngines(final int cellSize) {
      final Engine engine = new Engine(cellSize, 9);

      engine.setState(31, new State(cellSize, negState, negState, negState), 31);

      return engine;
   }

   public static void initMetaStateArr(final Engine engine) {
      final int size = calcMetaStateSize(engine);
      engine.metaStateArr = new MetaState[(int)Math.pow(engine.inputStateArr.length, size)];
      engine.inputMetaStatePosToMetaStateArr = new MetaState[(int)Math.pow(engine.inputStateArr.length, size)];

      int inputMetaStatePos = 0;
      final int[] inputMetaStatePosArr = new int[size];
      initMetaStatePos(engine, inputMetaStatePos, inputMetaStatePosArr, size - 1);
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
      initInputMetaStates(engine);
      initOutputMetaStates(engine);
   }

   public static void initInputMetaStates(final Engine engine) {
      for (int msPos = 0; msPos < engine.metaStateArr.length; msPos++) {
         final MetaState metaState = engine.metaStateArr[msPos];
         initInputMetaState(engine, metaState);
      }
   }

   private static void initOutputMetaStates(final Engine engine) {
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
      // 2  =>  9
      //    2        1   0   =>   0        0   0
      //    0    0   0       =>   1    0   1
      //             ^ 1:0,1               ^ 2:1,0
      //       2  =>  9
      //           2        1   0   =>   0        0   0
      //           0    0   0       =>   1    0   1
      //                    ^== searchedMetaStateInputStateArr
      final int metaStateSize = searchedMetaState.inputMetaStatePosArr.length;
      final State searchedMetaStateInputStateArr[] = new State[metaStateSize];

      for (int pos = 0; pos < metaStateSize; pos++) {
         final int inputMetaStatePos = searchedMetaState.inputMetaStatePosArr[pos];
         final State inputState = engineInputStateArr[inputMetaStatePos];
         searchedMetaStateInputStateArr[pos] = inputState.inputStateArr[pos];
      }

      final int searchedMetaStateInputStatePos;
      final State searchedMetaStateInputState;
      final int searchedMetaStateOutputStatePos;
      final State searchedMetaStateOutputState;
      {
         searchedMetaStateInputStatePos = searchStatePos(engine, searchedMetaStateInputStateArr);
         if (searchedMetaStateInputStatePos == -1) {
            throw new RuntimeException(String.format("For Meta-State %s no input state found.", convertToDebugString(searchedMetaStateInputStateArr)));
         }
         searchedMetaStateInputState = engineInputStateArr[searchedMetaStateInputStatePos];
         searchedMetaStateOutputStatePos = engineOutputStatePosArr.get(searchedMetaStateInputStatePos);
         searchedMetaStateOutputState = engineInputStateArr[searchedMetaStateOutputStatePos];
      }

      //searchedMetaState.outputMetaStatePos = -1;

      for (int msPos = 0; msPos < engineMetaStateArr.length; msPos++) {
         final MetaState metaState = engineMetaStateArr[msPos];
         final boolean inputMetaStateFound = isInputMetaStateFound(engineInputStateArr, metaState, searchedMetaState, searchedMetaStateInputState);
         if (inputMetaStateFound) {
            //searchedMetaState.outputMetaStatePos = msPos;
            searchedMetaState.metaStateInputStatePos = searchedMetaStateInputStatePos;
            //searchedMetaState.metaStateOutputStatePos = searchedMetaStateOutputStatePos;
            break;
         }
      }
      if (searchedMetaState.metaStateInputStatePos == -1) {
         throw new RuntimeException(String.format("E01: For Meta-State %s with input state %d:%s for output state %d:%s no input state found.",
                 convertToDebugString(searchedMetaState),
                 searchedMetaStateInputStatePos, convertToDebugString(searchedMetaStateInputState),
                 searchedMetaStateOutputStatePos, convertToDebugString(searchedMetaStateOutputState)));
      }
   }

   public static void initLevelUpOutputMetaStatePosArr(final Engine engine) {
      for (int msPos = 0; msPos < engine.metaStateArr.length; msPos++) {
         final MetaState metaState = engine.metaStateArr[msPos];
         initLevelUpOutputMetaStatePosArr(engine, metaState);
      }
   }

   public static void initLevelUpOutputMetaStatePosArr(final Engine engine, final MetaState searchedMetaState) {
      // TODO see: de.schmiereck.col.services.UniverseService.calcNewEqualState
   }

   public static boolean
   isInputMetaStateFound(final State[] engineInputStateArr,
                         final MetaState metaState, final MetaState searchedMetaState, final State searchedMetaStateOutputState) {
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
            /*
             if (inputMetaStatePos == inputStatePos) {
               final State expectedInputState = searchedMetaStateOutputState.inputStateArr[inputMetaStatePos];
               if (inputState != expectedInputState) {
                  inputStateFound = false;
                  break;
               }
            } else {
               final State searchedInputMetaState = engineInputStateArr[searchedMetaState.inputMetaStatePosArr[inputMetaStatePos]];
               final State searchedInputState = searchedInputMetaState.inputStateArr[inputStatePos];
               if (inputState != searchedInputState) {
                  inputStateFound = false;
                  break;
               }
            }
              */
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
      final int searchedMetaStateInputStatePos;
      final State searchedMetaStateInputState;
      final int searchedMetaStateOutputStatePos;
      final State searchedMetaStateOutputState;
         {
            searchedMetaStateInputStatePos = searchStatePos2(engine, searchedMetaStateInputStateArr);
            if (searchedMetaStateInputStatePos == -1) {
               throw new RuntimeException(String.format("For Meta-State %s no input state found.", convertToDebugString(searchedMetaStateInputStateArr)));
            }
            searchedMetaStateOutputStatePos = engineOutputStatePosArr.get(searchedMetaStateInputStatePos);
            searchedMetaStateInputState = engineInputStateArr[searchedMetaStateInputStatePos];
            searchedMetaStateOutputState = engineInputStateArr[searchedMetaStateOutputStatePos];
         }

      final int searchedMetaStateInputStatePos2 = searchedMetaState.metaStateInputStatePos;
      //final int searchedMetaStateOutputStatePos = searchedMetaState.metaStateOutputStatePos;
      final int searchedMetaStateOutputStatePos2 = engineOutputStatePosArr.get(searchedMetaStateInputStatePos2);
      final State searchedMetaStateOutputState2 = engineInputStateArr[searchedMetaStateOutputStatePos2];
      final int searchedMetaStateOutputOutputStatePos = engineOutputStatePosArr.get(searchedMetaStateOutputStatePos2);

      //searchedMetaState.outputMetaStatePos = -1;

      for (int msPos = 0; msPos < engineMetaStateArr.length; msPos++) {
         final MetaState metaState = engineMetaStateArr[msPos];

         final boolean inputMetaStateFound = isInputMetaStateFound(engineInputStateArr, metaState, searchedMetaState, searchedMetaStateOutputState);
         if (inputMetaStateFound) {
            if ((metaState.metaStateInputStatePos == searchedMetaStateOutputStatePos2))// &&
            //(metaState.metaStateOutputStatePos == searchedMetaStateOutputOutputStatePos))
            {
               searchedMetaState.outputMetaStatePos = msPos;
               //searchedMetaState.metaStateInputStatePos = searchedMetaStateInputStatePos;
               //searchedMetaState.metaStateOutputStatePos = searchedMetaStateOutputStatePos;
               break;
            }
         }
      }
      if (searchedMetaState.outputMetaStatePos == -1) {
         //throw new RuntimeException(String.format("E02: For Meta-State %s with input state %s no input state found.", convertToDebugString(searchedMetaState), convertToDebugString(searchedMetaStateOutputState)));
         System.out.println(String.format("E02: For Meta-State %s with input state %d:%s for output state %d:%s no input state found.",
                 convertToDebugString(searchedMetaState),
                 searchedMetaStateInputStatePos, convertToDebugString(searchedMetaStateInputState),
                 searchedMetaStateOutputStatePos, convertToDebugString(searchedMetaStateOutputState)));
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
            if (checkMetaStateExistForMetaStateInputState(engine, inputStatePos) == false) {
               break;
            }
         }
      }
      return retInputStatePos;
   }

   private static int searchStatePos2(final Engine engine, final State searchedStateArr[]) {
      final State[] engineInputStateArr = engine.inputStateArr;

      int retInputStatePos = -1;

      for (int inputStatePos = 0; inputStatePos < engineInputStateArr.length; inputStatePos++) {
         final State inputState = engineInputStateArr[inputStatePos];
         if (compareInputStateArr(inputState, searchedStateArr)) {
            retInputStatePos = inputStatePos;
            // Gibt  es für diese Kombination noch keinen MetaState?
            if (checkMetaStateExistForMetaStateInputAndOutputState2(engine, inputStatePos) == false) {
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

   private static boolean checkMetaStateExistForMetaStateInputState(final Engine engine, final int searchedMetaStateInputStatePos) {
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

   private static boolean checkMetaStateExistForMetaStateInputAndOutputState2(final Engine engine, final int searchedMetaStateInputStatePos) {
      final State[] engineInputStateArr = engine.inputStateArr;
      final LarrayInt engineOutputStatePosArr = engine.outputStatePosArr;
      final MetaState[] engineMetaStateArr = engine.metaStateArr;

      final int searchedMetaStateOutputStatePos = engineOutputStatePosArr.get(searchedMetaStateInputStatePos);
      //final State searchedMetaStateOutputState = engineInputStateArr[searchedMetaStateOutputStatePos];

      boolean found = false;

      for (int msPos = 0; msPos < engine.metaStateArr.length; msPos++) {
         final MetaState metaState = engine.metaStateArr[msPos];

         if ((metaState.metaStateInputStatePos == searchedMetaStateInputStatePos) &&
             (metaState.outputMetaStatePos == searchedMetaStateOutputStatePos))
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
