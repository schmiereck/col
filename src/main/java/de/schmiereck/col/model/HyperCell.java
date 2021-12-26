package de.schmiereck.col.model;

import static de.schmiereck.col.model.State.NULL_pos;

public class HyperCell {
   public static final int Max_Probability = 10;
   public static final int ProbStay = 0;
   public static final int ProbLeft = 1;
   public static final int ProbRight = 2;

   public int cellPos;
   public int metaStatePos;
   /**
    * 0:left 1:stay: 2:right
    */
   public final Probability dirProbability;

   public HyperCell() {
      this.cellPos = -1;
      this.metaStatePos = NULL_pos;
      this.dirProbability = new Probability(Max_Probability, 3);
   }

   public HyperCell(final int cellPos, final int metaStatePos) {
      this.cellPos = cellPos;
      this.metaStatePos = metaStatePos;
      this.dirProbability = new Probability(Max_Probability, 3);
   }
}
