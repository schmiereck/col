package de.schmiereck.col.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Probability;

import org.junit.jupiter.api.Test;

/**
 * Possibility
 * Probability, Probability theory
 * odds (Chance)
 */
public class Test_ProbabilityService_WHEN_calcNext_is_called {

   @Test
   void GIVEN_size2_max3_prob_0_3_THEN_x() {
      // Arrange
      final Probability probability = new Probability(3, 2);

      //                              3     = 100%
      probability.probabilityArr[0] = 0; // =   0% = 0/3
      probability.probabilityArr[1] = 3; // = 100% = 3/3

      ProbabilityService.calcInit(probability);

      final int[] lastPossibilityArr = {
              1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1
      };
      //      0   1   2   3   4   5   6   7   8   9  10  11  12
      final int[][] posibilityCnt2Arr = {
           {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
           {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
      };
      for (int pos = 0; pos < lastPossibilityArr.length; pos++) {
         // Assert
         assertEquals(lastPossibilityArr[pos], probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.lastProbabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.lastProbabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));

         // Act
         ProbabilityService.calcNext(probability);
      }
   }

   @Test
   void GIVEN_size2_max3_prob_1_2_THEN_x() {
      // Arrange
      final Probability probability = new Probability(3, 2);

      //                             3     = 100%
      probability.probabilityArr[0] = 1; // =  33% = 1/3
      probability.probabilityArr[1] = 2; // =  66% = 2/3

      ProbabilityService.calcInit(probability);

      final int[] lastPossibilityArr = {
              1,  0,  1,  1,  0,  1,  1,  0,  1,  1,  0
      };
      //      0   1   2   3   4   5   6   7   8   9  10  11  12
      final int[][] posibilityCnt2Arr = {
           {  2,  0,  1,  2,  0,  1,  2,  0,  1,  2,  0 },
           {  1,  3,  2,  1,  3,  2,  1,  3,  2,  1,  3 },
      };
      for (int pos = 0; pos < lastPossibilityArr.length; pos++) {
         // Assert
         assertEquals(lastPossibilityArr[pos], probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));

         // Act
         ProbabilityService.calcNext(probability);
      }
   }

