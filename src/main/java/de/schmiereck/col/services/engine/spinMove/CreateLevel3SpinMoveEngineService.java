package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.State.NULL_pos;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.engine.CreateEngineService.USE_check;
import static de.schmiereck.col.services.engine.CreateEngineService.initMetaStateArr;
import static de.schmiereck.col.services.engine.CreateEngineService.writeMetaState;
import static de.schmiereck.col.services.engine.spinMove.CreateLevelService.calcLeftMetaStatePosArr;
import static de.schmiereck.col.services.engine.spinMove.CreateLevelService.calcRightMetaStatePosArr;
import static de.schmiereck.col.services.engine.spinMove.CreateLevelService.calcStayMetaStatePosArr;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

import java.util.Arrays;

public class CreateLevel3SpinMoveEngineService {

   // null:
   public final static int NULL_u0_u0_u0_u0 = NULL_pos;
   // a:
   // stay:
   public final static int STAYa_p1_u0_u0_u0 = 1;
   public final static int STAYa_u0_p1_u0_u0 = 2;
   public final static int STAYa_u0_u0_p1_u0 = 3;
   public final static int STAYa_u0_u0_u0_p1 = 4;
   // left:
   public final static int LEFTa_p1_u0_u0_u0 = 5;
   public final static int LEFTa_u0_p1_u0_u0 = 6;
   public final static int LEFTa_u0_u0_p1_u0 = 7;
   public final static int LEFTa_u0_u0_u0_p1 = 8;
   // right:
   public final static int RIGHTa_p1_u0_u0_u0 = 9;
   public final static int RIGHTa_u0_p1_u0_u0 = 10;
   public final static int RIGHTa_u0_u0_p1_u0 = 11;
   public final static int RIGHTa_u0_u0_u0_p1 = 12;

