package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.FieldEngine.NPMS_L0_S1_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L1_S00_S01_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L2_S000_S000_S001_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L2_S000_S001_S000_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L2_S001_S000_S000_Pos;
import static de.schmiereck.col.model.FieldEngine.l0EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l2EnginePos;
import static de.schmiereck.col.model.FieldEngine.l3EnginePos;
import static de.schmiereck.col.model.NextPart.Command.CmdCombineToParent;
import static de.schmiereck.col.model.NextPart.Command.CmdNext;
import static de.schmiereck.col.model.NextPart.Command.CmdNextNew;
import static de.schmiereck.col.model.NextPart.LR_CONTINUE_MATRIX;
import static de.schmiereck.col.model.NextPart.LR_REFLECTION_MATRIX;
import static de.schmiereck.col.services.FieldEngineService.setNextPart;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.NULL_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.LEFTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.NULL_u0_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel3SpinMoveEngineService.LEFTa_u0_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel3SpinMoveEngineService.NULL_u0_u0_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcNextPartMetaStatePosArr;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_p1_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_u0_p1;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.NextPart;

public class NextPartLeftLxL1StayCreateService {

   static void createNextPartLeftReflection1stay(final FieldEngine fieldEngine) {
      final Engine l0E = fieldEngine.engineArr[l0EnginePos];
      final Engine l1E = fieldEngine.engineArr[l1EnginePos];
      final Engine l2E = fieldEngine.engineArr[l2EnginePos];
      final Engine l3E = fieldEngine.engineArr[l3EnginePos];
      final Engine l1SE = fieldEngine.engineArr[l1StayEnginePos];

      final int l0EP = l0EnginePos;
      final int l1EP = l1EnginePos;
      final int l2EP = l2EnginePos;
      final int l3EP = l3EnginePos;
      final int l1SEP = l1StayEnginePos;

      //              0   1   2   3   4
      //                  -   -
      //                      -   S          b
      //              0   1   2   3   4
      //                          S   -      b
      //                              -   -
      final NextPartCreateService.MetaPosArg[] l1StayMetaPosArgArr = {
              new NextPartCreateService.MetaPosArg(metaPos(l1SE, SSTAY_u0_p1, NULL_u0_u0), 0),
              new NextPartCreateService.MetaPosArg(metaPos(l1SE, NULL_u0_u0, SSTAY_p1_u0), 2)
      };
      final NextPartCreateService.MetaPosArg[] l1SMPArr = l1StayMetaPosArgArr;
      final int mp_L0_L1_N0 = metaPos(l0E, LEFTa_p1, NULL_u0);
      final int mp_L1_L01_N00 = metaPos(l1E, LEFTa_u0_p1, NULL_u0_u0);
      final int mp_L2_L001_N000_N000 = metaPos(l2E, LEFTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0);
      final int mp_L2_N000_L001_N000 = metaPos(l2E, NULL_u0_u0_u0, LEFTa_u0_u0_p1, NULL_u0_u0_u0);
      final int mp_L3_N0000_N0000_L0001_N0000 = metaPos(l3E, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, LEFTa_u0_u0_u0_p1, NULL_u0_u0_u0_u0);
      final int mp_L3_L0001_N0000_N0000_N0000 = metaPos(l3E, LEFTa_u0_u0_u0_p1, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0);
      //----------------------------------------------------------------------------------------------------------------
      // 0left reflection 1stay:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //              L0  L0  L0  L0  L0  L0  L0
      //              L1      L1      L1      L1
      //              0   1   2   3   4   5
      //          -   -
      //              -   S                  b
      //    x                 L              a
      // =>
      //    x                 R              c
      setNP(fieldEngine, l0EP, mp_L0_L1_N0, l1SEP, l1SMPArr, -2,               +0);
      //----------------------------------------------------------------------------------------------------------------
      // 1left reflection 1stay:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //              L1      L1      L1      L1
      //              L2          L2          L2
      //              0   1   2   3   4   5
      //          -   -
      //              -   S                  b
      //    x             -   -              a
      //    x                 -   L
      // =>
      //    x                     L          a
      //    x                 R              c
      setNP(fieldEngine, l1EP, mp_L1_L01_N00, l1SEP, l1SMPArr, -2,             l0EP, NPMS_L0_S1_Pos, +1,           l0EP, NPMS_L0_S1_Pos, +0);
      //----------------------------------------------------------------------------------------------------------------
      // 2left reflection 1stay (Stay-Pos 0):
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //              L1      L1      L1      L1
      //              L2          L2          L2
      //              0   1   2   3   4   5
      //          -   -
      //              -   S                  b
      //    x             -   -   -
      //    x                 -   -   L      a
      //    x                     -   -   -
      // =>
      //    x                     -   L      a
      //    x                         -   -
      //                      R              c
      setNP(fieldEngine, l2EP, mp_L2_N000_L001_N000, l1SEP, l1SMPArr, -3,      l1EP, NPMS_L1_S00_S01_Pos, +1,      l0EP, NPMS_L0_S1_Pos, -1);
      //----------------------------------------------------------------------------------------------------------------
      // 2left reflection 1stay (Stay-Pos 4):
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //      L1      L1      L1      L1      L1      L1      L1      L1      L1
      //                      L2          L2          L2          L2          L2
      //                      L3              L3              L3              L3
      //     -4  -3  -2  -1   0   1   2   3   4   5   6   7   8   9  10  11
      //                                  3   4   5   0/6 1/7 2/8
      //                                  -   -
      //                                      -   S              b
      //    x                                 -   -   -
      //    x                                     -   -   -
      //    x                                         -   -   L  a
      // =>
      //    x                                             -   L      a
      //    x                                                 -   -
      //                                              R          c
      setNP(fieldEngine, l2EP, mp_L2_L001_N000_N000, l1SEP, l1SMPArr, -2,      l1EP, NPMS_L1_S00_S01_Pos, +2,      l0EP, NPMS_L0_S1_Pos, +0);
      //----------------------------------------------------------------------------------------------------------------
      // 3left reflection 1stay (L-Pos:4, Stay-Pos 0):
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //      L1      L1      L1      L1      L1      L1      L1      L1      L1
      //                      L2          L2          L2          L2          L2
      //                      L3              L3              L3              L3
      //     -4  -3  -2  -1   0   1   2   3   4   5   6   7   8   9  10  11
      //                  -   -
      //                      -   S                  b
      //    x                     -   -   -   -
      //    x                         -   -   -   L
      //    x                             -   -   -   -
      //    x                                 -   -   -   -
      // =>
      //    x                     -   -   -
      //    x                         -   -   -      a
      //    x                             -   -   L
      //                              R              c
      setNP(fieldEngine, l3EP, mp_L3_N0000_N0000_L0001_N0000, l1SEP, l1SMPArr, -4,      l2EP, NPMS_L2_S001_S000_S000_Pos, -1,      l0EP, NPMS_L0_S1_Pos, -2,
                                                                                        l2EP, NPMS_L2_S000_S000_S001_Pos, +1,      l0EP, NPMS_L0_S1_Pos, -2);
      //----------------------------------------------------------------------------------------------------------------
      // 3left reflection 1stay (L-Pos:4, Stay-Pos 2):
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //      L1      L1      L1      L1      L1      L1      L1      L1      L1
      //                      L2          L2          L2          L2          L2
      //                      L3              L3              L3              L3
      //     -4  -3  -2  -1   0   1   2   3   4   5   6   7   8   9  10  11
      //                          -   -
      //                              -   S                           b-L1
      //    x                     -   -   -   -
      //    x                         -   -   -   -
      //    x                             -   -   -   -
      //    x                                 -   -   -   L           a-L3
      // =>    |        |            |
      //    x                                 -   -   -
      //    x                                     -   -   L           a-L2
      //    x                                         -   -   -
      //                                      R                       c-L0
      setNP(fieldEngine, l3EP, mp_L3_L0001_N0000_N0000_N0000, l1SEP, l1SMPArr, -2,      l2EP, NPMS_L2_S000_S001_S000_Pos, +2,      l0EP, NPMS_L0_S1_Pos, +0);
      //----------------------------------------------------------------------------------------------------------------
      // 3left reflection 1stay (L-Pos:8, Stay-Pos 4):
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //      L1      L1      L1      L1      L1      L1      L1      L1      L1
      //                      L2          L2          L2          L2          L2
      //                      L3              L3              L3              L3
      //     -4  -3  -2  -1   0   1   2   3   4   5   6   7   8   9  10  11
      //                                  -   -
      //                                      -   S                               b-L1
      //    x                                     -   -   -   -
      //    x                                         -   -   -   L               a-L3
      //    x                                             -   -   -   -
      //    x                                                 -   -   -   -
      // =>    |        |            |
      //    x                                             -   -   L               a-L2
      //    x                                                 -   -   -
      //    x                                                     -   -   -
      //                                              R                           c-L0
      // see (L-Pos:4, Stay-Pos 0)
      //setNP(fieldEngine, l3EP, mp_L3_N0000_N0000_L0001_N0000, l1SEP, l1SMPArr, -4,      l2EP, NPMS_L2_S000_S000_S001_Pos, +1,      l0EP, NPMS_L0_S1_Pos, -2);
      //----------------------------------------------------------------------------------------------------------------
   }

