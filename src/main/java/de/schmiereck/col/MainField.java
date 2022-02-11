package de.schmiereck.col;

import static de.schmiereck.col.services.UniverseUtils.calcCellPos;

import de.schmiereck.col.prob.model.ProbCell;

public class MainField {
   private static class FieldCell {
      public int inFieldArr[] = new int[2];
      public int outFieldArr[] = new int[2];
      public int inField;
      public int outField;
   }

   public static void main(String[] args) {
      //----------------------------------------------------------------------------------------------------------------
      final int universeSize = 60 + 1;
      final FieldCell[] fieldCellArr = new FieldCell[universeSize];

      for (int pos = 0; pos < fieldCellArr.length; pos++) {
         fieldCellArr[pos] = new FieldCell();
      }

      fieldCellArr[universeSize / 2].outField = 100;

      //----------------------------------------------------------------------------------------------------------------
      print(0, universeSize, fieldCellArr);

      for (int cnt = 1; cnt < 630; cnt++) {
         calc(universeSize, fieldCellArr);
         print(cnt, universeSize, fieldCellArr);
      }
      //----------------------------------------------------------------------------------------------------------------
   }

   private static void calc(final int universeSize, final FieldCell[] fieldCellArr) {
      clearIn(universeSize, fieldCellArr);
      calcOut2In(universeSize, fieldCellArr);
      copyIn2Out(universeSize, fieldCellArr);
   }

   private static void print(final int cnt, final int universeSize, final FieldCell[] fieldCellArr) {
      System.out.printf("%3d: ", cnt);
      for (int pos = 0; pos < fieldCellArr.length; pos++) {
         final FieldCell fieldCell = fieldCellArr[pos];

         System.out.printf("%3d ", fieldCell.outField);
      }
      System.out.printf("\n");
   }

   private static void clearIn(final int universeSize, final FieldCell[] fieldCellArr) {
      for (int pos = 0; pos < fieldCellArr.length; pos++) {
         final FieldCell fieldCell = fieldCellArr[pos];

         fieldCell.inField = 0;
      }
   }

   private static void calcOut2In(final int universeSize, final FieldCell[] fieldCellArr) {
      for (int pos = 0; pos < fieldCellArr.length; pos++) {
         final FieldCell lFieldCell = fieldCellArr[calcCellPos(universeSize, pos - 1)];
         final FieldCell rFieldCell = fieldCellArr[calcCellPos(universeSize, pos + 1)];
         final FieldCell fieldCell = fieldCellArr[pos];
         final int dl = fieldCell.outField - lFieldCell.outField;
         final int dr = fieldCell.outField - rFieldCell.outField;
         final int dlr = lFieldCell.outField - rFieldCell.outField;

         final int outField = fieldCell.outField;
         final int dField = outField / 3;
         final int lrField;
         if (dField > 0) {
            lrField = dField * 2;
            final int field = outField - lrField;
            fieldCell.inField += field;
            lFieldCell.inField += dField;
            rFieldCell.inField += dField;
         } else {
            fieldCell.inField += outField;
         }
      }
   }

   private static void copyIn2Out(final int universeSize, final FieldCell[] fieldCellArr) {
      for (int pos = 0; pos < fieldCellArr.length; pos++) {
         final FieldCell fieldCell = fieldCellArr[pos];

         fieldCell.outField = fieldCell.inField;
      }
   }
}
