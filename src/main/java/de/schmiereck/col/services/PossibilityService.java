package de.schmiereck.col.services;

import de.schmiereck.col.model.Possibility;

public class PossibilityService {

   public static void calcNext(final Possibility possibility) {
      for (int pos = 0; pos < possibility.posibilitySize; pos++) {
         possibility.posibilityCntArr[pos] += possibility.posibilityArr[pos];

         if (possibility.posibilityCntArr[pos] >= possibility.maxPossibility) {
            possibility.posibilityCntArr[pos] = 0;
            possibility.lastPossibility = pos;
         }
      }
   }
}
