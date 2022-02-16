package de.schmiereck.col;

import static de.schmiereck.col.prob.services.ProbCellService.DirProbLeft;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbSize;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;
import static de.schmiereck.col.prob.services.ProbCellService.Max_Probability;
import static de.schmiereck.col.prob.services.ProbCellServiceUtils.printProbLine;
import static de.schmiereck.col.prob.services.ProbUniverseService.calc;

import de.schmiereck.col.prob.model.Part;
import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbUniverse;
import de.schmiereck.col.prob.services.ProbUniverseService;

public class MainProb {

   //public static final int universeSize = 1*2*3 * 2;
   public static final int universeSize = 1*2*3 * 2 * 2;

   // TODO Elektron in Objekt auslagern das mit dem E-Feld verknüpft wird, um den Betrag (Impuls) für das P-Feld auszulesen.
   public static void main(String[] args) {
      //----------------------------------------------------------------------------------------------------------------
      final ProbUniverse probUniverse = new ProbUniverse(universeSize);

      ProbUniverseService.init(probUniverse);
      {
         final ProbCell probCell = probUniverse.probCellArr[0];

         //probCell.outProb.probabilityArr[DirProbLeft]    =  0;
         //probCell.outProb.probabilityArr[DirProbStay]    = 100;//70;
         //probCell.outProb.probabilityArr[DirProbRight]   = 0;//30;

         probCell.eOutPart = new Part(Max_Probability, DirProbSize);
         probCell.eOutPart.prob.probabilityArr[DirProbLeft]    =  0;
         probCell.eOutPart.prob.probabilityArr[DirProbStay]    = 70;
         probCell.eOutPart.prob.probabilityArr[DirProbRight]   = 30;

         probCell.eOutPart.field = 100;
      }
      {
         final ProbCell probCell = probUniverse.probCellArr[11];

         //probCell.outProb.probabilityArr[DirProbLeft]    =  0;
         //probCell.outProb.probabilityArr[DirProbStay]    = 100;//70;
         //probCell.outProb.probabilityArr[DirProbRight]   = 0;//30;

         probCell.eOutPart = new Part(Max_Probability, DirProbSize);
         probCell.eOutPart.prob.probabilityArr[DirProbLeft]    = 20;
         probCell.eOutPart.prob.probabilityArr[DirProbStay]    = 80;
         probCell.eOutPart.prob.probabilityArr[DirProbRight]   =  0;

         probCell.eOutPart.field = 100;
      }
      ProbUniverseService.calcInit(probUniverse);
      //----------------------------------------------------------------------------------------------------------------
      for (int cnt = 0; cnt <= 12; cnt++) {
         printProbLine(cnt, probUniverse);
         calc(probUniverse);
      }
      //----------------------------------------------------------------------------------------------------------------
   }
}
