package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.FieldEngine.NPMS_L0_S1_Pos;
import static de.schmiereck.col.model.FieldEngine.l0EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l2EnginePos;
import static de.schmiereck.col.model.NextPart.LR_CONTINUE_MATRIX;
import static de.schmiereck.col.model.NextPart.LR_REFLECTION_MATRIX;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcNextPartMetaStatePosArr;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.setNextPart;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_p1_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_u0_p1;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.NextPart;

public class NextPartL1CreateService {

   static void createNextPart1leftReflection1stay(final FieldEngine fieldEngine) {
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
      final NextPartCreateService.MetaPosArg[] stayMetaPosArgArr = {
              new NextPartCreateService.MetaPosArg(metaPos(l1SE, SSTAY_u0_p1, NULL_u0_u0), 0),
              new NextPartCreateService.MetaPosArg(metaPos(l1SE, NULL_u0_u0, SSTAY_p1_u0), 2)
      };
      //----------------------------------------------------------------------------------------------------------------
      // 1left reflection 1stay:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
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
              new NextPart(l0EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos), LR_CONTINUE_MATRIX /*metaPos(l0E, LEFTa_p1)*/, +1,
                      l0EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos), LR_REFLECTION_MATRIX /*metaPos(l0E, RIGHTa_p1)*/, +0));
      //----------------------------------------------------------------------------------------------------------------
   }

   static void createNextPart1rightReflection1stay(final FieldEngine fieldEngine) {
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
      final NextPartCreateService.MetaPosArg[] stayMetaPosArgArr = {
              new NextPartCreateService.MetaPosArg(metaPos(l1SE, SSTAY_u0_p1, NULL_u0_u0), 0),
              new NextPartCreateService.MetaPosArg(metaPos(l1SE, NULL_u0_u0, SSTAY_p1_u0), 2)
      };
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
      setNextPart(fieldEngine, l1EnginePos, l1StayEnginePos, 2,
              metaPos(l1E, RIGHTa_p1_u0, NULL_u0_u0),
              stayMetaPosArgArr,
              new NextPart(l0EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos), LR_CONTINUE_MATRIX /*metaPos(l1E, RIGHTa_p1, NULL_u0)*/, +0,
                      l0EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos), LR_REFLECTION_MATRIX /*metaPos(l0E, LEFTa_p1)*/, +1));

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
              new NextPart(l0EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos), LR_CONTINUE_MATRIX /*metaPos(l0E, RIGHTa_p1)*/, -1,
                      l0EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos), LR_REFLECTION_MATRIX /*metaPos(l0E, LEFTa_p1)*/, +0));
      //----------------------------------------------------------------------------------------------------------------
   }
}
