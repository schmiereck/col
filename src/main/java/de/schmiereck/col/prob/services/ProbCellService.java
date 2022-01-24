package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbField.FieldLeft;
import static de.schmiereck.col.prob.model.ProbField.FieldRight;
import static de.schmiereck.col.services.ProbabilityService.calcNext;
import static de.schmiereck.col.services.ProbabilityService.calcOperation;
import static de.schmiereck.col.services.UniverseUtils.calcCellPos;

import de.schmiereck.col.model.PMatrix;
import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbField;
import de.schmiereck.col.prob.model.ProbUniverse;
import de.schmiereck.col.services.ProbabilityService;

/**
 * Out ist der Zustand nach außen nach den Berechnungen.
 * In wird aufgrund der Out Zuständer der Zelle und ihrer Nachbarn berechnet.
 * Bei der Berechnung dürfen nur Out Zustände der L/R Nachbarn benutzt werden,
 * um Effekte durch die Reihenfolge der Berechnungen zu verhindern.
 *
 * 1. calcNextOutProb   next(out)
 * 2. calcInProb        in = calc(out)
 * 3. calcOut           out = in
 *                      in = 0
 */
public class ProbCellService {

   public static final int Max_Probability = 100;

   public static final int DirProbStay = 0;
   public static final int DirProbLeft = 1;
   public static final int DirProbRight = 2;
   public static final int DirProbSize = 3;
   //public static final int SpinProbUp = 3;
   //public static final int SpinProbStay = 4;
   //public static final int SpinProbDown = 5;
   //public static final int SpinProbSize = 3;
   //public static final int ProbSize = 6;

   public static final int EProbLeft = 0;
   public static final int EProbRight = 1;
   public static final int EProbSize = 2;

   /**
    * s:30 l:0 r:70
    *
    * s = s + l + r
    * l = s + l + r
    * r = s + l + r
    */
   public static PMatrix LR_REFLECTION_MATRIX = new PMatrix(new int[][]
           {
                   { 1, 0, 0 },
                   { 0, 0, 1 },
                   { 0, 1, 0 }
           });

   public static PMatrix LR_CONTINUE_MATRIX = new PMatrix(new int[][]
           {
                   { 1, 0, 0 },
                   { 0, 1, 0 },
                   { 0, 0, 1 }
           });

   public static void calcInFieldSource(final ProbField probField, final ProbField lProbField, final ProbField rProbField) {
      //probField.inField = probField.outField;
      //probCell.outEField = probCell.inEField + lProbCell.inEField/2 - rProbCell.inEField/2;
      //probCell.outEFieldArr[EFieldLeft] = probCell.inEFieldArr[EFieldLeft] + rProbCell.inEFieldArr[EFieldLeft]/2;
      //probCell.outEFieldArr[EFieldRight] = probCell.inEFieldArr[EFieldRight] + lProbCell.inEFieldArr[EFieldRight]/2;

      //probField.outFieldArr[FieldLeft] = probField.inField + rProbField.inField / Field_Divisor;
      //probField.outFieldArr[FieldRight] = probField.inField + lProbField.inField / Field_Divisor;
      probField.inFieldArr[FieldLeft] = rProbField.outField;// / Field_Divisor;
      probField.inFieldArr[FieldRight] = lProbField.outField;// / Field_Divisor;
   }

