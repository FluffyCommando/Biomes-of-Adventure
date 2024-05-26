package fluffycommando.biomesofadventure.block;

import static fluffycommando.biomesofadventure.BiomesofAdventure.MOD_ID;
import fluffycommando.biomesofadventure.BiomesofAdventureConfigs;
import net.minecraft.core.block.*;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.*;
import net.minecraft.core.item.tool.ItemToolPickaxe;
import net.minecraft.core.sound.BlockSound;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.CreativeHelper;
public class Block_Initialization {
	private int blockID(String blockName) {
		return BiomesofAdventureConfigs.cfg.getInt("Block IDs." + blockName);
	}
	public static Block KapokLog;
	public static Block RubberLog;
	public static Block MahoganyLog;
	public static Block BanyanLog;
	public static Block RedwoodLog;
	public static Block KapokLeaves;
	public static Block RubberLeaves;
	public static Block MahoganyLeaves;
	public static Block BanyanLeaves;
	public static Block RedwoodLeaves;
	public static Block KapokSapling;
	public static Block RubberSapling;
	public static Block MahoganySapling;
	public static Block BanyanSapling;
	public static Block RedwoodSapling;
	private void initializeBlockDetails() {
		CreativeHelper.setParent(KapokLog, Block.logOak);
		CreativeHelper.setParent(RubberLog, Block.logOak);
		CreativeHelper.setParent(MahoganyLog, Block.logOak);
		CreativeHelper.setParent(BanyanLog, Block.logOak);
		CreativeHelper.setParent(RedwoodLog, Block.logOak);

		CreativeHelper.setParent(KapokLeaves, Block.leavesOak);
		CreativeHelper.setParent(RubberLeaves, Block.leavesOak);
		CreativeHelper.setParent(MahoganyLeaves, Block.leavesOak);
		CreativeHelper.setParent(BanyanLeaves, Block.leavesOak);
		CreativeHelper.setParent(RedwoodLeaves, Block.leavesOak);

		CreativeHelper.setParent(KapokSapling, Block.saplingOak);
		CreativeHelper.setParent(RubberSapling, Block.saplingOak);
		CreativeHelper.setParent(MahoganySapling, Block.saplingOak);
		CreativeHelper.setParent(BanyanSapling, Block.saplingOak);
		CreativeHelper.setParent(RedwoodSapling, Block.saplingOak);
	}
	public void initializeBlocks() {
		BlockBuilder leaves = new BlockBuilder(MOD_ID)
				.setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f))
				.setHardness(0.2F)
				.setResistance(0.2F)
				.setFlammability(30, 60)
				.setTickOnLoad()
				.setVisualUpdateOnMetadata()
				.setItemBlock(ItemBlockLeaves::new)
				.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.MINEABLE_BY_HOE, BlockTags.MINEABLE_BY_SWORD, BlockTags.MINEABLE_BY_SHEARS, BlockTags.SHEARS_DO_SILK_TOUCH);
		BlockBuilder sapling = new BlockBuilder(MOD_ID)
				.setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f))
				.setHardness(0.0f)
				.setResistance(0.0f)
				.setBlockModel(new BlockModelRenderBlocks(1))
				.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.PLANTABLE_IN_JAR);
		BlockBuilder log = new BlockBuilder(MOD_ID)
				.setBlockSound(new BlockSound("step.wood", "step.wood", 1.0f, 1.0f))
				.setHardness(2.0F)
				.setResistance(1.0f)
				.setFlammability(5, 5)
				.setBlockModel(new BlockModelRenderBlocks(27))
				.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT);
		//logs
		KapokLog = log
				.setTopBottomTexture("kapok_log_top.png")
				.setSideTextures("kapok_log_side.png")
				.build(new BlockLog("log.kapok", blockID("KapokLog")));
		RubberLog = log
				.setTopBottomTexture("rubber_log_top.png")
				.setSideTextures("rubber_log_side.png")
				.build(new BlockLog("log.rubber", blockID("RubberLog")));
		MahoganyLog = log
				.setTopBottomTexture("mahogany_log_top.png")
				.setSideTextures("mahogany_log_side.png")
				.build(new BlockLog("log.mahogany", blockID("MahoganyLog")));
		BanyanLog = log
				.setTopBottomTexture("banyan_log_top.png")
				.setSideTextures("banyan_log_side.png")
				.build(new BlockLog("log.banyan", blockID("BanyanLog")));
		RedwoodLog = log
				.setTopBottomTexture("redwood_log_top.png")
				.setSideTextures("redwood_log_side.png")
				.build(new BlockLog("log.redwood", blockID("RedwoodLog")));
		//Leaves
		KapokLeaves = leaves
				.setSideTextures("kapok_leaves_fancy.png")
				.setBottomTexture("kapok_leaves_fast.png")
				.setTopBottomTexture("kapok_leaves_fancy.png")
				.build(new BlockLeavesBase("leaves.kapok", blockID("KapokLeaves"), Material.leaves, true) {
					@Override
					protected Block getSapling() {
						return Block_Initialization.KapokSapling;
					}
				});
		RubberLeaves = leaves
				.setSideTextures("rubber_leaves_fancy.png")
				.setBottomTexture("rubber_leaves_fast.png")
				.setTopBottomTexture("rubber_leaves_fancy.png")
				.build(new BlockLeavesBase("leaves.rubber", blockID("RubberLeaves"), Material.leaves, true) {
					@Override
					protected Block getSapling() {
						return Block_Initialization.RubberSapling;
					}
				});
		MahoganyLeaves = leaves
				.setSideTextures("mahogany_leaves_fancy.png")
				.setBottomTexture("mahogany_leaves_fast.png")
				.setTopBottomTexture("mahogany_leaves_fancy.png")
				.build(new BlockLeavesBase("leaves.mahogany", blockID("MahoganyLeaves"), Material.leaves, true) {
					@Override
					protected Block getSapling() {
						return Block_Initialization.MahoganySapling;
					}
				});
		BanyanLeaves = leaves
				.setSideTextures("banyan_leaves_fancy.png")
				.setBottomTexture("banyan_leaves_fast.png")
				.setTopBottomTexture("banyan_leaves_fancy.png")
				.build(new BlockLeavesBase("leaves.banyan", blockID("BanyanLeaves"), Material.leaves, true) {
					@Override
					protected Block getSapling() {
						return Block_Initialization.BanyanSapling;
					}
				});
		RedwoodLeaves = leaves
				.setSideTextures("redwood_leaves_fancy.png")
				.setBottomTexture("redwood_leaves_fast.png")
				.setTopBottomTexture("redwood_leaves_fancy.png")
				.build(new BlockLeavesBase("leaves.redwood", blockID("RedwoodLeaves"), Material.leaves, true) {
					@Override
					protected Block getSapling() {
						return Block_Initialization.RedwoodSapling;
					}
				});
		//Saplings
		KapokSapling = sapling
				.setTextures("kapok_sapling.png")
				.build(new BlockKapokSapling("sapling.kapok", blockID("KapokSapling")));
		RubberSapling = sapling
				.setTextures("rubber_sapling.png")
				.build(new BlockRubberSapling("sapling.rubber", blockID("RubberSapling")));
		MahoganySapling = sapling
				.setTextures("mahogany_sapling.png")
				.build(new BlockMahoganySapling("sapling.mahogany", blockID("MahoganySapling")));
		BanyanSapling = sapling
				.setTextures("banyan_sapling.png")
				.build(new BlockBanyanSapling("sapling.banyan", blockID("BanyanSapling")));
		RedwoodSapling = sapling
				.setTextures("redwood_sapling.png")
				.build(new BlockRedwoodSapling("sapling.redwood", blockID("RedwoodSapling")));
	}
}
