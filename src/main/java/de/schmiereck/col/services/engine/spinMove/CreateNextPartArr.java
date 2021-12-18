package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.NULL_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.LEFTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.NULL_u0_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_p1_u0_u0;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.NextPart;
import de.schmiereck.col.model.Universe;

public class CreateNextPartArr {
   /**
    * A Reflection: verry good, but a lot of Parts
    *                          (S)
    *               3R  2   1         6
    *   => Part
    *               4R  2             6
    *                       1L        2/2
    *   => State
    *                   4R  2         12
    *                   1L            4/2
    *   => Part
    *                   4R            4
    *                       2L        4/2
    *                   1L            4/4
    *   => State
    *                       1R        3
    *                   1L            2
    *               1L                1
    *   => Part
    *                       1L
    *                   1L
    *               1L
    */
   public static void createNextPartArrA(final Universe universe) {
      final Engine l0E = universe.fieldEngine.engineArr[0];
      final Engine l1E = universe.fieldEngine.engineArr[1];
      final Engine l2E = universe.fieldEngine.engineArr[2];

      //----------------------------------------------------------------------------------------------------------------
      // 2right 1stay:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //              0   1   2   3   4
      //                  -   -
      //                      -   S          b
      //    x -   -   -
      //    x     -   -   -
      //    x         R   -   -              a
      // =>
      //    x     -   -
      //    x         R   -                  a
      //                      L              c
      universe.fieldEngine.nextPartArr
              [2] // aPart.levelPos
              [1] // bPart.levelPos
              [2] // absDiff
              [metaPos(l2E, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0)] // aPart metaStatePos
              [metaPos(l1E, STAYa_u0_p1, NULL_u0_u0)] // bPart metaStatePos
              = new NextPart(1, metaPos(l1E, RIGHTa_p1_u0, NULL_u0_u0), +0,
              0, metaPos(l0E, LEFTa_p1), +2);

      //              0   1   2   3   4
      //                          S   -
      //                              -   -  b
      //    x             R   -              a
      //    x                 -   -
      // =>
      //    x             R                  a
      //    x                 L              c
      universe.fieldEngine.nextPartArr
              [1] // aPart.levelPos
              [1] // bPart.levelPos
              [2] // absDiff
              [metaPos(l1E, NULL_u0_u0, RIGHTa_p1_u0)] // aPart metaStatePos
              [metaPos(l1E, NULL_u0_u0, STAYa_p1_u0)] // bPart metaStatePos
              = new NextPart(0, metaPos(l0E, RIGHTa_p1), -1,
              0, metaPos(l0E, LEFTa_p1), +0);

      //              0   1   2   3   4
      //                          S   -
      //                              -   -  b
      //    x                 R              a
      // =>
      //    x                 L              c
      universe.fieldEngine.nextPartArr
              [0] // aPart.levelPos
              [1] // bPart.levelPos
              [2] // absDiff
              [metaPos(l0E, RIGHTa_p1)] // aPart metaStatePos
              [metaPos(l1E, NULL_u0_u0, STAYa_p1_u0)] // bPart metaStatePos
              = new NextPart(0, metaPos(l0E, LEFTa_p1), +0);

      //              0   1   2   3   4
      //                  -   -
      //                      -   S          b
      //    x                 R              a
      // =>
      //    x                 L              c
      universe.fieldEngine.nextPartArr
              [0] // aPart.levelPos
              [1] // bPart.levelPos
              [0] // absDiff
              [metaPos(l0E, RIGHTa_p1)] // aPart metaStatePos
              [metaPos(l1E, STAYa_u0_p1, NULL_u0_u0)] // bPart metaStatePos
              = new NextPart(0, metaPos(l0E, LEFTa_p1), +0);
   }

   /**
    * B Reflection: ok, but stange
    *                          (S)
    *                   3R  2   1         6
    *   => Part
    *                   3R                3
    *                   1   2L            3
    *   => State
    *                       3R            3
    *               1   2L                3
    *   => Part
    *                       3L            3
    *               1   2L                1,5
    *               -----------
    *               1   2   3
    */
   public static void createNextPartArrX(final Universe universe) {
      final Engine l0E = universe.fieldEngine.engineArr[0];
      final Engine l1E = universe.fieldEngine.engineArr[1];
      final Engine l2E = universe.fieldEngine.engineArr[2];

      //                  0   1   2   3
      //                      -   -
      //                          S   -      b
      //    x     -   -   -
      //    x         -   -   -
      //    x             R   -   -          a
      // =>
      //    x             R                  a
      //              -   -
      //                  -   L              c
      universe.fieldEngine.nextPartArr
              [2] // aPart.levelPos
              [1] // bPart.levelPos
              [2] // absDiff
              [metaPos(l2E, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0)] // aPart metaStatePos
              [metaPos(l1E, STAYa_p1_u0, NULL_u0_u0)] // bPart metaStatePos
              = new NextPart(0, metaPos(l0E, RIGHTa_p1), +0,
                1, metaPos(l1E, LEFTa_u0_p1, NULL_u0_u0), +0);

      //          0   1   2   3
      //              -   S
      //                  -   -
      //              R   -   -
      //                  -   -   -
      //                      -   -   -
      // =>
      //  -   -   -
      //      -   -   L
      //          -   -   -
      universe.fieldEngine.nextPartArr
              [2] // aPart.levelPos
              [1] // bPart.levelPos
              [1] // absDiff
              [metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_p1_u0_u0)] // aPart metaStatePos
              [metaPos(l1E, NULL_u0_u0, STAYa_u0_p1)] // bPart metaStatePos
              = new NextPart(2, metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0), 0,
              2, metaPos(l2E, NULL_u0_u0_u0, LEFTa_u0_u0_p1, NULL_u0_u0_u0), -3);
   }
}
