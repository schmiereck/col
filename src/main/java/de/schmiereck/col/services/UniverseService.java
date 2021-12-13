package de.schmiereck.col.services;

import static de.schmiereck.col.services.StateUtils.convertToDebugString;
import static de.schmiereck.col.services.UniverseUtils.calcCellPos;
import static de.schmiereck.col.services.UniverseUtils.readEngine;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.HyperCell;
import de.schmiereck.col.model.LevelCell;
import de.schmiereck.col.model.MetaState;
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
      final Engine[] engineArr = universe.engineArr;
      for (final Part aPart : universe.partList) {
         int levelPos = aPart.levelPos;
         final Engine engine = readEngine(universe, levelPos);
         //final Level level = readLevel(aPart, levelPos);

         if (Objects.nonNull(engine.metaStateArr)) {
         }
      }
   }

   public static void runCalcNextMetaState2(final Universe universe) {
      final Engine[] engineArr = universe.engineArr;
      for (final Part part : universe.partList) {
         int levelPos = part.levelPos;
         final Engine engine = readEngine(universe, levelPos);

         if (Objects.nonNull(engine.metaStateArr)) {
            calcNextStatePosByMetaStatePos2(universe, engine, part);
            //calcMetaStatePosByStatePosForNeighbours(engine, level, cellPos);
         }
      }
   }

   public static void calcNextStatePosByMetaStatePos2(final Universe universe, final Engine engine, final Part part) {
      final HyperCell hyperCell = part.hyperCell;

      final MetaState sourceMetaState = engine.metaStateArr[hyperCell.metaStatePos];
      final int nextSourceMetaStatePos = sourceMetaState.outputMetaStatePos;
      if (nextSourceMetaStatePos == -1) throw new RuntimeException(String.format("Level-Cell-Size %d: For Meta-State %s no output state found.", engine.cellSize, convertToDebugString(sourceMetaState)));

      final int targetCellPos = calcCellPos(universe, hyperCell.cellPos + sourceMetaState.cellPosOffset);

      hyperCell.metaStatePos = nextSourceMetaStatePos;
      hyperCell.cellPos = targetCellPos;
   }

   public static boolean CONFIG_use_levelUpOutputMetaStatePos = false;
/*
   public static void runLevelUp(final Universe universe) {
      final Engine[] engineArr = universe.engineArr;
      for (final Part part : universe.partList) {
         for (int sourceLevelPos = engineArr.length - 2; sourceLevelPos >= 0; sourceLevelPos--) {
            final int targetLevelPos = sourceLevelPos + 1;
            final Engine sourceEngine = readEngine(universe, sourceLevelPos);
            final Level sourceLevel = readLevel(part, sourceLevelPos);
            final Engine targetEngine = readEngine(universe, targetLevelPos);
            final Level targetLevel = readLevel(part, targetLevelPos);

            for (int cellPos = 0; cellPos < universe.universeSize; cellPos++) {
               if (CONFIG_use_levelUpOutputMetaStatePos && Objects.nonNull(sourceEngine.metaStateArr) && Objects.nonNull(targetEngine.metaStateArr)) {
                  //schauen, ob in der source cell eine levelUpOutputMetaStatePos für den target meta -state(momentan nur 0)
                  //eingetragen ist,
                  //wenn ja, diesen im target setzen
                  //und source auf meta state 0 setzen
                  final LevelCell targetLevelCell = readLevelCell(targetLevel, cellPos);
                  final Cell targetCell = targetLevelCell.metaCellArr[0];
                  final int targetMetaStatePos = targetCell.metaStatePos;

                  // Target ist ein NULL-Meta-State (momentan wird nur der als Ziel unterstützt)?
                  if (targetMetaStatePos == 0) {
                     final LevelCell sourceLevelCell = readLevelCell(sourceLevel, cellPos);
                     final Cell sourceCell = sourceLevelCell.metaCellArr[0];
                     final int sourceMetaStatePos = sourceCell.metaStatePos;

                     final MetaState sourceMetaState = sourceEngine.metaStateArr[sourceMetaStatePos];

                     final MetaState targetMetaState = targetEngine.metaStateArr[targetMetaStatePos];

                     final int levelUpOutputMetaStatePos = sourceMetaState.levelUpOutputMetaStatePosArr[targetMetaStatePos];

                     if (levelUpOutputMetaStatePos != -1) {
                        writeNewMetaStatePos(sourceEngine, sourceLevelCell, 0);
                        writeNewMetaStatePos(targetEngine, targetLevelCell, levelUpOutputMetaStatePos);
                     }
                  }
               } else {
                  throw new RuntimeException("NotImplemented");
               }
            }
         }
      }
   }
*/
   private static void writeNewMetaStatePos(final Engine targetEngine, final LevelCell targetLevelCell, final int newMetaStatePos) {
      final MetaState targetMetaState = targetEngine.metaStateArr[newMetaStatePos];
      targetLevelCell.metaCellArr[0].metaStatePos = newMetaStatePos;

      for (int pos = 0; pos < targetMetaState.inputMetaStatePosArr.length; pos++) {
         final int targetInputMetaStatePos = targetMetaState.inputMetaStatePosArr[pos];
         targetLevelCell.metaCellArr[pos].statePos = targetInputMetaStatePos;
      }
   }

   public static boolean CONFIG_use_levelDown_flag = false;
