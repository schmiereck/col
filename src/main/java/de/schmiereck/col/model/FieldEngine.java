package de.schmiereck.col.model;

import java.util.Arrays;
import java.util.Optional;

public class FieldEngine {
   public final Engine[] engineArr;
   public final int maxLevelPos;
   public final int maxMetaStatePos;
   public final int maxDiff;
   public final int maxCellSize;
   public final NextPart[] nextPart1Arr;
   public final NextPart[][][][][] nextPartArr;

   public FieldEngine(final Engine[] engineArr) {
      this.engineArr = engineArr;
      this.maxLevelPos = this.engineArr.length;
      final Optional<Engine> optionalEngine = Arrays.stream(this.engineArr).max((engine1, engine2) -> engine1.metaStateArr.length - engine2.metaStateArr.length);
      this.maxMetaStatePos = optionalEngine.get().metaStateArr.length;
      this.maxDiff = this.engineArr[this.engineArr.length - 1].cellSize * 2;
      this.maxCellSize = Arrays.stream(this.engineArr).max((engine1, engine2) -> engine1.cellSize - engine2.cellSize).get().cellSize;
      this.nextPart1Arr = new NextPart[maxLevelPos * maxLevelPos * maxDiff * maxMetaStatePos * maxMetaStatePos];
      this.nextPartArr = new NextPart[maxLevelPos][maxLevelPos][maxDiff][maxMetaStatePos][maxMetaStatePos];
   }
}
