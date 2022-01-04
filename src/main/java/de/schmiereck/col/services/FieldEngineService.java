package de.schmiereck.col.services;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.FieldEngine;
import de.schmiereck.col.model.FieldEngineANode;
import de.schmiereck.col.model.FieldEngineBNode;
import de.schmiereck.col.model.NextPart;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.engine.spinMove.NextPartCreateService;

import java.util.Objects;

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
         final int aPartMetaStatePos;
         final int bPartMetaStatePos;
         if (Objects.nonNull(aPart.hyperCell.dirMetaStatePosArr)) {
            aPartMetaStatePos = aPart.hyperCell.dirMetaStatePosArr[aPart.hyperCell.dirProbability.lastProbabilityPos];
            bPartMetaStatePos = bPart.hyperCell.dirMetaStatePosArr[bPart.hyperCell.dirProbability.lastProbabilityPos];
         } else {
            aPartMetaStatePos = aPart.hyperCell.metaStatePos;
            bPartMetaStatePos = bPart.hyperCell.metaStatePos;
         }
         //nextPart = fieldEngine.nextPartArr[aPart.enginePos][bPart.enginePos][calcRel2ArrPos(diff)][aPartMetaStatePos][bPartMetaStatePos];
         final FieldEngineANode fieldEngineANode = fieldEngine.nextPartANodeArr[aPart.enginePos][bPart.enginePos][calcRel2ArrPos(diff)];
         if (Objects.nonNull(fieldEngineANode)) {
            final FieldEngineBNode fieldEngineBNode = fieldEngineANode.bNodeArr[aPartMetaStatePos];
            if (Objects.nonNull(fieldEngineBNode)) {
               nextPart = fieldEngineBNode.nextPartArr[bPartMetaStatePos];
            } else {
               nextPart = null;
            }
         } else {
            nextPart = null;
         }
      } else {
         nextPart = null;
      }
      return nextPart;
   }

   public static void setNextPart(final FieldEngine fieldEngine,
                                  final int aPartEnginePos,
                                  final int aPartMetaStatePos,
                                  final int bPartEnginePos,
                                  final NextPartCreateService.MetaPosArg[] bPartMetaPosArgArr, final int absDiff,
                                  final NextPart nextPart) {
      for (final NextPartCreateService.MetaPosArg bPartMetaPosArg : bPartMetaPosArgArr) {
         setNextPart(fieldEngine,
                     aPartEnginePos, bPartEnginePos,
                     absDiff + bPartMetaPosArg.absDiffOff,
                     aPartMetaStatePos,
                     bPartMetaPosArg.metaPos,
                     nextPart);
      }
   }

   public static void setNextPart(final FieldEngine fieldEngine,
                                  final int aPartEnginePos, final int bPartEnginePos,
                                  final int absDiff,
                                  final int aPartMetaStatePos,
                                  final int bPartMetaStatePos,
                                  final NextPart nextPart) {
      final FieldEngineANode fieldEngineANode;
      final FieldEngineBNode fieldEngineBNode;

      final int diffPos = calcRel2ArrPos(absDiff);
      final FieldEngineANode searchedFieldEngineANode = fieldEngine.nextPartANodeArr[aPartEnginePos][bPartEnginePos][diffPos];
      if (Objects.nonNull(searchedFieldEngineANode)) {
         fieldEngineANode = searchedFieldEngineANode;
      } else {
         fieldEngineANode = new FieldEngineANode(fieldEngine.engineArr[aPartEnginePos].metaStateList.size());
         fieldEngine.nextPartANodeArr[aPartEnginePos][bPartEnginePos][diffPos] = fieldEngineANode;
      }

      final FieldEngineBNode searchedFieldEngineBNode = fieldEngineANode.bNodeArr[aPartMetaStatePos];
      if (Objects.nonNull(searchedFieldEngineBNode)) {
         fieldEngineBNode = searchedFieldEngineBNode;
      } else {
         fieldEngineBNode = new FieldEngineBNode(fieldEngine.engineArr[bPartEnginePos].metaStateList.size());
         fieldEngineANode.bNodeArr[aPartMetaStatePos] = fieldEngineBNode;
      }

      //fieldEngine.nextPartArr
      //        [aPartEnginePos] // aPart.enginePos
      //        [bPartEnginePos] // bPart.enginePos
      //        [calcRel2ArrPos(absDiff)] // absDiff
      //        [aPartMetaStatePos] // aPart metaStatePos
      //        [bPartMetaStatePos] // bPart metaStatePos
      //        = nextPart;
      fieldEngineBNode.nextPartArr[bPartMetaStatePos] = nextPart;
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
