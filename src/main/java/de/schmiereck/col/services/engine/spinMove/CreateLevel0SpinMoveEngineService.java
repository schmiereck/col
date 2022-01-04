package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.State.NULL_pos;
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
   public final static int NULL_u0 = NULL_pos;
   // a:
   // stay:
   public final static int STAYa_p1 = 1;
   public final static int STAYa_n1 = 2;
   // left:
   public final static int LEFTa_p1 = 3;
   public final static int LEFTa_n1 = 4;
   // right:
   public final static int RIGHTa_p1 = 5;
   public final static int RIGHTa_n1 = 6;

   public static Engine createLevel0SpinMoveEngine() {
      //----------------------------------------------------------------------------------------------------------------
      final Engine level0Engine = new Engine(1, 7);

      //----------------------------------------------------------------------------------------------------------------
      // null:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // 0    0   =>   0    0
      level0Engine.setState(NULL_u0, new State(1, nulState), NULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      // x        1   =>   x        1
      level0Engine.setState(STAYa_p1, new State(1, posState), STAYa_p1);
      // x       -1   =>   x       -1
      level0Engine.setState(STAYa_n1, new State(1, negState), STAYa_n1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // left:
      // x        1   =>   x        1
      level0Engine.setState(LEFTa_p1, new State(1, posState), LEFTa_p1);
      // x       -1   =>   x       -1
      level0Engine.setState(LEFTa_n1, new State(1, negState), LEFTa_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      // x        1   =>   x        1
      level0Engine.setState(RIGHTa_p1, new State(1, posState), RIGHTa_p1);
      // x       -1   =>   x       -1
      level0Engine.setState(RIGHTa_n1, new State(1, negState), RIGHTa_n1);
      //----------------------------------------------------------------------------------------------------------------
      initMetaStateArr(level0Engine, false);
      //initOutputMetaStatePos(level1dynamicEngine);

      final Engine e = level0Engine;

      //----------------------------------------------------------------------------------------------------------------
      // outputMetaState:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // null:
      // 0,0  =>  0,0
      //    0    0       =>   0    0
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, NULL_u0,   NULL_u0, NULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay & null:
      // 0,1  =>  0,1
      //    1    1       =>   1    1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, STAYa_p1,   NULL_u0, STAYa_p1);
      // 1,0  =>  1,0
      //    0    0       =>   0    0
      //    1        1   =>   1        1
      writeMetaState(e, STAYa_p1, NULL_u0,   STAYa_p1, NULL_u0);
      //    3   -1       =>   3   -1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, STAYa_n1,   NULL_u0, STAYa_n1);
      //    0    0       =>   0    0
      //    3       -1   =>   3       -1
      writeMetaState(e, STAYa_n1, NULL_u0,   STAYa_n1, NULL_u0);
      //    1    1       =>   1    1
      //    1        1   =>   1        1
      writeMetaState(e, STAYa_p1, STAYa_p1,   STAYa_p1, STAYa_p1);
      //    3   -1       =>   3   -1
      //    3       -1   =>   3       -1
      writeMetaState(e, STAYa_n1, STAYa_n1,   STAYa_n1, STAYa_n1);
      //    1    1       =>   0    0
      //    3       -1   =>   0        0
      writeMetaState(e, STAYa_n1, STAYa_p1,   NULL_u0, NULL_u0);
      //    3   -1       =>   0    0
      //    1        1   =>   0        0
      writeMetaState(e, STAYa_p1, STAYa_n1,   NULL_u0, NULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // left & null:
      //    0    0       =>   6    1
      //    5        1   =>   0        0
      writeMetaState(e, LEFTa_p1, NULL_u0,   LEFTa_p1, NULL_u0, -1);
      //    0    0       =>   6   -1
      //    5       -1   =>   0        0
      writeMetaState(e, LEFTa_n1, NULL_u0,   LEFTa_n1, NULL_u0, -1);
      //    5    1       =>   5    1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, LEFTa_p1,   NULL_u0, LEFTa_p1);
      //    7   -1       =>   7   -1
      //    0        0   =>   0        0
      writeMetaState(e, NULL_u0, LEFTa_n1,   NULL_u0, LEFTa_n1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right & null:
      //    0    0       =>   0    0
      //    9        1   =>   9        1
      writeMetaState(e, RIGHTa_p1, NULL_u0,   RIGHTa_p1, NULL_u0, +1);
      //    0    0       =>   0    0
      //   11       -1   =>  11       -1
      writeMetaState(e, RIGHTa_n1, NULL_u0,   RIGHTa_n1, NULL_u0, +1);
      //    9    1       =>   0    1
      //    0        0   =>  10        0
      writeMetaState(e, NULL_u0, RIGHTa_p1,   NULL_u0, RIGHTa_p1);
      //   11   -1       =>   0   -1
      //    0        0   =>  12        0
      writeMetaState(e, NULL_u0, RIGHTa_n1,   NULL_u0, RIGHTa_n1);
      //    9    1       =>   9    1
      //    1        1   =>   1        1
      writeMetaState(e, STAYa_p1, RIGHTa_p1,   STAYa_p1, RIGHTa_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (left & stay):
      //    1    1       =>   5    1
      //    5        1   =>   1        1
      writeMetaState(e, LEFTa_p1, STAYa_p1,   STAYa_p1, LEFTa_p1);
      //    5    1       =>   5    1
      //    1        1   =>   1        1
      writeMetaState(e, STAYa_p1, LEFTa_p1,   STAYa_p1, LEFTa_p1);
      //    3   -1       =>   7   -1
      //    7       -1   =>   3       -1
      writeMetaState(e, LEFTa_n1, STAYa_n1,   STAYa_n1, LEFTa_n1);
      //    7   -1       =>   7   -1
      //    3       -1   =>   3       -1
      writeMetaState(e, STAYa_n1, LEFTa_n1,   LEFTa_n1, STAYa_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (right & stay):
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (left, right):
      //    9    1       =>   5    1
      //    5        1   =>   9        1
      writeMetaState(e, LEFTa_p1, RIGHTa_p1,   RIGHTa_p1, LEFTa_p1);
      //   11   -1       =>   7   -1
      //    7       -1   =>  11       -1
      writeMetaState(e, LEFTa_n1, RIGHTa_n1,   RIGHTa_n1, LEFTa_n1);
      //   11   -1       =>   0    0
      //    5        1   =>   0        0
      writeMetaState(e, LEFTa_p1, RIGHTa_n1,   NULL_u0, NULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (right, left):
      //    5    1       =>   5    1
      //    9        1   =>   9        1
      writeMetaState(e, RIGHTa_p1, LEFTa_p1,   RIGHTa_p1, LEFTa_p1);
      //    7   -1       =>   7   -1
      //   11       -1   =>  11       -1
      writeMetaState(e, RIGHTa_n1, LEFTa_n1,   RIGHTa_n1, LEFTa_n1);
      //    5    1       =>   0    0
      //   11       -1   =>   0        0
      writeMetaState(e, RIGHTa_n1, LEFTa_p1,   NULL_u0, NULL_u0);
      //----------------------------------------------------------------------------------------------------------------
      // Probability:
      // Überlagerung dieser Zustände mit Wahrscheinlichkeiten:
      // LEFTa_p1 STAYa_p1 RIGHTa_p1
      // LEFTa_n1 STAYa_n1 RIGHTa_n1
      // NULL_u0  NULL_u0  NULL_u0
      final int probArr[][] = {
              { metaPos(e, STAYa_p1, NULL_u0), metaPos(e, LEFTa_p1, NULL_u0), metaPos(e, RIGHTa_p1, NULL_u0) },
              { metaPos(e, STAYa_n1, NULL_u0), metaPos(e, LEFTa_n1, NULL_u0), metaPos(e, RIGHTa_n1, NULL_u0) },
      };
      //----------------------------------------------------------------------------------------------------------------
      return level0Engine;
   }
}
