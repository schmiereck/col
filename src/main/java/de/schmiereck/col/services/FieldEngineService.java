package de.schmiereck.col.services;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.NextPart;
import de.schmiereck.col.model.Part;

public class FieldEngineService {

   public static int calcNextPart1Pos(final FieldEngine fieldEngine, final Part aPart, final Part bPart) {
      final Engine aEngine = fieldEngine.engineArr[aPart.enginePos];
      final Engine bEngine = fieldEngine.engineArr[bPart.enginePos];
      final int diff = bPart.hyperCell.cellPos - aPart.hyperCell.cellPos;
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

   public static NextPart calcNextPart(final FieldEngine fieldEngine, final Part aPart, final Part bPart) {
      final Engine aEngine = fieldEngine.engineArr[aPart.enginePos];
      final Engine bEngine = fieldEngine.engineArr[bPart.enginePos];
      final int diff = bPart.hyperCell.cellPos - aPart.hyperCell.cellPos;
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
