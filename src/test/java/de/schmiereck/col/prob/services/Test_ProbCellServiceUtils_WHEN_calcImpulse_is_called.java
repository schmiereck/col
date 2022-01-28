package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbField.FieldLeft;
import static de.schmiereck.col.prob.model.ProbField.FieldRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbLeft;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbSize;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;
import static de.schmiereck.col.prob.services.ProbCellService.Max_Probability;
import static de.schmiereck.col.prob.services.ProbCellServiceUtils.calcImpulse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.ProbField;

import org.junit.jupiter.api.Test;

public class Test_ProbCellServiceUtils_WHEN_calcImpulse_is_called {

   @Test
   void GIVEN_eOutL0S100R0_pL100R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbField pProbField = new ProbField();

      pProbField.outFieldArr[FieldLeft] = 100;
      pProbField.outFieldArr[FieldRight] = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // A1:   0		100		0		<- 100
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 100;
      outProb.probabilityArr[DirProbRight]   = 0;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulse(inProb, outProb, pProbField);

      // Assert
      //   100		0		0
      assertEquals(100, inProb.probabilityArr[DirProbLeft]);
      assertEquals(0, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S100R0_pL50R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbField pProbField = new ProbField();

      pProbField.outFieldArr[FieldLeft] = 50;
      pProbField.outFieldArr[FieldRight] = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // A2:   0		100		0		<- 50
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 100;
      outProb.probabilityArr[DirProbRight]   = 0;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulse(inProb, outProb, pProbField);

      // Assert
      //   50		50		0
      assertEquals(50, inProb.probabilityArr[DirProbLeft]);
      assertEquals(50, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S100R0_pL1R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbField pProbField = new ProbField();

      pProbField.outFieldArr[FieldLeft] = 1;
      pProbField.outFieldArr[FieldRight] = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // A3:   0  100 0    <- 1
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 100;
      outProb.probabilityArr[DirProbRight]   = 0;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulse(inProb, outProb, pProbField);

      // Assert
      //   1  99  0
      assertEquals(1, inProb.probabilityArr[DirProbLeft]);
      assertEquals(99, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S99R1_pL100R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbField pProbField = new ProbField();

      pProbField.outFieldArr[FieldLeft] = 100;
      pProbField.outFieldArr[FieldRight] = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // B1:   0		99		1		<- 100
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 99;
      outProb.probabilityArr[DirProbRight]   = 1;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulse(inProb, outProb, pProbField);

      // Assert
      //   99		1		0
      assertEquals(99, inProb.probabilityArr[DirProbLeft]);
      assertEquals(1, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S99R1_pL1R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbField pProbField = new ProbField();

      pProbField.outFieldArr[FieldLeft] = 1;
      pProbField.outFieldArr[FieldRight] = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // B2:   0  99 1    <- 1
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 99;
      outProb.probabilityArr[DirProbRight]   = 1;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulse(inProb, outProb, pProbField);

      // Assert
      //   1  99  0
      assertEquals(1, inProb.probabilityArr[DirProbLeft]);
      assertEquals(99, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S30R70_pL100R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbField pProbField = new ProbField();

      pProbField.outFieldArr[FieldLeft] = 100;
      pProbField.outFieldArr[FieldRight] = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // C1:   0		30		70		<- 100
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 30;
      outProb.probabilityArr[DirProbRight]   = 70;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulse(inProb, outProb, pProbField);

      // Assert
      //   30		70		0
      assertEquals(30, inProb.probabilityArr[DirProbLeft]);
      assertEquals(70, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S30R70_pL50R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbField pProbField = new ProbField();

      pProbField.outFieldArr[FieldLeft] = 50;
      pProbField.outFieldArr[FieldRight] = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // C2:   0		30		70		<- 50
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 30;
      outProb.probabilityArr[DirProbRight]   = 70;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulse(inProb, outProb, pProbField);

      // Assert
      // 0		65		35
      assertEquals(0, inProb.probabilityArr[DirProbLeft]);
      assertEquals(65, inProb.probabilityArr[DirProbStay]);
      assertEquals(35, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S70R30_pL50R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbField pProbField = new ProbField();

      pProbField.outFieldArr[FieldLeft] = 50;
      pProbField.outFieldArr[FieldRight] = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // C3:   0		70		30		<- 50
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 70;
      outProb.probabilityArr[DirProbRight]   = 30;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulse(inProb, outProb, pProbField);

      // Assert
      // 0		85		15
      assertEquals(0, inProb.probabilityArr[DirProbLeft]);
      assertEquals(85, inProb.probabilityArr[DirProbStay]);
      assertEquals(15, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S0R100_pL100R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbField pProbField = new ProbField();

      pProbField.outFieldArr[FieldLeft] = 100;
      pProbField.outFieldArr[FieldRight] = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // D1:   0		0		100		<- 100
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 0;
      outProb.probabilityArr[DirProbRight]   = 100;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulse(inProb, outProb, pProbField);

      // Assert
      // 0		100		0
      assertEquals(0, inProb.probabilityArr[DirProbLeft]);
      assertEquals(100, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S0R100_pL50R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbField pProbField = new ProbField();

      pProbField.outFieldArr[FieldLeft] = 50;
      pProbField.outFieldArr[FieldRight] = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // D2:   0		0		100		<- 50
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 0;
      outProb.probabilityArr[DirProbRight]   = 100;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulse(inProb, outProb, pProbField);

      // Assert
      // 0		50		50
      assertEquals(0, inProb.probabilityArr[DirProbLeft]);
      assertEquals(50, inProb.probabilityArr[DirProbStay]);
      assertEquals(50, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL30S70R0_pL100R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbField pProbField = new ProbField();

      pProbField.outFieldArr[FieldLeft] = 100;
      pProbField.outFieldArr[FieldRight] = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // E1:   30		70		0		<- 100
      outProb.probabilityArr[DirProbLeft]    = 30;
      outProb.probabilityArr[DirProbStay]    = 70;
      outProb.probabilityArr[DirProbRight]   = 0;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulse(inProb, outProb, pProbField);

      // Assert
      // 100		0		0
      assertEquals(100, inProb.probabilityArr[DirProbLeft]);
      assertEquals(0, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL30S70R0_pL50R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbField pProbField = new ProbField();

      pProbField.outFieldArr[FieldLeft] = 50;
      pProbField.outFieldArr[FieldRight] = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // E2:   30		70		0		<- 50
      outProb.probabilityArr[DirProbLeft]    = 30;
      outProb.probabilityArr[DirProbStay]    = 70;
      outProb.probabilityArr[DirProbRight]   = 0;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulse(inProb, outProb, pProbField);

      // Assert
      // 65		35		0
      assertEquals(65, inProb.probabilityArr[DirProbLeft]);
      assertEquals(35, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }
}
