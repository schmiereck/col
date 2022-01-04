package de.schmiereck.col.services;

import static de.schmiereck.col.model.HyperCell.DirProbLeft;
import static de.schmiereck.col.model.HyperCell.DirProbRight;
import static de.schmiereck.col.model.HyperCell.DirProbStay;
import static de.schmiereck.col.model.State.NULL_pos;
import static de.schmiereck.col.services.ProbabilityService.calcNext;
import static de.schmiereck.col.services.StateUtils.convertToDebugString;
import static de.schmiereck.col.services.UniverseUtils.calcCellPos;
import static de.schmiereck.col.services.UniverseUtils.readEngine;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.HyperCell;
import de.schmiereck.col.model.MetaState;
import de.schmiereck.col.model.NextPart;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Probability;
import de.schmiereck.col.model.Universe;

import java.util.Objects;

public class UniverseService {

   /**
    * Run Next Up+Meta+Down+State
    */
   public static void runNextUMD(final Universe universe) {
      //runLevelUp(universe);
      runCalcNextMetaState2(universe);
      //runLevelDown(universe);
   }

   /**
    * Run Next Up+State+Down+Meta
    */
   public static void runNextUDM(final Universe universe) {
      //runLevelUp(universe);
      //runCalcNextState(universe);
      //runLevelDown(universe);
      runCalcNextMetaState2(universe);
   }

   /**
    * Run Next Up+State+Meta
    */
   public static void runNextUM(final Universe universe) {
      //runLevelUp(universe);
      //runCalcNextState(universe);
      runCalcNextMetaState2(universe);
   }

