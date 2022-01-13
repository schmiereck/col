package de.schmiereck.col.model;

public class PMatrix {
   public final int[][] m;

   public PMatrix(final int[][] m) {
      this.m = m;
   }

   public PMatrix(final int size) {
      this.m = new int[size][size];
   }
}
