package de.schmiereck.larray;

public class LarrayInt {
   final int[] arr;

   public LarrayInt(final int size) {
      this.arr = new int[size];
   }

   public int get(final int pos) {
      return this.arr[pos];
   }

   public void set(final int pos, final int value) {
      this.arr[pos] = value;
   }
}
