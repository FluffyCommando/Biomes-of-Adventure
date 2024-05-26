package fluffycommando.biomesofadventure;

import fluffycommando.biomesofadventure.block.Block_Initialization;
import fluffycommando.biomesofadventure.item.Item_Initialization;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.util.ConfigUpdater;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class BiomesofAdventureConfigs {
	public static ConfigUpdater updater = ConfigUpdater.fromProperties();
	private static final Toml properties = new Toml("Biomes of Adventure TOML Config");
	public static TomlConfigHandler cfg;

	private static int blockIDs = 1333;

	private static int itemIDs = 13333;

	static {
		properties.addCategory("Biomes of Adventure")
				.addEntry("cfgVersion", 1);

		properties.addCategory("Block IDs");
		properties.addEntry("Block IDs.startingID", 1333);
		properties.addCategory("Item IDs");
		properties.addEntry("Item IDs.startingID", 13333);


		List<Field> blockFields = Arrays.stream(Block_Initialization.class.getDeclaredFields()).filter((F)-> Block.class.isAssignableFrom(F.getType())).collect(Collectors.toList());
		for (Field blockField : blockFields) {
			properties.addEntry("Block IDs." + blockField.getName(), blockIDs++);
		}
		List<Field> itemFields = Arrays.stream(Item_Initialization.class.getDeclaredFields()).filter((F)-> Item.class.isAssignableFrom(F.getType())).collect(Collectors.toList());
		for (Field itemField : itemFields) {
			properties.addEntry("Item IDs." + itemField.getName(), itemIDs++);
		}

		cfg = new TomlConfigHandler(updater, BiomesofAdventure.MOD_ID, properties);

	}
}
