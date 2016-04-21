package com.mrcrayfish.furniturece.tileentity.render;

import org.lwjgl.opengl.GL11;

import com.mrcrayfish.furniturece.tileentity.TileEntityWaterCooler;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class WaterCoolerRenderer extends TileEntitySpecialRenderer<TileEntityWaterCooler>
{

	@Override
	public void renderTileEntityAt(TileEntityWaterCooler te, double posX, double posY, double posZ, float partialTicks, int destroyStage) 
	{
		GlStateManager.color(0, 0, 1, 0.4F);
		
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		GlStateManager.disableLighting();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(posX, posY, posZ);
			
			renderCuboid(5.1F * 0.0625F, 14 * 0.0625F, 5.1F * 0.0625F, 10.9F * 0.0625F, (14F + 7F * te.getWaterLevelPercentage()) * 0.0625F, 10.9F * 0.0625F, 100000);
		}
		GlStateManager.popMatrix();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.disableAlpha();
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
