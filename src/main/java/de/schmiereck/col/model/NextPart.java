package de.schmiereck.col.model;

public class NextPart {
   public enum Command {
      CmdNext,
      CmdNextNew,
      CmdCombineToParent
   }

   public static int[][] LR_REFLECTION_MATRIX =
           {
                   { 0, 0, 0 },
                   { 0, 0, 1 },
                   { 0, 1, 0 }
           };

   public final Command command;
   public final NextPartArgument[] nextPartArgumentArr;

   public static class NextPartArgument {
      public final int nextPartEnginePos;
      public final int nextPartMetaStatePos;
      /**
       *  ( sm11 lm12 rm13 )
       *  ( sm21 lm22 rm23 )
       *  ( sm31 lm32 rm33 )
       * s = s*sm11 + l*lm12 + r*rm13
       * r = s*sm21 + l*lm22 + r*rm23
       * l = s*sm31 + l*lm32 + r*rm33
       */
      public final int[][] probabilityMatrix;
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
         this.probabilityMatrix = null;
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
         this.probabilityMatrix = null;
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
         this.probabilityMatrix = null;
         this.nextPartOffsetCellPos = nextPartOffsetCellPos;

         this.newPartEnginePos = -1;
         this.newPartMetaStatePos = -1;
         this.newPartMetaStatePosArr = null;//new int[] { -1, -1, -1 };
         this.probabilityArr = null;//new int[] { 0, 0, 0 };
         this.newPartOffsetCellPos = 0;
      }

      public NextPartArgument(final int nextPartEnginePos, final int[][] probabilityMatrix, final int nextPartOffsetCellPos) {
         this.nextPartEnginePos = nextPartEnginePos;
         this.nextPartMetaStatePos = -1;
         this.probabilityMatrix = probabilityMatrix;
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

   public NextPart(final int nextPartEnginePos, final int[][] probabilityMatrix, final int nextPartOffsetCellPos) {
      this.command = Command.CmdNext;
      this.nextPartArgumentArr = new NextPartArgument[1];

      final NextPartArgument nextPartArgument =
              new NextPartArgument(nextPartEnginePos, probabilityMatrix, nextPartOffsetCellPos);

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
