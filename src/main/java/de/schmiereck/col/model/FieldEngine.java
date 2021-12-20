package de.schmiereck.col.model;

import static de.schmiereck.col.services.FieldEngineService.calcRel2ArrPos;

import java.util.Arrays;
import java.util.Optional;

public class FieldEngine {

   public static final int l0EnginePos = 0;
   public static final int l1EnginePos = 1;
   public static final int l2EnginePos = 2;
   public static final int l1StayEnginePos = 3;

   public final Engine[] engineArr;
   public final int maxEnginePos;
   public final int maxMetaStatePos;
   public final int maxDiff;
   public final int maxCellSize;
   /**
    * maxEnginePos aPart
    * maxEnginePos bPart
    * maxDiff
    * maxMetaStatePos aPart
    * maxMetaStatePos bPart
    */
   public final NextPart[][][][][] nextPartArr;

   public FieldEngine(final Engine[] engineArr) {
      this.engineArr = engineArr;
      this.maxEnginePos = this.engineArr.length;
      final Engine maxMetaStateEngine = Arrays.stream(this.engineArr).max((engine1, engine2) -> engine1.metaStateArr.length - engine2.metaStateArr.length).orElseThrow();
      this.maxMetaStatePos = maxMetaStateEngine.metaStateArr.length;
      final Engine maxCellSizeEngine = Arrays.stream(this.engineArr).max((engine1, engine2) -> engine1.cellSize - engine2.cellSize).orElseThrow();
      this.maxCellSize = maxCellSizeEngine.cellSize;
      this.maxDiff = (this.maxCellSize * 2) - 1;
      this.nextPartArr = new NextPart[this.maxEnginePos][this.maxEnginePos][calcRel2ArrPos(this.maxDiff)][this.maxMetaStatePos][this.maxMetaStatePos];
   }
}
