package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.FieldEngine.l0EnginePos;
import static de.schmiereck.col.model.FieldEngine.l0StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l1EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l2EnginePos;
import static de.schmiereck.col.model.NextPart.LR_REFLECTION_MATRIX;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.NULL_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.setNextPart;
import static de.schmiereck.col.services.engine.stay.CreateLevel0StayEngineService.SNULL_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel0StayEngineService.SSTAY_p1;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_p1_u0;
import static de.schmiereck.col.services.engine.stay.CreateLevel1StayEngineService.SSTAY_u0_p1;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.NextPart;

public class NextPartL0CreateService {

   static void createNextPart0leftReflection0stay(final FieldEngine fieldEngine) {
      final Engine l0E = fieldEngine.engineArr[l0EnginePos];
      final Engine l1E = fieldEngine.engineArr[l1EnginePos];
      final Engine l2E = fieldEngine.engineArr[l2EnginePos];
      final Engine l0SE = fieldEngine.engineArr[l0StayEnginePos];
      final Engine l1SE = fieldEngine.engineArr[l1StayEnginePos];

      //              0   1   2   3   4
      //                      -
      //                          S          b
      //              0   1   2   3   4
      //                          S          b
      //                              -
      final NextPartCreateService.MetaPosArg[] stayMetaPosArgArr = {
              new NextPartCreateService.MetaPosArg(metaPos(l0SE, SSTAY_p1, SNULL_u0), 0),
              //new MetaPosArg(metaPos(l0SE, SNULL_u0, SSTAY_p1), 1)
      };
      //----------------------------------------------------------------------------------------------------------------
      // 0left reflection 0stay:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //              0   1   2   3   4
      //          -   -
      //              -   S                  b
      //    x                 L              a
      //    x                     -
      // =>
      //    x                 R              a
      //    x                     -
      //setNextPart(fieldEngine, l0EnginePos, l0StayEnginePos, -2,
      //        metaPos(l0E, NULL_u0, LEFTa_p1),
      //        stayMetaPosArgArr,
      //        new NextPart(l0EnginePos, metaPos(l0E, NULL_u0, RIGHTa_p1), +0));

      //              0   1   2   3   4
      //          -   -
      //              -   S                  b
      //    x             -
      //    x                 L              a
      // =>
      //    x             -
      //    x                 R              a
      setNextPart(fieldEngine, l0EnginePos, l0StayEnginePos, -1,
              metaPos(l0E, LEFTa_p1, NULL_u0),
              stayMetaPosArgArr,
              new NextPart(l0EnginePos, //metaPos(l0E, RIGHTa_p1, NULL_u0), +0));
                      LR_REFLECTION_MATRIX, +0));
      //----------------------------------------------------------------------------------------------------------------
   }

   static void createNextPart0rightReflection0stay(final FieldEngine fieldEngine) {
      final Engine l0E = fieldEngine.engineArr[l0EnginePos];
      final Engine l1E = fieldEngine.engineArr[l1EnginePos];
      final Engine l2E = fieldEngine.engineArr[l2EnginePos];
      final Engine l0SE = fieldEngine.engineArr[l0StayEnginePos];
      final Engine l1SE = fieldEngine.engineArr[l1StayEnginePos];

      //              0   1   2   3   4
      //                      -
      //                          S          b
      //              0   1   2   3   4
      //                          S          b
      //                              -
      final NextPartCreateService.MetaPosArg[] stayMetaPosArgArr = {
              new NextPartCreateService.MetaPosArg(metaPos(l0SE, SSTAY_p1, SNULL_u0), 0),
              //new MetaPosArg(metaPos(l0SE, SNULL_u0, SSTAY_p1), 1)
      };
      //----------------------------------------------------------------------------------------------------------------
      // 0right reflection 0stay:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      //              0   1   2   3   4
      //                      -
      //                          S          b
      //    x             -
      //    x                 R              a
      // =>
      //    x             -
      //    x                 L              a
      setNextPart(fieldEngine, l0EnginePos, l0StayEnginePos, 1,
              metaPos(l0E, RIGHTa_p1, NULL_u0),
              stayMetaPosArgArr,
              new NextPart(l0EnginePos, //metaPos(l0E, LEFTa_p1, NULL_u0),
                      LR_REFLECTION_MATRIX, +0));

      //              3   4   5   6   4
      //              -   -
      //                  -   S          b
      //    x             R
      //    x                 -              a
      // =>
      //    x             L                  a
      //    x                 -
      //setNextPart(fieldEngine, l0EnginePos, l0StayEnginePos, 0,
      //        metaPos(l0E, NULL_u0, RIGHTa_p1),
      //        stayMetaPosArgArr,
      //        new NextPart(l0EnginePos, metaPos(l0E, NULL_u0, LEFTa_p1), +0));
      //----------------------------------------------------------------------------------------------------------------
   }

   static void createNextPart0leftReflection1stay(final FieldEngine fieldEngine) {
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
      // 0left reflection 1stay:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

      //              0   1   2   3   4
      //          -   -
      //              -   S                  b
      //    x                 L              a
      // =>
      //    x                 R              c
      setNextPart(fieldEngine, l0EnginePos, l1StayEnginePos, -2,
              metaPos(l0E, LEFTa_p1),
              stayMetaPosArgArr,
              new NextPart(l0EnginePos, LR_REFLECTION_MATRIX /*metaPos(l0E, RIGHTa_p1)*/, +0));
      //----------------------------------------------------------------------------------------------------------------
   }

   static void createNextPart0rightReflection1stay(final FieldEngine fieldEngine) {
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
      setNextPart(fieldEngine, l0EnginePos, l1StayEnginePos, 0,
              metaPos(l0E, RIGHTa_p1),
              stayMetaPosArgArr,
              new NextPart(l0EnginePos, LR_REFLECTION_MATRIX /*metaPos(l0E, LEFTa_p1)*/, +0));
      //----------------------------------------------------------------------------------------------------------------
   }
}
