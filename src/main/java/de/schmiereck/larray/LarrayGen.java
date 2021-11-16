package de.schmiereck.larray;

import java.lang.reflect.Array;
import java.util.ArrayList ;

public class LarrayGen<T> {
   private T[] arr;

   public LarrayGen(final int size) {
      this.arr = (T[]) new Object[size];
   }

   private static <T> T[] createArr(Class<T> clazz, final int size) {
      return (T[]) Array.newInstance(clazz, size);
   }

   public T get(final int pos) {
      return this.arr[pos];
   }

   public void set(final int pos, final T value) {
      this.arr[pos] = value;
   }
}
