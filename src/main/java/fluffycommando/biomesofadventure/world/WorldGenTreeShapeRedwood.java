package fluffycommando.biomesofadventure.world;

import net.minecraft.core.block.*;
import net.minecraft.core.block.BlockGrass;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

public class WorldGenTreeShapeRedwood extends WorldFeature {
    protected int leavesID;
    protected int logID;

    public WorldGenTreeShapeRedwood(int leavesID, int logID) {
        this.leavesID = leavesID;
        this.logID = logID;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        int baseHeight = 20;
        int randomHeight = random.nextInt(5);
        int treeHeight = baseHeight + randomHeight;
        if (y < 1 || y + treeHeight + 1 > world.getWorldType().getMaxY()) {
            return false;
        }
        int i2 = world.getBlockId(x, y - 1, z);
        if (!(Block.blocksList[i2] instanceof BlockGrass) && i2 != Block.dirt.id || y >= world.getWorldType().getMaxY() - treeHeight - 1) {
            return false;
        }
        for (int CheckY = 1; CheckY <= treeHeight + 1; CheckY++) {
            int check1 = world.getBlockId(x,y + CheckY,z);
            if (check1 != 0) {
                if (check1 != this.leavesID) {
                    return false;
                }
            }
        }
        world.setBlockWithNotify(x, y - 1, z, Block.dirt.id);
        int[] LeavesX = {0,0,1,2,3,0,1,2,0,1,0,1,0,0,1,2,0,1,2,0,0,1,0,0,1,2,0,1,0,1,0,1,0,0,1,2,0,1,0,1,0,1,0,0,0,1,0,0,0,0,0,0};
        int[] LeavesZ = {1,1,1,1,1,2,2,2,3,3,1,1,2,1,1,1,2,2,2,3,1,1,2,1,1,1,2,2,1,1,1,1,2,1,1,1,2,2,1,1,1,1,2,1,1,1,1,1,0,0,0,0};
        int[] LeavesY = {14,13,13,13,13,13,13,13,13,13,12,12,12,11,11,11,11,11,11,11,10,10,10,9,9,9,9,9,8,8,7,7,7,6,6,6,6,6,5,5,4,4,4,3,2,2,1,0,0,-1,-2,-3};
        for (int Q = 1; Q <= 4; Q++) {
            for (int J = 0; J <= 51;) {
                int X1 = LeavesX[J];
                int Z1 = LeavesZ[J];
                int Y1 = treeHeight - LeavesY[J];
                if (Q == 2) {
                    X1 = LeavesZ[J];
                    Z1 = -LeavesX[J];
                }
                if (Q == 3) {
                    X1 = -LeavesX[J];
                    Z1 = -LeavesZ[J];
                }
                if (Q == 4) {
                    X1 = -LeavesZ[J];
                    Z1 = LeavesX[J];
                }
                int leafCheck = world.getBlockId(x + X1,y + Y1, z + Z1);
                if (leafCheck == 0) {
                    world.setBlockWithNotify(x + X1, y + Y1, z + Z1, this.leavesID);
                }
                J++;
            }
            int RootHeight = random.nextInt(4);
            for (int k5 = -1; k5 <= RootHeight; ++k5) {
                if (Q == 1) {
                    world.setBlockWithNotify(x + 1, y + k5, z, this.logID);
                }
                if (Q == 2) {
                    world.setBlockWithNotify(x, y + k5, z - 1, this.logID);
                }
                if (Q == 3) {
                    world.setBlockWithNotify(x - 1, y + k5, z, this.logID);
                }
                if (Q == 4) {
                    world.setBlockWithNotify(x, y + k5, z + 1, this.logID);
                }
            }
        }
        for (int k4 = -1; k4 < treeHeight; ++k4) {
            world.setBlockWithNotify(x, y + k4, z, this.logID);
        }
        return true;
    }
}

