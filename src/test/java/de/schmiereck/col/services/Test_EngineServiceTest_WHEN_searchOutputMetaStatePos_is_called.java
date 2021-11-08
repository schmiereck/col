package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.MetaState;
import de.schmiereck.col.model.State;

import org.junit.jupiter.api.Test;

public class Test_EngineServiceTest_WHEN_searchOutputMetaStatePos_is_called {

   @Test
   void searchOutputMetaStatePos() {
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

      EngineService.initMetaStateArr(level1dynamicEngine);

      // Act
      final int outputMetaStatePosArr[] = new int[level1dynamicEngine.metaStateArr.length];

      for (int msPos = 0; msPos < level1dynamicEngine.metaStateArr.length; msPos++) {
         final MetaState metaState = level1dynamicEngine.metaStateArr[msPos];
         outputMetaStatePosArr[msPos] = EngineService.searchOutputMetaStatePos(level1dynamicEngine.inputStateArr, level1dynamicEngine.outputStatePosArr, level1dynamicEngine.metaStateArr, metaState);
      }

      // Assert
      final int expectedOutputMetaStatePosArr[] = {
              0, 1, 9, 3, 27, 10, 12, 28, 30, 2, 5, 11, 6, 0, 14, 15, 1, 3
      };
      //for (int msPos = 0; msPos < level1dynamicEngine.metaStateArr.length; msPos++) {
      for (int msPos = 0; msPos < expectedOutputMetaStatePosArr.length; msPos++) {
         assertEquals(expectedOutputMetaStatePosArr[msPos], outputMetaStatePosArr[msPos], "Pos " + msPos + " should be other.");
      }
   }
}
