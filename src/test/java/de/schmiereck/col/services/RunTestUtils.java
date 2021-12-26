package de.schmiereck.col.services;

import static de.schmiereck.col.services.UniverseService.runCalcNextMetaState2;
import static de.schmiereck.col.services.UniverseService.runCalcNextPart;
import static de.schmiereck.col.services.UniverseUtils.printCells;

import de.schmiereck.col.model.Part;
import de.schmiereck.col.model.Universe;

public class RunTestUtils {

   public static void runTestNextUpDownMeta(final Universe universe, final Part part, final int cnt) {
      //runLevelUp(universe);
      printCells(universe, part, cnt, "runLevelUp");
      //runLevelDown(universe);
      printCells(universe, part, cnt, "runLevelDown");
      runCalcNextMetaState2(universe);
      printCells(universe, part, cnt, "runCalcNextMetaState");
   }

   public static void runTestNextUpMeta(final Universe universe, final Part part, final int cnt) {
      //runLevelUp(universe);
      printCells(universe, part, cnt, "runLevelUp");
      runCalcNextMetaState2(universe);
      printCells(universe, part, cnt, "runCalcNextMetaState");
   }

   public static void runTestNextMeta2(final Universe universe, final Part part, final int cnt) {
      runCalcNextMetaState2(universe);
      printCells(universe, part, cnt, "runCalcNextMetaState");
   }

   public static void runTestNextPart(final Universe universe, final Part part, final int cnt) {
      runCalcNextPart(universe);
      printCells(universe, part, cnt, "runCalcNextPart");
   }

   public static void runTestNextPartMeta(final Universe universe, final Part part, final int cnt) {
      runCalcNextPart(universe);
      printCells(universe, part, cnt, "runCalcNextPart");
      runCalcNextMetaState2(universe);
      printCells(universe, part, cnt, "runCalcNextMetaState");
   }

   public static void runTestNextMetaPart(final Universe universe, final int cnt) {
      runCalcNextMetaState2(universe);
      printCells(universe, cnt, "runCalcNextMetaState");
      runCalcNextPart(universe);
      printCells(universe, cnt, "runCalcNextPart");
   }
}
