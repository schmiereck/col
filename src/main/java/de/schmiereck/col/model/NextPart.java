package de.schmiereck.col.model;

public class NextPart {
   public final int nextPartLevelPos;
   public final int nextPartMetaStatePos;
   public final int nextPartOffsetCellPos;

   public final int newPartLevelPos;
   public final int newPartMetaStatePos;
   public final int newPartOffsetCellPos;

   public NextPart(final int nextPartLevelPos, final int nextPartMetaStatePos, final int nextPartOffsetCellPos,
                   final int newPartLevelPos, final int newPartMetaStatePos, final int newPartOffsetCellPos) {
      this.nextPartLevelPos = nextPartLevelPos;
      this.nextPartMetaStatePos = nextPartMetaStatePos;
      this.nextPartOffsetCellPos = nextPartOffsetCellPos;

      this.newPartLevelPos = newPartLevelPos;
      this.newPartMetaStatePos = newPartMetaStatePos;
      this.newPartOffsetCellPos = newPartOffsetCellPos;
   }

   public NextPart(final int newPartLevelPos, final int newPartMetaStatePos, final int newPartOffsetCellPos) {
      this.nextPartLevelPos = -1;
      this.nextPartMetaStatePos = -1;
      this.nextPartOffsetCellPos = 0;

      this.newPartLevelPos = newPartLevelPos;
      this.newPartMetaStatePos = newPartMetaStatePos;
      this.newPartOffsetCellPos = newPartOffsetCellPos;
   }
}
