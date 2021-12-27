package de.schmiereck.col.model;

public class NextPart {
   public enum Command {
      CmdNext,
      CmdNextNew,
      CmdCombineToParent
   }

   public final Command command;
   public final NextPartArgument[] nextPartArgumentArr;

   public static class NextPartArgument {
      public final int nextPartEnginePos;
      public final int nextPartMetaStatePos;
      public final int nextPartOffsetCellPos;

      public final int newPartEnginePos;
      public final int newPartMetaStatePos;
      public final int[] newPartMetaStatePosArr;
      public final int[] probabilityArr;
      public final int newPartOffsetCellPos;

      public NextPartArgument(final int nextPartEnginePos, final int nextPartMetaStatePos, final int nextPartOffsetCellPos,
                              final int newPartEnginePos, final int newPartMetaStatePos, final int newPartOffsetCellPos) {
         this.nextPartEnginePos = nextPartEnginePos;
         this.nextPartMetaStatePos = nextPartMetaStatePos;
         this.nextPartOffsetCellPos = nextPartOffsetCellPos;

         this.newPartEnginePos = newPartEnginePos;
         this.newPartMetaStatePos = newPartMetaStatePos;
         this.newPartMetaStatePosArr = null;
         this.probabilityArr = null;
         this.newPartOffsetCellPos = newPartOffsetCellPos;
      }

      public NextPartArgument(final int nextPartEnginePos, final int nextPartMetaStatePos, final int nextPartOffsetCellPos,
                              final int newPartEnginePos, final int[] newPartMetaStatePosArr, final int[] probabilityArr, final int newPartOffsetCellPos) {
         this.nextPartEnginePos = nextPartEnginePos;
         this.nextPartMetaStatePos = nextPartMetaStatePos;
         this.nextPartOffsetCellPos = nextPartOffsetCellPos;

         this.newPartEnginePos = newPartEnginePos;
         this.newPartMetaStatePos = -1;
         this.newPartMetaStatePosArr = newPartMetaStatePosArr;
         this.probabilityArr = probabilityArr;
         this.newPartOffsetCellPos = newPartOffsetCellPos;
      }

      public NextPartArgument(final int nextPartEnginePos, final int nextPartMetaStatePos, final int nextPartOffsetCellPos) {
         this.nextPartEnginePos = nextPartEnginePos;
         this.nextPartMetaStatePos = nextPartMetaStatePos;
         this.nextPartOffsetCellPos = nextPartOffsetCellPos;

         this.newPartEnginePos = -1;
         this.newPartMetaStatePos = -1;
         this.newPartMetaStatePosArr = null;//new int[] { -1, -1, -1 };
         this.probabilityArr = null;//new int[] { 0, 0, 0 };
         this.newPartOffsetCellPos = 0;
      }
   }

   public NextPart(final Command command, final NextPartArgument... nextPartArgumentArr) {
      this.command = command;

      this.nextPartArgumentArr = nextPartArgumentArr;
   }

   public NextPart(final int nextPartEnginePos, final int nextPartMetaStatePos, final int nextPartOffsetCellPos,
                   final int newPartEnginePos, final int newPartMetaStatePos, final int newPartOffsetCellPos) {
      this.command = Command.CmdNextNew;
      this.nextPartArgumentArr = new NextPartArgument[1];

      final NextPartArgument nextPartArgument =
              new NextPartArgument(nextPartEnginePos, nextPartMetaStatePos, nextPartOffsetCellPos,
                                   newPartEnginePos, newPartMetaStatePos, newPartOffsetCellPos);

      this.nextPartArgumentArr[0] = nextPartArgument;
   }

   public NextPart(final int nextPartEnginePos, final int nextPartMetaStatePos, final int nextPartOffsetCellPos,
                   final int newPartEnginePos, final int[] newPartMetaStatePosArr, final int[] probabilityArr, final int newPartOffsetCellPos) {
      this.command = Command.CmdNextNew;
      this.nextPartArgumentArr = new NextPartArgument[1];

      final NextPartArgument nextPartArgument =
              new NextPartArgument(nextPartEnginePos, nextPartMetaStatePos, nextPartOffsetCellPos,
                                   newPartEnginePos, newPartMetaStatePosArr, probabilityArr, newPartOffsetCellPos);

      this.nextPartArgumentArr[0] = nextPartArgument;
   }

   public NextPart(final int nextPartEnginePos, final int nextPartMetaStatePos, final int nextPartOffsetCellPos) {
      this.command = Command.CmdNext;
      this.nextPartArgumentArr = new NextPartArgument[1];

      final NextPartArgument nextPartArgument =
              new NextPartArgument(nextPartEnginePos, nextPartMetaStatePos, nextPartOffsetCellPos);

      this.nextPartArgumentArr[0] = nextPartArgument;
   }

   public NextPart(final Command command, final int nextPartEnginePos, final int nextPartMetaStatePos, final int nextPartOffsetCellPos) {
      this.command = command;
      this.nextPartArgumentArr = new NextPartArgument[1];

      final NextPartArgument nextPartArgument =
              new NextPartArgument(nextPartEnginePos, nextPartMetaStatePos, nextPartOffsetCellPos);

      this.nextPartArgumentArr[0] = nextPartArgument;
   }
}
