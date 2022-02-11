package de.schmiereck.col;

/**
 * numerator      Zähler
 * ----------- = --------
 * denominator    Nenner
 */
public class MainMath {

   public static void main(String[] args) {
       //----------------------------------------------------------------------------------------------------------------
      //final int value = 4;
      final int value = 7;
      final int denominator = calcDenominator(value);
      final int[] a = new int[value + 1];
      final int[] dArr = new int[value + 1];
      final int[] d2Arr = new int[value + 1];
      final int[] d3Arr = new int[value + 1];

      System.out.printf("value:%2d, basis:%2d\n", value, denominator);

      for (int r = 1; r <= value; r++) {
         final int normNum = calcNorm(value, r);
         a[r] = normNum;
      }
      for (int r = 1; r < value; r++) {
         final int rn = value - r;
         if (r < (value)) {
            dArr[r] = a[r] - a[r + 1];
            final int v = rn;
            d2Arr[r] = dArr[r] * v;
            d3Arr[r] = dArr[r] / v;
            if ((dArr[r] - (d3Arr[r] * v)) != 0) throw new RuntimeException("Rest: " + dArr[r] + " / " + v);
         }
      }

      for (int r = 1; r <= value; r++) {
         final int rn = value - r;
         final int normNum = a[r];
         final int normDenom = denominator;
         System.out.printf("%2d / %2d = %4d / %3d\n",
                 value, r,
                 normNum, normDenom);
         if (r < (value)) {
            final int dx = rn;//value;
            final int x = d3Arr[r] / dx;
            System.out.printf("                              ");
            System.out.printf("%5d = ",
                    dArr[r]);
            System.out.printf(" %2d * %2d\n",
                    dx, d3Arr[r]);
         }
      }
      //----------------------------------------------------------------------------------------------------------------
      System.out.printf("\n");

      final int v = calcDenominator2(value);

      int f = v;

      while (true) {
         final int r = v / f;
         final int d = f / (r + 1);
         final int fn = f - d;

         System.out.printf("fn:%d = (f:%d, d:%d, r:%d)\n", fn, f, d, r);

         if (d == 0) break;

         f = fn;
      }
      //----------------------------------------------------------------------------------------------------------------
   }

   private static int calcDenominator(final int value) {
      int denom = 1;
      for (int d = 1; d < value; d++) {
         denom *= d;
      }
      return denom;
   }

   private static int calcDenominator2(final int value) {
      int denom = 1;
      for (int d = 1; d <= value; d++) {
         denom *= d;
      }
      return denom;
   }

   private static long calcDenominatorL(final long value) {
      long denom = 1;
      for (long d = 1; d < value; d++) {
         denom *= d;
      }
      return denom;
   }

   private static int calcNorm(final int value, final int exer) {
      int denom = 1;
      for (int d = 1; d <= value; d++) {
         if (d != exer)
            denom *= d;
      }
      return denom;
   }
}
