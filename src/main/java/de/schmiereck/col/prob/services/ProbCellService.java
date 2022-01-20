package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbCell.EFieldLeft;
import static de.schmiereck.col.prob.model.ProbCell.EFieldRight;
import static de.schmiereck.col.services.ProbabilityService.calcNext;
import static de.schmiereck.col.services.UniverseUtils.calcCellPos;

import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbUniverse;

/**
 * 1. calcNextOutProb   next(out)
 * 2. calcInProb        in = calc(out)
 * 3. calcOut           out = in       in = 0
 */
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

   public static void calcNextOutProb(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         calcNextOutProb(probCell);
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
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse. universeSize, pos + 1)];
         calcOut(probCell, lProbCell, rProbCell);
      }

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         clearIn(probCell);
      }
   }

   private static void clearIn(final ProbCell probCell) {
      clearArr(probCell.inProb.probabilityArr);
      clearArr(probCell.inEField);
   }

   private static void calcNextOutProb(final ProbCell probCell) {
      calcNext(probCell.outProb);
   }

   private static void calcOut(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      copyArr(probCell.outProb.probabilityArr, probCell.inProb.probabilityArr);
      calcOutFields(probCell, lProbCell, rProbCell);
   }

   private static void calcOutFields(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      //probCell.outEField = probCell.inEField;
      //probCell.outEField = probCell.inEField + lProbCell.inEField/2 - rProbCell.inEField/2;
      probCell.outEField[EFieldLeft] = probCell.inEField[EFieldLeft] + rProbCell.inEField[EFieldLeft]/2;
      probCell.outEField[EFieldRight] = probCell.inEField[EFieldRight] + lProbCell.inEField[EFieldRight]/2;
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
      //inArr[pos] +=  aArr[pos] - (aArr[pos] - bArr[pos]);
      //probCell.inEField += probCell.outEField - (probCell.outEField - bProbCell.outEField);
      probCell.inEField[EFieldLeft] += probCell.outEField[EFieldLeft] - (probCell.outEField[EFieldLeft] - bProbCell.outEField[EFieldLeft]);
      probCell.inEField[EFieldRight] += probCell.outEField[EFieldRight] - (probCell.outEField[EFieldRight] - bProbCell.outEField[EFieldRight]);
   }
   
   private static void copy(final ProbCell probCell) {
      final Probability inProb = probCell.inProb;
      final Probability outProb = probCell.outProb;

      copyArr(inProb.probabilityArr, outProb.probabilityArr);

      probCell.inEField[EFieldLeft] = probCell.outEField[EFieldLeft];
      probCell.inEField[EFieldRight] = probCell.outEField[EFieldRight];
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
