package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.neg0State;
import static de.schmiereck.col.model.State.nul0State;
import static de.schmiereck.col.model.State.pos0State;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class EngineService {

   public static Engine createLevel1staticEngine() {
      final State nul_1State = new State(1);
      nul_1State.inputStates[0] = nul0State;

      final State pos_1State = new State(1);
      pos_1State.inputStates[0] = pos0State;

      final State neg_1State = new State(1);
      neg_1State.inputStates[0] = neg0State;

      final Engine level1Engine = new Engine(1);

      level1Engine.setState(0, nul_1State, 0);
      level1Engine.setState(1, pos_1State, 1);
      level1Engine.setState(2, neg_1State, 2);

      return level1Engine;
   }

   public static Engine createLevel2staticEngine() {
      final State nul_nul_2State = new State(2);
      nul_nul_2State.inputStates[0] = nul0State;
      nul_nul_2State.inputStates[1] = nul0State;

      final State nul_pos_2State = new State(2);
      nul_pos_2State.inputStates[0] = nul0State;
      nul_pos_2State.inputStates[1] = pos0State;

      final State nul_neg_2State = new State(2);
      nul_neg_2State.inputStates[0] = nul0State;
      nul_neg_2State.inputStates[1] = neg0State;

      final State pos_nul_2State = new State(2);
      pos_nul_2State.inputStates[0] = pos0State;
      pos_nul_2State.inputStates[1] = nul0State;

      final State pos_pos_2State = new State(2);
      pos_pos_2State.inputStates[0] = pos0State;
      pos_pos_2State.inputStates[1] = pos0State;

      final State pos_neg_2State = new State(2);
      pos_neg_2State.inputStates[0] = pos0State;
      pos_neg_2State.inputStates[1] = neg0State;

      final State neg_nul_2State = new State(2);
      neg_nul_2State.inputStates[0] = neg0State;
      neg_nul_2State.inputStates[1] = nul0State;

      final State neg_pos_2State = new State(2);
      neg_pos_2State.inputStates[0] = neg0State;
      neg_pos_2State.inputStates[1] = pos0State;

      final State neg_neg_2State = new State(2);
      neg_neg_2State.inputStates[0] = neg0State;
      neg_neg_2State.inputStates[1] = neg0State;

      final Engine level2Engine = new Engine(2);

      level2Engine.setState(0, nul_nul_2State, 0);
      level2Engine.setState(1, nul_pos_2State, 1);
      level2Engine.setState(2, nul_neg_2State, 2);
      level2Engine.setState(3, pos_nul_2State, 3);
      level2Engine.setState(4, pos_pos_2State, 4);
      level2Engine.setState(5, pos_neg_2State, 0);
      level2Engine.setState(6, neg_nul_2State, 6);
      level2Engine.setState(7, neg_pos_2State, 0);
      level2Engine.setState(8, neg_neg_2State, 8);

      return level2Engine;
   }

   public static Engine createLevel3staticEngine() {
      final Engine level3staticEngine = new Engine(3);

      //    0   0   0   =>  0   0   0     0   0   0
      final State nul_nul_nul_3State = new State(3, nul0State, nul0State, nul0State);
      level3staticEngine.setState(0, nul_nul_nul_3State, 0);
      //    0   0   1   =>  0   0   1     0   1   0
      level3staticEngine.setState(1, new State(3, nul0State, nul0State, pos0State), 1);
      //    0   0  -1  =>   0   0  -1     0  -1   0
      level3staticEngine.setState(2, new State(3, nul0State, nul0State, neg0State), 2);
      //    0   1   0   =>  0   1   0     0   1   0
      level3staticEngine.setState(3, new State(3, nul0State, pos0State, nul0State), 3);
      //    0   1   1   =>  0   1   1     1   1   0
      level3staticEngine.setState(4, new State(3, nul0State, pos0State, pos0State), 4);
      //    0   1  -1  =>   0   0   0     0   0   0
      level3staticEngine.setState(5, new State(3, nul0State, pos0State, neg0State), 0);
      //    0  -1   0   =>  0  -1   0     0  -1   0
      level3staticEngine.setState(6, new State(3, nul0State, neg0State, nul0State), 6);
      //    0  -1   1   =>  0   0   0     0   0   0
      level3staticEngine.setState(7, new State(3, nul0State, neg0State, nul0State), 7);
      //    0  -1  -1  =>   0  -1  -1    -1  -1   0
      level3staticEngine.setState(8, new State(3, nul0State, neg0State, neg0State), 8);
      //
      //    1   0   0   =>  1   0   0     0   1   0
      level3staticEngine.setState(9, new State(3, pos0State, nul0State, nul0State), 9);
      //    1   0   1   =>  1   0   1     1   0   1
      level3staticEngine.setState(10, new State(3, pos0State, nul0State, pos0State), 10);
      //    1   0  -1  =>   1   0  -1     0   0   0
      level3staticEngine.setState(11, new State(3, pos0State, nul0State, neg0State), 11);
      //    1   1   0   =>  1   1   0     0   1   1
      level3staticEngine.setState(12, new State(3, pos0State, pos0State, nul0State), 12);
      //    1   1   1   =>  1   1   1     1   1   1
      level3staticEngine.setState(13, new State(3, pos0State, pos0State, pos0State), 13);
      //    1   1  -1  =>   1   0   0     1   0   0
      level3staticEngine.setState(14, new State(3, pos0State, pos0State, neg0State), 9);
      //    1  -1   0   =>  0   0   0     0   0   0
      level3staticEngine.setState(15, new State(3, pos0State, neg0State, nul0State), 0);
      //    1  -1   1   =>  1  -1   1     0   1   0
      level3staticEngine.setState(16, new State(3, pos0State, neg0State, pos0State), 16);
      //    1  -1  -1  =>   0   0  -1     0   0  -1
      level3staticEngine.setState(17, new State(3, pos0State, neg0State, neg0State), 2);
      //
      //   -1   0   0   => -1   0   0     0  -1   0
      level3staticEngine.setState(18, new State(3, neg0State, nul0State, nul0State), 18);
      //   -1   0   1   => -1   0   1     0   0   0
      level3staticEngine.setState(19, new State(3, neg0State, nul0State, pos0State), 19);
      //   -1   0  -1  =>  -1   0  -1    -1   0  -1
      level3staticEngine.setState(20, new State(3, neg0State, nul0State, neg0State), 20);
      //   -1   1   0   =>  0   0   0     0   0   0
      level3staticEngine.setState(21, new State(3, neg0State, pos0State, nul0State), 0);
      //   -1   1   1   =>  0   0   1     0   0   1
      level3staticEngine.setState(22, new State(3, neg0State, pos0State, pos0State), 1);
      //   -1   1  -1  =>  -1   1  -1    -1   1  -1
      level3staticEngine.setState(23, new State(3, neg0State, pos0State, neg0State), 23);
      //   -1  -1   0   => -1  -1   0     0  -1  -1
      level3staticEngine.setState(24, new State(3, neg0State, neg0State, nul0State), 24);
      //   -1  -1   1   => -1   0   0    -1   0   0
      level3staticEngine.setState(25, new State(3, neg0State, neg0State, pos0State), 18);
      //   -1  -1  -1  =>  -1  -1  -1    -1  -1  -1
      level3staticEngine.setState(26, new State(3, neg0State, neg0State, neg0State), 26);

      return level3staticEngine;
   }

   public static Engine createLevel3dynamicEngine() {
      final Engine level3dynamicEngine = new Engine(3, 32);

      level3dynamicEngine.setState(0, new State(3, nul0State, nul0State, nul0State), 0);
      level3dynamicEngine.setState(1, new State(3, nul0State, nul0State, pos0State), 2);
      level3dynamicEngine.setState(2, new State(3, nul0State, pos0State, nul0State), 3);
      level3dynamicEngine.setState(3, new State(3, pos0State, nul0State, nul0State), 4);
      level3dynamicEngine.setState(4, new State(3, nul0State, pos0State, nul0State), 1);

      level3dynamicEngine.setState(5, new State(3, nul0State, nul0State, neg0State), 6);
      level3dynamicEngine.setState(6, new State(3, nul0State, neg0State, nul0State), 7);
      level3dynamicEngine.setState(7, new State(3, neg0State, nul0State, nul0State), 8);
      level3dynamicEngine.setState(8, new State(3, nul0State, neg0State, nul0State), 5);

      level3dynamicEngine.setState(9, new State(3, nul0State, pos0State, pos0State), 10);
      level3dynamicEngine.setState(10, new State(3, pos0State, nul0State, pos0State), 11);
      level3dynamicEngine.setState(11, new State(3, pos0State, pos0State, nul0State), 12);
      level3dynamicEngine.setState(12, new State(3, pos0State, nul0State, pos0State), 9);

      level3dynamicEngine.setState(13, new State(3, nul0State, neg0State, neg0State), 14);
      level3dynamicEngine.setState(14, new State(3, neg0State, nul0State, neg0State), 15);
      level3dynamicEngine.setState(15, new State(3, neg0State, neg0State, nul0State), 16);
      level3dynamicEngine.setState(16, new State(3, neg0State, nul0State, neg0State), 13);

      level3dynamicEngine.setState(17, new State(3, pos0State, pos0State, pos0State), 17);

      level3dynamicEngine.setState(18, new State(3, nul0State, pos0State, neg0State), 0);
      level3dynamicEngine.setState(19, new State(3, nul0State, neg0State, pos0State), 0);
      level3dynamicEngine.setState(20, new State(3, nul0State, neg0State, nul0State), 0);
      level3dynamicEngine.setState(21, new State(3, neg0State, pos0State, nul0State), 0);

      level3dynamicEngine.setState(22, new State(3, pos0State, pos0State, neg0State), 3);
      level3dynamicEngine.setState(23, new State(3, neg0State, pos0State, pos0State), 1);
      level3dynamicEngine.setState(24, new State(3, pos0State, neg0State, neg0State), 5);
      level3dynamicEngine.setState(25, new State(3, neg0State, neg0State, pos0State), 7);

      level3dynamicEngine.setState(26, new State(3, pos0State, nul0State, neg0State), 26);
      level3dynamicEngine.setState(27, new State(3, neg0State, nul0State, pos0State), 27);
      level3dynamicEngine.setState(28, new State(3, pos0State, neg0State, pos0State), 28);
      level3dynamicEngine.setState(29, new State(3, neg0State, nul0State, neg0State), 29);
      level3dynamicEngine.setState(30, new State(3, neg0State, pos0State, neg0State), 30);
      level3dynamicEngine.setState(31, new State(3, neg0State, neg0State, neg0State), 31);

      return level3dynamicEngine;
   }

   public static Engine createLevel4staticEngine() {
      final State nul_nul_nul_nul_4State = new State(4);
      nul_nul_nul_nul_4State.inputStates[0] = nul0State;
      nul_nul_nul_nul_4State.inputStates[1] = nul0State;
      nul_nul_nul_nul_4State.inputStates[2] = nul0State;
      nul_nul_nul_nul_4State.inputStates[3] = nul0State;

      final Engine level4staticEngine = new Engine(4);

      level4staticEngine.setState(0, nul_nul_nul_nul_4State, 0);

      return level4staticEngine;
   }
}
