package de.schmiereck.col.model;

/**
 * Part of a Particle.
 */
public class Part {
   public Event event;
   public Part parentPart;
   public int levelPos;
   public final HyperCell hyperCell;

   public Part(final Event event, final Part parentPart,
               final int levelPos) {
      this.event = event;
      this.parentPart = parentPart;
      this.levelPos = levelPos;
      this.hyperCell = new HyperCell();
   }

   public Part(final Event event, final Part parentPart,
               final int levelPos, final int cellPos, final int metaStatePos) {
      this.event = event;
      this.parentPart = parentPart;
      this.levelPos = levelPos;
      this.hyperCell = new HyperCell(cellPos, metaStatePos);
   }
}
