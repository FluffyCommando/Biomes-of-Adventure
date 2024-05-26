package fluffycommando.biomesofadventure.block;

import fluffycommando.biomesofadventure.world.WorldGenTreeShapeKapok;
import fluffycommando.biomesofadventure.world.WorldGenTreeShapeKapokLarge;
import net.minecraft.core.block.BlockSaplingBase;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

import static fluffycommando.biomesofadventure.block.Block_Initialization.KapokSapling;

public class BlockKapokSapling extends BlockSaplingBase {
    public BlockKapokSapling(String key, int id) {
		super(key, id);
    }


    public void growTree(World world, int x, int y, int z, Random random) {
        world.setBlock(z, y, z, 0);

        boolean areaLook = false;
        int c1 = world.getBlockId(x + 1, y, z) & world.getBlockId(x, y, z + 1) & world.getBlockId(x + 1, y, z + 1);
        int c2 = world.getBlockId(x + 1, y, z) & world.getBlockId(x, y, z - 1) & world.getBlockId(x + 1, y, z - 1);
        int c3 = world.getBlockId(x - 1, y, z) & world.getBlockId(x, y, z - 1) & world.getBlockId(x - 1, y, z - 1);
        int c4 = world.getBlockId(x - 1, y, z) & world.getBlockId(x, y, z + 1) & world.getBlockId(x - 1, y, z + 1);
        int treeSpawn = 0;
        if (c1 == KapokSapling.id) {
            areaLook = true;
            treeSpawn = 1;
        } else if (c2 == KapokSapling.id) {
            areaLook = true;
            treeSpawn = 2;
        } else if (c3 == KapokSapling.id) {
            areaLook = true;
            treeSpawn = 3;
        } else if (c4 == KapokSapling.id) {
            areaLook = true;
            treeSpawn = 4;
        }
        if (areaLook) {
			WorldFeature bigTree = new WorldGenTreeShapeKapokLarge(Block_Initialization.KapokLeaves.id, Block_Initialization.KapokLog.id, treeSpawn);
            bigTree.func_517_a(1.0, 1.0, 1.0);
            if (!bigTree.generate(world, random, x, y, z)) {
                world.setBlock(x, y, z, this.id);
            }
        } else {
			WorldFeature smallTree = new WorldGenTreeShapeKapok(Block_Initialization.KapokLeaves.id, Block_Initialization.KapokLog.id);
            smallTree.func_517_a(1.0, 1.0, 1.0);
            if (!smallTree.generate(world, random, x, y, z)) {
                    world.setBlock(x, y, z, this.id);
            }
        }
    }
}
