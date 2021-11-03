package de.schmiereck.col.model;

public class State {

   public final static State nulState = new State(0);
   public final static State posState = new State(0);
   public final static State negState = new State(0);

   public State[] inputStates;

   public State(final int size) {
      this.inputStates = new State[size];
   }
   public State(final int size, final State ... state) {
      this.inputStates = new State[size];
      for (int pos = 0; pos < state.length; pos++) {
         this.inputStates[pos] = state[pos];
      }
   }
}
