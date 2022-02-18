package de.schmiereck.col.prob.model;

import static de.schmiereck.col.prob.model.ProbField.FieldSize;

import de.schmiereck.col.model.Probability;

public class ProbCell {
   public static final int InState = 0;
   public static final int OutState = 1;
   static final int Size_State = 2;

   public ProbField[] eProbFieldArr = new ProbField[FieldSize];
   //public ProbField[] pProbFieldArr = new ProbField[FieldSize];

   public Part eInPart;
   public Part eOutPart;
   public Part pInPart;
   public Part pOutPart;

   public ProbCellState[] probCellState = new ProbCellState[Size_State];

   public ProbCell() {
      this.probCellState[InState] = new ProbCellState();
      this.probCellState[OutState] = new ProbCellState();
      for (int pos = 0; pos < FieldSize; pos++) {
         this.eProbFieldArr[pos] = new ProbField();
         this.probCellState[InState].pProbFieldArr[pos] = new ProbField();
         this.probCellState[OutState].pProbFieldArr[pos] = new ProbField();
      }
   }
}
