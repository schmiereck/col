package de.schmiereck.col.prob.model;

import de.schmiereck.col.model.Probability;

public class Part {
   //public Probability inProb;
   public final Probability prob;
   public int field;

   public Part(final int maxProbability, final int probabilitySize) {
      this.prob = new Probability(maxProbability, probabilitySize);
   }
}
