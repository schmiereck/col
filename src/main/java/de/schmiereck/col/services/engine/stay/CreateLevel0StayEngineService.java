package de.schmiereck.col.services.engine.stay;

import static de.schmiereck.col.model.State.NULL_pos;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.engine.CreateEngineService.initMetaStateArr;
import static de.schmiereck.col.services.engine.CreateEngineService.writeMetaState;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class CreateLevel0StayEngineService {

   // null:
   public final static int SNULL_u0 = NULL_pos;
   // a:
   // stay:
   public final static int SSTAY_p1 = 1;

   public static Engine createLevel0StayEngine() {
      //----------------------------------------------------------------------------------------------------------------
      final Engine e = new Engine(1, 2);

      //----------------------------------------------------------------------------------------------------------------
      // null:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //  x    0   =>   x    0
      e.setState(SNULL_u0, new State(1, nulState), SNULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      // x    1   =>   x    1
      e.setState(SSTAY_p1, new State(1, posState), SSTAY_p1);
      //----------------------------------------------------------------------------------------------------------------
      initMetaStateArr(e);

      //----------------------------------------------------------------------------------------------------------------
      // outputMetaState:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // null:
      //    x        0       =>   0        0
      //    x            0   =>   0            0
      writeMetaState(e, SNULL_u0, SNULL_u0,   SNULL_u0, SNULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      //    x        0       =>   x        0
      //    x            1   =>   x            1
      writeMetaState(e, SSTAY_p1, SNULL_u0,   SSTAY_p1, SNULL_u0, +0);
      //    x        0       =>   x        0  (1)
      //    x            1   =>   x            0   .
      //writeMetaState(e, SSTAY_p1, SNULL_u0,   SNULL_u0, SSTAY_p1, +1);
      //    x        1       =>   x    .   0   0
      //    x            0   =>   x       (1)  0   0
      //writeMetaState(e, SNULL_u0, SSTAY_p1,   SSTAY_p1, SNULL_u0, -1);
      //----------------------------------------------------------------------------------------------------------------
      return e;
   }
}
