package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbField.FieldLeft;
import static de.schmiereck.col.prob.model.ProbField.FieldRight;
import static de.schmiereck.col.services.ProbabilityService.calcNext;
import static de.schmiereck.col.utils.IntMathUtils.calcDenominator2;
import static de.schmiereck.col.utils.IntMathUtils.calcFieldIn;

import de.schmiereck.col.model.PMatrix;
import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.Part;
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

   public static final int PField_Range = 5;
   public static final int Max_PField = calcDenominator2(PField_Range);
   public static final int Min_PField = Max_PField/PField_Range;

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
         final Probability outProb = probCell.eOutPart.prob;

         // p-Field Source?
         if ((probCell.pProbFieldArr[FieldRight].outField > 0) && (probCell.pProbFieldArr[FieldLeft].outField > 0)) {
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
         if ((rProbCell.eOutPart.prob.lastProbabilityPos == DirProbLeft) && (lProbCell.eOutPart.prob.lastProbabilityPos == DirProbRight)) {
            throw new RuntimeException("LR crash.");
         }
      }
      if (Objects.nonNull(lProbCell.eOutPart) && (lProbCell.eOutPart.prob.lastProbabilityPos == DirProbRight)) {
         moveEPart(probCell, lProbCell);
      } else {
         if (Objects.nonNull(rProbCell.eOutPart) && (rProbCell.eOutPart.prob.lastProbabilityPos == DirProbLeft)) {
            moveEPart(probCell, rProbCell);
         } else {
            if (Objects.nonNull(probCell.eOutPart) && (probCell.eOutPart.prob.lastProbabilityPos == DirProbStay)) {
               stayEPart(probCell);
            }
         }
      }
      if (Objects.nonNull(lProbCell.pOutPart) && (lProbCell.pOutPart.prob.lastProbabilityPos == DirProbRight)) {
         movePPart(probCell, lProbCell);
      } else {
         if (Objects.nonNull(rProbCell.pOutPart) && (rProbCell.pOutPart.prob.lastProbabilityPos == DirProbLeft)) {
            movePPart(probCell, rProbCell);
         } else {
            if (Objects.nonNull(probCell.pOutPart) && (probCell.pOutPart.prob.lastProbabilityPos == DirProbStay)) {
               stayPPart(probCell);
            }
         }
      }
   }

   public static void calcEFieldOut2In(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      //probField.outField = probField.inField;
      //probField.inFieldArr[FieldLeft] += probField.outFieldArr[FieldLeft] + rProbField.outFieldArr[FieldLeft] / Field_Divisor;
      //probField.inFieldArr[FieldRight] += probField.outFieldArr[FieldRight] + lProbField.outFieldArr[FieldRight] / Field_Divisor;

      //probField.inFieldArr[FieldLeft] += rProbField.outFieldArr[FieldLeft] / Field_Divisor;
      //probField.inFieldArr[FieldRight] += lProbField.outFieldArr[FieldRight] / Field_Divisor;
      if (Objects.nonNull(rProbCell.eProbFieldArr[FieldLeft].outSourcePart))
      {
         final int out = probCell.eProbFieldArr[FieldLeft].outField;
         final int outL = 0;//lProbField.outFieldArr[FieldLeft];
         final int outR = rProbCell.eProbFieldArr[FieldLeft].outField;
         final int inL = calcFieldIn(outL, outR, out, Max_EField, Min_EField);
         probCell.eProbFieldArr[FieldLeft].inField = inL;
         if (inL > 0) {
            probCell.eProbFieldArr[FieldLeft].inSourcePart = rProbCell.eProbFieldArr[FieldLeft].outSourcePart;
         } else {
            probCell.eProbFieldArr[FieldLeft].inSourcePart = null;
         }
      } else {
         probCell.eProbFieldArr[FieldLeft].inField = 0;
         probCell.eProbFieldArr[FieldLeft].inSourcePart = null;
      }
      if (Objects.nonNull(lProbCell.eProbFieldArr[FieldRight].outSourcePart))
      {
         final int out = probCell.eProbFieldArr[FieldRight].outField;
         final int outL = lProbCell.eProbFieldArr[FieldRight].outField;
         final int outR = 0;//rProbField.outFieldArr[FieldRight];
         final int inR = calcFieldIn(outL, outR, out, Max_EField, Min_EField);
         probCell.eProbFieldArr[FieldRight].inField = inR;
         if (inR > 0) {
            probCell.eProbFieldArr[FieldRight].inSourcePart = lProbCell.eProbFieldArr[FieldRight].outSourcePart;
         } else {
            probCell.eProbFieldArr[FieldRight].inSourcePart = null;
         }
      } else {
         probCell.eProbFieldArr[FieldRight].inField = 0;
         probCell.eProbFieldArr[FieldRight].inSourcePart = null;
      }
   }

   public static void calcPFieldOut2In(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      if (Objects.nonNull(rProbCell.pProbFieldArr[FieldLeft].outSourcePart))
      {
         final int out = probCell.pProbFieldArr[FieldLeft].outField;
         final int outL = 0;//lProbField.outFieldArr[FieldLeft];
         final int outR = rProbCell.pProbFieldArr[FieldLeft].outField;
         final int inL = outR;//calcFieldIn(outL, outR, out, Max_EField, Min_EField);
         probCell.pProbFieldArr[FieldLeft].inField = inL;
         if (inL > 0) {
            probCell.pProbFieldArr[FieldLeft].inSourcePart = rProbCell.pProbFieldArr[FieldLeft].outSourcePart;
         } else {
            probCell.pProbFieldArr[FieldLeft].inSourcePart = null;
         }
      } else {
         probCell.pProbFieldArr[FieldLeft].inField = 0;
         probCell.pProbFieldArr[FieldLeft].inSourcePart = null;
      }
      if (Objects.nonNull(lProbCell.pProbFieldArr[FieldRight].outSourcePart))
      {
         final int out = probCell.pProbFieldArr[FieldRight].outField;
         final int outL = lProbCell.pProbFieldArr[FieldRight].outField;
         final int outR = 0;//rProbField.outFieldArr[FieldRight];
         final int inR = outL;//calcFieldIn(outL, outR, out, Max_EField, Min_EField);
         probCell.pProbFieldArr[FieldRight].inField = inR;
         if (inR > 0) {
            probCell.pProbFieldArr[FieldRight].inSourcePart = lProbCell.pProbFieldArr[FieldRight].outSourcePart;
         } else {
            probCell.pProbFieldArr[FieldRight].inSourcePart = null;
         }
      } else {
         probCell.pProbFieldArr[FieldRight].inField = 0;
         probCell.pProbFieldArr[FieldRight].inSourcePart = null;
      }
   }

   public static void calcPFieldEOut2PIn(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      final int eol = probCell.eProbFieldArr[FieldLeft].outField;
      final int eor = probCell.eProbFieldArr[FieldRight].outField;

      if ((eol > 0) && (eor > 0)) {
         {
            final ProbField lEProbField = probCell.eProbFieldArr[FieldLeft];
            final int lProb = lEProbField.outSourcePart.prob.probabilityArr[DirProbLeft];
            final ProbField lPProbField = probCell.pProbFieldArr[FieldLeft];
            lPProbField.inField = (eol * lProb) / Max_Probability;
            lPProbField.inSourcePart = new Part(Max_Probability, DirProbSize);
            lPProbField.inSourcePart.field = Max_PField;
         }
         {
            final ProbField rEProbField = probCell.eProbFieldArr[FieldRight];
            final int rProb = rEProbField.outSourcePart.prob.probabilityArr[DirProbRight];
            final ProbField rPProbField = probCell.pProbFieldArr[FieldRight];
            rPProbField.inField = (eor * rProb) / Max_Probability;
            rPProbField.inSourcePart = new Part(Max_Probability, DirProbSize);
            rPProbField.inSourcePart.field = Max_PField;
         }
      }
   }

   public static void calcPFieldEOut2PIn_innerCell(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      final int eol = probCell.eProbFieldArr[FieldLeft].outField;
      final int eor = probCell.eProbFieldArr[FieldRight].outField;

      if ((eol > 0) && (eor > 0)) {
         {
            final ProbField lEProbField = probCell.eProbFieldArr[FieldLeft];
            final int lProb = lEProbField.outSourcePart.prob.probabilityArr[DirProbLeft];
            final ProbField lPProbField = probCell.pProbFieldArr[FieldLeft];
            lPProbField.inField = (eol * lProb) / Max_Probability;
            lPProbField.inSourcePart = new Part(Max_Probability, DirProbSize);
            lPProbField.inSourcePart.field = Max_PField;
         }
         {
            final ProbField rEProbField = probCell.eProbFieldArr[FieldRight];
            final int rProb = rEProbField.outSourcePart.prob.probabilityArr[DirProbRight];
            final ProbField rPProbField = probCell.pProbFieldArr[FieldRight];
            rPProbField.inField = (eor * rProb) / Max_Probability;
            rPProbField.inSourcePart = new Part(Max_Probability, DirProbSize);
            rPProbField.inSourcePart.field = Max_PField;
         }
      }
      /*
      final int reol = rProbCell.eProbFieldArr[FieldLeft].outField;
      final int reor = rProbCell.eProbFieldArr[FieldRight].outField;
      final int leol = lProbCell.eProbFieldArr[FieldLeft].outField;
      final int leor = lProbCell.eProbFieldArr[FieldRight].outField;
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
      probCell.pProbFieldArr[FieldLeft].inField += pil;
      probCell.pProbFieldArr[FieldRight].inField += pir;
      probCell.pProbFieldArr[FieldLeft].inField += rProbCell.pProbFieldArr[FieldLeft].outField;
      probCell.pProbFieldArr[FieldRight].inField += lProbCell.pProbFieldArr[FieldRight].outField;
       */
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
      if (Objects.nonNull(probCell.eInPart)) {
         probCell.eInPart = null;
      }
      for (int pos = 0; pos < probCell.eProbFieldArr.length; pos++) {
         probCell.eProbFieldArr[pos].inField = 0;
         probCell.eProbFieldArr[pos].inSourcePart = null;
      }
      if (Objects.nonNull(probCell.pInPart)) {
         probCell.eInPart = null;
      }
      for (int pos = 0; pos < probCell.pProbFieldArr.length; pos++) {
         probCell.pProbFieldArr[pos].inField = 0;
         probCell.pProbFieldArr[pos].inSourcePart = null;
      }
   }

   public static void calcNextOutProb(final ProbCell probCell) {
      if (Objects.nonNull(probCell.eOutPart)) {
         calcNext(probCell.eOutPart.prob);
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
      probCell.eProbFieldArr[FieldLeft].outField = probCell.eProbFieldArr[FieldLeft].inField;
      probCell.eProbFieldArr[FieldLeft].outSourcePart = probCell.eProbFieldArr[FieldLeft].inSourcePart;
      probCell.eProbFieldArr[FieldRight].outField = probCell.eProbFieldArr[FieldRight].inField;
      probCell.eProbFieldArr[FieldRight].outSourcePart = probCell.eProbFieldArr[FieldRight].inSourcePart;

      calcEFieldSourceIn2Out(probCell, lProbCell, rProbCell);
   }

   static void calcPFieldIn2Out(final ProbCell probCell) {
      probCell.pProbFieldArr[FieldLeft].outField = probCell.pProbFieldArr[FieldLeft].inField;
      probCell.pProbFieldArr[FieldLeft].outSourcePart = probCell.pProbFieldArr[FieldLeft].inSourcePart;
      probCell.pProbFieldArr[FieldRight].outField = probCell.pProbFieldArr[FieldRight].inField;
      probCell.pProbFieldArr[FieldRight].outSourcePart = probCell.pProbFieldArr[FieldRight].inSourcePart;
   }

   private static int Field_Divisor = 10; // 2;

   public static void calcEFieldSourceIn2Out(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
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
         probCell.eProbFieldArr[FieldLeft].outField = rProbCell.eOutPart.field;
         probCell.eProbFieldArr[FieldLeft].outSourcePart = rProbCell.eOutPart;
      }
      //if (lProbField.inField > 0) {
      if (Objects.nonNull(lProbCell.eOutPart)) {
         //probField.outFieldArr[FieldRight] = lProbField.inField;
         //probField.outFieldArr[FieldRight] = lProbCell.ePart.inField;
         probCell.eProbFieldArr[FieldRight].outField = lProbCell.eOutPart.field;
         probCell.eProbFieldArr[FieldRight].outSourcePart = lProbCell.eOutPart;
      }
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
   static void moveEPart(final ProbCell probCell, final ProbCell bProbCell) {
      probCell.eInPart = bProbCell.eOutPart;
      probCell.eProbFieldArr[FieldLeft].inField = 0;
      probCell.eProbFieldArr[FieldRight].inField = 0;
      //bProbCell.eOutPart = null;
   }

   static void movePPart(final ProbCell probCell, final ProbCell bProbCell) {
      probCell.pInPart = bProbCell.pOutPart;
   }

   static void stayEPart(final ProbCell probCell) {
      probCell.eInPart = probCell.eOutPart;
      probCell.eOutPart = null;
   }

   static void stayPPart(final ProbCell probCell) {
      probCell.pInPart = probCell.pOutPart;
      probCell.pOutPart = null;
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
         if (probCell.eOutPart.field > 0) {
            // a is p-Field?  b: a:->   b:?  c:?
            if ((probCell.pProbFieldArr[FieldRight].outField > 0)) {
               calcImpulseOut2Out(probCell, probCell);
               // TODO P Event Remove  !!!
               ret = true;
            } else {
               // c is p-Field?  b: a:?   b:?  c:<-
               if ((probCell.pProbFieldArr[FieldLeft].outField > 0)) {
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
         final Probability outProb = probCell.eOutPart.prob;
         //final Probability inProb = probCell.inProb;

         //ProbCellServiceUtils.calcImpulse(inProb, outProb, pProbField);
         ProbCellServiceUtils.calcImpulse(outProb, outProb, pProbCell);
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
         ProbabilityService.calcInit(probCell.eOutPart.prob);
      }
      //copyOut2In(probCell);
   }
}
