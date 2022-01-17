package de.schmiereck.col;

import static de.schmiereck.col.services.ProbabilityService.calcInit;
import static de.schmiereck.col.services.ProbabilityService.calcNext;
import static de.schmiereck.col.services.UniverseUtils.calcCellPos;

import de.schmiereck.col.model.Probability;

public class MainProb {
   public static class ProbCell {
      public Probability inProb;
      public Probability outProb;
   }

   public static final int universeSize = 1*2*3;
   public static final int Max_Probability = 100;
   public static final int DirProbStay = 0;
   public static final int DirProbLeft = 1;
   public static final int DirProbRight = 2;
   public static final int SpinProbUp = 3;
   public static final int SpinProbStay = 4;
   public static final int SpinProbDown = 5;
   public static final int DirProbSize = 3;
   public static final int SpinProbSize = 3;
   public static final int ProbSize = 6;

   /**
    d=
    a-b
    a		b
    su	30	-30	60
    sd	70	30	40
    r	20	10	10
    s	80	-10	90
    l	0	0	0

    a-d		b+d
    a		b
    su	60		30
    sd	40		70
    r	10		20
    s	90		80
    l	0		0

    */
   public static void main(String[] args) {
      //----------------------------------------------------------------------------------------------------------------
      final ProbCell[] probCellArr = new ProbCell[universeSize];

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = new ProbCell();

         probCell.inProb = new Probability(Max_Probability, ProbSize);
         probCell.outProb = new Probability(Max_Probability, ProbSize);

         probCell.outProb.probabilityArr[DirProbStay]    = 100;

         probCellArr[pos] = probCell;
      }
      {
         final ProbCell probCell = probCellArr[0];

         probCell.outProb.probabilityArr[DirProbStay]    = 70;
         probCell.outProb.probabilityArr[DirProbLeft]    =  0;
         probCell.outProb.probabilityArr[DirProbRight]   = 30;
      }
      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];

         calcInit(probCell.outProb);
      }
      //----------------------------------------------------------------------------------------------------------------
      for (int cnt = 0; cnt < 12; cnt++) {
         System.out.printf("%2d: ", cnt);
         for (int pos = 0; pos < probCellArr.length; pos++) {
            final ProbCell probCell = probCellArr[pos];
            printProb(probCell);
            System.out.printf("| ");
         }
         System.out.printf("\n");
         for (int pos = 0; pos < probCellArr.length; pos++) {
            final ProbCell probCell = probCellArr[pos];
            calcNextProb(probCell);
         }
         for (int pos = 0; pos < probCellArr.length; pos++) {
            final ProbCell probCell = probCellArr[pos];
            final ProbCell lProbCell = probCellArr[calcCellPos(universeSize, pos - 1)];
            final ProbCell rProbCell = probCellArr[calcCellPos(universeSize, pos + 1)];

            calcInProb(probCell.inProb, probCell.outProb, lProbCell.outProb, rProbCell.outProb);
         }
         for (int pos = 0; pos < probCellArr.length; pos++) {
            final ProbCell probCell = probCellArr[pos];
            calcOut(probCell);
         }
      }
      //----------------------------------------------------------------------------------------------------------------
   }

   private static void printProb(final ProbCell probCell) {
      System.out.printf("%3d %3d %3d ",
              probCell.outProb.probabilityArr[DirProbLeft],
              probCell.outProb.probabilityArr[DirProbStay],
              probCell.outProb.probabilityArr[DirProbRight]);
   }

   private static void calcNextProb(final ProbCell probCell) {
      calcNext(probCell.outProb);
   }

   private static void calcOut(final ProbCell probCell) {
      copyArr(probCell.outProb.probabilityArr, probCell.inProb.probabilityArr);
      clearArr(probCell.inProb.probabilityArr);
   }

   /**
    * a = b
    */
   private static void copyArr(final int[] aArr, final int[] bArr) {
      for (int pos = 0; pos < aArr.length; pos++) {
         aArr[pos] =  bArr[pos];
      }
   }

   private static void calcInProb(final Probability inProb, final Probability outProb, final Probability lOutProb, final Probability rOutProb) {
      // calcOperation()

      switch (outProb.lastProbabilityPos) {
         case DirProbStay -> {
            switch (lOutProb.lastProbabilityPos) {
               case DirProbRight -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, lOutProb.probabilityArr);
               default -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
            }
         }
         case DirProbRight -> {
            switch (rOutProb.lastProbabilityPos) {
               case DirProbStay -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, rOutProb.probabilityArr);
               default -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
            }
         }
         default -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
      }
      //switch (rOutProb.lastProbabilityPos) {
      //   case DirProbLeft -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, rOutProb.probabilityArr);
      //}
   }

   private static void clearArr(final int[] inArr) {
      for (int pos = 0; pos < inArr.length; pos++) {
         inArr[pos] =  0;
      }
   }

   /**
    * in = a - b
    */
   private static void addArrDiff(final int[] inArr, final int[] aArr, final int[] bArr) {
      for (int pos = 0; pos < inArr.length; pos++) {
         inArr[pos] +=  aArr[pos] - (aArr[pos] - bArr[pos]);
      }
   }
}
