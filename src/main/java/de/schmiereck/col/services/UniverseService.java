package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.NULL_pos;
import static de.schmiereck.col.services.StateUtils.convertToDebugString;
import static de.schmiereck.col.services.UniverseUtils.calcCellPos;
import static de.schmiereck.col.services.UniverseUtils.readEngine;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.HyperCell;
import de.schmiereck.col.model.MetaState;
import de.schmiereck.col.model.NextPart;
import de.schmiereck.col.model.Part;
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
                        if (aPart == bPart.parentPart) {
                           aPart.enginePos = nextPartArgument.nextPartEnginePos;
                           aPart.hyperCell.cellPos = calcCellPos(universe, aPart.hyperCell.cellPos + nextPartArgument.nextPartOffsetCellPos);
                           aPart.hyperCell.metaStatePos = nextPartArgument.nextPartMetaStatePos;

                           bPart.hyperCell.metaStatePos = NULL_pos;
                        }
                     }
                     default -> {
                        if (nextPartArgument.newPartMetaStatePos != -1) {
                           final Part newPart = new Part(aPart.event, aPart,
                                   nextPartArgument.newPartEnginePos,
                                   calcCellPos(universe, aPart.hyperCell.cellPos + nextPartArgument.newPartOffsetCellPos),
                                   nextPartArgument.newPartMetaStatePos);

                           universe.partList.add(newPart);
                        }
                        if (nextPartArgument.nextPartMetaStatePos != -1) {
                           aPart.enginePos = nextPartArgument.nextPartEnginePos;
                           aPart.hyperCell.cellPos = calcCellPos(universe, aPart.hyperCell.cellPos + nextPartArgument.nextPartOffsetCellPos);
                           aPart.hyperCell.metaStatePos = nextPartArgument.nextPartMetaStatePos;
                        }
                     }
                  }
               }
            //}
         }
      }
      universe.partList.removeIf(part -> part.hyperCell.metaStatePos == NULL_pos);
   }

   public static void runCalcNextMetaState2(final Universe universe) {
      for (final Part part : universe.partList) {
         calcNextStatePosByMetaStatePos2(universe, part);
         //calcMetaStatePosByStatePosForNeighbours(engine, level, cellPos);
      }
   }

   public static void calcNextStatePosByMetaStatePos2(final Universe universe, final Part part) {
      final Engine engine = readEngine(universe, part.enginePos);

      if (Objects.nonNull(engine.metaStateArr)) {
         final HyperCell hyperCell = part.hyperCell;

         final MetaState sourceMetaState = engine.metaStateArr[hyperCell.metaStatePos];
         final int nextSourceMetaStatePos = sourceMetaState.outputMetaStatePos;
         if (nextSourceMetaStatePos == -1)
            throw new RuntimeException(String.format("Level-Cell-Size %d: For Meta-State %s no output state found.", engine.cellSize, convertToDebugString(sourceMetaState)));

         final int targetCellPos = calcCellPos(universe, hyperCell.cellPos + sourceMetaState.cellPosOffset);

         hyperCell.metaStatePos = nextSourceMetaStatePos;
         hyperCell.cellPos = targetCellPos;
      }
   }
}
