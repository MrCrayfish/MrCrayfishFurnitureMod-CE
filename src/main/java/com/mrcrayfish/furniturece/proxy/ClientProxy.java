package com.mrcrayfish.furniturece.proxy;

import com.mrcrayfish.furniturece.block.FurnitureBlocks;

public class ClientProxy implements IFurnitureProxy 
{
	@Override
	public void preInit() {}

	@Override
	public void init() 
	{
		FurnitureBlocks.registerRenders();
	}

	@Override
	public void postInit() {}
}
