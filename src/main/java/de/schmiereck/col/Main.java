package de.schmiereck.col;

public class Main {

   static class Cell {
      int mValue = 0;
      int rValue = 0;
      int lValue = 0;
      boolean block = false;
   }

   public static int arrSize = 14;

   public static void main(String[] args) {
      final Cell[][] cellArr = new Cell[2][arrSize];

      init(cellArr);

      int genPos = 0;
      int nextGenPos = 1;

      cellArr[genPos][arrSize / 2].mValue = 16;//32;
      cellArr[genPos][5].block = true;

      for (int cnt = 0; cnt < 12*8; cnt++) {
         printCells(cellArr, genPos, cnt);

         run(cellArr, genPos, nextGenPos);

         final int tempGenPos = genPos;
         genPos = nextGenPos;
         nextGenPos = tempGenPos;
      }
   }

   private static void init(final Cell[][] cellArr) {
      for (int genPos = 0; genPos < 2; genPos++) {
         for (int pos = 0; pos < arrSize; pos++) {
            cellArr[genPos][pos] = new Cell();
         }
      }
   }

   private static void printCells(final Cell[][] cellArr, final int genPos, final int cnt) {
      System.out.printf("%4d: ", cnt);
      for (int pos = 0; pos < arrSize; pos++) {
         final Cell cell = cellArr[genPos][pos];
         if (cell.block) {
            System.out.printf("(**/**/**) ");
         } else {
            System.out.printf("(%2d/%2d/%2d) ", cell.lValue, cell.mValue, cell.rValue);
         }
      }
      System.out.print(" | ");
      for (int pos = 0; pos < arrSize; pos++) {
         final Cell cell = cellArr[genPos][pos];
         if (cell.block) {
         System.out.printf("**  ");
         } else {
            System.out.printf("%2d  ", cell.lValue + cell.mValue + cell.rValue);
         }
      }
      System.out.println();
   }

   private static void run(final Cell[][] cellArr, final int genPos, final int nextGenPos) {
      for (int pos = 0; pos < arrSize; pos++) {
         cellArr[nextGenPos][pos].mValue = 0;
         cellArr[nextGenPos][pos].rValue = 0;
         cellArr[nextGenPos][pos].lValue = 0;
         cellArr[nextGenPos][pos].block = cellArr[genPos][pos].block;
      }
      for (int pos = 0; pos < arrSize; pos++) {
         final Cell cell = cellArr[genPos][pos];
         {
            final int sourceMValue = cell.mValue;
            if (sourceMValue > 1) {
               runRLValue(cellArr, nextGenPos, pos, sourceMValue);
            } else {
               cellArr[nextGenPos][pos].mValue += sourceMValue;
            }
         }
         {
            final int sourceRValue = cell.rValue;
            final int destPos = decPos(pos);
            runBackValue(cellArr, nextGenPos, sourceRValue, pos, destPos);
         }
         {
            final int sourceLValue = cell.lValue;
            final int destPos = incPos(pos);
            runBackValue(cellArr, nextGenPos, sourceLValue, pos, destPos);
         }
      }
   }

   private static void runBackValue(final Cell[][] cellArr, final int nextGenPos, final int sourceRValue, final int sourcePos, final int destPos) {
      if (sourceRValue > 1) {
         final int halfValue = sourceRValue / 2;
         final int diffValue = sourceRValue - (halfValue);
         cellArr[nextGenPos][destPos].mValue += halfValue;

         runRLValue(cellArr, nextGenPos, sourcePos, diffValue);
      } else {
         cellArr[nextGenPos][destPos].mValue += sourceRValue;
      }
   }

   private static void runRLValue(final Cell[][] cellArr, final int nextGenPos, final int pos, final int sourceMValue) {
      final int halfValue = sourceMValue / 2;
      final int diffValue = sourceMValue - (halfValue * 2);
      cellArr[nextGenPos][decPos(pos)].lValue += halfValue;
      cellArr[nextGenPos][incPos(pos)].rValue += halfValue;
      cellArr[nextGenPos][pos].mValue += diffValue;
   }

   private static int decPos(final int pos) {
      if (pos > 0) {
         return pos - 1;
      } else {
         return arrSize - 1;
      }
   }

   private static int incPos(final int pos) {
      if (pos < (arrSize - 1)) {
         return pos + 1;
      } else {
         return 0;
      }
   }

}
