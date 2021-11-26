package de.schmiereck.col.services.engine.stat;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class CreateLevel2StaticEngineService {

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
}
