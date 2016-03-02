package com.mrcrayfish.furniturece;

import com.mrcrayfish.furniturece.achievement.CraftingTracker;
import com.mrcrayfish.furniturece.achievement.FurnitureAchievements;
import com.mrcrayfish.furniturece.entity.EntitySittableBlock;
import com.mrcrayfish.furniturece.event.FurnitureEvents;
import com.mrcrayfish.furniturece.gui.GuiHandler;
import com.mrcrayfish.furniturece.init.FurnitureBlocks;
import com.mrcrayfish.furniturece.init.FurnitureCrafting;
import com.mrcrayfish.furniturece.init.FurnitureItems;
import com.mrcrayfish.furniturece.network.PacketHandler;
import com.mrcrayfish.furniturece.proxy.IFurnitureProxy;
import com.mrcrayfish.furniturece.tileentity.TileEntityAquarium;
import com.mrcrayfish.furniturece.tileentity.TileEntityBlackBoard;
import com.mrcrayfish.furniturece.tileentity.TileEntityBottle;
import com.mrcrayfish.furniturece.tileentity.TileEntityCanvas;
import com.mrcrayfish.furniturece.tileentity.TileEntityOfficeChair;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.WORKING_MC_VERSION)
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
		/** Item Registering */
		FurnitureItems.init();
		FurnitureItems.register();
		
		/** Block Registering */
		FurnitureBlocks.init();
		FurnitureBlocks.register();
		
		/** Achievement Registering */
		FurnitureAchievements.loadAchievements();
		FurnitureAchievements.registerPage();
		
		/** Pre-Initialize Proxies */
		proxy.preInit();
		
		/** Packet Registering */
		PacketHandler.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) 
	{
		/** Initialize Proxies */
		proxy.init();
		
		/** Crafting Registering */
		FurnitureCrafting.register();
		
		/** TileEntity Registering */
		GameRegistry.registerTileEntity(TileEntityAquarium.class, "cfmceAquarium");
		GameRegistry.registerTileEntity(TileEntityCanvas.class, "cfmceCanvas");
		GameRegistry.registerTileEntity(TileEntityBottle.class, "cfmceBottle");
		GameRegistry.registerTileEntity(TileEntityOfficeChair.class, "cfmceOfficeChair");
		GameRegistry.registerTileEntity(TileEntityBlackBoard.class, "cfmceBlackBoard");
		
		/** Entity Registering */
		EntityRegistry.registerModEntity(EntitySittableBlock.class, "cfmceSittableBlock", 0, this, 80, 1, false);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		/** Event Registering */
		FMLCommonHandler.instance().bus().register(new CraftingTracker());
		FMLCommonHandler.instance().bus().register(new FurnitureEvents());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		/** Post-Initialize Proxies */
		proxy.postInit();
	}
}
