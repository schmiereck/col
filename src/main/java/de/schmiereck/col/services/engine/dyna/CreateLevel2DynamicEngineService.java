package de.schmiereck.col.services.engine.dyna;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;
import de.schmiereck.col.services.engine.CreateEngineService;

public class CreateLevel2DynamicEngineService {

   public static Engine createLevel2dynamicEngine() {
      final Engine level2dynamicEngine = createLevel2dynamicEngineStates();

      // missing for meta states:
      // 3,2,0
      //    0    0   0   0
      //    2        0   1   0
      //    3            1   0   0
      // is 11 ???
      //level2dynamicEngine.setState(32, new State(3, posState, posState, nulState), 31);

      CreateEngineService.initMetaStateArr(level2dynamicEngine);
      CreateEngineService.initOutputMetaStatePos(level2dynamicEngine);
      CreateEngineService.initLevelUpOutputMetaStatePosArr(level2dynamicEngine);

      final Engine e = level2dynamicEngine;
      //----------------------------------------------------------------------------------------------------------------
      // outputMetaState:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // null:
      // 0,0,0  =>  0,0,0
      //    0    0   0   0           =>   0    0   0   0
      //    0        0   0   0       =>   0        0   0   0
      //    0            0   0   0   =>   0            0   0   0
      level2dynamicEngine.metaStateArr[CreateEngineService.metaPos(e, 0, 0, 0)].outputMetaStatePos = CreateEngineService.metaPos(e, 0, 0, 0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // 0,0,1  =>  0,2,0
      //    1    0   0   1           =>   0    0   0   0
      //    0        0   0   0       =>   2        0   1   0
      //    0            0   0   0   =>   0            0   0   0
      level2dynamicEngine.metaStateArr[CreateEngineService.metaPos(e, 0, 0, 1)].outputMetaStatePos = CreateEngineService.metaPos(e, 0, 2, 0);
      // 1,0,0  =>  1,0,0
      //    0    0   0   0           =>   0    0   0   0
      //    0        0   0   0       =>   0        0   0   0
      //    1            0   0   1   =>   1            0   0   1
      level2dynamicEngine.metaStateArr[CreateEngineService.metaPos(e, 1, 0, 0)].outputMetaStatePos = CreateEngineService.metaPos(e, 1, 0, 0);
      // 0,1,0  =>  0,1,0
      //    0    0   0   0           =>   0    0   0   0
      //    1        0   0   1       =>   1        0   0   1
      //    0            0   0   0   =>   0            0   0   0
      level2dynamicEngine.metaStateArr[CreateEngineService.metaPos(e, 0, 1, 0)].outputMetaStatePos = CreateEngineService.metaPos(e, 0, 1, 0);

      // 0,2,0  =>  3,0,0
      //    0    0   0   0           =>   0    0   0   0
      //    2        0   1   0       =>   0        0   0   0
      //    0            0   0   0   =>   3            1   0   0
      level2dynamicEngine.metaStateArr[CreateEngineService.metaPos(e, 0, 2, 0)].outputMetaStatePos = CreateEngineService.metaPos(e, 3, 0, 0);
      // 2,0,0  =>  2,0,0
      //    0    0   0   0           =>   0    0   0   0
      //    0        0   0   0       =>   0        0   0   0
      //    2            0   1   0   =>   2            0   1   0
      level2dynamicEngine.metaStateArr[CreateEngineService.metaPos(e, 2, 0, 0)].outputMetaStatePos = CreateEngineService.metaPos(e, 2, 0, 0);
      // 0,0,2  =>  0,0,2
      //    2    0   1   0           =>   2    0   1   0
      //    0        0   0   0       =>   0        0   0   0
      //    0            0   0   0   =>   0            0   0   0
      level2dynamicEngine.metaStateArr[CreateEngineService.metaPos(e, 0, 0, 2)].outputMetaStatePos = CreateEngineService.metaPos(e, 0, 0, 2);

      // 3,0,0  =>  0,4,0
      //    0    0   0   0           =>   0    0   0   0
      //    0        0   0   0       =>   4        0   1   0
      //    3            1   0   0   =>   0            0   0   0
      level2dynamicEngine.metaStateArr[CreateEngineService.metaPos(e, 3, 0, 0)].outputMetaStatePos = CreateEngineService.metaPos(e, 0, 4, 0);
      // 0,3,0  =>  0,3,0
      //    0    0   0   0           =>   0    0   0   0
      //    3        1   0   0       =>   3        1   0   0
      //    0            0   0   0   =>   0            0   0   0
      level2dynamicEngine.metaStateArr[CreateEngineService.metaPos(e, 0, 3, 0)].outputMetaStatePos = CreateEngineService.metaPos(e, 0, 3, 0);
      // 0,0,3  =>  0,0,3
      //    3    1   0   0           =>   3    1   0   0
      //    0        0   0   0       =>   0        0   0   0
      //    0            0   0   0   =>   0            0   0   0
      level2dynamicEngine.metaStateArr[CreateEngineService.metaPos(e, 0, 0, 3)].outputMetaStatePos = CreateEngineService.metaPos(e, 0, 0, 3);

      // 0,4,0  =>  0,0,1
      //    0    0   0   0           =>   1    0   0   1
      //    4        0   1   0       =>   0        0   0   0
      //    0            0   0   0   =>   0            0   0   0
      level2dynamicEngine.metaStateArr[CreateEngineService.metaPos(e, 0, 4, 0)].outputMetaStatePos = CreateEngineService.metaPos(e, 0, 0, 1);
      // 4,0,0  =>  4,0,0
      //    0    0   0   0           =>   0    0   0   0
      //    0        0   0   0       =>   0        0   0   0
      //    4            0   1   0   =>   4            0   1   0
      level2dynamicEngine.metaStateArr[CreateEngineService.metaPos(e, 4, 0, 0)].outputMetaStatePos = CreateEngineService.metaPos(e, 4, 0, 0);
      // 0,0,4  =>  0,0,4
      //    4    0   1   0           =>   4    0   1   0
      //    0        0   0   0       =>   0        0   0   0
      //    0            0   0   0   =>   0            0   0   0
      level2dynamicEngine.metaStateArr[CreateEngineService.metaPos(e, 0, 0, 4)].outputMetaStatePos = CreateEngineService.metaPos(e, 0, 0, 4);

      //----------------------------------------------------------------------------------------------------------------
      return level2dynamicEngine;
   }

   public static Engine createLevel2dynamicEngineStates() {
      final Engine level2dynamicEngine = new Engine(3, 32);

      // null
      level2dynamicEngine.setState( 0, new State(3, nulState, nulState, nulState), 0);

      // dyn:null,null,+1
      level2dynamicEngine.setState( 1, new State(3, nulState, nulState, posState), 2);
      level2dynamicEngine.setState( 2, new State(3, nulState, posState, nulState), 3);
      level2dynamicEngine.setState( 3, new State(3, posState, nulState, nulState), 4);
      level2dynamicEngine.setState( 4, new State(3, nulState, posState, nulState), 1);

      // dyn:null,null,-1
      level2dynamicEngine.setState( 5, new State(3, nulState, nulState, negState), 6);
      level2dynamicEngine.setState( 6, new State(3, nulState, negState, nulState), 7);
      level2dynamicEngine.setState( 7, new State(3, negState, nulState, nulState), 8);
      level2dynamicEngine.setState( 8, new State(3, nulState, negState, nulState), 5);

      // dyn:null,+1,+1
      level2dynamicEngine.setState( 9, new State(3, nulState, posState, posState), 10);
      level2dynamicEngine.setState(10, new State(3, posState, nulState, posState), 11);
      level2dynamicEngine.setState(11, new State(3, posState, posState, nulState), 12);
      level2dynamicEngine.setState(12, new State(3, posState, nulState, posState), 9);

      // dyn:null,-1,-1
      level2dynamicEngine.setState(13, new State(3, nulState, negState, negState), 14);
      level2dynamicEngine.setState(14, new State(3, negState, nulState, negState), 15);
      level2dynamicEngine.setState(15, new State(3, negState, negState, nulState), 16);
      level2dynamicEngine.setState(16, new State(3, negState, nulState, negState), 13);

      // +1,+1,+1
      level2dynamicEngine.setState(17, new State(3, posState, posState, posState), 17);

      // null,+1,-1
      level2dynamicEngine.setState(18, new State(3, nulState, posState, negState), 0);
      level2dynamicEngine.setState(19, new State(3, nulState, negState, posState), 0);
      level2dynamicEngine.setState(20, new State(3, posState, negState, nulState), 0);
      level2dynamicEngine.setState(21, new State(3, negState, posState, nulState), 0);

      // +1,+1,-1
      level2dynamicEngine.setState(22, new State(3, posState, posState, negState), 3);
      level2dynamicEngine.setState(23, new State(3, negState, posState, posState), 1);
      level2dynamicEngine.setState(24, new State(3, posState, negState, negState), 5);
      level2dynamicEngine.setState(25, new State(3, negState, negState, posState), 7);

      // +1,null,-1
      level2dynamicEngine.setState(26, new State(3, posState, nulState, negState), 26);
      level2dynamicEngine.setState(27, new State(3, negState, nulState, posState), 27);
      level2dynamicEngine.setState(28, new State(3, posState, negState, posState), 28);
      level2dynamicEngine.setState(29, new State(3, negState, nulState, negState), 29);
      level2dynamicEngine.setState(30, new State(3, negState, posState, negState), 30);
      level2dynamicEngine.setState(31, new State(3, negState, negState, negState), 31);

      return level2dynamicEngine;
   }
}
