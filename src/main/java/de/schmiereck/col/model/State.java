package de.schmiereck.col.model;

public class State {
   public final static int NULL_pos = 0;

   public final static State nulState = new State(0);
   public final static State posState = new State(0);
   public final static State negState = new State(0);

   public State[] inputStateArr;

   public State(final int size) {
      this.inputStateArr = new State[size];
   }
   public State(final int size, final State ... state) {
      this.inputStateArr = new State[size];
      for (int pos = 0; pos < state.length; pos++) {
         this.inputStateArr[pos] = state[pos];
      }
   }
   public State(final State ... state) {
      this.inputStateArr = new State[state.length];
      for (int pos = 0; pos < state.length; pos++) {
         this.inputStateArr[pos] = state[pos];
      }
   }
}
