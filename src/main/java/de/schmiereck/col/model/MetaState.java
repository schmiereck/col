package de.schmiereck.col.model;

public class MetaState {
   public int[] inputMetaStatePosArr;
   public int outputMetaStatePos;
   public int metaStateInputStatePos;
   //public int metaStateOutputStatePos;
   public boolean levelDown;
   public int cellPosOffset;

   public MetaState(final int outputMetaStatePos, final int ... inputMetaStatePosArr) {
      this.outputMetaStatePos = outputMetaStatePos;
      this.inputMetaStatePosArr = inputMetaStatePosArr;
      this.metaStateInputStatePos = -1;
      this.levelDown = false;
      this.cellPosOffset = 0;
   }
}
