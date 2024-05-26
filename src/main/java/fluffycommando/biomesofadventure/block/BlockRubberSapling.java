package fluffycommando.biomesofadventure.block;

import fluffycommando.biomesofadventure.world.WorldGenTreeShapeRubber;
import net.minecraft.core.block.BlockSaplingBase;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

public class BlockRubberSapling extends BlockSaplingBase {
	public BlockRubberSapling(String key, int id) {
		super(key, id);
	}

    @Override
    public void growTree(World world, int i, int j, int k, Random random) {
        world.setBlock(i, j, k, 0);
		WorldFeature obj = new WorldGenTreeShapeRubber(Block_Initialization.RubberLeaves.id, Block_Initialization.RubberLog.id);
        obj.func_517_a(1.0, 1.0, 1.0);
        if (!obj.generate(world, random, i, j, k)) {
            world.setBlock(i, j, k, this.id);
        }
    }
}
