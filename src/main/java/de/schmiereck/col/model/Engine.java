package de.schmiereck.col.model;

import de.schmiereck.col.Main2;

public class Engine {
   public final int cellSize;
   public State[] inputStateArr;
   public int[] outputStatePosArr;
   public MetaState metaStateArr[];

   public Engine(final int cellSize) {
      this.cellSize = cellSize;
      final int size = (int)Math.pow(3, cellSize);
      this.inputStateArr = new State[size];
      this.outputStatePosArr = new int[size];
   }

   public Engine(final int cellSize, final int size) {
      this.cellSize = cellSize;
      this.inputStateArr = new State[size];
      this.outputStatePosArr = new int[size];
   }

   public void setState(final int pos, final State inputState, final int outputStatePos) {
      this.inputStateArr[pos] = inputState;
      this.outputStatePosArr[pos] = outputStatePos;
   }

   public State getInputState(final int pos) {
      return this.inputStateArr[pos];
   }

}
