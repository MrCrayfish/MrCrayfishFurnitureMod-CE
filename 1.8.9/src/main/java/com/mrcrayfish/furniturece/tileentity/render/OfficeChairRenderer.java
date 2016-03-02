package com.mrcrayfish.furniturece.tileentity.render;

import com.mrcrayfish.furniturece.block.BlockOfficeChair;
import com.mrcrayfish.furniturece.init.FurnitureBlocks;
import com.mrcrayfish.furniturece.tileentity.TileEntityOfficeChair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class OfficeChairRenderer extends TileEntitySpecialRenderer
{
	private Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float partialTick, int breakStage)
	{
		TileEntityOfficeChair officeChair = (TileEntityOfficeChair) tileEntity;
		
		if(!officeChair.hasTop)
			return;
		
		IBlockState state = FurnitureBlocks.office_chair.getDefaultState().withProperty(BlockOfficeChair.TYPE, BlockOfficeChair.Type.CHAIR);
		BlockPos pos = tileEntity.getPos();
		
		bindTexture(TextureMap.locationBlocksTexture);
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(posX, posY, posZ);
			GlStateManager.translate(0.5, 0, 0.5);
			GlStateManager.rotate(-officeChair.getRotation() - 90F, 0, 1, 0);
			GlStateManager.translate(-0.5, 0, -0.5);
			
			GlStateManager.disableLighting();
			
			Tessellator tessellator = Tessellator.getInstance();
			WorldRenderer renderer = tessellator.getWorldRenderer();
			renderer.begin(7, DefaultVertexFormats.BLOCK);
			renderer.setTranslation(-pos.getX(), -pos.getY(), -pos.getZ());
			
			BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
			IBakedModel ibakedmodel = mc.getBlockRendererDispatcher().getBlockModelShapes().getModelForState(state);
			blockrendererdispatcher.getBlockModelRenderer().renderModel(getWorld(), ibakedmodel, state, pos, renderer, false);
			
			renderer.setTranslation(0.0D, 0.0D, 0.0D);
			tessellator.draw();
			
			GlStateManager.enableLighting();
		 }
		 GlStateManager.popMatrix();
	}
}
