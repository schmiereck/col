package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.engine.CreateEngineService.initMetaStateArr;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.CreateEngineService.writeMetaState;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_u0_p1;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class CreateLevel0SpinMoveEngineService {

   // null:
   public final static int NULL_u0 = 0;
   // stay:
   public final static int STAYa_p1 = 1;
   public final static int STAYb_p1 = 2;
   public final static int STAYa_n1 = 3;
   public final static int STAYb_n1 = 4;
   // left:
   public final static int LEFTa_p1 = 5;
   public final static int LEFTb_p1 = 6;
   public final static int LEFTa_n1 = 7;
   public final static int LEFTb_n1 = 8;
   // right:
   public final static int RIGHTa_p1 = 9;
   public final static int RIGHTb_p1 = 10;
   public final static int RIGHTa_n1 = 11;
   public final static int RIGHTb_n1 = 12;

   public static Engine createLevel0SpinMoveEngine() {
      //----------------------------------------------------------------------------------------------------------------
      final Engine level0Engine = new Engine(1, 13);

      //----------------------------------------------------------------------------------------------------------------
      // null:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // 0    0   =>   0    0
      level0Engine.setState(NULL_u0, new State(1, nulState), NULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      // STAYa_p1   =>   STAYb_p1   =>   STAYa_p1
      level0Engine.setState(STAYa_p1, new State(1, posState), STAYb_p1);
      level0Engine.setState(STAYb_p1, new State(1, posState), STAYa_p1);
      // STAYa_n1   =>   STAYb_n1   =>   STAYa_n1
      level0Engine.setState(STAYa_n1, new State(1, negState), STAYb_n1);
      level0Engine.setState(STAYb_n1, new State(1, negState), STAYa_n1);
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
      writeMetaState(e, NULL_u0, STAYa_p1, NULL_u0, STAYa_p1);
      // 1,0  =>  1,0
      //    0    0       =>   0    0
      //    1        1   =>   1        1
      writeMetaState(e, STAYa_p1, NULL_u0, STAYa_p1, NULL_u0);
      //    3   -1       =>   3   -1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, STAYa_n1, NULL_u0, STAYa_n1);
      //    0    0       =>   0    0
      //    2        1   =>   2        1
      writeMetaState(e, STAYb_p1, NULL_u0, STAYb_p1, NULL_u0);
      //    2    1       =>   2    1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, STAYb_p1, NULL_u0, STAYb_p1);
      //    0    0       =>   0    0
      //    3       -1   =>   3       -1
      writeMetaState(e, STAYa_n1, NULL_u0, STAYa_n1, NULL_u0);
      //    1    1       =>   1    1
      //    1        1   =>   1        1
      writeMetaState(e, STAYa_p1, STAYa_p1, STAYa_p1, STAYa_p1);
      //    3   -1       =>   3   -1
      //    3       -1   =>   3       -1
      writeMetaState(e, STAYa_n1, STAYa_n1, STAYa_n1, STAYa_n1);
      //    1    1       =>   0    0
      //    3       -1   =>   0        0
      writeMetaState(e, STAYa_n1, STAYa_p1, NULL_u0, NULL_u0);
      //    3   -1       =>   0    0
      //    1        1   =>   0        0
      writeMetaState(e, STAYa_p1, STAYa_n1, NULL_u0, NULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // left:
      //    0    0       =>   6    1
      //    5        1   =>   0        0
      writeMetaState(e, LEFTa_p1, NULL_u0, NULL_u0, LEFTb_p1);
      //    0    0       =>   8   -1
      //    7       -1   =>   0        0
      writeMetaState(e, LEFTa_n1, NULL_u0, NULL_u0, LEFTb_n1);
      //    5    1       =>   5    1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, LEFTa_p1, NULL_u0, LEFTa_p1);
      //    6    1       =>   6    1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, LEFTb_p1, NULL_u0, LEFTb_p1);
      //    0    0       =>   0    0
      //    6        1   =>   6        1
      writeMetaState(e, LEFTb_p1, NULL_u0, LEFTb_p1, NULL_u0);
      //    7   -1       =>   7   -1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, LEFTa_n1, NULL_u0, LEFTa_n1);
      //    0    0       =>   0    0
      //    8       -1   =>   8       -1
      writeMetaState(e, LEFTb_n1, NULL_u0, LEFTb_n1, NULL_u0);
      //    8   -1       =>   8   -1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, LEFTb_n1, NULL_u0, LEFTb_n1);
      //    1    1       =>   5    1
      //    5        1   =>   1        1
      writeMetaState(e, LEFTa_p1, STAYa_p1, STAYa_p1, LEFTa_p1);
      //    5    1       =>   5    1
      //    1        1   =>   1        1
      writeMetaState(e, STAYa_p1, LEFTa_p1, STAYa_p1, LEFTa_p1);
      //    3   -1       =>   7   -1
      //    7       -1   =>   3       -1
      writeMetaState(e, LEFTa_n1, STAYa_n1, STAYa_n1, LEFTa_n1);
      //    7   -1       =>   7   -1
      //    3       -1   =>   3       -1
      writeMetaState(e, STAYa_n1, LEFTa_n1, LEFTa_n1, STAYa_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      //    0    0       =>   0    0
      //    9        1   =>   9        1
      writeMetaState(e, RIGHTa_p1, NULL_u0, RIGHTa_p1, NULL_u0);
      //    0    0       =>   0    0
      //   11       -1   =>  11       -1
      writeMetaState(e, RIGHTa_n1, NULL_u0, RIGHTa_n1, NULL_u0);
      //    0    0       =>   0    0
      //   12       -1   =>   12      -1
      writeMetaState(e, RIGHTb_n1, NULL_u0, RIGHTb_n1, NULL_u0);
      //    9    1       =>   0    0
      //    0        0   =>  10        1
      writeMetaState(e, NULL_u0, RIGHTa_p1, RIGHTb_p1, NULL_u0);
      //   10    1       =>  10    1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, RIGHTb_p1, NULL_u0, RIGHTb_p1);
      //   11   -1       =>   0    0
      //    0        0   =>  12       -1
      writeMetaState(e, NULL_u0, RIGHTa_n1, RIGHTb_n1, NULL_u0);
      //    9    1       =>   9    1
      //    1        1   =>   1        1
      writeMetaState(e, STAYa_p1, RIGHTa_p1, STAYa_p1, RIGHTa_p1);
      //   10    1       =>  10    1
      //    1        1   =>   1        1
      writeMetaState(e, STAYa_p1, RIGHTb_p1, STAYa_p1, RIGHTb_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (left, right):
      //    9    1       =>   5    1
      //    5        1   =>   9        1
      writeMetaState(e, LEFTa_p1, RIGHTa_p1, RIGHTa_p1, LEFTa_p1);
      //   11   -1       =>   7   -1
      //    7       -1   =>  11       -1
      writeMetaState(e, LEFTa_n1, RIGHTa_n1, RIGHTa_n1, LEFTa_n1);
      //   11   -1       =>   0    0
      //    5        1   =>   0        0
      writeMetaState(e, LEFTa_p1, RIGHTa_n1, NULL_u0, NULL_u0);
      //   10    1       =>  10    1
      //    5        1   =>   5        1
      writeMetaState(e, LEFTa_p1, RIGHTb_p1, LEFTa_p1, RIGHTb_p1);
      //    9    1       =>   9    1
      //    6        1   =>   6        1
      writeMetaState(e, LEFTb_p1, RIGHTa_p1, LEFTb_p1, RIGHTa_p1);
      //   10    1       =>  10    1
      //    6        1   =>   6        1
      writeMetaState(e, LEFTb_p1, RIGHTb_p1, LEFTb_p1, RIGHTb_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (right, left):
      //    5    1       =>   5    1
      //    9        1   =>   9        1
      writeMetaState(e, RIGHTa_p1, LEFTa_p1, RIGHTa_p1, LEFTa_p1);
      //    7   -1       =>   7   -1
      //   11       -1   =>  11       -1
      writeMetaState(e, RIGHTa_n1, LEFTa_n1, RIGHTa_n1, LEFTa_n1);
      //    5    1       =>   0    0
      //   11       -1   =>   0        0
      writeMetaState(e, RIGHTa_n1, LEFTa_p1, NULL_u0, NULL_u0);
      //   10    1       =>  10    1
      //    9        1   =>   9        1
      writeMetaState(e, RIGHTa_p1, RIGHTb_p1, RIGHTa_p1, RIGHTb_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (right, stay):
      //    2    1       =>   2    1
      //    9        1   =>   9        1
      writeMetaState(e, RIGHTa_p1, STAYb_p1, RIGHTa_p1, STAYb_p1);
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
      e0.metaStateArr[metaPos(e0, STAYa_p1, NULL_u0)].levelUpOutputMetaStatePosArr[metaPos(e1, NULL_u0_u0, NULL_u0_u0)] = metaPos(e1, STAYa_p1_u0, STAYa_u0_p1);
      e0.metaStateArr[metaPos(e0, STAYb_p1, NULL_u0)].levelUpOutputMetaStatePosArr[metaPos(e1, NULL_u0_u0, NULL_u0_u0)] = metaPos(e1, STAYa_p1_u0, STAYa_u0_p1);
      // right -> (right, right)
      // 7,0  =>  5,6
      //    0    0       =>   6    0   1
      //    7        1   =>   5        1   0
      //    NULL_u0      ->   RIGHT_u0_p1
      //       RIGHT_p1  ->      RIGHT_p1_u0
      e0.metaStateArr[metaPos(e0, RIGHTa_p1, NULL_u0)].levelUpOutputMetaStatePosArr[metaPos(e1, NULL_u0_u0, NULL_u0_u0)] = metaPos(e1, RIGHTa_p1_u0, RIGHTa_u0_p1);
      e0.metaStateArr[metaPos(e0, RIGHTb_p1, NULL_u0)].levelUpOutputMetaStatePosArr[metaPos(e1, NULL_u0_u0, NULL_u0_u0)] = metaPos(e1, RIGHTa_p1_u0, RIGHTa_u0_p1);
      // left -> (left, left)
      // 7,0  =>  5,6
      //    0    0       =>   6    0   1
      //    7        1   =>   5        1   0
      //    NULL_u0      ->   LEFT_u0_p1
      //       LEFT_p1   ->      LEFT_p1_u0
      e0.metaStateArr[metaPos(e0, LEFTa_p1, NULL_u0)].levelUpOutputMetaStatePosArr[metaPos(e1, NULL_u0_u0, NULL_u0_u0)] = metaPos(e1, LEFTa_p1_u0, LEFTa_u0_p1);
      e0.metaStateArr[metaPos(e0, LEFTb_p1, NULL_u0)].levelUpOutputMetaStatePosArr[metaPos(e1, NULL_u0_u0, NULL_u0_u0)] = metaPos(e1, LEFTa_p1_u0, LEFTa_u0_p1);
      //----------------------------------------------------------------------------------------------------------------
   }
}
