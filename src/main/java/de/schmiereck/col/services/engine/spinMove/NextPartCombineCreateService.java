package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.FieldEngine.l0EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l2EnginePos;
import static de.schmiereck.col.model.NextPart.Command.CmdCombineToParent;
import static de.schmiereck.col.model.NextPart.LR_CONTINUE_MATRIX;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcL1S00S01NextPartMetaStatePosArr;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcL1S00S10NextPartMetaStatePosArr;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcL1S01S00NextPartMetaStatePosArr;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcL1S10S00NextPartMetaStatePosArr;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcL2S000S000S001NextPartMetaStatePosArr;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcL2S000S000S100NextPartMetaStatePosArr;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcL2S000S100S000NextPartMetaStatePosArr;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcL2S001S000S000NextPartMetaStatePosArr;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.setNextPart;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.NextPart;

public class NextPartCombineCreateService {

   static void createNextPartLevelUpLeft(final FieldEngine fieldEngine) {
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
                      new NextPart.NextPartArgument(l1EnginePos, calcL1S10S00NextPartMetaStatePosArr(fieldEngine, l1E), LR_CONTINUE_MATRIX /*metaPos(l1E, LEFTa_p1_u0, NULL_u0_u0)*/, +0),
                      new NextPart.NextPartArgument(l1EnginePos, calcL1S00S10NextPartMetaStatePosArr(fieldEngine, l1E), LR_CONTINUE_MATRIX /*metaPos(l1E, NULL_u0_u0, LEFTa_p1_u0)*/, +1)));

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
                      new NextPart.NextPartArgument(l1EnginePos, calcL1S00S01NextPartMetaStatePosArr(fieldEngine, l1E), LR_CONTINUE_MATRIX /*metaPos(l1E, NULL_u0_u0, LEFTa_u0_p1)*/, +0),
                      new NextPart.NextPartArgument(l1EnginePos, calcL1S01S00NextPartMetaStatePosArr(fieldEngine, l1E), LR_CONTINUE_MATRIX /*metaPos(l1E, LEFTa_u0_p1, NULL_u0_u0)*/, -1)));

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
                      new NextPart.NextPartArgument(l2EnginePos, calcL2S001S000S000NextPartMetaStatePosArr(fieldEngine, l2E), LR_CONTINUE_MATRIX /*metaPos(l2E, LEFTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0)*/, -2)));


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
                      new NextPart.NextPartArgument(l2EnginePos, calcL2S000S000S001NextPartMetaStatePosArr(fieldEngine, l2E), LR_CONTINUE_MATRIX /*metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, LEFTa_u0_u0_p1)*/, +1)));
      //----------------------------------------------------------------------------------------------------------------
   }

   static void createNextPartLevelUpRight(final FieldEngine fieldEngine) {
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
                      new NextPart.NextPartArgument(l1EnginePos, calcL1S00S01NextPartMetaStatePosArr(fieldEngine, l1E), LR_CONTINUE_MATRIX /*metaPos(l1E, NULL_u0_u0, RIGHTa_u0_p1)*/, +0),
                      new NextPart.NextPartArgument(l1EnginePos, calcL1S01S00NextPartMetaStatePosArr(fieldEngine, l1E), LR_CONTINUE_MATRIX /*metaPos(l1E, RIGHTa_u0_p1, NULL_u0_u0)*/, -1)));

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
      setNextPart(fieldEngine, l0EnginePos, l0EnginePos, +1,
              metaPos(l0E, RIGHTa_p1),
              metaPos(l0E, RIGHTa_p1),
              new NextPart(CmdCombineToParent,
                      new NextPart.NextPartArgument(l1EnginePos, calcL1S10S00NextPartMetaStatePosArr(fieldEngine, l1E), LR_CONTINUE_MATRIX /*metaPos(l1E, RIGHTa_p1_u0, NULL_u0_u0)*/, +0),
                      new NextPart.NextPartArgument(l1EnginePos, calcL1S00S10NextPartMetaStatePosArr(fieldEngine, l1E), LR_CONTINUE_MATRIX /*metaPos(l1E, NULL_u0_u0, RIGHTa_p1_u0)*/, +1)));

      //              0A  1B  2A  3B  4A
      //              -           R          b
      //    x             R   -              a
      //    x                 -   -
      // =>
      //    x             R   -   -
      //    x                 -   -   -      a
      //    x                     -   -   -
      setNextPart(fieldEngine, l1EnginePos, l0EnginePos, +1,
              metaPos(l1E, NULL_u0_u0, RIGHTa_p1_u0),
              metaPos(l0E, RIGHTa_p1),
              new NextPart(CmdCombineToParent,
                      new NextPart.NextPartArgument(l2EnginePos, calcL2S000S000S100NextPartMetaStatePosArr(fieldEngine, l2E), LR_CONTINUE_MATRIX /*metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_p1_u0_u0)*/, +1)));

      //              0A  1B  2A  3B  4A
      //                              R      b
      //    x             -   -
      //    x                 R   -          a
      // =>
      //    x             -   -   -
      //    x                 R   -   -      a
      //    x                     -   -   -
      setNextPart(fieldEngine, l1EnginePos, l0EnginePos, +2,
              metaPos(l1E, RIGHTa_p1_u0, NULL_u0_u0),
              metaPos(l0E, RIGHTa_p1),
              new NextPart(CmdCombineToParent,
                      new NextPart.NextPartArgument(l2EnginePos, calcL2S000S100S000NextPartMetaStatePosArr(fieldEngine, l2E), LR_CONTINUE_MATRIX /*metaPos(l2E, NULL_u0_u0_u0, RIGHTa_p1_u0_u0, NULL_u0_u0_u0)*/, +1)));
      //----------------------------------------------------------------------------------------------------------------
   }
}
