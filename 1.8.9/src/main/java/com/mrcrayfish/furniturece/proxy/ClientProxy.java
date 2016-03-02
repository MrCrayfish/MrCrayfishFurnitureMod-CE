package com.mrcrayfish.furniturece.proxy;

import com.mrcrayfish.furniturece.init.FurnitureBlocks;
import com.mrcrayfish.furniturece.init.FurnitureItems;
import com.mrcrayfish.furniturece.tileentity.TileEntityAquarium;
import com.mrcrayfish.furniturece.tileentity.TileEntityBottle;
import com.mrcrayfish.furniturece.tileentity.TileEntityCanvas;
import com.mrcrayfish.furniturece.tileentity.TileEntityOfficeChair;
import com.mrcrayfish.furniturece.tileentity.render.AquariumRenderer;
import com.mrcrayfish.furniturece.tileentity.render.BottleRenderer;
import com.mrcrayfish.furniturece.tileentity.render.CanvasRenderer;
import com.mrcrayfish.furniturece.tileentity.render.OfficeChairRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy implements IFurnitureProxy 
{
	@Override
	public void preInit() {}

	@Override
	public void init() 
	{
		FurnitureBlocks.registerRenders();
		FurnitureItems.registerRenders();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAquarium.class, new AquariumRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCanvas.class, new CanvasRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBottle.class, new BottleRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOfficeChair.class, new OfficeChairRenderer());
	}

	@Override
	public void postInit() {}
}
