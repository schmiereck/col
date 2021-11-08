package de.schmiereck.col.model;

public class MetaState {
   public State inputMetaStateArr[];
   public int outputMetaStatePos;

   public MetaState(final int outputMetaStatePos, final State ... inputMetaStateArr) {
      this.outputMetaStatePos = outputMetaStatePos;
      this.inputMetaStateArr = inputMetaStateArr;
   }
}
