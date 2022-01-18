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
      System.out.printf("%3d %3d %3d (%1d)",
              probCell.outProb.probabilityArr[DirProbLeft],
              probCell.outProb.probabilityArr[DirProbStay],
              probCell.outProb.probabilityArr[DirProbRight],
              probCell.outProb.lastProbabilityPos);
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
         //    b: a:?   b:<-  c:?
         case DirProbLeft -> {
             switch (lOutProb.lastProbabilityPos) {
               //    b: a:->  b:<-  c:?
               case DirProbRight -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    b: a:->  b:<-  c:<-
                     case DirProbLeft -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, rOutProb.probabilityArr);
                     //    b: a:->  b:<-  c:X
                     case DirProbStay -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, lOutProb.probabilityArr);
                     //    b: a:->  b:<-  c:->
                     case DirProbRight -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, lOutProb.probabilityArr);
                     //default -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:X   b:<-  c:?
               case DirProbStay -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:X   a:<-  b:<-
                     case DirProbLeft -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, lOutProb.probabilityArr);
                     //    b: c:X   a:<-  b:X
                     case DirProbStay -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, lOutProb.probabilityArr);
                     //    a: c:X   a:<-  b:->
                     case DirProbRight -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:<-  b:<-  c:?
               case DirProbLeft -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:<-  a:<-  b:<-
                     case DirProbLeft -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, lOutProb.probabilityArr);
                     //    b: c:<-  a:<-  b:X
                     case DirProbStay -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, lOutProb.probabilityArr);
                     //    a: c:<-  a:<-  b:->
                     case DirProbRight -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, lOutProb.probabilityArr);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //default -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
               default -> throw new IllegalStateException("Unexpected value: " + lOutProb.lastProbabilityPos);
            }

         }
         //    b: a:?   b:X   c:?
         case DirProbStay -> {
            switch (lOutProb.lastProbabilityPos) {
               //    b: a:->  b:X   c:?
               case DirProbRight -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    b: a:->  b:X   c:<-
                     case DirProbLeft -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, rOutProb.probabilityArr);
                     //    b: a:->  b:X   c:X
                     case DirProbStay -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, lOutProb.probabilityArr);
                     //    b: a:->  b:X   c:->
                     case DirProbRight -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, lOutProb.probabilityArr);
                     //default -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:X   b:X   c:?
               case DirProbStay -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:X   a:X   b:<-
                     case DirProbLeft -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, rOutProb.probabilityArr);
                     //    b: c:X   a:X   b:X
                     case DirProbStay -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
                     //    a: c:X   a:X   b:->
                     case DirProbRight -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:<-  b:X   c:?
               case DirProbLeft -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:<-  a:X   b:<-
                     case DirProbLeft -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, rOutProb.probabilityArr);
                     //    b: c:<-  a:X   b:X
                     case DirProbStay -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
                     //    a: c:<-  a:X   b:->
                     case DirProbRight -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //default -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
               default -> throw new IllegalStateException("Unexpected value: " + lOutProb.lastProbabilityPos);
            }
         }
         //    b: a:?   b:->  c:?
         case DirProbRight -> {
            switch (lOutProb.lastProbabilityPos) {
               //    b: a:->  b:->  c:?
               case DirProbRight -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    b: a:->  b:->  c:<-
                     case DirProbLeft -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, rOutProb.probabilityArr);
                     //    b: a:->  b:->  c:X
                     case DirProbStay -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, rOutProb.probabilityArr);
                     //    b: a:->  b:->  c:->
                     case DirProbRight -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, lOutProb.probabilityArr);
                     //default -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:X   b:X   c:?
               case DirProbStay -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:X   a:->  b:<-
                     case DirProbLeft -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, rOutProb.probabilityArr);
                     //    b: c:X   a:->  b:X
                     case DirProbStay -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, rOutProb.probabilityArr);
                     //    a: c:X   a:->  b:->
                     case DirProbRight -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:<-  b:X   c:?
               case DirProbLeft -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:<-  a:X   b:<-
                     case DirProbLeft -> addArrDiff(inProb.probabilityArr, outProb.probabilityArr, rOutProb.probabilityArr);
                     //    b: c:<-  a:X   b:X
                     case DirProbStay -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
                     //    a: c:<-  a:X   b:->
                     case DirProbRight -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //default -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
               default -> throw new IllegalStateException("Unexpected value: " + lOutProb.lastProbabilityPos);
            }
         }
         //default -> copyArr(inProb.probabilityArr, outProb.probabilityArr);
         default -> throw new IllegalStateException("Unexpected value: " + outProb.lastProbabilityPos);
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
