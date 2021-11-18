package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

import org.junit.jupiter.api.Test;

public class Test_CreateEngineService_WHEN_initOutputMetaStatePos_is_called {

   @Test
   void GIVEN_level1dynamicEngine_THEN_metaStateArr_and_inputMetaStatePosToMetaStateArr_initialized() {
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
      CreateEngineService.initOutputMetaStatePos(level1dynamicEngine);

      // Assert
      final int expected0InputMetaStatePosArr[] = {
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
      };
      final int expected1InputMetaStatePosArr[] = {
              0, 0, 0, 0, 0, 0, 0, 0, 0,
              1, 1, 1, 1, 1, 1, 1, 1, 1,
              2, 2, 2, 2, 2, 2, 2, 2, 2,
              3, 3, 3, 3, 3, 3, 3, 3, 3,
              4, 4, 4, 4, 4, 4, 4, 4, 4,
              5, 5, 5, 5, 5, 5, 5, 5, 5,
              6, 6, 6, 6, 6, 6, 6, 6, 6,
              7, 7, 7, 7, 7, 7, 7, 7, 7,
              8, 8, 8, 8, 8, 8, 8, 8, 8,
      };

      final int expectedMetaStateInputStatePosArr[] = {
              0, 0, 2, 0, 4, 2, 2, 4, 4,
              1, 1, 5, 1, 7, 5, 5, 7, 7,
              0, 0, 2, 0, 4, 2, 2, 4, 4,
              3, 3, 6, 3, 8, 6, 6, 8, 8,
              0, 0, 2, 0, 4, 2, 2, 4, 4,
              1, 1, 5, 1, 7, 5, 5, 7, 7,
              3, 3, 6, 3, 8, 6, 6, 8, 8,
              1, 1, 5, 1, 7, 5, 5, 7, 7,
              3, 3, 6, 3, 8, 6, 6, 8, 8,
      };
      /*
      final int expectedMetaStateOutputStatePosArr[] = {
              0, 0, 1, 0, 3, 1, 1, 3, 3,
              2, 2, 5, 2, 0, 5, 5, 0, 0,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
      };
       */
      final int expectedOutputMetaStatePosArr[] = {
           //  0,  1,  2,  3,  4,  5,  6,  7,  8,
               0,  1,  9,  3, 27, 10, 12, 28, 30,
           //  9, 10, 11, 12, 13, 14, 15, 16, 17,
               2,  5, 11,  6,  0, 14, 15,  1,  3,
              18, 19, 45, 21, 54, 46, 48, 55, 57,
               4,  7,  0,  8, 31,  1,  3, 34, 35,
              36, 37, 63, 39, 72, 64, 66, 73, 75,
              20, 23, 47, 24, 18, 50, 51, 19, 21,
              22, 25, 18, 26, 58, 19, 21, 61, 62,
              38, 41, 65, 42, 36, 68, 69, 37, 39,
              40, 43, 36, 44, 76, 37, 39, 79, 80,
              /*
           //  0,  1,  2,  3,  4,  5,  6,  7,  8,
               0,  1,  9,  3, 27, 10, 12, 28, 30,
           //  9, 10, 11, 12, 13, 14, 15, 16, 17,
               2,  5, 11,  6,  0, 14, 15,  1,  3,
              18, 19, 45, 21, 54, 46, 48, 55, 57,
               4,  7,  0,  8, 31,  1,  3, 34, 35,
              36, 37, 63, 39, 72, 64, 66, 73, 75,
              20, 23, 47, 24, 18, 50, 51, 19, 21,
              22, 25, 18, 26, 58, 19, 21, 61, 62,
              38, 41, 65, 42, 36, 68, 69, 37, 39,
              40, 43, 36, 44, 76, 37, 39, 79, 80,
               */
      };
      //for (int msPos = 0; msPos < level1dynamicEngine.metaStateArr.length; msPos++) {
      for (int msPos = 0; msPos < expected0InputMetaStatePosArr.length; msPos++) {
         assertNotNull(level1dynamicEngine.metaStateArr[msPos], "metaStateArr Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[0], "Pos0 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[1], "Pos1 " + msPos + " should be other.");

         assertEquals(expectedMetaStateInputStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].metaStateInputStatePos, "level1 metaStateInputStatePos on pos(" + msPos + ") should be other.");
         //assertEquals(expectedMetaStateOutputStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].metaStateOutputStatePos, "level1 metaStateOutputStatePos on pos(" + msPos + ") should be other.");
         assertEquals(expectedOutputMetaStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].outputMetaStatePos, "level1 outputMetaStatePos on pos(" + msPos + ") should be other.");

         assertNotNull(level1dynamicEngine.inputMetaStatePosToMetaStateArr[msPos], "Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level1dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[0], "Pos1 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level1dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[1], "Pos1 " + msPos + " should be other.");
      }
   }

   @Test
   void GIVEN_level1specialEngine_THEN_metaStateArr_and_inputMetaStatePosToMetaStateArr_initialized() {
      // Arrange
      final Engine level1dynamicEngine = new Engine(2, 2);

      // 0    1   0   =>   1
      level1dynamicEngine.setState( 0, new State(2, posState, nulState), 1);
      // 1    1   0   =>   0
      level1dynamicEngine.setState( 1, new State(2, posState, nulState), 0);

      // 0  =>  0
      //    0        1   0   =>   0        1   0
      //    0    1   0       =>   0    1   0
      // 1  =>  0
      //    1        1   0   =>   1        1   0
      //    0    1   0       =>   0    1   0
      // 2  =>  0
      //    0        1   0   =>   0        1   0
      //    1    1   0       =>   1    1   0
      // 3  =>  0
      //    1        1   0   =>   1        1   0
      //    1    1   0       =>   1    1   0

      CreateEngineService.initMetaStateArr(level1dynamicEngine);

      // Act
      CreateEngineService.initOutputMetaStatePos(level1dynamicEngine);

      // Assert
      final int expected0InputMetaStatePosArr[] = {
              0, 1,   0, 1,
      };
      final int expected1InputMetaStatePosArr[] = {
              0, 0,   1, 1,
      };

      final int expectedMetaStateInputStatePosArr[] = {
              0, 1,   1, 1
      };
      /*
      final int expectedMetaStateOutputStatePosArr[] = {
              1, 0,   0, 0
      };
       */
      final int expectedOutputMetaStatePosArr[] = {
              1, 0,   0, 0
      };
      //for (int msPos = 0; msPos < level1dynamicEngine.metaStateArr.length; msPos++) {
      for (int msPos = 0; msPos < expected0InputMetaStatePosArr.length; msPos++) {
         assertNotNull(level1dynamicEngine.metaStateArr[msPos], "metaStateArr Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[0], "Pos0 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[1], "Pos1 " + msPos + " should be other.");
         assertEquals(expectedMetaStateInputStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].metaStateInputStatePos, "level1 metaStateInputStatePos on pos(" + msPos + ") should be other.");
         //assertEquals(expectedMetaStateOutputStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].metaStateOutputStatePos, "level1 metaStateOutputStatePos on pos(" + msPos + ") should be other.");
         assertEquals(expectedOutputMetaStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].outputMetaStatePos, "level1 outputMetaStatePos on pos(" + msPos + ") should be other.");

         assertNotNull(level1dynamicEngine.inputMetaStatePosToMetaStateArr[msPos], "Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level1dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[0], "Pos1 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level1dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[1], "Pos1 " + msPos + " should be other.");
      }
   }
}
