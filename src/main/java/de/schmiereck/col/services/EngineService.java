package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class EngineService {

   public static Engine createLevel0staticEngine() {
      final State nul_0State = new State(1);
      nul_0State.inputStates[0] = nulState;

      final State pos_0State = new State(1);
      pos_0State.inputStates[0] = posState;

      final State neg_0State = new State(1);
      neg_0State.inputStates[0] = negState;

      final Engine level1Engine = new Engine(1);

      level1Engine.setState(0, nul_0State, 0);
      level1Engine.setState(1, pos_0State, 1);
      level1Engine.setState(2, neg_0State, 2);

      return level1Engine;
   }

   public static Engine createLevel1staticEngine() {
      final State nul_nul_1State = new State(2);
      nul_nul_1State.inputStates[0] = nulState;
      nul_nul_1State.inputStates[1] = nulState;

      final State nul_pos_1State = new State(2);
      nul_pos_1State.inputStates[0] = nulState;
      nul_pos_1State.inputStates[1] = posState;

      final State nul_neg_1State = new State(2);
      nul_neg_1State.inputStates[0] = nulState;
      nul_neg_1State.inputStates[1] = negState;

      final State pos_nul_1State = new State(2);
      pos_nul_1State.inputStates[0] = posState;
      pos_nul_1State.inputStates[1] = nulState;

      final State pos_pos_1State = new State(2);
      pos_pos_1State.inputStates[0] = posState;
      pos_pos_1State.inputStates[1] = posState;

      final State pos_neg_1State = new State(2);
      pos_neg_1State.inputStates[0] = posState;
      pos_neg_1State.inputStates[1] = negState;

      final State neg_nul_1State = new State(2);
      neg_nul_1State.inputStates[0] = negState;
      neg_nul_1State.inputStates[1] = nulState;

      final State neg_pos_1State = new State(2);
      neg_pos_1State.inputStates[0] = negState;
      neg_pos_1State.inputStates[1] = posState;

      final State neg_neg_1State = new State(2);
      neg_neg_1State.inputStates[0] = negState;
      neg_neg_1State.inputStates[1] = negState;

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

      return level1dynamicEngine;
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
      final Engine level2dynamicEngine = new Engine(3, 32);

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

      return level2dynamicEngine;
   }

   public static Engine createLevel3staticEngine() {
      final State nul_nul_nul_nul_3State = new State(4);
      nul_nul_nul_nul_3State.inputStates[0] = nulState;
      nul_nul_nul_nul_3State.inputStates[1] = nulState;
      nul_nul_nul_nul_3State.inputStates[2] = nulState;
      nul_nul_nul_nul_3State.inputStates[3] = nulState;

      final Engine level4staticEngine = new Engine(4);

      level4staticEngine.setState(0, nul_nul_nul_nul_3State, 0);

      return level4staticEngine;
   }
}
