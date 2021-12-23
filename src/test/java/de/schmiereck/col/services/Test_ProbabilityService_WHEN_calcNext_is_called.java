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
   void GIVEN_size2_max3_poss_1_2_THEN_x() {
      // Arrange
      final Probability probability = new Probability(3, 2);

      //                             3     = 100%
      probability.posibilityArr[0] = 1; // =  33% = 1/3
      probability.posibilityArr[1] = 2; // =  66% = 2/3

      PossibilityService.calcInit(probability);

      final int[] lastPossibilityArr = {
              1,  0,  1,  1,  0,  1,  1,  0,  1,  1,  0
      };
      //      0   1   2   3   4   5   6   7   8   9   10  11  12
      final int[][] posibilityCnt2Arr = {
           {  2,  0,  1,  2,  0,  1,  2,  0,  1,  2,  0 },
           {  1,  3,  2,  1,  3,  2,  1,  3,  2,  1,  3 },
      };
      for (int pos = 0; pos < lastPossibilityArr.length; pos++) {
         // Assert
         assertEquals(lastPossibilityArr[pos], probability.lastPossibility, String.format("lastPossibility - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.posibilityCntArr[0], String.format("posibilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.posibilityCntArr[1], String.format("posibilityCntArr[1] - pos:%d", pos));

         // Act
         PossibilityService.calcNext(probability);
      }
   }

   @Test
   void GIVEN_size2_max3_poss_2_1_THEN_x() {
      // Arrange
      final Probability probability = new Probability(3, 2);

      //                             3     = 100%
      probability.posibilityArr[0] = 2; // =  66% = 2/3
      probability.posibilityArr[1] = 1; // =  33% = 1/3

      PossibilityService.calcInit(probability);

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
         assertEquals(lastPossibilityArr[pos], probability.lastPossibility, String.format("lastPossibility - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.posibilityCntArr[0], String.format("posibilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.posibilityCntArr[1], String.format("posibilityCntArr[1] - pos:%d", pos));

         // Act
         PossibilityService.calcNext(probability);
      }
   }

   @Test
   void GIVEN_size2_max8_poss_2_6_THEN_x() {
      // Arrange
      final Probability probability = new Probability(8, 2);

      //                             8     = 100%
      probability.posibilityArr[0] = 2; // =  25% = 1/4
      probability.posibilityArr[1] = 6; // =  75% = 3/4

      PossibilityService.calcInit(probability);

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
         PossibilityService.calcNext(probability);

         // Assert
         assertEquals(lastPossibilityArr[pos], probability.lastPossibility, String.format("lastPossibility - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.posibilityCntArr[0], String.format("posibilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.posibilityCntArr[1], String.format("posibilityCntArr[1] - pos:%d", pos));
      }
   }

   @Test
   void GIVEN_size3_max10_poss_3_3_4_THEN_x() {
      // Arrange
      final Probability probability = new Probability(10, 3);

      //                            10     = 100%
      probability.posibilityArr[0] = 3; // =  30% = 3/10
      probability.posibilityArr[1] = 3; // =  30% = 3/10
      probability.posibilityArr[2] = 4; // =  40% = 4/10

      PossibilityService.calcInit(probability);

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
         PossibilityService.calcNext(probability);

         // Assert
         assertEquals(lastPossibilityArr[pos], probability.lastPossibility, String.format("lastPossibility - pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], probability.posibilityCntArr[0], String.format("posibilityCntArr[0] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], probability.posibilityCntArr[1], String.format("posibilityCntArr[1] - pos:%d", pos));
         assertEquals(posibilityCnt2Arr[2][pos], probability.posibilityCntArr[2], String.format("posibilityCntArr[2] - pos:%d", pos));
      }
   }
}
