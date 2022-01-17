package de.schmiereck.col.prob.services;

import static de.schmiereck.col.services.ProbabilityService.calcNext;
import static de.schmiereck.col.services.UniverseUtils.calcCellPos;

import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbUniverse;

public class ProbCellService {

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

   public static void calcOut(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         calcOut(probCell);
      }
   }

   public static void calcInProb(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse. universeSize, pos + 1)];

         calcInProb(probCell.inProb, probCell.outProb, lProbCell.outProb, rProbCell.outProb);
      }
   }

   public static void calcNextProb(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         calcNextProb(probCell);
      }
   }

   public static void printProbLine(final int cnt, final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      System.out.printf("%2d: ", cnt);
      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         printProb(probCell);
         System.out.printf("| ");
      }
      System.out.printf("\n");
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

      //    -> <-
      //    --> <-
      //    -> <--
      //    X ->
      switch (outProb.lastProbabilityPos) {
         case DirProbStay -> {
            switch (lOutProb.lastProbabilityPos) {
               case DirProbRight -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, lOutProb.probabilityArr);
               default -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
            }
         }
         case DirProbRight -> {
            switch (rOutProb.lastProbabilityPos) {
               //    -> X
               case DirProbStay -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, rOutProb.probabilityArr);
               //    -> ->
               //    --> ->
               //    -> -->
               case DirProbRight -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, rOutProb.probabilityArr);
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
