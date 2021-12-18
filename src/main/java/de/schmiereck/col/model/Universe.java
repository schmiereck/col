package de.schmiereck.col.model;

import static de.schmiereck.col.services.EngineService.calcMetaStateSize;
import static de.schmiereck.col.services.UniverseUtils.calcCellPos;

import java.util.LinkedList;
import java.util.List;

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
 * Universe
 *    -> Engine[] engineArr
 *    -> Level[] levelArr
 *       -> Engine engine
 *       -> LevelCell[] levelCellArr
 *          -> Cell[] metaCellArr
 *             -> statePos
 *             -> metaStatePos
 *             -> Event event
 */
public class Universe {
   public final FieldEngine fieldEngine;
   public final int universeSize;
   public final List<Part> partList = new LinkedList<>();

   public Universe(final FieldEngine fieldEngine, final int levelSize) {
      this.fieldEngine = fieldEngine;
      this.universeSize = levelSize;

      final int levelCount = fieldEngine.engineArr.length;

      for (int enginePos = 0; enginePos < levelCount; enginePos++) {
         final Engine engine = fieldEngine.engineArr[enginePos];

         final int metaStateSize = calcMetaStateSize(engine);
      }
   }
}
