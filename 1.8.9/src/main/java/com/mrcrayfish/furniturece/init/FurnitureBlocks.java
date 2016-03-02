package com.mrcrayfish.furniturece.init;

import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.block.BlockAquarium;
import com.mrcrayfish.furniturece.block.BlockBearRug;
import com.mrcrayfish.furniturece.block.BlockBlackBoard;
import com.mrcrayfish.furniturece.block.BlockBottle;
import com.mrcrayfish.furniturece.block.BlockCanvas;
import com.mrcrayfish.furniturece.block.BlockChimney;
import com.mrcrayfish.furniturece.block.BlockCoffeeTable;
import com.mrcrayfish.furniturece.block.BlockGreenWheelieBin;
import com.mrcrayfish.furniturece.block.BlockOfficeChair;
import com.mrcrayfish.furniturece.block.BlockSpiralStairs;
import com.mrcrayfish.furniturece.item.ItemFurniture;

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
		canvas = new BlockCanvas(Material.wood).setUnlocalizedName("canvas");
		chimney = new BlockChimney(Material.rock).setUnlocalizedName("chimney");
		spiral_stairs = new BlockSpiralStairs(Material.wood).setUnlocalizedName("spiral_stairs");
		bear_rug = new BlockBearRug(Material.cloth).setUnlocalizedName("bear_rug");
		black_board = new BlockBlackBoard(Material.wood).setUnlocalizedName("black_board");
		office_chair = new BlockOfficeChair(Material.clay).setUnlocalizedName("office_chair");
		green_wheelie_bin = new BlockGreenWheelieBin(Material.wood).setUnlocalizedName("green_wheelie_bin");
		coffee_table = new BlockCoffeeTable(Material.wood).setUnlocalizedName("coffee_table");
		bottle = new BlockBottle(Material.glass).setUnlocalizedName("bottle");
	}
	
	public static void register()
	{
		GameRegistry.registerBlock(aquarium, ItemFurniture.class, aquarium.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(canvas, ItemFurniture.class, canvas.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(chimney, ItemFurniture.class, chimney.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(spiral_stairs, ItemFurniture.class, spiral_stairs.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(bear_rug, ItemFurniture.class, bear_rug.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(black_board, ItemFurniture.class, black_board.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(office_chair, ItemFurniture.class, office_chair.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(green_wheelie_bin, ItemFurniture.class, green_wheelie_bin.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(coffee_table, ItemFurniture.class, coffee_table.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(bottle, ItemFurniture.class, bottle.getUnlocalizedName().substring(5));
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
