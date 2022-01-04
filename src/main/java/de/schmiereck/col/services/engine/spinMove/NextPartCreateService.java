package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.model.FieldEngine.NPMS_L0_S1_Pos;
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
import static de.schmiereck.col.model.FieldEngine.l2EnginePos;
import static de.schmiereck.col.services.FieldEngineService.calcRel2ArrPos;
import static de.schmiereck.col.services.engine.spinMove.NextPartCombineCreateService.createNextPartLevelUpLeft;
import static de.schmiereck.col.services.engine.spinMove.NextPartCombineCreateService.createNextPartLevelUpRight;
import static de.schmiereck.col.services.engine.spinMove.NextPartL0CreateService.createNextPart0leftReflection0stay;
import static de.schmiereck.col.services.engine.spinMove.NextPartL0CreateService.createNextPart0rightReflection0stay;
import static de.schmiereck.col.services.engine.spinMove.NextPartL0CreateService.createNextPart0rightReflection1stay;
import static de.schmiereck.col.services.engine.spinMove.NextPartL1CreateService.createNextPart1rightReflection1stay;
import static de.schmiereck.col.services.engine.spinMove.NextPartL2CreateService.createNextPart2rightReflection1stay;
import static de.schmiereck.col.services.engine.spinMove.NextPartLeftLxL1StayCreateService.createNextPartLeftReflection1stay;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.NextPart;
import de.schmiereck.col.model.Universe;

import java.util.Objects;

public class NextPartCreateService {
   /**
    * A Reflection: very good, but a lot of Parts
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
      createNextPartLeftReflection1stay(universe.fieldEngine);

      if (Objects.nonNull(universe.fieldEngine.engineArr[l0EnginePos])) {
         createNextPart0leftReflection0stay(universe.fieldEngine);
         createNextPart0rightReflection0stay(universe.fieldEngine);

         if (Objects.nonNull(universe.fieldEngine.engineArr[l1EnginePos])) {
            //createNextPart0leftReflection1stay(universe.fieldEngine);
            createNextPart0rightReflection1stay(universe.fieldEngine);
         }
      }
      if (Objects.nonNull(universe.fieldEngine.engineArr[l1EnginePos])) {
         createNextPart1rightReflection1stay(universe.fieldEngine);
         //createNextPart1leftReflection1stay(universe.fieldEngine);

         if (Objects.nonNull(universe.fieldEngine.engineArr[l2EnginePos])) {
            createNextPart2rightReflection1stay(universe.fieldEngine);
            //createNextPart2leftReflection1stay(universe.fieldEngine);
         }
      }
      if (universe.use_levelUp) {
         createNextPartLevelUpLeft(universe.fieldEngine);
         createNextPartLevelUpRight(universe.fieldEngine);
      }
      //----------------------------------------------------------------------------------------------------------------
   }

   public static class MetaPosArg {
      public final int metaPos;
      public final int absDiffOff;

      MetaPosArg(final int metaPos, final int absDiffOff) {
         this.metaPos = metaPos;
         this.absDiffOff = absDiffOff;
      }
   }

   static int[] calcNextPartMetaStatePosArr(final FieldEngine fieldEngine, final int enginePos, final int nextPartMetaStatePos) {
      return fieldEngine.nextPartMetaStatePosArr[enginePos][nextPartMetaStatePos];
   }

   static int[] calcL0S1NextPartMetaStatePosArr(final FieldEngine fieldEngine, final Engine l0E) {
      //return new int[]{metaPos(l0E, STAYa_p1), metaPos(l0E, LEFTa_p1), metaPos(l0E, RIGHTa_p1)};
      return calcNextPartMetaStatePosArr(fieldEngine, l0EnginePos, NPMS_L0_S1_Pos);
   }

   static int[] calcL1S00S01NextPartMetaStatePosArr(final FieldEngine fieldEngine, final Engine l1E) {
      //return new int[]{metaPos(l1E, NULL_u0_u0, STAYa_u0_p1), metaPos(l1E, NULL_u0_u0, LEFTa_u0_p1), metaPos(l1E, NULL_u0_u0, RIGHTa_u0_p1)};
      return calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S00_S01_Pos);
   }

   static int[] calcL1S01S00NextPartMetaStatePosArr(final FieldEngine fieldEngine, final Engine l1E) {
      //return new int[]{metaPos(l1E, STAYa_u0_p1, NULL_u0_u0), metaPos(l1E, LEFTa_u0_p1, NULL_u0_u0), metaPos(l1E, RIGHTa_u0_p1, NULL_u0_u0)};
      return calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S01_S00_Pos);
   }

   static int[] calcL1S10S00NextPartMetaStatePosArr(final FieldEngine fieldEngine, final Engine l1E) {
      //return new int[]{metaPos(l1E, STAYa_u0_p1, NULL_u0_u0), metaPos(l1E, LEFTa_u0_p1, NULL_u0_u0), metaPos(l1E, RIGHTa_p1_u0, NULL_u0_u0)};
      return calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S10_S00_Pos);
   }

   static int[] calcL1S00S10NextPartMetaStatePosArr(final FieldEngine fieldEngine, final Engine l1E) {
      //return new int[]{metaPos(l1E, NULL_u0_u0, STAYa_p1_u0), metaPos(l1E, NULL_u0_u0, LEFTa_p1_u0), metaPos(l1E, NULL_u0_u0, RIGHTa_p1_u0)};
      return calcNextPartMetaStatePosArr(fieldEngine, l1EnginePos, NPMS_L1_S00_S10_Pos);
   }

   static int[] calcL2S001S000S000NextPartMetaStatePosArr(final FieldEngine fieldEngine, final Engine l2E) {
      //return new int[]{metaPos(l2E, STAYa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0),
      //                 metaPos(l2E, LEFTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0),
      //                 metaPos(l2E, RIGHTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0)};
      return calcNextPartMetaStatePosArr(fieldEngine, l2EnginePos, NPMS_L2_S001_S000_S000_Pos);
   }

   static int[] calcL2S000S000S100NextPartMetaStatePosArr(final FieldEngine fieldEngine, final Engine l2E) {
      //return new int[]{metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, STAYa_p1_u0_u0),
      //                 metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, LEFTa_p1_u0_u0),
      //                 metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_p1_u0_u0)};
      return calcNextPartMetaStatePosArr(fieldEngine, l2EnginePos, NPMS_L2_S000_S000_S100_Pos);
   }

   static int[] calcL2S000S000S001NextPartMetaStatePosArr(final FieldEngine fieldEngine, final Engine l2E) {
      //return new int[]{metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, STAYa_u0_u0_p1),
      //                 metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, LEFTa_u0_u0_p1),
      //                 metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_u0_u0_p1)};
      return calcNextPartMetaStatePosArr(fieldEngine, l2EnginePos, NPMS_L2_S000_S000_S001_Pos);
   }

   static int[] calcL2S000S100S000NextPartMetaStatePosArr(final FieldEngine fieldEngine, final Engine l2E) {
      //return new int[]{metaPos(l2E, NULL_u0_u0_u0, STAYa_p1_u0_u0, NULL_u0_u0_u0),
      //                 metaPos(l2E, NULL_u0_u0_u0, LEFTa_p1_u0_u0, NULL_u0_u0_u0),
      //                 metaPos(l2E, NULL_u0_u0_u0, RIGHTa_p1_u0_u0, NULL_u0_u0_u0)};
      return calcNextPartMetaStatePosArr(fieldEngine, l2EnginePos, NPMS_L2_S000_S100_S000_Pos);
   }
}
