package de.schmiereck.col.services.engine.dynaMove;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.engine.CreateEngineService.initMetaStateArr;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.CreateEngineService.writeMetaState;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel1DynamicMoveEngineService.LEFT_p1_u0;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel1DynamicMoveEngineService.LEFT_u0_p1;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel1DynamicMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel1DynamicMoveEngineService.RIGHT_p1_u0;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel1DynamicMoveEngineService.RIGHT_u0_p1;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel1DynamicMoveEngineService.STAY_p1_u0;
import static de.schmiereck.col.services.engine.dynaMove.CreateLevel1DynamicMoveEngineService.STAY_u0_p1;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class CreateLevel0DynamicMoveEngineService {

   // null:
   public final static int NULL_u0 = 0;
   // stay:
   public final static int STAY_p1 = 1;
   public final static int STAY_n1 = 2;
   // left:
   public final static int LEFTa_p1 = 3;
   public final static int LEFTb_p1 = 4;
   public final static int LEFTa_n1 = 5;
   public final static int LEFTb_n1 = 6;
   // right:
   public final static int RIGHTa_p1 = 7;
   public final static int RIGHTb_p1 = 8;
   public final static int RIGHTa_n1 = 9;
   public final static int RIGHTb_n1 = 10;

   public static Engine createLevel0DynamicMoveEngine() {
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
      level0Engine.setState(LEFTa_p1, new State(1, posState), LEFTa_p1);
      level0Engine.setState(LEFTb_p1, new State(1, posState), LEFTa_p1);
      // 0    0       =>   4   -1
      // 4       -1   =>   0        0
      level0Engine.setState(LEFTa_n1, new State(1, negState), LEFTa_p1);
      level0Engine.setState(LEFTb_n1, new State(1, negState), LEFTa_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      // 5    1       =>   0    0
      // 0        0   =>   5        1
      level0Engine.setState(RIGHTa_p1, new State(1, posState), RIGHTa_p1);
      level0Engine.setState(RIGHTb_p1, new State(1, posState), RIGHTa_p1);
      // 6   -1       =>   0    0
      // 0        0   =>   6       -1
      level0Engine.setState(RIGHTa_n1, new State(1, negState), RIGHTa_n1);
      level0Engine.setState(RIGHTb_n1, new State(1, negState), RIGHTa_n1);
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
      //    0    0       =>   4    1
      //    3        1   =>   0        0
      writeMetaState(e, LEFTa_p1, NULL_u0, NULL_u0, LEFTb_p1);
      //    0    0       =>   4   -1
      //    4       -1   =>   0        0
      writeMetaState(e, LEFTa_n1, NULL_u0, NULL_u0, LEFTb_n1);
      //    3    1       =>   3    1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, LEFTa_p1, NULL_u0, LEFTa_p1);
      //    4    1       =>   4    1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, LEFTb_p1, LEFTb_p1, NULL_u0);
      //    0    0       =>   0    0
      //    4        1   =>   4        1
      writeMetaState(e, LEFTb_p1, NULL_u0, LEFTb_p1, NULL_u0);
      //    4   -1       =>   4   -1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, LEFTa_n1, NULL_u0, LEFTa_n1);
      //    0    0       =>   0    0
      //    6       -1   =>   6       -1
      writeMetaState(e, LEFTb_n1, NULL_u0, LEFTb_n1, NULL_u0);
      //    6   -1       =>   6   -1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, LEFTb_n1, NULL_u0, LEFTb_n1);
      //    1    1       =>   3    1
      //    3        1   =>   1        1
      writeMetaState(e, LEFTa_p1, STAY_p1, STAY_p1, LEFTa_p1);
      //    3    1       =>   3    1
      //    1        1   =>   1        1
      writeMetaState(e, LEFTa_p1, STAY_p1, LEFTa_p1, STAY_p1);
      //    1    1       =>   4   -1
      //    4       -1   =>   1        1
      writeMetaState(e, LEFTa_n1, STAY_p1, STAY_p1, LEFTa_n1);
      //    4   -1       =>   4   -1
      //    1        1   =>   1        1
      writeMetaState(e, LEFTa_n1, STAY_p1, LEFTa_n1, STAY_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      //    0    0       =>   0    0
      //    5        1   =>   5        1
      writeMetaState(e, RIGHTa_p1, NULL_u0, RIGHTa_p1, NULL_u0);
      //    0    0       =>   0    0
      //    6       -1   =>   6       -1
      writeMetaState(e, RIGHTa_n1, NULL_u0, RIGHTa_n1, NULL_u0);
      //    0    0       =>   0    0
      //   10       -1   =>   10      -1
      writeMetaState(e, RIGHTb_n1, NULL_u0, RIGHTb_n1, NULL_u0);
      //    5    1       =>   0    0
      //    0        0   =>   8        1
      writeMetaState(e, NULL_u0, RIGHTa_p1, RIGHTb_p1, NULL_u0);
      //    8    1       =>   8    1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, RIGHTb_p1, NULL_u0, RIGHTb_p1);
      //    6   -1       =>   0    0
      //    0        0   =>   6       -1
      writeMetaState(e, NULL_u0, RIGHTa_n1, RIGHTb_n1, NULL_u0);
      //    7    1       =>   7    1
      //    1        1   =>   1        1
      writeMetaState(e, STAY_p1, RIGHTa_p1, STAY_p1, RIGHTa_p1);
      //    8    1       =>   8    1
      //    1        1   =>   1        1
      writeMetaState(e, STAY_p1, RIGHTb_p1, STAY_p1, RIGHTb_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (right -> <- left):
      //    5    1       =>   3    1
      //    3        1   =>   5        1
      writeMetaState(e, LEFTa_p1, RIGHTa_p1, RIGHTa_p1, LEFTa_p1);
      //    9   -1       =>   6   -1
      //    5       -1   =>   4       -1
      writeMetaState(e, LEFTa_n1, RIGHTa_n1, RIGHTa_n1, LEFTa_n1);
      //    6   -1       =>   0    0
      //    3        1   =>   0        0
      writeMetaState(e, LEFTa_p1, RIGHTa_n1, NULL_u0, NULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (left -> <- right):
      //    3    1       =>   5    1
      //    7        1   =>   3        1
      writeMetaState(e, RIGHTa_p1, LEFTa_p1, RIGHTa_p1, LEFTa_p1);
      //    5   -1       =>   4   -1
      //    9       -1   =>   6       -1
      writeMetaState(e, RIGHTa_n1, LEFTa_n1, RIGHTa_n1, LEFTa_n1);
      //    3    1       =>   0    0
      //    9       -1   =>   0        0
      writeMetaState(e, RIGHTa_n1, LEFTa_p1, NULL_u0, NULL_u0);

      //    8    1       =>   8    1
      //    3        1   =>   3        1
      writeMetaState(e, LEFTa_p1, RIGHTb_p1, LEFTa_p1, RIGHTb_p1);
      //    7    1       =>   7    1
      //    4        1   =>   4        1
      writeMetaState(e, LEFTb_p1, RIGHTa_p1, LEFTb_p1, RIGHTa_p1);
      //    8    1       =>   8    1
      //    7        1   =>   7        1
      writeMetaState(e, RIGHTa_p1, RIGHTb_p1, RIGHTa_p1, RIGHTb_p1);
      //    8    1       =>   8    1
      //    4        1   =>   4        1
      writeMetaState(e, LEFTb_p1, RIGHTb_p1, LEFTb_p1, RIGHTb_p1);

      //----------------------------------------------------------------------------------------------------------------
      return level0Engine;
   }

   public static void initLevelUpOutputMetaStates(final Engine e0, final Engine e1) {
      //----------------------------------------------------------------------------------------------------------------
      // Up:
      // stay -> (stay, stay)
      // 1,0  =>  2,1
      //    0    0       =>   1    0   1
      //    1        1   =>   2        1   0
      //    NULL_u0      ->   STAY_u0_p1
      //       STAY_p1   ->      STAY_p1_u0
      e0.metaStateArr[metaPos(e0, STAY_p1, NULL_u0)].levelUpOutputMetaStatePosArr[metaPos(e1, NULL_u0_u0, NULL_u0_u0)] = metaPos(e1, STAY_p1_u0, STAY_u0_p1);
      // right -> (right, right)
      // 7,0  =>  5,6
      //    0    0       =>   6    0   1
      //    7        1   =>   5        1   0
      //    NULL_u0      ->   RIGHT_u0_p1
      //       RIGHT_p1  ->      RIGHT_p1_u0
      e0.metaStateArr[metaPos(e0, RIGHTa_p1, NULL_u0)].levelUpOutputMetaStatePosArr[metaPos(e1, NULL_u0_u0, NULL_u0_u0)] = metaPos(e1, RIGHT_p1_u0, RIGHT_u0_p1);
      // left -> (left, left)
      // 7,0  =>  5,6
      //    0    0       =>   6    0   1
      //    7        1   =>   5        1   0
      //    NULL_u0      ->   LEFT_u0_p1
      //       LEFT_p1   ->      LEFT_p1_u0
      e0.metaStateArr[metaPos(e0, LEFTa_p1, NULL_u0)].levelUpOutputMetaStatePosArr[metaPos(e1, NULL_u0_u0, NULL_u0_u0)] = metaPos(e1, LEFT_p1_u0, LEFT_u0_p1);
      //----------------------------------------------------------------------------------------------------------------
   }
}
