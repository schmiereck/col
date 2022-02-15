package de.schmiereck.col.prob.model;

import de.schmiereck.col.model.Probability;

public class Part {
   //public Probability inProb;
   public final Probability outProb;
   public int outField;

   public Part(final int maxProbability, final int probabilitySize) {
      this.outProb = new Probability(maxProbability, probabilitySize);
   }
}