   public static void runCalcNextPart(final Universe universe) {
      //for (final Part aPart : universe.partList) {
      for (int aPartPos = 0; aPartPos < universe.partList.size(); aPartPos++) {
         final Part aPart = universe.partList.get(aPartPos);
         //final Engine aEngine = readEngine(universe, aPart.enginePos);

         //for (final Part bPart : universe.partList) {
         for (int bPartPos = aPartPos + 1; bPartPos < universe.partList.size(); bPartPos++) {
            final Part bPart = universe.partList.get(bPartPos);
            //final Engine bEngine = readEngine(universe, bPart.enginePos);

            //final int diff = bPart.hyperCell.cellPos - aPart.hyperCell.cellPos;
            //final int absDiff = Math.abs(diff);
            //final int minDiff = aEngine.cellSize + bEngine.cellSize;

            //if (absDiff < minDiff) {
               final NextPart nextPart = FieldEngineService.calcNextPart(universe, aPart, bPart);

               if (Objects.nonNull(nextPart)) {
                  final int relCellPos = aPart.hyperCell.cellPos % nextPart.nextPartArgumentArr.length;
                  final NextPart.NextPartArgument nextPartArgument = nextPart.nextPartArgumentArr[relCellPos];
                  switch (nextPart.command) {
                     case CmdCombineToParent -> {
                        if ((aPart == bPart.parentPart) && (probIsEqual(aPart.hyperCell.dirProbability, bPart.hyperCell.dirProbability))) {
                           if ((aPart.enginePos != nextPartArgument.nextPartEnginePos) && Objects.isNull(nextPartArgument.nextPartMetaStatePosArr)) {
                              throw new RuntimeException("aPart.enginePos != nextPartArgument.nextPartEnginePos - We need \"nextPartArgument.nextPartMetaStatePosArr\".");
                           }
                           aPart.enginePos = nextPartArgument.nextPartEnginePos;
                           aPart.hyperCell.cellPos = calcCellPos(universe, aPart.hyperCell.cellPos + nextPartArgument.nextPartOffsetCellPos);
                           if (Objects.nonNull(nextPartArgument.nextPartMetaStatePosArr)) {
                              aPart.hyperCell.dirMetaStatePosArr[DirProbStay] = nextPartArgument.nextPartMetaStatePosArr[DirProbStay];
                              aPart.hyperCell.dirMetaStatePosArr[DirProbLeft] = nextPartArgument.nextPartMetaStatePosArr[DirProbLeft];
                              aPart.hyperCell.dirMetaStatePosArr[DirProbRight] = nextPartArgument.nextPartMetaStatePosArr[DirProbRight];

                              calcReflection(aPart.hyperCell.dirProbability, nextPartArgument.nextPartProbabilityMatrix);
                           } else {
                              if (Objects.nonNull(aPart.hyperCell.dirMetaStatePosArr)) {
                                 aPart.hyperCell.dirMetaStatePosArr[aPart.hyperCell.dirProbability.lastProbabilityPos] = nextPartArgument.nextPartMetaStatePos;
                              } else {
                                 aPart.hyperCell.metaStatePos = nextPartArgument.nextPartMetaStatePos;
                              }
                           }
                           if (Objects.nonNull(bPart.hyperCell.dirMetaStatePosArr)) {
                              bPart.hyperCell.dirMetaStatePosArr[bPart.hyperCell.dirProbability.lastProbabilityPos] = NULL_pos;
                           } else {
                              bPart.hyperCell.metaStatePos = NULL_pos;
                           }
                        }
                     }
                     default -> {
                        // New-Part:
                        if (Objects.nonNull(nextPartArgument.newPartProbabilityMatrix)) {
                           final Part newPart = new Part(aPart.event, aPart,
                                   nextPartArgument.newPartEnginePos,
                                   calcCellPos(universe, aPart.hyperCell.cellPos + nextPartArgument.newPartOffsetCellPos),
                                   nextPartArgument.newPartMetaStatePosArr, aPart.hyperCell.dirProbability.probabilityArr);

                           calcReflection(newPart.hyperCell.dirProbability, nextPartArgument.newPartProbabilityMatrix);

                           universe.partList.add(newPart);
                        } else {
                           if (Objects.nonNull(nextPartArgument.newPartMetaStatePosArr)) {
                              final Part newPart = new Part(aPart.event, aPart,
                                      nextPartArgument.newPartEnginePos,
                                      calcCellPos(universe, aPart.hyperCell.cellPos + nextPartArgument.newPartOffsetCellPos),
                                      nextPartArgument.newPartMetaStatePosArr, nextPartArgument.probabilityArr);

                              universe.partList.add(newPart);
                           } else {
                              if (nextPartArgument.newPartMetaStatePos != -1) {
                                 final Part newPart = new Part(aPart.event, aPart,
                                         nextPartArgument.newPartEnginePos,
                                         calcCellPos(universe, aPart.hyperCell.cellPos + nextPartArgument.newPartOffsetCellPos),
                                         nextPartArgument.newPartMetaStatePos);

                                 universe.partList.add(newPart);
                              }
                           }
                        }
                        // Next-Part:
                        if (Objects.nonNull(nextPartArgument.nextPartProbabilityMatrix)) {
                           if ((aPart.enginePos != nextPartArgument.nextPartEnginePos) && Objects.isNull(nextPartArgument.nextPartMetaStatePosArr)) {
                              throw new RuntimeException("aPart.enginePos != nextPartArgument.nextPartEnginePos - We need \"nextPartArgument.nextPartMetaStatePosArr\".");
                           }
                           aPart.enginePos = nextPartArgument.nextPartEnginePos;
                           aPart.hyperCell.cellPos = calcCellPos(universe, aPart.hyperCell.cellPos + nextPartArgument.nextPartOffsetCellPos);
                           if (Objects.nonNull(nextPartArgument.nextPartMetaStatePosArr)) {
                              aPart.hyperCell.dirMetaStatePosArr[DirProbStay] = nextPartArgument.nextPartMetaStatePosArr[DirProbStay];
                              aPart.hyperCell.dirMetaStatePosArr[DirProbLeft] = nextPartArgument.nextPartMetaStatePosArr[DirProbLeft];
                              aPart.hyperCell.dirMetaStatePosArr[DirProbRight] = nextPartArgument.nextPartMetaStatePosArr[DirProbRight];
                           }
                           calcReflection(aPart.hyperCell.dirProbability, nextPartArgument.nextPartProbabilityMatrix);

                           //if (Objects.nonNull(aPart.hyperCell.dirMetaStatePosArr)) {
                           //   aPart.hyperCell.dirMetaStatePosArr[aPart.hyperCell.dirProbability.lastProbabilityPos] = nextPartArgument.nextPartMetaStatePos;
                           //} else {
                           //   aPart.hyperCell.metaStatePos = nextPartArgument.nextPartMetaStatePos;
                           //}
                        } else {
                           if (nextPartArgument.nextPartMetaStatePos != -1) {
                              aPart.enginePos = nextPartArgument.nextPartEnginePos;
                              aPart.hyperCell.cellPos = calcCellPos(universe, aPart.hyperCell.cellPos + nextPartArgument.nextPartOffsetCellPos);
                              if (Objects.nonNull(aPart.hyperCell.dirMetaStatePosArr)) {
                                 aPart.hyperCell.dirMetaStatePosArr[aPart.hyperCell.dirProbability.lastProbabilityPos] = nextPartArgument.nextPartMetaStatePos;
                              } else {
                                 aPart.hyperCell.metaStatePos = nextPartArgument.nextPartMetaStatePos;
                              }
                           }
                        }
                     }
                  }
               }
            //}
         }
      }
         universe.partList.removeIf(part -> {
            if (Objects.nonNull(part.hyperCell.dirMetaStatePosArr)) {
               return part.hyperCell.dirMetaStatePosArr[part.hyperCell.dirProbability.lastProbabilityPos] == NULL_pos;
            } else {
               return part.hyperCell.metaStatePos == NULL_pos;
            }
         });
   }

