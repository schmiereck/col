package de.schmiereck.col.model;

import static de.schmiereck.col.model.State.NULL_pos;

import de.schmiereck.col.services.ProbabilityService;

public class HyperCell {
   public static final int Max_Probability = 10;
   public static final int DirProbStay = 0;
   public static final int DirProbLeft = 1;
   public static final int DirProbRight = 2;
   public static final int DirProbSize = 3;

   public int cellPos;
   public int metaStatePos;
   public int[] dirMetaStatePosArr;
   /**
    * 0:left 1:stay: 2:right
    */
   public final Probability dirProbability;

   public HyperCell() {
      this.cellPos = -1;
      this.metaStatePos = -1;
      this.dirMetaStatePosArr = new int[DirProbSize];
      this.dirMetaStatePosArr[DirProbStay] = NULL_pos;
      this.dirMetaStatePosArr[DirProbLeft] = NULL_pos;
      this.dirMetaStatePosArr[DirProbRight] = NULL_pos;
      this.dirProbability = new Probability(Max_Probability, DirProbSize);
      ProbabilityService.calcInit(this.dirProbability);
   }

   public HyperCell(final int cellPos, final int metaStatePos) {
      this.cellPos = cellPos;
      this.metaStatePos = metaStatePos;
      this.dirMetaStatePosArr = null;
      this.dirProbability = null;
   }

   public HyperCell(final int cellPos, final int[] dirMetaStatePosArr, final int[] probabilityArr) {
      this.cellPos = cellPos;
      this.metaStatePos = -1;
      this.dirMetaStatePosArr = new int[DirProbSize];
      this.dirMetaStatePosArr[DirProbStay] = dirMetaStatePosArr[DirProbStay];
      this.dirMetaStatePosArr[DirProbLeft] = dirMetaStatePosArr[DirProbLeft];
      this.dirMetaStatePosArr[DirProbRight] = dirMetaStatePosArr[DirProbRight];
      this.dirProbability = new Probability(Max_Probability, DirProbSize);
      this.dirProbability.probabilityArr[DirProbStay] = probabilityArr[DirProbStay];
      this.dirProbability.probabilityArr[DirProbLeft] = probabilityArr[DirProbLeft];
      this.dirProbability.probabilityArr[DirProbRight] = probabilityArr[DirProbRight];
      ProbabilityService.calcInit(this.dirProbability);
   }
}