   public static Engine createLevel3SpinMoveEngine() {
      //----------------------------------------------------------------------------------------------------------------
      final Engine e = new Engine(4, 13);

      //----------------------------------------------------------------------------------------------------------------
      // null:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      e.setState(NULL_u0_u0_u0_u0, new State(nulState, nulState, nulState, nulState), NULL_u0_u0_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      e.setState(STAYa_p1_u0_u0_u0, new State(posState, nulState, nulState, nulState), STAYa_p1_u0_u0_u0);
      e.setState(STAYa_u0_p1_u0_u0, new State(nulState, posState, nulState, nulState), STAYa_u0_p1_u0_u0);
      e.setState(STAYa_u0_u0_p1_u0, new State(nulState, nulState, posState, nulState), STAYa_u0_u0_p1_u0);
      e.setState(STAYa_u0_u0_u0_p1, new State(nulState, nulState, nulState, posState), STAYa_u0_u0_u0_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // left:
      e.setState(LEFTa_p1_u0_u0_u0, new State(posState, nulState, nulState, nulState), LEFTa_p1_u0_u0_u0);
      e.setState(LEFTa_u0_p1_u0_u0, new State(nulState, posState, nulState, nulState), LEFTa_u0_p1_u0_u0);
      e.setState(LEFTa_u0_u0_p1_u0, new State(nulState, nulState, posState, nulState), LEFTa_u0_u0_p1_u0);
      e.setState(LEFTa_u0_u0_u0_p1, new State(nulState, nulState, nulState, posState), LEFTa_u0_u0_u0_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      e.setState(RIGHTa_p1_u0_u0_u0, new State(posState, nulState, nulState, nulState), RIGHTa_p1_u0_u0_u0);
      e.setState(RIGHTa_u0_p1_u0_u0, new State(nulState, posState, nulState, nulState), RIGHTa_u0_p1_u0_u0);
      e.setState(RIGHTa_u0_u0_p1_u0, new State(nulState, nulState, posState, nulState), RIGHTa_u0_u0_p1_u0);
      e.setState(RIGHTa_u0_u0_u0_p1, new State(nulState, nulState, nulState, posState), RIGHTa_u0_u0_u0_p1);
      //----------------------------------------------------------------------------------------------------------------
      initMetaStateArr(e, true);

      //----------------------------------------------------------------------------------------------------------------
      // outputMetaState:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // null:
      //    x    0   0   0           =>   x    0   0   0
      //    x        0   0   0       =>   x        0   0   0
      //    x            0   0   0   =>   x            0   0   0
      writeMetaState(e, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0,   NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      final int[] stayMetaStatePosArr = { STAYa_p1_u0_u0_u0, STAYa_u0_p1_u0_u0, STAYa_u0_u0_p1_u0, STAYa_u0_u0_u0_p1 };
      calcStayMetaStatePosArr(e, stayMetaStatePosArr);
      //    x    0   0   0   0               =>   x    0   0   0   0
      //    x        0   0   0   0           =>   x        0   0   0   0
      //    x            0   0   0   0       =>   x            0   1   0   0
      //    x                1   0   0   0   =>   x                0   0   0   0
      writeMetaState(e, STAYa_p1_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0,   NULL_u0_u0_u0_u0, STAYa_u0_p1_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0);
      //    x    0   0   0   0               =>   x    0   0   0   0
      //    x        0   0   0   0           =>   x        0   0   0   0
      //    x            0   0   0   0       =>   x            0   0   1   0
      //    x                0   1   0   0   =>   x                0   0   0   0
      writeMetaState(e, STAYa_u0_p1_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0,   NULL_u0_u0_u0_u0, STAYa_u0_u0_p1_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0);
      //    x    0   0   0   0               =>   x    0   0   0   0
      //    x        0   0   0   0           =>   x        0   0   0   0
      //    x            0   0   0   0       =>   x            0   0   0   1
      //    x                0   0   1   0   =>   x                0   0   0   0
      writeMetaState(e, STAYa_u0_u0_p1_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0,   NULL_u0_u0_u0_u0, STAYa_u0_u0_u0_p1, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0);
      //    x    0   0   0   0               =>   x    0   0   0   0   .   .   .   .
      //    x        0   0   0   0           =>   x        0   0   0   0   .   .   .   .
      //    x            0   0   0   0       =>   x            0   0   0   0  (1)  .   .   .
      //    x                0   0   0   1   =>   x                0   0   0   0   .   .   .   .
      writeMetaState(e, STAYa_u0_u0_u0_p1, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0,   NULL_u0_u0_u0_u0, STAYa_p1_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, +4);

      //    x    0   0   0   0               =>   x    0   0   0   0
      //    x        0   0   0   0           =>   x        0   1   0   0
      //    x            1   0   0   0       =>   x            0   0   0   0
      //    x                0   0   0   0   =>   x                0   0   0   0
      writeMetaState(e, NULL_u0_u0_u0_u0, STAYa_p1_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0,   NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_u0_p1_u0_u0, NULL_u0_u0_u0_u0);
      //    x    0   0   0   0               =>   x    0   0   0   0
      //    x        0   0   0   0           =>   x        0   0   1   0
      //    x            0   1   0   0       =>   x            0   0   0   0
      //    x                0   0   0   0   =>   x                0   0   0   0
      writeMetaState(e, NULL_u0_u0_u0_u0, STAYa_u0_p1_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0,   NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_u0_u0_p1_u0, NULL_u0_u0_u0_u0);
      //    x    0   0   0   0               =>   x    0   0   0   0
      //    x        0   0   0   0           =>   x        0   0   0   1
      //    x            0   0   1   0       =>   x            0   0   0   0
      //    x                0   0   0   0   =>   x                0   0   0   0
      writeMetaState(e, NULL_u0_u0_u0_u0, STAYa_u0_u0_p1_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0,   NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_u0_u0_u0_p1, NULL_u0_u0_u0_u0);
      //    x    0   0   0   0               =>   x    0   0   0   0   .   .   .   .
      //    x        0   0   0   0           =>   x        0   0   0   0  (1)  .   .   .
      //    x            0   0   0   1       =>   x            0   0   0   0   .   .   .   .
      //    x                0   0   0   0   =>   x                0   0   0   0   .   .   .   .
      writeMetaState(e, NULL_u0_u0_u0_u0, STAYa_u0_u0_u0_p1, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0,   NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_p1_u0_u0_u0, NULL_u0_u0_u0_u0, +4);

      //    x    0   0   0   0               =>   x    0   1   0   0
      //    x        1   0   0   0           =>   x        0   0   0   0
      //    x            0   0   0   0       =>   x            0   0   0   0
      //    x                0   0   0   0   =>   x                0   0   0   0
      writeMetaState(e, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_p1_u0_u0_u0, NULL_u0_u0_u0_u0,   NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_u0_p1_u0_u0);
      //    x    0   0   0   0               =>   x    0   0   1   0
      //    x        0   1   0   0           =>   x        0   0   0   0
      //    x            0   0   0   0       =>   x            0   0   0   0
      //    x                0   0   0   0   =>   x                0   0   0   0
      writeMetaState(e, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_u0_p1_u0_u0, NULL_u0_u0_u0_u0,   NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_u0_u0_p1_u0);
      //    x    0   0   0   0               =>   x    0   0   0   1
      //    x        0   0   1   0           =>   x        0   0   0   0
      //    x            0   0   0   0       =>   x            0   0   0   0
      //    x                0   0   0   0   =>   x                0   0   0   0
      writeMetaState(e, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_u0_u0_p1_u0, NULL_u0_u0_u0_u0,   NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_u0_u0_u0_p1);
      //    x    0   0   0   0               =>   x    0   0   0   0  (1)  .   .   .
      //    x        0   0   0   1           =>   x        0   0   0   0   .   .   .   .
      //    x            0   0   0   0       =>   x            0   0   0   0   .   .   .   .
      //    x                0   0   0   0   =>   x                0   0   0   0   .   .   .   .
      writeMetaState(e, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_u0_u0_u0_p1, NULL_u0_u0_u0_u0,   NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_p1_u0_u0_u0, +4);

      //    x    1   0   0   0               =>   x    .   .   .   .   0   0   0   0
      //    x        0   0   0   0           =>   x        .   .   .   .   0   0   0   0
      //    x            0   0   0   0       =>   x            .   .   .   .   0   0   0   0
      //    x                0   0   0   0   =>   x                .  (1)  .   .   0   0   0   0
      writeMetaState(e, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_p1_u0_u0_u0,   STAYa_u0_p1_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, -4);
      //    x    0   1   0   0               =>   x    .   .   .   .   0   0   0   0
      //    x        0   0   0   0           =>   x        .   .   .   .   0   0   0   0
      //    x            0   0   0   0       =>   x            .   .   .   .   0   0   0   0
      //    x                0   0   0   0   =>   x                .   .  (1)  .   0   0   0   0
      writeMetaState(e, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_u0_p1_u0_u0,   STAYa_u0_u0_p1_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, -4);
      //    x    0   0   1   0               =>   x    .   .   .   .   0   0   0   0
      //    x        0   0   0   0           =>   x        .   .   .   .   0   0   0   0
      //    x            0   0   0   0       =>   x            .   .   .   .   0   0   0   0
      //    x                0   0   0   0   =>   x                .   .   .  (1)  0   0   0   0
      writeMetaState(e, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_u0_u0_p1_u0,   STAYa_u0_u0_u0_p1, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, -4);
      //    x    0   0   0   1               =>   x    .   .   .   .   0   0   0   0
      //    x        0   0   0   0           =>   x        .   .   .   .   0   0   0   0
      //    x            0   0   0   0       =>   x            .   .   .   .   0   0   0   0
      //    x                0   0   0   0   =>   x                .   .   .   .   1   0   0   0
      writeMetaState(e, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, STAYa_u0_u0_u0_p1,   STAYa_p1_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      final int[] leftMetaStatePosArr = { LEFTa_p1_u0_u0_u0, LEFTa_u0_p1_u0_u0, LEFTa_u0_u0_p1_u0, LEFTa_u0_u0_u0_p1 };
      calcLeftMetaStatePosArr(e, leftMetaStatePosArr);
      /*
      // left:
      //    x    0   0   0           =>   x    0   0   0
      //    x        0   0   0       =>   x        1   0   0
      //    x            1   0   0   =>   x            0   0   0
      writeMetaState(e, LEFTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, LEFTa_p1_u0_u0, NULL_u0_u0_u0);
      //    x    0   0   0           =>   x    0   0   0
      //    x        0   0   0       =>   x        0   1   0
      //    x            0   1   0   =>   x            0   0   0
      writeMetaState(e, LEFTa_u0_p1_u0, NULL_u0_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, LEFTa_u0_p1_u0, NULL_u0_u0_u0);
      //    x    0   0   0           =>   x    0   0   0
      //    x        0   0   0       =>   x        0   0   1
      //    x            0   0   1   =>   x            0   0   0
      writeMetaState(e, LEFTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, LEFTa_u0_u0_p1, NULL_u0_u0_u0);

      //    x    0   0   0           =>   x    1   0   0
      //    x        1   0   0       =>   x        0   0   0
      //    x            0   0   0   =>   x            0   0   0
      writeMetaState(e, NULL_u0_u0_u0, LEFTa_p1_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, NULL_u0_u0_u0, LEFTa_p1_u0_u0);
      //    x    0   0   0           =>   x    0   1   0
      //    x        0   1   0       =>   x        0   0   0
      //    x            0   0   0   =>   x            0   0   0
      writeMetaState(e, NULL_u0_u0_u0, LEFTa_u0_p1_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, NULL_u0_u0_u0, LEFTa_u0_p1_u0);
      //    x    0   0   0           =>   x    0   0   1
      //    x        0   0   1       =>   x        0   0   0
      //    x            0   0   0   =>   x            0   0   0
      writeMetaState(e, NULL_u0_u0_u0, LEFTa_u0_u0_p1, NULL_u0_u0_u0,   NULL_u0_u0_u0, NULL_u0_u0_u0, LEFTa_u0_u0_p1);

      //    x    1   0   0           =>   x    .   .   .   0   0   0
      //    x        0   0   0       =>   x        .   .   .   0   0   0
      //    x            0   0   0   =>   x           (1)  .   .   0   0   0
      writeMetaState(e, NULL_u0_u0_u0, NULL_u0_u0_u0, LEFTa_p1_u0_u0,   LEFTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0, -3);
      //    x    0   1   0           =>   x    .   .   .   0   0   0
      //    x        0   0   0       =>   x        .   .   .   0   0   0
      //    x            0   0   0   =>   x            .  (1)  .   0   0   0
      writeMetaState(e, NULL_u0_u0_u0, NULL_u0_u0_u0, LEFTa_u0_p1_u0,   LEFTa_u0_p1_u0, NULL_u0_u0_u0, NULL_u0_u0_u0, -3);
      //    x    0   0   1           =>   x    .   .   .   0   0   0
      //    x        0   0   0       =>   x        .   .   .   0   0   0
      //    x            0   0   0   =>   x            .   .  (1)  0   0   0
      writeMetaState(e, NULL_u0_u0_u0, NULL_u0_u0_u0, LEFTa_u0_u0_p1,   LEFTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0, -3);
      */
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      final int[] rightMetaStatePosArr = { RIGHTa_p1_u0_u0_u0, RIGHTa_u0_p1_u0_u0, RIGHTa_u0_u0_p1_u0, RIGHTa_u0_u0_u0_p1 };
      calcRightMetaStatePosArr(e, rightMetaStatePosArr);
      /*
      //    x    0   0   0           =>   x    0   0   0  (1)  .   .
      //    x        0   0   0       =>   x        0   0   0   .   .   .
      //    x            1   0   0   =>   x            0   0   0   .   .   .
      writeMetaState(e, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_p1_u0_u0, +3);
      //    x    0   0   0           =>   x    0   0   0   .  (1)  .
      //    x        0   0   0       =>   x        0   0   0   .   .   .
      //    x            0   1   0   =>   x            0   0   0   .   .   .
      writeMetaState(e, RIGHTa_u0_p1_u0, NULL_u0_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_u0_p1_u0, +3);
      //    x    0   0   0           =>   x    0   0   0   .   .  (1)
      //    x        0   0   0       =>   x        0   0   0   .   .   .
      //    x            0   0   1   =>   x            0   0   0   .   .   .
      writeMetaState(e, RIGHTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_u0_u0_p1, +3);

      //    x    0   0   0           =>   x    0   0   0
      //    x        1   0   0       =>   x        0   0   0
      //    x            0   0   0   =>   x            1   0   0
      writeMetaState(e, NULL_u0_u0_u0, RIGHTa_p1_u0_u0, NULL_u0_u0_u0,   RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0);
      //    x    0   0   0           =>   x    0   0   0   .   .   .
      //    x        0   1   0       =>   x        0   0   0   .   .   .
      //    x            0   0   0   =>   x            0   1   0   .   .   .
      writeMetaState(e, NULL_u0_u0_u0, RIGHTa_u0_p1_u0, NULL_u0_u0_u0,   RIGHTa_u0_p1_u0, NULL_u0_u0_u0, NULL_u0_u0_u0);
      //    x    0   0   0           =>   x    0   0   0   .   .   .
      //    x        0   0   1       =>   x        0   0   0   .   .   .
      //    x            0   0   0   =>   x            0   0   1   .   .   .
      writeMetaState(e, NULL_u0_u0_u0, RIGHTa_u0_u0_p1, NULL_u0_u0_u0,   RIGHTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0);

      //    x    1   0   0           =>   x    .   .   .   0   0   0
      //    x        0   0   0       =>   x        .   .   .   1   0   0
      //    x            0   0   0   =>   x            .   .   .   0   0   0
      writeMetaState(e, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_p1_u0_u0,   NULL_u0_u0_u0, RIGHTa_p1_u0_u0, NULL_u0_u0_u0);
      //    x    0   1   0           =>   x    .   .   .   0   0   0
      //    x        0   0   0       =>   x        .   .   .   0   1   0
      //    x            0   0   0   =>   x            .   .   .   0   0   0
      writeMetaState(e, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_u0_p1_u0,   NULL_u0_u0_u0, RIGHTa_u0_p1_u0, NULL_u0_u0_u0);
      //    x    0   0   1           =>   x    .   .   .   0   0   0
      //    x        0   0   0       =>   x        .   .   .   0   0   1
      //    x            0   0   0   =>   x            .   .   .   0   0   0
      writeMetaState(e, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_u0_u0_p1,   NULL_u0_u0_u0, RIGHTa_u0_u0_p1, NULL_u0_u0_u0);
      //----------------------------------------------------------------------------------------------------------------
       */
      //----------------------------------------------------------------------------------------------------------------
      return e;
   }
}
