package de.schmiereck.col.services;

import static de.schmiereck.col.model.HyperCell.DirProbLeft;
import static de.schmiereck.col.model.HyperCell.DirProbRight;
import static de.schmiereck.col.model.HyperCell.DirProbStay;

import de.schmiereck.col.model.PMatrix;
import de.schmiereck.col.model.Probability;

import java.util.Arrays;

public class ProbabilityService {

   public static void calcInit(final Probability probability) {
      while (probability.lastProbabilityPos == -1) {
         calcNext(probability);
      }
   }

   public static int calcMaxProb(final Probability probability) {
      int maxProb = -1;
      for (int pos = 0; pos < probability.probabilitySize; pos++) {
         if (probability.probabilityArr[pos] > maxProb) {
            maxProb = probability.probabilityArr[pos];
         }
      }
      return maxProb;
   }

   public static int calcMaxProbPos(final Probability probability) {
      int maxProb = -1;
      int maxProbPos = -1;
      for (int pos = 0; pos < probability.probabilitySize; pos++) {
         if (probability.probabilityArr[pos] > maxProb) {
            maxProb = probability.probabilityArr[pos];
            maxProbPos = pos;
         }
      }
      return maxProbPos;
   }

   public static void calcNext(final Probability probability) {
      calcCnt(probability);
      calcLast(probability);
   }

   public static void calcCnt(final Probability probability) {
      for (int pos = 0; pos < probability.probabilitySize; pos++) {
         probability.lastProbabilityCntArr[pos] = probability.probabilityCntArr[pos];
         probability.probabilityCntArr[pos] += probability.probabilityArr[pos];
      }
   }

   public static void calcLast(final Probability probability) {
      int lastPos = probability.lastProbabilityPos;

      if (lastPos >= 0) {
         probability.lastProbabilityArr[lastPos] = 0;
      }

      for (int pos = 0; pos < probability.probabilitySize; pos++) {
         lastPos++;
         if (lastPos >= probability.probabilitySize) {
            lastPos = 0;
         }
         if (probability.probabilityCntArr[lastPos] >= probability.maxProbability) {
            probability.probabilityCntArr[lastPos] -= probability.maxProbability;
            probability.lastProbabilityPos = lastPos;
            probability.lastProbabilityArr[lastPos] = 1;
            break;
         }
      }
   }

   public static void calcInit2(final Probability probability) {
      final int maxProbPos = calcMaxProbPos(probability);
      probability.probabilityCntArr[maxProbPos] = probability.probabilityArr[maxProbPos];

      while (Arrays.stream(probability.lastProbabilityArr).sum() <= 0) {
         calcNext2(probability);
      }
   }

  public static void calcInit2try(final Probability probability) {
      final int maxProb = calcMaxProb(probability);
      final int mul = (probability.maxProbability / maxProb) + 1;

      for (int pos = 0; pos < probability.probabilitySize; pos++) {
         probability.probabilityCntArr[pos] = probability.probabilityArr[pos] * mul;
         probability.lastProbabilityCntArr[pos] = probability.probabilityCntArr[pos];
      }

      calcLast2(probability);
   }

   public static void calcNext2(final Probability probability) {
      calcCnt(probability);
      calcLast2(probability);
   }

   public static void calcLast2(final Probability probability) {
      for (int pos = 0; pos < probability.probabilitySize; pos++) {
         if (probability.probabilityCntArr[pos] >= probability.maxProbability) {
            probability.probabilityCntArr[pos] -= probability.maxProbability;
            probability.lastProbabilityArr[pos] = 1;
         } else {
            probability.lastProbabilityArr[pos] = 0;
         }
      }
   }

   public static void calcReflection(final Probability dirProbability, final PMatrix probabilityMatrix) {
      final int s = dirProbability.probabilityArr[DirProbStay];
      final int l = dirProbability.probabilityArr[DirProbLeft];
      final int r = dirProbability.probabilityArr[DirProbRight];
      // s = s*sm11 + l*lm12 + r*rm13
      dirProbability.probabilityArr[DirProbStay]  = s*probabilityMatrix.m[0][0] + l*probabilityMatrix.m[0][1] + r*probabilityMatrix.m[0][2];
      // r = s*sm21 + l*lm22 + r*rm23
      dirProbability.probabilityArr[DirProbLeft]  = s*probabilityMatrix.m[1][0] + l*probabilityMatrix.m[1][1] + r*probabilityMatrix.m[1][2];
      // l = s*sm31 + l*lm32 + r*rm33
      dirProbability.probabilityArr[DirProbRight] = s*probabilityMatrix.m[2][0] + l*probabilityMatrix.m[2][1] + r*probabilityMatrix.m[2][2];

      final int sc = dirProbability.probabilityCntArr[DirProbStay];
      final int lc = dirProbability.probabilityCntArr[DirProbLeft];
      final int rc = dirProbability.probabilityCntArr[DirProbRight];
      dirProbability.probabilityCntArr[DirProbStay]  = sc*probabilityMatrix.m[0][0] + lc*probabilityMatrix.m[0][1] + rc*probabilityMatrix.m[0][2];
      dirProbability.probabilityCntArr[DirProbLeft]  = sc*probabilityMatrix.m[1][0] + lc*probabilityMatrix.m[1][1] + rc*probabilityMatrix.m[1][2];
      dirProbability.probabilityCntArr[DirProbRight] = sc*probabilityMatrix.m[2][0] + lc*probabilityMatrix.m[2][1] + rc*probabilityMatrix.m[2][2];

      calcNext(dirProbability);
   }

