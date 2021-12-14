package de.schmiereck.col.services;

import static de.schmiereck.col.services.UniverseUtils.readEngine;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.NextPart;
import de.schmiereck.col.model.Part;

public class FieldEngineService {

   public static int calcNextPart1Pos(final FieldEngine fieldEngine, final Part aPart, final Part bPart) {
      final Engine aEngine = fieldEngine.engineArr[aPart.levelPos];
      final Engine bEngine = fieldEngine.engineArr[bPart.levelPos];
      final int diff = bPart.hyperCell.cellPos - aPart.hyperCell.cellPos;
      final int absDiff = Math.abs(diff);
      final int minDiff = aEngine.cellSize + bEngine.cellSize;

      final int nextPartPos;

      if (absDiff < minDiff) {
         nextPartPos =
                 aPart.levelPos + fieldEngine.maxLevelPos *
                 bPart.levelPos + fieldEngine.maxLevelPos *
                 absDiff + fieldEngine.maxDiff *
                 aPart.hyperCell.metaStatePos + fieldEngine.maxMetaStatePos *
                 bPart.hyperCell.metaStatePos;
      } else {
         nextPartPos = -1;
      }

      return nextPartPos;
   }

   public static NextPart calcNextPart1(final FieldEngine fieldEngine, final int nextPartPos) {
      return fieldEngine.nextPart1Arr[nextPartPos];
   }

   public static NextPart calcNextPart(final FieldEngine fieldEngine, final Part aPart, final Part bPart) {
      final Engine aEngine = fieldEngine.engineArr[aPart.levelPos];
      final Engine bEngine = fieldEngine.engineArr[aPart.levelPos];
      final int diff = bPart.hyperCell.cellPos - aPart.hyperCell.cellPos;
      final int absDiff = Math.abs(diff);
      final int minDiff = aEngine.cellSize + bEngine.cellSize;

      final NextPart nextPart;
      if (absDiff < minDiff) {
         nextPart = fieldEngine.nextPartArr[aPart.levelPos][bPart.levelPos][absDiff][aPart.hyperCell.metaStatePos][bPart.hyperCell.metaStatePos];
      } else {
         nextPart = null;
      }
      return nextPart;
   }
}
