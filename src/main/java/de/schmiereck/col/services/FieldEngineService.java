package de.schmiereck.col.services;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.NextPart;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;

public class FieldEngineService {

   public static int calcNextPart1Pos(final Universe universe, final Part aPart, final Part bPart) {
      final FieldEngine fieldEngine = universe.fieldEngine;
      final Engine aEngine = fieldEngine.engineArr[aPart.enginePos];
      final Engine bEngine = fieldEngine.engineArr[bPart.enginePos];
      final int diff = calcWrapedDiff(universe, aPart, bPart);
      final int absDiff = Math.abs(diff);
      final int minDiff = aEngine.cellSize + bEngine.cellSize;

      final int nextPartPos;

      if (absDiff < minDiff) {
         nextPartPos =
                 aPart.enginePos + fieldEngine.maxEnginePos *
                 bPart.enginePos + fieldEngine.maxEnginePos *
                 absDiff + fieldEngine.maxDiff *
                 aPart.hyperCell.metaStatePos + fieldEngine.maxMetaStatePos *
                 bPart.hyperCell.metaStatePos;
      } else {
         nextPartPos = -1;
      }

      return nextPartPos;
   }

   public static NextPart calcNextPart(final Universe universe, final Part aPart, final Part bPart) {
      final FieldEngine fieldEngine = universe.fieldEngine;
      final Engine aEngine = fieldEngine.engineArr[aPart.enginePos];
      final Engine bEngine = fieldEngine.engineArr[bPart.enginePos];

      final int diff = calcWrapedDiff(universe, aPart, bPart);
      final int absDiff = Math.abs(diff);
      final int minDiff = aEngine.cellSize + bEngine.cellSize;

      final NextPart nextPart;
      if (absDiff < minDiff) {
         nextPart = fieldEngine.nextPartArr[aPart.enginePos][bPart.enginePos][calcRel2ArrPos(diff)][aPart.hyperCell.metaStatePos][bPart.hyperCell.metaStatePos];
      } else {
         nextPart = null;
      }
      return nextPart;
   }

   private static int calcWrapedDiff(final Universe universe, final Part aPart, final Part bPart) {
      final int diff = (bPart.hyperCell.cellPos) - (aPart.hyperCell.cellPos);
      final int absDiff = Math.abs(diff);
      final int diff2 = (bPart.hyperCell.cellPos + universe.universeSize) - (aPart.hyperCell.cellPos);
      final int absDiff2 = Math.abs(diff2);
      final int diff3 = (bPart.hyperCell.cellPos) - (aPart.hyperCell.cellPos + universe.universeSize);
      final int absDiff3 = Math.abs(diff3);
      final int d1;
      if (absDiff2 < absDiff) {
         //              2   3   4   5   0   1
         //                          S   -
         //                              -   -  b
         //    x             R   -              a
         //    x                 -   -
         d1 = diff2;
      } else {
         d1 = diff;
      }
      final int d2;
      if (absDiff3 < d1) {
         //              3   4   5   0   1   2
         //              -   -
         //                  -   S              b
         //    x             -   -   -
         //    x                 -   -   -
         //    x                     -   -   L  a
         d2 = diff3;
      } else {
         d2 = d1;
      }
      return d2;
   }

   public static int calcRel2ArrPos(final int relPos) {
      if (relPos >= 0) {
         return relPos << 1;
      } else {
         return ((-relPos) << 1) | 1;
      }
   }

   public static int calcArrPos2Rel(final int arrPos) {
      if ((arrPos & 1) == 0) {
         return arrPos >> 1;
      } else {
         return -(arrPos >> 1);
      }
   }
}
