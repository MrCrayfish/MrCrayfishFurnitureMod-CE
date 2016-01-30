package com.mrcrayfish.furniturece;

import com.mrcrayfish.furniturece.block.FurnitureBlocks;
import com.mrcrayfish.furniturece.proxy.IFurnitureProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class MrCrayfishFurnitureModCE 
{	
	@Instance(Reference.MOD_ID)
	public static MrCrayfishFurnitureModCE instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IFurnitureProxy proxy;
	
	public static CreativeTabs tabFurniture = new FurnitureTab("tabFurnitureCE");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		/** Block Registering */
		FurnitureBlocks.init();
		FurnitureBlocks.register();
		
		/** Pre-Initialize Proxies */
		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) 
	{
		/** Initialize Proxies */
		proxy.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		/** Post-Initialize Proxies */
		proxy.postInit();
	}
}
