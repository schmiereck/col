package de.schmiereck.col.services;

import static de.schmiereck.col.services.UniverseService.runCalcNextMetaState;
import static de.schmiereck.col.services.UniverseService.runCalcNextState;
import static de.schmiereck.col.services.UniverseService.runLevelDown;
import static de.schmiereck.col.services.UniverseService.runLevelUp;
import static de.schmiereck.col.services.UniverseUtils.printCells;

import de.schmiereck.col.model.Universe;

public class RunTestUtils {

   public static void runTestNextUpStateDownMeta(final Universe universe, final int cnt) {
      runLevelUp(universe);
      printCells(universe, cnt, "runLevelUp");
      runCalcNextState(universe);
      printCells(universe, cnt, "runCalcNextState");
      runLevelDown(universe);
      printCells(universe, cnt, "runLevelDown");
      runCalcNextMetaState(universe);
      printCells(universe, cnt, "runCalcNextMetaState");
   }

   public static void runTestNextUpStateMeta(final Universe universe, final int cnt) {
      runLevelUp(universe);
      printCells(universe, cnt, "runLevelUp");
      runCalcNextState(universe);
      printCells(universe, cnt, "runCalcNextState");
      runCalcNextMetaState(universe);
      printCells(universe, cnt, "runCalcNextMetaState");
   }

   public static void runTestNextStateMeta(final Universe universe, final int cnt) {
      runCalcNextState(universe);
      printCells(universe, cnt, "runCalcNextState");
      runCalcNextMetaState(universe);
      printCells(universe, cnt, "runCalcNextMetaState");
   }
}