/*
   public static void runLevelDown(final Universe universe) {
      final Engine[] engineArr = universe.engineArr;
      for (int sourceLevelPos = 1; sourceLevelPos < engineArr.length; sourceLevelPos++) {
         final int targetLevelPos = sourceLevelPos - 1;
         final Engine sourceEngine = readEngine(universe, sourceLevelPos);
         final Level sourceLevel = readLevel(universe, sourceLevelPos);
         final Engine targetEngine = readEngine(universe, targetLevelPos);
         final Level targetLevel = readLevel(universe, targetLevelPos);

         for (int cellPos = 0; cellPos < universe.universeSize; cellPos++) {
            if (CONFIG_use_levelDown_flag) { // && Objects.nonNull(sourceCell.event)) {
               final LevelCell sourceLevelCell = readLevelCell(sourceLevel, cellPos);
               final Cell sourceCell = readCell(sourceLevelCell);
               final MetaState sourceCellMetaState = readMetaState(sourceEngine, sourceCell);
               if (sourceCellMetaState.levelDown) {
                  //move state down to next level
                  final LevelCell targetLevelCell = readLevelCell(targetLevel, cellPos + (sourceEngine.cellSize - targetEngine.cellSize));
                  final int targetMetaStatePos = readMetaStatePos(targetLevelCell);
                  final MetaState sourceMetaState = sourceEngine.metaStateArr[sourceCell.metaStatePos];
                  final int levelDownOutputMetaStatePos = sourceMetaState.levelDownOutputMetaStatePosArr[targetMetaStatePos];
                  if (levelDownOutputMetaStatePos == -1) {
                     final MetaState targetMetaState = targetEngine.metaStateArr[targetMetaStatePos];
                     throw new RuntimeException(String.format("For Meta-State no levelDownOutputMetaStatePos defined: source(%d:%s), target(%d:%s).",
                             sourceEngine.cellSize, convertToDebugString(sourceMetaState),
                             targetEngine.cellSize, convertToDebugString(targetMetaState)));
                  }
                  writeNewMetaStatePos(targetEngine, targetLevelCell, levelDownOutputMetaStatePos);
                  sourceCell.event.levelDownFlag = true;
               }
            } else {
               throw new RuntimeException("NotImplemented");
            }
         }
      }
   }
*/
   public static void calcInitialMetaStates(final Universe universe) {
      /*
      final Engine[] engineArr = universe.engineArr;
      for (int levelPos = 0; levelPos < engineArr.length; levelPos++) {
         final Engine engine = readEngine(universe, levelPos);
         final Level level = readLevel(universe, levelPos);

         if (Objects.nonNull(engine.metaStateArr)) {
            for (int cellPos = 0; cellPos < universe.universeSize; cellPos++) {
               final LevelCell levelCell = readLevelCell(level, cellPos);
               //final Cell sourceCell = readCell(universe, cellPos, levelPos);
               final int metaStatePos = searchMetaStatePos(engine, levelCell);
               levelCell.metaCellArr[0].metaStatePos = metaStatePos;
            }
         }
      }

       */
   }
}
