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

   //    -> X

   //    --> ->
   //    -> -->
   //    -> <-
   //    --> <-
   //    -> <--
   //    X ->

   @Test
   void GIVEN_right70_to_stay100_THEN_right70_moved() {
      // Arrange
      //    --> X
      final ProbUniverse probUniverse = new ProbUniverse(6);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 0,    0, 30, 70);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);

      // Assert
      printProbLine(1, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0);
      assertProb(probUniverse, 1,   0, 30, 70);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0);
      assertProb(probUniverse, 1,   0, 30, 70);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0);
      assertProb(probUniverse, 1,   0, 100, 0);
      assertProb(probUniverse, 2,   0, 30, 70);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(4, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0);
      assertProb(probUniverse, 1,   0, 100, 0);
      assertProb(probUniverse, 2,   0, 30, 70);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(5, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0);
      assertProb(probUniverse, 1,   0, 100, 0);
      assertProb(probUniverse, 2,   0, 100, 0);
      assertProb(probUniverse, 3,   0, 30, 70);
   }

   @Test
   void GIVEN_right30_to_right30_THEN_right30_moved() {
      // Arrange
      //    -> ->
      final ProbUniverse probUniverse = new ProbUniverse(6);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 0, 0, 70, 30);
      initProbCell(probUniverse, 1, 0, 70, 30);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);
      printProbLine(1, probUniverse);
      calc(probUniverse);

      // Assert
      printProbLine(2, probUniverse);
      assertProb(probUniverse, 0,   0, 70, 30);
      assertProb(probUniverse, 1,   0, 100, 0);
      assertProb(probUniverse, 2,   0, 70, 30);
   }

   @Test
   void GIVEN_right80_to_right20_THEN_right30_moved() {
      // Arrange
      //    -> ->
      final ProbUniverse probUniverse = new ProbUniverse(6);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 0, 0, 20, 80);
      initProbCell(probUniverse, 1, 0, 80, 20);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);
      // Assert
      printProbLine(1, probUniverse);
      assertProb(probUniverse, 0,   0, 80, 20);
      assertProb(probUniverse, 1,   0, 20, 80);
      assertProb(probUniverse, 2,   0, 100, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProb(probUniverse, 0,   0, 80, 20);
      assertProb(probUniverse, 1,   0, 100, 0);
      assertProb(probUniverse, 2,   0, 20, 80);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProb(probUniverse, 0,   0, 80, 20);
      assertProb(probUniverse, 1,   0, 100, 0);
      assertProb(probUniverse, 2,   0, 20, 80);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(4, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0);
      assertProb(probUniverse, 1,   0, 80, 20);
      assertProb(probUniverse, 2,   0, 100, 0);
      assertProb(probUniverse, 3,   0, 20, 80);
      assertProb(probUniverse, 4,   0, 100, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(5, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0);
      assertProb(probUniverse, 1,   0, 80, 20);
      assertProb(probUniverse, 2,   0, 100, 0);
      assertProb(probUniverse, 3,   0, 20, 80);
      assertProb(probUniverse, 4,   0, 100, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(6, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0);
      assertProb(probUniverse, 1,   0, 80, 20);
      assertProb(probUniverse, 2,   0, 100, 0);
      assertProb(probUniverse, 3,   0, 100, 0);
      assertProb(probUniverse, 4,   0, 20, 80);
      assertProb(probUniverse, 5,   0, 100, 0);
   }

   private static void assertProb(final ProbUniverse probUniverse, final int pos, final int lp, final int sp, final int rp) {
      assertEquals(lp, probUniverse.probCellArr[pos].outProb.probabilityArr[DirProbLeft], String.format("Left: pos:%d", pos));
      assertEquals(sp, probUniverse.probCellArr[pos].outProb.probabilityArr[DirProbStay], String.format("Stay: pos:%d", pos));
      assertEquals(rp, probUniverse.probCellArr[pos].outProb.probabilityArr[DirProbRight], String.format("Right: pos:%d", pos));
   }

   private static void initProbCell(final ProbUniverse probUniverse, final int pos, final int lp, final int sp, final int rp) {
      final ProbCell probCell = probUniverse.probCellArr[pos];

      probCell.outProb.probabilityArr[DirProbStay]    = sp;
      probCell.outProb.probabilityArr[DirProbLeft]    = lp;
      probCell.outProb.probabilityArr[DirProbRight]   = rp;
   }
}
