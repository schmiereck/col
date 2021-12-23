package de.schmiereck.col.model;

public class Possibility {
   public final int maxPossibility;
   public final int posibilitySize;
   public final int[] posibilityArr;
   public final int[] posibilityCntArr;
   public int lastPossibility;

   public Possibility(final int maxPossibility, final int posibilitySize) {
      this.maxPossibility = maxPossibility;
      this.posibilitySize = posibilitySize;
      this.posibilityArr = new int[posibilitySize];
      this.posibilityCntArr = new int[posibilitySize];
      this.lastPossibility = -1;
   }
}
