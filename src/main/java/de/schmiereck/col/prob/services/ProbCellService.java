package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbCell.InState;
import static de.schmiereck.col.prob.model.ProbCell.OutState;
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
 * <p>
 * 1. calcNextOutProb   next(out)
 * 2. calcInProb        in = calc(out)
 * 3. calcOut           out = in
 * in = 0
 */
public class ProbCellService {

   public static final int Max_Probability = 100;

   public static final int EField_Range = 5;
   public static final int Max_EField = calcDenominator2(EField_Range);
   public static final int Min_EField = Max_EField / EField_Range;

   public static final int PField_Range = 5;
   public static final int Max_PField = calcDenominator2(PField_Range);
   public static final int Min_PField = Max_PField / PField_Range;

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
    * <p>
    * s = s + l + r
    * l = s + l + r
    * r = s + l + r
    */
   public static PMatrix LR_REFLECTION_MATRIX = new PMatrix(new int[][]
           {
                   {1, 0, 0},
                   {0, 0, 1},
                   {0, 1, 0}
           });

   public static PMatrix LR_CONTINUE_MATRIX = new PMatrix(new int[][]
           {
                   {1, 0, 0},
                   {0, 1, 0},
                   {0, 0, 1}
           });

   /*
   public static void calcInFieldSource(final ProbField probField, final ProbField lProbField, final ProbField rProbField) {
      //probField.field = probField.field;
      //probCell.outEField = probCell.inEField + lProbCell.inEField/2 - rProbCell.inEField/2;
      //probCell.outEFieldArr[EFieldLeft] = probCell.inEFieldArr[EFieldLeft] + rProbCell.inEFieldArr[EFieldLeft]/2;
      //probCell.outEFieldArr[EFieldRight] = probCell.inEFieldArr[EFieldRight] + lProbCell.inEFieldArr[EFieldRight]/2;

      //probField.outFieldArr[FieldLeft] = probField.field + rProbField.field / Field_Divisor;
      //probField.outFieldArr[FieldRight] = probField.field + lProbField.field / Field_Divisor;
      probField.inFieldArr[FieldLeft] = rProbField.field;// / Field_Divisor;
      probField.inFieldArr[FieldRight] = lProbField.field;// / Field_Divisor;
   }
   */
   public static void calcProbOut2In(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      // calcOperation()

      if (Objects.nonNull(probCell.eOutPart)) {
         //final Probability inProb = probCell.inProb;
         final Probability outProb = probCell.eOutPart.prob;

         // p-Field Source?
         if ((probCell.probCellState[OutState].pProbFieldArr[FieldRight].field > 0) && (probCell.probCellState[OutState].pProbFieldArr[FieldLeft].field > 0)) {
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
      //probField.field = probField.field;
      //probField.inFieldArr[FieldLeft] += probField.outFieldArr[FieldLeft] + rProbField.outFieldArr[FieldLeft] / Field_Divisor;
      //probField.inFieldArr[FieldRight] += probField.outFieldArr[FieldRight] + lProbField.outFieldArr[FieldRight] / Field_Divisor;

      //probField.inFieldArr[FieldLeft] += rProbField.outFieldArr[FieldLeft] / Field_Divisor;
      //probField.inFieldArr[FieldRight] += lProbField.outFieldArr[FieldRight] / Field_Divisor;
      if (Objects.nonNull(rProbCell.probCellState[OutState].eProbFieldArr[FieldLeft].sourcePart)) {
         final int out = probCell.probCellState[OutState].eProbFieldArr[FieldLeft].field;
         final int outL = 0;//lProbField.outFieldArr[FieldLeft];
         final int outR = rProbCell.probCellState[OutState].eProbFieldArr[FieldLeft].field;
         final int inL = calcFieldIn(outL, outR, out, Max_EField, Min_EField);
         probCell.probCellState[InState].eProbFieldArr[FieldLeft].field = inL;
         if (inL > 0) {
            probCell.probCellState[InState].eProbFieldArr[FieldLeft].sourcePart = rProbCell.probCellState[OutState].eProbFieldArr[FieldLeft].sourcePart;
         } else {
            probCell.probCellState[InState].eProbFieldArr[FieldLeft].sourcePart = null;
         }
      } else {
         probCell.probCellState[InState].eProbFieldArr[FieldLeft].field = 0;
         probCell.probCellState[InState].eProbFieldArr[FieldLeft].sourcePart = null;
      }
      if (Objects.nonNull(lProbCell.probCellState[OutState].eProbFieldArr[FieldRight].sourcePart)) {
         final int out = probCell.probCellState[OutState].eProbFieldArr[FieldRight].field;
         final int outL = lProbCell.probCellState[OutState].eProbFieldArr[FieldRight].field;
         final int outR = 0;//rProbField.outFieldArr[FieldRight];
         final int inR = calcFieldIn(outL, outR, out, Max_EField, Min_EField);
         probCell.probCellState[InState].eProbFieldArr[FieldRight].field = inR;
         if (inR > 0) {
            probCell.probCellState[InState].eProbFieldArr[FieldRight].sourcePart = lProbCell.probCellState[OutState].eProbFieldArr[FieldRight].sourcePart;
         } else {
            probCell.probCellState[InState].eProbFieldArr[FieldRight].sourcePart = null;
         }
      } else {
         probCell.probCellState[InState].eProbFieldArr[FieldRight].field = 0;
         probCell.probCellState[InState].eProbFieldArr[FieldRight].sourcePart = null;
      }
   }

   public static void calcPFieldOut2In(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      if (Objects.nonNull(rProbCell.probCellState[OutState].pProbFieldArr[FieldLeft].sourcePart)) {
         final int out = probCell.probCellState[OutState].pProbFieldArr[FieldLeft].field;
         final int outL = 0;//lProbField.outFieldArr[FieldLeft];
         final int outR = rProbCell.probCellState[OutState].pProbFieldArr[FieldLeft].field;
         final int inL = outR;//calcFieldIn(outL, outR, out, Max_EField, Min_EField);
         probCell.probCellState[InState].pProbFieldArr[FieldLeft].field = inL;
         if (inL > 0) {
            probCell.probCellState[InState].pProbFieldArr[FieldLeft].sourcePart = rProbCell.probCellState[OutState].pProbFieldArr[FieldLeft].sourcePart;
         } else {
            probCell.probCellState[InState].pProbFieldArr[FieldLeft].sourcePart = null;
         }
      } else {
         probCell.probCellState[InState].pProbFieldArr[FieldLeft].field = 0;
         probCell.probCellState[InState].pProbFieldArr[FieldLeft].sourcePart = null;
      }
      if (Objects.nonNull(lProbCell.probCellState[OutState].pProbFieldArr[FieldRight].sourcePart)) {
         final int out = probCell.probCellState[OutState].pProbFieldArr[FieldRight].field;
         final int outL = lProbCell.probCellState[OutState].pProbFieldArr[FieldRight].field;
         final int outR = 0;//rProbField.outFieldArr[FieldRight];
         final int inR = outL;//calcFieldIn(outL, outR, out, Max_EField, Min_EField);
         probCell.probCellState[InState].pProbFieldArr[FieldRight].field = inR;
         if (inR > 0) {
            probCell.probCellState[InState].pProbFieldArr[FieldRight].sourcePart = lProbCell.probCellState[OutState].pProbFieldArr[FieldRight].sourcePart;
         } else {
            probCell.probCellState[InState].pProbFieldArr[FieldRight].sourcePart = null;
         }
      } else {
         probCell.probCellState[InState].pProbFieldArr[FieldRight].field = 0;
         probCell.probCellState[InState].pProbFieldArr[FieldRight].sourcePart = null;
      }
   }

   public static void calcPFieldEOut2P(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell, final int targetState) {
      final int eol = probCell.probCellState[OutState].eProbFieldArr[FieldLeft].field;
      final int eor = probCell.probCellState[OutState].eProbFieldArr[FieldRight].field;

      if ((eol > 0) && (eor > 0)) {
         {
            final ProbField lOutEProbField = probCell.probCellState[OutState].eProbFieldArr[FieldLeft];
            final int lProb = lOutEProbField.sourcePart.prob.probabilityArr[DirProbLeft];
            final ProbField lInPProbField = probCell.probCellState[targetState].pProbFieldArr[FieldLeft];
            lInPProbField.field = (eol * lProb) / Max_Probability;
            lInPProbField.sourcePart = new Part(Max_Probability, DirProbSize);
            lInPProbField.sourcePart.field = Max_PField;
         }
         {
            final ProbField rOutEProbField = probCell.probCellState[OutState].eProbFieldArr[FieldRight];
            final int rProb = rOutEProbField.sourcePart.prob.probabilityArr[DirProbRight];
            final ProbField rInPProbField = probCell.probCellState[targetState].pProbFieldArr[FieldRight];
            rInPProbField.field = (eor * rProb) / Max_Probability;
            rInPProbField.sourcePart = new Part(Max_Probability, DirProbSize);
            rInPProbField.sourcePart.field = Max_PField;
         }
      }
   }

   public static void calcPFieldEOut2PIn_innerCell(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      final int eol = probCell.probCellState[OutState].eProbFieldArr[FieldLeft].field;
      final int eor = probCell.probCellState[OutState].eProbFieldArr[FieldRight].field;

      if ((eol > 0) && (eor > 0)) {
         {
            final ProbField lOutEProbField = probCell.probCellState[OutState].eProbFieldArr[FieldLeft];
            final int lProb = lOutEProbField.sourcePart.prob.probabilityArr[DirProbLeft];
            final ProbField lInPProbField = probCell.probCellState[InState].pProbFieldArr[FieldLeft];
            lInPProbField.field = (eol * lProb) / Max_Probability;
            lInPProbField.sourcePart = new Part(Max_Probability, DirProbSize);
            lInPProbField.sourcePart.field = Max_PField;
         }
         {
            final ProbField rOutEProbField = probCell.probCellState[OutState].eProbFieldArr[FieldRight];
            final int rProb = rOutEProbField.sourcePart.prob.probabilityArr[DirProbRight];
            final ProbField rInPProbField = probCell.probCellState[InState].pProbFieldArr[FieldRight];
            rInPProbField.field = (eor * rProb) / Max_Probability;
            rInPProbField.sourcePart = new Part(Max_Probability, DirProbSize);
            rInPProbField.sourcePart.field = Max_PField;
         }
      }
      /*
      final int reol = rProbCell.eProbFieldArr[FieldLeft].field;
      final int reor = rProbCell.eProbFieldArr[FieldRight].field;
      final int leol = lProbCell.eProbFieldArr[FieldLeft].field;
      final int leor = lProbCell.eProbFieldArr[FieldRight].field;
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
      probCell.pProbFieldArr[FieldLeft].field += pil;
      probCell.pProbFieldArr[FieldRight].field += pir;
      probCell.pProbFieldArr[FieldLeft].field += rProbCell.pProbFieldArr[FieldLeft].field;
      probCell.pProbFieldArr[FieldRight].field += lProbCell.pProbFieldArr[FieldRight].field;
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
         //probCell.eProbField.field = 0;
         //clearArr(probCell.eProbField.inFieldArr);
         //probCell.pProbField.field = 0;
         //clearArr(probCell.pProbField.inFieldArr);
      }
   */
   public static void clearFieldsIn(final ProbCell probCell) {
      if (Objects.nonNull(probCell.eInPart)) {
         probCell.eInPart = null;
      }
      for (int pos = 0; pos < probCell.probCellState[InState].eProbFieldArr.length; pos++) {
         probCell.probCellState[InState].eProbFieldArr[pos].field = 0;
         probCell.probCellState[InState].eProbFieldArr[pos].sourcePart = null;
      }
      if (Objects.nonNull(probCell.pInPart)) {
         probCell.eInPart = null;
      }
      for (int pos = 0; pos < probCell.probCellState[InState].pProbFieldArr.length; pos++) {
         probCell.probCellState[InState].pProbFieldArr[pos].field = 0;
         probCell.probCellState[InState].pProbFieldArr[pos].sourcePart = null;
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
      probCell.probCellState[OutState].eProbFieldArr[FieldLeft].field = probCell.probCellState[InState].eProbFieldArr[FieldLeft].field;
      probCell.probCellState[OutState].eProbFieldArr[FieldLeft].sourcePart = probCell.probCellState[InState].eProbFieldArr[FieldLeft].sourcePart;
      probCell.probCellState[OutState].eProbFieldArr[FieldRight].field = probCell.probCellState[InState].eProbFieldArr[FieldRight].field;
      probCell.probCellState[OutState].eProbFieldArr[FieldRight].sourcePart = probCell.probCellState[InState].eProbFieldArr[FieldRight].sourcePart;

      calcEFieldSourceIn2Out(probCell, lProbCell, rProbCell);
   }

   static void calcPFieldIn2Out(final ProbCell probCell) {
      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].field = probCell.probCellState[InState].pProbFieldArr[FieldLeft].field;
      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].sourcePart = probCell.probCellState[InState].pProbFieldArr[FieldLeft].sourcePart;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].field = probCell.probCellState[InState].pProbFieldArr[FieldRight].field;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].sourcePart = probCell.probCellState[InState].pProbFieldArr[FieldRight].sourcePart;
   }

