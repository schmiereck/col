package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.FieldEngine.NPMS_L0_S1_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L1_S00_S01_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L1_S10_S00_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L2_S000_S000_S100_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L2_S000_S100_S000_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L2_S001_S000_S000_Pos;
import static de.schmiereck.col.model.FieldEngine.l0EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l2EnginePos;
import static de.schmiereck.col.model.FieldEngine.l3EnginePos;
import static de.schmiereck.col.model.NextPart.LR_CONTINUE_MATRIX;
import static de.schmiereck.col.model.NextPart.LR_REFLECTION_MATRIX;
import static de.schmiereck.col.services.FieldEngineService.setNextPart;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.NULL_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.LEFTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.NULL_u0_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_p1_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel3SpinMoveEngineService.LEFTa_u0_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel3SpinMoveEngineService.NULL_u0_u0_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel3SpinMoveEngineService.RIGHTa_p1_u0_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel3SpinMoveEngineService.RIGHTa_u0_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcNextPartMetaStatePosArr;
import static de.schmiereck.col.services.engine.spinMove.NextPartLeftLxL1StayCreateService.setNP;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_p1_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_u0_p1;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.NextPart;

public class NextPartRightLxL1StayCreateService {

   static void createNextPartRightReflection1stay(final FieldEngine fieldEngine) {
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
      final int mp_L0_R1_N0 = metaPos(l0E, RIGHTa_p1, NULL_u0);
      final int mp_L1_R01_N00 = metaPos(l1E, RIGHTa_u0_p1, NULL_u0_u0);
      final int mp_L1_R10_N00 = metaPos(l1E, RIGHTa_p1_u0, NULL_u0_u0);
      final int mp_L1_N00_R10 = metaPos(l1E, NULL_u0_u0, RIGHTa_p1_u0);
      final int mp_L2_R001_N000_N000 = metaPos(l2E, RIGHTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0);
      final int mp_L2_N000_R001_N000 = metaPos(l2E, NULL_u0_u0_u0, RIGHTa_u0_u0_p1, NULL_u0_u0_u0);
      final int mp_L2_N000_R100_N000 = metaPos(l2E, NULL_u0_u0_u0, RIGHTa_p1_u0_u0, NULL_u0_u0_u0);
      final int mp_L2_R100_N000_N000 = metaPos(l2E, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0);
      final int mp_L3_N0000_N0000_R0001_N0000 = metaPos(l3E, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, RIGHTa_u0_u0_u0_p1, NULL_u0_u0_u0_u0);
      final int mp_L3_N0000_N0000_N0000_R1000 = metaPos(l3E, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0, RIGHTa_p1_u0_u0_u0);
      final int mp_L3_N0000_R1000_N0000_N0000 = metaPos(l3E, NULL_u0_u0_u0_u0, RIGHTa_p1_u0_u0_u0, NULL_u0_u0_u0_u0, NULL_u0_u0_u0_u0);
      //----------------------------------------------------------------------------------------------------------------
      // 0right reflection 1stay:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //              2   3   4   5   0   1
      //                  -   -
      //                      -   S          b
      //              2   3   4   5   0   1
      //                          S   -
      //                              -   -  b
      //    x             R   -              a
      //    x                 -   -
      // =>
      //    x             R                  a
      //    x                 L              c

      //              0   1   2   3   4
      //                  -   -
      //                      -   S          b
      //    x                 R              a
      // =>
      //    x                 L              c
      //setNextPart(fieldEngine, l0EnginePos, metaPos(l0E, RIGHTa_p1, NULL_u0), l1StayEnginePos, l1StayMetaPosArgArr, 0,
      //            new NextPart(l0EnginePos, LR_REFLECTION_MATRIX, +0));
      setNP(fieldEngine, l0EP, mp_L0_R1_N0, l1SEP, l1SMPArr, 0,               +0);
      //----------------------------------------------------------------------------------------------------------------
      // 1right reflection 1stay:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //              0   1   2   3   4
      //                  -   -
      //                      -   S          b
      //    x         -   -
      //    x             R   -              a
      // =>
      //    x         -
      //    x             R                  a
      //                      L              c
      //TODO Wrong a pos?!
      //setNextPart(fieldEngine, l1EnginePos, metaPos(l1E, RIGHTa_p1_u0, NULL_u0_u0), l1StayEnginePos, l1StayMetaPosArgArr, 2,
      //            new NextPart(l0EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos), LR_CONTINUE_MATRIX, +0,
      //                         l0EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos), LR_REFLECTION_MATRIX, +1));
      setNP(fieldEngine, l1EP, mp_L1_R10_N00, l1SEP, l1SMPArr, +2,             l0EP, NPMS_L0_S1_Pos, +0,           l0EP, NPMS_L0_S1_Pos, +1);

      //              0   1   2   3   4
      //                  -   -
      //                      -   S          b
      //    x             R   -              a
      //    x                 -   -
      // =>
      //    x             R                  a
      //    x                 L              c
      //setNextPart(fieldEngine, l1EnginePos, metaPos(l1E, NULL_u0_u0, RIGHTa_p1_u0), l1StayEnginePos, l1StayMetaPosArgArr, 0,
      //            new NextPart(l0EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos), LR_CONTINUE_MATRIX, -1,
      //                         l0EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos), LR_REFLECTION_MATRIX, +0));
      setNP(fieldEngine, l1EP, mp_L1_N00_R10, l1SEP, l1SMPArr, +0,             l0EP, NPMS_L0_S1_Pos, -1,           l0EP, NPMS_L0_S1_Pos, +0);
      //----------------------------------------------------------------------------------------------------------------
      // 2right reflection 1stay (Stay-Pos 0):
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
      //setNextPart(fieldEngine, l2EnginePos, metaPos(l2E, RIGHTa_p1_u0_u0, NULL_u0_u0_u0, NULL_u0_u0_u0), l1StayEnginePos, l1StayMetaPosArgArr, 2,
      //            new NextPart(l1EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S10_S00_Pos), LR_CONTINUE_MATRIX, +0,
      //                         l0EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos), LR_REFLECTION_MATRIX, +2));
      setNP(fieldEngine, l2EP, mp_L2_R100_N000_N000, l1SEP, l1SMPArr, +2,      l1EP, NPMS_L1_S10_S00_Pos, +0,      l0EP, NPMS_L0_S1_Pos, +2);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // 2right reflection 1stay (Stay-Pos 0):
      //              3   4   5   6   4
      //              -   -
      //                  -   S          b
      //    x -   -   -
      //    x     R   -   -
      //    x         -   -   -              a
      // =>
      //    x -   -                          a
      //    x     R   -
      //                  L                  c
      //setNextPart(fieldEngine, l2EnginePos, metaPos(l2E, NULL_u0_u0_u0, RIGHTa_p1_u0_u0, NULL_u0_u0_u0), l1StayEnginePos, l1StayMetaPosArgArr, 1,
      //            new NextPart(l1EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S10_S00_Pos), LR_CONTINUE_MATRIX, -1,
      //                         l0EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos), LR_REFLECTION_MATRIX, +1));
      setNP(fieldEngine, l2EP, mp_L2_N000_R100_N000, l1SEP, l1SMPArr, +1,      l1EP, NPMS_L1_S10_S00_Pos, -1,      l0EP, NPMS_L0_S1_Pos, +1);
      //----------------------------------------------------------------------------------------------------------------
      // 3right reflection 1stay (R-Pos:0, Stay-Pos:0):
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //      L1      L1      L1      L1      L1      L1      L1      L1      L1
      //                      L2          L2          L2          L2          L2
      //                      L3              L3              L3              L3
      //     -4  -3  -2  -1   0   1   2   3   4   5
      //                  -   -
      //                      -   S                   b
      //    x     R   -   -   -                       a
      //    x         -   -   -   -
      //    x             -   -   -   -
      //    x                 -   -   -   -
      // =>
      //    x -   -   -
      //    x     R   -   -                           a
      //    x         -   -   -
      //                      L                       c
      //setNP(fieldEngine, l3EP, mp_L3_N0000_N0000_N0000_R1000, l1SEP, l1SMPArr, +0,      l2EP, NPMS_L2_S000_S100_S000_Pos, -2,      l0EP, NPMS_L0_S1_Pos, +0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // 3right reflection 1stay (R-Pos:4, Stay-Pos:4):
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //      L1      L1      L1      L1      L1      L1      L1      L1      L1
      //                      L2          L2          L2          L2          L2
      //                      L3              L3              L3              L3
      //      4  -3  -2  -1   0   1   2   3   4   5
      //                                  -   -
      //                                      -   S           b-L1
      //    x                     R   -   -   -               a-L3
      //    x                         -   -   -   -
      //    x                             -   -   -   -
      //    x                                 -   -   -   -
      // =>    .
      //    x                     R   -   -
      //    x                         -   -   -              a-L2
      //    x                             -   -   -
      //                                      L              c-L0
      //setNP(fieldEngine, l3EP, mp_L3_N0000_N0000_N0000_R1000, l1SEP, l1SMPArr, +0,      l2EP, NPMS_L2_S000_S100_S000_Pos, -1,      l0EP, NPMS_L0_S1_Pos, +0);
      setNP(fieldEngine, l3EP, mp_L3_N0000_N0000_N0000_R1000, l1SEP, l1SMPArr, +0,      l2EP, NPMS_L2_S000_S100_S000_Pos, -2,      l0EP, NPMS_L0_S1_Pos, +0,
                                                                                        l2EP, NPMS_L2_S000_S000_S100_Pos, -1,      l0EP, NPMS_L0_S1_Pos, +0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // 3right reflection 1stay (R-Pos:0, Stay-Pos:2):
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //      L1      L1      L1      L1      L1      L1      L1      L1      L1
      //                      L2          L2          L2          L2          L2
      //                      L3              L3              L3              L3
      //     -4  -3  -2  -1   0   1   2   3   4   5
      //                          -   -
      //                              -   S           b
      //    x     -   -   -   -                       a
      //    x         -   -   -   -
      //    x             R   -   -   -
      //    x                 -   -   -   -
      // =>
      //    x         -   -   -
      //    x             R   -   -                           a
      //    x                 -   -   -
      //                              L                       c
      setNP(fieldEngine, l3EP, mp_L3_N0000_R1000_N0000_N0000, l1SEP, l1SMPArr, +2,      l2EP, NPMS_L2_S000_S100_S000_Pos, +0,      l0EP, NPMS_L0_S1_Pos, +2);
      //----------------------------------------------------------------------------------------------------------------
   }
}
