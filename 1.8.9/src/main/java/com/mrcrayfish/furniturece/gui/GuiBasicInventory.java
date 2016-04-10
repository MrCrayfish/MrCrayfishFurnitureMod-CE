package com.mrcrayfish.furniturece.gui;

import org.lwjgl.opengl.GL11;

import com.mrcrayfish.furniturece.gui.container.ContainerBasicInventory;
import com.mrcrayfish.furniturece.tileentity.BasicInventory;
import com.mrcrayfish.furniturece.util.GuiHelper;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiBasicInventory extends GuiContainer
{
	private static final ResourceLocation GUI = new ResourceLocation("cfmce:textures/gui/basic_inventory.png");
	
	public static final int ID = 2;
	
	private final int inventorySize;
	private final int inventoryWidth;
	
	public GuiBasicInventory(InventoryPlayer playerInventory, BasicInventory basicInventory) 
	{
		super(new ContainerBasicInventory(playerInventory, basicInventory));
		this.xSize = 176;
		this.ySize = 152;
		this.inventorySize = basicInventory.getSizeInventory();
		this.inventoryWidth = 7 + basicInventory.getSizeInventory() * 18 + 7;
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GUI);
		
		int posX = (width - inventoryWidth) / 2;
		int posY = (height - ySize) / 2;
		
		/* Corners */
		this.drawTexturedModalRect(posX, posY, 0, 0, 7, 17);
		this.drawTexturedModalRect(posX + inventoryWidth - 7, posY, 25, 0, 7, 17);
		this.drawTexturedModalRect(posX + inventoryWidth - 7, posY + 35, 25, 35, 7, 7);
		this.drawTexturedModalRect(posX, posY + 35, 0, 35, 7, 7);
		
		/* Edges */
		GuiHelper.drawModalRectWithUV(posX + 7, posY, 7, 0, inventoryWidth - 14, 17, 1, 17);
		GuiHelper.drawModalRectWithUV(posX + inventoryWidth - 7, posY + 17, 25, 17, 7, 18, 7, 1);
		GuiHelper.drawModalRectWithUV(posX + 7, posY + 35, 7, 35, inventoryWidth - 14, 7, 1, 7);
		GuiHelper.drawModalRectWithUV(posX, posY + 17, 0, 17, 7, 18, 7, 1);
		
		for(int i = 0; i < inventorySize; i++)
		{
			this.drawTexturedModalRect(posX + 7 + i * 18, posY + 17, 7, 17, 18, 18);
		}
		
		posX = (width - xSize) / 2;
		posY = (height - ySize) / 2;
		
		this.drawTexturedModalRect(posX, posY + 52, 0, 42, xSize, 100);
	}

}
