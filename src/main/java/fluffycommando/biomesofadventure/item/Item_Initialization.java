package fluffycommando.biomesofadventure.item;

import fluffycommando.biomesofadventure.BiomesofAdventureConfigs;
import net.minecraft.core.item.*;
public class Item_Initialization {
	private int itemID(String blockName) {
		return BiomesofAdventureConfigs.cfg.getInt("Item IDs." + blockName);
	}


	public void initializeItems() {

	}
}
