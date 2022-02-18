package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbCell.OutState;
import static de.schmiereck.col.prob.model.ProbField.FieldLeft;
import static de.schmiereck.col.prob.model.ProbField.FieldRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbLeft;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbSize;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;
import static de.schmiereck.col.prob.services.ProbCellService.Max_Probability;
import static de.schmiereck.col.prob.services.ProbCellServiceUtils.calcImpulseWeight;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.ProbCell;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Test_ProbCellServiceUtils_WHEN_calcImpulseWeight_is_called {

   private static  Stream<Arguments> provideArguments() {
      return Stream.of(
              // A1-R:   0		100		0		<- 100      =>    100		0		  0
              Arguments.of(new int[]{100, 0}, new int[]{0, 100, 0}, new int[]{100, 0, 0}),
              // A1-L:   100 ->    0		100		0      =>    0		   0		100
              Arguments.of(new int[]{0, 100}, new int[]{0, 100, 0}, new int[]{0, 0, 100})
      );
   }

   @ParameterizedTest
   @MethodSource("provideArguments")
   void GIVEN_eOutLxSxRx_pLxRx_THEN_eIn_accelerated(final int[] pFieldArr, final int[] outProbArr, final int[] inProbArr) {
      // Arrange
      final ProbCell probCell = new ProbCell();

      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].outField = pFieldArr[0];
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].outField = pFieldArr[1];

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      outProb.probabilityArr[DirProbLeft]    = outProbArr[0];
      outProb.probabilityArr[DirProbStay]    = outProbArr[1];
      outProb.probabilityArr[DirProbRight]   = outProbArr[2];

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulseWeight(inProb, outProb, probCell);

      // Assert
      assertEquals(inProbArr[0], inProb.probabilityArr[DirProbLeft]);
      assertEquals(inProbArr[1], inProb.probabilityArr[DirProbStay]);
      assertEquals(inProbArr[2], inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S100R0_pL50R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbCell probCell = new ProbCell();

      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].outField = 50;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].outField = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // A2-R:   0		100		0		<- 50
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 100;
      outProb.probabilityArr[DirProbRight]   = 0;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulseWeight(inProb, outProb, probCell);

      // Assert
      //   50		50		0
      assertEquals(50, inProb.probabilityArr[DirProbLeft]);
      assertEquals(50, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S100R0_pL0R50_THEN_eIn_accelerated() {
      // Arrange
      final ProbCell probCell = new ProbCell();

      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].outField = 0;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].outField = 50;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // A2-L:   50 ->    0		100		0
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 100;
      outProb.probabilityArr[DirProbRight]   = 0;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulseWeight(inProb, outProb, probCell);

      // Assert
      //   0		50		50
      assertEquals(0, inProb.probabilityArr[DirProbLeft]);
      assertEquals(50, inProb.probabilityArr[DirProbStay]);
      assertEquals(50, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S100R0_pL1R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbCell probCell = new ProbCell();

      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].outField = 1;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].outField = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // A3-R:   0  100 0    <- 1
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 100;
      outProb.probabilityArr[DirProbRight]   = 0;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulseWeight(inProb, outProb, probCell);

      // Assert
      //   1  99  0
      assertEquals(1, inProb.probabilityArr[DirProbLeft]);
      assertEquals(99, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S99R1_pL100R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbCell probCell = new ProbCell();

      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].outField = 100;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].outField = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // B1-R:   0		99		1		<- 100
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 99;
      outProb.probabilityArr[DirProbRight]   = 1;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulseWeight(inProb, outProb, probCell);

      // Assert
      //   99		1		0
      assertEquals(99, inProb.probabilityArr[DirProbLeft]);
      assertEquals(1, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S99R1_pL1R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbCell probCell = new ProbCell();

      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].outField = 1;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].outField = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // B2-R:   0  99 1    <- 1
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 99;
      outProb.probabilityArr[DirProbRight]   = 1;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulseWeight(inProb, outProb, probCell);

      // Assert
      //   1  99  0
      assertEquals(1, inProb.probabilityArr[DirProbLeft]);
      assertEquals(99, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S30R70_pL100R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbCell probCell = new ProbCell();

      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].outField = 100;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].outField = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // C1-R:   0		30		70		<- 100
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 30;
      outProb.probabilityArr[DirProbRight]   = 70;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulseWeight(inProb, outProb, probCell);

      // Assert
      //   30		70		0
      assertEquals(30, inProb.probabilityArr[DirProbLeft]);
      assertEquals(70, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S30R70_pL50R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbCell probCell = new ProbCell();

      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].outField = 50;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].outField = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // C2-R:   0		30		70		<- 50
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 30;
      outProb.probabilityArr[DirProbRight]   = 70;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulseWeight(inProb, outProb, probCell);

      // Assert
      // 0		65		35
      assertEquals(0, inProb.probabilityArr[DirProbLeft]);
      assertEquals(65, inProb.probabilityArr[DirProbStay]);
      assertEquals(35, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S70R30_pL50R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbCell probCell = new ProbCell();

      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].outField = 50;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].outField = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // C3-R:   0		70		30		<- 50
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 70;
      outProb.probabilityArr[DirProbRight]   = 30;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulseWeight(inProb, outProb, probCell);

      // Assert
      // 0		85		15
      assertEquals(0, inProb.probabilityArr[DirProbLeft]);
      assertEquals(85, inProb.probabilityArr[DirProbStay]);
      assertEquals(15, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S0R100_pL100R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbCell probCell = new ProbCell();

      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].outField = 100;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].outField = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // D1-R:   0		0		100		<- 100
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 0;
      outProb.probabilityArr[DirProbRight]   = 100;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulseWeight(inProb, outProb, probCell);

      // Assert
      // 0		100		0
      assertEquals(0, inProb.probabilityArr[DirProbLeft]);
      assertEquals(100, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S0R100_pL50R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbCell probCell = new ProbCell();

      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].outField = 50;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].outField = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // D2-R:   0		0		100		<- 50
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 0;
      outProb.probabilityArr[DirProbRight]   = 100;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulseWeight(inProb, outProb, probCell);

      // Assert
      // 0		50		50
      assertEquals(0, inProb.probabilityArr[DirProbLeft]);
      assertEquals(50, inProb.probabilityArr[DirProbStay]);
      assertEquals(50, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL30S70R0_pL100R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbCell probCell = new ProbCell();

      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].outField = 100;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].outField = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // E1-R:   30		70		0		<- 100
      outProb.probabilityArr[DirProbLeft]    = 30;
      outProb.probabilityArr[DirProbStay]    = 70;
      outProb.probabilityArr[DirProbRight]   = 0;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulseWeight(inProb, outProb, probCell);

      // Assert
      // 100		0		0
      assertEquals(100, inProb.probabilityArr[DirProbLeft]);
      assertEquals(0, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL30S70R0_pL50R0_THEN_eIn_accelerated() {
      // Arrange
      final ProbCell probCell = new ProbCell();

      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].outField = 50;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].outField = 0;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // E2-R:   30		70		0		<- 50
      outProb.probabilityArr[DirProbLeft]    = 30;
      outProb.probabilityArr[DirProbStay]    = 70;
      outProb.probabilityArr[DirProbRight]   = 0;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulseWeight(inProb, outProb, probCell);

      // Assert
      // 65		35		0
      assertEquals(65, inProb.probabilityArr[DirProbLeft]);
      assertEquals(35, inProb.probabilityArr[DirProbStay]);
      assertEquals(0, inProb.probabilityArr[DirProbRight]);
   }

   @Test
   void GIVEN_eOutL0S70R30_pL0R50_THEN_eIn_accelerated() {
      // Arrange
      final ProbCell probCell = new ProbCell();

      probCell.probCellState[OutState].pProbFieldArr[FieldLeft].outField = 0;
      probCell.probCellState[OutState].pProbFieldArr[FieldRight].outField = 50;

      final Probability outProb = new Probability(Max_Probability, DirProbSize);

      // E2-L:   50  ->    0		70		30
      outProb.probabilityArr[DirProbLeft]    = 0;
      outProb.probabilityArr[DirProbStay]    = 70;
      outProb.probabilityArr[DirProbRight]   = 30;

      final Probability inProb = new Probability(Max_Probability, DirProbSize);

      // Act
      calcImpulseWeight(inProb, outProb, probCell);

      // Assert
      // 65		35		65
      assertEquals(0, inProb.probabilityArr[DirProbLeft]);
      assertEquals(35, inProb.probabilityArr[DirProbStay]);
      assertEquals(65, inProb.probabilityArr[DirProbRight]);
   }
}
