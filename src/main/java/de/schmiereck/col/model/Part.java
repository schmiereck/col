package de.schmiereck.col.model;

/**
 * Part of a Particle.
 */
public class Part {
   public Event event;
   public Part parentPart;
   public int enginePos;
   public final HyperCell hyperCell;

   public Part(final Event event, final Part parentPart,
               final int enginePos) {
      this.event = event;
      this.parentPart = parentPart;
      this.enginePos = enginePos;
      this.hyperCell = new HyperCell();
   }

   public Part(final Event event, final Part parentPart,
               final int enginePos, final int cellPos, final int[] metaStatePosArr, final int[] probabilityArr) {
      this.event = event;
      this.parentPart = parentPart;
      this.enginePos = enginePos;
      this.hyperCell = new HyperCell(cellPos, metaStatePosArr, probabilityArr);
   }

   public Part(final Event event, final Part parentPart,
               final int enginePos, final int cellPos, final int metaStatePos) {
      this.event = event;
      this.parentPart = parentPart;
      this.enginePos = enginePos;
      this.hyperCell = new HyperCell(cellPos, metaStatePos);
   }
}
