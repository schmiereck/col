package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.MetaState;
import de.schmiereck.col.model.State;

import org.junit.jupiter.api.Test;

public class Test_CreateEngineService_WHEN_initMetaStateArr_is_called {

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

      // Act
      CreateEngineService.initMetaStateArr(level1dynamicEngine);

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
      //for (int msPos = 0; msPos < level1dynamicEngine.metaStateArr.length; msPos++) {
      for (int msPos = 0; msPos < expected0InputMetaStatePosArr.length; msPos++) {
         assertEquals(expected0InputMetaStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[0], "Pos0 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level1dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[1], "Pos1 " + msPos + " should be other.");
         assertNotNull(level1dynamicEngine.inputMetaStatePosToMetaStateArr[msPos], "Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level1dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[0], "Pos1 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level1dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[1], "Pos1 " + msPos + " should be other.");
      }
   }

   @Test
   void GIVEN_level2dynamicEngine_with_4_states_THEN_metaStateArr_and_inputMetaStatePosToMetaStateArr_initialized() {
      // Arrange
      final Engine level2dynamicEngine = new Engine(3, 4);

      level2dynamicEngine.setState( 0, new State(3, nulState, nulState, nulState), 0);
      level2dynamicEngine.setState( 1, new State(3, nulState, nulState, posState), 2);
      level2dynamicEngine.setState( 2, new State(3, nulState, posState, nulState), 3);
      level2dynamicEngine.setState( 3, new State(3, posState, nulState, nulState), 4);

      // Act
      CreateEngineService.initMetaStateArr(level2dynamicEngine);

      // Assert
      // 4^3 = 4*4 * 4 = 16 * 4 = 40+24 = 64
      // 3^4 = 3*3 * 3*3 = 9 * 9 = 81
      // 3^3 = 3*3*3 = 9*3 = 27
      final int expected0InputMetaStatePosArr[] = { //64
           // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
              0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3,
              0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3,
      };
      final int expected1InputMetaStatePosArr[] = {
           // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
              0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3,
              0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3,
              0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3,
              0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3,
      };
      final int expected2InputMetaStatePosArr[] = {
           // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
      };
      //for (int msPos = 0; msPos < level1dynamicEngine.metaStateArr.length; msPos++) {
      for (int msPos = 0; msPos < expected0InputMetaStatePosArr.length; msPos++) {
         assertNotNull(level2dynamicEngine.metaStateArr[msPos], "metaStateArr Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[0], "meta Pos0 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[1], "meta Pos1 " + msPos + " should be other.");
         assertEquals(expected2InputMetaStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[2], "meta Pos2 " + msPos + " should be other.");
         assertNotNull(level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos], "inputMetaStatePosToMetaStateArr Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[0], "inputMeta Pos0 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[1], "inputMeta Pos1 " + msPos + " should be other.");
         assertEquals(expected2InputMetaStatePosArr[msPos], level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[2], "inputMeta Pos2 " + msPos + " should be other.");
      }
   }

   @Test
   void GIVEN_level2dynamicEngine_with_2_states_THEN_metaStateArr_and_inputMetaStatePosToMetaStateArr_initialized() {
      // Arrange
      final Engine level2dynamicEngine = new Engine(3, 2);

      level2dynamicEngine.setState( 0, new State(3, nulState, nulState, nulState), 0);
      level2dynamicEngine.setState( 1, new State(3, nulState, nulState, posState), 2);

      // Act
      CreateEngineService.initMetaStateArr(level2dynamicEngine);

      // Assert
      final int expected0InputMetaStatePosArr[] = {
           // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
              0, 1, 0, 1, 0, 1, 0, 1,
      };
      final int expected1InputMetaStatePosArr[] = {
           // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
              0, 0, 1, 1, 0, 0, 1, 1
      };
      final int expected2InputMetaStatePosArr[] = {
           // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
              0, 0, 0, 0, 1, 1, 1, 1
      };
      //for (int msPos = 0; msPos < level1dynamicEngine.metaStateArr.length; msPos++) {
      for (int msPos = 0; msPos < expected0InputMetaStatePosArr.length; msPos++) {
         assertNotNull(level2dynamicEngine.metaStateArr[msPos], "metaStateArr Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[0], "meta Pos0 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[1], "meta Pos1 " + msPos + " should be other.");
         assertEquals(expected2InputMetaStatePosArr[msPos], level2dynamicEngine.metaStateArr[msPos].inputMetaStatePosArr[2], "meta Pos2 " + msPos + " should be other.");
         assertNotNull(level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos], "inputMetaStatePosToMetaStateArr Pos " + msPos + " should be not null.");
         assertEquals(expected0InputMetaStatePosArr[msPos], level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[0], "inputMeta Pos0 " + msPos + " should be other.");
         assertEquals(expected1InputMetaStatePosArr[msPos], level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[1], "inputMeta Pos1 " + msPos + " should be other.");
         assertEquals(expected2InputMetaStatePosArr[msPos], level2dynamicEngine.inputMetaStatePosToMetaStateArr[msPos].inputMetaStatePosArr[2], "inputMeta Pos2 " + msPos + " should be other.");
      }
   }
}
