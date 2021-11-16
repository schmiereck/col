package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.services.EngineService.calcMetaStatePosByLevelCell;
import static de.schmiereck.col.services.EngineService.searchMetaStatePos;
import static de.schmiereck.col.services.EngineService.searchStatePosWithNewStateOnPos;
import static de.schmiereck.col.services.LevelService.calcEqualMetaStateValues;
import static de.schmiereck.col.services.StateUtils.convertToDebugString;
import static de.schmiereck.col.services.StateUtils.convertToValue;
import static de.schmiereck.col.services.UniverseUtils.readCell;
import static de.schmiereck.col.services.UniverseUtils.readCellSize;
import static de.schmiereck.col.services.UniverseUtils.readEngine;
import static de.schmiereck.col.services.UniverseUtils.readLevel;
import static de.schmiereck.col.services.UniverseUtils.readLevelCell;

import de.schmiereck.col.model.Cell;
import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Level;
import de.schmiereck.col.model.LevelCell;
import de.schmiereck.col.model.MetaState;
import de.schmiereck.col.model.State;
import de.schmiereck.col.model.Universe;

import java.util.Objects;

public class UniverseService {

   public static void run(final Universe universe) {
      runLevelUp(universe);
      runCalcNextState(universe);
      runLevelDown(universe);
      runCalcNextState(universe);
   }

   public static void run2(final Universe universe) {
      runLevelUp(universe);
      runCalcNextMetaState(universe);
      runLevelDown(universe);
      runCalcNextState(universe);
   }

   public static void run2b(final Universe universe) {
      runLevelUp(universe);
      runCalcNextState(universe);
      runLevelDown(universe);
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
            final Cell sourceCell = readCell(level, cellPos);
            final int outputStatePos = engine.outputStatePosArr.get(sourceCell.statePos);

            final Cell targetCell = sourceCell;
            targetCell.statePos = outputStatePos;

            if (engine.metaStateArr != null) {
               final MetaState metaState = engine.metaStateArr[sourceCell.metaStatePos];
               final int nextMetaStatePos = searchForMetaStatePos(engine, outputStatePos, metaState);
               targetCell.metaStatePos = nextMetaStatePos;

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
            for (int cellPos = 0; cellPos < universe.universeSize; cellPos++) {
               calcNextStatePosByMetaStatePos(engine, level, cellPos);
               calcMetaStatePosByStatePosForNeighbours(engine, level, cellPos);
            }
         }
      }
   }

   public static void calcMetaStatePosByStatePosForNeighbours(final Engine engine, final Level level, final int cellPos) {
      for (int pos = 1; pos < engine.cellSize; pos++) {
         calcMetaStatePosByStatePos(engine, level, cellPos - pos);
         calcMetaStatePosByStatePos(engine, level, cellPos + pos);
      }
   }

   public static void calcNextStatePosByMetaStatePos(final Engine engine, final Level level, final int cellPos) {
      final LevelCell sourceLevelCell = readLevelCell(level, cellPos);
      final Cell sourceCell = readCell(level, cellPos);
      final MetaState metaState = engine.metaStateArr[sourceCell.metaStatePos];
      final int nextMetaStatePos = metaState.outputMetaStatePos;
      if (nextMetaStatePos == -1) throw new RuntimeException(String.format("For Meta-State %s no output state found.", convertToDebugString(metaState)));
      sourceCell.metaStatePos = nextMetaStatePos;
      final MetaState nextMetaState = engine.metaStateArr[nextMetaStatePos];
      for (int metaPos = 0; metaPos < sourceLevelCell.metaCellArr.length; metaPos++) {
         sourceLevelCell.metaCellArr[metaPos].statePos = nextMetaState.inputMetaStatePosArr[metaPos];
      }
   }

   private static void calcMetaStatePosByStatePos(final Engine engine, final Level level, final int cellPos) {
      final LevelCell sourceLevelCell = readLevelCell(level, cellPos);
      final Cell sourceCell = readCell(level, cellPos);
      int metaStatePos = calcMetaStatePosByLevelCell(engine, sourceLevelCell);

      // a = new int[X][Y][Z] = new int[X * Y * Z]
      // a[k][j][i] simply means (indirection levels apart) a[k*Y*X + j*X + i]

      //final MetaState nextMetaState = engine.metaStateArr[nextMetaStatePos];
      sourceCell.metaStatePos = metaStatePos;
      //for (int metaPos = 0; metaPos < sourceLevelCell2.metaCellArr.length; metaPos++) {
      //   sourceLevelCell2.metaCellArr[metaPos].statePos = nextMetaState.inputMetaStatePosArr[metaPos];
      //}
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

   public static void runLevelDown(final Universe universe) {
      final Engine[] engineArr = universe.engineArr;
      for (int levelPos = engineArr.length - 2; levelPos >= 0; levelPos--) {
         final int targetLevelPos = levelPos + 1;
         final Engine engine = readEngine(universe, levelPos);
         final Level level = readLevel(universe, levelPos);
         final Engine targetEngine = readEngine(universe, targetLevelPos);
         final Level targetLevel = readLevel(universe, targetLevelPos);

         for (int cellPos = 0; cellPos < universe.universeSize; cellPos++) {
            final int cellSize = readCellSize(level);
            final int targetCellSize = readCellSize(targetLevel);

            final State equalState = calcEqualMetaStateValues(level, cellPos, cellSize);
            final State targetEqualState = calcEqualMetaStateValues(targetLevel, cellPos, targetCellSize);

            if ((equalState != null) && (equalState != nulState) && (targetEqualState == nulState)) {
               // Level 1 -> 2:

               // Status aller Zellen in Level 1 auf 0 setzen
               calcNewEqualState(engine, level, cellPos, nulState);

               // Status aller Zellen in Level 2 auf equalState setzen
               calcNewEqualState(targetEngine, targetLevel, cellPos, equalState);
            }
         }
      }
   }

   public static void runLevelUp(final Universe universe) {
      final Engine[] engineArr = universe.engineArr;
      for (int levelPos = 1; levelPos < engineArr.length; levelPos++) {
         final int targetLevelPos = levelPos - 1;
         final Engine engine = readEngine(universe, levelPos);
         final Level level = readLevel(universe, levelPos);
         final Engine targetEngine = readEngine(universe, targetLevelPos);
         final Level targetLevel = readLevel(universe, targetLevelPos);

         for (int cellPos = 0; cellPos < universe.universeSize; cellPos++) {
            final int cellSize = readCellSize(level);
            final int targetCellSize = readCellSize(targetLevel);

            final State equalState = calcEqualMetaStateValues(level, cellPos, cellSize);
            final State targetEqualState = calcEqualMetaStateValues(targetLevel, cellPos, targetCellSize);

            if ((equalState != null) && (equalState != nulState) && (targetEqualState == nulState)) {
               // Level 2 -> 1:

               // Status aller Zellen in Level 2 auf 0 setzen
               calcNewEqualState(engine, level, cellPos, nulState);

               // Status aller Zellen in Level 1 auf equalState setzen
               calcNewEqualState(targetEngine, targetLevel, cellPos, equalState);
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
