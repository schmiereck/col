package de.schmiereck.col.model;

public class Level {
   public final Engine engine;
   public final LevelCell[] levelCellArr;

   public Level(final Engine engine, final int levelSize) {
      this.engine = engine;
      this.levelCellArr = new LevelCell[levelSize];
   }
}
