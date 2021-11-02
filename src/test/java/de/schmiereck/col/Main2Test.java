package de.schmiereck.col;

import static de.schmiereck.col.model.State.neg0State;
import static de.schmiereck.col.model.State.nul0State;
import static de.schmiereck.col.model.State.pos0State;
import static de.schmiereck.col.services.UniverseService.printCells;
import static de.schmiereck.col.services.UniverseService.readCell;
import static de.schmiereck.col.services.UniverseService.run;
import static de.schmiereck.col.services.UniverseService.setStatePos;
import static org.junit.jupiter.api.Assertions.*;

import de.schmiereck.col.model.Engine;
import de.schmiereck.col.model.Universe;
import de.schmiereck.col.services.EngineService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Main2Test {

   @BeforeEach
   void setUp() {
   }

   @Test
   void main() {
      // Arrange
      final int universeSize = 12;

      final Engine level1Engine = EngineService.createLevel1staticEngine();
      final Engine level2Engine = EngineService.createLevel2staticEngine();

      final Engine[] engine2Arr = new Engine[2];
      engine2Arr[0] = level1Engine;
      engine2Arr[1] = level2Engine;

      final Universe universe = new Universe(engine2Arr, universeSize);

      setStatePos(universe, universeSize / 2, 0,  1);
      setStatePos(universe, 0, 0, 1);
      setStatePos(universe, 1, 0, 2);

      // Act
      printCells(universe, engine2Arr, 0);
      run(engine2Arr, universe);
      printCells(universe, engine2Arr, 1);

      // Assert
      assertEquals(nul0State, level1Engine.inputStateArr[readCell(universe, 6, 0).statePos].inputStates[0]);

      assertEquals(pos0State, level2Engine.inputStateArr[readCell(universe, 6, 1).statePos].inputStates[0]);
      assertEquals(nul0State, level2Engine.inputStateArr[readCell(universe, 7, 1).statePos].inputStates[0]);

      assertEquals(nul0State, level2Engine.inputStateArr[readCell(universe, -1, 1).statePos].inputStates[0]);
      assertEquals(pos0State, level2Engine.inputStateArr[readCell(universe, -1, 1).statePos].inputStates[1]);
      assertEquals(neg0State, level2Engine.inputStateArr[readCell(universe, 1, 1).statePos].inputStates[0]);
      assertEquals(nul0State, level2Engine.inputStateArr[readCell(universe, 1, 1).statePos].inputStates[1]);
   }
}