package de.schmiereck.col.model;

public class MetaState {
   public int[] inputMetaStatePosArr;
   public int outputMetaStatePos;
   public int metaStateInputStatePos;
   public int[] levelUpOutputMetaStatePosArr;
   //public int metaStateOutputStatePos;

   public MetaState(final int outputMetaStatePos, final int ... inputMetaStatePosArr) {
      this.outputMetaStatePos = outputMetaStatePos;
      this.inputMetaStatePosArr = inputMetaStatePosArr;
      this.metaStateInputStatePos = -1;
      this.levelUpOutputMetaStatePosArr = new int[] { -1, -1 };
   }
}
