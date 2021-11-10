package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.CreateEngineService.initOutputMetaStatePos;
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
      final int expected1OutputMetaStatePosArr[] = {
           //  0,  1,  2,  3,  4,  5,  6,  7,  8,
               0,  1,  9,  3, 27, 10, 12, 28, 30,
           //  9, 10, 11, 12, 13, 14, 15, 16, 17,
               2,  5, 11,  6,  0, 14, 15,  1,  3,
              18, 19, 45, 21, 54, 46, 48, 55, 57,
               4,  7,  0,  8, 31,  1,  3, 34, 35,
              36, 37, 63, 39, 72, 64, 66, 73, 75,
              20, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
              0, 1, 2, 3, 4, 5, 6, 7, 8,
      };
      //for (int msPos = 0; msPos < level1dynamicEngine.metaStateArr.length; msPos++) {
      for (int msPos = 0; msPos < expected0InputMetaStatePosArr.length; msPos++) {
         assertNotNull(level1dynamicEngine.metaStateArr[msPos], "metaStateArr Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[0], "Pos0 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[1], "Pos1 " + msPos + " should be other.");
         assertEquals(expected1OutputMetaStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].outputMetaStatePos, "level1 outputMetaStatePos on pos(" + msPos + ") should be other.");
         assertNotNull(level1dynamicEngine.inputMetaStatePosToMetaStateArr[msPos], "Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level1dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[0], "Pos1 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level1dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[1], "Pos1 " + msPos + " should be other.");
      }
   }
}
