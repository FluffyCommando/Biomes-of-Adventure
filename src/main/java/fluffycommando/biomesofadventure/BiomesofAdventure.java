package fluffycommando.biomesofadventure;

import fluffycommando.biomesofadventure.block.Block_Initialization;
import fluffycommando.biomesofadventure.item.Item_Initialization;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class BiomesofAdventure implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
	public static final String MOD_ID = "biomesofadventure";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		new Block_Initialization().initializeBlocks();
		new Item_Initialization().initializeItems();
		LOGGER.info("Biomes_of_Adventure initialized.");
    }

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {

	}
}
