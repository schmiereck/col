package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbField.FieldLeft;
import static de.schmiereck.col.prob.model.ProbField.FieldRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbLeft;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;
import static de.schmiereck.col.prob.services.ProbCellService.Max_Probability;
import static de.schmiereck.col.prob.services.ProbCellServiceUtils.printProbLine;
import static de.schmiereck.col.prob.services.ProbUniverseService.calc;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbUniverse;

import org.junit.jupiter.api.Test;

public class Test_ProbUniverseService_WHEN_calc_is_called {

   @Test
   void GIVEN_stay100_THEN_stay100() {
      // Arrange
      //    --> X
      final ProbUniverse probUniverse = new ProbUniverse(7);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 3,    0, 100, 0);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);

      // Assert
      printProbLine(1, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0,  0, 0, 0);
      assertProb(probUniverse, 1,   0, 100, 0,  0, 0, 0);
      assertProb(probUniverse, 2,   0, 100, 0,  100, 0, 0);
      assertProb(probUniverse, 3,   0, 100, 0,  0, 100, 0);
      assertProb(probUniverse, 4,   0, 100, 0,  0, 0, 100);
      assertProb(probUniverse, 5,   0, 100, 0,  0, 0, 0);
      assertProb(probUniverse, 6,   0, 100, 0,  0, 0, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0,  0, 0, 0);
      assertProb(probUniverse, 1,   0, 100, 0,  10, 0, 0);
      assertProb(probUniverse, 2,   0, 100, 0,  100, 0, 0);
      assertProb(probUniverse, 3,   0, 100, 0,  0, 100, 0);
      assertProb(probUniverse, 4,   0, 100, 0,  0, 0, 100);
      assertProb(probUniverse, 5,   0, 100, 0,  0, 0, 10);
      assertProb(probUniverse, 6,   0, 100, 0,  0, 0, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0,  1, 0, 0);
      assertProb(probUniverse, 1,   0, 100, 0,  10, 0, 0);
      assertProb(probUniverse, 2,   0, 100, 0,  100, 0, 0);
      assertProb(probUniverse, 3,   0, 100, 0,  0, 100, 0);
      assertProb(probUniverse, 4,   0, 100, 0,  0, 0, 100);
      assertProb(probUniverse, 5,   0, 100, 0,  0, 0, 10);
      assertProb(probUniverse, 6,   0, 100, 0,  0, 0, 1);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(4, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0,  1, 0, 0);
      assertProb(probUniverse, 1,   0, 100, 0,  10, 0, 0);
      assertProb(probUniverse, 2,   0, 100, 0,  100, 0, 0);
      assertProb(probUniverse, 3,   0, 100, 0,  0, 100, 0);
      assertProb(probUniverse, 4,   0, 100, 0,  0, 0, 100);
      assertProb(probUniverse, 5,   0, 100, 0,  0, 0, 10);
      assertProb(probUniverse, 6,   0, 100, 0,  0, 0, 1);
   }

   @Test
   void GIVEN_right100_to_stay100_THEN_right100_moved() {
      // Arrange
      //    --> X
      final ProbUniverse probUniverse = new ProbUniverse(8);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 3,    0, 0, 100);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);
      // Assert
      printProbLine(1, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0,  0, 0, 0);
      assertProb(probUniverse, 1,   0, 100, 0,  0, 0, 0);
      assertProb(probUniverse, 2,   0, 100, 0,  0, 0, 0);
      assertProb(probUniverse, 3,   0, 100, 0,  100, 0, 0);
      assertProb(probUniverse, 4,   0, 0, 100,  0, 100, 0);
      assertProb(probUniverse, 5,   0, 100, 0,  0, 0, 100);
      assertProb(probUniverse, 6,   0, 100, 0,  0, 0, 0);
      assertProb(probUniverse, 7,   0, 100, 0,  0, 0, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0,  0, 0, 0);
      assertProb(probUniverse, 1,   0, 100, 0,  0, 0, 0);
      assertProb(probUniverse, 2,   0, 100, 0,  10, 0, 0);
      assertProb(probUniverse, 3,   0, 100, 0,  0, 0, 0);
      assertProb(probUniverse, 4,   0, 100, 0,  100, 0, 0);
      assertProb(probUniverse, 5,   0, 0, 100,  0, 100, 0);
      assertProb(probUniverse, 6,   0, 100, 0,  0, 0, 110);
      assertProb(probUniverse, 7,   0, 100, 0,  0, 0, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0,  0, 0, 0);
      assertProb(probUniverse, 1,   0, 100, 0,  1, 0, 0);
      assertProb(probUniverse, 2,   0, 100, 0,  0, 0, 0);
      assertProb(probUniverse, 3,   0, 100, 0,  10, 0, 0);
      assertProb(probUniverse, 4,   0, 100, 0,  0, 0, 0);
      assertProb(probUniverse, 5,   0, 100, 0,  100, 0, 0);
      assertProb(probUniverse, 6,   0, 0, 100,  0, 100, 0);
      assertProb(probUniverse, 7,   0, 100, 0,  0, 0, 111);
   }

   @Test
   void GIVEN_left100_to_stay100_THEN_left100_moved() {
      // Arrange
      //    --> X
      final ProbUniverse probUniverse = new ProbUniverse(4);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 2,    100, 0, 0);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);

      // Assert
      printProbLine(1, probUniverse);
      assertProb(probUniverse, 2,   0, 100, 0,  0, 0, 100);
      assertProb(probUniverse, 1,   100, 0, 0,  0, 100, 0);
      assertProb(probUniverse, 0,   0, 100, 0,  100, 0, 0);
   }

   @Test
   void GIVEN_right70_to_stay100_THEN_right70_moved() {
      // Arrange
      //    --> X
      final ProbUniverse probUniverse = new ProbUniverse(8);

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
   void GIVEN_left70_to_stay100_THEN_right70_moved() {
      // Arrange
      //    --> X
      final ProbUniverse probUniverse = new ProbUniverse(8);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 5,    70, 30, 0);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);

      // Assert
      printProbLine(1, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   70, 30, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   70, 30, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   0, 100, 0);
      assertProb(probUniverse, 3,   70, 30, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(4, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   0, 100, 0);
      assertProb(probUniverse, 3,   70, 30, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(5, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   0, 100, 0);
      assertProb(probUniverse, 3,   0, 100, 0);
      assertProb(probUniverse, 2,   70, 30, 0);
   }

   @Test
   void GIVEN_left20_to_stay100_THEN_right70_moved() {
      // Arrange
      //    --> X
      final ProbUniverse probUniverse = new ProbUniverse(8);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 5,    20, 80, 0);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);
      // Assert
      printProbLine(1, probUniverse);
      assertProb(probUniverse, 5,   20, 80, 0);
      assertProb(probUniverse, 4,   0, 100, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProb(probUniverse, 5,   20, 80, 0);
      assertProb(probUniverse, 4,   0, 100, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProb(probUniverse, 5,   20, 80, 0);
      assertProb(probUniverse, 4,   0, 100, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(4, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   20, 80, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(5, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   20, 80, 0);
      assertProb(probUniverse, 3,   0, 100, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(6, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   20, 80, 0);
      assertProb(probUniverse, 3,   0, 100, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(7, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   20, 80, 0);
      assertProb(probUniverse, 3,   0, 100, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(8, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   20, 80, 0);
      assertProb(probUniverse, 3,   0, 100, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(9, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   0, 100, 0);
      assertProb(probUniverse, 3,   20, 80, 0);
   }

   @Test
   void GIVEN_right30_to_right30_THEN_right30_moved() {
      // Arrange
      //    -> ->
      final ProbUniverse probUniverse = new ProbUniverse(8);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 1,    0, 70, 30);
      initProbCell(probUniverse, 3,    0, 70, 30);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);
      printProbLine(1, probUniverse);
      calc(probUniverse);
      printProbLine(2, probUniverse);
      calc(probUniverse);

      // Assert
      printProbLine(3, probUniverse);
      assertProb(probUniverse, 0,   0, 70, 30);
      assertProb(probUniverse, 1,   0, 100, 0);
      assertProb(probUniverse, 2,   0, 70, 30);
   }

   @Test
   void GIVEN_left30_to_left30_THEN_right30_moved() {
      // Arrange
      //    -> ->
      final ProbUniverse probUniverse = new ProbUniverse(8);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 6,    30, 70, 0);
      initProbCell(probUniverse, 4,    30, 70, 0);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);
      printProbLine(1, probUniverse);
      calc(probUniverse);
      printProbLine(2, probUniverse);
      calc(probUniverse);

      // Assert
      printProbLine(3, probUniverse);
      assertProb(probUniverse, 5,   30, 70, 0);
      assertProb(probUniverse, 4,   0, 100, 0);
      assertProb(probUniverse, 3,   30, 70, 0);
   }

   @Test
   void GIVEN_right80_to_right20_THEN_right30_moved() {
      // Arrange
      //    -> ->
      final ProbUniverse probUniverse = new ProbUniverse(8);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 1, 0, 20, 80);
      initProbCell(probUniverse, 3, 0, 80, 20);
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
      assertProb(probUniverse, 0,   0, 100, 0);
      assertProb(probUniverse, 1,   0, 80, 20);
      assertProb(probUniverse, 2,   0, 20, 80);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(4, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0);
      assertProb(probUniverse, 1,   0, 80, 20);
      assertProb(probUniverse, 2,   0, 100, 0);
      assertProb(probUniverse, 3,   0, 20, 80);

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

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(7, probUniverse);
      assertProb(probUniverse, 0,   0, 100, 0);
      assertProb(probUniverse, 1,   0, 80, 20);
      assertProb(probUniverse, 2,   0, 100, 0);
      assertProb(probUniverse, 3,   0, 100, 0);
      assertProb(probUniverse, 4,   0, 20, 80);
      assertProb(probUniverse, 5,   0, 100, 0);
   }

   @Test
   void GIVEN_left80_to_left20_THEN_right30_moved() {
      // Arrange
      //    -> ->
      final ProbUniverse probUniverse = new ProbUniverse(8);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 6,    80, 20, 0);
      initProbCell(probUniverse, 4,    20, 80, 0);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);
      // Assert
      printProbLine(1, probUniverse);
      assertProb(probUniverse, 5,   20, 80, 0);
      assertProb(probUniverse, 4,   80, 20, 0);
      assertProb(probUniverse, 3,   0, 100, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProb(probUniverse, 5,   20, 80, 0);
      assertProb(probUniverse, 4,   0, 100, 0);
      assertProb(probUniverse, 3,   80, 20, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProb(probUniverse, 5,   20, 80, 0);
      assertProb(probUniverse, 4,   0, 100, 0);
      assertProb(probUniverse, 3,   80, 20, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(4, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   20, 80, 0);
      assertProb(probUniverse, 3,   0, 100, 0);
      assertProb(probUniverse, 2,   80, 20, 0);
      assertProb(probUniverse, 1,   0, 100, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(5, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   20, 80, 0);
      assertProb(probUniverse, 3,   0, 100, 0);
      assertProb(probUniverse, 2,   80, 20, 0);
      assertProb(probUniverse, 1,   0, 100, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(6, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   20, 80, 0);
      assertProb(probUniverse, 3,   0, 100, 0);
      assertProb(probUniverse, 2,   0, 100, 0);
      assertProb(probUniverse, 1,   80, 20, 0);
      assertProb(probUniverse, 0,   0, 100, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(7, probUniverse);
      assertProb(probUniverse, 5,   0, 100, 0);
      assertProb(probUniverse, 4,   0, 100, 0);
      assertProb(probUniverse, 3,   20, 80, 0);
      assertProb(probUniverse, 2,   0, 100, 0);
      assertProb(probUniverse, 1,   80, 20, 0);
      assertProb(probUniverse, 0,   0, 100, 0);
   }

   @Test
   void GIVEN_pos2_left70_to_pos3_right100_THEN_right70_moved() {
      // Arrange
      //    --> X
      final ProbUniverse probUniverse = new ProbUniverse(8);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 2,    0, 30, 70);
      initProbCell(probUniverse, 3,    70, 30, 0);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);
      // Assert
      printProbLine(1, probUniverse);
      assertProb(probUniverse, 2,    70, 30, 0);
      assertProb(probUniverse, 3,    0, 30, 70);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProb(probUniverse, 2,    70, 30, 0);
      assertProb(probUniverse, 3,    0, 30, 70);
   }

   @Test
   void GIVEN_pos2_left70_to_pos6_right70_THEN_reflection_with_eField() {
      // Arrange
      final ProbUniverse probUniverse = new ProbUniverse(8);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 2,    0, 30, 70);
      initProbCell(probUniverse, 6,    70, 30, 0);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);
      // Assert
      printProbLine(1, probUniverse);
      assertProb(probUniverse, 3,    0, 30, 70);
      assertProb(probUniverse, 5,    70, 30, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProb(probUniverse, 3,    30, 70, 0);
      assertProb(probUniverse, 4,    0, 100, 0);
      assertProb(probUniverse, 5,    0, 70, 30);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProb(probUniverse, 3,    100, 0, 0);
      assertProb(probUniverse, 4,    0, 100, 0);
      assertProb(probUniverse, 5,    0, 0, 100);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(4, probUniverse);
      assertProb(probUniverse, 2,    100, 0, 0);
      assertProb(probUniverse, 3,    0, 100, 0);
      assertProb(probUniverse, 4,    0, 100, 0);
      assertProb(probUniverse, 5,    0, 100, 0);
      assertProb(probUniverse, 6,    0, 0, 100);
   }

   @Test
   void GIVEN_pos1_left70_to_pos6_right70_THEN_reflection_with_eField() {
      // Arrange
      final ProbUniverse probUniverse = new ProbUniverse(8);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 1,    0, 30, 70);
      initProbCell(probUniverse, 6,    70, 30, 0);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);
      // Assert
      printProbLine(1, probUniverse);
      assertProb(probUniverse, 2,    0, 30, 70);
      assertProb(probUniverse, 5,    70, 30, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProbP(probUniverse, 2,    0, 30, 70,   10, 0, 0);
      assertProbP(probUniverse, 5,    70, 30, 0,   0, 0, 10);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProb(probUniverse, 2,    0, 37, 63);
      assertProb(probUniverse, 5,    63, 37, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(4, probUniverse);
      assertProb(probUniverse, 2,    0, 37, 63);
      assertProb(probUniverse, 5,    63, 37, 0);
   }

   private static void assertProb(final ProbUniverse probUniverse, final int pos, final int lp, final int sp, final int rp) {
      assertEquals(lp, probUniverse.probCellArr[pos].outProb.probabilityArr[DirProbLeft], String.format("Left: pos:%d", pos));
      assertEquals(sp, probUniverse.probCellArr[pos].outProb.probabilityArr[DirProbStay], String.format("Stay: pos:%d", pos));
      assertEquals(rp, probUniverse.probCellArr[pos].outProb.probabilityArr[DirProbRight], String.format("Right: pos:%d", pos));
   }

   private static void assertProb(final ProbUniverse probUniverse, final int pos, final int lp, final int sp, final int rp,
                                  final int lEField, final int eField, final int rEField) {
      assertEquals(lp, probUniverse.probCellArr[pos].outProb.probabilityArr[DirProbLeft], String.format("Left: pos:%d", pos));
      assertEquals(sp, probUniverse.probCellArr[pos].outProb.probabilityArr[DirProbStay], String.format("Stay: pos:%d", pos));
      assertEquals(rp, probUniverse.probCellArr[pos].outProb.probabilityArr[DirProbRight], String.format("Right: pos:%d", pos));
      assertEquals(lEField, probUniverse.probCellArr[pos].eProbField.outFieldArr[FieldLeft], String.format("eFieldLeft: pos:%d", pos));
      assertEquals(eField, probUniverse.probCellArr[pos].eProbField.outField, String.format("eField: pos:%d", pos));
      assertEquals(rEField, probUniverse.probCellArr[pos].eProbField.outFieldArr[FieldRight], String.format("eFieldRight: pos:%d", pos));
   }

   private static void assertProbP(final ProbUniverse probUniverse, final int pos, final int lp, final int sp, final int rp,
                                   final int lPField, final int pField, final int rPField) {
      assertEquals(lp, probUniverse.probCellArr[pos].outProb.probabilityArr[DirProbLeft], String.format("Left: pos:%d", pos));
      assertEquals(sp, probUniverse.probCellArr[pos].outProb.probabilityArr[DirProbStay], String.format("Stay: pos:%d", pos));
      assertEquals(rp, probUniverse.probCellArr[pos].outProb.probabilityArr[DirProbRight], String.format("Right: pos:%d", pos));
      assertEquals(lPField, probUniverse.probCellArr[pos].pProbField.outFieldArr[FieldLeft], String.format("pFieldLeft: pos:%d", pos));
      assertEquals(pField, probUniverse.probCellArr[pos].pProbField.outField, String.format("pField: pos:%d", pos));
      assertEquals(rPField, probUniverse.probCellArr[pos].pProbField.outFieldArr[FieldRight], String.format("pFieldRight: pos:%d", pos));
   }

   private static void initProbCell(final ProbUniverse probUniverse, final int pos, final int lp, final int sp, final int rp) {
      final ProbCell probCell = probUniverse.probCellArr[pos];

      probCell.outProb.probabilityArr[DirProbStay]    = sp;
      probCell.outProb.probabilityArr[DirProbLeft]    = lp;
      probCell.outProb.probabilityArr[DirProbRight]   = rp;
      probCell.eProbField.outField = Max_Probability;
      //probCell.outEFieldArr[EFieldLeft] = Max_Probability;
      //probCell.outEFieldArr[EFieldRight] = Max_Probability;
   }
}
