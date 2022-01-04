package de.schmiereck.col.model;

public class FieldEngineBNode {
   public final NextPart[] nextPartArr;

   public FieldEngineBNode(final int nextPartArrSize) {
      this.nextPartArr = new NextPart[nextPartArrSize];
   }
}
