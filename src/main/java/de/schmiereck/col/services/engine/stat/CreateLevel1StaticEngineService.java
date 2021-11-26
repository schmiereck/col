package de.schmiereck.col.services.engine.stat;

import static de.schmiereck.col.model.State.negState;
import static de.schmiereck.col.model.State.nulState;
import static de.schmiereck.col.model.State.posState;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.State;

public class CreateLevel1StaticEngineService {

   public static Engine createLevel1staticEngine() {
      final State nul_nul_1State = new State(2);
      nul_nul_1State.inputStateArr[0] = nulState;
      nul_nul_1State.inputStateArr[1] = nulState;

      final State nul_pos_1State = new State(2);
      nul_pos_1State.inputStateArr[0] = nulState;
      nul_pos_1State.inputStateArr[1] = posState;

      final State nul_neg_1State = new State(2);
      nul_neg_1State.inputStateArr[0] = nulState;
      nul_neg_1State.inputStateArr[1] = negState;

      final State pos_nul_1State = new State(2);
      pos_nul_1State.inputStateArr[0] = posState;
      pos_nul_1State.inputStateArr[1] = nulState;

      final State pos_pos_1State = new State(2);
      pos_pos_1State.inputStateArr[0] = posState;
      pos_pos_1State.inputStateArr[1] = posState;

      final State pos_neg_1State = new State(2);
      pos_neg_1State.inputStateArr[0] = posState;
      pos_neg_1State.inputStateArr[1] = negState;

      final State neg_nul_1State = new State(2);
      neg_nul_1State.inputStateArr[0] = negState;
      neg_nul_1State.inputStateArr[1] = nulState;

      final State neg_pos_1State = new State(2);
      neg_pos_1State.inputStateArr[0] = negState;
      neg_pos_1State.inputStateArr[1] = posState;

      final State neg_neg_1State = new State(2);
      neg_neg_1State.inputStateArr[0] = negState;
      neg_neg_1State.inputStateArr[1] = negState;

      final Engine level1Engine = new Engine(2);

      level1Engine.setState(0, nul_nul_1State, 0);
      level1Engine.setState(1, nul_pos_1State, 1);
      level1Engine.setState(2, nul_neg_1State, 2);
      level1Engine.setState(3, pos_nul_1State, 3);
      level1Engine.setState(4, pos_pos_1State, 4);
      level1Engine.setState(5, pos_neg_1State, 0);
      level1Engine.setState(6, neg_nul_1State, 6);
      level1Engine.setState(7, neg_pos_1State, 0);
      level1Engine.setState(8, neg_neg_1State, 8);

      return level1Engine;
   }
}
