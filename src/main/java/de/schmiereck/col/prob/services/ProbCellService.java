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
   public static final int DirProbSize = 3;
   public static final int SpinProbUp = 3;
   public static final int SpinProbStay = 4;
   public static final int SpinProbDown = 5;
   public static final int SpinProbSize = 3;
   public static final int ProbSize = 6;

   public static final int EProbLeft = 0;
   public static final int EProbRight = 1;
   public static final int EProbSize = 2;

   public static void calcNextProb(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         calcNextProb(probCell);
      }
   }

   public static void calcInProb(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse. universeSize, pos + 1)];

         calcInProb(probCell, lProbCell, rProbCell);
      }
   }

   public static void calcOut(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         calcOut(probCell);
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

      System.out.printf("    ", cnt);
      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         System.out.printf("%3d            ", probCell.outEField);
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
      probCell.outEField = probCell.inEField;

      clearArr(probCell.inProb.probabilityArr);
      probCell.inEField = 0;
   }

   private static void calcInProb(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      // calcOperation()

      final Probability inProb = probCell.inProb;
      final Probability outProb = probCell.outProb;
      final Probability lOutProb = lProbCell.outProb;
      final Probability rOutProb = rProbCell.outProb;

      //    -> <-
      //    --> <-
      //    -> <--
      //    X ->
      switch (outProb.lastProbabilityPos) {
         //    b: a:?   b:<-  c:?   -----------------------
         case DirProbLeft -> {
             switch (lOutProb.lastProbabilityPos) {
               //    b: a:->  b:<-  c:?
               case DirProbRight -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    b: a:->  b:<-  c:<-
                     case DirProbLeft ->  addDiff(probCell, rProbCell);
                     //    b: a:->  b:<-  c:X
                     case DirProbStay ->  addDiff(probCell, lProbCell);
                     //    b: a:->  b:<-  c:->
                     case DirProbRight ->  addDiff(probCell, lProbCell);
                     //default -> copy(probCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:X   b:<-  c:?
               case DirProbStay -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:X   a:<-  b:<-
                     case DirProbLeft ->  addDiff(probCell, lProbCell);
                     //    b: c:X   a:<-  b:X
                     case DirProbStay ->  addDiff(probCell, lProbCell);
                     //    a: c:X   a:<-  b:->
                     case DirProbRight -> copy(probCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:<-  b:<-  c:?
               case DirProbLeft -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:<-  a:<-  b:<-
                     case DirProbLeft ->  addDiff(probCell, lProbCell);
                     //    b: c:<-  a:<-  b:X
                     case DirProbStay ->  addDiff(probCell, lProbCell);
                     //    a: c:<-  a:<-  b:->
                     case DirProbRight ->  addDiff(probCell, lProbCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //default -> copy(probCell);
               default -> throw new IllegalStateException("Unexpected value: " + lOutProb.lastProbabilityPos);
            }

         }
         //    b: a:?   b:X   c:?   -----------------------
         case DirProbStay -> {
            switch (lOutProb.lastProbabilityPos) {
               //    b: a:->  b:X   c:?
               case DirProbRight -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    b: a:->  b:X   c:<-
                     case DirProbLeft ->  addDiff(probCell, rProbCell);
                     //    b: a:->  b:X   c:X
                     case DirProbStay ->  addDiff(probCell, lProbCell);
                     //    b: a:->  b:X   c:->
                     case DirProbRight ->  addDiff(probCell, lProbCell);
                     //default -> copy(probCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:X   b:X   c:?
               case DirProbStay -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:X   a:X   b:<-
                     case DirProbLeft ->  addDiff(probCell, rProbCell);
                     //    b: c:X   a:X   b:X
                     case DirProbStay -> copy(probCell);
                     //    a: c:X   a:X   b:->
                     case DirProbRight -> copy(probCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:<-  b:X   c:?
               case DirProbLeft -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:<-  a:X   b:<-
                     case DirProbLeft ->  addDiff(probCell, rProbCell);
                     //    b: c:<-  a:X   b:X
                     case DirProbStay -> copy(probCell);
                     //    a: c:<-  a:X   b:->
                     case DirProbRight -> copy(probCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //default -> copy(probCell);
               default -> throw new IllegalStateException("Unexpected value: " + lOutProb.lastProbabilityPos);
            }
         }
         //    b: a:?   b:->  c:?   -----------------------
         case DirProbRight -> {
            switch (lOutProb.lastProbabilityPos) {
               //    b: a:->  b:->  c:?
               case DirProbRight -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    b: a:->  b:->  c:<-
                     case DirProbLeft ->  addDiff(probCell, rProbCell);
                     //    b: a:->  b:->  c:X
                     case DirProbStay ->  addDiff(probCell, rProbCell);
                     //    a: a:->  b:->  c:->
                     case DirProbRight ->  addDiff(probCell, lProbCell);
                     //default -> copy(probCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:X   b:X   c:?
               case DirProbStay -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:X   a:->  b:<-
                     case DirProbLeft ->  addDiff(probCell, rProbCell);
                     //    a: c:X   a:->  b:X
                     case DirProbStay ->  addDiff(probCell, rProbCell);
                     //    a: c:X   a:->  b:->
                     case DirProbRight -> copy(probCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:<-  b:X   c:?
               case DirProbLeft -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:<-  a:X   b:<-
                     case DirProbLeft ->  addDiff(probCell, rProbCell);
                     //    a: c:<-  a:X   b:X
                     case DirProbStay -> copy(probCell);
                     //    a: c:<-  a:X   b:->
                     case DirProbRight -> copy(probCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //default -> copy(probCell);
               default -> throw new IllegalStateException("Unexpected value: " + lOutProb.lastProbabilityPos);
            }
         }
         //default -> copy(probCell);
         default -> throw new IllegalStateException("Unexpected value: " + outProb.lastProbabilityPos);
      }
      //switch (rOutProb.lastProbabilityPos) {
      //   case DirProbLeft ->  addDiff(probCell, rProbCell);
      //}
   }

   private static void addDiff(final ProbCell probCell, final ProbCell bProbCell) {
      final Probability inProb = probCell.inProb;
      final Probability outProb = probCell.outProb;
      final Probability bOutProb = bProbCell.outProb;

      addArrDiff(inProb.probabilityArr, outProb.probabilityArr, bOutProb.probabilityArr);

      //probCell.inEField += bProbCell.outEField;
      probCell.inEField += probCell.outEField - (probCell.outEField - bProbCell.outEField);
      //inArr[pos] +=  aArr[pos] - (aArr[pos] - bArr[pos]);
   }
   
   private static void copy(final ProbCell probCell) {
      final Probability inProb = probCell.inProb;
      final Probability outProb = probCell.outProb;

      copyArr(inProb.probabilityArr, outProb.probabilityArr);

      probCell.inEField += probCell.outEField;
   }

   /**
    * a = b
    */
   private static void copyArr(final int[] aArr, final int[] bArr) {
      for (int pos = 0; pos < aArr.length; pos++) {
         aArr[pos] =  bArr[pos];
      }
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
