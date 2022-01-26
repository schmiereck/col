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

   public static void calc_works(final ProbUniverse probUniverse) {
      calcInProb(probUniverse);
      calcInEFields(probUniverse);
      calcInPFields(probUniverse);

      calcOutProb(probUniverse);
      calcOutEFields(probUniverse);
      calcOutPFields(probUniverse);

      clearInProb(probUniverse);
      clearInFields(probUniverse);

      calcNextOutProb(probUniverse);
   }

   public static void calc(final ProbUniverse probUniverse) {

      calcInProb(probUniverse);
      calcOutProb(probUniverse);
      clearInProb(probUniverse);

      calcInEFields(probUniverse);
      calcOutEFields(probUniverse);
      calcInPFields(probUniverse);
      calcOutPFields(probUniverse);
      clearInFields(probUniverse);


      calcNextOutProb(probUniverse);
   }

   public static void calc_target(final ProbUniverse probUniverse) {
      // Fields:
      calcInEFields(probUniverse);
      calcInPFields(probUniverse);
      calcOutEFields(probUniverse);
      calcOutPFields(probUniverse);
      clearInFields(probUniverse);

      // Dir-Probality:
      calcInProb(probUniverse);
      calcOutProb(probUniverse);
      clearInProb(probUniverse);
      calcNextOutProb(probUniverse);
   }

   /**
    * 1. calcNextOutProb   next(out)
    */
   public static void calcNextOutProb(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         ProbCellService.calcNextOutProb(probCell);
      }
   }

   /**
    * 2. calcInProb        in = calc(out)
    */
   public static void calcInEFields(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];

//         ProbCellService.calcInFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
         ProbCellService.calcInField(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
         //ProbCellService.calcInProbField(probCell, lProbCell, rProbCell);
         //ProbCellService.calcInProb(probCell, lProbCell, rProbCell);
      }
   }

   public static void calcInPFields(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];

//         ProbCellService.calcInFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
         //ProbCellService.calcInField(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
         ProbCellService.calcInProbField(probCell, lProbCell, rProbCell);
         //ProbCellService.calcInProb(probCell, lProbCell, rProbCell);
      }
   }

   /**
    * 2. calcInProb        in = calc(out)
    */
   public static void calcInProb(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];

//         ProbCellService.calcInFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
         //ProbCellService.calcInField(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
         //ProbCellService.calcInProbField(probCell, lProbCell, rProbCell);
         ProbCellService.calcInProb(probCell, lProbCell, rProbCell);
      }
   }

   /**
    * 3. calcOut           out = in
    *                      in = 0
    */
   public static void calcOutEFields(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];
         //ProbCellService.calcOutProb(probCell, lProbCell, rProbCell);
         ProbCellService.calcOutEFields(probCell, lProbCell, rProbCell);
         //ProbCellService.calcOutPFields(probCell, lProbCell, rProbCell);
         //ProbCellService.calcOutFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
      }
   }

   public static void calcOutPFields(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];
         //ProbCellService.calcOutProb(probCell, lProbCell, rProbCell);
         //ProbCellService.calcOutEFields(probCell, lProbCell, rProbCell);
         ProbCellService.calcOutPFields(probCell, lProbCell, rProbCell);
         //ProbCellService.calcOutFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
      }
   }

   public static void clearInFields(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         ProbCellService.clearInFields(probCell);
      }
   }

   /**
    * 3. calcOut           out = in
    *                      in = 0
    */
   public static void calcOutProb(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbCell lProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probCellArr[calcCellPos(probUniverse.universeSize, pos + 1)];
         ProbCellService.calcOutProb(probCell, lProbCell, rProbCell);
         //ProbCellService.calcOutFields(probCell, lProbCell, rProbCell);
         //ProbCellService.calcOutFieldSource(probCell.eProbField, lProbCell.eProbField, rProbCell.eProbField);
      }
   }

   public static void clearInProb(final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         ProbCellService.clearInProb(probCell);
      }
   }
}