   private static int Field_Divisor = 10; // 2;

   public static void calcEFieldSourceIn2Out(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      if (Objects.nonNull(probCell.eOutPart)) {
         //probField.field = probField.field;
         // probCell.ePart.field = probCell.ePart.field;
      }
      //probCell.outEField = probCell.inEField + lProbCell.inEField/2 - rProbCell.inEField/2;
      //probCell.outEFieldArr[EFieldLeft] = probCell.inEFieldArr[EFieldLeft] + rProbCell.inEFieldArr[EFieldLeft]/2;
      //probCell.outEFieldArr[EFieldRight] = probCell.inEFieldArr[EFieldRight] + lProbCell.inEFieldArr[EFieldRight]/2;

      //probField.outFieldArr[FieldLeft] = probField.field + rProbField.field / Field_Divisor;
      //probField.outFieldArr[FieldRight] = probField.field + lProbField.field / Field_Divisor;
      //probField.outFieldArr[FieldLeft] += (rProbField.field * rProbCell.inProb.probabilityArr[DirProbLeft]) / Max_Probability;
      //probField.outFieldArr[FieldRight] += (lProbField.field * lProbCell.inProb.probabilityArr[DirProbRight]) / Max_Probability;

      //if (rProbField.field > 0) {
      if (Objects.nonNull(rProbCell.eOutPart)) {
         //probField.outFieldArr[FieldLeft] = rProbField.field;
         //probField.outFieldArr[FieldLeft] = rProbCell.ePart.field;
         probCell.probCellState[OutState].eProbFieldArr[FieldLeft].field = rProbCell.eOutPart.field;
         probCell.probCellState[OutState].eProbFieldArr[FieldLeft].sourcePart = rProbCell.eOutPart;
      }
      //if (lProbField.field > 0) {
      if (Objects.nonNull(lProbCell.eOutPart)) {
         //probField.outFieldArr[FieldRight] = lProbField.field;
         //probField.outFieldArr[FieldRight] = lProbCell.ePart.field;
         probCell.probCellState[OutState].eProbFieldArr[FieldRight].field = lProbCell.eOutPart.field;
         probCell.probCellState[OutState].eProbFieldArr[FieldRight].sourcePart = lProbCell.eOutPart;
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
      probCell.probCellState[InState].eProbFieldArr[FieldLeft].field = 0;
      probCell.probCellState[InState].eProbFieldArr[FieldRight].field = 0;
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
      probField.field += probField.field - (probField.field - bProbField.field);
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
            if ((probCell.probCellState[OutState].pProbFieldArr[FieldRight].field > 0)) {
               ProbCellServiceUtils.calcImpulse(probCell.eOutPart.prob, probCell.eOutPart.prob, probCell);
               probCell.probCellState[OutState].pProbFieldArr[FieldRight].field = 0;
               probCell.probCellState[OutState].pProbFieldArr[FieldRight].sourcePart = null;
               // TODO P Event Remove  !!!
               ret = true;
            } else {
               // c is p-Field?  b: a:?   b:?  c:<-
               if ((probCell.probCellState[OutState].pProbFieldArr[FieldLeft].field > 0)) {
                  ProbCellServiceUtils.calcImpulse(probCell.eOutPart.prob, probCell.eOutPart.prob, probCell);
                  probCell.probCellState[OutState].pProbFieldArr[FieldLeft].field = 0;
                  probCell.probCellState[OutState].pProbFieldArr[FieldLeft].sourcePart = null;
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

/*
   static void copyOut2In(final ProbCell probCell) {
      final Probability inProb = probCell.inProb;
      final Probability outProb = probCell.outProb;

      copyArr(inProb.probabilityArr, outProb.probabilityArr);

      //copyFieldOut2In(probCell.eProbField);
      if (Objects.nonNull(probCell.ePart)) {
         //probCell.ePart.field = probCell.ePart.field;
      }
      //copyFieldOut2In(probCell.pProbField);
      if (Objects.nonNull(probCell.pPart)) {
         //probCell.pPart.field = probCell.pPart.field;
      }
   }
 */
   /*
   private static void copyFieldOut2In(final ProbField probField) {
      probField.field = probField.field;
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
