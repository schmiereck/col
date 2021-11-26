package de.schmiereck.col.services.engine.stat;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class CreateLevel0StaticEngineService {

   public static Engine createLevel0staticEngine() {
      //----------------------------------------------------------------------------------------------------------------
      final Engine level1Engine = new Engine(1);

      //----------------------------------------------------------------------------------------------------------------
      // null:
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      final int NULL_u0 = 0;
      // 0    0   =>   0    0
      level1Engine.setState(NULL_u0, new State(1, nulState), NULL_u0);
      // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      // stay:
      final int STAY_p1 = 1;
      final int STAY_n1 = 2;
      // 1    1   =>   1    1
      level1Engine.setState(STAY_p1, new State(1, posState), STAY_p1);
      // 2   -1   =>   2   -1
      level1Engine.setState(STAY_n1, new State(1, negState), STAY_n1);

      //----------------------------------------------------------------------------------------------------------------
      return level1Engine;
   }
}
