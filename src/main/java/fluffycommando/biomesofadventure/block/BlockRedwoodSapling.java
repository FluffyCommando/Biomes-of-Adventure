package fluffycommando.biomesofadventure.block;

import fluffycommando.biomesofadventure.world.WorldGenTreeShapeRedwood;
import net.minecraft.core.block.BlockSaplingBase;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;
public class BlockRedwoodSapling extends BlockSaplingBase {
	public BlockRedwoodSapling(String key, int id) {
		super(key, id);
	}

    @Override
    public void growTree(World world, int i, int j, int k, Random random) {
        world.setBlock(i, j, k, 0);
		WorldFeature obj = new WorldGenTreeShapeRedwood(Block_Initialization.RedwoodLeaves.id, Block_Initialization.RedwoodLog.id);
        obj.func_517_a(1.0, 1.0, 1.0);
        if (!obj.generate(world, random, i, j, k)) {
            world.setBlock(i, j, k, this.id);
        }
    }
}
