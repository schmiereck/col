package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbField.FieldLeft;
import static de.schmiereck.col.prob.model.ProbField.FieldRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;
import static de.schmiereck.col.prob.services.ProbCellService.Max_Probability;
import static de.schmiereck.col.prob.services.ProbCellService.ProbSize;
import static de.schmiereck.col.prob.services.ProbCellService.calcInProb;
import static de.schmiereck.col.prob.services.ProbCellService.calcNextOutProb;
import static de.schmiereck.col.prob.services.ProbCellService.calcOut;
import static de.schmiereck.col.services.UniverseUtils.calcCellPos;

import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbField;
import de.schmiereck.col.prob.model.ProbUniverse;
import de.schmiereck.col.services.ProbabilityService;

public class ProbUniverseService {
   public static void init(final ProbUniverse probUniverse) {
      for (int pos = 0; pos < probUniverse.probCellArr.length; pos++) {
         final ProbCell probCell = new ProbCell();

         initField(probCell.eProbField);
         initField(probCell.pProbField);
         probCell.inProb = new Probability(Max_Probability, ProbSize);
         probCell.outProb = new Probability(Max_Probability, ProbSize);

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

         ProbCellService.calcInit(probCell);
      }
      for (int pos = 0; pos < probUniverse.probCellArr.length; pos++) {
         final ProbCell probCell = probUniverse.probCellArr[pos];
         final ProbCell lProbCell = probUniverse.probCellArr[calcCellPos(probUniverse.universeSize, pos - 1)];
         final ProbCell rProbCell = probUniverse.probCellArr[calcCellPos(probUniverse. universeSize, pos + 1)];
         ProbCellService.calcOut(probCell, lProbCell, rProbCell);
      }

      for (int pos = 0; pos < probUniverse.probCellArr.length; pos++) {
         final ProbCell probCell = probUniverse.probCellArr[pos];
         ProbCellService.clearIn(probCell);
      }
   }

   public static void calc(final ProbUniverse probUniverse) {
      calcNextOutProb(probUniverse);
      calcInProb(probUniverse);
      calcOut(probUniverse);
   }
}
