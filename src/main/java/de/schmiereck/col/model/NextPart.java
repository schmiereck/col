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

   public NextPart(final int nextPartLevelPos, final int nextPartMetaStatePos, final int nextPartOffsetCellPos) {
      this.nextPartLevelPos = nextPartLevelPos;
      this.nextPartMetaStatePos = nextPartMetaStatePos;
      this.nextPartOffsetCellPos = nextPartOffsetCellPos;

      this.newPartLevelPos = -1;
      this.newPartMetaStatePos = -1;
      this.newPartOffsetCellPos = 0;
   }
}
