package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbField.FieldLeft;
import static de.schmiereck.col.prob.model.ProbField.FieldRight;
import static de.schmiereck.col.services.ProbabilityService.calcNext;
import static de.schmiereck.col.services.UniverseUtils.calcCellPos;

import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbField;
import de.schmiereck.col.prob.model.ProbUniverse;
import de.schmiereck.col.services.ProbabilityService;

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
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];

         calcInProb(probCell, lProbCell, rProbCell);
      }
   }

   public static void calcOut(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];
         calcOut(probCell, lProbCell, rProbCell);
      }

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         clearIn(probCell);
      }
   }

   public static void clearIn(final ProbCell probCell) {
      clearArr(probCell.inProb.probabilityArr);
      probCell.eProbField.inField = 0;
      clearArr(probCell.eProbField.inFieldArr);
      probCell.pProbField.inField = 0;
      clearArr(probCell.pProbField.inFieldArr);
   }

   private static void calcNextOutProb(final ProbCell probCell) {
      calcNext(probCell.outProb);
   }

   public static void calcOut(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      copyArr(probCell.outProb.probabilityArr, probCell.inProb.probabilityArr);
      calcOutFields(probCell, lProbCell, rProbCell);
   }

   private static void calcOutFields(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      calcOutFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
      calcOutField(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
      calcOutField(probCell.pProbField, lProbCell.pProbField, rProbCell.pProbField);
   }

   private static int Field_Divisor = 10; // 2;

   private static void calcOutFieldSource(final ProbField probField, final ProbField lProbField, final ProbField rProbField) {
      probField.outField = probField.inField;
      //probCell.outEField = probCell.inEField + lProbCell.inEField/2 - rProbCell.inEField/2;
      //probCell.outEFieldArr[EFieldLeft] = probCell.inEFieldArr[EFieldLeft] + rProbCell.inEFieldArr[EFieldLeft]/2;
      //probCell.outEFieldArr[EFieldRight] = probCell.inEFieldArr[EFieldRight] + lProbCell.inEFieldArr[EFieldRight]/2;

      //probField.outFieldArr[FieldLeft] = probField.inField + rProbField.inField / Field_Divisor;
      //probField.outFieldArr[FieldRight] = probField.inField + lProbField.inField / Field_Divisor;
      probField.outFieldArr[FieldLeft] = rProbField.inField / Field_Divisor;
      probField.outFieldArr[FieldRight] = lProbField.inField / Field_Divisor;
   }

   private static void calcOutField(final ProbField probField, final ProbField lProbField, final ProbField rProbField) {
      probField.outField = probField.inField;
      //probCell.outEField = probCell.inEField + lProbCell.inEField/2 - rProbCell.inEField/2;
      probField.outFieldArr[FieldLeft] += probField.inFieldArr[FieldLeft] + rProbField.inFieldArr[FieldLeft] / Field_Divisor;
      probField.outFieldArr[FieldRight] += probField.inFieldArr[FieldRight] + lProbField.inFieldArr[FieldRight] / Field_Divisor;
      //probField.outFieldArr[FieldLeft] = probField.inField + rProbField.inField / 2;
      //probField.outFieldArr[FieldRight] = probField.inField + lProbField.inField / 2;
   }

   private static void calcInProb(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      // calcOperation()

      final Probability inProb = probCell.inProb;
      final Probability outProb = probCell.outProb;
      final Probability lOutProb = lProbCell.outProb;
      final Probability rOutProb = rProbCell.outProb;

      calcInProbField(probCell, lProbCell, rProbCell);

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
                     case DirProbLeft -> addDiff(probCell, rProbCell);
                     //    b: a:->  b:<-  c:X
                     case DirProbStay -> addDiff(probCell, lProbCell);
                     //    b: a:->  b:<-  c:->
                     case DirProbRight -> addDiff(probCell, lProbCell);
                     //default -> copy(probCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:X   b:<-  c:?
               case DirProbStay -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:X   a:<-  b:<-
                     case DirProbLeft -> addDiff(probCell, lProbCell);
                     //    b: c:X   a:<-  b:X
                     case DirProbStay -> addDiff(probCell, lProbCell);
                     //    a: c:X   a:<-  b:->
                     case DirProbRight -> copyOut2In(probCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:<-  b:<-  c:?
               case DirProbLeft -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:<-  a:<-  b:<-
                     case DirProbLeft -> addDiff(probCell, lProbCell);
                     //    b: c:<-  a:<-  b:X
                     case DirProbStay -> addDiff(probCell, lProbCell);
                     //    a: c:<-  a:<-  b:->
                     case DirProbRight -> addDiff(probCell, lProbCell);
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
                     case DirProbLeft -> addDiff(probCell, rProbCell);
                     //    b: a:->  b:X   c:X
                     case DirProbStay -> addDiff(probCell, lProbCell);
                     //    b: a:->  b:X   c:->
                     case DirProbRight -> addDiff(probCell, lProbCell);
                     //default -> copy(probCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:X   b:X   c:?
               case DirProbStay -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:X   a:X   b:<-
                     case DirProbLeft -> addDiff(probCell, rProbCell);
                     //    b: c:X   a:X   b:X
                     case DirProbStay -> copyOut2In(probCell);
                     //    a: c:X   a:X   b:->
                     case DirProbRight -> copyOut2In(probCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:<-  b:X   c:?
               case DirProbLeft -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:<-  a:X   b:<-
                     case DirProbLeft -> addDiff(probCell, rProbCell);
                     //    b: c:<-  a:X   b:X
                     case DirProbStay -> copyOut2In(probCell);
                     //    a: c:<-  a:X   b:->
                     case DirProbRight -> copyOut2In(probCell);
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
                     case DirProbLeft -> addDiff(probCell, rProbCell);
                     //    b: a:->  b:->  c:X
                     case DirProbStay -> addDiff(probCell, rProbCell);
                     //    a: a:->  b:->  c:->
                     case DirProbRight -> addDiff(probCell, lProbCell);
                     //default -> copy(probCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:X   b:X   c:?
               case DirProbStay -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:X   a:->  b:<-
                     case DirProbLeft -> addDiff(probCell, rProbCell);
                     //    a: c:X   a:->  b:X
                     case DirProbStay -> addDiff(probCell, rProbCell);
                     //    a: c:X   a:->  b:->
                     case DirProbRight -> copyOut2In(probCell);
                     default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                  }
               }
               //    b: a:<-  b:X   c:?
               case DirProbLeft -> {
                  switch (rOutProb.lastProbabilityPos) {
                     //    a: c:<-  a:X   b:<-
                     case DirProbLeft -> addDiff(probCell, rProbCell);
                     //    a: c:<-  a:X   b:X
                     case DirProbStay -> copyOut2In(probCell);
                     //    a: c:<-  a:X   b:->
                     case DirProbRight -> copyOut2In(probCell);
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

   public static void calcInProbField(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      final int eol = probCell.eProbField.outFieldArr[FieldLeft];
      final int eor = probCell.eProbField.outFieldArr[FieldRight];
      final int pil;
      final int pir;

      if ((eol > 0) && (eor > 0)) {
         pil = eol;
         pir = eor;
      } else {
         pil = 0;
         pir = 0;
      }
      probCell.pProbField.inFieldArr[FieldLeft] += pil;
      probCell.pProbField.inFieldArr[FieldRight] += pir;
/*
      final int eolr = lProbCell.eProbField.outFieldArr[FieldRight];
      final int eorl = rProbCell.eProbField.outFieldArr[FieldLeft];
      final int pilr;
      final int pirl;

      if ((eolr > 0) && (eorl > 0)) {
         pilr = eolr;
         pirl = eorl;
      } else {
         pilr = 0;
         pirl = 0;
      }
      probCell.pProbField.inFieldArr[FieldRight] += pilr;
      probCell.pProbField.inFieldArr[FieldLeft] += pirl;
*/
   }

   private static void addDiff(final ProbCell probCell, final ProbCell bProbCell) {
      final Probability inProb = probCell.inProb;
      final Probability outProb = probCell.outProb;
      final Probability bOutProb = bProbCell.outProb;

      addArrDiff(inProb.probabilityArr, outProb.probabilityArr, bOutProb.probabilityArr);

      addFieldDiff(probCell.eProbField, bProbCell.eProbField);
      addFieldDiff(probCell.pProbField, bProbCell.pProbField);
   }

   private static void addFieldDiff(final ProbField probField, final ProbField bProbField) {
      //probCell.inEField += bProbCell.outEField;
      //inArr[pos] +=  aArr[pos] - (aArr[pos] - bArr[pos]);
      probField.inField += probField.outField - (probField.outField - bProbField.outField);
      //probCell.inEFieldArr[EFieldLeft] += probCell.outEFieldArr[EFieldLeft] - (probCell.outEFieldArr[EFieldLeft] - bProbCell.outEFieldArr[EFieldLeft]);
      //probCell.inEFieldArr[EFieldRight] += probCell.outEFieldArr[EFieldRight] - (probCell.outEFieldArr[EFieldRight] - bProbCell.outEFieldArr[EFieldRight]);
   }

   private static void copyOut2In(final ProbCell probCell) {
      final Probability inProb = probCell.inProb;
      final Probability outProb = probCell.outProb;

      copyArr(inProb.probabilityArr, outProb.probabilityArr);

      copyField(probCell.eProbField);
      copyField(probCell.pProbField);
   }

   private static void copyField(final ProbField probField) {
      probField.inField = probField.outField;
      probField.inFieldArr[FieldLeft] = probField.outFieldArr[FieldLeft];
      probField.inFieldArr[FieldRight] = probField.outFieldArr[FieldRight];
   }

   /**
    * a = b
    */
   private static void copyArr(final int[] aArr, final int[] bArr) {
      for (int pos = 0; pos < aArr.length; pos++) {
         aArr[pos] = bArr[pos];
      }
   }

   private static void clearArr(final int[] inArr) {
      for (int pos = 0; pos < inArr.length; pos++) {
         inArr[pos] = 0;
      }
   }

   /**
    * in = a - b
    */
   private static void addArrDiff(final int[] inArr, final int[] aArr, final int[] bArr) {
      for (int pos = 0; pos < inArr.length; pos++) {
         inArr[pos] += aArr[pos] - (aArr[pos] - bArr[pos]);
      }
   }

   public static void calcInit(final ProbCell probCell) {
      ProbabilityService.calcInit(probCell.outProb);
      copyOut2In(probCell);
   }
}
