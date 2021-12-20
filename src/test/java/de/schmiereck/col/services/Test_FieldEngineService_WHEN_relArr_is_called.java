package de.schmiereck.col.services;

import static de.schmiereck.col.services.FieldEngineService.calcArrPos2Rel;
import static de.schmiereck.col.services.FieldEngineService.calcRel2ArrPos;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Test_FieldEngineService_WHEN_relArr_is_called {

   @Test
   void GIVEN_Rel_THEN_ArrPos() {
      assertEquals(2, 1 << 1);

      assertEquals(7, calcRel2ArrPos(-3));
      assertEquals(5, calcRel2ArrPos(-2));
      assertEquals(3, calcRel2ArrPos(-1));
      assertEquals(0, calcRel2ArrPos(0));
      assertEquals(2, calcRel2ArrPos(1));
      assertEquals(4, calcRel2ArrPos(2));
      assertEquals(6, calcRel2ArrPos(3));
   }

   @Test
   void GIVEN_ArrPos_THEN_Rel() {
      assertEquals(1, 2 >> 1);

      assertEquals(-3, calcArrPos2Rel(7));
      assertEquals(-2, calcArrPos2Rel(5));
      assertEquals(-1, calcArrPos2Rel(3));
      assertEquals(0, calcArrPos2Rel(0));
      assertEquals(1, calcArrPos2Rel(2));
      assertEquals(2, calcArrPos2Rel(4));
      assertEquals(3, calcArrPos2Rel(6));
   }
}
