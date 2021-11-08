package de.schmiereck.col.model;

public class MetaState {
   public int inputMetaStatePosArr[];
   public int outputMetaStatePos;

   public MetaState(final int outputMetaStatePos, final int ... inputMetaStatePosArr) {
      this.outputMetaStatePos = outputMetaStatePos;
      this.inputMetaStatePosArr = inputMetaStatePosArr;
   }
}
