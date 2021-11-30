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
   final static int LEFTa_u0_p1 = 5;
   final static int LEFTb_u0_p1 = 6;
   final static int LEFTa_p1_u0 = 7;
   final static int LEFTb_p1_u0 = 8;
   // right:
   final static int RIGHTa_p1_u0 = 9;
   final static int RIGHTb_p1_u0 = 10;
   final static int RIGHTa_u0_p1 = 11;
   final static int RIGHTb_u0_p1 = 12;
   // collision:
   //final static int COLLa_RR_p1_p1 = 13;
   // Wirldcards:
   final static int[] ALL_xX_xX =  { NULL_u0_u0,
           STAYa_u0_p1, STAYb_u0_p1, STAYa_p1_u0, STAYb_p1_u0,
           LEFTa_u0_p1, LEFTb_u0_p1, LEFTa_p1_u0, LEFTb_p1_u0,
           RIGHTa_p1_u0, RIGHTb_p1_u0, RIGHTa_u0_p1, RIGHTb_u0_p1 };

   public static Engine createLevel1SpinMoveEngine() {
      //----------------------------------------------------------------------------------------------------------------
      final Engine level1moveEngine = new Engine(2, 13, true);

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
      // 5    0   1   =>   4    0   1
      level1moveEngine.setState(LEFTa_u0_p1, new State(2, nulState, posState), LEFTa_u0_p1); // Move.
      level1moveEngine.setState(LEFTb_u0_p1, new State(2, nulState, posState), LEFTa_u0_p1);
      // 6    1   0   =>   4    1   0
      level1moveEngine.setState(LEFTa_p1_u0, new State(2, posState, nulState), LEFTa_p1_u0); // Move.
      level1moveEngine.setState(LEFTb_p1_u0, new State(2, posState, nulState), LEFTa_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      // 7    1   0   =>   7    0   1
      level1moveEngine.setState(RIGHTa_p1_u0, new State(2, posState, nulState), RIGHTa_p1_u0); // Move.
      level1moveEngine.setState(RIGHTb_p1_u0, new State(2, posState, nulState), RIGHTa_p1_u0);
      // 8    0   1   =>   8    0   1
      level1moveEngine.setState(RIGHTa_u0_p1, new State(2, nulState, posState), RIGHTa_u0_p1); // Move.
      level1moveEngine.setState(RIGHTb_u0_p1, new State(2, nulState, posState), RIGHTa_u0_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision:
      //level1moveEngine.setState(COLLa_RR_p1_p1, new State(2, posState, posState), COLLa_RR_p1_p1);

      //----------------------------------------------------------------------------------------------------------------
      initMetaStateArr(level1moveEngine);
      //initOutputMetaStatePos(level1dynamicEngine);

      final Engine e = level1moveEngine;

      //----------------------------------------------------------------------------------------------------------------
      // outputMetaState:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // null:
      //    0    0   0           =>   0    0   0
      //    0        0   0       =>   0        0   0
      //    0            0   0   =>   0            0   0
      writeMetaState(e, NULL_u0_u0, NULL_u0_u0, NULL_u0_u0, NULL_u0_u0, NULL_u0_u0, NULL_u0_u0);
      writeMetaState(e, NULL_u0_u0, NULL_u0_u0, ALL_xX_xX, NULL_u0_u0, NULL_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      //    0    0   0           =>   0    0   0
      //    1        0   1       =>   1        0   1
      //    0            0   0   =>   0            0   0
      writeMetaState(e, NULL_u0_u0, STAYa_u0_p1, ALL_xX_xX, NULL_u0_u0, STAYa_u0_p1);
      writeMetaState(e, NULL_u0_u0, STAYb_u0_p1, ALL_xX_xX, NULL_u0_u0, STAYb_u0_p1);
      //    0    0   0           =>   0    0   0
      //    0        0   0       =>   0        0   0
      //    1            0   1   =>   1            0   1
      writeMetaState(e, STAYa_u0_p1, NULL_u0_u0, ALL_xX_xX, STAYa_u0_p1, NULL_u0_u0);
      writeMetaState(e, STAYb_u0_p1, NULL_u0_u0, ALL_xX_xX, STAYb_u0_p1, NULL_u0_u0);
      //    0    0   0           =>   0    0   0
      //    2        1   0       =>   2        1   0       2:stay -> 2:stay
      //    0            0   0   =>   0            0   0
      writeMetaState(e, NULL_u0_u0, STAYa_p1_u0, ALL_xX_xX, NULL_u0_u0, STAYa_p1_u0);
      writeMetaState(e, NULL_u0_u0, STAYb_p1_u0, ALL_xX_xX, NULL_u0_u0, STAYb_p1_u0);
      //    0    0   0           =>   0    0   0
      //    0        0   0       =>   0        0   0
      //    2            1   0   =>   2            1   0   2:stay -> 2:stay
      writeMetaState(e, STAYa_p1_u0, NULL_u0_u0, ALL_xX_xX, STAYa_p1_u0, NULL_u0_u0);
      writeMetaState(e, STAYb_p1_u0, NULL_u0_u0, ALL_xX_xX, STAYb_p1_u0, NULL_u0_u0);
      //    0    0   0           =>   0    0   0
      //    1        0   1       =>   1        0   1
      //    1            0   1   =>   1            0   1
      writeMetaState(e, STAYa_u0_p1, STAYa_u0_p1, ALL_xX_xX, STAYa_u0_p1, STAYa_u0_p1);
      writeMetaState(e, STAYb_u0_p1, STAYb_u0_p1, ALL_xX_xX, STAYb_u0_p1, STAYb_u0_p1);
      //    0    0   0           =>   0    0   0
      //    2        1   0       =>   2        1   0       2:stay -> 2:stay
      //    1            0   1   =>   1            0   1
      writeMetaState(e, STAYa_u0_p1, STAYa_p1_u0, ALL_xX_xX, STAYa_u0_p1, STAYa_p1_u0);
      writeMetaState(e, STAYb_u0_p1, STAYa_p1_u0, ALL_xX_xX, STAYb_u0_p1, STAYa_p1_u0);
      writeMetaState(e, STAYa_u0_p1, STAYb_p1_u0, ALL_xX_xX, STAYa_u0_p1, STAYb_p1_u0);
      writeMetaState(e, STAYb_u0_p1, STAYb_p1_u0, ALL_xX_xX, STAYb_u0_p1, STAYb_p1_u0);
      //    0    0   0           =>   0    0   0
      //    1        0   1       =>   1        0   1
      //    2            1   0   =>   2            1   0   2:stay -> 2:stay
      writeMetaState(e, STAYa_p1_u0, STAYa_u0_p1, ALL_xX_xX, STAYa_p1_u0, STAYa_u0_p1);
      writeMetaState(e, STAYb_p1_u0, STAYa_u0_p1, ALL_xX_xX, STAYb_p1_u0, STAYa_u0_p1);
      writeMetaState(e, STAYa_p1_u0, STAYb_u0_p1, ALL_xX_xX, STAYa_p1_u0, STAYb_u0_p1);
      writeMetaState(e, STAYb_p1_u0, STAYb_u0_p1, ALL_xX_xX, STAYb_p1_u0, STAYb_u0_p1);
      //    0    0   0           =>   0    0   0
      //    1        0   1       =>   1        0   1
      //    2            0   1   =>   2            0   1
      writeMetaState(e, STAYb_u0_p1, STAYa_u0_p1, ALL_xX_xX, STAYb_u0_p1, STAYa_u0_p1);
      writeMetaState(e, STAYa_u0_p1, STAYb_u0_p1, ALL_xX_xX, STAYa_u0_p1, STAYb_u0_p1);
      //    0    0   0           =>   0    0   0
      //    3        1   0       =>   3        1   0
      //    4            1   0   =>   4            1   0
      writeMetaState(e, STAYb_p1_u0, STAYa_p1_u0, ALL_xX_xX, STAYb_p1_u0, STAYa_p1_u0);
      //    0    0   0           =>   0    0   0
      //    4        1   0       =>   4        1   0
      //    3            1   0   =>   3            1   0
      writeMetaState(e, STAYa_p1_u0, STAYb_p1_u0, ALL_xX_xX, STAYa_p1_u0, STAYb_p1_u0);
      //    0    0   0           =>   0    0   0
      //    3        1   0       =>   3        1   0
      //    3            1   0   =>   3            1   0
      writeMetaState(e, STAYa_p1_u0, STAYa_p1_u0, ALL_xX_xX, STAYa_p1_u0, STAYa_p1_u0);
      //    0    0   0           =>   0    0   0
      //    4        1   0       =>   4        1   0
      //    4            1   0   =>   4            1   0
      writeMetaState(e, STAYb_p1_u0, STAYb_p1_u0, ALL_xX_xX, STAYb_p1_u0, STAYb_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (stay, right):
      //    *    *   *           =>   *    *   *
      //    9        1   0       =>   9        1   0        // !!! no move !!!   Level Down?
      //    1            0   1   =>   1            0   1
      writeMetaState(e, STAYa_u0_p1, RIGHTa_p1_u0, ALL_xX_xX, STAYa_u0_p1, RIGHTa_p1_u0);
      writeMetaState(e, STAYb_u0_p1, RIGHTa_p1_u0, ALL_xX_xX, STAYb_u0_p1, RIGHTa_p1_u0);
      writeMetaState(e, STAYa_u0_p1, RIGHTb_p1_u0, ALL_xX_xX, STAYa_u0_p1, RIGHTb_p1_u0);
      writeMetaState(e, STAYb_u0_p1, RIGHTb_p1_u0, ALL_xX_xX, STAYb_u0_p1, RIGHTb_p1_u0);
      //    *    *   *           =>   *    *   *
      //   11        0   1       =>   1        0   1        // collision
      //    1            0   1   =>  11            0   1
      writeMetaState(e, STAYa_u0_p1, RIGHTa_u0_p1, ALL_xX_xX, RIGHTa_u0_p1, STAYa_u0_p1);
      writeMetaState(e, STAYb_u0_p1, RIGHTa_u0_p1, ALL_xX_xX, RIGHTb_u0_p1, STAYa_u0_p1);
      writeMetaState(e, STAYa_u0_p1, RIGHTb_u0_p1, ALL_xX_xX, RIGHTa_u0_p1, STAYb_u0_p1);
      writeMetaState(e, STAYb_u0_p1, RIGHTb_u0_p1, ALL_xX_xX, RIGHTb_u0_p1, STAYb_u0_p1);
      //    *    *   *           =>   *    *   *
      //   11        0   1       =>   1        0   1        // collision
      //    3            1   0   =>   9            1   0
      writeMetaState(e, STAYa_p1_u0, RIGHTa_u0_p1, ALL_xX_xX, RIGHTa_p1_u0, STAYa_u0_p1);
      writeMetaState(e, STAYb_p1_u0, RIGHTa_u0_p1, ALL_xX_xX, RIGHTb_p1_u0, STAYa_u0_p1);
      writeMetaState(e, STAYa_p1_u0, RIGHTb_u0_p1, ALL_xX_xX, RIGHTa_p1_u0, STAYb_u0_p1);
      writeMetaState(e, STAYb_p1_u0, RIGHTb_u0_p1, ALL_xX_xX, RIGHTb_p1_u0, STAYb_u0_p1);
      //    *    *   *           =>   *    *   *
      //    9        1   0       =>   3        1   0        // collision
      //    3            1   0   =>   9            1   0
      writeMetaState(e, STAYa_p1_u0, RIGHTa_p1_u0, ALL_xX_xX, RIGHTa_p1_u0, STAYa_p1_u0);
      writeMetaState(e, STAYb_p1_u0, RIGHTa_p1_u0, ALL_xX_xX, RIGHTb_p1_u0, STAYa_p1_u0);
      writeMetaState(e, STAYa_p1_u0, RIGHTb_p1_u0, ALL_xX_xX, RIGHTa_p1_u0, STAYb_p1_u0);
      writeMetaState(e, STAYb_p1_u0, RIGHTb_p1_u0, ALL_xX_xX, RIGHTb_p1_u0, STAYb_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (stay, left):
      //    *    *   *           =>   *    *   *
      //    7        1   0       =>   7        1   0        // nothing to do
      //    3            1   0   =>   3            1   0
      writeMetaState(e, STAYa_p1_u0, LEFTa_p1_u0, ALL_xX_xX, STAYa_p1_u0, LEFTa_p1_u0);
      writeMetaState(e, STAYb_p1_u0, LEFTa_p1_u0, ALL_xX_xX, STAYb_p1_u0, LEFTa_p1_u0);
      writeMetaState(e, STAYa_p1_u0, LEFTb_p1_u0, ALL_xX_xX, STAYa_p1_u0, LEFTb_p1_u0);
      writeMetaState(e, STAYb_p1_u0, LEFTb_p1_u0, ALL_xX_xX, STAYb_p1_u0, LEFTb_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // left:
      //    *    *   *           =>   *    *   *
      //    5        0   1       =>   5        0   1        // nothing to do
      //    0            0   0   =>   0            0   0
      writeMetaState(e, NULL_u0_u0, LEFTa_u0_p1, ALL_xX_xX, NULL_u0_u0, LEFTa_u0_p1);
      writeMetaState(e, NULL_u0_u0, LEFTb_u0_p1, ALL_xX_xX, NULL_u0_u0, LEFTb_u0_p1);
      //    *    *   *           =>   *    *   *
      //    4        1   0       =>   4        1   0        // nothing to do
      //    0            0   0   =>   0            0   0
      writeMetaState(e, NULL_u0_u0, LEFTa_p1_u0, ALL_xX_xX, NULL_u0_u0, LEFTa_p1_u0);
      writeMetaState(e, NULL_u0_u0, LEFTb_p1_u0, ALL_xX_xX, NULL_u0_u0, LEFTb_p1_u0);
      //    *    *   *           =>   *    *   *
      //    0        0   0       =>   6        0   1        // move
      //    5            0   1   =>   0            0   0
      writeMetaState(e, LEFTa_u0_p1, NULL_u0_u0, ALL_xX_xX, NULL_u0_u0, LEFTb_u0_p1);
      writeMetaState(e, LEFTb_u0_p1, NULL_u0_u0, ALL_xX_xX, LEFTb_u0_p1, NULL_u0_u0);
      //    *    *   *           =>   *    *   *
      //    0        0   0       =>   8        1   0        // move
      //    7            1   0   =>   0            0   0
      writeMetaState(e, LEFTa_p1_u0, NULL_u0_u0, ALL_xX_xX, NULL_u0_u0, LEFTb_p1_u0);
      writeMetaState(e, LEFTb_p1_u0, NULL_u0_u0, ALL_xX_xX, LEFTb_p1_u0, NULL_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (left, left):
      //    *    *   *           =>   *    *   *
      //    5        0   1       =>   5        0   1       // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    5            0   1   =>   5            0   1
      writeMetaState(e, LEFTa_u0_p1, LEFTa_u0_p1, ALL_xX_xX, LEFTa_u0_p1, LEFTa_u0_p1);
      writeMetaState(e, LEFTb_u0_p1, LEFTa_u0_p1, ALL_xX_xX, LEFTb_u0_p1, LEFTa_u0_p1);
      writeMetaState(e, LEFTa_u0_p1, LEFTb_u0_p1, ALL_xX_xX, LEFTa_u0_p1, LEFTb_u0_p1);
      writeMetaState(e, LEFTb_u0_p1, LEFTb_u0_p1, ALL_xX_xX, LEFTb_u0_p1, LEFTb_u0_p1);
      //    *    *   *           =>   *    *   *
      //    6        1   0       =>   6        1   0        // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    6            1   0   =>   6            1   0
      writeMetaState(e, LEFTa_p1_u0, LEFTa_p1_u0, ALL_xX_xX, LEFTa_p1_u0, LEFTa_p1_u0);
      writeMetaState(e, LEFTb_p1_u0, LEFTa_p1_u0, ALL_xX_xX, LEFTb_p1_u0, LEFTa_p1_u0);
      writeMetaState(e, LEFTa_p1_u0, LEFTb_p1_u0, ALL_xX_xX, LEFTa_p1_u0, LEFTb_p1_u0);
      writeMetaState(e, LEFTb_p1_u0, LEFTb_p1_u0, ALL_xX_xX, LEFTb_p1_u0, LEFTb_p1_u0);
      //    *    *   *           =>   *    *   *
      //    5        0   1       =>   5        0   1        // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    7            1   0   =>   7            1   0
      writeMetaState(e, LEFTa_p1_u0, LEFTa_u0_p1, ALL_xX_xX, LEFTa_p1_u0, LEFTa_u0_p1);
      writeMetaState(e, LEFTb_p1_u0, LEFTa_u0_p1, ALL_xX_xX, LEFTb_p1_u0, LEFTa_u0_p1);
      writeMetaState(e, LEFTa_p1_u0, LEFTb_u0_p1, ALL_xX_xX, LEFTa_p1_u0, LEFTb_u0_p1);
      writeMetaState(e, LEFTb_p1_u0, LEFTb_u0_p1, ALL_xX_xX, LEFTb_p1_u0, LEFTb_u0_p1);
      //    *    *   *           =>   *    *   *
      //    6        1   0       =>   6        1   0        // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    5            0   1   =>   5            0   1
      writeMetaState(e, LEFTa_u0_p1, LEFTa_p1_u0, ALL_xX_xX, LEFTa_u0_p1, LEFTa_p1_u0);
      writeMetaState(e, LEFTb_u0_p1, LEFTa_p1_u0, ALL_xX_xX, LEFTb_u0_p1, LEFTa_p1_u0);
      writeMetaState(e, LEFTa_u0_p1, LEFTb_p1_u0, ALL_xX_xX, LEFTa_u0_p1, LEFTb_p1_u0);
      writeMetaState(e, LEFTb_u0_p1, LEFTb_p1_u0, ALL_xX_xX, LEFTb_u0_p1, LEFTb_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (left, stay):
      //    *    *   *           =>   *    *   *
      //    1        0   1       =>   5        0   1        // collision
      //    7            1   0   =>   3            1   0
      writeMetaState(e, LEFTa_p1_u0, STAYa_u0_p1, ALL_xX_xX, STAYa_p1_u0, LEFTa_u0_p1);
      writeMetaState(e, LEFTb_p1_u0, STAYa_u0_p1, ALL_xX_xX, STAYb_p1_u0, LEFTa_u0_p1);
      writeMetaState(e, LEFTa_p1_u0, STAYb_u0_p1, ALL_xX_xX, STAYa_p1_u0, LEFTb_u0_p1);
      writeMetaState(e, LEFTb_p1_u0, STAYb_u0_p1, ALL_xX_xX, STAYb_p1_u0, LEFTb_u0_p1);
      //    *    *   *           =>   *    *   *
      //    3        1   0       =>   7        1   0        // collision
      //    7            1   0   =>   3            1   0
      writeMetaState(e, LEFTa_p1_u0, STAYa_p1_u0, ALL_xX_xX, STAYa_p1_u0, LEFTa_p1_u0);
      writeMetaState(e, LEFTb_p1_u0, STAYa_p1_u0, ALL_xX_xX, STAYb_p1_u0, LEFTa_p1_u0);
      writeMetaState(e, LEFTa_p1_u0, STAYb_p1_u0, ALL_xX_xX, STAYa_p1_u0, LEFTb_p1_u0);
      writeMetaState(e, LEFTb_p1_u0, STAYb_p1_u0, ALL_xX_xX, STAYb_p1_u0, LEFTb_p1_u0);
      //    *    *   *           =>   *    *   *
      //    3        1   0       =>   3        1   0        // !!! no move !!!   Level Down?
      //    5            0   1   =>   5            0   1    //
      writeMetaState(e, LEFTa_u0_p1, STAYa_p1_u0, ALL_xX_xX, LEFTa_u0_p1, STAYa_p1_u0);
      writeMetaState(e, LEFTa_u0_p1, STAYb_p1_u0, ALL_xX_xX, LEFTa_u0_p1, STAYb_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (left, right):
      //    *    *   *           =>   *    *   *
      //    9        1   0       =>   7        1   0        // collision
      //    7            1   0   =>   9            1   0
      writeMetaState(e, LEFTa_p1_u0, RIGHTa_p1_u0, ALL_xX_xX, RIGHTa_p1_u0, LEFTa_p1_u0);
      writeMetaState(e, LEFTb_p1_u0, RIGHTa_p1_u0, ALL_xX_xX, RIGHTb_p1_u0, LEFTa_p1_u0);
      writeMetaState(e, LEFTa_p1_u0, RIGHTb_p1_u0, ALL_xX_xX, RIGHTa_p1_u0, LEFTb_p1_u0);
      writeMetaState(e, LEFTb_p1_u0, RIGHTb_p1_u0, ALL_xX_xX, RIGHTb_p1_u0, LEFTb_p1_u0);
      //    *    *   *           =>   *    *   *
      //   11        0   1       =>   5        0   1        // collision
      //    7            1   0   =>   9            1   0
      writeMetaState(e, LEFTa_p1_u0, RIGHTa_u0_p1, ALL_xX_xX, RIGHTa_p1_u0, LEFTa_u0_p1);
      writeMetaState(e, LEFTb_p1_u0, RIGHTa_u0_p1, ALL_xX_xX, RIGHTb_p1_u0, LEFTa_u0_p1);
      writeMetaState(e, LEFTa_p1_u0, RIGHTb_u0_p1, ALL_xX_xX, RIGHTa_p1_u0, LEFTb_u0_p1);
      writeMetaState(e, LEFTb_p1_u0, RIGHTb_u0_p1, ALL_xX_xX, RIGHTb_p1_u0, LEFTb_u0_p1);
      /*
      //    *    *   *           =>   *    *   *
      //    9        1   0       =>   9        1   0        // !!! no move !!!
      //    5            0   1   =>   5            0   1    // !!! no move !!!
      writeMetaState(e, LEFTa_u0_p1, RIGHTa_p1_u0, ALL_xX_xX, LEFTa_u0_p1, RIGHTa_p1_u0);    // Level Down?
      writeMetaState(e, LEFTb_u0_p1, RIGHTa_p1_u0, ALL_xX_xX, LEFTb_u0_p1, RIGHTa_p1_u0);
      writeMetaState(e, LEFTa_u0_p1, RIGHTb_p1_u0, ALL_xX_xX, LEFTa_u0_p1, RIGHTb_p1_u0);
      writeMetaState(e, LEFTb_u0_p1, RIGHTb_p1_u0, ALL_xX_xX, LEFTb_u0_p1, RIGHTb_p1_u0);
      // 95/1/0:            >  11|11| 0    11|11| 1 >1981| 5| 0  1981| 5| 1 > 936| 0| 0   936| 0| 0
      // 95/1/1:    0| 0| 0     0| 0| 0 > 152| 9| 1   152| 9| 0 >1593| 7| 1  1593| 7| 0
      //                            11:RIGHTa_u0_p1           5:LEFTa_u0_p1
      //                                9:RIGHTa_p1_u0           7:LEFTa_p1_u0
       */
      //    *    *   *           =>   *    *   *
      //    9        1   0       =>   9        0   1        // collision + move
      //    5            0   1   =>   5            1   0
      writeMetaState(e, LEFTa_u0_p1, RIGHTa_p1_u0, ALL_xX_xX, RIGHTb_p1_u0, LEFTb_u0_p1);    // Move (or Level Down?)
      writeMetaState(e, LEFTb_u0_p1, RIGHTa_p1_u0, ALL_xX_xX, LEFTb_u0_p1, RIGHTa_p1_u0);
      writeMetaState(e, LEFTa_u0_p1, RIGHTb_p1_u0, ALL_xX_xX, LEFTa_u0_p1, RIGHTb_p1_u0);
      writeMetaState(e, LEFTb_u0_p1, RIGHTb_p1_u0, ALL_xX_xX, LEFTb_u0_p1, RIGHTb_p1_u0);
      //    *    *   *           =>   *    *   *
      //   11        0   1       =>   5        0   1        // collision
      //    5            0   1   =>  11            0   1    //
      writeMetaState(e, LEFTa_u0_p1, RIGHTa_u0_p1, ALL_xX_xX, RIGHTa_u0_p1, LEFTa_u0_p1);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // right:
      //    *    *   *           =>   *    *   *
      //    7        1   0       =>   0        0   0
      //    0            0   0   =>   7            1   0
      writeMetaState(e, NULL_u0_u0, RIGHTa_p1_u0, ALL_xX_xX, RIGHTb_p1_u0, NULL_u0_u0);
      writeMetaState(e, NULL_u0_u0, RIGHTb_p1_u0, ALL_xX_xX, NULL_u0_u0, RIGHTb_p1_u0);
      //    *    *   *           =>   *    *   *
      //    8        0   1       =>   0        0   0
      //    0            0   0   =>   8            0   1
      writeMetaState(e, NULL_u0_u0, RIGHTa_u0_p1, ALL_xX_xX, RIGHTb_u0_p1, NULL_u0_u0);
      writeMetaState(e, NULL_u0_u0, RIGHTb_u0_p1, ALL_xX_xX, NULL_u0_u0, RIGHTb_u0_p1);
      //    *    *   *           =>   *    *   *
      //    0        0   0       =>   0        0   0
      //    7            1   0   =>   7            1   0
      writeMetaState(e, RIGHTa_p1_u0, NULL_u0_u0, ALL_xX_xX, RIGHTa_p1_u0, NULL_u0_u0);
      writeMetaState(e, RIGHTb_p1_u0, NULL_u0_u0, ALL_xX_xX, RIGHTb_p1_u0, NULL_u0_u0);
      //    *    *   *           =>   *    *   *
      //    0        0   0       =>   0        0   0
      //    8            0   1   =>   8            0   1
      writeMetaState(e, RIGHTa_u0_p1, NULL_u0_u0, ALL_xX_xX, RIGHTa_u0_p1, NULL_u0_u0);
      writeMetaState(e, RIGHTb_u0_p1, NULL_u0_u0, ALL_xX_xX, RIGHTb_u0_p1, NULL_u0_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (right, right):
      //    *    *   *           =>   *    *   *
      //    8        0   1       =>   8        0   1       // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    8            0   1   =>   8            0   1   // !!! no move !!!  (!!! Alternativ: collision-left-left !!!)
      writeMetaState(e, RIGHTa_u0_p1, RIGHTa_u0_p1, ALL_xX_xX, RIGHTa_u0_p1, RIGHTa_u0_p1);
      writeMetaState(e, RIGHTb_u0_p1, RIGHTa_u0_p1, ALL_xX_xX, RIGHTb_u0_p1, RIGHTa_u0_p1);
      writeMetaState(e, RIGHTa_u0_p1, RIGHTb_u0_p1, ALL_xX_xX, RIGHTa_u0_p1, RIGHTb_u0_p1);
      writeMetaState(e, RIGHTb_u0_p1, RIGHTb_u0_p1, ALL_xX_xX, RIGHTb_u0_p1, RIGHTb_u0_p1);
      //    *    *   *           =>   *    *   *
      //    7        1   0       =>   7        1   0        // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    7            1   0   =>   7            1   0
      writeMetaState(e, RIGHTa_p1_u0, RIGHTa_p1_u0, ALL_xX_xX, RIGHTa_p1_u0, RIGHTa_p1_u0);
      writeMetaState(e, RIGHTb_p1_u0, RIGHTa_p1_u0, ALL_xX_xX, RIGHTb_p1_u0, RIGHTa_p1_u0);
      writeMetaState(e, RIGHTa_p1_u0, RIGHTb_p1_u0, ALL_xX_xX, RIGHTa_p1_u0, RIGHTb_p1_u0);
      writeMetaState(e, RIGHTb_p1_u0, RIGHTb_p1_u0, ALL_xX_xX, RIGHTb_p1_u0, RIGHTb_p1_u0);
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
         //writeMetaState(e, RIGHTa_p1_u0, RIGHTa_u0_p1, ALL_xX_xX, COLLa_RR_p1_p1, NULL_u0_u0);
         //    *    *   *           =>   *    *   *
         //   13        1   1       =>   9        1   0
         //    0            0   0   =>  11            0   1
         //writeMetaState(e, NULL_u0_u0, COLLa_RR_p1_p1, ALL_xX_xX, RIGHTa_u0_p1, RIGHTa_p1_u0);
      }

      //    *    *   *           =>   *    *   *
      //    *        0   1       =>   *        0   1        // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    *            1   0   =>   *            1   0
      writeMetaState(e, RIGHTa_p1_u0, RIGHTa_u0_p1, ALL_xX_xX, RIGHTa_p1_u0, RIGHTa_u0_p1);
      writeMetaState(e, RIGHTb_p1_u0, RIGHTa_u0_p1, ALL_xX_xX, RIGHTb_p1_u0, RIGHTa_u0_p1);
      writeMetaState(e, RIGHTa_p1_u0, RIGHTb_u0_p1, ALL_xX_xX, RIGHTa_p1_u0, RIGHTb_u0_p1);
      writeMetaState(e, RIGHTb_p1_u0, RIGHTb_u0_p1, ALL_xX_xX, RIGHTb_p1_u0, RIGHTb_u0_p1);

      // try
      //    *    0   1           =>   *    0   0
      //    *        1   0       =>   *        0   1
      //    *            0   0   =>   *            1   0
      writeMetaState(e, NULL_u0_u0, RIGHTa_p1_u0, RIGHTa_u0_p1,   RIGHTb_p1_u0, RIGHTb_u0_p1, NULL_u0_u0);

      //    *    *   *           =>   *    *   *
      //    7        1   0       =>   7        1   0        // !!! no move !!!  (!!! Unschön: bleibt stehen bis Platz ist !!!)
      //    8            0   1   =>   8            0   1
      writeMetaState(e, RIGHTa_u0_p1, RIGHTa_p1_u0, ALL_xX_xX, RIGHTa_u0_p1, RIGHTa_p1_u0);
      writeMetaState(e, RIGHTb_u0_p1, RIGHTa_p1_u0, ALL_xX_xX, RIGHTb_u0_p1, RIGHTa_p1_u0);
      writeMetaState(e, RIGHTa_u0_p1, RIGHTb_p1_u0, ALL_xX_xX, RIGHTa_u0_p1, RIGHTb_p1_u0);
      writeMetaState(e, RIGHTb_u0_p1, RIGHTb_p1_u0, ALL_xX_xX, RIGHTb_u0_p1, RIGHTb_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (right, stay):
      //    *    *   *           =>   *    *   *
      //    2        0   1       =>   2        0   1        // nothing to do
      //    8            0   1   =>   8            0   1
      writeMetaState(e, RIGHTa_u0_p1, STAYa_u0_p1, ALL_xX_xX, RIGHTa_u0_p1, STAYa_u0_p1);
      writeMetaState(e, RIGHTb_u0_p1, STAYa_u0_p1, ALL_xX_xX, RIGHTb_u0_p1, STAYa_u0_p1);
      writeMetaState(e, RIGHTa_u0_p1, STAYb_u0_p1, ALL_xX_xX, RIGHTa_u0_p1, STAYb_u0_p1);
      writeMetaState(e, RIGHTb_u0_p1, STAYb_u0_p1, ALL_xX_xX, RIGHTb_u0_p1, STAYb_u0_p1);
      //    *    *   *           =>   *    *   *
      //    3        1   0       =>   5        1   0        // nothing to do
      //    8            0   1   =>   6            0   1
      writeMetaState(e, RIGHTa_u0_p1, STAYa_p1_u0, ALL_xX_xX, RIGHTa_u0_p1, STAYa_p1_u0);
      writeMetaState(e, RIGHTb_u0_p1, STAYa_p1_u0, ALL_xX_xX, RIGHTb_u0_p1, STAYa_p1_u0);
      writeMetaState(e, RIGHTa_u0_p1, STAYb_p1_u0, ALL_xX_xX, RIGHTa_u0_p1, STAYb_p1_u0);
      writeMetaState(e, RIGHTb_u0_p1, STAYb_p1_u0, ALL_xX_xX, RIGHTb_u0_p1, STAYb_p1_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // collision (right, left):
      //    *    *   *           =>   *    *   *
      //    5        0   1       =>   5        0   1       // nothing to do
      //   11            0   1   =>  11            0   1
      writeMetaState(e, RIGHTa_u0_p1, LEFTa_u0_p1, ALL_xX_xX, RIGHTa_u0_p1, LEFTa_u0_p1);
      writeMetaState(e, RIGHTb_u0_p1, LEFTa_u0_p1, ALL_xX_xX, RIGHTb_u0_p1, LEFTa_u0_p1);
      writeMetaState(e, RIGHTa_u0_p1, LEFTb_u0_p1, ALL_xX_xX, RIGHTa_u0_p1, LEFTb_u0_p1);
      writeMetaState(e, RIGHTb_u0_p1, LEFTb_u0_p1, ALL_xX_xX, RIGHTb_u0_p1, LEFTb_u0_p1);
      //    *    *   *           =>   *    *   *
      //    7        1   0       =>   7        1   0       // nothing to do
      //   11            0   1   =>  11            0   1
      writeMetaState(e, RIGHTa_u0_p1, LEFTa_p1_u0, ALL_xX_xX, RIGHTa_u0_p1, LEFTa_p1_u0);
      writeMetaState(e, RIGHTb_u0_p1, LEFTa_p1_u0, ALL_xX_xX, RIGHTb_u0_p1, LEFTa_p1_u0);
      writeMetaState(e, RIGHTa_u0_p1, LEFTb_p1_u0, ALL_xX_xX, RIGHTa_u0_p1, LEFTb_p1_u0);
      writeMetaState(e, RIGHTb_u0_p1, LEFTb_p1_u0, ALL_xX_xX, RIGHTb_u0_p1, LEFTb_p1_u0);
      //    *    *   *           =>   *    *   *
      //    5        0   1       =>   5        0   1       // nothing to do
      //    9            1   0   =>   9            1   0
      writeMetaState(e, RIGHTa_p1_u0, LEFTa_u0_p1, ALL_xX_xX, RIGHTa_p1_u0, LEFTa_u0_p1);
      writeMetaState(e, RIGHTb_p1_u0, LEFTa_u0_p1, ALL_xX_xX, RIGHTb_p1_u0, LEFTa_u0_p1);
      writeMetaState(e, RIGHTa_p1_u0, LEFTb_u0_p1, ALL_xX_xX, RIGHTa_p1_u0, LEFTb_u0_p1);
      writeMetaState(e, RIGHTb_p1_u0, LEFTb_u0_p1, ALL_xX_xX, RIGHTb_p1_u0, LEFTb_u0_p1);
      //    2    0   1       =>   2    0   1
      //    9        1   1   =>   9        1   1
      //writeMetaState(e, COLL_LR_p1_p1, STAYb_u0_p1, COLL_LR_p1_p1, STAYb_u0_p1);
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
      level1moveEngine.metaStateArr[metaPos(e, NULL_u0_u0, NULL_u0_u0)].levelUpOutputMetaStatePosArr[metaPos(e, NULL_u0_u0, NULL_u0_u0)] = metaPos(e, RIGHTa_p1_u0, 3);
      // stay -> 1 (1:stay <- 1:stay -> 7:collision)
      // 1,0  =>  7,1
      //    0    0   0       =>   1    0   1       0:null -> 1:stay
      //    1        0   1   =>   7        1   1   1:stay -> 7:collision
      level1moveEngine.metaStateArr[metaPos(e, STAYa_u0_p1, NULL_u0_u0)].levelUpOutputMetaStatePosArr[metaPos(e, NULL_u0_u0, NULL_u0_u0)] = metaPos(e, 7, 1);
      // stay -> 1 (3:left <- 3:left -> 7:collision)
      // 3,0  =>  7,3
      //    0    0   0       =>   3    0   1       0:null -> 3:left
      //    3        0   1   =>   7        1   1   3:left -> 7:collision
      level1moveEngine.metaStateArr[metaPos(e, LEFTa_u0_p1, NULL_u0_u0)].levelUpOutputMetaStatePosArr[metaPos(e, NULL_u0_u0, NULL_u0_u0)] = metaPos(e, 7, 3);
      // stay -> 1 (6:right <- 6:right -> 7:collision)
      // 6,0  =>  7,6
      //    0    0   0       =>   6    0   1       0:null -> 6:right
      //    6        0   1   =>   7        1   1   6:right -> 7:collision
      level1moveEngine.metaStateArr[metaPos(e, RIGHTa_u0_p1, NULL_u0_u0)].levelUpOutputMetaStatePosArr[metaPos(e, NULL_u0_u0, NULL_u0_u0)] = metaPos(e, 7, 6);

      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      return level1moveEngine;
   }
}
