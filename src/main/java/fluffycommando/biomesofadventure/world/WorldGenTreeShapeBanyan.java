package fluffycommando.biomesofadventure.world;

import net.minecraft.core.block.*;
import net.minecraft.core.block.BlockGrass;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.*;
import java.util.ArrayDeque;
import java.util.Queue;

import java.util.Random;
import java.util.Timer;

public class WorldGenTreeShapeBanyan extends WorldFeature {
	protected int leavesID;
	protected int logID;

	public WorldGenTreeShapeBanyan(int leavesID, int logID) {
		this.leavesID = leavesID;
		this.logID = logID;
	}
	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		int baseHeight = 12;
		int randomHeight = random.nextInt(4);
		int treeHeight = baseHeight + randomHeight;
		if (y < 1 || y + treeHeight + 1 > world.getWorldType().getMaxY()) {
			return false;
		}
		int[] TrunkX = {0, 1, 1, 0, -1, -1, -1, 0, 1};
		int[] TrunkZ = {0, 0, 1, 1, 1, 0, -1, -1, -1};
		//Tree Trunk
		for (int I = 0; I <= 8; I++) {
			for (int K = -1; K <= treeHeight; ++K) {
				world.setBlockWithNotify(x + TrunkX[I], y + K, z + TrunkZ[I], this.logID);
			}
		}
		//Tree Roots
		int[] RootsX = {4, 4, 1, -1, -4, -4, -1, 1, 3, -3, 3, -3};
		int[] RootsZ = {-1, 1, 4, 4, 1, -1, -4, -4, 3, 3, -3, -3};
		for (int I = 0; I <= 11; I++) {
			int L1 = random.nextInt(2);
			int L2 = random.nextInt(2);
			int L3 = random.nextInt(2);
			int T = 0;
			int B = 0;
			int G = 0;
			int H = 0;
			if (RootsX[I] > 1) {
				T = L1;
				G = -1;
			}
			if (RootsZ[I] > 1) {
				B = L1;
				H = -1;
			}
			if (RootsX[I] < -1) {
				T = -L1;
				G = 1;
			}
			if (RootsZ[I] < -1) {
				B = -L1;
				H = 1;
			}
			int RootsX2 = RootsX[I] + T;
			int RootsZ2 = RootsZ[I] + B;
			//Inner tree roots
			for (int K = -1; K <= treeHeight; ++K) {
				world.setBlockWithNotify(x + RootsX2, y + K, z + RootsZ2, this.logID);
			}
			//Inner root feet
			int ran = random.nextInt(6);
			if (ran == 0) {
				world.setBlockWithNotify(x + RootsX2 + G, y, z + RootsZ2 + H, this.logID);
				world.setBlockWithNotify(x + RootsX2 + G, y + 1, z + RootsZ2 + H, this.logID);
			}
			if (ran == 1 || ran == 2) {
				world.setBlockWithNotify(x + RootsX2 + G, y, z + RootsZ2 + H, this.logID);
			}
			int C = 0;
			int E = 0;
			int P = 0;
			int U = 0;
			int P2 = 0;
			int U2 = 0;
			int qx = 0;
			int qz = 0;
			if (RootsX2 > 1) {
				C = 2 + L2;
				P = C - 1;
				P2 = C - 2;
			}
			if (RootsZ2 > 1) {
				E = 2 + L2;
				U = E - 1;
				U2 = E - 2;
			}
			if (RootsX2 < -1) {
				C = -2 - L2;
				P = C + 1;
				P2 = C + 2;
				qx = 1;
			}
			if (RootsZ2 < -1) {
				E = -2 - L2;
				U = E + 1;
				U2 = E + 2;
				qz = 1;
			}
			//outer tree roots
			int RootHeight1 = 3 + random.nextInt(3);
			int RootHeight2 = 3 + random.nextInt(3);
			int RootHeight3 = 3 + random.nextInt(3);
			if ((RootsX2 > 1 || RootsX2 < -1) && (RootsZ2 > 1 || RootsZ2 < -1)) {
				for (int K1 = -1; K1 <= RootHeight1; ++K1) {
					world.setBlockWithNotify(x + RootsX2 + C, y + K1, z + RootsZ2, this.logID);
				}
				for (int K2 = -1; K2 <= RootHeight2; ++K2) {
					world.setBlockWithNotify(x + RootsX2, y + K2, z + RootsZ2 + E, this.logID);
				}
			} else {
				for (int K3 = -1; K3 <= RootHeight3; ++K3) {
					world.setBlockWithNotify(x + RootsX2 + C, y + K3, z + RootsZ2 + E, this.logID);
				}
			}
			//Tree roots connection
			int[] RootsC = {-1, 0, 1, 0, 1};
			for (int F = 0; F <= 4; ++F) {
				if ((RootsX2 > 1 || RootsX2 < -1) && (RootsZ2 > 1 || RootsZ2 < -1)) {
					if (F > 2 && (L2 == 1 || L3 == 1)) {
						world.setBlockWithNotify(x + RootsX2 + P2, y + RootHeight1 + RootsC[F], z + RootsZ2, this.logID);
						world.setBlockWithNotify(x + RootsX2, y + RootHeight2 + RootsC[F], z + RootsZ2 + U2, this.logID);
					} else {
						world.setBlockWithNotify(x + RootsX2 + P, y + RootHeight1 + RootsC[F], z + RootsZ2, this.logID);
						world.setBlockWithNotify(x + RootsX2, y + RootHeight2 + RootsC[F], z + RootsZ2 + U, this.logID);
					}
				} else if (F > 2 && L2 == 1) {
					world.setBlockWithNotify(x + RootsX2 + P2, y + RootHeight3 + RootsC[F], z + RootsZ2 + U2, this.logID);
				} else {
					world.setBlockWithNotify(x + RootsX2 + P, y + RootHeight3 + RootsC[F], z + RootsZ2 + U, this.logID);
				}
			}
			//outer tree feet
			int ran2 = random.nextInt(3);
			if (ran2 == 0) {
				world.setBlockWithNotify(x + RootsX2, y, z + RootsZ2, this.logID);
				world.setBlockWithNotify(x + RootsX2, y + 1, z + RootsZ2, this.logID);
			}
			if (ran2 == 1 ) {
				world.setBlockWithNotify(x + RootsX2, y, z + RootsZ2, this.logID);
			}

			//Lower Tree Branches.
			int BranchPick = random.nextInt(3);
			int BranchNum = random.nextInt(2);
			int BranchPlace1 = RootHeight1 + 1;
			int BranchPlace2 = BranchPlace1 + 3 + random.nextInt(2);
			int l;
			int[][] BranchType = {{-1, 0, 0, 1, 1}, {1, 0, 0, -1, -1}, {-1, 0, 0, 0, -1}};
			int[][] BranchXZ = {{-1, -1, -2, -2, -3}, {1, 1, 2, 2, 3}, {-1, -1, -2, -3, -3}, {1, 1, 2, 3, 3}};

			if (BranchPick == 0) {
				l = 0;
			} else if (BranchPick == 1) {
				l = 1;
			} else {
				l = 2;
				if (qx == 1) {
					qx = 3;
				} else {
					qx = 2;
				}
			}
			for (int i = 0; i <= 4; ++i) {
				if ((RootsX2 > 1 || RootsX2 < -1) && (RootsZ2 > 1 || RootsZ2 < -1)) {

					world.setBlockWithNotify(x + RootsX2 + BranchXZ[qx][i], y + BranchPlace1 + BranchType[l][i], z + RootsZ2, this.logID);
					world.setBlockWithNotify(x + RootsX2, y + BranchPlace1 + BranchType[l][i], z + RootsZ2 + BranchXZ[qz][i], this.logID);

				} else if (RootsX2 > 1 || RootsX2 < -1) {
					world.setBlockWithNotify(x + RootsX2 + BranchXZ[qx][i], y + BranchPlace1 + BranchType[l][i], z + RootsZ2, this.logID);
				} else {
					world.setBlockWithNotify(x + RootsX2, y + BranchPlace1 + BranchType[l][i], z + RootsZ2 + BranchXZ[qz][i], this.logID);
				}
				if (BranchNum == 1) {
					if (RootsX2 > 1 || RootsX2 < -1) {
						world.setBlockWithNotify(x + RootsX2 + BranchXZ[qx][i], y + BranchPlace2 + BranchType[l][i], z + RootsZ2, this.logID);
					} else {
						world.setBlockWithNotify(x + RootsX2, y + BranchPlace2 + BranchType[l][i], z + RootsZ2 + BranchXZ[qz][i], this.logID);
					}
				}
			}
		}
		//Tree Leaves
		int[][] LeavesX = {
				{6, 6, 6, 6, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6, -6, -6, -6, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5},
				{7, 7, 7, 8, 9, 9, 9, 8, 7, 8, 9, 9, 9, 8, 7, 7, 7, 6, 5, 4, 3, 3, 2, 1, 1, 0, -1, -1, -2, -3, -3, -4, -5, -6, -7, -7, -7, -8, -9, -9, -9, -8, -7, -8, -9, -9, -9, -8, -7, -7, -7, -6, -5, -4, -3, -3, -2, -1, -1, 0, 1, 1, 2, 3, 3, 4, 5, 6},
				{11, 12, 10, 11, 12, 12, 11, 12, 10, 9, 10, 11, 8, 10, 8, 8, 7, 7, 6, 5, 5, 4, 3, 2, 1, 0, 0, 0, -1, -2, -2, -3, -4, -5, -6, -6, -7, -8, -9, -9, -10, -11, -10, -11, -12, -10, -11, -12, -10, -11, -12, -9, -10, -11, -12, -12, -11, -10, -10, -11, -9, -8, -9, -8, -7, -7, -6, -5, -5, -4, -3, -3, -3, -2, -2, -1, 0, 1, 2, 3, 4, 5, 5, 6, 6, 7, 8, 8, 9, 8, 9, 10, 11, 10, 11, 10, 11, 12, 12},
				{11, 11, 11, 11, 10, 9, 8, 6, 7, 5, 5, 4, 3, 2, 1, 0, 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -10, -10, -11, -11, -10, -11, -11, -11, -11, -10, -9, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9, 10, 10, 10, 11},
				{10, 10, 9, 8, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6, -7, -8, -8, -9, -9, -9, -9, -9, -10, -9, -8, -7, -7, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 9, 9},
				{7, 7, 6, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6, -7, -6, -7, -8, -7, -7, -6, -6, -5, -4, -3, -2, -1, 0, 1, 2, 2, 3, 4, 5, 6, 7, 6}};
		int[][] LeavesZ = {
				{-2, -1, 0, 1, 2, 3, 4, 5, 6, 6, 6, 6, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6, -6, -6, -6, -6, -5, -4, -3},
				{-6, -5, -4, -3, -3, -2, -1, -1, 0, 1, 1, 2, 3, 3, 4, 5, 6, 7, 7, 7, 8, 9, 9, 9, 8, 7, 8, 9, 9, 9, 8, 7, 7, 7, 6, 5, 4, 3, 3, 2, 1, 1, 0, -1, -1, -2, -3, -3, -4, -5, -6, -7, -7, -7, -8, -9, -9, -9, -8, -7, -8, -9, -9, -9, -8, -7, -7, -7},
				{0, 0, 1, 2, 2, 3, 4, 4, 5, 6, 6, 6, 7, 7, 8, 10, 9, 10, 9, 10, 11, 10, 11, 12, 11, 11, 12, 13, 10, 11, 12, 12, 11, 10, 10, 11, 9, 8, 9, 8, 7, 6, 5, 4, 4, 3, 2, 2, 1, 1, 1, 0, -1, -1, -2, -3, -4, -5, -6, -6, -7, -8, -9, -9, -10, -11, -10, -10, -11, -9, -10, -11, -12, -12, -13, -11, -12, -13, -13, -12, -11, -11, -12, -9, -10, -8, -9, -8, -8, -7, -6, -6, -6, -5, -4, -3, -2, -2, -1},
				{0, 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 9, 10, 10, 10, 11, 12, 11, 11, 11, 10, 10, 9, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -11, -11, -11, -12, -12, -12, -11, -10, -10, -9, -8, -7, -7, -6, -5, -4, -3, -2, -1},
				{0, 1, 2, 3, 4, 5, 6, 7, 7, 8, 8, 9, 9, 10, 9, 9, 8, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6, -7, -7, -7, -8, -9, -9, -9, -10, -10, -10, -9, -8, -7, -7, -6, -5, -4, -3, -2, -1},
				{0, 1, 2, 3, 4, 4, 5, 6, 6, 7, 7, 6, 6, 5, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -4, -5, -6, -7, -8, -7, -6, -7, -6, -5, -5, -4, -3, -2, -1}};
		for (int q = 0; q <= 5; q++) {
			int blockY = treeHeight + q - 1;
			for (int m = 0; m <= LeavesX[q].length - 1; m++) {
				world.setBlockWithNotify(x + LeavesX[q][m], y + blockY, z + LeavesZ[q][m], this.leavesID);
			}
			for (int l = 0; l <= 3; l++) {
				boolean finished = false;
				int DirectionX = 1;
				int DirectionZ = 1;
				if (l == 1) {
					DirectionX = -1;
				}
				if (l == 2) {
					DirectionX = -1;
					DirectionZ = -1;
				}
				if (l == 3) {
					DirectionZ = -1;
				}
				for (int i = 0; i < 8 && !finished; ) {
					boolean Xdone = false;
					boolean Zdone = false;
					boolean rowX = false;
					boolean rowZ = false;
					int BlockX = (i) * DirectionX;
					int BlockZ = (i) * DirectionZ;
					if (i > 2 && world.getBlockId(x + BlockX + DirectionX, y + blockY, z + BlockZ + DirectionZ) == this.leavesID) {
						if (world.getBlockId(x + BlockX, y + blockY, z + BlockZ) == 0) {
							world.setBlockWithNotify(x + BlockX, y + blockY, z + BlockZ, this.leavesID);
						}
						Xdone = true;
						Zdone = true;
					} else {
						world.setBlockWithNotify(x + BlockX, y + blockY, z + BlockZ, this.leavesID);
					}
					for (int l2 = 0; l2 < 20 && !rowX; ) {
						int BlockX1 = (l2 + i + 1) * DirectionX;
						int BlockZ1 = i * DirectionZ;
						if (world.getBlockId(x + BlockX1, y + blockY, z + BlockZ1) == this.leavesID) {
							if (l2 * DirectionX == 0) {
								if (i != 0) {
									Xdone = true;
								}
								break;
							} else {
								rowX = true;
							}
						} else if (world.getBlockId(x + BlockX1, y + blockY, z + BlockZ1) == 0) {
							world.setBlockWithNotify(x + BlockX1, y + blockY, z + BlockZ1, this.leavesID);
							l2++;
						} else {
							l2++;
						}
					}
					for (int l3 = 0; l3 < 20 && !rowZ; ) {
						int BlockX2 = i * DirectionX;
						int BlockZ2 = (l3 + i + 1) * DirectionZ;
						if (world.getBlockId(x + BlockX2, y + blockY, z + BlockZ2) == this.leavesID) {
							if (l3 * DirectionZ == 0) {
								if (i != 0) {
									Zdone = true;
								}
								break;
							} else {
								rowZ = true;
							}
						} else if (world.getBlockId(x + BlockX2, y + blockY, z + BlockZ2) == 0) {
							world.setBlockWithNotify(x + BlockX2, y + blockY, z + BlockZ2, this.leavesID);
							l3++;
						} else {
							l3++;
						}
					}
					if (Xdone && Zdone) {
						if (world.getBlockId(x + BlockX + DirectionX, y + blockY, z + BlockZ + DirectionZ) == 0) {
							world.setBlockWithNotify(x + BlockX + DirectionX, y + blockY, z + BlockZ + DirectionZ, this.leavesID);
						}
						finished = true;
					} else {
						i++;
					}
				}
			}
		}
		//Tree Top Branches
		int[] StraightX = {2, 2, 3, 4, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] StraightZ = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2};
		int[] StraightY = {-2, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 1};
		int[] DiagonalX = {2, 3, 2, 3, 4, 5, 6, 7, 8, 4, 4, 5, 5};
		int[] DiagonalZ = {2, 3, 2, 3, 4, 4, 4, 5, 5, 5, 6, 7, 8};
		int[] DiagonalY = {-1, -1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1};
		int meta = 0;
		for (int w = 0; w <= 3; w++) {
			int B = 1;
			int D = 1;
			if (w == 1) {
				D = -1;
			}
			if (w == 2) {
				B = -1;
			}
			if (w == 3) {
				B = -1;
				D = -1;
			}
			for (int k = 0; k <= 12; k++) {
				world.setBlockAndMetadataWithNotify(x + StraightX[k] * B, y + treeHeight + StraightY[k] - 1, z + StraightZ[k] * D, this.logID, meta);
				world.setBlockAndMetadataWithNotify(x + StraightZ[k] * B, y + treeHeight + StraightY[k] - 1, z + StraightX[k] * D, this.logID, meta);
				world.setBlockAndMetadataWithNotify(x + DiagonalX[k] * B, y + treeHeight + DiagonalY[k] - 1, z + DiagonalZ[k] * D, this.logID, meta);
			}
		}
		//Circle generation
		int Circumference;
		double Pi = 3.14159;
		double radius = 5;
		int degrees = 360;

		Circumference = (int) (2 * Pi * radius);

		for (int f = 0; f <= Circumference; f++) {

			//current distance < radius

		}
		return true;
	}
}
