package fluffycommando.biomesofadventure.world;

import net.minecraft.core.block.*;
import net.minecraft.core.block.BlockGrass;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

public class WorldGenTreeShapeKapokLarge extends WorldFeature {
    protected int leavesID;
    protected int logID;
    protected int treeSpawn;

    public WorldGenTreeShapeKapokLarge(int leavesID, int logID, int treeSpawn) {
        this.leavesID = leavesID;
        this.logID = logID;
        this.treeSpawn = treeSpawn;
    }
    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        int baseHeight = 20;
        int randomHeight = random.nextInt(10);
        int treeHeight = baseHeight + randomHeight;
        if (y < 1 || y + treeHeight + 1 > world.getWorldType().getMaxY()) {
            return false;
        }
        int i2 = world.getBlockId(x, y - 1, z);
        if (!(Block.blocksList[i2] instanceof BlockGrass) && i2 != Block.dirt.id || y >= world.getWorldType().getMaxY() - treeHeight - 1) {
            return false;
        }
        int[] TrunkX = {0,1,1,1,1,0,0,-1,-1,-1,-1,0};
        int[] TrunkZ = {1,1,0,0,-1,-1,-1,-1,0,0,1,1};
        int[] StartX = {1,1,0,0,1,1,0,0,0,0,-1,-1,0,0,-1,-1};
        int[] StartZ = {1,0,0,1,0,-1,-1,0,0,-1,-1,0,1,0,0,1};
        int s1 = 0; int s2 = 0; int s3 = 0;
        int k1 = 0; int k2 = 0; int k3 = 0; int k4 = 0;
        if (treeSpawn == 1) {
            s1 = 0; s2 = 1; s3 = 2;
            k1 = 0; k2 = 1; k3 = 2; k4 = 3;
        }
        if (treeSpawn == 2) {
            s1 = 3; s2 = 4; s3 = 5;
            k1 = 4; k2 = 5; k3 = 6; k4 = 7;
        }
        if (treeSpawn == 3) {
            s1 = 6; s2 = 7; s3 = 8;
            k1 = 8; k2 = 9; k3 = 10; k4 = 11;
        }
        if (treeSpawn == 4) {
            s1 = 9; s2 = 10; s3 = 11;
            k1 = 12; k2 = 13; k3 = 14; k4 = 15;
        }
        for (int CheckY = 1; CheckY <= treeHeight + 1; CheckY++) {
            int check1 = world.getBlockId(x,y + CheckY,z);
            int check2 = world.getBlockId(x + TrunkX[s1],y + CheckY,z + TrunkZ[s1]);
            int check3 = world.getBlockId(x + TrunkX[s2],y + CheckY,z + TrunkZ[s2]);
            int check4 = world.getBlockId(x + TrunkX[s3],y + CheckY,z + TrunkZ[s3]);
            if (check1 != 0 || check2 != 0 || check3 != 0 || check4 != 0) {
                if (check1 != this.leavesID || check2 != this.leavesID || check3 != this.leavesID || check4 != this.leavesID) {
                    return false;
                }
            }
        }
        //Tree Leaves
        int leafCheck;
        int[][] Row1 = {{4,4,4,4,5,4,2,3,2,0},{9,9,9,7,8,6,6,4,4,0},{8,8,6,5,6,5,3,3,0,0},{5,5,4,3,1,0,0,0,0,0}};
        int[][] Row2 = {{-4,-4,-4,-4,-5,-4,-2,-3,-2,0},{-9,-9,-9,-7,-8,-6,-6,-4,-4,-0},{-8,-8,-6,-5,-6,-5,-3,-3,0,0},{-5,-5,-4,-3,-1,0,0,0,0,0}};
        int[] Skip = {4,4,3,2,2,1,1,1,0,0};
        int i;
        int l = 0;
        int leavesX;
        int leavesZ;
        int Y1;
        int X1;
        int Z1;
        for (int leavesY = -2; leavesY <= 1;) {
            for (int Q = 1; Q <= 4; ++Q) {
                if (leavesY == -1) {
                    l = 1;
                } else if (leavesY == 0) {
                    l = 2;
                } else if (leavesY == 1) {
                    l = 3;
                }
                if (Q == 1) {
                    i = 0;
                    leavesZ = 0;
                    for (leavesX = 0; leavesX <= Row1[l][i]; ++leavesX) {
                        if (i < 9) {
                            if (leavesX == Row1[l][i]) {
                                leavesZ++;
                                leavesX = 0;
                                i++;
                            }
                        }
                        X1 = StartX[k1] + leavesX;
                        Z1 = StartZ[k1] + leavesZ;
                        Y1 = treeHeight + leavesY;
                        if (leavesY == -2) {
                            X1 = StartX[k1] + leavesX + Skip[i];
                        }
                        leafCheck = world.getBlockId(x + X1,y + Y1, z + Z1);
                        if (leafCheck == 0) {
                            world.setBlockWithNotify(x + X1, y + Y1, z + Z1, this.leavesID);
                        }
                    }
                }
                if (Q == 2) {
                    i = 0;
                    leavesX = 0;
                    for (leavesZ = 0; leavesZ >= Row2[l][i]; --leavesZ) {
                        if (i < 9) {
                            if (leavesZ == Row2[l][i]) {
                                leavesX++;
                                leavesZ = 0;
                                i++;
                            }
                        }
                        X1 = StartX[k2] + leavesX;
                        Z1 = StartZ[k2] + leavesZ;
                        Y1 = treeHeight + leavesY;
                        if (leavesY == -2) {
                            Z1 = StartX[k2] + leavesZ - Skip[i];
                        }
                        leafCheck = world.getBlockId(x + X1,y + Y1, z + Z1);
                        if (leafCheck == 0) {
                            world.setBlockWithNotify(x + X1, y + Y1, z + Z1, this.leavesID);
                        }
                    }
                }
                if (Q == 3) {
                    i = 0;
                    leavesZ = 0;
                    for (leavesX = 0; leavesX >= Row2[l][i]; --leavesX) {
                        if (i < 9) {
                            if (leavesX == Row2[l][i]) {
                                leavesZ--;
                                leavesX = 0;
                                i++;
                            }
                        }
                        X1 = StartX[k3] + leavesX;
                        Z1 = StartZ[k3] + leavesZ;
                        Y1 = treeHeight + leavesY;
                        if (leavesY == -2) {
                            X1 = StartX[k3] + leavesX - Skip[i];
                        }
                        leafCheck = world.getBlockId(x + X1,y + Y1, z + Z1);
                        if (leafCheck == 0) {
                            world.setBlockWithNotify(x + X1, y + Y1, z + Z1, this.leavesID);
                        }
                    }
                }
                if (Q == 4) {
                    i = 0;
                    leavesX = 0;
                    for (leavesZ = 0; leavesZ <= Row1[l][i]; ++leavesZ) {
                        if (i < 9) {
                            if (leavesZ == Row1[l][i]) {
                                leavesX--;
                                leavesZ = 0;
                                i++;
                            }
                        }
                        X1 = StartX[k4] + leavesX;
                        Z1 = StartZ[k4] + leavesZ;
                        Y1 = treeHeight + leavesY;
                        if (leavesY == -2) {
                            Z1 = StartX[k4] + leavesZ + Skip[i];
                        }
                        leafCheck = world.getBlockId(x + X1,y + Y1, z + Z1);
                        if (leafCheck == 0) {
                            world.setBlockWithNotify(x + X1, y + Y1, z + Z1, this.leavesID);
                        }
                    }
                }
            }
            ++leavesY;
        }
        // Tree Top
        for (int Q = 1; Q <= 4; ++Q) {
            for (int I = 0; I <= 15; ) {
                int[] Log1 = {1,1,1,1,2,2,2,2,2,3,4,4,5,6,7,7};
                int[] Log2X = {1,1,1,2,2,2,3,3,4,4};
                int[] Log2Z = {0,1,1,1,1,2,2,2,3,3};
                int[] LogY1 = {6,5,4,3,4,3,2,1,0,3,3,2,2,2,2,1};
                int[] LogY2 = {4,5,4,4,3,3,3,2,2,1};
                int[] LeavesX = {6,5,3,0,0,1};
                int[] LeavesZ = {0,2,4,5,6,6};
                int LeafY = 3;
                int X2 = StartX[k1];
                int Z2 = StartZ[k1] + Log1[I];
                if (Q == 2) {
                    X2 = StartX[k2] + Log1[I];
                    Z2 = StartZ[k2];
                }
                if (Q == 3) {
                    X2 = StartX[k3];
                    Z2 = StartZ[k3] - Log1[I];
                }
                if (Q == 4) {
                    X2 = StartX[k4] - Log1[I];
                    Z2 = StartZ[k4];
                }
                if (I <= 5) {
                    int X3 = StartX[k1] + LeavesX[I];
                    int Z3 = StartZ[k1] + LeavesZ[I];
                    if (Q == 2) {
                        X3 = StartX[k2] + LeavesZ[I];
                        Z3 = StartZ[k2] - LeavesX[I];
                    }
                    if (Q == 3) {
                        X3 = StartX[k3] - LeavesX[I];
                        Z3 = StartZ[k3] - LeavesZ[I];
                    }
                    if (Q == 4) {
                        X3 = StartX[k4] - LeavesZ[I];
                        Z3 = StartZ[k4] + LeavesX[I];
                    }
                    leafCheck = world.getBlockId(x + X3,y + treeHeight - LeafY, z + Z3);
                    if (leafCheck == 0) {
                        world.setBlockWithNotify(x + X3, y + treeHeight - LeafY, z + Z3, this.leavesID);
                    }
                }
                if (I <= 9) {
                    int X4 = StartX[k1] + Log2X[I];
                    int Z4 = StartZ[k1] + Log2Z[I];
                    if (Q == 2) {
                        X4 = StartX[k2] + Log2Z[I];
                        Z4 = StartZ[k2] - Log2X[I];
                    }
                    if (Q == 3) {
                        X4 = StartX[k3] - Log2X[I];
                        Z4 = StartZ[k3] - Log2Z[I];
                    }
                    if (Q == 4) {
                        X4 = StartX[k4] - Log2Z[I];
                        Z4 = StartZ[k4] + Log2X[I];
                    }
                    world.setBlockWithNotify(x + X4, y + treeHeight - LogY2[I], z + Z4, this.logID);
                }
                world.setBlockWithNotify(x + X2, y + treeHeight - LogY1[I], z + Z2, this.logID);
                I++;
            }
            //Tree Branches
            int BranchMax = treeHeight - 10;
            int BranchMin = 7;
            int BranchHeight = random.nextInt(BranchMax + 1 - BranchMin) + BranchMin;
            int SpawnBranch = random.nextInt(2);
            if (SpawnBranch == 1) {
                for (int T = 0; T <= 10;) {
                    int[] BranchX = {1,1,1,2,2,3,4,4,5,6,6};
                    int[] BranchY = {0,-1,-2,0,1,1,1,2,2,2,3};
                    int X5 = StartX[k1] + BranchX[T];
                    int Z5 = StartZ[k1];
                    if (Q == 2) {
                        //Uses X -Z
                        X5 = StartX[k2];
                        Z5 = StartZ[k2] - BranchX[T];
                    }
                    if (Q == 3) {
                        //Uses -Z -X
                        X5 = StartX[k3] - BranchX[T];
                        Z5 = StartZ[k3];
                    }
                    if (Q == 4) {
                        //Uses -X +Z
                        X5 = StartX[k4];
                        Z5 = StartZ[k4] + BranchX[T];
                    }
                    world.setBlockWithNotify(x + X5, y + BranchHeight + BranchY[T], z + Z5, this.logID);
                    T++;
                }
                //Branch Leaves

				for (int G = 0; G <= 51;) {
                    int[] LeafLayerX = {0,-1,-1,0,0,0,0,1,1,1,2,-3,-2,-2,-2,-2,-1,-1,-1,-1,0,0,0,0,0,0,1,1,1,1,2,2,2,2,3,-2,-2,-1,-1,-1,-1,-1,0,0,0,1,1,1,1,1,2,2};
                    int[] LeafLayerZ = {0,-1,1,-1,-2,1,2,0,-1,1,0,0,0,-2,1,2,0,-1,-2,1,-3,-2,-1,1,2,3,0,-1,1,2,0,-1,-2,2,0,-1,1,0,-1,-2,1,2,0,-1,1,0,-1,-2,1,2,-1,1};
                    int[] LeafLayerY = {-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
                    int LeafStartY = BranchHeight + 3;
                    int LeafStartX = StartX[k1] + 6;
                    int LeafStartZ = StartZ[k1];
                    int X6 = LeafStartX + LeafLayerX[G];
                    int Z6 = LeafStartZ + LeafLayerZ[G];
                    if (Q == 2) {
                        //Uses X -Z
                        LeafStartX = StartX[k2];
                        LeafStartZ = StartZ[k2] - 6;
                        X6 = LeafStartX + LeafLayerZ[G];
                        Z6 = LeafStartZ - LeafLayerX[G];
                    }
                    if (Q == 3) {
                        //Uses -Z -X
                        LeafStartX = StartX[k3] - 6;
                        LeafStartZ = StartZ[k3];
                        X6 = LeafStartX - LeafLayerX[G];
                        Z6 = LeafStartZ - LeafLayerZ[G];
                    }
                    if (Q == 4) {
                        //Uses -X +Z
                        LeafStartX = StartX[k4];
                        LeafStartZ = StartZ[k4] + 6;
                        X6 = LeafStartX - LeafLayerZ[G];
                        Z6 = LeafStartZ + LeafLayerX[G];
                    }
                    leafCheck = world.getBlockId(x + X6,y + + LeafStartY + LeafLayerY[G], z + Z6);
                    if (leafCheck == 0) {
                        world.setBlockWithNotify(x + X6, y + LeafStartY + LeafLayerY[G], z + Z6, this.leavesID);
                    }
                    G++;
                }
            }
            //Tree Roots
            int RootHeight = 3 + random.nextInt(3);
            for (int k5 = -1; k5 <= RootHeight; ++k5) {
                if (Q == 1) {
                    world.setBlockWithNotify(x + StartX[k1] + 1, y + k5, z + StartZ[k1], this.logID);
                }
                if (Q == 2) {
                    world.setBlockWithNotify(x + StartX[k2], y + k5, z + StartZ[k2] - 1, this.logID);
                }
                if (Q == 3) {
                    world.setBlockWithNotify(x + StartX[k3] - 1, y + k5, z + StartZ[k3], this.logID);
                }
                if (Q == 4) {
                    world.setBlockWithNotify(x + StartX[k4], y + k5, z + StartZ[k4] + 1, this.logID);
                }
            }
            for (int J = 0; J <= 5 ;) {
                int[] RootsX = {2,2,2,0,0,0};
                int[] RootsZ = {0,0,0,1,1,1};
                int[] RootsY = {0,-1,-2,1,0,-1};
                //Uses Z X
                int X7 = StartX[k1] + RootsX[J];
                int Z7 = StartZ[k1] + RootsZ[J];
                if (Q == 2) {
                    //Uses X -Z
                    X7 = StartX[k2] + RootsZ[J];
                    Z7 = StartZ[k2] - RootsX[J];
                }
                if (Q == 3) {
                    //Uses -Z -X
                    X7 = StartX[k3] - RootsX[J];
                    Z7 = StartZ[k3] - RootsZ[J];
                }
                if (Q == 4) {
                    //Uses -X +Z
                    X7 = StartX[k4] - RootsZ[J];
                    Z7 = StartZ[k4] + RootsX[J];
                }
                world.setBlockWithNotify(x + X7, y + RootsY[J], z + Z7, this.logID);
                J++;
            }
        }
        //Tree Trunk
        for (int k6 = -1; k6 <= treeHeight; ++k6) {
            world.setBlockWithNotify(x, y + k6, z, this.logID);
            world.setBlockWithNotify(x + TrunkX[s1], y + k6, z + TrunkZ[s1], this.logID);
            world.setBlockWithNotify(x + TrunkX[s2], y + k6, z + TrunkZ[s2], this.logID);
            world.setBlockWithNotify(x + TrunkX[s3], y + k6, z + TrunkZ[s3], this.logID);
        }
        return true;
    }
}
