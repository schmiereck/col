package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.services.ProbCellService.DirProbLeft;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;
import static de.schmiereck.col.prob.services.ProbCellService.printProbLine;
import static de.schmiereck.col.prob.services.ProbUniverseService.calc;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbUniverse;

import org.junit.jupiter.api.Test;

public class Test_ProbUniverseService_WHEN_calc_is_called {

   @Test
   void GIVEN_right70_to_stay100_THEN_right70_moved() {
      // Arrange
      final ProbUniverse probUniverse = new ProbUniverse(6);

      ProbUniverseService.init(probUniverse);
      {
         final ProbCell probCell = probUniverse.probCellArr[0];

         probCell.outProb.probabilityArr[DirProbStay]    = 30;
         probCell.outProb.probabilityArr[DirProbLeft]    =  0;
         probCell.outProb.probabilityArr[DirProbRight]   = 70;
      }
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);

      // Assert
      printProbLine(1, probUniverse);
      assertEquals(0, probUniverse.probCellArr[0].outProb.probabilityArr[DirProbLeft]);
      assertEquals(100, probUniverse.probCellArr[0].outProb.probabilityArr[DirProbStay]);
      assertEquals(0, probUniverse.probCellArr[0].outProb.probabilityArr[DirProbRight]);

      assertEquals(0, probUniverse.probCellArr[1].outProb.probabilityArr[DirProbLeft]);
      assertEquals(30, probUniverse.probCellArr[1].outProb.probabilityArr[DirProbStay]);
      assertEquals(70, probUniverse.probCellArr[1].outProb.probabilityArr[DirProbRight]);
   }
}
