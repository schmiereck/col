package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.State.NULL_pos;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.engine.CreateEngineService.initMetaStateArr;
import static de.schmiereck.col.services.engine.CreateEngineService.writeMetaState;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class CreateLevel2SpinMoveEngineService {

   // null:
   final static int NULL_u0_u0_u0 = NULL_pos;
   // a:
   // stay:
   final static int STAYa_p1_u0_u0 = 1;
   final static int STAYa_u0_p1_u0 = 2;
   final static int STAYa_u0_u0_p1 = 3;
   // left:
   final static int LEFTa_p1_u0_u0 = 4;
   final static int LEFTa_u0_p1_u0 = 5;
   final static int LEFTa_u0_u0_p1 = 6;
   // right:
   final static int RIGHTa_p1_u0_u0 = 7;
   final static int RIGHTa_u0_p1_u0 = 8;
   final static int RIGHTa_u0_u0_p1 = 9;
   
   public static Engine createLevel2SpinMoveEngine() {
      //----------------------------------------------------------------------------------------------------------------
      final Engine e = new Engine(3, 10);

      //----------------------------------------------------------------------------------------------------------------
      // null:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //  0    0   0   =>   0
      e.setState(NULL_u0_u0_u0, new State(nulState, nulState, nulState), NULL_u0_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      // x    1   0   0   =>   x    1   0   0
      e.setState(STAYa_p1_u0_u0, new State(posState, nulState, nulState), STAYa_p1_u0_u0);
      // x    0   1   0   =>   x    0   1   0
      e.setState(STAYa_u0_p1_u0, new State(nulState, posState, nulState), STAYa_u0_p1_u0);
      // x    0   0   1   =>   x    0   0   1
      e.setState(STAYa_u0_u0_p1, new State(nulState, nulState, posState), STAYa_u0_u0_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // left:
      // x    1   0   0   =>   x    1   0   0
      e.setState(LEFTa_p1_u0_u0, new State(posState, nulState, nulState), LEFTa_p1_u0_u0);
      // x    0   1   0   =>   x    0   1   0
      e.setState(LEFTa_u0_p1_u0, new State(nulState, posState, nulState), LEFTa_u0_p1_u0);
      // x    0   0   1   =>   x    0   0   1
      e.setState(LEFTa_u0_u0_p1, new State(nulState, nulState, posState), LEFTa_u0_u0_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      // x    1   0   0   =>   x    1   0   0
      e.setState(RIGHTa_p1_u0_u0, new State(posState, nulState, nulState), RIGHTa_p1_u0_u0);
      // x    0   1   0   =>   x    0   1   0
      e.setState(RIGHTa_u0_p1_u0, new State(nulState, posState, nulState), RIGHTa_u0_p1_u0);
      // x    0   0   1   =>   x    0   0   1
      e.setState(RIGHTa_u0_u0_p1, new State(nulState, nulState, posState), RIGHTa_u0_u0_p1);
      //----------------------------------------------------------------------------------------------------------------
      initMetaStateArr(e);
      //initOutputMetaStatePos(level1dynamicEngine);

      //----------------------------------------------------------------------------------------------------------------
      // outputMetaState:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // null:
      //    x    0   0   0           =>   x    0   0   0
      //    x        0   0   0       =>   x        0   0   0
      //    x            0   0   0   =>   x            0   0   0
      writeMetaState(e, NULL_u0_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      //    x    0   0   0           =>   x    0   0   0
      //    x        0   0   0       =>   x        0   1   0
      //    x            1   0   0   =>   x            0   0   0
      writeMetaState(e, STAYa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, STAYa_u0_p1_u0, NULL_u0_u0_u0);
      //    x    0   0   0           =>   x    0   0   0
      //    x        0   0   0       =>   x        0   0   1
      //    x            0   1   0   =>   x            0   0   0
      writeMetaState(e, STAYa_u0_p1_u0, NULL_u0_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, STAYa_u0_u0_p1, NULL_u0_u0_u0);
      //    x    0   0   0           =>   x    0   0   0   .   .   .
      //    x        0   0   0       =>   x        0   0   0  (1)  .   .
      //    x            0   0   1   =>   x            0   0   0   .   .   .
      writeMetaState(e, STAYa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, STAYa_p1_u0_u0, NULL_u0_u0_u0, +3);
      //    x    0   0   0           =>   x    0   1   0
      //    x        1   0   0       =>   x        0   0   0
      //    x            0   0   0   =>   x            0   0   0
      writeMetaState(e, NULL_u0_u0_u0, STAYa_p1_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, NULL_u0_u0_u0, STAYa_u0_p1_u0);
      //    x    0   0   0           =>   x    0   0   1
      //    x        0   1   0       =>   x        0   0   0
      //    x            0   0   0   =>   x            0   0   0
      writeMetaState(e, NULL_u0_u0_u0, STAYa_u0_p1_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, NULL_u0_u0_u0, STAYa_u0_u0_p1);
      //    x    0   0   0           =>   x    0   0   0  (1)  .   .
      //    x        0   0   1       =>   x        0   0   0
      //    x            0   0   0   =>   x            0   0   0
      writeMetaState(e, NULL_u0_u0_u0, STAYa_u0_u0_p1, NULL_u0_u0_u0,   NULL_u0_u0_u0, NULL_u0_u0_u0, STAYa_p1_u0_u0, +3);
      //    x    1   0   0           =>   x    .   .   .   0   0   0
      //    x        0   0   0       =>   x        .   .   .   0   0   0
      //    x            0   0   0   =>   x            .  (1)  .   0   0   0
      writeMetaState(e, NULL_u0_u0_u0, NULL_u0_u0_u0, STAYa_p1_u0_u0,   STAYa_u0_p1_u0, NULL_u0_u0_u0, NULL_u0_u0_u0, -3);
      //    x    0   1   0           =>   x    .   .   .   0   0   0
      //    x        0   0   0       =>   x        .   .   .   0   0   0
      //    x            0   0   0   =>   x            .   .  (1)  0   0   0
      writeMetaState(e, NULL_u0_u0_u0, NULL_u0_u0_u0, STAYa_u0_p1_u0,   STAYa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0, -3);
      //    x    0   0   1           =>   x    .   .   .   0   0   0
      //    x        0   0   0       =>   x        .   .   .   0   0   0
      //    x            0   0   0   =>   x            .   .   .   1   0   0
      writeMetaState(e, NULL_u0_u0_u0, NULL_u0_u0_u0, STAYa_u0_u0_p1,   STAYa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
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
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      //    x    0   0   0           =>   x    0   0   0
      //    x        0   0   0       =>   x        0   0   1
      //    x            1   0   0   =>   x            0   0   0
      writeMetaState(e, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, RIGHTa_u0_u0_p1, NULL_u0_u0_u0);
      //    x    0   0   0           =>   x    0   0   0   .   .   .
      //    x        0   0   0       =>   x        0   0   0  (1)  .   .
      //    x            0   1   0   =>   x            0   0   0   .   .   .
      writeMetaState(e, RIGHTa_u0_p1_u0, NULL_u0_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, +3);
      //    x    0   0   0           =>   x    0   0   0   .   .   .
      //    x        0   0   0       =>   x        0   0   0   .  (1)  .
      //    x            0   0   1   =>   x            0   0   0   .   .   .
      writeMetaState(e, RIGHTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, RIGHTa_u0_p1_u0, NULL_u0_u0_u0, +3);

      //    x    0   0   0           =>   x    0   0   1
      //    x        1   0   0       =>   x        0   0   0
      //    x            0   0   0   =>   x            0   0   0
      writeMetaState(e, NULL_u0_u0_u0, RIGHTa_p1_u0_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_u0_u0_p1);
      //    x    0   0   0           =>   x    0   0   0  (1)  .   .
      //    x        0   1   0       =>   x        0   0   0   .   .   .
      //    x            0   0   0   =>   x            0   0   0   .   .   .
      writeMetaState(e, NULL_u0_u0_u0, RIGHTa_u0_p1_u0, NULL_u0_u0_u0,   NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_p1_u0_u0, +3);
      //    x    0   0   0           =>   x    0   0   0   .  (1)  .
      //    x        0   0   1       =>   x        0   0   0   .   .   .
      //    x            0   0   0   =>   x            0   0   0   .   .   .
      writeMetaState(e, NULL_u0_u0_u0, RIGHTa_u0_u0_p1, NULL_u0_u0_u0,   NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_u0_p1_u0, +3);

      //    x    1   0   0           =>   x    .   .   .   0   0   0
      //    x        0   0   0       =>   x        .   .   .   0   0   0
      //    x            0   0   0   =>   x            .   .  (1)  0   0   0
      writeMetaState(e, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_p1_u0_u0,   RIGHTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0, -3);
      //    x    0   1   0           =>   x    .   .   .   0   0   0
      //    x        0   0   0       =>   x        .   .   .   0   0   0
      //    x            0   0   0   =>   x            .   .   .   1   0   0
      writeMetaState(e, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_u0_p1_u0,   RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0);
      //    x    0   0   1           =>   x    .   .   .   0   0   0
      //    x        0   0   0       =>   x        .   .   .   0   0   0
      //    x            0   0   0   =>   x            .   .   .   0   1   0
      writeMetaState(e, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_u0_u0_p1,   RIGHTa_u0_p1_u0, NULL_u0_u0_u0, NULL_u0_u0_u0);
      //----------------------------------------------------------------------------------------------------------------
      //----------------------------------------------------------------------------------------------------------------
      return e;
   }
}
