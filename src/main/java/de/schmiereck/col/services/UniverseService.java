package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;
import static de.schmiereck.col.services.EngineService.calcMetaStatePosByLevelCell;
import static de.schmiereck.col.services.EngineService.calcMetaStateSize;
import static de.schmiereck.col.services.EngineService.readMetaState;
import static de.schmiereck.col.services.EngineService.searchMetaStatePos;
import static de.schmiereck.col.services.EngineService.searchStatePosWithNewStateOnPos;
import static de.schmiereck.col.services.LevelService.calcEqualMetaStateValues;
import static de.schmiereck.col.services.StateUtils.convertToDebugString;
import static de.schmiereck.col.services.UniverseUtils.readCell;
import static de.schmiereck.col.services.UniverseUtils.readEngine;
import static de.schmiereck.col.services.UniverseUtils.readLevel;
import static de.schmiereck.col.services.UniverseUtils.readLevelCell;
import static de.schmiereck.col.services.UniverseUtils.readMetaStatePos;

import de.schmiereck.col.model.Cell;
import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Level;
import de.schmiereck.col.model.LevelCell;
import de.schmiereck.col.model.MetaState;
import de.schmiereck.col.model.State;
import de.schmiereck.col.model.Universe;

import java.util.Objects;

public class UniverseService {

   /**
    * Run Next Up+State+Down+State
    */
   public static void runNextUSDS(final Universe universe) {
      runLevelUp(universe);
      runCalcNextState(universe);
      runLevelDown(universe);
      runCalcNextState(universe);
   }

   /**
    * Run Next Up+Meta+Down+State
    */
   public static void runNextUMDS(final Universe universe) {
      runLevelUp(universe);
      runCalcNextMetaState(universe);
      runLevelDown(universe);
      runCalcNextState(universe);
   }

   /**
    * Run Next Up+State+Down+Meta
    */
   public static void runNextUSDM(final Universe universe) {
      runLevelUp(universe);
      runCalcNextState(universe);
      runLevelDown(universe);
      runCalcNextMetaState(universe);
   }

   /**
    * Run Next Up+State+Meta
    */
   public static void runNextUSM(final Universe universe) {
      runLevelUp(universe);
      runCalcNextState(universe);
      runCalcNextMetaState(universe);
   }

   public static void run3(final Universe universe) {
      //runLevelDown(universe);
      //runCalcNextMetaState(universe);
      //runLevelUp(universe);
      runCalcNextState(universe);
   }

   public static void runCalcNextState(final Universe universe) {
      final Engine[] engineArr = universe.engineArr;
      for (int levelPos = 0; levelPos < engineArr.length; levelPos++) {
         final Engine engine = readEngine(universe, levelPos);
         final Level level = readLevel(universe, levelPos);

         for (int cellPos = 0; cellPos < universe.universeSize; cellPos++) {
            final Cell cell = readCell(level, cellPos);

            final int nextStatePos;
            if (CONFIG_use_levelDown_flag && Objects.nonNull(cell.event) && cell.event.levelDownFlag) {
               nextStatePos = 0;
               cell.event = null;
            } else {
               nextStatePos = engine.outputStatePosArr.get(cell.statePos);
            }
            cell.statePos = nextStatePos;

            if (engine.metaStateArr != null) {
               final MetaState metaState = engine.metaStateArr[cell.metaStatePos];
               final int nextMetaStatePos = searchForMetaStatePos(engine, nextStatePos, metaState);
               cell.metaStatePos = nextMetaStatePos;

               calcMetaStatePosByStatePosForNeighbours(engine, level, cellPos);
            }
         }
      }
   }

   private static int searchForMetaStatePos(final Engine engine, final int newStatePos, final MetaState givenMetaState) {
      for (int msPos = 0; msPos < engine.metaStateArr.length; msPos++) {
         final MetaState metaState = engine.metaStateArr[msPos];
         boolean found = true;
         for (int metaStatePos = 0; metaStatePos < metaState.inputMetaStatePosArr.length; metaStatePos++) {
            final int inputMetaStatePos = metaState.inputMetaStatePosArr[metaStatePos];
            if (metaStatePos == 0) {
               if (newStatePos != inputMetaStatePos) {
                  found = false;
                  break;
               }
            } else {
               final int givenInputMetaStatePos = givenMetaState.inputMetaStatePosArr[metaStatePos];
               if (givenInputMetaStatePos != inputMetaStatePos) {
                  found = false;
                  break;
               }
            }
         }
         if (found) {
            return msPos;
         }
      }
      return -1;
   }

