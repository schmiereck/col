package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbField.FieldLeft;
import static de.schmiereck.col.prob.model.ProbField.FieldRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbSize;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;
import static de.schmiereck.col.prob.services.ProbCellService.Max_Probability;
import static de.schmiereck.col.services.UniverseUtils.calcCellPos;

import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbField;
import de.schmiereck.col.prob.model.ProbUniverse;

public class ProbUniverseService {
   public static void init(final ProbUniverse probUniverse) {
      for (int pos = 0; pos < probUniverse.probCellArr.length; pos++) {
         final ProbCell probCell = new ProbCell();

         initField(probCell.eProbField);
         initField(probCell.pProbField);
         probCell.inProb = new Probability(Max_Probability, DirProbSize);
         probCell.outProb = new Probability(Max_Probability, DirProbSize);

         probCell.outProb.probabilityArr[DirProbStay]    = 100;

         probUniverse.probCellArr[pos] = probCell;
      }
   }

   private static void initField(final ProbField probField) {
      //probCell.eProb = new Probability(Max_Probability, EProbSize);
      probField.inField = 0;
      probField.inFieldArr[FieldLeft] = 0;
      probField.inFieldArr[FieldRight] = 0;
      probField.outField = 0;
      probField.outFieldArr[FieldLeft] = 0;
      probField.outFieldArr[FieldRight] = 0;
   }

   public static void calcInit(final ProbUniverse probUniverse) {
      for (int pos = 0; pos < probUniverse.probCellArr.length; pos++) {
         final ProbCell probCell = probUniverse.probCellArr[pos];

         // in = out
         ProbCellService.calcInit(probCell);
      }
      /*
      for (int pos = 0; pos < probUniverse.probCellArr.length; pos++) {
         final ProbCell probCell = probUniverse.probCellArr[pos];
         final ProbCell lProbCell = probUniverse.probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probUniverse.probCellArr[calcCellPos(probUniverse. universeSize, pos + 1)];
         // out = in
         ProbCellService.calcOut(probCell, lProbCell, rProbCell);
      }
      for (int pos = 0; pos < probUniverse.probCellArr.length; pos++) {
         final ProbCell probCell = probUniverse.probCellArr[pos];
         final ProbCell lProbCell = probUniverse.probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probUniverse.probCellArr[calcCellPos(probUniverse. universeSize, pos + 1)];

         // in = out
         ProbCellService.calcInProbField(probCell, lProbCell, rProbCell);
      }
      for (int pos = 0; pos < probUniverse.probCellArr.length; pos++) {
         final ProbCell probCell = probUniverse.probCellArr[pos];
         final ProbCell lProbCell = probUniverse.probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probUniverse.probCellArr[calcCellPos(probUniverse. universeSize, pos + 1)];
         // out = in
         ProbCellService.calcOut(probCell, lProbCell, rProbCell);
      }

      for (int pos = 0; pos < probUniverse.probCellArr.length; pos++) {
         final ProbCell probCell = probUniverse.probCellArr[pos];
         ProbCellService.clearIn(probCell);
      }
      */
   }

   public static void calc(final ProbUniverse probUniverse) {
      clearFieldsIn(probUniverse);
      clearProbIn(probUniverse);

      // Prob:
      calcImpulseOut2Out(probUniverse);

      calcProbOut2In(probUniverse);
      calcProbIn2Out(probUniverse);

      calcNextProbOut2Out(probUniverse);

      // Fields:
      calcEFieldOut2In(probUniverse);
      calcEFieldIn2Out(probUniverse);

      calcPFieldEOut2PIn(probUniverse);
      calcPFieldIn2Out(probUniverse);
   }

   /**
    * 1. calcNextOutProb   next(out)
    */
   public static void calcNextProbOut2Out(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         ProbCellService.calcNextOutProb(probCell);
      }
   }

   /**
    * 2. calcInProb        in = calc(out)
    */
   public static void calcEFieldOut2In(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];

//         ProbCellService.calcInFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
         ProbCellService.calcFieldOut2In(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
         //ProbCellService.calcInProbField(probCell, lProbCell, rProbCell);
         //ProbCellService.calcInProb(probCell, lProbCell, rProbCell);
      }
   }

   public static void calcPFieldEOut2PIn(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];

//         ProbCellService.calcInFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
         //ProbCellService.calcInField(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
         ProbCellService.calcPFieldEOut2PIn(probCell, lProbCell, rProbCell);
         //ProbCellService.calcInProb(probCell, lProbCell, rProbCell);
      }
   }

   public static void calcImpulseOut2Out(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];

//         ProbCellService.calcInFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
         //ProbCellService.calcInField(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
         //ProbCellService.calcInProbField(probCell, lProbCell, rProbCell);
         ProbCellService.calcImpulseOut2Out(probCell);
      }
   }

   /**
    * 2. calcInProb        in = calc(out)
    */
   public static void calcProbOut2In(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];

//         ProbCellService.calcInFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
         //ProbCellService.calcInField(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
         //ProbCellService.calcInProbField(probCell, lProbCell, rProbCell);
         ProbCellService.calcProbOut2In(probCell, lProbCell, rProbCell);
      }
   }

   /**
    * 3. calcOut           out = in
    *                      in = 0
    */
   public static void calcEFieldIn2Out(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];
         //ProbCellService.calcOutProb(probCell, lProbCell, rProbCell);
         ProbCellService.calcEFieldIn2Out(probCell, lProbCell, rProbCell);
         //ProbCellService.calcOutPFields(probCell, lProbCell, rProbCell);
         //ProbCellService.calcOutFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
      }
   }

   public static void calcPFieldIn2Out(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];
         //ProbCellService.calcOutProb(probCell, lProbCell, rProbCell);
         //ProbCellService.calcOutEFields(probCell, lProbCell, rProbCell);
         ProbCellService.calcPFieldIn2Out(probCell);
         //ProbCellService.calcOutFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
      }
   }

   public static void clearFieldsIn(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         ProbCellService.clearFieldsIn(probCell);
      }
   }

   /**
    * 3. calcOut           out = in
    *                      in = 0
    */
   public static void calcProbIn2Out(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];
         ProbCellService.calcProbIn2Out(probCell, lProbCell, rProbCell);
         //ProbCellService.calcOutFields(probCell, lProbCell, rProbCell);
         //ProbCellService.calcOutFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
      }
   }

   public static void clearProbIn(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         ProbCellService.clearProbIn(probCell);
      }
   }
}