   private static boolean probIsEqual(final Probability dirProbability, final Probability dirProbability1) {
      return (dirProbability.probabilityArr[DirProbStay] == dirProbability1.probabilityArr[DirProbStay]) &&
             (dirProbability.probabilityArr[DirProbLeft] == dirProbability1.probabilityArr[DirProbLeft]) &&
             (dirProbability.probabilityArr[DirProbRight] == dirProbability1.probabilityArr[DirProbRight]);
   }

   private static void calcReflection(final Probability dirProbability, final int[][] probabilityMatrix) {
      final int s = dirProbability.probabilityArr[DirProbStay];
      final int l = dirProbability.probabilityArr[DirProbLeft];
      final int r = dirProbability.probabilityArr[DirProbRight];
      // s = s*sm11 + l*lm12 + r*rm13
      dirProbability.probabilityArr[DirProbStay]  = s*probabilityMatrix[0][0] + l*probabilityMatrix[0][1] + r*probabilityMatrix[0][2];
      // r = s*sm21 + l*lm22 + r*rm23
      dirProbability.probabilityArr[DirProbLeft]  = s*probabilityMatrix[1][0] + l*probabilityMatrix[1][1] + r*probabilityMatrix[1][2];
      // l = s*sm31 + l*lm32 + r*rm33
      dirProbability.probabilityArr[DirProbRight] = s*probabilityMatrix[2][0] + l*probabilityMatrix[2][1] + r*probabilityMatrix[2][2];

      final int sc = dirProbability.probabilityCntArr[DirProbStay];
      final int lc = dirProbability.probabilityCntArr[DirProbLeft];
      final int rc = dirProbability.probabilityCntArr[DirProbRight];
      dirProbability.probabilityCntArr[DirProbStay]  = sc*probabilityMatrix[0][0] + lc*probabilityMatrix[0][1] + rc*probabilityMatrix[0][2];
      dirProbability.probabilityCntArr[DirProbLeft]  = sc*probabilityMatrix[1][0] + lc*probabilityMatrix[1][1] + rc*probabilityMatrix[1][2];
      dirProbability.probabilityCntArr[DirProbRight] = sc*probabilityMatrix[2][0] + lc*probabilityMatrix[2][1] + rc*probabilityMatrix[2][2];

      calcNext(dirProbability);
   }

   public static void runCalcNextMetaState2(final Universe universe) {
      for (final Part part : universe.partList) {
         calcNextStatePosByMetaStatePos2(universe, part);
         //calcMetaStatePosByStatePosForNeighbours(engine, level, cellPos);
      }
   }

   public static void calcNextStatePosByMetaStatePos2(final Universe universe, final Part part) {
      final Engine engine = readEngine(universe, part.enginePos);

      if (Objects.nonNull(engine.metaStateList)) {
         final HyperCell hyperCell = part.hyperCell;

         final MetaState sourceMetaState;
         if (Objects.nonNull(hyperCell.dirMetaStatePosArr)) {
            final int metaStatePos = hyperCell.dirMetaStatePosArr[hyperCell.dirProbability.lastProbabilityPos];
            sourceMetaState = engine.metaStateList.get(metaStatePos);

            for (int dirMetaStatePosPos = 0; dirMetaStatePosPos < hyperCell.dirMetaStatePosArr.length; dirMetaStatePosPos++) {
               final int dirMetaStatePos = hyperCell.dirMetaStatePosArr[dirMetaStatePosPos];
               final MetaState dirSourceMetaState = engine.metaStateList.get(dirMetaStatePos);
               final int nextDirSourceMetaStatePos = dirSourceMetaState.outputMetaStatePos;
               if (nextDirSourceMetaStatePos == -1)
                  throw new RuntimeException(String.format("Level-Cell-Size %d: For Meta-State %s no output state found.", engine.cellSize, convertToDebugString(sourceMetaState)));
               hyperCell.dirMetaStatePosArr[dirMetaStatePosPos] = nextDirSourceMetaStatePos;
            }

            calcNext(hyperCell.dirProbability);
         } else {
            sourceMetaState = engine.metaStateList.get(hyperCell.metaStatePos);
            final int nextSourceMetaStatePos = sourceMetaState.outputMetaStatePos;
            if (nextSourceMetaStatePos == -1)
               throw new RuntimeException(String.format("Level-Cell-Size %d: For Meta-State %s no output state found.", engine.cellSize, convertToDebugString(sourceMetaState)));
            hyperCell.metaStatePos = nextSourceMetaStatePos;
         }

         final int targetCellPos = calcCellPos(universe, hyperCell.cellPos + sourceMetaState.cellPosOffset);

         hyperCell.cellPos = targetCellPos;
      }
   }
}
