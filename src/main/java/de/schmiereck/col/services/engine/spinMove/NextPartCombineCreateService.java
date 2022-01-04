package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.FieldEngine.NPMS_L1_S00_S01_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L1_S00_S10_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L1_S01_S00_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L1_S10_S00_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L2_S000_S000_S001_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L2_S000_S000_S100_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L2_S000_S100_S000_Pos;
import static de.schmiereck.col.model.FieldEngine.NPMS_L2_S001_S000_S000_Pos;
import static de.schmiereck.col.model.FieldEngine.l0EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1EnginePos;
import static de.schmiereck.col.model.FieldEngine.l1StayEnginePos;
import static de.schmiereck.col.model.FieldEngine.l2EnginePos;
import static de.schmiereck.col.model.NextPart.Command.CmdCombineToParent;
import static de.schmiereck.col.model.NextPart.LR_CONTINUE_MATRIX;
import static de.schmiereck.col.services.FieldEngineService.setNextPart;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.NextPartCreateService.calcNextPartMetaStatePosArr;

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
                      new NextPart.NextPartArgument(l1EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S10_S00_Pos), LR_CONTINUE_MATRIX, +0),
                      new NextPart.NextPartArgument(l1EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S00_S10_Pos), LR_CONTINUE_MATRIX, +1)));

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
                      new NextPart.NextPartArgument(l1EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S00_S01_Pos), LR_CONTINUE_MATRIX, +0),
                      new NextPart.NextPartArgument(l1EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S01_S00_Pos), LR_CONTINUE_MATRIX, -1)));

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
                      new NextPart.NextPartArgument(l2EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l2EnginePos, NPMS_L2_S001_S000_S000_Pos), LR_CONTINUE_MATRIX, -2)));


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
                      new NextPart.NextPartArgument(l2EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l2EnginePos, NPMS_L2_S000_S000_S001_Pos), LR_CONTINUE_MATRIX, +1)));
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
                      new NextPart.NextPartArgument(l1EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S00_S01_Pos), LR_CONTINUE_MATRIX, +0),
                      new NextPart.NextPartArgument(l1EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S01_S00_Pos), LR_CONTINUE_MATRIX , -1)));

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
                      new NextPart.NextPartArgument(l1EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S10_S00_Pos), LR_CONTINUE_MATRIX, +0),
                      new NextPart.NextPartArgument(l1EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S00_S10_Pos), LR_CONTINUE_MATRIX, +1)));

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
                      new NextPart.NextPartArgument(l2EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l2EnginePos, NPMS_L2_S000_S000_S100_Pos), LR_CONTINUE_MATRIX, +1)));

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
                      new NextPart.NextPartArgument(l2EnginePos, calcNextPartMetaStatePosArr(fieldEngine, l2EnginePos, NPMS_L2_S000_S100_S000_Pos), LR_CONTINUE_MATRIX, +1)));
      //----------------------------------------------------------------------------------------------------------------
   }
}
