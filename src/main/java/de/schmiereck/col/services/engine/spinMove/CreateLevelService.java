package de.schmiereck.col.services.engine.spinMove;

import static de.schmiereck.col.services.engine.CreateEngineService.USE_check;
import static de.schmiereck.col.services.engine.CreateEngineService.writeMetaState;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel3SpinMoveEngineService.NULL_u0_u0_u0_u0;

import de.schmiereck.col.model.Engine;

import java.util.Arrays;

public class CreateLevelService {

   public static void calcStayMetaStatePosArr(final Engine e, final int[] stayMetaStatePosArr) {
      final String dirStr = "STAY";
      final int inOffset = 1;
      final int metaOffset = 1;
      final int[] dirMetaStatePosArr = stayMetaStatePosArr;
      calcRightMetaStatePosArr(e, metaOffset, dirMetaStatePosArr, inOffset, dirStr);
   }

   public static void calcLeftMetaStatePosArr(final Engine e, final int[] leftMetaStatePosArr) {
      final String dirStr = "LEFT";
      final int inOffset = 0;
      final int metaOffset = 1;
      final int[] dirMetaStatePosArr = leftMetaStatePosArr;
      calcRightMetaStatePosArr(e, metaOffset, dirMetaStatePosArr, inOffset, dirStr);
   }

   public static void calcRightMetaStatePosArr(final Engine e, final int[] rightMetaStatePosArr2) {
      final String dirStr = "RIGHT";
      final int inOffset = 0;
      final int metaOffset = -1;
      final int[] dirMetaStatePosArr = rightMetaStatePosArr2;
      calcRightMetaStatePosArr(e, metaOffset, dirMetaStatePosArr, inOffset, dirStr);
   }

   private static void calcRightMetaStatePosArr(final Engine e, final int metaOffset, final int[] dirMetaStatePosArr, final int inOffset, final String dirStr) {
      for (int metaPos = 0; metaPos < e.cellSize; metaPos++) {
         final boolean lastMeta = (metaPos + 1) >= e.cellSize;
         final boolean notNegativeMeta = (metaPos + metaOffset) >= 0;

         for (int inPos = 0; inPos < dirMetaStatePosArr.length; inPos++) {
            final int inMSPos = dirMetaStatePosArr[inPos];
            final int outPos = (inPos + inOffset) % (dirMetaStatePosArr.length);
            final int outMetaPos = notNegativeMeta ? (metaPos + metaOffset) % (e.cellSize) : (metaPos + metaOffset) + e.cellSize;
            final boolean outWrap = (outPos < inPos);
            final int outMSPos = dirMetaStatePosArr[outPos];
            final int offset;
            if (metaOffset == -1) {
               offset = (notNegativeMeta == false) ? dirMetaStatePosArr.length : 0;
            } else {
               if (lastMeta == false) {
                  offset = (outWrap == true) ? dirMetaStatePosArr.length : 0;
               } else {
                  offset = (outWrap == true) ? 0 : -dirMetaStatePosArr.length;
               }
            }
            if (USE_check)
               System.out.printf("%s: [metaPos:%d]=inMSPos:%d, [outMetaPos:%d]=outMSPos:%d, inPos:%d, outPos:%d, metaPos:%d, outMetaPos:%d, lastMeta:%b, outWrap:%b, offset:%d, notNegativeMeta:%b\n",
                                 dirStr, metaPos, inMSPos, outMetaPos, outMSPos,
                                 inPos, outPos, metaPos, outMetaPos, lastMeta, outWrap,
                                 offset, notNegativeMeta);

            calcRightMetaStatePosArr(e, metaPos, inMSPos, outMetaPos, outMSPos, offset);
         }
      }
   }

   private static void calcRightMetaStatePosArr(final Engine e, final int metaPos, final int inMSPos, final int outMetaPos, final int outMSPos, final int offset) {
      final int[] inMetaStateArr = new int[e.cellSize];
      Arrays.fill(inMetaStateArr, NULL_u0_u0_u0_u0);
      final int[] outMetaStateArr = new int[e.cellSize];
      Arrays.fill(outMetaStateArr, NULL_u0_u0_u0_u0);

      inMetaStateArr[metaPos] = inMSPos;
      outMetaStateArr[outMetaPos] = outMSPos;

      writeMetaState(e,
                     inMetaStateArr,
                     outMetaStateArr,
                     offset);
   }
}
