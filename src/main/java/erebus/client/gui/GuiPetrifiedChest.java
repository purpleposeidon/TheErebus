package erebus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.helper.Utils;
import erebus.inventory.ContainerPetrifiedWoodChest;

@SideOnly(Side.CLIENT)
public class GuiPetrifiedChest extends GuiContainer {

	private static final ResourceLocation field_110421_t = new ResourceLocation("erebus:textures/gui/container/petrifiedContainerBig.png");
	private final IInventory upperChestInventory, lowerChestInventory;

	private final int inventoryRows;

	public GuiPetrifiedChest(IInventory upperInventory, IInventory lowerInventory) {
		super(new ContainerPetrifiedWoodChest(upperInventory, lowerInventory));
		upperChestInventory = upperInventory;
		lowerChestInventory = lowerInventory;
		allowUserInput = false;
		inventoryRows = lowerInventory.getSizeInventory() / 9;
		ySize = 112 + inventoryRows * 18;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRendererObj.drawString(lowerChestInventory.hasCustomInventoryName() ? lowerChestInventory.getInventoryName() : StatCollector.translateToLocal(lowerChestInventory.getInventoryName()), 8, 6, Utils.getColour(255, 255, 255));
		fontRendererObj.drawString(upperChestInventory.hasCustomInventoryName() ? upperChestInventory.getInventoryName() : StatCollector.translateToLocal(upperChestInventory.getInventoryName()), 8, ySize - 96 + 2, Utils.getColour(255, 255, 255));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(field_110421_t);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, inventoryRows * 18 + 17);
		drawTexturedModalRect(k, l + inventoryRows * 18 + 17, 0, 124 + 36, xSize, 96);
	}
}