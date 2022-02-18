package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbCell.OutState;
import static de.schmiereck.col.prob.model.ProbField.FieldLeft;
import static de.schmiereck.col.prob.model.ProbField.FieldRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbLeft;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbSize;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;
import static de.schmiereck.col.prob.services.ProbCellService.Max_Probability;
import static de.schmiereck.col.prob.services.ProbCellServiceUtils.printProbLine;
import static de.schmiereck.col.prob.services.ProbUniverseService.calc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import de.schmiereck.col.prob.model.Part;
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
      assertProbEF(probUniverse, 0);
      assertProbEF(probUniverse, 1);
      assertProbEF(probUniverse, 2,   0, 100, 0,  100, 0, 0);
      assertProbEF(probUniverse, 3,   0, 100, 0,  0, 100, 0);
      assertProbEF(probUniverse, 4,   0, 100, 0,  0, 0, 100);
      assertProbEF(probUniverse, 5);
      assertProbEF(probUniverse, 6);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProbEF(probUniverse, 0);
      assertProbEF(probUniverse, 1,   0, 100, 0,  10, 0, 0);
      assertProbEF(probUniverse, 2,   0, 100, 0,  100, 0, 0);
      assertProbEF(probUniverse, 3,   0, 100, 0,  0, 100, 0);
      assertProbEF(probUniverse, 4,   0, 100, 0,  0, 0, 100);
      assertProbEF(probUniverse, 5,   0, 100, 0,  0, 0, 10);
      assertProbEF(probUniverse, 6);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProbEF(probUniverse, 0,   0, 100, 0,  1, 0, 0);
      assertProbEF(probUniverse, 1,   0, 100, 0,  10, 0, 0);
      assertProbEF(probUniverse, 2,   0, 100, 0,  100, 0, 0);
      assertProbEF(probUniverse, 3,   0, 100, 0,  0, 100, 0);
      assertProbEF(probUniverse, 4,   0, 100, 0,  0, 0, 100);
      assertProbEF(probUniverse, 5,   0, 100, 0,  0, 0, 10);
      assertProbEF(probUniverse, 6,   0, 100, 0,  0, 0, 1);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(4, probUniverse);
      assertProbEF(probUniverse, 0,   0, 100, 0,  1, 0, 0);
      assertProbEF(probUniverse, 1,   0, 100, 0,  10, 0, 0);
      assertProbEF(probUniverse, 2,   0, 100, 0,  100, 0, 0);
      assertProbEF(probUniverse, 3,   0, 100, 0,  0, 100, 0);
      assertProbEF(probUniverse, 4,   0, 100, 0,  0, 0, 100);
      assertProbEF(probUniverse, 5,   0, 100, 0,  0, 0, 10);
      assertProbEF(probUniverse, 6,   0, 100, 0,  0, 0, 1);
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
      assertProbEF(probUniverse, 0);
      assertProbEF(probUniverse, 1);
      assertProbEF(probUniverse, 2);
      assertProbEF(probUniverse, 3,   0, 100, 0,  100, 0, 0);
      assertProbEF(probUniverse, 4,   0, 0, 100,  0, 100, 0);
      assertProbEF(probUniverse, 5,   0, 100, 0,  0, 0, 100);
      assertProbEF(probUniverse, 6);
      assertProbEF(probUniverse, 7);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProbEF(probUniverse, 0);
      assertProbEF(probUniverse, 1);
      assertProbEF(probUniverse, 2,   0, 100, 0,  10, 0, 0);
      assertProbEF(probUniverse, 3);
      assertProbEF(probUniverse, 4,   0, 100, 0,  100, 0, 0);
      assertProbEF(probUniverse, 5,   0, 0, 100,  0, 100, 0);
      assertProbEF(probUniverse, 6,   0, 100, 0,  0, 0, 110);
      assertProbEF(probUniverse, 7);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProbEF(probUniverse, 0);
      assertProbEF(probUniverse, 1,   0, 100, 0,  1, 0, 0);
      assertProbEF(probUniverse, 2);
      assertProbEF(probUniverse, 3,   0, 100, 0,  10, 0, 0);
      assertProbEF(probUniverse, 4);
      assertProbEF(probUniverse, 5,   0, 100, 0,  100, 0, 0);
      assertProbEF(probUniverse, 6,   0, 0, 100,  0, 100, 0);
      assertProbEF(probUniverse, 7,   0, 100, 0,  0, 0, 111);
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
      assertProbEF(probUniverse, 2,   0, 100, 0,  0, 0, 100);
      assertProbEF(probUniverse, 1,   100, 0, 0,  0, 100, 0);
      assertProbEF(probUniverse, 0,   0, 100, 0,  100, 0, 0);
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
      assertProb(probUniverse, 4);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProb(probUniverse, 5,   20, 80, 0);
      assertProb(probUniverse, 4);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProb(probUniverse, 5,   20, 80, 0);
      assertProb(probUniverse, 4);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(4, probUniverse);
      assertProb(probUniverse, 5);
      assertProb(probUniverse, 4,   20, 80, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(5, probUniverse);
      assertProb(probUniverse, 5);
      assertProb(probUniverse, 4,   20, 80, 0);
      assertProb(probUniverse, 3);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(6, probUniverse);
      assertProb(probUniverse, 5);
      assertProb(probUniverse, 4,   20, 80, 0);
      assertProb(probUniverse, 3);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(7, probUniverse);
      assertProb(probUniverse, 5);
      assertProb(probUniverse, 4,   20, 80, 0);
      assertProb(probUniverse, 3);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(8, probUniverse);
      assertProb(probUniverse, 5);
      assertProb(probUniverse, 4,   20, 80, 0);
      assertProb(probUniverse, 3);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(9, probUniverse);
      assertProb(probUniverse, 5);
      assertProb(probUniverse, 4);
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
      assertProb(probUniverse, 1);
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
      assertProb(probUniverse, 4);
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
      assertProb(probUniverse, 2);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProb(probUniverse, 0,   0, 80, 20);
      assertProb(probUniverse, 1);
      assertProb(probUniverse, 2,   0, 20, 80);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProb(probUniverse, 0);
      assertProb(probUniverse, 1,   0, 80, 20);
      assertProb(probUniverse, 2,   0, 20, 80);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(4, probUniverse);
      assertProb(probUniverse, 0);
      assertProb(probUniverse, 1,   0, 80, 20);
      assertProb(probUniverse, 2);
      assertProb(probUniverse, 3,   0, 20, 80);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(5, probUniverse);
      assertProb(probUniverse, 0);
      assertProb(probUniverse, 1,   0, 80, 20);
      assertProb(probUniverse, 2);
      assertProb(probUniverse, 3,   0, 20, 80);
      assertProb(probUniverse, 4);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(6, probUniverse);
      assertProb(probUniverse, 0);
      assertProb(probUniverse, 1,   0, 80, 20);
      assertProb(probUniverse, 2);
      assertProb(probUniverse, 3);
      assertProb(probUniverse, 4,   0, 20, 80);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(7, probUniverse);
      assertProb(probUniverse, 0);
      assertProb(probUniverse, 1,   0, 80, 20);
      assertProb(probUniverse, 2);
      assertProb(probUniverse, 3);
      assertProb(probUniverse, 4,   0, 20, 80);
      assertProb(probUniverse, 5);
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
      assertProb(probUniverse, 3);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProb(probUniverse, 5,   20, 80, 0);
      assertProb(probUniverse, 4);
      assertProb(probUniverse, 3,   80, 20, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProb(probUniverse, 5,   20, 80, 0);
      assertProb(probUniverse, 4);
      assertProb(probUniverse, 3,   80, 20, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(4, probUniverse);
      assertProb(probUniverse, 5);
      assertProb(probUniverse, 4,   20, 80, 0);
      assertProb(probUniverse, 3);
      assertProb(probUniverse, 2,   80, 20, 0);
      assertProb(probUniverse, 1);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(5, probUniverse);
      assertProb(probUniverse, 5);
      assertProb(probUniverse, 4,   20, 80, 0);
      assertProb(probUniverse, 3);
      assertProb(probUniverse, 2,   80, 20, 0);
      assertProb(probUniverse, 1);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(6, probUniverse);
      assertProb(probUniverse, 5);
      assertProb(probUniverse, 4,   20, 80, 0);
      assertProb(probUniverse, 3);
      assertProb(probUniverse, 2);
      assertProb(probUniverse, 1,   80, 20, 0);
      assertProb(probUniverse, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(7, probUniverse);
      assertProb(probUniverse, 5);
      assertProb(probUniverse, 4);
      assertProb(probUniverse, 3,   20, 80, 0);
      assertProb(probUniverse, 2);
      assertProb(probUniverse, 1,   80, 20, 0);
      assertProb(probUniverse, 0);
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
      final ProbUniverse probUniverse = new ProbUniverse(12);

      ProbUniverseService.init(probUniverse);
      initProbCell(probUniverse, 1,    0, 30, 70);
      probUniverse.probCellArr[2].probCellState[OutState].eProbFieldArr[FieldRight].field = 100;
      probUniverse.probCellArr[3].probCellState[OutState].eProbFieldArr[FieldRight].field = 11;
      probUniverse.probCellArr[7].probCellState[OutState].eProbFieldArr[FieldLeft].field = 11;
      probUniverse.probCellArr[8].probCellState[OutState].eProbFieldArr[FieldLeft].field = 100;
      initProbCell(probUniverse, 9,    70, 30, 0);
      ProbUniverseService.calcInit(probUniverse);

      // Act
      printProbLine(0, probUniverse);
      calc(probUniverse);
      // Assert
      printProbLine(1, probUniverse);
      assertProb(probUniverse, 2,    0, 30, 70);
      assertProb(probUniverse, 8,    70, 30, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(2, probUniverse);
      assertProbP(probUniverse, 2,    0, 30, 70,   0, 0, 0);
      assertProbP(probUniverse, 8,    70, 30, 0,   0, 0, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(3, probUniverse);
      assertProbP(probUniverse, 3,    0, 30, 70,   0, 0, 0);
      assertProbP(probUniverse, 4,    0, 100, 0,   1, 0, 0);
      assertProbP(probUniverse, 6,    0, 100, 0,   0, 0, 1);
      assertProbP(probUniverse, 7,    70, 30, 0,   0, 0, 0);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(4, probUniverse);
      assertProbP(probUniverse, 3,    0, 30, 70,   1, 0, 0);
      assertProbP(probUniverse, 4,    0, 100, 0,   11, 0, 0);
      assertProbP(probUniverse, 6,    0, 100, 0,   0, 0, 11);
      assertProbP(probUniverse, 7,    70, 30, 0,   0, 0, 1);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(5, probUniverse);
      assertProbP(probUniverse, 4,    0, 31, 69,   110, 0, 0);
      assertProbP(probUniverse, 6,    69, 31, 0,   0, 0, 110);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(6, probUniverse);
      assertProb(probUniverse, 4,    31, 69, 0);
      assertProb(probUniverse, 6,    0, 69, 31);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(7, probUniverse);
      assertProb(probUniverse, 4,    31, 69, 0);
      assertProb(probUniverse, 6,    0, 69, 31);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(8, probUniverse);
      assertProb(probUniverse, 4,    31, 69, 0);
      assertProb(probUniverse, 6,    0, 69, 31);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(9, probUniverse);
      assertProb(probUniverse, 4,    31, 69, 0);
      assertProb(probUniverse, 6,    0, 69, 31);

      // Act
      calc(probUniverse);
      // Assert
      printProbLine(10, probUniverse);
      assertProb(probUniverse, 4,    31, 69, 0);
      assertProb(probUniverse, 6,    0, 69, 31);
   }

   private static void assertProb(final ProbUniverse probUniverse, final int pos, final int lp, final int sp, final int rp) {
      assertEquals(lp, probUniverse.probCellArr[pos].eOutPart.prob.probabilityArr[DirProbLeft], String.format("Left: pos:%d", pos));
      assertEquals(sp, probUniverse.probCellArr[pos].eOutPart.prob.probabilityArr[DirProbStay], String.format("Stay: pos:%d", pos));
      assertEquals(rp, probUniverse.probCellArr[pos].eOutPart.prob.probabilityArr[DirProbRight], String.format("Right: pos:%d", pos));
   }

   private static void assertProb(final ProbUniverse probUniverse, final int pos) {
      assertNull(probUniverse.probCellArr[pos].eOutPart);
   }

   private static void assertProbEF(final ProbUniverse probUniverse, final int pos, final int lp, final int sp, final int rp,
                                    final int lEField, final int eField, final int rEField) {
      assertEquals(lp, probUniverse.probCellArr[pos].eOutPart.prob.probabilityArr[DirProbLeft], String.format("Left: pos:%d", pos));
      assertEquals(sp, probUniverse.probCellArr[pos].eOutPart.prob.probabilityArr[DirProbStay], String.format("Stay: pos:%d", pos));
      assertEquals(rp, probUniverse.probCellArr[pos].eOutPart.prob.probabilityArr[DirProbRight], String.format("Right: pos:%d", pos));
      assertEquals(lEField, probUniverse.probCellArr[pos].probCellState[OutState].eProbFieldArr[FieldLeft].field, String.format("eFieldLeft: pos:%d", pos));
      assertEquals(eField, probUniverse.probCellArr[pos].eOutPart.field, String.format("eField: pos:%d", pos));
      assertEquals(rEField, probUniverse.probCellArr[pos].probCellState[OutState].eProbFieldArr[FieldRight].field, String.format("eFieldRight: pos:%d", pos));
   }

   private static void assertProbEF(final ProbUniverse probUniverse, final int pos) {
      assertNull(probUniverse.probCellArr[pos].eOutPart);
      assertEquals(0, probUniverse.probCellArr[pos].probCellState[OutState].eProbFieldArr[FieldLeft].field, String.format("eFieldLeft: pos:%d", pos));
      assertEquals(0, probUniverse.probCellArr[pos].eOutPart.field, String.format("eField: pos:%d", pos));
      assertEquals(0, probUniverse.probCellArr[pos].probCellState[OutState].eProbFieldArr[FieldRight].field, String.format("eFieldRight: pos:%d", pos));
   }

   private static void assertProbP(final ProbUniverse probUniverse, final int pos, final int lp, final int sp, final int rp,
                                   final int lPField, final int pField, final int rPField) {
      assertEquals(lp, probUniverse.probCellArr[pos].eOutPart.prob.probabilityArr[DirProbLeft], String.format("Left: pos:%d", pos));
      assertEquals(sp, probUniverse.probCellArr[pos].eOutPart.prob.probabilityArr[DirProbStay], String.format("Stay: pos:%d", pos));
      assertEquals(rp, probUniverse.probCellArr[pos].eOutPart.prob.probabilityArr[DirProbRight], String.format("Right: pos:%d", pos));
      assertEquals(lPField, probUniverse.probCellArr[pos].probCellState[OutState].pProbFieldArr[FieldLeft].field, String.format("pFieldLeft: pos:%d", pos));
      assertEquals(pField, probUniverse.probCellArr[pos].eOutPart.field, String.format("pField: pos:%d", pos));
      assertEquals(rPField, probUniverse.probCellArr[pos].probCellState[OutState].pProbFieldArr[FieldRight].field, String.format("pFieldRight: pos:%d", pos));
   }

   private static void initProbCell(final ProbUniverse probUniverse, final int pos, final int lp, final int sp, final int rp) {
      final ProbCell probCell = probUniverse.probCellArr[pos];

      probCell.eOutPart = new Part(Max_Probability, DirProbSize);
      probCell.eOutPart.prob.probabilityArr[DirProbStay]    = sp;
      probCell.eOutPart.prob.probabilityArr[DirProbLeft]    = lp;
      probCell.eOutPart.prob.probabilityArr[DirProbRight]   = rp;
      probCell.eOutPart.field = Max_Probability;
      //probCell.outEFieldArr[EFieldLeft] = Max_Probability;
      //probCell.outEFieldArr[EFieldRight] = Max_Probability;
   }
}
