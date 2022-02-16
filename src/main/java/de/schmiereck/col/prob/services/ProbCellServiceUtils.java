package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbField.FieldLeft;
import static de.schmiereck.col.prob.model.ProbField.FieldRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbLeft;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;
import static de.schmiereck.col.prob.services.ProbCellService.Max_Probability;
import static de.schmiereck.col.utils.IntMathUtils.absIM;

import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.Part;
import de.schmiereck.col.prob.model.ProbCell;
import de.schmiereck.col.prob.model.ProbField;
import de.schmiereck.col.prob.model.ProbUniverse;

import java.util.Objects;
import java.util.function.Function;

public class ProbCellServiceUtils {

   public static void printProbLine(final int cnt, final ProbUniverse probUniverse) {
      final ProbCell[] probCellArr = probUniverse.probCellArr;

      System.out.printf("%2d:e", cnt);
      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         printProb(probCell);
         System.out.printf("| ");
      }
      System.out.printf("\n");

      printOutProbFieldLine("e", probCellArr,
              (final ProbCell probCell) -> { return probCell.eProbFieldArr; },
              (final ProbCell probCell) -> { return probCell.eOutPart; });
      printInProbFieldLine("e", probCellArr,
              (final ProbCell probCell) -> { return probCell.eProbFieldArr; },
              (final ProbCell probCell) -> { return probCell.eInPart; });
      printOutProbFieldLine("p", probCellArr,
              (final ProbCell probCell) -> { return probCell.pProbFieldArr; },
              (final ProbCell probCell) -> { return probCell.pPart; });
      printInProbFieldLine("p", probCellArr,
              (final ProbCell probCell) -> { return probCell.pProbFieldArr; },
              (final ProbCell probCell) -> { return probCell.pPart; });
   }

   private static void printInProbFieldLine(final String fieldName, final ProbCell[] probCellArr,
                                            final Function<ProbCell, ProbField[]> probFieldFunction,
                                            final Function<ProbCell, Part> partFunction) {
      System.out.printf("%s-i ", fieldName);
      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbField[] probFieldArr = probFieldFunction.apply(probCell);
         final Part part = partFunction.apply(probCell);
         printInProbField(part, probFieldArr);
      }
      System.out.printf("\n");
   }

   private static void printInProbField(final Part part, final ProbField[] probFieldArr) {
      final int eInField;
      if (Objects.nonNull(part)) {
         eInField = 0;//part.inField;
      } else {
         eInField = 0;
      }
      System.out.printf("%3d %3d %3d    ",
              probFieldArr[FieldLeft].inField, eInField, probFieldArr[FieldRight].inField);
      System.out.printf("| ");
   }

   private static void printOutProbFieldLine(final String fieldName, final ProbCell[] probCellArr,
                                             final Function<ProbCell, ProbField[]> probFieldFunction,
                                             final Function<ProbCell, Part> partFunction) {
      System.out.printf("%s-o ", fieldName);
      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbField[] probFieldArr = probFieldFunction.apply(probCell);
         final Part part = partFunction.apply(probCell);
         printOutProbField(part, probFieldArr);
      }
      System.out.printf("\n");
   }

   private static void printOutProbField(final Part part, final ProbField[] probFieldArr) {
      final int eOutField;
      if (Objects.nonNull(part)) {
         eOutField = part.outField;
      } else {
         eOutField = 0;
      }
      String pLStr = (Objects.nonNull(probFieldArr[FieldLeft].sourcePart)) ? "<" : " ";
      String pRStr = (Objects.nonNull(probFieldArr[FieldRight].sourcePart)) ? ">" : " ";
      System.out.printf("%3d %3d %3d %s%s ",
              probFieldArr[FieldLeft].outField, eOutField, probFieldArr[FieldRight].outField, pLStr, pRStr);
      System.out.printf("| ");
   }

   private static void printProb(final ProbCell probCell) {
      if (Objects.nonNull(probCell.eOutPart)) {
         System.out.printf("%3d %3d %3d (%1d)",
                 probCell.eOutPart.outProb.probabilityArr[DirProbLeft],
                 probCell.eOutPart.outProb.probabilityArr[DirProbStay],
                 probCell.eOutPart.outProb.probabilityArr[DirProbRight],
                 probCell.eOutPart.outProb.lastProbabilityPos);
      } else {
         System.out.printf("--- --- --- (-)");
      }
   }

   public static void calcImpulse(final Probability inProb, final Probability outProb, final ProbCell pProbCell) {
      final int pol = pProbCell.pProbFieldArr[FieldLeft].outField;
      final int por = pProbCell.pProbFieldArr[FieldRight].outField;
      //final int pod = absIM(por - pol);
      //final int pod = absIM(pol - por);
      final int pod = (por - pol);
      final int apodt = absIM(pod);
      final int apod;
      if (apodt > Max_Probability) {
         // TODO reflect the other p-part
         apod = Max_Probability;
      } else {
         apod = apodt;
      }

      final int ol = outProb.probabilityArr[DirProbLeft];
      final int os = outProb.probabilityArr[DirProbStay];
      final int or = outProb.probabilityArr[DirProbRight];
      final int olm = Max_Probability - ol;
      final int osm = Max_Probability - os;
      final int orm = Max_Probability - or;

      final int odl = weightIM(ol, apod, Max_Probability);
      final int ods = weightIM(os, apod, Max_Probability);
      final int odr = weightIM(or, apod, Max_Probability);

      //final int dl = minIM(or, pol);
      //final int dr = minIM(ol, por);
      //final int odd = Max_Probability - od;

      final int il;
      final int is;
      final int ir;
      // To Left?
      if (pod < 0) {
         ir = or - odr;
         if (ir > 0) {
            is = os + odr;
            il = ol;
         } else {
            is = os + odr - ods;
            il = ol + ods;
         }
      } else {
         // To Right.
         il = ol - odl;
         if (il > 0) {
            is = os + odl;
            ir = or;
         } else {
            is = os + odl - ods;
            ir = or + ods;
         }
      }
      inProb.probabilityArr[DirProbLeft] = il;
      inProb.probabilityArr[DirProbStay] = is;
      inProb.probabilityArr[DirProbRight] = ir;
   }

   private static int weightIM(final int ol, final int apod, final int maxWeight) {
      final int odlt = ((ol * apod) / maxWeight);
      final int odl = odlt > 0 ? odlt : ((ol * apod) % maxWeight) > 0 ? 1 : 0;
      return odl;
   }
}