   public static void calcInProb(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      // calcOperation()

      final Probability inProb = probCell.inProb;
      final Probability outProb = probCell.outProb;
      final Probability lOutProb = lProbCell.outProb;
      final Probability rOutProb = rProbCell.outProb;

      // p-Field Source?
      if ((probCell.pProbField.outFieldArr[FieldRight] > 0) && (probCell.pProbField.outFieldArr[FieldLeft] > 0)) {
         copyOut2In(probCell);
      } else {
         // a is p-Field?  b: a:->   b:?  c:?
         if (lProbCell.pProbField.outFieldArr[FieldRight] > 0) {
            calcOperation(probCell.inProb, probCell.outProb, LR_REFLECTION_MATRIX);
            // TODO P Event Remove  !!!
         } else {
            // c is p-Field?  b: a:?   b:?  c:<-
            if (rProbCell.pProbField.outFieldArr[FieldLeft] > 0) {
               calcOperation(probCell.inProb, probCell.outProb, LR_REFLECTION_MATRIX);
            } else {
               if ((rOutProb.lastProbabilityPos == DirProbLeft) && (lOutProb.lastProbabilityPos == DirProbRight)) {
                  throw new RuntimeException("LR crash.");
               }
               if (outProb.lastProbabilityPos == DirProbRight) {
                  addDiff(probCell, rProbCell);
               } else {
                  if (outProb.lastProbabilityPos == DirProbLeft) {
                     addDiff(probCell, lProbCell);
                  } else {
                     if (lOutProb.lastProbabilityPos == DirProbRight) {
                        addDiff(probCell, lProbCell);
                     } else {
                        if (rOutProb.lastProbabilityPos == DirProbLeft) {
                           addDiff(probCell, rProbCell);
                        } else {
                           if (outProb.lastProbabilityPos == DirProbStay) {
                              copyOut2In(probCell);
                              //addDiff(probCell, rProbCell);
                           } else {
                              copyOut2In(probCell);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public static void calcInField(final ProbField probField, final ProbField lProbField, final ProbField rProbField) {
      //probField.outField = probField.inField;
      //probField.inFieldArr[FieldLeft] += probField.outFieldArr[FieldLeft] + rProbField.outFieldArr[FieldLeft] / Field_Divisor;
      //probField.inFieldArr[FieldRight] += probField.outFieldArr[FieldRight] + lProbField.outFieldArr[FieldRight] / Field_Divisor;
      probField.inFieldArr[FieldLeft] += rProbField.outFieldArr[FieldLeft] / Field_Divisor;
      probField.inFieldArr[FieldRight] += lProbField.outFieldArr[FieldRight] / Field_Divisor;
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

   public static void clearIn(final ProbCell probCell) {
      clearArr(probCell.inProb.probabilityArr);
      probCell.eProbField.inField = 0;
      clearArr(probCell.eProbField.inFieldArr);
      probCell.pProbField.inField = 0;
      clearArr(probCell.pProbField.inFieldArr);
   }

   public static void calcNextOutProb(final ProbCell probCell) {
      calcNext(probCell.outProb);
   }

   public static void calcOut(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      copyArr(probCell.outProb.probabilityArr, probCell.inProb.probabilityArr);
      calcOutFields(probCell, lProbCell, rProbCell);
   }

   private static void calcOutFields(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      calcOutField(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
      calcOutField(probCell.pProbField, lProbCell.pProbField, rProbCell.pProbField);
      calcOutFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
   }

   private static int Field_Divisor = 10; // 2;

   public static void calcOutFieldSource(final ProbField probField, final ProbField lProbField, final ProbField rProbField) {
      probField.outField = probField.inField;
      //probCell.outEField = probCell.inEField + lProbCell.inEField/2 - rProbCell.inEField/2;
      //probCell.outEFieldArr[EFieldLeft] = probCell.inEFieldArr[EFieldLeft] + rProbCell.inEFieldArr[EFieldLeft]/2;
      //probCell.outEFieldArr[EFieldRight] = probCell.inEFieldArr[EFieldRight] + lProbCell.inEFieldArr[EFieldRight]/2;

      //probField.outFieldArr[FieldLeft] = probField.inField + rProbField.inField / Field_Divisor;
      //probField.outFieldArr[FieldRight] = probField.inField + lProbField.inField / Field_Divisor;
      probField.outFieldArr[FieldLeft] += rProbField.inField;// / Field_Divisor;
      probField.outFieldArr[FieldRight] += lProbField.inField;// / Field_Divisor;
   }

   private static void calcOutField(final ProbField probField, final ProbField lProbField, final ProbField rProbField) {
      //probField.outField = probField.inField;
      //probField.outFieldArr[FieldLeft] += probField.inFieldArr[FieldLeft] + rProbField.inFieldArr[FieldLeft] / Field_Divisor;
      //probField.outFieldArr[FieldRight] += probField.inFieldArr[FieldRight] + lProbField.inFieldArr[FieldRight] / Field_Divisor;
      probField.outFieldArr[FieldLeft] = probField.inFieldArr[FieldLeft];
      probField.outFieldArr[FieldRight] = probField.inFieldArr[FieldRight];
   }

   static void addDiff(final ProbCell probCell, final ProbCell bProbCell) {
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

   static void copyOut2In(final ProbCell probCell) {
      final Probability inProb = probCell.inProb;
      final Probability outProb = probCell.outProb;

      copyArr(inProb.probabilityArr, outProb.probabilityArr);

      copyField(probCell.eProbField);
      copyField(probCell.pProbField);
   }

   private static void copyField(final ProbField probField) {
      probField.inField = probField.outField;
      //probField.inFieldArr[FieldLeft] = probField.outFieldArr[FieldLeft];
      //probField.inFieldArr[FieldRight] = probField.outFieldArr[FieldRight];
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
      //copyOut2In(probCell);
   }
}