   @Test
   void GIVEN_size2_max3_prob_2_1_THEN_x() {
      // Arrange
      final Probability probability = new Probability(3, 2);

      //                              3     = 100%
      probability.probabilityArr[0] = 2; // =  66% = 2/3
      probability.probabilityArr[1] = 1; // =  33% = 1/3

      ProbabilityService.calcInit(probability);

      // 0: 7   1: 6
      final int[] lastPossibilityArr = {
              0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0
      };
      //      0   1   2   3   4   5   6   7   8   9  10  11  12
      final int[][] posibilityCnt2Arr = {
           {  1,  3,  2,  1,  3,  2,  1,  3,  2,  1,  3,  2,  1 },
           {  2,  0,  1,  2,  0,  1,  2,  0,  1,  2,  0,  1,  2 },
      };
      for (int pos = 0; pos < lastPossibilityArr.length; pos++) {
         // Assert
         assertEquals(lastPossibilityArr[pos], probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));

         // Act
         ProbabilityService.calcNext(probability);
      }
   }

   @Test
   void GIVEN_size2_max8_prob_2_6_THEN_x() {
      // Arrange
      final Probability probability = new Probability(8, 2);

      //                              8     = 100%
      probability.probabilityArr[0] = 2; // =  25% = 1/4
      probability.probabilityArr[1] = 6; // =  75% = 3/4

      ProbabilityService.calcInit(probability);

      final int[] lastPossibilityArr = {
              1,  0,  1,  1,  1,  0,  1,  1,  1,  0,  1,  1,  1
      };
      //      0   1   2   3   4   5   6   7   8   9  10  11  12
      final int[][] posibilityCnt2Arr = {
           {  6,  0,  2,  4,  6,  0,  2,  4,  6,  0,  2,  4,  6 },
           {  2,  8,  6,  4,  2,  8,  6,  4,  2,  8,  6,  4,  2 },
      };
      for (int pos = 0; pos < lastPossibilityArr.length; pos++) {
         // Act
         ProbabilityService.calcNext(probability);

         // Assert
         assertEquals(lastPossibilityArr[pos], probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));
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

      ProbabilityService.calcInit(probability);

      final int[] lastPossibilityArr = {
              2,  0,  2,  2,  2,  0,  2,  2,  2,  0,  2,  2,  2
      };
      //      0   1   2   3   4   5   6   7   8   9  10  11  12
      final int[][] posibilityCnt2Arr = {
           {  6,  0,  2,  4,  6,  0,  2,  4,  6,  0,  2,  4,  6 },
           {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
           {  2,  8,  6,  4,  2,  8,  6,  4,  2,  8,  6,  4,  2 },
      };
      for (int pos = 0; pos < lastPossibilityArr.length; pos++) {
         // Act
         ProbabilityService.calcNext(probability);

         // Assert
         assertEquals(lastPossibilityArr[pos], probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[2][pos], probability.probabilityCntArr[2], String.format("probabilityCntArr[2] - pos:%d", pos));
      }
   }

   @Test
   void GIVEN_size2_max100_prob_10_90_THEN_x() {
      // Arrange
      final Probability probability = new Probability(100, 2);

      //                            100     = 100%
      probability.probabilityArr[0] = 10; // =  10% = 10/100
      probability.probabilityArr[1] = 90; // =  90% = 90/100

      ProbabilityService.calcInit(probability);

      final int[] lastPossibilityArr = {
              1,  1,  1,  1,  1,  1,  1,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  1,  1,  1,  1,  1
      };
      //      0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22
      final int[][] posibilityCnt2Arr = {
           { 20, 30, 40, 50, 60, 70, 80, 90,  0, 10, 20, 30, 40, 50, 60, 70, 80, 90,  0, 10, 20, 30, 40, 50 },
           { 80, 70, 60, 50, 40, 30, 20, 10,100, 90, 80, 70, 60, 50, 40, 30, 20, 10,100, 90, 80, 70, 60, 50 },
      };
      for (int pos = 0; pos < lastPossibilityArr.length; pos++) {
         // Act
         ProbabilityService.calcNext(probability);

         // Assert
         assertEquals(lastPossibilityArr[pos], probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.lastProbabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.lastProbabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));
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

      ProbabilityService.calcInit(probability);

      // 0:4  1:3  2:4
      final int[] lastPossibilityArr = {
              0,  1,  2,  0,  1,  2,  0,  1,  2,  2,  0,  1,  2
      };
      //      0   1   2   3   4   5   6   7   8   9  10  11  12
      final int[][] posibilityCnt2Arr = {
           {  2,  5,  8,  1,  4,  7,  0,  3,  6,  9,  2,  5,  8 },
           { 12,  5,  8, 11,  4,  7, 10,  3,  6,  9, 12,  5,  8 },
           {  6, 10,  4,  8, 12,  6, 10, 14,  8,  2,  6, 10,  4 },
      };
      for (int pos = 0; pos < lastPossibilityArr.length; pos++) {
         // Act
         ProbabilityService.calcNext(probability);

         // Assert
         assertEquals(lastPossibilityArr[pos], probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[2][pos], probability.probabilityCntArr[2], String.format("probabilityCntArr[2] - pos:%d", pos));
      }
   }

   @Test
   void GIVEN_size2_max100_prob_99_1_THEN_x() {
      // Arrange
      final Probability probability = new Probability(100, 2);

      //                            100     = 100%
      probability.probabilityArr[0] = 99; // =  99% = 99/100
      probability.probabilityArr[1] = 1;  // =   1% = 1/100

      probability.probabilityCntArr[0] = -99;
      probability.probabilityCntArr[1] = 0;

      ProbabilityService.calcInit(probability);

      for (int pos = 0; pos <= 95; pos++) {
         // Act
         ProbabilityService.calcNext(probability);
         // Assert
         assertEquals(0, probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", pos));
      }

      // Act
      ProbabilityService.calcNext(probability);
      // Assert
      assertEquals(1, probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", 96));

      for (int pos = 97; pos <= 195; pos++) {
         // Act
         ProbabilityService.calcNext(probability);
         // Assert
         assertEquals(0, probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", pos));
      }

      // Act
      ProbabilityService.calcNext(probability);
      // Assert
      assertEquals(1, probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", 196));

      for (int pos = 197; pos <= 295; pos++) {
         // Act
         ProbabilityService.calcNext(probability);
         // Assert
         assertEquals(0, probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", pos));
      }

      // Act
      ProbabilityService.calcNext(probability);
      // Assert
      assertEquals(1, probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", 296));
   }

   @Test
   void GIVEN_size3_max10_prob_5_0_5_THEN_x() {
      // Arrange
      final Probability probability = new Probability(10, 3);

      //                             10     = 100%
      probability.probabilityArr[0] = 5; // =  50% = 5/10
      probability.probabilityArr[1] = 0; // =   0% = 0/10
      probability.probabilityArr[2] = 5; // =  50% = 5/10

      probability.probabilityCntArr[0] = -5;
      probability.probabilityCntArr[1] = 0;
      probability.probabilityCntArr[2] = 0;

      ProbabilityService.calcInit(probability);

      final int[] lastPossibilityArr = {
              0,  2,  0,  2,  0,  2,  0,  2,  0,  2,  0,  2,  0
      };
      //      0   1   2   3   4   5   6   7   8   9  10  11  12
      final int[][] posibilityCnt2Arr = {
           {  0,  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5 },
           {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
           {  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5 },
      };
      for (int pos = 0; pos < lastPossibilityArr.length; pos++) {
         // Act
         ProbabilityService.calcNext(probability);

         // Assert
         assertEquals(lastPossibilityArr[pos], probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[2][pos], probability.probabilityCntArr[2], String.format("probabilityCntArr[2] - pos:%d", pos));
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

      probability.probabilityCntArr[0] = -5;
      probability.probabilityCntArr[1] = 0;
      probability.probabilityCntArr[2] = 0;

      ProbabilityService.calcInit(probability);

      final int[] lastPossibilityArr = { // 0: 13x, 1: 4x,  2: 7x
                 0,  2,  0,  1,  2,  0,  0,  1,  2,  0,  0,  2,  0,  1,  2,  0,  0,  1,  2,  0,  0,  2,  0,  1
      };
      final int[][] lastPossibility2Arr = {
              {  1,  0,  1,  0,  0,  1,  1,  0,  0,  1,  1,  0,  1,  0,  0,  1,  1,  0,  0,  1,  1,  0,  1,  0 },
              {  0,  0,  0,  1,  0,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0,  0,  0,  1,  0,  0,  0,  0,  0,  1 },
              {  0,  1,  0,  0,  1,  0,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  0,  1,  0,  0,  1,  0,  0 },
      };
      //         0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23
      final int[][] posibilityCnt2Arr = {
              {  0,  5,  0,  5, 10,  5,  0,  5, 10,  5,  0,  5,  0,  5, 10,  5,  0,  5, 10,  5,  0,  5,  0,  5 },
              {  6,  8, 10,  2,  4,  6,  8,  0,  2,  4,  6,  8, 10,  2,  4,  6,  8,  0,  2,  4,  6,  8, 10,  2 },
              {  9,  2,  5,  8,  1,  4,  7, 10,  3,  6,  9,  2,  5,  8,  1,  4,  7, 10,  3,  6,  9,  2,  5,  8 },
      };
      for (int pos = 0; pos < lastPossibilityArr.length; pos++) {
         // Assert
         assertEquals(lastPossibilityArr[pos], probability.lastProbabilityPos, String.format("lastPossibility - pos:%d", pos));

         assertEquals(lastPossibility2Arr[0][pos], probability.lastProbabilityArr[0], String.format("lastProbabilityArr[0] - pos:%d", pos));
         assertEquals(lastPossibility2Arr[1][pos], probability.lastProbabilityArr[1], String.format("lastProbabilityArr[1] - pos:%d", pos));
         assertEquals(lastPossibility2Arr[2][pos], probability.lastProbabilityArr[2], String.format("lastProbabilityArr[2] - pos:%d", pos));


         assertEquals(posibilityCnt2Arr[0][pos], probability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[2][pos], probability.probabilityCntArr[2], String.format("probabilityCntArr[2] - pos:%d", pos));

         // Act
         ProbabilityService.calcNext(probability);
      }
   }

   @Test
   void GIVEN_a_size3_max10_prob_5_0_5_AND_b_size3_max10_prob_3_3_4_THEN_x() {
      // Arrange
      // a  5  0  5  = 10
      // b  3  3  4  = 10
      // -----------
      //    8  3  9  = 20
      final Probability aProbability = new Probability(10, 3);

      //                              10     = 100%
      aProbability.probabilityArr[0] = 5; // =  50% = 5/10
      aProbability.probabilityArr[1] = 0; // =   0% = 0/10
      aProbability.probabilityArr[2] = 5; // =  50% = 5/10

      final Probability bProbability = new Probability(10, 3);

      //                              10     = 100%
      bProbability.probabilityArr[0] = 3; // =  30% = 3/10
      bProbability.probabilityArr[1] = 3; // =  30% = 3/10
      bProbability.probabilityArr[2] = 4; // =  40% = 4/10

      ProbabilityService.calcInit(aProbability);
      ProbabilityService.calcInit(bProbability);

      final int[] lastPossibilityArr = {
              2,  0,  2,  0,  2,  0,  2,  0,  2,  0,  2,  0,  2
      };
      //      0   1   2   3   4   5   6   7   8   9  10  11  12
      final int[][] posibilityCnt2Arr = {
              {  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5,  0,  5 },
              {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
              {  5, 10,  5, 10,  5, 10,  5, 10,  5, 10,  5, 10,  5 },
      };
      for (int pos = 0; pos < lastPossibilityArr.length; pos++) {
         // Act
         ProbabilityService.calcNext(aProbability);

         // Assert
         assertEquals(lastPossibilityArr[pos], aProbability.lastProbabilityPos, String.format("lastPossibility - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], aProbability.probabilityCntArr[0], String.format("probabilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], aProbability.probabilityCntArr[1], String.format("probabilityCntArr[1] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[2][pos], aProbability.probabilityCntArr[2], String.format("probabilityCntArr[2] - pos:%d", pos));
      }
   }
}
