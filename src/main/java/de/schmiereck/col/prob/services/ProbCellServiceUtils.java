package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbField.FieldLeft;
import static de.schmiereck.col.prob.model.ProbField.FieldRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbLeft;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;
import static de.schmiereck.col.prob.services.ProbCellService.Max_Probability;
import static de.schmiereck.col.utils.IntMathUtils.absIM;

import de.schmiereck.col.model.Probability;
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

      printOutProbFieldLine("e", probCellArr, (final ProbCell probCell) -> { return probCell.eProbField; });
      printInProbFieldLine("e", probCellArr, (final ProbCell probCell) -> { return probCell.eProbField; });
      printOutProbFieldLine("p", probCellArr, (final ProbCell probCell) -> { return probCell.pProbField; });
      printInProbFieldLine("p", probCellArr, (final ProbCell probCell) -> { return probCell.pProbField; });
   }

   private static void printInProbFieldLine(final String fieldName, final ProbCell[] probCellArr, final Function<ProbCell, ProbField> probFieldFunction) {
      System.out.printf("%s-i ", fieldName);
      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbField probField = probFieldFunction.apply(probCell);
         printInProbField(probField);
      }
      System.out.printf("\n");
   }

   private static void printInProbField(final ProbField probField) {
      System.out.printf("%3d %3d %3d    ", probField.inFieldArr[FieldLeft], probField.inField, probField.inFieldArr[FieldRight]);
      System.out.printf("| ");
   }

   private static void printOutProbFieldLine(final String fieldName, final ProbCell[] probCellArr, final Function<ProbCell, ProbField> probFieldFunction) {
      System.out.printf("%s-o ", fieldName);
      for (int pos = 0; pos < probCellArr.length; pos++) {
         final ProbCell probCell = probCellArr[pos];
         final ProbField probField = probFieldFunction.apply(probCell);
         printOutProbField(probField);
      }
      System.out.printf("\n");
   }

   private static void printOutProbField(final ProbField probField) {
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

   public static void calcImpulse(final Probability inProb, final Probability outProb, final ProbField pProbField) {
      final int pol = pProbField.outFieldArr[FieldLeft];
      final int por = pProbField.outFieldArr[FieldRight];
      //final int pod = absIM(por - pol);
      //final int pod = absIM(pol - por);
      final int pod = (por - pol);
      final int apod = absIM(pod);

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

   public static void calcImpulse_wrong(final Probability inProb, final Probability outProb, final ProbField pProbField) {
      final int pol = pProbField.outFieldArr[FieldLeft];
      final int por = pProbField.outFieldArr[FieldRight];
      //final int pod = absIM(por - pol);
      //final int pod = absIM(pol - por);
      final int pod = (por - pol);
      final int apod = absIM(pod);

      final int ol = outProb.probabilityArr[DirProbLeft];
      final int os = outProb.probabilityArr[DirProbStay];
      final int or = outProb.probabilityArr[DirProbRight];
      final int olm = Max_Probability - ol;
      final int osm = Max_Probability - os;
      final int orm = Max_Probability - or;
      final int odl = ((ol * apod) / Max_Probability);
      final int ods = ((os * apod) / Max_Probability);
      final int odr = ((or * apod) / Max_Probability);

      //final int dl = minIM(or, pol);
      //final int dr = minIM(ol, por);
      //final int odd = Max_Probability - od;

      final int il;
      final int is;
      final int ir;
      if (pod < 0) {
         il = ol + ods;
         is = os - ods + odr;
         ir = or - odr;
      } else {
         il = ol - odl;
         is = os - ods + odl;
         ir = or + ods;
      }
      inProb.probabilityArr[DirProbLeft] = il;
      inProb.probabilityArr[DirProbStay] = is;
      inProb.probabilityArr[DirProbRight] = ir;
   }
}
