package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.FieldEngine.l0EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l2EnginePos;
import static de.schmiereck.col.model.NextPart.Command.CmdCombineToParent;
import static de.schmiereck.col.services.FieldEngineService.calcRel2ArrPos;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.LEFTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.NULL_u0_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_p1_u0_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SNULL_u0_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_p1_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_u0_p1;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
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
      createNextPart2rightReflection1stay(universe.fieldEngine);
      createNextPart2leftReflection1stay(universe.fieldEngine);
      if (universe.use_levelUp) {
         createNextPartLevelUpLeft(universe.fieldEngine);
         createNextPartLevelUpRight(universe.fieldEngine);
      }
      //----------------------------------------------------------------------------------------------------------------
   }

   private static class MetaPosArg {
      final int metaPos;
      final int absDiffOff;

      private MetaPosArg(final int metaPos, final int absDiffOff) {
         this.metaPos = metaPos;
         this.absDiffOff = absDiffOff;
      }
   }

   private static void createNextPart2rightReflection1stay(final FieldEngine fieldEngine) {
      final Engine l0E = fieldEngine.engineArr[l0EnginePos];
      final Engine l1E = fieldEngine.engineArr[l1EnginePos];
      final Engine l2E = fieldEngine.engineArr[l2EnginePos];
      final Engine l1SE = fieldEngine.engineArr[l1StayEnginePos];

      //              0   1   2   3   4
      //                  -   -
      //                      -   S          b
      //              0   1   2   3   4
      //                          S   -      b
      //                              -   -
      final MetaPosArg[] stayMetaPosArgArr = {
              new MetaPosArg(metaPos(l1SE, SSTAY_u0_p1, NULL_u0_u0), 0),
              new MetaPosArg(metaPos(l1SE, NULL_u0_u0, SSTAY_p1_u0), 2)
      };
      //----------------------------------------------------------------------------------------------------------------
      // 2right reflection 1stay:
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
      setNextPart(fieldEngine, l2EnginePos, l1StayEnginePos, 2,
                  metaPos(l2E, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0),
                  stayMetaPosArgArr,
                     new NextPart(l1EnginePos, metaPos(l1E, RIGHTa_p1_u0, NULL_u0_u0), +0,
                                  l0EnginePos, metaPos(l0E, LEFTa_p1), +2));

      //              0   1   2   3   4
      //                  -   -
      //                      -   S          b
      //    x             R   -              a
      //    x                 -   -
      // =>
      //    x             R                  a
      //    x                 L              c
      setNextPart(fieldEngine, l1EnginePos, l1StayEnginePos, 0,
              metaPos(l1E, NULL_u0_u0, RIGHTa_p1_u0),
              stayMetaPosArgArr,
               new NextPart(l0EnginePos, metaPos(l0E, RIGHTa_p1), -1,
              0, metaPos(l0E, LEFTa_p1), +0));

      //              0   1   2   3   4
      //                  -   -
      //                      -   S          b
      //    x                 R              a
      // =>
      //    x                 L              c
      setNextPart(fieldEngine, l0EnginePos, l1StayEnginePos, 0,
              metaPos(l0E, RIGHTa_p1),
              stayMetaPosArgArr,
               new NextPart(l0EnginePos, metaPos(l0E, LEFTa_p1), +0));
   }


   private static void createNextPart2leftReflection1stay(final FieldEngine fieldEngine) {
      final Engine l0E = fieldEngine.engineArr[l0EnginePos];
      final Engine l1E = fieldEngine.engineArr[l1EnginePos];
      final Engine l2E = fieldEngine.engineArr[l2EnginePos];
      final Engine l1SE = fieldEngine.engineArr[l1StayEnginePos];

      //              0   1   2   3   4
      //                  -   -
      //                      -   S          b
      //              0   1   2   3   4
      //                          S   -      b
      //                              -   -
      final MetaPosArg[] stayMetaPosArgArr = {
              new MetaPosArg(metaPos(l1SE, SSTAY_u0_p1, NULL_u0_u0), 0),
              new MetaPosArg(metaPos(l1SE, NULL_u0_u0, SSTAY_p1_u0), 2)
      };
      //----------------------------------------------------------------------------------------------------------------
      // 2right reflection 1stay:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //              0   1   2   3   4
      //          -   -
      //              -   S                  b
      //    x             -   -   -
      //    x                 -   -   L      a
      //    x                     -   -   -
      // =>
      //    x                     -   L      a
      //    x                         -   -
      //                      R              c
      setNextPart(fieldEngine, l2EnginePos, l1StayEnginePos, -3,
              metaPos(l2E, NULL_u0_u0_u0, LEFTa_u0_u0_p1, NULL_u0_u0_u0),
              stayMetaPosArgArr,
              new NextPart(l1EnginePos, metaPos(l1E, NULL_u0_u0, LEFTa_u0_p1), +1,
                           l0EnginePos, metaPos(l0E, RIGHTa_p1), -1));

      //              0   1   2   3   4
      //          -   -
      //              -   S                  b
      //    x             -   -              a
      //    x                 -   L
      // =>
      //    x                     L          a
      //    x                 R              c
      setNextPart(fieldEngine, l1EnginePos, l1StayEnginePos, -2,
              metaPos(l1E, LEFTa_u0_p1, NULL_u0_u0),
              stayMetaPosArgArr,
              new NextPart(l0EnginePos, metaPos(l0E, LEFTa_p1), +1,
                      0, metaPos(l0E, RIGHTa_p1), +0));

      //              0   1   2   3   4
      //          -   -
      //              -   S                  b
      //    x                 L              a
      // =>
      //    x                 R              c
      setNextPart(fieldEngine, l0EnginePos, l1StayEnginePos, -2,
              metaPos(l0E, LEFTa_p1),
              stayMetaPosArgArr,
              new NextPart(l0EnginePos, metaPos(l0E, RIGHTa_p1), +0));
   }

   private static void createNextPartLevelUpLeft(final FieldEngine fieldEngine) {
      final Engine l0E = fieldEngine.engineArr[l0EnginePos];
      final Engine l1E = fieldEngine.engineArr[l1EnginePos];
      final Engine l2E = fieldEngine.engineArr[l2EnginePos];
      final Engine l1SE = fieldEngine.engineArr[l1StayEnginePos];

      //----------------------------------------------------------------------------------------------------------------
      // combine (level up) left:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //              0A  1B  2A  3B  4A
      //                  L                  b
      //    x         L                      a
      // =>
      //    x     -   -
      //    x         L   -                  a

      //              0A  1B  2A  3B  4A
      //                      L              b
      //    x             L                  a
      // =>
      //    x             L   -              a
      //    x                 -   -
      setNextPart(fieldEngine, l0EnginePos, l0EnginePos, 1,
              metaPos(l0E, LEFTa_p1),
              metaPos(l0E, LEFTa_p1),
               new NextPart(CmdCombineToParent,
                  new NextPart.NextPartArgument(l1EnginePos, metaPos(l1E, LEFTa_p1_u0, NULL_u0_u0), +0),
                  new NextPart.NextPartArgument(l1EnginePos, metaPos(l1E, NULL_u0_u0, LEFTa_p1_u0), +1)));

      //              0A  1B  2A  3B  4A
      //              L                      b
      //    x             L                  a
      // =>
      //    x     -   -
      //    x         -   L                  a

      //              0A  1B  2A  3B  4A
      //                  L                  b
      //    x                 L              a
      // =>
      //    x             -   L              a
      //    x                 -   -
      setNextPart(fieldEngine, l0EnginePos, l0EnginePos, -1,
              metaPos(l0E, LEFTa_p1),
              metaPos(l0E, LEFTa_p1),
               new NextPart(CmdCombineToParent,
                  new NextPart.NextPartArgument(l1EnginePos, metaPos(l1E, NULL_u0_u0, LEFTa_u0_p1), +0),
                  new NextPart.NextPartArgument(l1EnginePos, metaPos(l1E, LEFTa_u0_p1, NULL_u0_u0), -1)));

      //              0A  1B  2A  3B  4A
      //              L                      b
      //    x             -   L              a
      //    x                 -   -
      // =>
      //    x -   -   -
      //    x     -   -   -
      //    x         -   -   L              a
      setNextPart(fieldEngine, l1EnginePos, l0EnginePos, -2,
              metaPos(l1E, NULL_u0_u0, LEFTa_u0_p1),
              metaPos(l0E, LEFTa_p1),
               new NextPart(CmdCombineToParent,
                  new NextPart.NextPartArgument(l2EnginePos, metaPos(l2E, LEFTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0), -2)));


      //              0A  1B  2A  3B  4A
      //                  L                  b
      //    x             -   -
      //    x                 -   L          a
      // =>
      //    x             -   -   L
      //    x                 -   -   -      a
      //    x                     -   -   -
      setNextPart(fieldEngine, l1EnginePos, l0EnginePos, -1,
              metaPos(l1E, LEFTa_u0_p1, NULL_u0_u0),
              metaPos(l0E, LEFTa_p1),
               new NextPart(CmdCombineToParent,
                  new NextPart.NextPartArgument(l2EnginePos, metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, LEFTa_u0_u0_p1), +1)));
   }


   private static void createNextPartLevelUpRight(final FieldEngine fieldEngine) {
      final Engine l0E = fieldEngine.engineArr[l0EnginePos];
      final Engine l1E = fieldEngine.engineArr[l1EnginePos];
      final Engine l2E = fieldEngine.engineArr[l2EnginePos];
      final Engine l1SE = fieldEngine.engineArr[l1StayEnginePos];

      //----------------------------------------------------------------------------------------------------------------
      // combine (level up) left:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //              0A  1B  2A  3B  4A
      //          R                  b
      //    x         R              a
      // =>
      //    x     -   R              a
      //    x         -   -
      //              0A  1B  2A  3B  4A
      //              R                      b
      //    x             R                  a
      // =>
      //    x     -   -
      //    x         -   R                  a
      setNextPart(fieldEngine, l0EnginePos, l0EnginePos, -1,
              metaPos(l0E, RIGHTa_p1),
              metaPos(l0E, RIGHTa_p1),
              new NextPart(CmdCombineToParent,
                      new NextPart.NextPartArgument(l1EnginePos, metaPos(l1E, NULL_u0_u0, RIGHTa_u0_p1), +0),
                      new NextPart.NextPartArgument(l1EnginePos, metaPos(l1E, RIGHTa_u0_p1, NULL_u0_u0), -1)));

      //              0A  1B  2A  3B  4A
      //                  R                  b
      //    x         R                      a
      // =>
      //    x     -   -
      //    x         R   -                  a

      //              0A  1B  2A  3B  4A
      //                      R              b
      //    x             R                  a
      // =>
      //    x             R   -              a
      //    x                 -   -
      setNextPart(fieldEngine, l0EnginePos, l0EnginePos, -1,
              metaPos(l0E, RIGHTa_p1),
              metaPos(l0E, RIGHTa_p1),
              new NextPart(CmdCombineToParent,
                      new NextPart.NextPartArgument(l1EnginePos, metaPos(l1E, RIGHTa_p1_u0, NULL_u0_u0), +0),
                      new NextPart.NextPartArgument(l1EnginePos, metaPos(l1E, NULL_u0_u0, RIGHTa_p1_u0), +1)));

      //              0A  1B  2A  3B  4A
      //              L                      b
      //    x             -   L              a
      //    x                 -   -
      // =>
      //    x -   -   -
      //    x     -   -   -
      //    x         -   -   L              a
      setNextPart(fieldEngine, l1EnginePos, l0EnginePos, -2,
              metaPos(l1E, NULL_u0_u0, LEFTa_u0_p1),
              metaPos(l0E, LEFTa_p1),
              new NextPart(CmdCombineToParent,
                      new NextPart.NextPartArgument(l2EnginePos, metaPos(l2E, LEFTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0), -2)));


      //              0A  1B  2A  3B  4A
      //                  L                  b
      //    x             -   -
      //    x                 -   L          a
      // =>
      //    x             -   -   L
      //    x                 -   -   -      a
      //    x                     -   -   -
      setNextPart(fieldEngine, l1EnginePos, l0EnginePos, -1,
              metaPos(l1E, LEFTa_u0_p1, NULL_u0_u0),
              metaPos(l0E, LEFTa_p1),
              new NextPart(CmdCombineToParent,
                      new NextPart.NextPartArgument(l2EnginePos, metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, LEFTa_u0_u0_p1), +1)));
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
   public static void createNextPartArrX(final Universe universe, final int l1StayEnginePos) {
      final Engine l0E = universe.fieldEngine.engineArr[l0EnginePos];
      final Engine l1E = universe.fieldEngine.engineArr[l1EnginePos];
      final Engine l2E = universe.fieldEngine.engineArr[l2EnginePos];
      final Engine l1SE = universe.fieldEngine.engineArr[l1StayEnginePos];

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
              [l2EnginePos] // aPart.enginePos
              [l1StayEnginePos] // bPart.enginePos
              [calcRel2ArrPos(2)] // absDiff
              [metaPos(l2E, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0)] // aPart metaStatePos
              [metaPos(l1SE, SSTAY_p1_u0, SNULL_u0_u0)] // bPart metaStatePos
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
              [l2EnginePos] // aPart.enginePos
              [l1StayEnginePos] // bPart.enginePos
              [calcRel2ArrPos(1)] // absDiff
              [metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_p1_u0_u0)] // aPart metaStatePos
              [metaPos(l1SE, NULL_u0_u0, SSTAY_u0_p1)] // bPart metaStatePos
              = new NextPart(2, metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0), 0,
              2, metaPos(l2E, NULL_u0_u0_u0, LEFTa_u0_u0_p1, NULL_u0_u0_u0), -3);
   }

   private static void setNextPart(final FieldEngine fieldEngine,
                                   final int aPartEnginePos, final int bPartEnginePos,
                                   final int absDiff,
                                   final int aPartMetaStatePos,
                                   final MetaPosArg[] bPartMetaPosArgArr,
                                   final NextPart nextPart) {
      for (final MetaPosArg bPartMetaPosArg : bPartMetaPosArgArr) {
         setNextPart(fieldEngine,
                     aPartEnginePos, bPartEnginePos,
                    absDiff + bPartMetaPosArg.absDiffOff,
                     aPartMetaStatePos,
                     bPartMetaPosArg.metaPos,
                     nextPart);
      }
   }

   private static void setNextPart(final FieldEngine fieldEngine,
                                   final int aPartEnginePos, final int bPartEnginePos,
                                   final int absDiff,
                                   final int aPartMetaStatePos,
                                   final int bPartMetaStatePos,
                                   final NextPart nextPart) {
      fieldEngine.nextPartArr
              [aPartEnginePos] // aPart.enginePos
              [bPartEnginePos] // bPart.enginePos
              [calcRel2ArrPos(absDiff)] // absDiff
              [aPartMetaStatePos] // aPart metaStatePos
              [bPartMetaStatePos] // bPart metaStatePos
              = nextPart;
   }
}
