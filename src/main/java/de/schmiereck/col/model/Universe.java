package de.schmiereck.col.model;

import static de.schmiereck.col.services.UniverseUtils.calcCellPos;

/**
 * <code>
 *    00000 33333 66666 99999
 *   .0 11111 44444 7777. 0000.
 *   .111 22222 55555 88888 11.
 *                              ^
 *    0-0 2-2 4-4 6-6 8-8 0-0   cell[].metaCellArr[0]
 *   .1 1-1 3-3 5-5 7-7 9-9 1-. cell[].metaCellArr[1]
 *                              ^
 *    0 1 2 3 4 5 6 7 8 9 0 1   cell[]
 * </code>
 * Level[] -> { LevelCell[], Engine } -> Cell[] -> { Cell[] metaCellArr, statePos }
 */
public class Universe {
   public final Engine[] engineArr;
   public final int universeSize;
   public final Level[] levelArr;

   public Universe(final Engine[] engineArr, final int levelSize) {
      this.engineArr = engineArr;
      this.universeSize = levelSize;

      final int levelCount = engineArr.length;
      this.levelArr = new Level[levelCount];

      for (int levelPos = 0; levelPos < levelCount; levelPos++) {
         final Engine engine = engineArr[levelPos];

         final Level level = new Level(engine, levelSize);
         this.levelArr[levelPos] = level;

         for (int levelCellPos = 0; levelCellPos < levelSize; levelCellPos++) {
            final LevelCell levelCell = new LevelCell(engine.cellSize);

            level.levelCellArr[levelCellPos] = levelCell;
         }

         for (int levelCellPos = 0; levelCellPos < levelSize; levelCellPos++) {
            final Cell newCell = new Cell();

            for (int metaCellPos = 0; metaCellPos < engine.cellSize; metaCellPos++) {
               level.levelCellArr[calcCellPos(level, levelCellPos + metaCellPos)].metaCellArr[metaCellPos] = newCell;
            }
         }
      }
   }
}
