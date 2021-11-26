package de.schmiereck.col.services.engine.stat;

import static de.schmiereck.col.model.State.nulState;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class CreateLevel3StaticEngineService {

   public static Engine createLevel3staticEngine() {
      final State nul_nul_nul_nul_3State = new State(4);
      nul_nul_nul_nul_3State.inputStateArr[0] = nulState;
      nul_nul_nul_nul_3State.inputStateArr[1] = nulState;
      nul_nul_nul_nul_3State.inputStateArr[2] = nulState;
      nul_nul_nul_nul_3State.inputStateArr[3] = nulState;

      final Engine level4staticEngine = new Engine(4);

      level4staticEngine.setState(0, nul_nul_nul_nul_3State, 0);

      return level4staticEngine;
   }
}
