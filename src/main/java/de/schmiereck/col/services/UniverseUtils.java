package de.schmiereck.col.services;

import static de.schmiereck.col.model.State.NULL_pos;
import static de.schmiereck.col.services.StateUtils.convertToValue;

import de.schmiereck.col.model.Cell;
import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Event;
import de.schmiereck.col.model.LevelCell;
import de.schmiereck.col.model.MetaState;
import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.State;
import de.schmiereck.col.model.Universe;

public class UniverseUtils {

   public static void printCells(final Universe universe, final Part part, final int cnt) {
      printCells(universe, part, cnt, null);
   }

   public static void printCells(final Universe universe, final int cnt, final String msg) {
      for (final Part part : universe.partList) {
         printCells(universe, part, cnt, msg);
      }
   }
   public static void printCells(final Universe universe, final Part part, final int cnt, final String msg) {
      final int enginePos = part.enginePos;
      {
         final Engine engine = readEngine(universe, enginePos);
         for (int levelShift = engine.cellSize - 1; levelShift >= 0; levelShift--) {
            printCellLine(universe, engine, part, cnt, enginePos, levelShift);
         }
         System.out.print("  ------- ");
         System.out.print(" ---------- ".repeat(universe.universeSize + (engine.cellSize + (universe.fieldEngine.maxCellSize - engine.cellSize) - 1)));
         if (msg != null) System.out.print(": " + msg);
         System.out.println();
      }
   }

   private static void printCellLine(final Universe universe, final Engine engine, final Part part, final int cnt, final int enginePos, final int levelShift) {
      final int metaPos = (engine.cellSize - 1) - levelShift;
      final int xPos = levelShift;
      System.out.printf("%4d/%1d/%1d:%s ", cnt, enginePos, levelShift, " ".repeat((metaPos + (universe.fieldEngine.maxCellSize - engine.cellSize)) * (1+4+1+2+1+1+1+1)));
      for (int cellPos = 0; cellPos < universe.universeSize; cellPos += engine.cellSize) {
         final int printCellPos = calcCellPos(universe, cellPos);
         final int cellMetaStatePos;
         final int cellStatePos;
         final State state;

         if (printCellPos == part.hyperCell.cellPos) {
            cellMetaStatePos = part.hyperCell.metaStatePos;
            final MetaState metaState = engine.metaStateArr[cellMetaStatePos];
            final int inputMetaStatePos = metaState.inputMetaStatePosArr[levelShift];
            cellStatePos = inputMetaStatePos;
            state = engine.inputStateArr[inputMetaStatePos];
         } else {
            cellMetaStatePos = NULL_pos;
            cellStatePos = NULL_pos;
            state = engine.inputStateArr[NULL_pos];
         }

         printCellState(cellMetaStatePos, cellStatePos, state);
      }
      //System.out.printf("  - xPos:%d, levelShift:%d, metaPos:%1d", xPos, levelShift, metaPos);
      System.out.println();
   }

   private static void printCell(final Engine engine, final Cell cell) {
      final State state = engine.inputStateArr[cell.statePos];
      printCellState(cell.metaStatePos, cell.statePos, state);
   }

   private static void printCellState(final int cellMetaStatePos, final int cellStatePos, final State cellState) {
      System.out.print(">");
      for (int statePos = 0; statePos < cellState.inputStateArr.length; statePos++) {
         final State inputState = cellState.inputStateArr[statePos];
         final int value = convertToValue(inputState);
         if (statePos > 0) {
            System.out.print("  ");
         }
         System.out.printf("%4d|%2d|%2d", cellMetaStatePos, cellStatePos, value);
      }
      System.out.print(" ");
   }

   static Engine readEngine(final Universe universe, final int enginePos) {
      return universe.fieldEngine.engineArr[enginePos];
   }

   public static Part setMetaStatePos(final Universe universe, final int cellPos, final int enginePos, final int metaStatePos) {
      return setMetaStatePos(universe, null, null, cellPos, enginePos, metaStatePos);
   }

   public static Part setMetaStatePos(final Universe universe, final Event event, final Part parentPart, final int cellPos, final int enginePos, final int metaStatePos) {
      final Part part = new Part(event, parentPart, enginePos, cellPos, metaStatePos);
      universe.partList.add(part);
      return part;
   }

   public static void setMetaStatePos(final Part part, final int cellPos, final int metaStatePos) {
      part.hyperCell.cellPos = cellPos;
      part.hyperCell.metaStatePos = metaStatePos;
   }

   public static Cell readCell(final LevelCell levelCell) {
      return readCell(levelCell, 0);
   }

   public static Cell readCell(final LevelCell levelCell, final int metaCellPos) {
      return levelCell.metaCellArr[metaCellPos];
   }

   public static int readMetaStatePos(final LevelCell levelCell) {
      return readMetaStatePos(levelCell, 0);
   }

   public static int readMetaStatePos(final LevelCell levelCell, final int metaCellPos) {
      return levelCell.metaCellArr[metaCellPos].metaStatePos;
   }
   public static int calcCellPos(final Universe universe, final int pos) {
      return calcCellPos(universe.universeSize, pos);
   }

   public static int calcCellPos(final int universeSize, final int pos) {
      final int retPos;
      if (pos >= universeSize) {
         retPos = pos - universeSize;
      } else {
         if (pos < 0) {
            retPos = pos + universeSize;
         } else {
            retPos = pos;
         }
      }
      return retPos;
   }
}
