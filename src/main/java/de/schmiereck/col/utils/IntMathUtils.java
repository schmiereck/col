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

   public static int fieldIM(final int v, final int f) {
      final int r = v / f;
      final int d = f / (r + 1);
      final int fn = f - d;
      return fn;
   }
}