   public static void calcReflection2(final Probability dirProbability, final PMatrix probabilityMatrix) {
      final int s = dirProbability.probabilityArr[DirProbStay];
      final int l = dirProbability.probabilityArr[DirProbLeft];
      final int r = dirProbability.probabilityArr[DirProbRight];
      // s = s*sm11 + l*lm12 + r*rm13
      dirProbability.probabilityArr[DirProbStay]  = s*probabilityMatrix.m[0][0] + l*probabilityMatrix.m[0][1] + r*probabilityMatrix.m[0][2];
      // r = s*sm21 + l*lm22 + r*rm23
      dirProbability.probabilityArr[DirProbLeft]  = s*probabilityMatrix.m[1][0] + l*probabilityMatrix.m[1][1] + r*probabilityMatrix.m[1][2];
      // l = s*sm31 + l*lm32 + r*rm33
      dirProbability.probabilityArr[DirProbRight] = s*probabilityMatrix.m[2][0] + l*probabilityMatrix.m[2][1] + r*probabilityMatrix.m[2][2];

      final int sc = dirProbability.probabilityCntArr[DirProbStay];
      final int lc = dirProbability.probabilityCntArr[DirProbLeft];
      final int rc = dirProbability.probabilityCntArr[DirProbRight];
      dirProbability.probabilityCntArr[DirProbStay]  = sc*probabilityMatrix.m[0][0] + lc*probabilityMatrix.m[0][1] + rc*probabilityMatrix.m[0][2];
      dirProbability.probabilityCntArr[DirProbLeft]  = sc*probabilityMatrix.m[1][0] + lc*probabilityMatrix.m[1][1] + rc*probabilityMatrix.m[1][2];
      dirProbability.probabilityCntArr[DirProbRight] = sc*probabilityMatrix.m[2][0] + lc*probabilityMatrix.m[2][1] + rc*probabilityMatrix.m[2][2];


      final int sl = dirProbability.lastProbabilityArr[DirProbStay];
      final int ll = dirProbability.lastProbabilityArr[DirProbLeft];
      final int rl = dirProbability.lastProbabilityArr[DirProbRight];
      dirProbability.lastProbabilityArr[DirProbStay]  = sl*probabilityMatrix.m[0][0] + ll*probabilityMatrix.m[0][1] + rl*probabilityMatrix.m[0][2];
      dirProbability.lastProbabilityArr[DirProbLeft]  = sl*probabilityMatrix.m[1][0] + ll*probabilityMatrix.m[1][1] + rl*probabilityMatrix.m[1][2];
      dirProbability.lastProbabilityArr[DirProbRight] = sl*probabilityMatrix.m[2][0] + ll*probabilityMatrix.m[2][1] + rl*probabilityMatrix.m[2][2];
   }

   public static void calcOperation(final Probability outProb, final Probability inProb, final PMatrix probabilityMatrix) {
      calcOperation(outProb.probabilityArr, inProb.probabilityArr, probabilityMatrix);
      calcOperation(outProb.probabilityCntArr, inProb.probabilityCntArr, probabilityMatrix);
      calcOperation(outProb.lastProbabilityArr, inProb.lastProbabilityArr, probabilityMatrix);
   }

   private static void calcOperation(final int[] outProbArr, final int[] inProbArr, final PMatrix probabilityMatrix) {
      for (int pPos = 0; pPos < inProbArr.length; pPos++) {
         int prob = 0;
         for (int mPos = 0; mPos < inProbArr.length; mPos++) {
            prob += inProbArr[mPos] * probabilityMatrix.m[pPos][mPos];
         }
         outProbArr[pPos] = prob;
      }
   }
}
