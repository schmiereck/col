package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbField.FieldLeft;
import static de.schmiereck.col.prob.model.ProbField.FieldRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbLeft;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;

import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbField;
import de.schmiereck.col.prob.model.ProbUniverse;

import java.util.function.Function;

public class ProbCellServiceUtils {

   public static void printProbLine(final int cnt, final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      System.out.printf("%2d: ", cnt);
      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         printProb(probCell);
         System.out.printf("| ");
      }
      System.out.printf("\n");

      printProbFieldLine("e", probCellArr, (final ProbCell probCell) -> { return probCell.eProbField; });
      printProbFieldLine("p", probCellArr, (final ProbCell probCell) -> { return probCell.pProbField; });
   }

   private static void printProbFieldLine(final String fieldName, final ProbCell[] probCellArr, final Function<ProbCell, ProbField> probFieldFunction) {
      System.out.printf("%s   ", fieldName);
      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbField probField = probFieldFunction.apply(probCell);
         printProbField(probField);
      }
      System.out.printf("\n");
   }

   private static void printProbField(final ProbField probField) {
      System.out.printf("%3d %3d %3d    ", probField.outFieldArr[FieldLeft], probField.outField, probField.outFieldArr[FieldRight]);
      System.out.printf("| ");
   }

   private static void printProb(final ProbCell probCell) {
      System.out.printf("%3d %3d %3d (%1d)",
              probCell.outProb.probabilityArr[DirProbLeft],
              probCell.outProb.probabilityArr[DirProbStay],
              probCell.outProb.probabilityArr[DirProbRight],
              probCell.outProb.lastProbabilityPos);
   }
}
