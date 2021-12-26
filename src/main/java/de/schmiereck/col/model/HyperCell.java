package de.schmiereck.col.model;

import static de.schmiereck.col.model.State.NULL_pos;

public class HyperCell {
   public static final int Max_Probability = 10;
   public static final int DirProbStay = 0;
   public static final int DirProbLeft = 1;
   public static final int DirProbRight = 2;
   public static final int DirProbSize = 3;

   public int cellPos;
   //PROB: public int metaStatePos;
   public int[] metaStatePosArr;
   /**
    * 0:left 1:stay: 2:right
    */
   public final Probability dirProbability;

   public HyperCell() {
      this.cellPos = -1;
      this.metaStatePosArr = new int[DirProbSize];
      this.metaStatePosArr[DirProbStay] = NULL_pos;
      this.metaStatePosArr[DirProbLeft] = NULL_pos;
      this.metaStatePosArr[DirProbRight] = NULL_pos;
      this.dirProbability = new Probability(Max_Probability, DirProbSize);
   }

   public HyperCell(final int cellPos, final int[] metaStatePosArr, final int[] probabilityArr) {
      this.cellPos = cellPos;
      this.metaStatePosArr = new int[DirProbSize];
      this.metaStatePosArr[DirProbStay] = metaStatePosArr[DirProbStay];
      this.metaStatePosArr[DirProbLeft] = metaStatePosArr[DirProbLeft];
      this.metaStatePosArr[DirProbRight] = metaStatePosArr[DirProbRight];
      this.dirProbability = new Probability(Max_Probability, DirProbSize);
      this.dirProbability.probabilityArr[DirProbStay] = probabilityArr[DirProbStay];
      this.dirProbability.probabilityArr[DirProbLeft] = probabilityArr[DirProbLeft];
      this.dirProbability.probabilityArr[DirProbRight] = probabilityArr[DirProbRight];
   }
}
