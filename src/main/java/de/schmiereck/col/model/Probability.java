package de.schmiereck.col.model;

public class Probability {
   public final int maxProbability;
   public final int probabilitySize;
   public final int[] probabilityArr;
   public final int[] probabilityCntArr;
   public final int[] lastProbabilityArr;
   public final int[] lastProbabilityCntArr;
   public int lastProbabilityPos;

   public Probability(final int maxProbability, final int probabilitySize) {
      this.maxProbability = maxProbability;
      this.probabilitySize = probabilitySize;
      this.probabilityArr = new int[probabilitySize];
      this.probabilityCntArr = new int[probabilitySize];
      this.lastProbabilityArr = new int[probabilitySize];
      this.lastProbabilityCntArr = new int[probabilitySize];
      this.lastProbabilityPos = -1;
   }
}
