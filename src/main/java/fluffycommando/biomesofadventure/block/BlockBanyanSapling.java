package fluffycommando.biomesofadventure.block;

import fluffycommando.biomesofadventure.world.WorldGenTreeShapeBanyan;
import net.minecraft.core.block.BlockSaplingBase;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

import static fluffycommando.biomesofadventure.block.Block_Initialization.BanyanSapling;

public class BlockBanyanSapling extends BlockSaplingBase {
	public BlockBanyanSapling(String key, int id) {
		super(key, id);
	}

	@Override
	public void growTree(World world, int x, int y, int z, Random random) {
		boolean areaLook = true;
		boolean YLook = true;
		int[] SaplingX = {1, 1, 0, -1, -1, -1, 0, 1, 0};
		int[] SaplingZ = {0, 1, 1, 1, 0, -1, -1, -1, 0};

		for (int I = 0; I <= 8; I++) {
			int check1 = world.getBlockId(x + SaplingX[I], y, z + SaplingZ[I]);
			if (check1 != BanyanSapling.id) {
				areaLook = false;
				break;
			}
			for (int CheckY = 1; CheckY <= 16; CheckY++) {
				int check2 = world.getBlockId(x + SaplingX[I],y + CheckY, z + SaplingZ[I]);
				if (check2 != 0) {
					YLook = false;
					break;
				}
			}
		}
		if (areaLook && YLook) {
			for (int I = 0; I <= 8; I++) {
				world.setBlock(x + SaplingX[I], y, z + SaplingZ[I], 0);
			}
			WorldFeature bigTree = new WorldGenTreeShapeBanyan(Block_Initialization.BanyanLeaves.id, Block_Initialization.BanyanLog.id);
			bigTree.func_517_a(1.0, 1.0, 1.0);
			if (!bigTree.generate(world, random, x, y, z)) {
					world.setBlock(x, y, z, this.id);
			}
		}
	}
}
