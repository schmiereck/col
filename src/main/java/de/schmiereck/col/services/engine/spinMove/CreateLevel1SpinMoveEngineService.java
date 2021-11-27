package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.engine.CreateEngineService.initMetaStateArr;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.CreateEngineService.writeMetaState;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class CreateLevel1SpinMoveEngineService {

   // null:
   final static int NULL_u0_u0 = 0;
   // stay:
   final static int STAYa_u0_p1 = 1;
   final static int STAYb_u0_p1 = 2;
   final static int STAYa_p1_u0 = 3;
   final static int STAYb_p1_u0 = 4;
   // left:
   final static int LEFT_u0_p1 = 5;
   final static int LEFT_p1_u0 = 6;
   // right:
   final static int RIGHT_p1_u0 = 7;
   final static int RIGHT_u0_p1 = 8;
   // collision:
   final static int COLL_LR_p1_p1 = 9;
   final static int COLL_RS_p1_p1 = 10;
   final static int COLL_LS_p1_p1 = 11;

   public static Engine createLevel1SpinMoveEngine() {
      //----------------------------------------------------------------------------------------------------------------
      final Engine level1moveEngine = new Engine(2, 12);

      //----------------------------------------------------------------------------------------------------------------
      // null:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //  0    0   0   =>   0
      level1moveEngine.setState(NULL_u0_u0, new State(2, nulState, nulState), NULL_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      // 1    0   1   =>   1    0   1
      level1moveEngine.setState(STAYa_u0_p1, new State(2, nulState, posState), STAYb_u0_p1);
      level1moveEngine.setState(STAYb_u0_p1, new State(2, nulState, posState), STAYa_u0_p1);
      // 2    1   0   =>   2    1   0
      level1moveEngine.setState(STAYa_p1_u0, new State(2, posState, nulState), STAYb_p1_u0);
      level1moveEngine.setState(STAYb_p1_u0, new State(2, posState, nulState), STAYa_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // left:
      // 3    0   1   =>   4    1   0
      level1moveEngine.setState(LEFT_u0_p1, new State(2, nulState, posState), LEFT_p1_u0);
      // 4    1   0   =>   4    1   0
      level1moveEngine.setState(LEFT_p1_u0, new State(2, posState, nulState), LEFT_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      // 5    1   0   =>   6    0   1
      level1moveEngine.setState(RIGHT_p1_u0, new State(2, posState, nulState), RIGHT_u0_p1);
      // 6    0   1   =>   6    0   1
      level1moveEngine.setState(RIGHT_u0_p1, new State(2, nulState, posState), RIGHT_u0_p1);

      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision:
      // 7:collision (left -> <- right):
      // 7    1   1   =>   7
      level1moveEngine.setState(COLL_LR_p1_p1, new State(2, posState, posState), COLL_LR_p1_p1);
      // 8:collision-to-right (right -> stay):
      // 8    1   1   =>   8
      level1moveEngine.setState(COLL_RS_p1_p1, new State(2, posState, posState), COLL_RS_p1_p1);
      // 9:collision-to-left (left -> stay):
      // 9    1   1   =>   9
      level1moveEngine.setState(COLL_LS_p1_p1, new State(2, posState, posState), COLL_LS_p1_p1);

      //----------------------------------------------------------------------------------------------------------------
      initMetaStateArr(level1moveEngine);
      //initOutputMetaStatePos(level1dynamicEngine);

      final Engine e = level1moveEngine;

      //----------------------------------------------------------------------------------------------------------------
      // outputMetaState:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // null:
      // 0,0  =>  0,0
      //    0    0   0       =>   0    0   0
      //    0        0   0   =>   0        0   0
      writeMetaState(e, NULL_u0_u0, NULL_u0_u0, NULL_u0_u0, NULL_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      // 0,1  =>  0,1
      //    1    0   1       =>   1    0   1
      //    0        0   0   =>   0        0   0
      writeMetaState(e, NULL_u0_u0, STAYa_u0_p1, NULL_u0_u0, STAYa_u0_p1);
      writeMetaState(e, NULL_u0_u0, STAYb_u0_p1, NULL_u0_u0, STAYb_u0_p1);
      // 1,0  =>  1,0
      //    0    0   0       =>   0    0   0
      //    1        0   1   =>   1        0   1
      writeMetaState(e, STAYa_u0_p1, NULL_u0_u0, STAYa_u0_p1, NULL_u0_u0);
      writeMetaState(e, STAYb_u0_p1, NULL_u0_u0, STAYb_u0_p1, NULL_u0_u0);
      // 0,2  =>  0,2
      //    2    1   0       =>   2    1   0       2:stay -> 2:stay
      //    0        0   0   =>   0        0   0
      writeMetaState(e, NULL_u0_u0, STAYa_p1_u0, NULL_u0_u0, STAYa_p1_u0);
      writeMetaState(e, NULL_u0_u0, STAYb_p1_u0, NULL_u0_u0, STAYb_p1_u0);
      // 2,0  =>  2,0
      //    0    0   0       =>   0    0   0
      //    2        1   0   =>   2        1   0   2:stay -> 2:stay
      writeMetaState(e, STAYa_p1_u0, NULL_u0_u0, STAYa_p1_u0, NULL_u0_u0);
      writeMetaState(e, STAYb_p1_u0, NULL_u0_u0, STAYb_p1_u0, NULL_u0_u0);
      // 1,1  =>  1,1
      //    1    0   1       =>   1    0   1
      //    1        0   1   =>   1        0   1
      writeMetaState(e, STAYa_u0_p1, STAYa_u0_p1, STAYa_u0_p1, STAYa_u0_p1);
      writeMetaState(e, STAYb_u0_p1, STAYb_u0_p1, STAYb_u0_p1, STAYb_u0_p1);
      // 1,2  =>  1,2
      //    2    1   0       =>   2    1   0       2:stay -> 2:stay
      //    1        0   1   =>   1        0   1
      writeMetaState(e, STAYa_u0_p1, STAYa_p1_u0, STAYa_u0_p1, STAYa_p1_u0);
      writeMetaState(e, STAYb_u0_p1, STAYa_p1_u0, STAYb_u0_p1, STAYa_p1_u0);
      writeMetaState(e, STAYa_u0_p1, STAYb_p1_u0, STAYa_u0_p1, STAYb_p1_u0);
      writeMetaState(e, STAYb_u0_p1, STAYb_p1_u0, STAYb_u0_p1, STAYb_p1_u0);
      // 2,1  =>  2,1
      //    1    0   1       =>   1    0   1
      //    2        1   0   =>   2        1   0   2:stay -> 2:stay
      writeMetaState(e, STAYa_p1_u0, STAYa_u0_p1, STAYa_p1_u0, STAYa_u0_p1);
      writeMetaState(e, STAYb_p1_u0, STAYa_u0_p1, STAYb_p1_u0, STAYa_u0_p1);
      writeMetaState(e, STAYa_p1_u0, STAYb_u0_p1, STAYa_p1_u0, STAYb_u0_p1);
      writeMetaState(e, STAYb_p1_u0, STAYb_u0_p1, STAYb_p1_u0, STAYb_u0_p1);
      //    1    0   1       =>   1    0   1
      //    2        0   1   =>   2        0   1
      writeMetaState(e, STAYb_u0_p1, STAYa_u0_p1, STAYb_u0_p1, STAYa_u0_p1);
      writeMetaState(e, STAYa_u0_p1, STAYb_u0_p1, STAYa_u0_p1, STAYb_u0_p1);
      //    3    1   0       =>   3    1   0
      //    4        1   0   =>   4        1   0
      writeMetaState(e, STAYb_p1_u0, STAYa_p1_u0, STAYb_p1_u0, STAYa_p1_u0);
      //    4    1   0       =>   4    1   0
      //    3        1   0   =>   3        1   0
      writeMetaState(e, STAYa_p1_u0, STAYb_p1_u0, STAYa_p1_u0, STAYb_p1_u0);
      //    3    1   0       =>   3    1   0
      //    3        1   0   =>   3        1   0
      writeMetaState(e, STAYa_p1_u0, STAYa_p1_u0, STAYa_p1_u0, STAYa_p1_u0);
      //    4    1   0       =>   4    1   0
      //    4        1   0   =>   4        1   0
      writeMetaState(e, STAYb_p1_u0, STAYb_p1_u0, STAYb_p1_u0, STAYb_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      //    5    1   0       =>   5    1   0
      //    0        0   0   =>   0        0   0
      writeMetaState(e, NULL_u0_u0, RIGHT_p1_u0, NULL_u0_u0, RIGHT_p1_u0);
      //    0    0   0       =>   0    0   0
      //    5        1   0   =>   5        1   0
      writeMetaState(e, RIGHT_p1_u0, NULL_u0_u0, RIGHT_p1_u0, NULL_u0_u0);
      //    0    0   0       =>   0    0   0
      //    8        0   1   =>   8        0   1
      writeMetaState(e, RIGHT_u0_p1, NULL_u0_u0, RIGHT_u0_p1, NULL_u0_u0);
      //    8    0   1       =>   8    0   1       6:right  -> 6:right  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    8        0   1   =>   8        0   1   6:right  -> 6:right  (!!! Alternativ: collision-left-left !!!)
      writeMetaState(e, RIGHT_u0_p1, RIGHT_u0_p1, RIGHT_u0_p1, RIGHT_u0_p1);
      //    6    0   1       =>   0    0   0
      //    0        0   0   =>   5        1   0
      writeMetaState(e, NULL_u0_u0, RIGHT_u0_p1, RIGHT_p1_u0, NULL_u0_u0);
      //    5    1   0       =>   5    1   0
      //    5        1   0   =>   5        1   0
      writeMetaState(e, RIGHT_p1_u0, RIGHT_p1_u0, RIGHT_p1_u0, RIGHT_p1_u0);
      //    7    1   0       =>   5    1   0      5:right -> 5:right
      //    8        0   1   =>   6        0   1  6:right -> 6:right
      writeMetaState(e, RIGHT_u0_p1, RIGHT_p1_u0, RIGHT_u0_p1, RIGHT_p1_u0);
      //    2    0   1       =>   2    0   1
      //    8        0   1   =>   8        0   1
      writeMetaState(e, RIGHT_u0_p1, STAYb_u0_p1, RIGHT_u0_p1, STAYb_u0_p1);
      //    3    1   0       =>   5    1   0
      //    8        0   1   =>   6        0   1
      writeMetaState(e, RIGHT_u0_p1, STAYa_p1_u0, RIGHT_u0_p1, STAYa_p1_u0);
      //    4    1   0       =>   5    1   0
      //    8        0   1   =>   6        0   1
      writeMetaState(e, RIGHT_u0_p1, STAYb_p1_u0, RIGHT_u0_p1, STAYb_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // left:
      //    3    0   1       =>   3    0   1
      //    0        0   0   =>   0        0   0
      writeMetaState(e, NULL_u0_u0, LEFT_u0_p1, NULL_u0_u0, LEFT_u0_p1);
      //    0    0   0       =>   0    0   0
      //    3        0   1   =>   3        0   1
      writeMetaState(e, LEFT_u0_p1, NULL_u0_u0, LEFT_u0_p1, NULL_u0_u0);
      //    4    1   0       =>   4    1   0
      //    0        0   0   =>   0        0   0
      writeMetaState(e, NULL_u0_u0, LEFT_p1_u0, NULL_u0_u0, LEFT_p1_u0);
      //    0    0   0       =>   3    0   1
      //    4        1   0   =>   0        0   0
      writeMetaState(e, LEFT_p1_u0, NULL_u0_u0, NULL_u0_u0, LEFT_u0_p1);
      //    3    0   1       =>   3    0   1       3:left -> 3:left
      //    3        0   1   =>   3        0   1   3:left -> 3:left
      writeMetaState(e, LEFT_u0_p1, LEFT_u0_p1, LEFT_u0_p1, LEFT_u0_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (left, right):
      //    5    1   0       =>   7    1   0
      //    4        1   0   =>   0        1   0
      writeMetaState(e, LEFT_p1_u0, RIGHT_p1_u0, RIGHT_p1_u0, LEFT_p1_u0);
      //    8    0   1       =>   5    0   1
      //    6        1   0   =>   7        1   0
      writeMetaState(e, LEFT_p1_u0, RIGHT_u0_p1, RIGHT_p1_u0, LEFT_u0_p1);
      //    7    1   0       =>   7    1   0
      //    5        0   1   =>   5        0   1
      writeMetaState(e, LEFT_u0_p1, RIGHT_p1_u0, LEFT_u0_p1, RIGHT_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (left, left):
      //    6    1   0       =>   6    1   0
      //    6        1   0   =>   6        1   0
      writeMetaState(e, LEFT_p1_u0, LEFT_p1_u0, LEFT_p1_u0, LEFT_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (right, left):
      // 3,6  =>  7,0
      //    6    0   1       =>   0    0   1       6:right -> 0:null
      //    3        0   1   =>   7        0   1   3:left  -> 7:collision
      writeMetaState(e, RIGHT_u0_p1, LEFT_u0_p1, RIGHT_u0_p1, LEFT_u0_p1);
      // 6,4  =>  6,4
      //    4    1   0       =>   4    1   0       4:left -> 4:left
      //    6        0   1   =>   6        0   1   6:right -> 6:right
      writeMetaState(e, RIGHT_u0_p1, LEFT_p1_u0, RIGHT_u0_p1, LEFT_p1_u0);
      // 4,6  =>  5,3
      //    5    0   1       =>   5    0   1       6:right -> 3:left
      //    7        1   0   =>   7        1   0   4:left  -> 5:right
      writeMetaState(e, RIGHT_p1_u0, LEFT_u0_p1, RIGHT_p1_u0, LEFT_u0_p1);
      //    2    0   1       =>   2    0   1
      //    9        1   1   =>   9        1   1
      writeMetaState(e, COLL_LR_p1_p1, STAYb_u0_p1, COLL_LR_p1_p1, STAYb_u0_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (stay, right):
      //    7    1   0       =>   7    1   0
      //    2        0   1   =>   2        1   0
      writeMetaState(e, STAYb_u0_p1, RIGHT_p1_u0, STAYb_u0_p1, RIGHT_p1_u0);
      //    8    0   1       =>   2    0   1
      //    1        0   1   =>   8        0   1
      writeMetaState(e, STAYa_u0_p1, RIGHT_u0_p1, RIGHT_u0_p1, STAYb_u0_p1);
      //    8    0   1       =>   1    0   1
      //    2        0   1   =>   8        0   1
      writeMetaState(e, STAYb_u0_p1, RIGHT_u0_p1, RIGHT_u0_p1, STAYa_u0_p1);
      //    8    0   1       =>   4    0   1
      //    3        1   0   =>   7        1   0
      writeMetaState(e, STAYa_p1_u0, RIGHT_u0_p1, RIGHT_p1_u0, STAYa_u0_p1);
      //    8    0   1       =>   2    0   1
      //    4        1   0   =>   7        1   0
      writeMetaState(e, STAYb_p1_u0, RIGHT_u0_p1, RIGHT_p1_u0, STAYb_u0_p1);
      //    7    1   0       =>   7    1   0
      //    1        0   1   =>   1        0   1
      writeMetaState(e, STAYa_u0_p1, RIGHT_p1_u0, STAYa_u0_p1, RIGHT_p1_u0);
      //    7    1   0       =>   3    1   0
      //    4        1   0   =>   7        1   0
      writeMetaState(e, STAYb_p1_u0, RIGHT_p1_u0, RIGHT_p1_u0, STAYa_p1_u0);
      //    7    1   0       =>   4    1   0
      //    3        1   0   =>   7        1   0
      writeMetaState(e, STAYa_p1_u0, RIGHT_p1_u0, RIGHT_p1_u0, STAYb_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (right, stay):
      //    1    0   1       =>   1    0   1
      //    8        0   1   =>   8        0   1
      writeMetaState(e, RIGHT_u0_p1, STAYa_u0_p1, RIGHT_u0_p1, STAYa_u0_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (stay, left):
      //    6    1   0       =>   6    1   0
      //    4        1   0   =>   4        1   0
      writeMetaState(e, STAYb_p1_u0, LEFT_p1_u0, STAYb_p1_u0, LEFT_p1_u0);
     // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (left, stay):
      //    2    0   1       =>   5    0   1
      //    6        1   0   =>   4        1   0
      writeMetaState(e, LEFT_p1_u0, STAYb_u0_p1, STAYb_p1_u0, LEFT_u0_p1);
      //    1    0   1       =>   5    0   1
      //    6        1   0   =>   3        1   0
      writeMetaState(e, LEFT_p1_u0, STAYa_u0_p1, STAYa_p1_u0, LEFT_u0_p1);
      //    3    1   0       =>   6    1   0
      //    6        1   0   =>   4        1   0
      writeMetaState(e, LEFT_p1_u0, STAYa_p1_u0, STAYb_p1_u0, LEFT_p1_u0);
      //    4    1   0       =>   6    1   0
      //    6        1   0   =>   3        1   0
      writeMetaState(e, LEFT_p1_u0, STAYb_p1_u0, STAYa_p1_u0, LEFT_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // 7:collision (left -> <- right):
      // 8:collision-to-right (right -> stay):
      // 9:collision-to-left (left -> stay):

      //----------------------------------------------------------------------------------------------------------------
      // Level Up output:
      /*
      // stay:
      // 1    0   1   =>   2
      // 2    1   0   =>   1
      // left:
      // 3    0   1   =>   4
      // 4    1   0   =>   4
      // right:
      // 5    1   0   =>   6
      // 6    0   1   =>   6
       */
      // null -> 1 (3:left <- 0:null -> 5:right)
      // 0,0  =>  5,3
      //    0    0   0       =>   3    0   1       0:null -> 3:left
      //    0        0   0   =>   5        1   0   0:null -> 5:right
      level1moveEngine.metaStateArr[metaPos(e, NULL_u0_u0, NULL_u0_u0)].levelUpOutputMetaStatePosArr[metaPos(e, NULL_u0_u0, NULL_u0_u0)] = metaPos(e, RIGHT_p1_u0, 3);
      // stay -> 1 (1:stay <- 1:stay -> 7:collision)
      // 1,0  =>  7,1
      //    0    0   0       =>   1    0   1       0:null -> 1:stay
      //    1        0   1   =>   7        1   1   1:stay -> 7:collision
      level1moveEngine.metaStateArr[metaPos(e, STAYa_u0_p1, NULL_u0_u0)].levelUpOutputMetaStatePosArr[metaPos(e, NULL_u0_u0, NULL_u0_u0)] = metaPos(e, 7, 1);
      // stay -> 1 (3:left <- 3:left -> 7:collision)
      // 3,0  =>  7,3
      //    0    0   0       =>   3    0   1       0:null -> 3:left
      //    3        0   1   =>   7        1   1   3:left -> 7:collision
      level1moveEngine.metaStateArr[metaPos(e, LEFT_u0_p1, NULL_u0_u0)].levelUpOutputMetaStatePosArr[metaPos(e, NULL_u0_u0, NULL_u0_u0)] = metaPos(e, 7, 3);
      // stay -> 1 (6:right <- 6:right -> 7:collision)
      // 6,0  =>  7,6
      //    0    0   0       =>   6    0   1       0:null -> 6:right
      //    6        0   1   =>   7        1   1   6:right -> 7:collision
      level1moveEngine.metaStateArr[metaPos(e, RIGHT_u0_p1, NULL_u0_u0)].levelUpOutputMetaStatePosArr[metaPos(e, NULL_u0_u0, NULL_u0_u0)] = metaPos(e, 7, 6);

      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      return level1moveEngine;
   }
}
