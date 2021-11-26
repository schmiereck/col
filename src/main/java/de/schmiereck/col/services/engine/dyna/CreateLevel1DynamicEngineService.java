package de.schmiereck.col.services.engine.dyna;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.engine.CreateEngineService.initLevelUpOutputMetaStatePosArr;
import static de.schmiereck.col.services.engine.CreateEngineService.initMetaStateArr;
import static de.schmiereck.col.services.engine.CreateEngineService.initOutputMetaStatePos;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class CreateLevel1DynamicEngineService {

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
      initLevelUpOutputMetaStatePosArr(level1dynamicEngine);

      //for (int l1Pos = 0; l1Pos < level1dynamicEngine.inputStateArr.length; l1Pos++) {
      //   for (int l2Pos = 0; l2Pos < level1dynamicEngine.inputStateArr.length; l2Pos++) {
      //      level1dynamicEngine.inputMetaStatePosToMetaStateArr[l1Pos][l2Pos];
      //   }
      //}

      return level1dynamicEngine;
   }
}