   public static void setNP(final FieldEngine fieldEngine,
                            final int aPartEnginePos, final int aPartMetaStatePos,
                            final int bPartEnginePos, final NextPartCreateService.MetaPosArg[] bPartMetaPosArgArr,
                            final int absDiff,
                            final int nextPartOffsetCellPos) {
      setNextPart(fieldEngine, aPartEnginePos, aPartMetaStatePos, bPartEnginePos, bPartMetaPosArgArr, absDiff,
                  new NextPart(aPartEnginePos, LR_REFLECTION_MATRIX, nextPartOffsetCellPos));
   }

   public static void setNP(final FieldEngine fieldEngine,
                            final int aPartEnginePos, final int aPartMetaStatePos,
                            final int bPartEnginePos, final NextPartCreateService.MetaPosArg[] bPartMetaPosArgArr,
                            final int absDiff,
                            final int nextPartEnginePos, final int nextPartMetaStatePos, final int nextPartOffsetCellPos,
                            final int newPartEnginePos, final int newPartMetaStatePos, final int newPartOffsetCellPos) {
      setNextPart(fieldEngine, aPartEnginePos, aPartMetaStatePos, bPartEnginePos, bPartMetaPosArgArr, absDiff,
                  new NextPart(nextPartEnginePos, calcNextPartMetaStatePosArr(fieldEngine, nextPartEnginePos, nextPartMetaStatePos), LR_CONTINUE_MATRIX, nextPartOffsetCellPos,
                               newPartEnginePos, calcNextPartMetaStatePosArr(fieldEngine, newPartEnginePos, newPartMetaStatePos), LR_REFLECTION_MATRIX, newPartOffsetCellPos));
   }

