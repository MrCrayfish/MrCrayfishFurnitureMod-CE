package com.mrcrayfish.furniturece.tileentity.render;

import org.lwjgl.opengl.GL11;

import com.mrcrayfish.furniturece.block.BlockBottle;
import com.mrcrayfish.furniturece.tileentity.TileEntityBottle;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class BottleRenderer extends TileEntitySpecialRenderer
{
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float partialTick, int breakStage)
	{
		if(!(tileEntity.getBlockType() instanceof BlockBottle))
			return;
		
		BlockBottle bottle = (BlockBottle) tileEntity.getBlockType();
		TileEntityBottle tileEntityBottle = (TileEntityBottle) tileEntity;
		
		int colour = tileEntityBottle.getPotionColour();
        float red = (float)(colour >> 16 & 255) / 255.0F;
        float green = (float)(colour >> 8 & 255) / 255.0F;
        float blue = (float)(colour & 255) / 255.0F;
        
        GlStateManager.color(red, green, blue);
        
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.disableLighting();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(posX, posY, posZ);
			
			if(tileEntityBottle.level > 0)
			{
				renderCuboid(6 * 0.0625F, 0.0625F, 6 * 0.0625F, 11 * 0.0625F, 2 * 0.0625F, 11 * 0.0625F, 100000);
				renderCuboid(5 * 0.0625F, 2 * 0.0625F, 5 * 0.0625F, 12 * 0.0625F, 3 * 0.0625F, 12 * 0.0625F, 100000);
			}
			
			if(tileEntityBottle.level > 1)
			{
				renderCuboid(4 * 0.0625F, 3 * 0.0625F, 4 * 0.0625F, 13 * 0.0625F, 5 * 0.0625F, 13 * 0.0625F, 100000);
			}
			
			if(tileEntityBottle.level > 2)
			{
				renderCuboid(4 * 0.0625F, 5 * 0.0625F, 4 * 0.0625F, 13 * 0.0625F, 7 * 0.0625F, 13 * 0.0625F, 100000);
			}
			
			if(tileEntityBottle.level > 3)
			{
				renderCuboid(5 * 0.0625F, 7 * 0.0625F, 5 * 0.0625F, 12 * 0.0625F, 8 * 0.0625F, 12 * 0.0625F, 100000);
				renderCuboid(6 * 0.0625F, 8 * 0.0625F, 6 * 0.0625F, 11 * 0.0625F, 9 * 0.0625F, 11 * 0.0625F, 100000);
				renderCuboid(7 * 0.0625F, 9 * 0.0625F, 7 * 0.0625F, 10 * 0.0625F, 10 * 0.0625F, 10 * 0.0625F, 100000);
			}
		}
		GlStateManager.popMatrix();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GlStateManager.enableLighting();
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
	}
	
	public void renderCuboid(float x1, float y1, float z1, float x2, float y2, float z2, int colour)
	{
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex3f(x1, y1, z1);
			GL11.glVertex3f(x1, y1, z2);
			GL11.glVertex3f(x1, y2, z2);
			GL11.glVertex3f(x1, y2, z1);

			GL11.glVertex3f(x2, y1, z1);
			GL11.glVertex3f(x1, y1, z1);
			GL11.glVertex3f(x1, y2, z1);
			GL11.glVertex3f(x2, y2, z1);

			GL11.glVertex3f(x1, y1, z2);
			GL11.glVertex3f(x2, y1, z2);
			GL11.glVertex3f(x2, y2, z2);
			GL11.glVertex3f(x1, y2, z2);

			GL11.glVertex3f(x2, y1, z2);
			GL11.glVertex3f(x2, y1, z1);
			GL11.glVertex3f(x2, y2, z1);
			GL11.glVertex3f(x2, y2, z2);

			GL11.glVertex3f(x1, y2, z1);
			GL11.glVertex3f(x1, y2, z2);
			GL11.glVertex3f(x2, y2, z2);
			GL11.glVertex3f(x2, y2, z1);

			GL11.glVertex3f(x1, y1, z1);
			GL11.glVertex3f(x1, y1, z2);
			GL11.glVertex3f(x2, y1, z2);
			GL11.glVertex3f(x2, y1, z1);
		}
		GL11.glEnd();
	}
}
