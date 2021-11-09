package de.schmiereck.col.model;

public class Level {
   public final Engine engine;
   public final int levelSize;
   public final LevelCell[] levelCellArr;

   public Level(final Engine engine, final int levelSize) {
      this.engine = engine;
      this.levelSize = levelSize;
      this.levelCellArr = new LevelCell[this.levelSize];
   }
}
