package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbField.FieldLeft;
import static de.schmiereck.col.prob.model.ProbField.FieldRight;
import static de.schmiereck.col.services.ProbabilityService.calcNext;
import static de.schmiereck.col.services.UniverseUtils.calcCellPos;
import static de.schmiereck.col.utils.IntMathUtils.calcDenominator2;
import static de.schmiereck.col.utils.IntMathUtils.calcFieldIn;

import de.schmiereck.col.model.PMatrix;
import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbField;
import de.schmiereck.col.services.ProbabilityService;

import java.util.Objects;

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
   public static final int EField_Range = 5;
   public static final int Max_EField = calcDenominator2(EField_Range);
   public static final int Min_EField = Max_EField/EField_Range;

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
   /*
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
   */
   public static void calcProbOut2In(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      // calcOperation()

      if (Objects.nonNull(probCell.eOutPart)) {
         //final Probability inProb = probCell.inProb;
         final Probability outProb = probCell.eOutPart.outProb;

         // p-Field Source?
         if ((probCell.pProbField.outFieldArr[FieldRight] > 0) && (probCell.pProbField.outFieldArr[FieldLeft] > 0)) {
            //copyOut2In(probCell);
         } else {
            //if (calcInImpulse(probCell, lProbCell, rProbCell) == false)
            {
               if (outProb.lastProbabilityPos == DirProbRight) {
                  //addDiffOut2In(probCell, rProbCell);
               } else {
                  if (outProb.lastProbabilityPos == DirProbLeft) {
                     //addDiffOut2In(probCell, lProbCell);
                  }
               }
            }
         }
      }

      if (Objects.nonNull(lProbCell.eOutPart) && Objects.nonNull(rProbCell.eOutPart)) {
         if ((rProbCell.eOutPart.outProb.lastProbabilityPos == DirProbLeft) && (lProbCell.eOutPart.outProb.lastProbabilityPos == DirProbRight)) {
            throw new RuntimeException("LR crash.");
         }
      }
      if (Objects.nonNull(lProbCell.eOutPart) && (lProbCell.eOutPart.outProb.lastProbabilityPos == DirProbRight)) {
         //addDiffOut2In(probCell, lProbCell);
         movePart(probCell, lProbCell);
      } else {
         if (Objects.nonNull(rProbCell.eOutPart) && (rProbCell.eOutPart.outProb.lastProbabilityPos == DirProbLeft)) {
            //addDiffOut2In(probCell, rProbCell);
            movePart(probCell, rProbCell);
         } else {
            if (Objects.nonNull(probCell.eOutPart) && (probCell.eOutPart.outProb.lastProbabilityPos == DirProbStay)) {
               stayPart(probCell);
            } else {
               //if (Objects.nonNull(probCell.eOutPart) && (probCell.eOutPart.outProb.lastProbabilityPos == DirProbLeft)) {
               //   clearPart(probCell);
               //}
            }
         }
      }
   }

   public static void calcFieldOut2In(final ProbField probField, final ProbField lProbField, final ProbField rProbField) {
      //probField.outField = probField.inField;
      //probField.inFieldArr[FieldLeft] += probField.outFieldArr[FieldLeft] + rProbField.outFieldArr[FieldLeft] / Field_Divisor;
      //probField.inFieldArr[FieldRight] += probField.outFieldArr[FieldRight] + lProbField.outFieldArr[FieldRight] / Field_Divisor;

      //probField.inFieldArr[FieldLeft] += rProbField.outFieldArr[FieldLeft] / Field_Divisor;
      //probField.inFieldArr[FieldRight] += lProbField.outFieldArr[FieldRight] / Field_Divisor;
      {
         final int out = probField.outFieldArr[FieldLeft];
         final int outL = 0;//lProbField.outFieldArr[FieldLeft];
         final int outR = rProbField.outFieldArr[FieldLeft];
         final int inL = calcFieldIn(outL, outR, out, Max_EField, Min_EField);
         probField.inFieldArr[FieldLeft] = inL;
      }
      {
         final int out = probField.outFieldArr[FieldRight];
         final int outL = lProbField.outFieldArr[FieldRight];
         final int outR = 0;//rProbField.outFieldArr[FieldRight];
         final int inR = calcFieldIn(outL, outR, out, Max_EField, Min_EField);
         probField.inFieldArr[FieldRight] = inR;
      }
   }

   public static void calcPFieldEOut2PIn(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      //final int eol = probCell.eProbField.outFieldArr[FieldLeft];
      //final int eor = probCell.eProbField.outFieldArr[FieldRight];
      final int reol = rProbCell.eProbField.outFieldArr[FieldLeft];
      final int reor = rProbCell.eProbField.outFieldArr[FieldRight];
      final int leol = lProbCell.eProbField.outFieldArr[FieldLeft];
      final int leor = lProbCell.eProbField.outFieldArr[FieldRight];
      final int pil;
      final int pir;

      if ((reol > 0) && (reor > 0)) {
         pil = reol;
         pir = 0;//reor;
      } else {
         if ((leol > 0) && (leor > 0)) {
            pil = 0;//leol;
            pir = leor;
         } else {
            pil = 0;
            pir = 0;
         }
      }
      probCell.pProbField.inFieldArr[FieldLeft] += pil;
      probCell.pProbField.inFieldArr[FieldRight] += pir;
      probCell.pProbField.inFieldArr[FieldLeft] += rProbCell.pProbField.outFieldArr[FieldLeft];
      probCell.pProbField.inFieldArr[FieldRight] += lProbCell.pProbField.outFieldArr[FieldRight];
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
/*
   public static void clearProbIn(final ProbCell probCell) {
      clearArr(probCell.inProb.probabilityArr);
      //probCell.eProbField.inField = 0;
      //clearArr(probCell.eProbField.inFieldArr);
      //probCell.pProbField.inField = 0;
      //clearArr(probCell.pProbField.inFieldArr);
   }
*/
   public static void clearFieldsIn(final ProbCell probCell) {
      //clearArr(probCell.inProb.probabilityArr);
      if (Objects.nonNull(probCell.eInPart)) {
         //probCell.ePart.inField = 0;
         probCell.eInPart = null;
      }
      clearArr(probCell.eProbField.inFieldArr);
      if (Objects.nonNull(probCell.pPart)) {
         //probCell.pPart.inField = 0;
      }
      clearArr(probCell.pProbField.inFieldArr);
   }

   public static void calcNextOutProb(final ProbCell probCell) {
      if (Objects.nonNull(probCell.eOutPart)) {
         calcNext(probCell.eOutPart.outProb);
      }
   }

   public static void calcProbIn2Out(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      //copyArr(probCell.outProb.probabilityArr, probCell.inProb.probabilityArr);
      if (Objects.nonNull(probCell.eInPart)) {
         probCell.eOutPart = probCell.eInPart;
         probCell.eInPart = null;
      } else {
         probCell.eOutPart = null;
      }
      //copyArr(probCell.outProb.probabilityCntArr, probCell.inProb.probabilityCntArr);
      //copyArr(probCell.outProb.lastProbabilityArr, probCell.inProb.lastProbabilityArr);
      //copyArr(probCell.outProb.lastProbabilityCntArr, probCell.inProb.lastProbabilityCntArr);
      //probCell.outProb.lastProbabilityPos = probCell.inProb.lastProbabilityPos;
   }

   static void calcEFieldIn2Out(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      calcFieldIn2Out(probCell.eProbField);
      calcEFieldSourceIn2Out(probCell, lProbCell, rProbCell);
   }

   static void calcPFieldIn2Out(final ProbCell probCell) {
      calcFieldIn2Out(probCell.pProbField);
   }

   private static int Field_Divisor = 10; // 2;

   public static void calcEFieldSourceIn2Out(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      final ProbField probField = probCell.eProbField;

      final ProbField lProbField = lProbCell.eProbField;
      final ProbField rProbField = rProbCell.eProbField;

      if (Objects.nonNull(probCell.eOutPart)) {
         //probField.outField = probField.inField;
        // probCell.ePart.outField = probCell.ePart.inField;
      }
      //probCell.outEField = probCell.inEField + lProbCell.inEField/2 - rProbCell.inEField/2;
      //probCell.outEFieldArr[EFieldLeft] = probCell.inEFieldArr[EFieldLeft] + rProbCell.inEFieldArr[EFieldLeft]/2;
      //probCell.outEFieldArr[EFieldRight] = probCell.inEFieldArr[EFieldRight] + lProbCell.inEFieldArr[EFieldRight]/2;

      //probField.outFieldArr[FieldLeft] = probField.inField + rProbField.inField / Field_Divisor;
      //probField.outFieldArr[FieldRight] = probField.inField + lProbField.inField / Field_Divisor;
      //probField.outFieldArr[FieldLeft] += (rProbField.inField * rProbCell.inProb.probabilityArr[DirProbLeft]) / Max_Probability;
      //probField.outFieldArr[FieldRight] += (lProbField.inField * lProbCell.inProb.probabilityArr[DirProbRight]) / Max_Probability;

      //if (rProbField.inField > 0) {
      if (Objects.nonNull(rProbCell.eOutPart)) {
         //probField.outFieldArr[FieldLeft] = rProbField.inField;
         //probField.outFieldArr[FieldLeft] = rProbCell.ePart.inField;
         probField.outFieldArr[FieldLeft] = rProbCell.eOutPart.outField;
      }
      //if (lProbField.inField > 0) {
      if (Objects.nonNull(lProbCell.eOutPart)) {
         //probField.outFieldArr[FieldRight] = lProbField.inField;
         //probField.outFieldArr[FieldRight] = lProbCell.ePart.inField;
         probField.outFieldArr[FieldRight] = lProbCell.eOutPart.outField;
      }
   }

   private static void calcFieldIn2Out(final ProbField probField) {
      //probField.outField = probField.inField;
      //probField.outFieldArr[FieldLeft] += probField.inFieldArr[FieldLeft] + rProbField.inFieldArr[FieldLeft] / Field_Divisor;
      //probField.outFieldArr[FieldRight] += probField.inFieldArr[FieldRight] + lProbField.inFieldArr[FieldRight] / Field_Divisor;
      probField.outFieldArr[FieldLeft] = probField.inFieldArr[FieldLeft];
      probField.outFieldArr[FieldRight] = probField.inFieldArr[FieldRight];
   }
/*
   static void addDiffOut2In(final ProbCell probCell, final ProbCell bProbCell) {
      final Probability inProb = probCell.inProb;
      final Probability outProb = probCell.outProb;
      final Probability bOutProb = bProbCell.outProb;

      addArrDiff(inProb.probabilityArr, outProb.probabilityArr, bOutProb.probabilityArr);
      addArrDiff(inProb.probabilityCntArr, outProb.probabilityCntArr, bOutProb.probabilityCntArr);
      addArrDiff(inProb.lastProbabilityArr, outProb.lastProbabilityArr, bOutProb.lastProbabilityArr);
      addArrDiff(inProb.lastProbabilityCntArr, outProb.lastProbabilityCntArr, bOutProb.lastProbabilityCntArr);
      inProb.lastProbabilityPos = bOutProb.lastProbabilityPos;

      //addFieldDiff(probCell.eProbField, bProbCell.eProbField);
      //addFieldDiff(probCell.pProbField, bProbCell.pProbField);
   }
*/
   static void movePart(final ProbCell probCell, final ProbCell bProbCell) {
      probCell.eInPart = bProbCell.eOutPart;
      probCell.eProbField.outFieldArr[FieldLeft] = 0;
      probCell.eProbField.outFieldArr[FieldRight] = 0;
      //bProbCell.eOutPart = null;

      probCell.pPart = bProbCell.pPart;
      bProbCell.pPart = null;
   }

   static void stayPart(final ProbCell probCell) {
      probCell.eInPart = probCell.eOutPart;
      probCell.eOutPart = null;
   }
   /*
   private static void addFieldDiff(final ProbField probField, final ProbField bProbField) {
      //probCell.inEField += bProbCell.outEField;
      //inArr[pos] +=  aArr[pos] - (aArr[pos] - bArr[pos]);
      probField.inField += probField.outField - (probField.outField - bProbField.outField);
      //probCell.inEFieldArr[EFieldLeft] += probCell.outEFieldArr[EFieldLeft] - (probCell.outEFieldArr[EFieldLeft] - bProbCell.outEFieldArr[EFieldLeft]);
      //probCell.inEFieldArr[EFieldRight] += probCell.outEFieldArr[EFieldRight] - (probCell.outEFieldArr[EFieldRight] - bProbCell.outEFieldArr[EFieldRight]);
   }
   */
   public static boolean calcImpulseOut2Out(final ProbCell probCell) {
      final boolean ret;
      // E-Field particle?
      if (Objects.nonNull(probCell.eOutPart)) {
         if (probCell.eOutPart.outField > 0) {
            // a is p-Field?  b: a:->   b:?  c:?
            if ((probCell.pProbField.outFieldArr[FieldRight] > 0)) {
               calcImpulseOut2Out(probCell, probCell);
               // TODO P Event Remove  !!!
               ret = true;
            } else {
               // c is p-Field?  b: a:?   b:?  c:<-
               if ((probCell.pProbField.outFieldArr[FieldLeft] > 0)) {
                  calcImpulseOut2Out(probCell, probCell);
                  ret = true;
               } else {
               /*
               if ((lProbCell.pProbField.outFieldArr[FieldRight] > 0)) {
                  calcBeschl(probCell, lProbCell);
                  // TODO P Event Remove  !!!
                  ret = true;
               } else {
                  if ((rProbCell.pProbField.outFieldArr[FieldLeft] > 0)) {
                     calcBeschl(probCell, rProbCell);
                     ret = true;
                  } else
                   {
                     ret = false;
                  }
               }
               */
                  ret = false;
               }
            }
         } else {
            ret = false;
         }
      } else {
         ret = false;
      }
      return ret;
   }

   /**
    *	L  S  R
    *	Richtung pField verschieben (100 = 100%, 50 = 50%, ...)
    *	Auch Stay berücksichtigen!
    */
   private static void calcImpulseOut2Out(final ProbCell probCell, final ProbCell pProbCell) {
      // Impuls des pFields übertragen.
      if (Objects.nonNull(probCell.eOutPart)) {
         final ProbField pProbField = pProbCell.pProbField;
         final Probability outProb = probCell.eOutPart.outProb;
         //final Probability inProb = probCell.inProb;

         //ProbCellServiceUtils.calcImpulse(inProb, outProb, pProbField);
         ProbCellServiceUtils.calcImpulse(outProb, outProb, pProbField);
      }
      //calcOperation(probCell.inProb, probCell.outProb, LR_REFLECTION_MATRIX);

      //copyField(probCell.eProbField);
      //copyField(probCell.pProbField);
   }
/*
   static void copyOut2In(final ProbCell probCell) {
      final Probability inProb = probCell.inProb;
      final Probability outProb = probCell.outProb;

      copyArr(inProb.probabilityArr, outProb.probabilityArr);

      //copyFieldOut2In(probCell.eProbField);
      if (Objects.nonNull(probCell.ePart)) {
         //probCell.ePart.inField = probCell.ePart.outField;
      }
      //copyFieldOut2In(probCell.pProbField);
      if (Objects.nonNull(probCell.pPart)) {
         //probCell.pPart.inField = probCell.pPart.outField;
      }
   }
 */
   /*
   private static void copyFieldOut2In(final ProbField probField) {
      probField.inField = probField.outField;
      //probField.inFieldArr[FieldLeft] = probField.outFieldArr[FieldLeft];
      //probField.inFieldArr[FieldRight] = probField.outFieldArr[FieldRight];
   }
   */
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
      if (Objects.nonNull(probCell.eOutPart)) {
         ProbabilityService.calcInit(probCell.eOutPart.outProb);
      }
      //copyOut2In(probCell);
   }
}
