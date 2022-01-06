package de.schmiereck.col.services.engine.spinMove;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.services.engine.CreateEngineService;

import org.junit.jupiter.api.Test;

public class Test_CreateLevel3SpinMoveEngineService_WHEN_create_is_called {

   @Test
   void GIVEN_call_THEN_state_is_created() {
      // Arrange

      // Act
      CreateEngineService.USE_check = true;
      final Engine engine = CreateLevel3SpinMoveEngineService.createLevel3SpinMoveEngine();
      CreateEngineService.USE_check = false;

      // Assert
   }
}
