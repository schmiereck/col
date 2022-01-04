package de.schmiereck.col.model;

public class FieldEngineANode {
   public final FieldEngineBNode[] bNodeArr;

   public FieldEngineANode(final int bNodeArrSize) {
      this.bNodeArr = new FieldEngineBNode[bNodeArrSize];
   }
}
