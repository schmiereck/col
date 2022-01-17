package de.schmiereck.col;

import static de.schmiereck.col.prob.services.ProbCellService.DirProbLeft;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;
import static de.schmiereck.col.prob.services.ProbCellService.Max_Probability;
import static de.schmiereck.col.prob.services.ProbCellService.ProbSize;
import static de.schmiereck.col.prob.services.ProbCellService.calcInProb;
import static de.schmiereck.col.prob.services.ProbCellService.calcNextProb;
import static de.schmiereck.col.prob.services.ProbCellService.calcOut;
import static de.schmiereck.col.prob.services.ProbCellService.printProbLine;
import static de.schmiereck.col.prob.services.ProbUniverseService.calc;
import static de.schmiereck.col.services.ProbabilityService.calcNext;
import static de.schmiereck.col.services.UniverseUtils.calcCellPos;

import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbUniverse;
import de.schmiereck.col.prob.services.ProbUniverseService;

public class MainProb {

   public static final int universeSize = 1*2*3;

   /**
    d=
    a-b
    a		b
    su	30	-30	60
    sd	70	30	40
    r	20	10	10
    s	80	-10	90
    l	0	0	0

    a-d		b+d
    a		b
    su	60		30
    sd	40		70
    r	10		20
    s	90		80
    l	0		0

    */
   public static void main(String[] args) {
      //----------------------------------------------------------------------------------------------------------------
      final ProbUniverse probUniverse = new ProbUniverse(universeSize);

      ProbUniverseService.init(probUniverse);
      {
         final ProbCell probCell = probUniverse.probCellArr[0];

         probCell.outProb.probabilityArr[DirProbStay]    = 70;
         probCell.outProb.probabilityArr[DirProbLeft]    =  0;
         probCell.outProb.probabilityArr[DirProbRight]   = 30;
      }
      ProbUniverseService.calcInit(probUniverse);
      //----------------------------------------------------------------------------------------------------------------
      for (int cnt = 0; cnt < 12; cnt++) {
         printProbLine(cnt, probUniverse);
         calc(probUniverse);
      }
      //----------------------------------------------------------------------------------------------------------------
   }
}
