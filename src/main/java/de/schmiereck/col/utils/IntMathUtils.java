package de.schmiereck.col.utils;

public class IntMathUtils {

   public static int minIM(final int a, final int b) {
      final int ret;
      if (a <= b) {
         ret = a;
      } else {
         ret = b;
      }
      return ret;
   }

   public static int absIM(final int a) {
      final int ret;
      if (a < 0) {
         ret = -a;
      } else {
         ret = a;
      }
      return ret;
   }

   public static int calcDenominator(final int value) {
      int denom = 1;
      for (int d = 1; d < value; d++) {
         denom *= d;
      }
      return denom;
   }

   public static int calcDenominator2(final int value) {
      int denom = 1;
      for (int d = 1; d <= value; d++) {
         denom *= d;
      }
      return denom;
   }

   public static long calcDenominatorL(final long value) {
      long denom = 1;
      for (long d = 1; d < value; d++) {
         denom *= d;
      }
      return denom;
   }

   public static int calcNorm(final int value, final int exer) {
      int denom = 1;
      for (int d = 1; d <= value; d++) {
         if (d != exer)
            denom *= d;
      }
      return denom;
   }

   public static int calcFieldIn(final int outL, final int outR, final int out, final int v, final int fMin) {
      final int d;
      if (outL > outR) {
         d = outL;
      } else {
         d = outR;
      }

      final int in;
      if ((d > 0) && (d >= out)) {
         in = fieldIM(v, d, fMin);
      } else {
         in = out;
      }
      return in;
   }

   public static int fieldIM(final int v, final int f) {
      final int r = v / f;
      final int d = (f) / (r + 1);
      final int fn = ((f) - d);
      return fn;
   }

   public static int fieldIM(final int v, final int f, final int fMin) {
      final int r = v / f;
      final int d = (f) / (r + 1);
      final int fn = ((f) - d);
      if (f < fMin) {
         return 0;
      }
      return fn;
   }

   public static int fieldIMReel(final int v, final int f) {
      final int r = v / f;
      final int d = (v*f) / (r + 1);
      final int fn = ((v*f) - d)/v;
      return fn;
   }
}
