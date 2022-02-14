package de.schmiereck.col;

import static de.schmiereck.col.services.UniverseUtils.calcCellPos;
import static de.schmiereck.col.utils.IntMathUtils.absIM;
import static de.schmiereck.col.utils.IntMathUtils.calcDenominator2;
import static de.schmiereck.col.utils.IntMathUtils.calcFieldIn;
import static de.schmiereck.col.utils.IntMathUtils.fieldIM;

public class MainField2 {
   public static final int Field_Range = 7;
   public static final int Max_Field = calcDenominator2(Field_Range);
   public static final int Min_Field = Max_Field/Field_Range;

   private static class FieldCell {
      public int inFieldArr[] = new int[2];
      public int outFieldArr[] = new int[2];
      public int inField;
      public int outField;
   }

   public static void main(String[] args) {
      //----------------------------------------------------------------------------------------------------------------
      final int universeSize = 40 + 1;
      final MainField2.FieldCell[] fieldCellArr = new MainField2.FieldCell[universeSize];

      for (int pos = 0; pos < fieldCellArr.length; pos++) {
         fieldCellArr[pos] = new MainField2.FieldCell();
      }

      //----------------------------------------------------------------------------------------------------------------
      print(0, universeSize, fieldCellArr);

      for (int cnt = 1; cnt < 19; cnt++) {

         fieldCellArr[universeSize / 2 + cnt / 2].outField = Max_Field;
         calc(universeSize, fieldCellArr);
         print(cnt, universeSize, fieldCellArr);
      }
      //----------------------------------------------------------------------------------------------------------------
   }

   private static void calc(final int universeSize, final MainField2.FieldCell[] fieldCellArr) {
      clearIn(universeSize, fieldCellArr);
      //calcOut2In(universeSize, fieldCellArr);
      //calcOut2In2(universeSize, fieldCellArr);
      //calcOut2In3(universeSize, fieldCellArr);
      calcOut2In4(universeSize, fieldCellArr);
      copyIn2Out(universeSize, fieldCellArr);
   }

   private static void print(final int cnt, final int universeSize, final MainField2.FieldCell[] fieldCellArr) {
      System.out.printf("%3d: ", cnt);
      for (int pos = 0; pos < fieldCellArr.length; pos++) {
         final MainField2.FieldCell fieldCell = fieldCellArr[pos];

         System.out.printf("%4d ", fieldCell.outField);
      }
      System.out.printf("\n");
   }

   private static void clearIn(final int universeSize, final MainField2.FieldCell[] fieldCellArr) {
      for (int pos = 0; pos < fieldCellArr.length; pos++) {
         final MainField2.FieldCell fieldCell = fieldCellArr[pos];

         fieldCell.inField = 0;
      }
   }

   private static void calcOut2In4(final int universeSize, final MainField2.FieldCell[] fieldCellArr) {
      for (int pos = 0; pos < fieldCellArr.length; pos++) {
         final MainField2.FieldCell lFieldCell = fieldCellArr[calcCellPos(universeSize, pos - 1)];
         final MainField2.FieldCell rFieldCell = fieldCellArr[calcCellPos(universeSize, pos + 1)];
         final MainField2.FieldCell fieldCell = fieldCellArr[pos];
         final int dl = lFieldCell.outField;
         final int dr = rFieldCell.outField;
         final int dlr = lFieldCell.outField - rFieldCell.outField;
         final int adlr = absIM(dlr);
         final int out = fieldCell.outField;

         final int in = calcFieldIn(dl, dr, out, Max_Field, Min_Field);

         fieldCell.inField = in;
      }
   }

   private static void calcOut2In(final int universeSize, final MainField2.FieldCell[] fieldCellArr) {
      for (int pos = 0; pos < fieldCellArr.length; pos++) {
         final MainField2.FieldCell lFieldCell = fieldCellArr[calcCellPos(universeSize, pos - 1)];
         final MainField2.FieldCell rFieldCell = fieldCellArr[calcCellPos(universeSize, pos + 1)];
         final MainField2.FieldCell fieldCell = fieldCellArr[pos];
         final int dl = lFieldCell.outField - fieldCell.outField;
         final int dr = rFieldCell.outField - fieldCell.outField;
         final int dlr = lFieldCell.outField - rFieldCell.outField;
         final int adlr = absIM(dlr);

         if (dr > 0) {
            //fieldCell.inField = fieldCell.outField + (dr - ((Max_Probability - dr) + 1));
            fieldCell.inField += fieldIM(Max_Field, dr);
         } //else
         {
            if (dl > 0) {
               //fieldCell.inField = fieldCell.outField + dl - 1;
               fieldCell.inField += fieldIM(Max_Field, dl);
            } //else
            if ((dr <= 0) && (dl <= 0)) {
               fieldCell.inField = fieldCell.outField;
            }
         }
      }
   }

   private static void calcOut2In3(final int universeSize, final MainField2.FieldCell[] fieldCellArr) {
      for (int pos = 0; pos < fieldCellArr.length; pos++) {
         final MainField2.FieldCell lFieldCell = fieldCellArr[calcCellPos(universeSize, pos - 1)];
         final MainField2.FieldCell rFieldCell = fieldCellArr[calcCellPos(universeSize, pos + 1)];
         final MainField2.FieldCell fieldCell = fieldCellArr[pos];
         final int dl = lFieldCell.outField;
         final int dr = rFieldCell.outField;
         final int dlr = lFieldCell.outField - rFieldCell.outField;
         final int adlr = absIM(dlr);

         if (dr > 0) {
            //fieldCell.inField = fieldCell.outField + (dr - ((Max_Probability - dr) + 1));
            fieldCell.inField += fieldIM(Max_Field, dr);
         } //else
         {
            if (dl > 0) {
               //fieldCell.inField = fieldCell.outField + dl - 1;
               fieldCell.inField += fieldIM(Max_Field, dl);
            } //else
            if ((dr <= 0) && (dl <= 0))
            {
               fieldCell.inField = fieldCell.outField;
            }
         }
      }
   }

   private static void calcOut2In2(final int universeSize, final MainField2.FieldCell[] fieldCellArr) {
      for (int pos = 0; pos < fieldCellArr.length; pos++) {
         final MainField2.FieldCell lFieldCell = fieldCellArr[calcCellPos(universeSize, pos - 1)];
         final MainField2.FieldCell rFieldCell = fieldCellArr[calcCellPos(universeSize, pos + 1)];
         final MainField2.FieldCell fieldCell = fieldCellArr[pos];
         final int dl = lFieldCell.outField - fieldCell.outField;
         final int dr = rFieldCell.outField - fieldCell.outField;
         final int dlr = lFieldCell.outField - rFieldCell.outField;
         final int adlr = absIM(dlr);

         if (dlr > 0) {
            fieldCell.inField += fieldIM(Max_Field, dlr);
         } //else
         {
            if (dlr < 0) {
               fieldCell.inField += fieldIM(Max_Field, -dlr);
            } //else
            if (dlr == 0) {
               //fieldCell.inField = fieldCell.outField;
            }
         }
      }
   }

   private static void copyIn2Out(final int universeSize, final MainField2.FieldCell[] fieldCellArr) {
      for (int pos = 0; pos < fieldCellArr.length; pos++) {
         final MainField2.FieldCell fieldCell = fieldCellArr[pos];

         fieldCell.outField = fieldCell.inField;
      }
   }
}
