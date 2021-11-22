package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.CreateEngineService.initMetaStateArr;
import static de.schmiereck.col.services.CreateEngineService.writeMetaState;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class CreateLevel0EngineService {

   public static Engine createLevel0staticEngine() {
      //----------------------------------------------------------------------------------------------------------------
      final Engine level1Engine = new Engine(1);

      //----------------------------------------------------------------------------------------------------------------
      // null:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      final int NULL_u0 = 0;
      // 0    0   =>   0    0
      level1Engine.setState(NULL_u0, new State(1, nulState), NULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      final int STAY_p1 = 1;
      final int STAY_n1 = 2;
      // 1    1   =>   1    1
      level1Engine.setState(STAY_p1, new State(1, posState), STAY_p1);
      // 2   -1   =>   2   -1
      level1Engine.setState(STAY_n1, new State(1, negState), STAY_n1);

      //----------------------------------------------------------------------------------------------------------------
      return level1Engine;
   }

   // null:
   final static int NULL_u0 = 0;
   // stay:
   final static int STAY_p1 = 1;
   final static int STAY_n1 = 2;
   // left:
   final static int LEFT_p1 = 3;
   final static int LEFT2_p1 = 4;
   final static int LEFT_n1 = 5;
   final static int LEFT2_n1 = 6;
   // right:
   final static int RIGHT_p1 = 7;
   final static int RIGHT2_p1 = 8;
   final static int RIGHT_n1 = 9;
   final static int RIGHT2_n1 = 10;

   public static Engine createLevel0moveEngine() {
      //----------------------------------------------------------------------------------------------------------------
      final Engine level0Engine = new Engine(1, 11);

      //----------------------------------------------------------------------------------------------------------------
      // null:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // 0    0   =>   0    0
      level0Engine.setState(NULL_u0, new State(1, nulState), NULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      // 1    1   =>   1    1
      level0Engine.setState(STAY_p1, new State(1, posState), STAY_p1);
      // 2   -1   =>   2   -1
      level0Engine.setState(STAY_n1, new State(1, negState), STAY_n1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // left:
      // 0    0       =>   3    1
      // 3        1   =>   0        0
      level0Engine.setState(LEFT_p1, new State(1, posState), LEFT_p1);
      level0Engine.setState(LEFT2_p1, new State(1, posState), LEFT_p1);
      // 0    0       =>   4   -1
      // 4       -1   =>   0        0
      level0Engine.setState(LEFT_n1, new State(1, negState), LEFT_p1);
      level0Engine.setState(LEFT2_n1, new State(1, negState), LEFT_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      // 5    1       =>   0    0
      // 0        0   =>   5        1
      level0Engine.setState(RIGHT_p1, new State(1, posState), RIGHT_p1);
      level0Engine.setState(RIGHT2_p1, new State(1, posState), RIGHT_p1);
      // 6   -1       =>   0    0
      // 0        0   =>   6       -1
      level0Engine.setState(RIGHT_n1, new State(1, negState), RIGHT_n1);
      level0Engine.setState(RIGHT2_n1, new State(1, negState), RIGHT_n1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision:

      //----------------------------------------------------------------------------------------------------------------
      initMetaStateArr(level0Engine);
      //initOutputMetaStatePos(level1dynamicEngine);

      final Engine e = level0Engine;

      //----------------------------------------------------------------------------------------------------------------
      // outputMetaState:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // null:
      // 0,0  =>  0,0
      //    0    0       =>   0    0
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, NULL_u0, NULL_u0, NULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      // 0,1  =>  0,1
      //    1    1       =>   1    1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, STAY_p1, NULL_u0, STAY_p1);
      // 1,0  =>  1,0
      //    0    0       =>   0    0
      //    1        1   =>   1        1
      writeMetaState(e, STAY_p1, NULL_u0, STAY_p1, NULL_u0);
      // 0,2  =>  0,2
      //    2   -1       =>   2   -1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, STAY_n1, NULL_u0, STAY_n1);
      // 2,0  =>  2,0
      //    0    0       =>   0    0
      //    2       -1   =>   2       -1
      writeMetaState(e, STAY_n1, NULL_u0, STAY_n1, NULL_u0);
      // 1,1  =>  1,1
      //    1    1       =>   1    1
      //    1        1   =>   1        1
      writeMetaState(e, STAY_p1, STAY_p1, STAY_p1, STAY_p1);
      // 2,2  =>  2,2
      //    2   -1       =>   2   -1
      //    2       -1   =>   2       -1
      writeMetaState(e, STAY_n1, STAY_n1, STAY_n1, STAY_n1);
      // 2,1  =>  0,0
      //    1    1       =>   0    0
      //    2       -1   =>   0        0
      writeMetaState(e, STAY_n1, STAY_p1, NULL_u0, NULL_u0);
      // 1,2  =>  0,0
      //    2   -1       =>   0    0
      //    1        1   =>   0        0
      writeMetaState(e, STAY_p1, STAY_n1, NULL_u0, NULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // left:
      // 0    0       =>   4    1
      // 3        1   =>   0        0
      writeMetaState(e, LEFT_p1, NULL_u0, NULL_u0, LEFT2_p1);
      // 0    0       =>   4   -1
      // 4       -1   =>   0        0
      writeMetaState(e, LEFT_n1, NULL_u0, NULL_u0, LEFT2_n1);
      // 3    1       =>   3    1
      // 0        0   =>   0        0
      writeMetaState(e, NULL_u0, LEFT_p1, NULL_u0, LEFT_p1);
      // 4    1       =>   4    1
      // 0        0   =>   0        0
      writeMetaState(e, NULL_u0, LEFT2_p1, LEFT2_p1, NULL_u0);
      // 4   -1       =>   4   -1
      // 0        0   =>   0        0
      writeMetaState(e, NULL_u0, LEFT_n1, NULL_u0, LEFT_n1);
      // 6   -1       =>   6   -1
      // 0        0   =>   0        0
      writeMetaState(e, NULL_u0, LEFT2_n1, NULL_u0, LEFT2_n1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      // 0    0       =>   0    0
      // 5        1   =>   5        1
      writeMetaState(e, RIGHT_p1, NULL_u0, RIGHT_p1, NULL_u0);
      // 0    0       =>   0    0
      // 6       -1   =>   6       -1
      writeMetaState(e, RIGHT_n1, NULL_u0, RIGHT_n1, NULL_u0);
      // 0    0       =>   0    0
      // 10      -1   =>   10      -1
      writeMetaState(e, RIGHT2_n1, NULL_u0, RIGHT2_n1, NULL_u0);
      // 5    1       =>   0    0
      // 0        0   =>   8        1
      writeMetaState(e, NULL_u0, RIGHT_p1, RIGHT2_p1, NULL_u0);
      // 8    1       =>   8    1
      // 0        0   =>   0        0
      writeMetaState(e, NULL_u0, RIGHT2_p1, NULL_u0, RIGHT2_p1);
      // 6   -1       =>   0    0
      // 0        0   =>   6       -1
      writeMetaState(e, NULL_u0, RIGHT_n1, RIGHT2_n1, NULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (right -> <- left):
      // 5    1       =>   5    1
      // 3        1   =>   3        1
      writeMetaState(e, LEFT_p1, RIGHT_p1, LEFT_p1, RIGHT_p1);
      // 6   -1       =>   6   -1
      // 4       -1   =>   4       -1
      writeMetaState(e, LEFT_n1, RIGHT_n1, LEFT_n1, RIGHT_n1);
      // 6   -1       =>   0    0
      // 3        1   =>   0        0
      writeMetaState(e, LEFT_p1, RIGHT_n1, NULL_u0, NULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (left -> <- right):
      // 5    1       =>   5    1
      // 3        1   =>   3        1
      writeMetaState(e, RIGHT_p1, LEFT_p1, RIGHT_p1, LEFT_p1);
      // 6   -1       =>   4   -1
      // 4       -1   =>   6       -1
      writeMetaState(e, RIGHT_n1, LEFT_n1, LEFT_n1, RIGHT_n1);
      // 3    1       =>   0    0
      // 6       -1   =>   0        0
      writeMetaState(e, RIGHT_n1, LEFT_p1, NULL_u0, NULL_u0);

      //----------------------------------------------------------------------------------------------------------------
      return level0Engine;
   }
}
