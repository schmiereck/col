package de.schmiereck.col.services.engine.stay;

import static de.schmiereck.col.model.State.NULL_pos;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.engine.CreateEngineService.initMetaStateArr;
import static de.schmiereck.col.services.engine.CreateEngineService.writeMetaState;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class CreateLevel1StayEngineService {

   // null:
   public final static int SNULL_u0_u0 = NULL_pos;
   // a:
   // stay:
   public final static int SSTAY_p1_u0 = 1;
   public final static int SSTAY_u0_p1 = 2;

   public static Engine createLevel1StayEngine() {
      //----------------------------------------------------------------------------------------------------------------
      final Engine e = new Engine(2, 3);

      //----------------------------------------------------------------------------------------------------------------
      // null:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //  0    0   0   =>   0
      e.setState(SNULL_u0_u0, new State(2, nulState, nulState), SNULL_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      // 1    0   1   =>   1    0   1
      e.setState(SSTAY_u0_p1, new State(2, nulState, posState), SSTAY_u0_p1);
      // 2    1   0   =>   2    1   0
      e.setState(SSTAY_p1_u0, new State(2, posState, nulState), SSTAY_p1_u0);
      //----------------------------------------------------------------------------------------------------------------
      initMetaStateArr(e);

      //----------------------------------------------------------------------------------------------------------------
      // outputMetaState:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // null:
      //    0        0   0       =>   0        0   0
      //    0            0   0   =>   0            0   0
      writeMetaState(e, SNULL_u0_u0, SNULL_u0_u0, SNULL_u0_u0, SNULL_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      //    1        0   1       =>   1        0   0
      //    0            0   0   =>   0            1   0
      writeMetaState(e, SNULL_u0_u0, SSTAY_u0_p1, SSTAY_p1_u0, SNULL_u0_u0);
      //    0        0   0       =>   0        0   0  (1)  .
      //    1            0   1   =>   1            0   0   .   .
      writeMetaState(e, SSTAY_u0_p1, SNULL_u0_u0, SNULL_u0_u0, SSTAY_p1_u0, +2);
      //    2        1   0       =>   2.   .   1   0       2:stay -> 2:stay
      //    0            0   0   =>   0    .  (1)  0   0
      writeMetaState(e, SNULL_u0_u0, SSTAY_p1_u0, SSTAY_u0_p1, SNULL_u0_u0, -2);
      //    0        0   0       =>   0        0   1
      //    2            1   0   =>   2            0   0   2:stay -> 2:stay
      writeMetaState(e, SSTAY_p1_u0, SNULL_u0_u0, SNULL_u0_u0, SSTAY_u0_p1);
      //    1        0   1       =>   1        0   1
      //    1            0   1   =>   1            0   1
      writeMetaState(e, SSTAY_u0_p1, SSTAY_u0_p1, SSTAY_u0_p1, SSTAY_u0_p1);
      //    2        1   0       =>   2        1   0       2:stay -> 2:stay
      //    1            0   1   =>   1            0   1
      writeMetaState(e, SSTAY_u0_p1, SSTAY_p1_u0, SSTAY_u0_p1, SSTAY_p1_u0);
      //    1        0   1       =>   1        0   1
      //    2            1   0   =>   2            1   0   2:stay -> 2:stay
      writeMetaState(e, SSTAY_p1_u0, SSTAY_u0_p1, SSTAY_p1_u0, SSTAY_u0_p1);
      //    3        1   0       =>   3        1   0
      //    4            1   0   =>   4            1   0
      writeMetaState(e, SSTAY_p1_u0, SSTAY_p1_u0, SSTAY_p1_u0, SSTAY_p1_u0);
      //----------------------------------------------------------------------------------------------------------------
      return e;
   }
}
