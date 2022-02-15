package de.schmiereck.col.prob.model;

public class ProbField {
   public static final int FieldLeft = 0;
   public static final int FieldRight = 1;
   public static final int FieldSize = 2;

   //public Probability eProb;
   //public int inField;
   //public int outField;
   public int[] inFieldArr = new int[FieldSize];
   public int[] outFieldArr = new int[FieldSize];
}
