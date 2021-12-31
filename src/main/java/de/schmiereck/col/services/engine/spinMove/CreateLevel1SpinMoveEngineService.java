package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.State.NULL_pos;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.engine.CreateEngineService.initMetaStateArr;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.CreateEngineService.writeMetaState;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.NULL_u0;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class CreateLevel1SpinMoveEngineService {

   // null:
   public final static int NULL_u0_u0 = NULL_pos;
   // a:
   // stay:
   public final static int STAYa_p1_u0 = 1;
   public final static int STAYa_u0_p1 = 2;
   // left:
   public final static int LEFTa_p1_u0 = 3;
   public final static int LEFTa_u0_p1 = 4;
   // right:
   public final static int RIGHTa_p1_u0 = 5;
   public final static int RIGHTa_u0_p1 = 6;
   // Wirldcards:
   final static int[] ALL_xX_xX =  { NULL_u0_u0,
           STAYa_u0_p1,  STAYa_p1_u0,
           LEFTa_u0_p1,  LEFTa_p1_u0,
           RIGHTa_p1_u0,  RIGHTa_u0_p1,  };

   /**
    * Move nur im State.
    * Meta wechselt nur zwischen den Metas (ohne Move).
    * ??? b noch notwendig?
    */
   public static Engine createLevel1SpinMoveEngine() {
      //----------------------------------------------------------------------------------------------------------------
      final Engine level1moveEngine = new Engine(2, 7);

      //----------------------------------------------------------------------------------------------------------------
      // null:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //  0    0   0   =>   0
      level1moveEngine.setState(NULL_u0_u0, new State(2, nulState, nulState), NULL_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      // 1    0   1   =>   1    0   1
      level1moveEngine.setState(STAYa_u0_p1, new State(2, nulState, posState), STAYa_u0_p1);
      // 2    1   0   =>   2    1   0
      level1moveEngine.setState(STAYa_p1_u0, new State(2, posState, nulState), STAYa_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // left:
      // 5    0   1   =>   4    1   0
      level1moveEngine.setState(LEFTa_u0_p1, new State(2, nulState, posState), LEFTa_u0_p1); // Wait-Move.
      // 6    1   0   =>   4    1   0
      level1moveEngine.setState(LEFTa_p1_u0, new State(2, posState, nulState), LEFTa_p1_u0); // Wait-Move.
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      // 7    1   0   =>   7    0   1
      level1moveEngine.setState(RIGHTa_p1_u0, new State(2, posState, nulState), RIGHTa_p1_u0); // Wait-Move.
      // 8    0   1   =>   8    0   1
      level1moveEngine.setState(RIGHTa_u0_p1, new State(2, nulState, posState), RIGHTa_u0_p1); // Wait-Move.
      //----------------------------------------------------------------------------------------------------------------
      initMetaStateArr(level1moveEngine);
      //initOutputMetaStatePos(level1dynamicEngine);

      final Engine e = level1moveEngine;

      //----------------------------------------------------------------------------------------------------------------
      // outputMetaState:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // null:
      //    0        0   0       =>   0        0   0
      //    0            0   0   =>   0            0   0
      //writeMetaState(e, NULL_u0_u0, NULL_u0_u0, NULL_u0_u0, NULL_u0_u0, NULL_u0_u0, NULL_u0_u0);
      writeMetaState(e, NULL_u0_u0, NULL_u0_u0,   NULL_u0_u0, NULL_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      //    1        0   1       =>   1        0   0
      //    0            0   0   =>   0            1   0
      writeMetaState(e, NULL_u0_u0, STAYa_u0_p1,   STAYa_p1_u0, NULL_u0_u0);
      //    0        0   0       =>   0        0   0  (1)  .
      //    1            0   1   =>   1            0   0   .   .
      writeMetaState(e, STAYa_u0_p1, NULL_u0_u0,   NULL_u0_u0, STAYa_p1_u0, +2);
      //    2        1   0       =>   2.   .   1   0       2:stay -> 2:stay
      //    0            0   0   =>   0    .  (1)  0   0
      writeMetaState(e, NULL_u0_u0, STAYa_p1_u0,   STAYa_u0_p1, NULL_u0_u0, -2);
      //    0        0   0       =>   0        0   1
      //    2            1   0   =>   2            0   0   2:stay -> 2:stay
      writeMetaState(e, STAYa_p1_u0, NULL_u0_u0,   NULL_u0_u0, STAYa_u0_p1);
      //    1        0   1       =>   1        0   1
      //    1            0   1   =>   1            0   1
      writeMetaState(e, STAYa_u0_p1, STAYa_u0_p1,   STAYa_u0_p1, STAYa_u0_p1);
      //    2        1   0       =>   2        1   0       2:stay -> 2:stay
      //    1            0   1   =>   1            0   1
      writeMetaState(e, STAYa_u0_p1, STAYa_p1_u0,   STAYa_u0_p1, STAYa_p1_u0);
      //    1        0   1       =>   1        0   1
      //    2            1   0   =>   2            1   0   2:stay -> 2:stay
      writeMetaState(e, STAYa_p1_u0, STAYa_u0_p1,   STAYa_p1_u0, STAYa_u0_p1);
      //    3        1   0       =>   3        1   0
      //    4            1   0   =>   4            1   0
      writeMetaState(e, STAYa_p1_u0, STAYa_p1_u0,   STAYa_p1_u0, STAYa_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // left:
      //    0        0   0       =>   6        1   0           // move-Meta-Left
      //    7            1   0   =>   0            0   0
      writeMetaState(e, LEFTa_p1_u0, NULL_u0_u0,    NULL_u0_u0, LEFTa_p1_u0);
      //    0        0   0       =>   0        0   1
      //    5            0   1   =>   5            0   0       // move-Meta-Left
      writeMetaState(e, LEFTa_u0_p1, NULL_u0_u0,    NULL_u0_u0, LEFTa_u0_p1);
      //    4        1   0       =>   4        0   0           // move-Meta-Left (-2)
      //    0            0   0   =>   0            1   0
      //writeMetaState(e, NULL_u0_u0, LEFTa_p1_u0,   NULL_u0_u0, LEFTa_p1_u0);   // nothing to do
      writeMetaState(e, NULL_u0_u0, LEFTa_p1_u0,   LEFTa_p1_u0, NULL_u0_u0, -2);  // move-Meta-Left (-2)
      //    5        0   1       =>   5        0   0           // move-Meta-Left (-2)
      //    0            0   0   =>   0            0   1
      //writeMetaState(e, NULL_u0_u0, LEFTa_u0_p1,   NULL_u0_u0, LEFTa_u0_p1);     // nothing to do
      writeMetaState(e, NULL_u0_u0, LEFTa_u0_p1,   LEFTa_u0_p1, NULL_u0_u0, -2);     // move-Meta-Left (-2)
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      //    0        0   0       =>   0        1   0
      //    9            1   0   =>   9            0   0       // Move-Meta-Right (+2)
      //writeMetaState(e, RIGHTa_p1_u0, NULL_u0_u0,   RIGHTa_p1_u0, NULL_u0_u0);      // nothing to do
      writeMetaState(e, RIGHTa_p1_u0, NULL_u0_u0,   NULL_u0_u0, RIGHTa_p1_u0, +2);     // Move-Meta-Right (+2)
      //    0        0   0       =>   0        0   1
      //   11            0   1   =>  11            0   0       // Move-Meta-Right (+2)
      //writeMetaState(e, RIGHTa_u0_p1, NULL_u0_u0,   RIGHTa_u0_p1, NULL_u0_u0);      // nothing to do
      writeMetaState(e, RIGHTa_u0_p1, NULL_u0_u0,   NULL_u0_u0, RIGHTa_u0_p1, +2);      // Move-Meta-Right (+2)
      //    9        1   0       =>   9        0   0           // Move-Meta-Right
      //    0            0   0   =>   0            1   0
      writeMetaState(e, NULL_u0_u0, RIGHTa_p1_u0,   RIGHTa_p1_u0, NULL_u0_u0);
      //   11        0   1       =>   0        0   0           // Move-Meta-Right
      //    0            0   0   =>  10            0   1
      writeMetaState(e, NULL_u0_u0, RIGHTa_u0_p1,   RIGHTa_u0_p1, NULL_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (stay, right):
      //    *    *   *           =>   *    *   *
      //    9        1   0       =>   9        1   0        // nothing to do
      //    1            0   1   =>   1            0   1
      writeMetaState(e, STAYa_u0_p1, RIGHTa_p1_u0,   STAYa_u0_p1, RIGHTa_p1_u0);
      //    *    *   *           =>   *    *   *
      //   11        0   1       =>   1        0   1        // nothing to do
      //    1            0   1   =>  11            0   1
      writeMetaState(e, STAYa_u0_p1, RIGHTa_u0_p1,   STAYa_u0_p1, RIGHTa_u0_p1);
      //    *    *   *           =>   *    *   *
      //   11        0   1       =>   1        0   1        // collision
      //    3            1   0   =>   9            1   0
      writeMetaState(e, STAYa_p1_u0, RIGHTa_u0_p1,   RIGHTa_p1_u0, STAYa_u0_p1);
      //    *    *   *           =>   *    *   *
      //    9        1   0       =>   3        1   0        // collision
      //    3            1   0   =>   9            1   0
      writeMetaState(e, STAYa_p1_u0, RIGHTa_p1_u0,   RIGHTa_p1_u0, STAYa_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (stay, left):
      //    *    *   *           =>   *    *   *
      //    7        1   0       =>   7        1   0        // nothing to do
      //    3            1   0   =>   3            1   0
      writeMetaState(e, STAYa_p1_u0, LEFTa_p1_u0,   STAYa_p1_u0, LEFTa_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (left, left):
      //    *    *   *           =>   *    *   *
      //    5        0   1       =>   5        0   1       // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    5            0   1   =>   5            0   1
      writeMetaState(e, LEFTa_u0_p1, LEFTa_u0_p1,   LEFTa_u0_p1, LEFTa_u0_p1);
      //    *    *   *           =>   *    *   *
      //    6        1   0       =>   6        1   0        // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    6            1   0   =>   6            1   0
      writeMetaState(e, LEFTa_p1_u0, LEFTa_p1_u0,   LEFTa_p1_u0, LEFTa_p1_u0);
      //    *    *   *           =>   *    *   *
      //    5        0   1       =>   5        0   1        // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    7            1   0   =>   7            1   0
      writeMetaState(e, LEFTa_p1_u0, LEFTa_u0_p1,   LEFTa_p1_u0, LEFTa_u0_p1);
      //    *    *   *           =>   *    *   *
      //    6        1   0       =>   6        1   0        // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    5            0   1   =>   5            0   1
      writeMetaState(e, LEFTa_u0_p1, LEFTa_p1_u0,   LEFTa_u0_p1, LEFTa_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (left, stay):
      //    *    *   *           =>   *    *   *
      //    1        0   1       =>   5        0   1        // collision
      //    7            1   0   =>   3            1   0
      writeMetaState(e, LEFTa_p1_u0, STAYa_u0_p1,   STAYa_p1_u0, LEFTa_u0_p1);
      //    *    *   *           =>   *    *   *
      //    3        1   0       =>   7        1   0        // collision
      //    7            1   0   =>   3            1   0
      writeMetaState(e, LEFTa_p1_u0, STAYa_p1_u0,   STAYa_p1_u0, LEFTa_p1_u0);
      //    *    *   *           =>   *    *   *
      //    3        1   0       =>   3        1   0        // !!! no move !!!   Level Down?
      //    5            0   1   =>   5            0   1    //
      writeMetaState(e, LEFTa_u0_p1, STAYa_p1_u0,   LEFTa_u0_p1, STAYa_p1_u0);
       // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (left, right):
      //    *    *   *           =>   *    *   *
      //    9        1   0       =>   7        1   0        // collision
      //    7            1   0   =>   9            1   0
      writeMetaState(e, LEFTa_p1_u0, RIGHTa_p1_u0,   RIGHTa_p1_u0, LEFTa_p1_u0);
      //    *    *   *           =>   *    *   *
      //   11        0   1       =>   5        0   1        // collision
      //    7            1   0   =>   9            1   0
      writeMetaState(e, LEFTa_p1_u0, RIGHTa_u0_p1,   RIGHTa_p1_u0, LEFTa_u0_p1);
      /*
      //    *    *   *           =>   *    *   *
      //    9        1   0       =>   9        1   0        // !!! no move !!!
      //    5            0   1   =>   5            0   1    // !!! no move !!!
      writeMetaState(e, LEFTa_u0_p1, RIGHTa_p1_u0,   LEFTa_u0_p1, RIGHTa_p1_u0);    // Level Down?
      writeMetaState(e, LEFTb_u0_p1, RIGHTa_p1_u0,   LEFTb_u0_p1, RIGHTa_p1_u0);
      writeMetaState(e, LEFTa_u0_p1, RIGHTb_p1_u0,   LEFTa_u0_p1, RIGHTb_p1_u0);
      writeMetaState(e, LEFTb_u0_p1, RIGHTb_p1_u0,   LEFTb_u0_p1, RIGHTb_p1_u0);
      // 95/1/0:            >  11|11| 0    11|11| 1 >1981| 5| 0  1981| 5| 1 > 936| 0| 0   936| 0| 0
      // 95/1/1:    0| 0| 0     0| 0| 0 > 152| 9| 1   152| 9| 0 >1593| 7| 1  1593| 7| 0
      //                            11:RIGHTa_u0_p1           5:LEFTa_u0_p1
      //                                9:RIGHTa_p1_u0           7:LEFTa_p1_u0
       */
      //    *    *   *           =>   *    *   *
      //    9        1   0       =>   9        0   1        // collision + move
      //    5            0   1   =>   5            1   0
      //writeMetaState(e, LEFTa_u0_p1, RIGHTa_p1_u0,   RIGHTb_p1_u0, LEFTb_u0_p1);    // Move (or Level Down?)
      //writeMetaState(e, LEFTa_u0_p1, RIGHTa_p1_u0,   LEFTa_u0_p1, RIGHTa_p1_u0, true);    // Level Down
      //    *    *   *           =>   *    *   *
      //   11        0   1       =>   5        0   1        // collision
      //    5            0   1   =>  11            0   1    //
      writeMetaState(e, LEFTa_u0_p1, RIGHTa_u0_p1,   RIGHTa_u0_p1, LEFTa_u0_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (right, right):
      //    *    *   *           =>   *    *   *
      //    8        0   1       =>   8        0   1       // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    8            0   1   =>   8            0   1   // !!! no move !!!  (!!! Alternativ: collision-left-left !!!)
      writeMetaState(e, RIGHTa_u0_p1, RIGHTa_u0_p1,   RIGHTa_u0_p1, RIGHTa_u0_p1);
      //    *    *   *           =>   *    *   *
      //    7        1   0       =>   7        1   0        // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    7            1   0   =>   7            1   0
      writeMetaState(e, RIGHTa_p1_u0, RIGHTa_p1_u0,   RIGHTa_p1_u0, RIGHTa_p1_u0);
      {
         // 0/1/0:             >  11|11| 0    11|11| 1 >2282| 0| 0  2282| 0| 0 >   0| 0| 0     0| 0| 0
         // 0/1/1: >   0| 0| 0     0| 0| 0 > 163| 9| 1   163| 9| 0 >1764| 0| 0  1764| 0| 0
         //-------  ----------  ----------  ----------  ----------  ----------  ---------- : runCalcNextState
         // 0/1/0:             >  11|11| 0    11|11| 1 >1869|10| 1  1869|10| 0 >1690| 0| 0  1690| 0| 0
         // 0/1/1: >   0| 0| 0     0| 0| 0 > 143| 0| 0   143| 0| 0 > 130| 0| 0   130| 0| 0
         //-------  ----------  ----------  ----------  ----------  ----------  ---------- : runCalcNextMetaState
         // 1/1/0:             >  11|11| 0    11|11| 1 >1869|10| 1  1869|10| 0 >1690| 0| 0  1690| 0| 0
         // 1/1/1: >   0| 0| 0     0| 0| 0 > 143| 0| 0   143| 0| 0 > 130| 0| 0   130| 0| 0
         //-------  ----------  ----------  ----------  ----------  ----------  ---------- : runLevelUp
         //    *    *   *           =>   *    *   *
         //    *        0   1       =>   *        0   1        // !!! no move !!!
         //    *            1   0   =>   *            1   0

         //    *    0   1a          =>   *    0   1a
         //    *        1a  0       =>   *        x   0        // !!! no move !!!
         //    *            0   0   =>   *            1b  0

         //    *    *   *           =>   *    *   *
         //   11        0   1       =>   0        0   0
         //    9            1   0   =>  13            1   1
         //writeMetaState(e, RIGHTa_p1_u0, RIGHTa_u0_p1,   COLLa_RR_p1_p1, NULL_u0_u0);
         //    *    *   *           =>   *    *   *
         //   13        1   1       =>   9        1   0
         //    0            0   0   =>  11            0   1
         //writeMetaState(e, NULL_u0_u0, COLLa_RR_p1_p1,   RIGHTa_u0_p1, RIGHTa_p1_u0);
      }

      //    *    *   *           =>   *    *   *
      //    *        0   1       =>   *        0   1        // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    *            1   0   =>   *            1   0
      writeMetaState(e, RIGHTa_p1_u0, RIGHTa_u0_p1,   RIGHTa_p1_u0, RIGHTa_u0_p1);
      //    *    *   *           =>   *    *   *
      //    7        1   0       =>   7        1   0        // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    8            0   1   =>   8            0   1
      writeMetaState(e, RIGHTa_u0_p1, RIGHTa_p1_u0,   RIGHTa_u0_p1, RIGHTa_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (right, stay):
      //    *    *   *           =>   *    *   *
      //    2        0   1       =>   2        0   1        // nothing to do
      //    8            0   1   =>   8            0   1
      writeMetaState(e, RIGHTa_u0_p1, STAYa_u0_p1,   RIGHTa_u0_p1, STAYa_u0_p1);
      //    *    *   *           =>   *    *   *
      //    3        1   0       =>   5        1   0        // nothing to do
      //    8            0   1   =>   6            0   1
      writeMetaState(e, RIGHTa_u0_p1, STAYa_p1_u0,   RIGHTa_u0_p1, STAYa_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (right, left):
      //    *    *   *           =>   *    *   *
      //    5        0   1       =>   5        0   1       // nothing to do
      //   11            0   1   =>  11            0   1
      writeMetaState(e, RIGHTa_u0_p1, LEFTa_u0_p1,   RIGHTa_u0_p1, LEFTa_u0_p1);
      //    *    *   *           =>   *    *   *
      //    7        1   0       =>   7        1   0       // nothing to do
      //   11            0   1   =>  11            0   1
      writeMetaState(e, RIGHTa_u0_p1, LEFTa_p1_u0,   RIGHTa_u0_p1, LEFTa_p1_u0);
      //    *    *   *           =>   *    *   *
      //    5        0   1       =>   5        0   1       // nothing to do
      //    9            1   0   =>   9            1   0
      writeMetaState(e, RIGHTa_p1_u0, LEFTa_u0_p1,   RIGHTa_p1_u0, LEFTa_u0_p1);
      //    2    0   1       =>   2    0   1
      //    9        1   1   =>   9        1   1
      //writeMetaState(e, COLL_LR_p1_p1, STAYb_u0_p1, COLL_LR_p1_p1, STAYb_u0_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // 7:collision (left -> <- right):
      // 8:collision-to-right (right -> stay):
      // 9:collision-to-left (left -> stay):

      //----------------------------------------------------------------------------------------------------------------
      return level1moveEngine;
   }
}
