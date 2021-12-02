package de.schmiereck.col.model;

public class MetaState {
   public int[] inputMetaStatePosArr;
   public int outputMetaStatePos;
   public int metaStateInputStatePos;
   public int[] levelUpOutputMetaStatePosArr;
   public int[] levelDownOutputMetaStatePosArr;
   //public int metaStateOutputStatePos;
   public boolean levelDown;

   public MetaState(final int outputMetaStatePos, final int ... inputMetaStatePosArr) {
      this.outputMetaStatePos = outputMetaStatePos;
      this.inputMetaStatePosArr = inputMetaStatePosArr;
      this.metaStateInputStatePos = -1;
      this.levelUpOutputMetaStatePosArr = new int[] { -1, -1 };
      this.levelDownOutputMetaStatePosArr = new int[] { -1, -1 };
      this.levelDown = false;
   }
}