   public static void runCalcNextMetaState(final Universe universe) {
      final Engine[] engineArr = universe.engineArr;
      for (int levelPos = 0; levelPos < engineArr.length; levelPos++) {
         final Engine engine = readEngine(universe, levelPos);
         final Level level = readLevel(universe, levelPos);

         if (Objects.nonNull(engine.metaStateArr)) {
            //for (int cellPos = universe.universeSize - 1; cellPos >= 0; cellPos--) {
            for (int cellPos = 0; cellPos < universe.universeSize; cellPos += engine.cellSize) {
               calcNextStatePosByMetaStatePos(engine, level, cellPos);
               calcMetaStatePosByStatePosForNeighbours(engine, level, cellPos);
            }
         }
      }
   }

   public static void calcMetaStatePosByStatePosForNeighbours(final Engine engine, final Level level, final int cellPos) {
      final int metaStateSize = calcMetaStateSize(engine);
      for (int pos = 1; pos < metaStateSize; pos++) {
         calcMetaStatePosByStatePos(engine, level, cellPos - pos);
         calcMetaStatePosByStatePos(engine, level, cellPos + pos);
      }
   }

   public static void calcNextStatePosByMetaStatePos(final Engine engine, final Level level, final int cellPos) {
      final int metaStateSize = calcMetaStateSize(engine);
      final LevelCell sourceLevelCell = readLevelCell(level, cellPos);
      final Cell sourceCell = readCell(level, cellPos);
      final MetaState metaState = engine.metaStateArr[sourceCell.metaStatePos];
      final int nextMetaStatePos = metaState.outputMetaStatePos;
      if (nextMetaStatePos == -1) throw new RuntimeException(String.format("Level-Cell-Size %d: For Meta-State %s no output state found.", engine.cellSize, convertToDebugString(metaState)));
      sourceCell.metaStatePos = nextMetaStatePos;
      final MetaState nextMetaState = engine.metaStateArr[nextMetaStatePos];
      //for (int metaPos = 0; metaPos < sourceLevelCell.metaCellArr.length; metaPos++) {
      for (int metaPos = 0; metaPos < metaStateSize; metaPos++) {
         sourceLevelCell.metaCellArr[metaPos].statePos = nextMetaState.inputMetaStatePosArr[metaPos];
      }
   }

   private static void calcMetaStatePosByStatePos(final Engine engine, final Level level, final int cellPos) {
      final LevelCell sourceLevelCell = readLevelCell(level, cellPos);
      int metaStatePos = calcMetaStatePosByLevelCell(engine, sourceLevelCell);

      // a = new int[X][Y][Z] = new int[X * Y * Z]
      // a[k][j][i] simply means (indirection levels apart) a[k*Y*X + j*X + i]

      final Cell sourceCell = readCell(level, cellPos);
      sourceCell.metaStatePos = metaStatePos;
   }

   private static void calcNextMetaStatePosByStatePos(final Universe universe, final int levelPos, final Engine engine, final int cellPos) {
      final Level level = readLevel(universe, levelPos);
      final LevelCell sourceLevelCell = readLevelCell(level, cellPos);
      final Cell sourceCell = readCell(level, cellPos);
      int metaStatePos = calcMetaStatePosByLevelCell(engine, sourceLevelCell);
      final int nextMetaStatePos = engine.inputMetaStatePosToMetaStateArr[metaStatePos].outputMetaStatePos;

      // Arr[l1Pos * engine.inputStateArr.length + l2Pos]
      // Arr[l1Pos * engine.inputStateArr.length + l2Pos * engine.inputStateArr.length + l3Pos]

      // a = new int[X][Y][Z] = new int[X * Y * Z]
      // a[k][j][i] simply means (indirection levels apart) a[k*Y*X + j*X + i]

      //final MetaState nextMetaState = engine.metaStateArr[nextMetaStatePos];
      sourceCell.metaStatePos = nextMetaStatePos;
      //for (int metaPos = 0; metaPos < sourceLevelCell2.metaCellArr.length; metaPos++) {
      //   sourceLevelCell2.metaCellArr[metaPos].statePos = nextMetaState.inputMetaStatePosArr[metaPos];
      //}
   }

   public static boolean CONFIG_use_levelUpOutputMetaStatePos = false;

