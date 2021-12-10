package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.engine.CreateEngineService.initInputMetaState;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.MetaState;
import de.schmiereck.col.model.State;
import de.schmiereck.col.services.engine.CreateEngineService;

import org.junit.jupiter.api.Test;

public class Test_CreateEngineService_WHEN_initOutputMetaState_is_called {

   @Test
   void initOutputMetaState() {
      // Arrange
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

      CreateEngineService.initMetaStateArr(level1dynamicEngine);

      // Act
      for (int msPos = 0; msPos < level1dynamicEngine.metaStateArr.length; msPos++) {
         final MetaState metaState = level1dynamicEngine.metaStateArr[msPos];
         initInputMetaState(level1dynamicEngine, metaState);
      }
      for (int msPos = 0; msPos < level1dynamicEngine.metaStateArr.length; msPos++) {
         final MetaState metaState = level1dynamicEngine.metaStateArr[msPos];
         CreateEngineService.initOutputMetaState(level1dynamicEngine, metaState);
      }

      // Assert

      // 0:0,0
      // 1:0,1
      // 2:1,0
      final int expected0InputMetaStatePosArr[] = { //2*2*2=4
           // 0  1  2  3  4  5  6  7  8    9 10 11 12 13 14 15 16 17   18 19 20 21 22 23 24 25 26 ....
              0, 1, 2, 3, 4, 5, 6, 7, 8,   0, 1, 2, 3, 4, 5, 6, 7, 8,   0, 1, 2, 3, 4, 5, 6, 7, 8,
      };
      final int expected1InputMetaStatePosArr[] = {
           // 0  1  2  3  4  5  6  7  8    9 10 11 12 13 14 15 16 17   18 19 20 21 22 23 24 25 26 ....
              0, 0, 0, 0, 0, 0, 0, 0, 0,   1, 1, 1, 1, 1, 1, 1, 1, 1,   2, 2, 2, 2, 2, 2, 2, 2, 2,
      };

      // 0  =>  0
      //    0    0   0       =>   0    0   0
      //    0        0   0   =>   0        0   0
      //             ^ 0:0,0               ^ 0:0,0
      // 1  =>  1
      //    0    0   0       =>   0    0   0
      //    1        0   1   =>   1        0   1
      //             ^ 0:0,0               ^ 0:0,0
      // 2  =>  9
      //    0    0   0       =>   1    0   1
      //    2        1   0   =>   0        0   0
      //             ^ 2:1,0               ^ 1:0,1
      // 3  =>  ?
      //    0    0   0       =>
      //    3        0  -1   =>
      //             ^ 0:0,0                ^ ?:0,0
      // 9  =>  ?
      //    1    0   1       =>
      //    0        0   0   =>
      //             ^ ?:0,1                 ^ ?:0,0
      final int expectedMetaStateInputStatePosArr[] = {
           // 0  1  2  3  4  5  6  7  8    9 10 11 12 13 14 15 16 17   18 19 20 21 22 23 24 25 26 ....
              0, 0, 2, 0, 4, 2, 2, 4, 4,   1, 1, 5, 1, 7, 5, 5, 7, 7,
      };
      final int expectedOutputMetaStatePosArr[] = {
           // 0  1  2  3  4  5  6  7  8    9 10 11 12 13 14 15 16 17   18 19 20 21 22 23 24 25 26 ....
              0, 1, 9, 3,27,10,12,28,30,   2, 5,11, 6, 0,14,15, 1, 3
      };
      //for (int msPos = 0; msPos < level1dynamicEngine.metaStateArr.length; msPos++) {
      for (int msPos = 0; msPos < expectedOutputMetaStatePosArr.length; msPos++) {
         assertEquals(expected0InputMetaStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[0], "inputMetaStatePosArr Pos0 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[1], "inputMetaStatePosArr Pos1 " + msPos + " should be other.");
         assertEquals(expectedMetaStateInputStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].metaStateInputStatePos, "metaStateInputStatePos Pos " + msPos + " should be other.");
         assertEquals(expectedOutputMetaStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].outputMetaStatePos, "outputMetaStatePos Pos " + msPos + " should be other.");
      }
   }

   @Test
   void GIVEN_level2dynamicEngine_with_2_states_THEN_metaStateArr_and_inputMetaStatePosToMetaStateArr_initialized() {
      // Arrange
      final Engine level2dynamicEngine = new Engine(3, 2);

      level2dynamicEngine.setState( 0, new State(3, posState, nulState, nulState), 1);
      level2dynamicEngine.setState( 1, new State(3, posState, nulState, nulState), 0);

      // Act
      CreateEngineService.initMetaStateArr(level2dynamicEngine);
      CreateEngineService.initOutputMetaStatePos(level2dynamicEngine);

      // Assert
      final int expected0InputMetaStatePosArr[] = { //2*2*2=4
           // 0  1  2  3  4  5  6  7    8  9 10 11 12 13 14 15
              0, 1, 0, 1, 0, 1, 0, 1,
      };
      final int expected1InputMetaStatePosArr[] = {
           // 0  1  2  3  4  5  6  7    8  9 10 11 12 13 14 15
              0, 0, 1, 1, 0, 0, 1, 1,
      };
      final int expected2InputMetaStatePosArr[] = {
           // 0  1  2  3  4  5  6  7    8  9 10 11 12 13 14 15
              0, 0, 0, 0, 1, 1, 1, 1,
      };
      final int expectedOutputMetaStatePosArr[] = {
           // 0  1  2  3  4  5  6  7    8  9 10 11 12 13 14 15
              1, 0, 0, 0, 0, 0, 0, 0,
      };
      //for (int msPos = 0; msPos < level1dynamicEngine.metaStateArr.length; msPos++) {
      for (int msPos = 0; msPos < expected0InputMetaStatePosArr.length; msPos++) {
         assertNotNull(level2dynamicEngine.metaStateArr[msPos], "metaStateArr Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[0], "meta Pos0 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[1], "meta Pos1 " + msPos + " should be other.");
         assertEquals(expected2InputMetaStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[2], "meta Pos2 " + msPos + " should be other.");
         assertEquals(expectedOutputMetaStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].outputMetaStatePos, "outputMetaStatePos Pos " + msPos + " should be other.");

         assertNotNull(level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos], "inputMetaStatePosToMetaStateArr Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[0], "inputMeta Pos0 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[1], "inputMeta Pos1 " + msPos + " should be other.");
         assertEquals(expected2InputMetaStatePosArr[msPos], level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[2], "inputMeta Pos2 " + msPos + " should be other.");
      }
   }

   @Test
   void GIVEN_level2dynamicEngine_with_3_states_THEN_metaStateArr_and_inputMetaStatePosToMetaStateArr_initialized() {
      // Arrange
      final Engine level2dynamicEngine = new Engine(3, 3);

      level2dynamicEngine.setState( 0, new State(3, nulState, nulState, nulState), 1);
      level2dynamicEngine.setState( 1, new State(3, nulState, nulState, nulState), 2);
      level2dynamicEngine.setState( 2, new State(3, nulState, nulState, nulState), 0);

      // Act
      CreateEngineService.initMetaStateArr(level2dynamicEngine);
      CreateEngineService.initOutputMetaStatePos(level2dynamicEngine);

      // Assert
      final int expected0InputMetaStatePosArr[] = { //3*3*3=
           // 0  1  2    3  4  5    6  7  8    9 10 11   12 13 14   15
              0, 1, 2,   0, 1, 2,   0, 1, 2,   0, 1, 2,   0, 1, 2,   0, 1, 2,   0, 1, 2,   0, 1, 2,   0, 1, 2,
      };
      final int expected1InputMetaStatePosArr[] = {
           // 0  1  2    3  4  5    6  7  8    9 10 11   12 13 14   15
              0, 0, 0,   1, 1, 1,   2, 2, 2,   0, 0, 0,   1, 1, 1,   2, 2, 2,   0, 0, 0,   1, 1, 1,   2, 2, 2,
      };
      final int expected2InputMetaStatePosArr[] = {
           // 0  1  2    3  4  5    6  7  8    9 10 11   12 13 14   15
              0, 0, 0,   0, 0, 0,   0, 0, 0,   1, 1, 1,   1, 1, 1,   1, 1, 1,   2, 2, 2,   2, 2, 2,   2, 2, 2,
      };
      final int expectedMetaStateInputStatePosArr[] = {
           // 0  1  2    3  4  5    6  7  8    9 10 11   12 13 14   15
              0, 1, 2,   2, 2, 2,   2, 2, 2,   2, 2, 2,   2, 2, 2,   2, 2, 2,   2, 2, 2,   2, 2, 2,   2, 2, 2,
      };
      final int expectedOutputMetaStatePosArr[] = {
           // 0  1  2    3  4  5    6  7  8    9 10 11   12 13 14   15
              1, 2, 0,   0, 0, 0,   0, 0, 0,   0, 0, 0,   0, 0, 0,   0, 0, 0,   0, 0, 0,   0, 0, 0,   0, 0, 0,
      };
      //for (int msPos = 0; msPos < level1dynamicEngine.metaStateArr.length; msPos++) {
      for (int msPos = 0; msPos < expected0InputMetaStatePosArr.length; msPos++) {
         assertNotNull(level2dynamicEngine.metaStateArr[msPos], "metaStateArr Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[0], "meta Pos0 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[1], "meta Pos1 " + msPos + " should be other.");
         assertEquals(expected2InputMetaStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[2], "meta Pos2 " + msPos + " should be other.");
         assertEquals(expectedMetaStateInputStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].metaStateInputStatePos, "metaStateInputStatePos Pos " + msPos + " should be other.");
         assertEquals(expectedOutputMetaStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].outputMetaStatePos, "outputMetaStatePos Pos " + msPos + " should be other.");

         assertNotNull(level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos], "inputMetaStatePosToMetaStateArr Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[0], "inputMeta Pos0 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[1], "inputMeta Pos1 " + msPos + " should be other.");
         assertEquals(expected2InputMetaStatePosArr[msPos], level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[2], "inputMeta Pos2 " + msPos + " should be other.");
      }
   }
}
