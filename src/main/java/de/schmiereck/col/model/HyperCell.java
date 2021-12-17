package de.schmiereck.col.model;

import static de.schmiereck.col.model.State.NULL_pos;

public class HyperCell {
   public int cellPos;
   public int metaStatePos;

   public HyperCell() {
      this.cellPos = -1;
      this.metaStatePos = NULL_pos;
   }

   public HyperCell(final int cellPos, final int metaStatePos) {
      this.cellPos = cellPos;
      this.metaStatePos = metaStatePos;
   }
}
