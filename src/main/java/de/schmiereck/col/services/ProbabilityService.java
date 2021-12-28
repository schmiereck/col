package de.schmiereck.col.services;

import de.schmiereck.col.model.Probability;

public class ProbabilityService {

   public static void calcInit(final Probability probability) {
      while (probability.lastProbabilityPos == -1) {
         calcNext(probability);
      }
   }

   public static void calcNext(final Probability probability) {
      for (int pos = 0; pos < probability.probabilitySize; pos++) {
         probability.lastProbabilityCntArr[pos] = probability.probabilityCntArr[pos];
         probability.probabilityCntArr[pos] += probability.probabilityArr[pos];
      }

      calcLast(probability);
   }

   public static void calcLast(final Probability probability) {
      int startPos = probability.lastProbabilityPos;

      for (int pos = 0; pos < probability.probabilitySize; pos++) {
         startPos++;
         if (startPos >= probability.probabilitySize) {
            startPos = 0;
         }
         if (probability.probabilityCntArr[startPos] >= probability.maxProbability) {
            probability.probabilityCntArr[startPos] -= probability.maxProbability;
            probability.lastProbabilityPos = startPos;
            break;
         }
      }
   }
}
