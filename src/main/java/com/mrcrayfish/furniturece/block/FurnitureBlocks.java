package com.mrcrayfish.furniturece.block;

import com.mrcrayfish.furniturece.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FurnitureBlocks {
	
	/* Basics */
	public static Block aquarium;
	public static Block canvas;
	public static Block chimney;
	public static Block spiral_stairs;
	public static Block bear_rug;
	public static Block black_board;
	public static Block office_chair;
	public static Block green_wheelie_bin;
	public static Block coffee_table;
	public static Block bottle;
	
	public static void init()
	{
		aquarium = new BlockAquarium(Material.glass).setUnlocalizedName("aquarium");
		canvas = new BlockFurniture(Material.wood).setUnlocalizedName("canvas");
		chimney = new BlockFurniture(Material.rock).setUnlocalizedName("chimney");
		spiral_stairs = new BlockFurniture(Material.wood).setUnlocalizedName("spiral_stairs");
		bear_rug = new BlockFurniture(Material.cloth).setUnlocalizedName("bear_rug");
		black_board = new BlockFurniture(Material.wood).setUnlocalizedName("black_board");
		office_chair = new BlockFurniture(Material.clay).setUnlocalizedName("office_chair");
		green_wheelie_bin = new BlockFurniture(Material.wood).setUnlocalizedName("green_wheelie_bin");
		coffee_table = new BlockCoffeeTable(Material.wood).setUnlocalizedName("coffee_table");
		bottle = new BlockBottle(Material.glass).setUnlocalizedName("bottle");
	}
	
	public static void register()
	{
		GameRegistry.registerBlock(aquarium, aquarium.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(canvas, canvas.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(chimney, chimney.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(spiral_stairs, spiral_stairs.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(bear_rug, bear_rug.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(black_board, black_board.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(office_chair, office_chair.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(green_wheelie_bin, green_wheelie_bin.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(coffee_table, coffee_table.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(bottle, bottle.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders() 
	{
		registerRender(aquarium);
		registerRender(canvas);
		registerRender(chimney);
		registerRender(spiral_stairs);
		registerRender(bear_rug);
		registerRender(black_board);
		registerRender(office_chair);
		registerRender(green_wheelie_bin);
		registerRender(coffee_table);
		registerRender(bottle);
	}
	
	private static void registerRender(Block blockIn)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(blockIn), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + blockIn.getUnlocalizedName().substring(5), "inventory"));
	}
}
