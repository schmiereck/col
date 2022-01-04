package de.schmiereck.col.model;

import static de.schmiereck.col.model.State.posState;

import java.util.HashMap;
import java.util.Objects;

public class MetaStateList {
   private int metaStateListLength;
   public MetaState metaStateArr[];
   public HashMap<Integer, MetaState> metaStateMap;

   public MetaStateList(final int inputStateArrSize, final int metaStateSize, final boolean useHash) {
      this.metaStateListLength = (int) Math.pow(inputStateArrSize, metaStateSize);

      if (useHash == true) {
         this.metaStateArr = null;
         this.metaStateMap = new HashMap<>();
      } else {
         this.metaStateArr = new MetaState[this.metaStateListLength];
         this.metaStateMap = null;
      }
   }

   public int size() {
      return this.metaStateListLength;
   }

   public MetaState get(final int msPos) {
      if (Objects.nonNull(this.metaStateArr)) {
         return this.metaStateArr[msPos];
      } else {
         return this.metaStateMap.get(Integer.valueOf(msPos));
      }
   }

   public void set(final int msPos, final MetaState metaState) {
      if (Objects.nonNull(this.metaStateArr)) {
         this.metaStateArr[msPos] = metaState;
      } else {
         this.metaStateMap.put(Integer.valueOf(msPos), metaState);
      }
   }
}
