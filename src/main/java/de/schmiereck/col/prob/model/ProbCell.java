package de.schmiereck.col.prob.model;

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
   public ProbField eProbField = new ProbField();
   public ProbField pProbField = new ProbField();
   //public Probability inProb;
   //public Probability outProb;
   public Part eInPart;
   public Part eOutPart;
   public Part pPart;
}
