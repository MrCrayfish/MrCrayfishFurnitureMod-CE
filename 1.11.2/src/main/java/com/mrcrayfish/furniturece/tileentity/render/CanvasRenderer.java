package com.mrcrayfish.furniturece.tileentity.render;

import org.lwjgl.opengl.GL11;

import com.mrcrayfish.furniturece.block.BlockCanvas;
import com.mrcrayfish.furniturece.tileentity.TileEntityCanvas;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityPainting.EnumArt;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class CanvasRenderer extends TileEntitySpecialRenderer
{
	private EntityPainting painting = new EntityPainting(Minecraft.getMinecraft().world);
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float partialTick, int breakStage) 
	{
		if(!(tileEntity.getBlockType() instanceof BlockCanvas))
			return;
		
		BlockCanvas canvas = (BlockCanvas) tileEntity.getBlockType();
		TileEntityCanvas tileEntityCanvas = (TileEntityCanvas) tileEntity;
		
		if(!tileEntityCanvas.hasArt)
			return;
		
		int meta = canvas.getMetaFromState(tileEntityCanvas.getWorld().getBlockState(tileEntityCanvas.getPos()));
		
		painting.art = EnumArt.values()[tileEntityCanvas.art];
		
		float yOffset = (1F / 16F) * (painting.art.sizeY / 2) - 0.5F;
		
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(posX, posY, posZ);
			GlStateManager.translate(0.5, 1.25, 0.5);
			GlStateManager.rotate(meta * -90F, 0, 1, 0);
			GlStateManager.rotate(180F, 0, 1, 0);
			GlStateManager.rotate(-22.5F, 1, 0, 0);
			GlStateManager.translate(0, yOffset, -0.3);
			
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(painting, 0, 0, 0, 0, 0 ,false);
		}
		GlStateManager.popMatrix();
	}
}
