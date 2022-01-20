package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;
import static de.schmiereck.col.prob.services.ProbCellService.EProbSize;
import static de.schmiereck.col.prob.services.ProbCellService.Max_Probability;
import static de.schmiereck.col.prob.services.ProbCellService.ProbSize;
import static de.schmiereck.col.prob.services.ProbCellService.calcInProb;
import static de.schmiereck.col.prob.services.ProbCellService.calcNextProb;
import static de.schmiereck.col.prob.services.ProbCellService.calcOut;
import static de.schmiereck.col.services.ProbabilityService.calcInit;
import static de.schmiereck.col.services.ProbabilityService.calcInit;

import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbUniverse;
import de.schmiereck.col.services.ProbabilityService;

public class ProbUniverseService {
   public static void init(final ProbUniverse probUniverse) {
      for (int pos = 0; pos < probUniverse.probCellArr.length; pos++) {
         final ProbCell probCell = new ProbCell();

         //probCell.eProb = new Probability(Max_Probability, EProbSize);
         probCell.inEField = 0;
         probCell.outEField = 0;
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
      calcNextProb(probUniverse);
      calcInProb(probUniverse);
      calcOut(probUniverse);
   }
}
