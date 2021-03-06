package erebus.block.ores;

import erebus.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import java.util.Random;

public class UmberOreEncrustedDiamond extends UmberOre {

	public UmberOreEncrustedDiamond() {
		super(Blocks.diamond_ore, "EncrustedDiamond", 3);
		setHardness(10F);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return ModItems.encrustedDiamond;
	}

	@Override
	public int damageDropped(int meta) {
		return 0;
	}
}