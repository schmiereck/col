package de.schmiereck.col.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Probability;

import org.junit.jupiter.api.Test;

public class Test_ProbabilityService_WHEN_calcNext2_is_called {

   @Test
   void GIVEN_size2_max3_prob_0_3_THEN_x() {
      // Arrange
      final Probability probability = new Probability(3, 2);

      //                              3     = 100%
      probability.probabilityArr[0] = 0; // =   0% = 0/3
      probability.probabilityArr[1] = 3; // = 100% = 3/3

      probability.probabilityCntArr[0] = 0;
      probability.probabilityCntArr[1] = 3;

      ProbabilityService.calcInit2(probability);

      final int[][] lastPossibilityArr = {
              {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
              {  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1 },
      };
      //         0   1   2   3   4   5   6   7   8   9  10  11  12
      final int[][] posibilityCnt2Arr = {
              {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
              {  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
      };
      for (int pos = 0; pos < lastPossibilityArr[0].length; pos++) {
         // Assert
         assertEquals(lastPossibilityArr[0][pos], probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", pos));
         assertEquals(lastPossibilityArr[1][pos], probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));

         // Act
         ProbabilityService.calcNext2(probability);
      }
   }

   @Test
   void GIVEN_size2_max3_prob_1_2_THEN_x() {
      // Arrange
      final Probability probability = new Probability(3, 2);

      //                             3     = 100%
      probability.probabilityArr[0] = 1; // =  33% = 1/3
      probability.probabilityArr[1] = 2; // =  66% = 2/3

      probability.probabilityCntArr[0] = 0; // =  33% = 1/3
      probability.probabilityCntArr[1] = 2; // =  66% = 2/3

      ProbabilityService.calcInit2(probability);

      final int[][] lastPossibilityArr = {
              {  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0 },
              {  1,  1,  0,  1,  1,  0,  1,  1,  0,  1,  1,  0,  1 },
      };
      //         0   1   2   3   4   5   6   7   8   9  10  11  12
      final int[][] posibilityCnt2Arr = {
              {  1,  2,  0,  1,  2,  0,  1,  2,  0,  1,  2,  0,  1 },
              {  1,  0,  2,  1,  0,  2,  1,  0,  2,  1,  0,  2,  1 },
      };
      for (int pos = 0; pos < lastPossibilityArr[0].length; pos++) {
         // Assert
         assertEquals(lastPossibilityArr[0][pos], probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", pos));
         assertEquals(lastPossibilityArr[1][pos], probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));

         // Act
         ProbabilityService.calcNext2(probability);
      }
   }

   @Test
   void GIVEN_size2_max3_prob_2_1_THEN_x() {
      // Arrange
      final Probability probability = new Probability(3, 2);

      //                              3     = 100%
      probability.probabilityArr[0] = 2; // =  66% = 2/3
      probability.probabilityArr[1] = 1; // =  33% = 1/3

      probability.probabilityCntArr[0] = 2; // =  66% = 2/3
      probability.probabilityCntArr[1] = 0;

      ProbabilityService.calcInit2(probability);

      final int[][] lastPossibilityArr = {
              {  1,  1,  0,  1,  1,  0,  1,  1,  0,  1,  1,  0,  1 },
              {  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0 },
      };
      //         0   1   2   3   4   5   6   7   8   9  10  11  12
      final int[][] posibilityCnt2Arr = {
              {  1,  0,  2,  1,  0,  2,  1,  0,  2,  1,  0,  2,  1 },
              {  1,  2,  0,  1,  2,  0,  1,  2,  0,  1,  2,  0,  1 },
      };
      for (int pos = 0; pos < lastPossibilityArr[0].length; pos++) {
         // Assert
         assertEquals(lastPossibilityArr[0][pos], probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", pos));
         assertEquals(lastPossibilityArr[1][pos], probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));

         // Act
         ProbabilityService.calcNext2(probability);
      }
   }

   @Test
   void GIVEN_size2_max8_prob_2_6_THEN_x() {
      // Arrange
      final Probability probability = new Probability(8, 2);

      //                              8     = 100%
      probability.probabilityArr[0] = 2; // =  25% = 1/4
      probability.probabilityArr[1] = 6; // =  75% = 3/4

      probability.probabilityCntArr[0] = 0;
      probability.probabilityCntArr[1] = 6;

      ProbabilityService.calcInit2(probability);

      final int[][] lastPossibilityArr = {
              {  0,  0,  0,  1,  0,  0,  0,  1,  0,  0,  0,  1,  0 },
              {  1,  1,  1,  0,  1,  1,  1,  0,  1,  1,  1,  0,  1 },
      };
      //         0   1   2   3   4   5   6   7   8   9  10  11  12
      final int[][] posibilityCnt2Arr = {
              {  2,  4,  6,  0,  2,  4,  6,  0,  2,  4,  6,  0,  2 },
              {  4,  2,  0,  6,  4,  2,  0,  6,  4,  2,  0,  6,  4 },
      };
      for (int pos = 0; pos < lastPossibilityArr[0].length; pos++) {
         // Assert
         assertEquals(lastPossibilityArr[0][pos], probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", pos));
         assertEquals(lastPossibilityArr[1][pos], probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));

         // Act
         ProbabilityService.calcNext2(probability);
      }
   }

   @Test
   void GIVEN_size3_max8_prob_2_0_6_THEN_x() {
      // Arrange
      final Probability probability = new Probability(8, 3);

      //                              8     = 100%
      probability.probabilityArr[0] = 2; // =  25% = 1/4
      probability.probabilityArr[1] = 0; // =   0% = 0/4
      probability.probabilityArr[2] = 6; // =  75% = 3/4

      probability.probabilityCntArr[0] = 0;
      probability.probabilityCntArr[1] = 0;
      probability.probabilityCntArr[2] = 6;

      ProbabilityService.calcInit2(probability);

      final int[][] lastPossibilityArr = {
              {  0,  0,  0,  1,  0,  0,  0,  1,  0,  0,  0,  1,  0 },
              {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
              {  1,  1,  1,  0,  1,  1,  1,  0,  1,  1,  1,  0,  1 },
      };
      //         0   1   2   3   4   5   6   7   8   9  10  11  12
      final int[][] posibilityCnt2Arr = {
              {  2,  4,  6,  0,  2,  4,  6,  0,  2,  4,  6,  0,  2 },
              {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
              {  4,  2,  0,  6,  4,  2,  0,  6,  4,  2,  0,  6,  4 },
      };
      for (int pos = 0; pos < lastPossibilityArr[0].length; pos++) {
         // Assert
         assertEquals(lastPossibilityArr[0][pos], probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", pos));
         assertEquals(lastPossibilityArr[1][pos], probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", pos));
         assertEquals(lastPossibilityArr[2][pos], probability.lastProbabilityArr[2], String.format("lastProbabilityArr[2] - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[2][pos], probability.probabilityCntArr[2], String.format("probabilityCntArr[2] - pos:%d", pos));

         // Act
         ProbabilityService.calcNext2(probability);
      }
   }

   @Test
   void GIVEN_size2_max100_prob_10_90_THEN_x() {
      // Arrange
      final Probability probability = new Probability(100, 2);

      //                            100     = 100%
      probability.probabilityArr[0] = 10; // =  10% = 10/100
      probability.probabilityArr[1] = 90; // =  90% = 90/100

      probability.probabilityCntArr[0] = 0;
      probability.probabilityCntArr[1] = 90;

      ProbabilityService.calcInit2(probability);

      final int[][] lastPossibilityArr = {
              {  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  0,  0,  0 },
              {  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  1,  1,  1 },
      };
      //         0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22
      final int[][] posibilityCnt2Arr = {
              {  0, 10, 20, 30, 40, 50, 60, 70, 80, 90,  0, 10, 20, 30, 40, 50, 60, 70, 80, 90,  0, 10, 20, 30, 40, 50 },
              { 90, 80, 70, 60, 50, 40, 30, 20, 10,  0, 90, 80, 70, 60, 50, 40, 30, 20, 10,  0, 90, 80, 70, 60, 50 },
      };
      for (int pos = 0; pos < lastPossibilityArr[0].length; pos++) {
         // Assert
         assertEquals(lastPossibilityArr[0][pos], probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", pos));
         assertEquals(lastPossibilityArr[1][pos], probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.lastProbabilityCntArr[0], String.format("lastProbabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.lastProbabilityCntArr[1], String.format("lastProbabilityCntArr[1] - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos + 1], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos + 1], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));

         // Act
         ProbabilityService.calcNext2(probability);
      }
   }

   @Test
   void GIVEN_size3_max10_prob_3_3_4_THEN_x() {
      // Arrange
      final Probability probability = new Probability(10, 3);

      //                             10     = 100%
      probability.probabilityArr[0] = 3; // =  30% = 3/10
      probability.probabilityArr[1] = 3; // =  30% = 3/10
      probability.probabilityArr[2] = 4; // =  40% = 4/10

      probability.probabilityCntArr[0] = 0;
      probability.probabilityCntArr[1] = 0;
      probability.probabilityCntArr[2] = 4;

      ProbabilityService.calcInit2(probability);

      final int[][] lastPossibilityArr = {
              {  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  1,  0 },
              {  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  1,  0 },
              {  1,  0,  1,  0,  0,  1,  0,  1,  0,  0,  1,  0,  1,  0,  0,  1,  0,  1,  0,  0,  1,  0,  1,  0 },
      };
      //         0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23
      final int[][] posibilityCnt2Arr = {
              {  6,  9,  2,  5,  8,  1,  4,  7,  0,  3,  6,  9,  2,  5,  8,  1,  4,  7,  0,  3,  6,  9,  2,  5 },
              {  6,  9,  2,  5,  8,  1,  4,  7,  0,  3,  6,  9,  2,  5,  8,  1,  4,  7,  0,  3,  6,  9,  2,  5,  8 },
              {  2,  6,  0,  4,  8,  2,  6,  0,  4,  8,  2,  6,  0,  4,  8,  2,  6,  0,  4,  8,  2,  6,  0,  4 },
      };
      for (int pos = 0; pos < lastPossibilityArr[0].length; pos++) {
         // Assert
         assertEquals(lastPossibilityArr[0][pos], probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", pos));
         assertEquals(lastPossibilityArr[1][pos], probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", pos));
         assertEquals(lastPossibilityArr[2][pos], probability.lastProbabilityArr[2], String.format("lastProbabilityArr[2] - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[2][pos], probability.probabilityCntArr[2], String.format("probabilityCntArr[2] - pos:%d", pos));

         // Act
         ProbabilityService.calcNext2(probability);
      }
   }

   @Test
   void GIVEN_size2_max100_prob_99_1_THEN_x() {
      // Arrange
      final Probability probability = new Probability(100, 2);

      //                            100     = 100%
      probability.probabilityArr[0] = 99; // =  99% = 99/100
      probability.probabilityArr[1] = 1;  // =   1% = 1/100

      probability.probabilityCntArr[0] = 99;
      probability.probabilityCntArr[1] = 0;

      ProbabilityService.calcInit2(probability);

      for (int pos = 0; pos <= 98; pos++) {
         // Assert
         assertEquals(1, probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", pos));
         assertEquals(0, probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", pos));
         // Act
         ProbabilityService.calcNext2(probability);
      }

      // Assert
      assertEquals(0, probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", 99));
      assertEquals(1, probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", 99));
      // Act
      ProbabilityService.calcNext2(probability);

      for (int pos = 100; pos <= 198; pos++) {
         // Assert
         assertEquals(1, probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", pos));
         assertEquals(0, probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", pos));
         // Act
         ProbabilityService.calcNext2(probability);
      }

      // Assert
      assertEquals(0, probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", 199));
      assertEquals(1, probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", 199));
      // Act
      ProbabilityService.calcNext2(probability);

      for (int pos = 200; pos <= 298; pos++) {
         // Assert
         assertEquals(1, probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", pos));
         assertEquals(0, probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", pos));
         // Act
         ProbabilityService.calcNext2(probability);
      }

      // Assert
      assertEquals(0, probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", 299));
      assertEquals(1, probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", 299));
      // Act
      ProbabilityService.calcNext2(probability);
   }

   @Test
   void GIVEN_size3_max10_prob_5_0_5_THEN_x() {
      // Arrange
      final Probability probability = new Probability(10, 3);

      //                             10     = 100%
      probability.probabilityArr[0] = 5; // =  50% = 5/10
      probability.probabilityArr[1] = 0; // =   0% = 0/10
      probability.probabilityArr[2] = 5; // =  50% = 5/10

      probability.probabilityCntArr[0] = 5;
      probability.probabilityCntArr[1] = 0;
      probability.probabilityCntArr[2] = 0;

      ProbabilityService.calcInit2(probability);

      final int[][] lastPossibilityArr = {
              {  1,  0,  1,  0,  1,  0,  1,  0,  1,  0,  1,  0,  1 },
              {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
              {  0,  1,  0,  1,  0,  1,  0,  1,  0,  1,  0,  1,  0 },
      };
      //         0   1   2   3   4   5   6   7   8   9  10  11  12
      final int[][] posibilityCnt2Arr = {
              {  0,  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5 },
              {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
              {  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5 },
      };
      for (int pos = 0; pos < lastPossibilityArr[0].length; pos++) {

         // Assert
         assertEquals(lastPossibilityArr[0][pos], probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", pos));
         assertEquals(lastPossibilityArr[1][pos], probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", pos));
         assertEquals(lastPossibilityArr[2][pos], probability.lastProbabilityArr[2], String.format("lastProbabilityArr[2] - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[2][pos], probability.probabilityCntArr[2], String.format("probabilityCntArr[2] - pos:%d", pos));

         // Act
         ProbabilityService.calcNext2(probability);
      }
   }

   @Test
   void GIVEN_size3_max10_prob_5_2_3_THEN_x() {
      // Arrange
      final Probability probability = new Probability(10, 3);

      //                             10     = 100%
      probability.probabilityArr[0] = 5; // =  50% = 5/10
      probability.probabilityArr[1] = 2; // =  20% = 2/10
      probability.probabilityArr[2] = 3; // =  30% = 3/10

      probability.probabilityCntArr[0] = 5;
      probability.probabilityCntArr[1] = 0;
      probability.probabilityCntArr[2] = 0;

      ProbabilityService.calcInit2(probability);

      final int[][] lastPossibilityArr = {
              {  1,  0,  1,  0,  1,  0,  1,  0,  1,  0,  1,  0,  1,  0,  1,  0,  1,  0,  1,  0,  1,  0,  1,  0 },
              {  0,  0,  0,  0,  1,  0,  0,  0,  0,  1,  0,  0,  0,  0,  1,  0,  0,  0,  0,  1,  0,  0,  0,  0,  1,  0 },
              {  0,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  1,  0,  0,  1 },
      };
      //         0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23
      final int[][] posibilityCnt2Arr = {
              {  0,  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5 },
              {  2,  4,  6,  8,  0,  2,  4,  6,  8,  0,  2,  4,  6,  8,  0,  2,  4,  6,  8,  0,  2,  4,  6,  8,  0 },
              {  3,  6,  9,  2,  5,  8,  1,  4,  7,  0,  3,  6,  9,  2,  5,  8,  1,  4,  7,  0,  3,  6,  9,  2,  5,  8,  1,  4,  7,  0,  3,  6,  9 },
      };
      for (int pos = 0; pos < lastPossibilityArr[0].length; pos++) {
         // Assert
         assertEquals(lastPossibilityArr[0][pos], probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", pos));
         assertEquals(lastPossibilityArr[1][pos], probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", pos));
         assertEquals(lastPossibilityArr[2][pos], probability.lastProbabilityArr[2], String.format("lastProbabilityArr[2] - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[2][pos], probability.probabilityCntArr[2], String.format("probabilityCntArr[2] - pos:%d", pos));

         // Act
         ProbabilityService.calcNext2(probability);
      }
   }
}
