package de.schmiereck.col.model;

/**
 * Part of a Particle.
 */
public class Part {
   public int levelPos;
   public final HyperCell hyperCell;

   public Part(final int levelPos) {
      this.levelPos = levelPos;
      this.hyperCell = new HyperCell();
   }
}
