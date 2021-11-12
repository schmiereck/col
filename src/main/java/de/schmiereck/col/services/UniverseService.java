package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.services.EngineService.calcMetaStatePosByLevelCell;
import static de.schmiereck.col.services.EngineService.searchMetaStatePos;
import static de.schmiereck.col.services.EngineService.searchStatePosWithNewStateOnPos;
import static de.schmiereck.col.services.LevelService.calcEqualMetaStateValues;
import static de.schmiereck.col.services.StateUtils.convertToValue;

import de.schmiereck.col.model.Cell;
import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Level;
import de.schmiereck.col.model.LevelCell;
import de.schmiereck.col.model.MetaState;
import de.schmiereck.col.model.State;
import de.schmiereck.col.model.Universe;

import java.util.Objects;

public class UniverseService {

   public static void printCells(final Universe universe, final int cnt) {
      final Engine[] engineArr = universe.engineArr;
      for (int levelPos = engineArr.length - 1; levelPos >= 0; levelPos--) {
         final Engine engine = readEngine(universe, levelPos);
         final Level level = readLevel(universe, levelPos);
         final int levelNr = engine.cellSize; //levelPos + 1;
         for (int levelShift = 0; levelShift < levelNr; levelShift++) {
            final int metaPos = levelShift == 0 ? 0 : levelNr - levelShift;
            System.out.printf("%4d/%1d/%1d:%s ", cnt, levelPos, metaPos, " ".repeat((levelShift) * (1+4+1+2+1+2+1+1)));
            for (int cellPos = 0; cellPos < universe.universeSize; cellPos += levelNr) {
               final Cell cell = readCell(level, cellPos + levelShift);
               //if (cell.block) {
               //   System.out.printf("(**/**/**) ");
               //} else {
               final State state = engine.inputStateArr[cell.statePos];
               System.out.print("(");
               for (int statePos = 0; statePos < state.inputStateArr.length; statePos++) {
                  final State inputState = state.inputStateArr[statePos];
                  final int value = convertToValue(inputState);
                  if (statePos > 0) {
                     System.out.print("   ");
                  }
                  System.out.printf("%4d|%2d|%2d", cell.metaStatePos, cell.statePos, value);
               }
               System.out.print(") ");
               //}
            }
            System.out.println();
         }
      }
      System.out.print("  ------- ");
      System.out.println("---------- ".repeat(universe.universeSize));
   }

   public static void printCellsMinimal(final Universe universe, final int cnt) {
      final Engine[] engineArr = universe.engineArr;

      System.out.printf("%4d: ", cnt);

      for (int cellPos = 0; cellPos < universe.universeSize; cellPos++) {
         int outValue = 0;
         for (int levelPos = 0; levelPos < engineArr.length; levelPos++) {
            final Engine engine = readEngine(universe, levelPos);
            final Level level = readLevel(universe, levelPos);
            final Cell cell = readCell(level, cellPos);

            final State state = engine.inputStateArr[cell.statePos];
            for (int statePos = 0; statePos < state.inputStateArr.length; statePos++) {
               final State inputState = state.inputStateArr[statePos];
               final int value = convertToValue(inputState);
               outValue += value * (engineArr.length - levelPos);
            }
         }
         if (cellPos > 0) {
            System.out.print("|");
         }
         System.out.printf("%2d", outValue);
      }
      System.out.println();
   }

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
            final int outputStatePos = engine.outputStatePosArr[sourceCell.statePos];

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
               calcNextStatePosByMetaStatePos(level, engine, cellPos);
               calcMetaStatePosByStatePosForNeighbours(engine, level, cellPos);
            }
         }
      }
   }

   private static void calcMetaStatePosByStatePosForNeighbours(final Engine engine, final Level level, final int cellPos) {
      for (int pos = 1; pos < engine.cellSize; pos++) {
         calcMetaStatePosByStatePos(engine, level, cellPos - pos);
         calcMetaStatePosByStatePos(engine, level, cellPos + pos);
      }
   }

   private static void calcNextStatePosByMetaStatePos(final Level level, final Engine engine, final int cellPos) {
      final LevelCell sourceLevelCell = readLevelCell(level, cellPos);
      final Cell sourceCell = readCell(level, cellPos);
      final MetaState metaState = engine.metaStateArr[sourceCell.metaStatePos];
      final int nextMetaStatePos = metaState.outputMetaStatePos;
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

   private static Engine readEngine(final Universe universe, final int levelPos) {
      return universe.engineArr[levelPos];
   }

   public static void setStatePos(final Universe universe, final int cellPos, final int levelPos, final int statePos) {
      setStatePos(universe, cellPos, levelPos, 0, statePos);
   }

   public static void setStatePos(final Universe universe, final int cellPos, final int levelPos, final int metaCellPos, final int statePos) {
      final Level level = readLevel(universe, levelPos);
      setStatePos(level, cellPos, metaCellPos, statePos);
   }

   public static void setStatePos(final Level level, final int cellPos, final int metaCellPos, final int statePos) {
      level.levelCellArr[cellPos].metaCellArr[metaCellPos].statePos = statePos;
   }

   public static State readCellState(final Universe universe, final int cellPos, final int levelPos, final int inputStatePos) {
      final Engine levelEngine = readEngine(universe, levelPos);
      final Level level = readLevel(universe, levelPos);
      return levelEngine.inputStateArr[readCell(level, cellPos).statePos].inputStateArr[inputStatePos];
   }

   public static Cell readCell(final Universe universe, final int cellPos, final int levelPos) {
      final Level level = readLevel(universe, levelPos);
      return readCell(level, cellPos, 0);
   }

   public static Cell readCell(final Universe universe, final int cellPos, final int levelPos, final int metaCellPos) {
      final Level level = readLevel(universe, levelPos);
      return readLevelCell(level, cellPos).metaCellArr[metaCellPos];
   }

   public static Cell readCell(final Level level, final int cellPos) {
      return readCell(level, cellPos, 0);
   }

   public static Cell readCell(final Level level, final int cellPos, final int metaCellPos) {
      return readLevelCell(level, cellPos).metaCellArr[metaCellPos];
   }

   public static int readCellStatePos(final Universe universe, final int cellPos, final int levelPos, final int metaCellPos) {
      final Level level = readLevel(universe, levelPos);
      return readLevelCell(level, cellPos).metaCellArr[metaCellPos].statePos;
   }

   public static Level readLevel(final Universe universe, final int levelPos) {
      return universe.levelArr[levelPos];
   }

   public static LevelCell readLevelCell(final Level level, final int cellPos) {
      return level.levelCellArr[calcCellPos(level, cellPos)];
   }

   public static int readCellSize(final Level level) {
      return level.engine.cellSize;
   }

   public static int calcCellPos(final Level level, final int pos) {
      return calcCellPos(level.levelSize, pos);
   }

   public static int calcCellPos(final int levelSize, final int pos) {
      final int retPos;
      if (pos >= levelSize) {
         retPos = pos - levelSize;
      } else {
         if (pos < 0) {
            retPos = pos + levelSize;
         } else {
            retPos = pos;
         }
      }
      return retPos;
   }
}