   public static void setNP(final FieldEngine fieldEngine,
                            final int aPartEnginePos, final int aPartMetaStatePos,
                            final int bPartEnginePos, final NextPartCreateService.MetaPosArg[] bPartMetaPosArgArr,
                            final int absDiff,
                            final int nextPart0EnginePos, final int nextPart0MetaStatePos, final int nextPart0OffsetCellPos,
                            final int newPart0EnginePos, final int newPart0MetaStatePos, final int newPart0OffsetCellPos,
                            final int nextPart1EnginePos, final int nextPart1MetaStatePos, final int nextPart1OffsetCellPos,
                            final int newPart1EnginePos, final int newPart1MetaStatePos, final int newPart1OffsetCellPos) {
      setNextPart(fieldEngine, aPartEnginePos, aPartMetaStatePos, bPartEnginePos, bPartMetaPosArgArr, absDiff,
                  new NextPart(CmdNextNew,
                               new NextPart.NextPartArgument(nextPart0EnginePos, calcNextPartMetaStatePosArr(fieldEngine, nextPart0EnginePos, nextPart0MetaStatePos), LR_CONTINUE_MATRIX, nextPart0OffsetCellPos,
                                                             newPart0EnginePos, calcNextPartMetaStatePosArr(fieldEngine, newPart0EnginePos, newPart0MetaStatePos), LR_REFLECTION_MATRIX, newPart0OffsetCellPos),
                               new NextPart.NextPartArgument(nextPart1EnginePos, calcNextPartMetaStatePosArr(fieldEngine, nextPart1EnginePos, nextPart1MetaStatePos), LR_CONTINUE_MATRIX, nextPart1OffsetCellPos,
                                                             newPart1EnginePos, calcNextPartMetaStatePosArr(fieldEngine, newPart1EnginePos, newPart1MetaStatePos), LR_REFLECTION_MATRIX, newPart1OffsetCellPos)
                               ));
   }
}
