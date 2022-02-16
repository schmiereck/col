package de.schmiereck.col.prob.model;

import static de.schmiereck.col.prob.model.ProbField.FieldSize;

import de.schmiereck.col.model.Probability;

public class ProbCell {
   //public static final int EFieldLeft = 0;
   //public static final int EFieldRight = 1;
   //public static final int EFieldSize = 2;

   //public Probability eProb;
   //public int inEField;
   //public int outEField;
   //public int[] inEFieldArr = new int[EFieldSize];
   //public int[] outEFieldArr = new int[EFieldSize];
   public ProbField[] eProbFieldArr = new ProbField[FieldSize];
   public ProbField[] pProbFieldArr = new ProbField[FieldSize];
   //public Probability inProb;
   //public Probability outProb;
   public Part eInPart;
   public Part eOutPart;
   public Part pInPart;
   public Part pOutPart;

   public ProbCell() {
      for (int pos = 0; pos < FieldSize; pos++) {
         this.eProbFieldArr[pos] = new ProbField();
         this.pProbFieldArr[pos] = new ProbField();
      }
   }
}