   public static void runLevelUp(final Universe universe) {
      final Engine[] engineArr = universe.engineArr;
      for (int sourceLevelPos = engineArr.length - 2; sourceLevelPos >= 0; sourceLevelPos--) {
         final int targetLevelPos = sourceLevelPos + 1;
         final Engine sourceEngine = readEngine(universe, sourceLevelPos);
         final Level sourceLevel = readLevel(universe, sourceLevelPos);
         final Engine targetEngine = readEngine(universe, targetLevelPos);
         final Level targetLevel = readLevel(universe, targetLevelPos);

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
               final State sourceEqualState = calcEqualMetaStateValues(sourceLevel, cellPos);
               final int sourceEqualStatePos;

               if (sourceEqualState == posState) {
                  sourceEqualStatePos = 0;
               } else {
                  if (sourceEqualState == negState) {
                     sourceEqualStatePos = 1;
                  } else {
                     sourceEqualStatePos = -1;
                  }
               }

               final int levelUpOutputMetaStatePos;
               if (targetEngine.metaStateArr != null) {
                  final LevelCell targetLevelCell = readLevelCell(targetLevel, cellPos);
                  if (sourceEqualStatePos != -1) {
                     levelUpOutputMetaStatePos = targetEngine.metaStateArr[targetLevelCell.metaCellArr[0].metaStatePos].levelUpOutputMetaStatePosArr[sourceEqualStatePos];
                  } else {
                     levelUpOutputMetaStatePos = -1;
                  }
               } else {
                  levelUpOutputMetaStatePos = -1;
               }
               if (levelUpOutputMetaStatePos != -1) {
                  // Status aller Zellen in Level 1 auf 0 setzen
                  calcNewEqualState(sourceEngine, sourceLevel, cellPos, nulState);

                  final LevelCell targetLevelCell = readLevelCell(targetLevel, cellPos);

                  writeNewMetaStatePos(targetEngine, targetLevelCell, levelUpOutputMetaStatePos);
               } else {
                  final State targetEqualState = calcEqualMetaStateValues(targetLevel, cellPos);

                  if ((sourceEqualState != null) && (sourceEqualState != nulState) && (targetEqualState == nulState)) {
                     // Level 1 -> 2:

                     // Status aller Zellen in Level 1 auf 0 setzen
                     calcNewEqualState(sourceEngine, sourceLevel, cellPos, nulState);

                     // Status aller Zellen in Level 2 auf equalState setzen
                     calcNewEqualState(targetEngine, targetLevel, cellPos, sourceEqualState);
                  }
               }
            }
         }
      }
   }

   private static void writeNewMetaStatePos(final Engine targetEngine, final LevelCell targetLevelCell, final int newMetaStatePos) {
      final MetaState targetMetaState = targetEngine.metaStateArr[newMetaStatePos];
      targetLevelCell.metaCellArr[0].metaStatePos = newMetaStatePos;

      for (int pos = 0; pos < targetMetaState.inputMetaStatePosArr.length; pos++) {
         final int targetInputMetaStatePos = targetMetaState.inputMetaStatePosArr[pos];
         targetLevelCell.metaCellArr[pos].statePos = targetInputMetaStatePos;
      }
   }

   public static boolean CONFIG_use_levelDown_flag = false;

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
               final State sourceEqualState = calcEqualMetaStateValues(sourceLevel, cellPos);
               final State targetEqualState = calcEqualMetaStateValues(targetLevel, cellPos);

               if ((sourceEqualState != null) && (sourceEqualState != nulState) && (targetEqualState == nulState)) {
                  // Level 2 -> 1:

                  // Status aller Zellen in Level 2 auf 0 setzen
                  calcNewEqualState(sourceEngine, sourceLevel, cellPos, nulState);

                  // Status aller Zellen in Level 1 auf equalState setzen
                  calcNewEqualState(targetEngine, targetLevel, cellPos, sourceEqualState);
               }
            }
         }
      }
   }

   private static void calcNewEqualState(final Engine engine, final Level level, final int cellPos, final State newState) {
      final int cellSizeInLevel = engine.cellSize;

      for (int pos = 0; pos < cellSizeInLevel; pos++) {
         final Cell metaCell = readCell(level, cellPos, pos);
         metaCell.statePos = searchStatePosWithNewStateOnPos(engine, metaCell, pos, newState);
      }

      if (Objects.nonNull(engine.metaStateArr)) {
         calcMetaStatePosByStatePos(engine, level, cellPos);
         calcMetaStatePosByStatePosForNeighbours(engine, level, cellPos);
      }
   }

   public static void calcInitialMetaStates(final Universe universe) {
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
   }
}
