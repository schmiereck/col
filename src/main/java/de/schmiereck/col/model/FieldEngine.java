package de.schmiereck.col.model;

import static de.schmiereck.col.services.EngineService.calcMetaStateSize;
import static de.schmiereck.col.services.FieldEngineService.calcRel2ArrPos;
import static de.schmiereck.col.services.engine.CreateEngineService.metaPos;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.LEFTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.RIGHTa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel0SpinMoveEngineService.STAYa_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.LEFTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.NULL_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.RIGHTa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_p1_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel1SpinMoveEngineService.STAYa_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.LEFTa_p1_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.LEFTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.NULL_u0_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_p1_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.RIGHTa_u0_u0_p1;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.STAYa_p1_u0_u0;
import static de.schmiereck.col.services.engine.spinMove.CreateLevel2SpinMoveEngineService.STAYa_u0_u0_p1;

import java.util.Arrays;
import java.util.Objects;

public class FieldEngine {

   public static final int l0EnginePos = 0;
   public static final int l1EnginePos = 1;
   public static final int l2EnginePos = 2;
   public static final int l0StayEnginePos = 3;
   public static final int l1StayEnginePos = 4;
   public static final int MaxEngineSize = 5;

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

   public static final int NPMS_L0_S1_Pos = 0;
   public static final int NPMS_L1_S00_S01_Pos = 0;
   public static final int NPMS_L1_S01_S00_Pos = 1;
   public static final int NPMS_L1_S10_S00_Pos = 2;
   public static final int NPMS_L1_S00_S10_Pos = 3;
   public static final int NPMS_L2_S001_S000_S000_Pos = 0;
   public static final int NPMS_L2_S000_S000_S100_Pos = 1;
   public static final int NPMS_L2_S000_S000_S001_Pos = 2;
   public static final int NPMS_L2_S000_S100_S000_Pos = 3;

   public final int[][] nextPartMetaStatePosArr[];

   public FieldEngine(final Engine[] engineArr) {
      this.engineArr = engineArr;
      this.maxEnginePos = this.engineArr.length;
      final Engine maxMetaStateEngine = Arrays.stream(this.engineArr).filter(engine1 -> Objects.nonNull(engine1)).max((engine1, engine2) -> engine1.metaStateArr.length - engine2.metaStateArr.length).orElseThrow();
      this.maxMetaStatePos = maxMetaStateEngine.metaStateArr.length;
      final Engine maxCellSizeEngine = Arrays.stream(this.engineArr).filter(engine1 -> Objects.nonNull(engine1)).max((engine1, engine2) -> engine1.cellSize - engine2.cellSize).orElseThrow();
      this.maxCellSize = maxCellSizeEngine.cellSize;
      this.maxDiff = (calcMetaStateSize(maxCellSizeEngine) * 2) - 1;
      this.nextPartArr = new NextPart[this.maxEnginePos][this.maxEnginePos][calcRel2ArrPos(this.maxDiff)][this.maxMetaStatePos][this.maxMetaStatePos];

      final Engine l0E = this.engineArr[l0EnginePos];
      final Engine l1E = this.engineArr[l1EnginePos];
      final Engine l2E = this.engineArr[l2EnginePos];
      final Engine l1SE = this.engineArr[l1StayEnginePos];

      this.nextPartMetaStatePosArr = new int[MaxEngineSize][this.maxCellSize * this.maxCellSize][];

      this.nextPartMetaStatePosArr[l0EnginePos][NPMS_L0_S1_Pos] = new int[]{metaPos(l0E, STAYa_p1), metaPos(l0E, LEFTa_p1), metaPos(l0E, RIGHTa_p1)};
      this.nextPartMetaStatePosArr[l1EnginePos][NPMS_L1_S00_S01_Pos] = new int[]{metaPos(l1E, NULL_u0_u0, STAYa_u0_p1), metaPos(l1E, NULL_u0_u0, LEFTa_u0_p1), metaPos(l1E, NULL_u0_u0, RIGHTa_u0_p1)};
      this.nextPartMetaStatePosArr[l1EnginePos][NPMS_L1_S01_S00_Pos] = new int[]{metaPos(l1E, STAYa_u0_p1, NULL_u0_u0), metaPos(l1E, LEFTa_u0_p1, NULL_u0_u0), metaPos(l1E, RIGHTa_u0_p1, NULL_u0_u0)};
      this.nextPartMetaStatePosArr[l1EnginePos][NPMS_L1_S10_S00_Pos] = new int[]{metaPos(l1E, STAYa_p1_u0, NULL_u0_u0), metaPos(l1E, LEFTa_p1_u0, NULL_u0_u0), metaPos(l1E, RIGHTa_p1_u0, NULL_u0_u0)};
      this.nextPartMetaStatePosArr[l1EnginePos][NPMS_L1_S00_S10_Pos] = new int[]{metaPos(l1E, NULL_u0_u0, STAYa_p1_u0), metaPos(l1E, NULL_u0_u0, LEFTa_p1_u0), metaPos(l1E, NULL_u0_u0, RIGHTa_p1_u0)};
      this.nextPartMetaStatePosArr[l2EnginePos][NPMS_L2_S001_S000_S000_Pos] = new int[]{
              metaPos(l2E, STAYa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0),
              metaPos(l2E, LEFTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0),
              metaPos(l2E, RIGHTa_u0_u0_p1, NULL_u0_u0_u0, NULL_u0_u0_u0)};
      this.nextPartMetaStatePosArr[l2EnginePos][NPMS_L2_S000_S000_S100_Pos] = new int[]{
              metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, STAYa_p1_u0_u0),
              metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, LEFTa_p1_u0_u0),
              metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_p1_u0_u0)};
      this.nextPartMetaStatePosArr[l2EnginePos][NPMS_L2_S000_S000_S001_Pos] = new int[]{
              metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, STAYa_u0_u0_p1),
              metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, LEFTa_u0_u0_p1),
              metaPos(l2E, NULL_u0_u0_u0, NULL_u0_u0_u0, RIGHTa_u0_u0_p1)};
      this.nextPartMetaStatePosArr[l2EnginePos][NPMS_L2_S000_S100_S000_Pos] = new int[]{
              metaPos(l2E, NULL_u0_u0_u0, STAYa_p1_u0_u0, NULL_u0_u0_u0),
              metaPos(l2E, NULL_u0_u0_u0, LEFTa_p1_u0_u0, NULL_u0_u0_u0),
              metaPos(l2E, NULL_u0_u0_u0, RIGHTa_p1_u0_u0, NULL_u0_u0_u0)};
   }
}
