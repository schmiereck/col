package de.schmiereck.col.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.schmiereck.col.model.Possibility;

import org.junit.jupiter.api.Test;

public class Test_PossibilityService_WHEN_x_is_called {

   @Test
   void GIVEN_x_THEN_x() {
      // Arrange
      final Possibility possibility = new Possibility(8, 2);

      possibility.posibilityArr[0] = 2;
      possibility.posibilityArr[1] = 6;

      possibility.posibilityCntArr[0] = 0;//possibility.posibilityArr[0];
      possibility.posibilityCntArr[1] = possibility.posibilityArr[1];

      final int[] lastPossibilityArr = {
              1,  1,  1,  0,  1,  1,  1,  0,  1
      };
      final int[][] posibilityCnt2Arr = {
           {  2,  4,  6,  0,  2,  4,  6,  0,  2,  4,  6 },
           {  0,  6,  0,  6,  0,  6,  0,  6,  0,  6 },
      };
      for (int pos = 0; pos < lastPossibilityArr.length; pos++) {
         // Act
         PossibilityService.calcNext(possibility);

         // Assert
         assertEquals(lastPossibilityArr[pos], possibility.lastPossibility, String.format("pos:%d", pos));

         assertEquals(posibilityCnt2Arr[0][pos], possibility.posibilityCntArr[0], String.format("pos:%d", pos));
         assertEquals(posibilityCnt2Arr[1][pos], possibility.posibilityCntArr[1], String.format("pos:%d", pos));
      }
   }
}
