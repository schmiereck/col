package de.schmiereck.col.services.engine.dyna;

import static org.junit.jupiter.api.Assertions.*;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;
import de.schmiereck.col.services.engine.dyna.CreateLevel1DynamicEngineService;

import org.junit.jupiter.api.Test;

class Test_CreateEngineService_WHEN_createLevel1dynamicEngine_is_called {

   @Test
   void createLevel1dynamicEngine() {
      // Arrange

      // Act
      final Engine engine = CreateLevel1DynamicEngineService.createLevel1dynamicEngine();

      // Assert
      //0+x:
      // 0  =>  0
      //    0        0   0   =>   0        0   0
      //    0    0   0       =>   0    0   0
      assertEquals(0, engine.metaStateArr[0].outputMetaStatePos);
      assertEquals(State.nulState, engine.inputStateArr[engine.metaStateArr[0].inputMetaStatePosArr[0]].inputStateArr[0]);
      assertEquals(State.nulState, engine.inputStateArr[engine.metaStateArr[0].inputMetaStatePosArr[0]].inputStateArr[1]);
      assertEquals(State.nulState, engine.inputStateArr[engine.metaStateArr[0].inputMetaStatePosArr[1]].inputStateArr[0]);
      assertEquals(State.nulState, engine.inputStateArr[engine.metaStateArr[0].inputMetaStatePosArr[1]].inputStateArr[1]);
      // 1  =>  1
      //    1        0   1   =>   1        0   1
      //    0    0   0       =>   0    0   0
      assertEquals(1, engine.metaStateArr[1].outputMetaStatePos);
      assertEquals(State.nulState, engine.inputStateArr[engine.metaStateArr[1].inputMetaStatePosArr[0]].inputStateArr[0]);
      assertEquals(State.posState, engine.inputStateArr[engine.metaStateArr[1].inputMetaStatePosArr[0]].inputStateArr[1]);
      assertEquals(State.nulState, engine.inputStateArr[engine.metaStateArr[1].inputMetaStatePosArr[1]].inputStateArr[0]);
      assertEquals(State.nulState, engine.inputStateArr[engine.metaStateArr[1].inputMetaStatePosArr[1]].inputStateArr[1]);
      // 2  =>  9
      //     2        1   0   =>   0        0   0
      //     0    0   0       =>   1    0   1
      assertEquals(9, engine.metaStateArr[2].outputMetaStatePos);
      assertEquals(State.posState, engine.inputStateArr[engine.metaStateArr[2].inputMetaStatePosArr[0]].inputStateArr[0]);
      assertEquals(State.nulState, engine.inputStateArr[engine.metaStateArr[2].inputMetaStatePosArr[0]].inputStateArr[1]);
      assertEquals(State.nulState, engine.inputStateArr[engine.metaStateArr[2].inputMetaStatePosArr[1]].inputStateArr[0]);
      assertEquals(State.nulState, engine.inputStateArr[engine.metaStateArr[2].inputMetaStatePosArr[1]].inputStateArr[1]);
      // 9  =>  2
      //     0        0   0   =>   2        1   0
      //     1    0   1       =>   0    0   0
      assertEquals(2, engine.metaStateArr[9].outputMetaStatePos);
      assertEquals(State.nulState, engine.inputStateArr[engine.metaStateArr[9].inputMetaStatePosArr[0]].inputStateArr[0]);
      assertEquals(State.nulState, engine.inputStateArr[engine.metaStateArr[9].inputMetaStatePosArr[0]].inputStateArr[1]);
      assertEquals(State.nulState, engine.inputStateArr[engine.metaStateArr[9].inputMetaStatePosArr[1]].inputStateArr[0]);
      assertEquals(State.posState, engine.inputStateArr[engine.metaStateArr[9].inputMetaStatePosArr[1]].inputStateArr[1]);
   }
}