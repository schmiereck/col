package de.schmiereck.col.prob.services;

import static de.schmiereck.col.prob.model.ProbField.FieldLeft;
import static de.schmiereck.col.prob.model.ProbField.FieldRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbLeft;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbRight;
import static de.schmiereck.col.prob.services.ProbCellService.DirProbStay;
import static de.schmiereck.col.prob.services.ProbCellService.LR_REFLECTION_MATRIX;
import static de.schmiereck.col.prob.services.ProbCellService.addDiff;
import static de.schmiereck.col.prob.services.ProbCellService.copyOut2In;
import static de.schmiereck.col.services.ProbabilityService.calcOperation;

import de.schmiereck.col.model.Probability;
import de.schmiereck.col.prob.model.ProbCell;

public class ProbCellInServiceUtils {

   public static void calcInProb(final ProbCell probCell, final ProbCell lProbCell, final ProbCell rProbCell) {
      // calcOperation()

      final Probability inProb = probCell.inProb;
      final Probability outProb = probCell.outProb;
      final Probability lOutProb = lProbCell.outProb;
      final Probability rOutProb = rProbCell.outProb;

      //    -> <-
      //    --> <-
      //    -> <--
      //    X ->
      switch (outProb.lastProbabilityPos) {
         //    b: a:?   b:<-  c:?   -----------------------
         case DirProbLeft -> {
            if (lProbCell.pProbField.outFieldArr[FieldRight] > 0) {
               calcOperation(probCell.inProb, probCell.outProb, LR_REFLECTION_MATRIX);
               // TODO P Event Remove  !!!
            } else {
               switch (lOutProb.lastProbabilityPos) {
                  //    b: a:->  b:<-  c:?
                  case DirProbRight -> {
                     switch (rOutProb.lastProbabilityPos) {
                        //    b: a:->  b:<-  c:<-
                        case DirProbLeft -> addDiff(probCell, rProbCell);
                        //    b: a:->  b:<-  c:X
                        case DirProbStay -> addDiff(probCell, lProbCell);
                        //    b: a:->  b:<-  c:->
                        case DirProbRight -> addDiff(probCell, lProbCell);
                        //default -> copy(probCell);
                        default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                     }
                  }
                  //    b: a:X   b:<-  c:?
                  case DirProbStay -> {
                     switch (rOutProb.lastProbabilityPos) {
                        //    b: a:X   b:<-  c:<-
                        case DirProbLeft -> addDiff(probCell, lProbCell);
                        //    b: a:X   b:<-  c:X
                        case DirProbStay -> addDiff(probCell, lProbCell);
                        //    b: a:X   b:<-  c:->
                        case DirProbRight -> copyOut2In(probCell);
                        default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                     }
                  }
                  //    b: a:<-  b:<-  c:?
                  case DirProbLeft -> {
                     switch (rOutProb.lastProbabilityPos) {
                        //    b: a:<-  b:<-  c:<-
                        case DirProbLeft -> addDiff(probCell, lProbCell);
                        //    b: a:<-  b:<-  c:X
                        case DirProbStay -> addDiff(probCell, lProbCell);
                        //    b: a:<-  b:<-  c:->
                        case DirProbRight -> addDiff(probCell, lProbCell);
                        default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                     }
                  }
                  //default -> copy(probCell);
                  default -> throw new IllegalStateException("Unexpected value: " + lOutProb.lastProbabilityPos);
               }
            }
         }
         //    b: a:?   b:X   c:?   -----------------------
         case DirProbStay -> {
            if ((probCell.pProbField.outFieldArr[FieldRight] > 0) && (probCell.pProbField.outFieldArr[FieldLeft] > 0)) {
               copyOut2In(probCell);
            } else {
               switch (lOutProb.lastProbabilityPos) {
                  //    b: a:->  b:X   c:?
                  case DirProbRight -> {
                     switch (rOutProb.lastProbabilityPos) {
                        //    b: a:->  b:X   c:<-
                        case DirProbLeft -> addDiff(probCell, rProbCell);
                        //    b: a:->  b:X   c:X
                        case DirProbStay -> addDiff(probCell, lProbCell);
                        //    b: a:->  b:X   c:->
                        case DirProbRight -> addDiff(probCell, lProbCell);
                        //default -> copy(probCell);
                        default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                     }
                  }
                  //    b: a:X   b:X   c:?
                  case DirProbStay -> {
                     switch (rOutProb.lastProbabilityPos) {
                        //    b: a:X   b:X   c:<-
                        case DirProbLeft -> addDiff(probCell, rProbCell);
                        //    b: a:X   b:X   c:X
                        case DirProbStay -> copyOut2In(probCell);
                        //    b: a:X   b:X   c:->
                        case DirProbRight -> copyOut2In(probCell);
                        default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                     }
                  }
                  //    b: a:<-  b:X   c:?
                  case DirProbLeft -> {
                     switch (rOutProb.lastProbabilityPos) {
                        //    b: a:<-  b:X   c:<-
                        case DirProbLeft -> addDiff(probCell, rProbCell);
                        //    b: a:<-  b:X   c:X
                        case DirProbStay -> copyOut2In(probCell);
                        //    b: a:<-  b:X   c:->
                        case DirProbRight -> copyOut2In(probCell);
                        default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                     }
                  }
                  //default -> copy(probCell);
                  default -> throw new IllegalStateException("Unexpected value: " + lOutProb.lastProbabilityPos);
               }
            }
         }
         //    b: a:?   b:->  c:?   -----------------------
         case DirProbRight -> {
            // c is pField?
            if (rProbCell.pProbField.outFieldArr[FieldLeft] > 0) {
               calcOperation(probCell.inProb, probCell.outProb, LR_REFLECTION_MATRIX);
            } else {
               switch (lOutProb.lastProbabilityPos) {
                  //    b: a:->  b:->  c:?
                  case DirProbRight -> {
                     switch (rOutProb.lastProbabilityPos) {
                        //    b: a:->  b:->  c:<-
                        case DirProbLeft -> addDiff(probCell, rProbCell);
                        //    b: a:->  b:->  c:X
                        case DirProbStay -> addDiff(probCell, rProbCell);
                        //    b: a:->  b:->  c:->
                        case DirProbRight -> addDiff(probCell, lProbCell);
                        //default -> copy(probCell);
                        default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                     }
                  }
                  //    b: a:X   b:X   c:?
                  case DirProbStay -> {
                     switch (rOutProb.lastProbabilityPos) {
                        //    b: a:X   b:->  c:<-
                        case DirProbLeft -> addDiff(probCell, rProbCell);
                        //    b: a:X   b:->  c:X
                        case DirProbStay -> addDiff(probCell, rProbCell);
                        //    b: a:X   b:->  c:->
                        case DirProbRight -> copyOut2In(probCell);
                        default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                     }
                  }
                  //    b: a:<-  b:X   c:?
                  case DirProbLeft -> {
                     switch (rOutProb.lastProbabilityPos) {
                        //    b: a:<-  b:X   c:<-
                        case DirProbLeft -> addDiff(probCell, rProbCell);
                        //    b: a:<-  b:X   c:X
                        case DirProbStay -> copyOut2In(probCell);
                        //    b: a:<-  b:X   c:->
                        case DirProbRight -> copyOut2In(probCell);
                        default -> throw new IllegalStateException("Unexpected value: " + rOutProb.lastProbabilityPos);
                     }
                  }
                  //default -> copy(probCell);
                  default -> throw new IllegalStateException("Unexpected value: " + lOutProb.lastProbabilityPos);
               }
            }
         }
         //default -> copy(probCell);
         default -> throw new IllegalStateException("Unexpected value: " + outProb.lastProbabilityPos);
      }
      //switch (rOutProb.lastProbabilityPos) {
      //   case DirProbLeft ->  addDiff(probCell, rProbCell);
      //}
   }
}
