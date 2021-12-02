package de.schmiereck.col.services;

import static de.schmiereck.col.services.StateUtils.convertToValue;

import de.schmiereck.col.model.Cell;
import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Event;
import de.schmiereck.col.model.Level;
import de.schmiereck.col.model.LevelCell;
import de.schmiereck.col.model.State;
import de.schmiereck.col.model.Universe;

public class UniverseUtils {

   public static void printCells(final Universe universe, final int cnt) {
      printCells(universe, cnt, null);
   }

   public static void printCells(final Universe universe, final int cnt, final String msg) {
      final Engine[] engineArr = universe.engineArr;
      for (int levelPos = engineArr.length - 1; levelPos >= 0; levelPos--) {
         final Engine engine = readEngine(universe, levelPos);
         final Level level = readLevel(universe, levelPos);
         for (int levelShift = engine.cellSize - 1; levelShift >= 0; levelShift--) {
            printCellLine(universe, engine, level, cnt, engine.cellSize, levelPos, levelShift);
         }
      }
      System.out.print("  ------- ");
      System.out.print(" ---------- ".repeat(universe.universeSize));
      if (msg != null) System.out.print(": " + msg);
      System.out.println();
   }

   private static void printCellLine(final Universe universe, final Engine engine, final Level level, final int cnt, final int cellSize, final int levelPos, final int levelShift) {
      final int metaPos = (cellSize - 1) - levelShift;
      System.out.printf("%4d/%1d/%1d:%s ", cnt, levelPos, metaPos, " ".repeat(levelShift * (1+4+1+2+1+1+1+1)));
      for (int cellPos = levelShift; cellPos < universe.universeSize; cellPos += cellSize) {
         final Cell cell = readCell(level, cellPos);
         printCell(engine, cell);
      }
      //System.out.printf("  - levelNr:%d, levelShift:%d, metaPos:%1d", levelNr, levelShift, metaPos);
      System.out.println();
   }

   private static void printCell(final Engine engine, final Cell cell) {
      final State state = engine.inputStateArr[cell.statePos];
      System.out.print(">");
      for (int statePos = 0; statePos < state.inputStateArr.length; statePos++) {
         final State inputState = state.inputStateArr[statePos];
         final int value = convertToValue(inputState);
         if (statePos > 0) {
            System.out.print("  ");
         }
         System.out.printf("%4d|%2d|%2d", cell.metaStatePos, cell.statePos, value);
      }
      System.out.print(" ");
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

   static Engine readEngine(final Universe universe, final int levelPos) {
      return universe.engineArr[levelPos];
   }

   public static void setStatePos(final Universe universe, final int cellPos, final int levelPos, final int statePos) {
      setStatePos(universe, cellPos, levelPos, 0, statePos, null);
   }

   public static void setStatePos(final Universe universe, final int cellPos, final int levelPos, final int statePos, final Event event) {
      setStatePos(universe, cellPos, levelPos, 0, statePos, event);
   }

   public static void setStatePos(final Universe universe, final int cellPos, final int levelPos, final int metaCellPos, final int statePos) {
      setStatePos(universe, cellPos, levelPos, metaCellPos, statePos, null);
   }

   public static void setStatePos(final Universe universe, final int cellPos, final int levelPos, final int metaCellPos, final int statePos, final Event event) {
      final Level level = readLevel(universe, levelPos);
      setStatePos(level, cellPos, metaCellPos, statePos, event);
   }

   public static void setStatePos(final Level level, final int cellPos, final int metaCellPos, final int statePos) {
      setStatePos(level, cellPos, metaCellPos, statePos, null);
   }

   public static void setStatePos(final Level level, final int cellPos, final int metaCellPos, final int statePos, final Event event) {
      final Cell cell = level.levelCellArr[cellPos].metaCellArr[metaCellPos];
      cell.statePos = statePos;
      cell.event = event;
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
      final LevelCell levelCell = readLevelCell(level, cellPos);
      return readCell(levelCell);
   }

   public static Cell readCell(final LevelCell levelCell) {
      return readCell(levelCell, 0);
   }

   public static Cell readCell(final LevelCell levelCell, final int metaCellPos) {
      return levelCell.metaCellArr[metaCellPos];
   }

   public static Cell readCell(final Level level, final int cellPos, final int metaCellPos) {
      final LevelCell levelCell = readLevelCell(level, cellPos);
      return readCell(levelCell, metaCellPos);
   }

   public static int readCellStatePos(final Universe universe, final int cellPos, final int levelPos, final int metaCellPos) {
      final Level level = readLevel(universe, levelPos);
      return readLevelCell(level, cellPos).metaCellArr[metaCellPos].statePos;
   }

   public static int readMetaStatePos(final LevelCell levelCell) {
      return readMetaStatePos(levelCell, 0);
   }

   public static int readMetaStatePos(final LevelCell levelCell, final int metaCellPos) {
      return levelCell.metaCellArr[metaCellPos].metaStatePos;
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
