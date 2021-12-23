package de.schmiereck.col.services;

import de.schmiereck.col.model.Probability;

public class PossibilityService {

   public static void calcInit(final Probability probability) {
      while (probability.lastPossibility == -1) {
         calcNext(probability);
      }
   }

   public static void calcNext(final Probability probability) {
      for (int pos = 0; pos < probability.posibilitySize; pos++) {
         probability.posibilityCntArr[pos] += probability.posibilityArr[pos];
      }

      int startPos = probability.lastPossibility;

      for (int pos = 0; pos < probability.posibilitySize; pos++) {
         startPos++;
         if (startPos >= probability.posibilitySize) {
            startPos = 0;
         }
         if (probability.posibilityCntArr[startPos] >= probability.maxPossibility) {
            probability.posibilityCntArr[startPos] -= probability.maxPossibility;
            probability.lastPossibility = startPos;
            break;
         }
      }
   }
}
