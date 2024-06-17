package com.github.kill05.blocks.architectstation.inventory.part;

import com.github.kill05.ArchitectTools;
import com.github.kill05.blocks.architectstation.ArchitectTableTileEntity;
import com.github.kill05.blocks.architectstation.inventory.ArchitectStationGui;
import com.github.kill05.inventory.container.TileContainer;
import com.github.kill05.items.part.ArchitectPart;
import com.github.kill05.materials.ArchitectMaterial;
import com.github.kill05.materials.MaterialInfo;
import com.github.kill05.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.FontRenderer;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import org.lwjgl.opengl.GL11;

public class PartModeGui extends ArchitectStationGui<ArchitectPart> {

	public PartModeGui(ArchitectTableTileEntity tile, EntityPlayer player) {
		super(new PartModeContainer(tile, player), player);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f) {
		RenderUtils.bindTexture("gui/part_mode.png");
		GL11.glColor4f(1f, 1f, 1f, 1f);

		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		TileContainer container = getContainer();
		IInventory inventory = container.getInventory();
		for(int i = 0; i < 2; i++) {
			if (inventory.getStackInSlot(i) == null) {
				drawTexturedModalRect(
					x + container.alignX(5.5f),
					y + container.alignY(2.5f + i * 2),
					xSize, i * 16, 16, 16
				);
			}
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer() {
		FontRenderer fontRenderer = Minecraft.getMinecraft(this).fontRenderer;
		ArchitectTableTileEntity tile = getContainer().getTile();
		IInventory inv = tile.getPartInventory();
		ItemStack materialStack = inv.getStackInSlot(1);

		ArchitectPart part = tile.getSelectedPart();
		MaterialInfo info = ArchitectTools.getMaterialInfo(materialStack);
		float amount = ArchitectMaterial.getDisplayMaterialValue(info.value() * (materialStack != null ? materialStack.stackSize : 0));
		float materialCost = part != null ? ArchitectMaterial.getDisplayMaterialValue(part.getMaterialCost()) : 0;
		fontRenderer.drawCenteredString(amount + "/" + materialCost, 151, 83, materialCost > amount ? 0x00c00000 : 0x0000c000);
	}
}
