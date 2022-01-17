package de.schmiereck.col.prob.model;

public class ProbUniverse {
   public final int universeSize;
   public final ProbCell[] probCellArr;

   public ProbUniverse(final int universeSize) {
      this.universeSize = universeSize;
      this.probCellArr = new ProbCell[this.universeSize];
   }
}
