package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbCell.EFieldLeft;
import static de.schmiereck.col.prob.model.ProbCell.EFieldRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;
import static de.schmiereck.col.prob.services.ProbCellService.Max_Probability;
import static de.schmiereck.col.prob.services.ProbCellService.ProbSize;
import static de.schmiereck.col.prob.services.ProbCellService.calcInProb;
import static de.schmiereck.col.prob.services.ProbCellService.calcNextOutProb;
import static de.schmiereck.col.prob.services.ProbCellService.calcOut;

import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbUniverse;
import de.schmiereck.col.services.ProbabilityService;

public class ProbUniverseService {
   public static void init(final ProbUniverse probUniverse) {
      for (int pos = 0; pos < probUniverse.probCellArr.length; pos++) {
         final ProbCell probCell = new ProbCell();

         //probCell.eProb = new Probability(Max_Probability, EProbSize);
         probCell.inEField[EFieldLeft] = 0;
         probCell.inEField[EFieldRight] = 0;
         probCell.outEField[EFieldLeft] = 0;
         probCell.outEField[EFieldRight] = 0;
         probCell.inProb = new Probability(Max_Probability, ProbSize);
         probCell.outProb = new Probability(Max_Probability, ProbSize);

         probCell.outProb.probabilityArr[DirProbStay]    = 100;

         probUniverse.probCellArr[pos] = probCell;
      }
   }

   public static void calcInit(final ProbUniverse probUniverse) {
      for (int pos = 0; pos < probUniverse.probCellArr.length; pos++) {
         final ProbCell probCell = probUniverse.probCellArr[pos];

         ProbabilityService.calcInit(probCell.outProb);
      }
   }

   public static void calc(final ProbUniverse probUniverse) {
      calcNextOutProb(probUniverse);
      calcInProb(probUniverse);
      calcOut(probUniverse);
   }
}
