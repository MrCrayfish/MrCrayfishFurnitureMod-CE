package com.mrcrayfish.furniturece.tileentity.render;

import com.mrcrayfish.furniturece.block.BlockAquarium;
import com.mrcrayfish.furniturece.tileentity.TileEntityAquarium;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class AquariumRenderer extends TileEntitySpecialRenderer 
{
	private EntityItem entityItem = new EntityItem(Minecraft.getMinecraft().world, 0D, 0D, 0D, new ItemStack(Items.FISH, 1, 2));
			
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float partialTick, int breakStage) 
	{
		entityItem.hoverStart = 0;
		
		if(!(tileEntity.getBlockType() instanceof BlockAquarium))
			return;
		TileEntityAquarium tileEntityGrill = (TileEntityAquarium) tileEntity;
		
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(posX, posY, posZ);
			GlStateManager.translate(0.5, -0.15, 0.5);
			GlStateManager.scale(0.9, 0.9, 0.9);
			float rotation = (float) tileEntityGrill.rotation + partialTick;
			if(tileEntityGrill.special) rotation *= 50;
			GlStateManager.rotate(rotation, 0, 1, 0);
			GlStateManager.translate(0.2, 0, 0);
			GlStateManager.rotate(45F, 1, 0, 0);
			GlStateManager.rotate(-90F, 0, 1, 0);
			GlStateManager.translate(-0.25, 0, 0);
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(entityItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F , false);
		}
		GlStateManager.popMatrix();
	}
}
