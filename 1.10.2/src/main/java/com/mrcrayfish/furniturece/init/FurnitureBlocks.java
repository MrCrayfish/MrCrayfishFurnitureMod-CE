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
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FurnitureBlocks {
	
	/* Basics */
	public static BlockAquarium aquarium;
	public static BlockCanvas canvas;
	public static BlockChimney chimney;
	public static BlockSpiralStairs spiral_stairs;
	public static BlockBearRug bear_rug;
	public static BlockBlackBoard black_board;
	public static BlockOfficeChair office_chair;
	public static BlockGreenWheelieBin green_wheelie_bin;
	public static BlockCoffeeTable coffee_table;
	public static BlockBottle bottle;
	
	public static void init()
	{
		aquarium = new BlockAquarium(Material.GLASS);
		canvas = new BlockCanvas(Material.WOOD);
		chimney = new BlockChimney(Material.ROCK);
		spiral_stairs = new BlockSpiralStairs(Material.WOOD);
		bear_rug = new BlockBearRug(Material.CLOTH);
		black_board = new BlockBlackBoard(Material.WOOD);
		office_chair = new BlockOfficeChair(Material.CLAY);
		green_wheelie_bin = new BlockGreenWheelieBin(Material.WOOD);
		coffee_table = new BlockCoffeeTable(Material.WOOD);
		bottle = new BlockBottle(Material.GLASS);
	}
	
	public static void register()
	{
		GameRegistry.register(aquarium);
		GameRegistry.register(new ItemFurniture(aquarium).setRegistryName(aquarium.getRegistryName()));
		GameRegistry.register(canvas);
		GameRegistry.register(new ItemFurniture(canvas).setRegistryName(canvas.getRegistryName()));
		GameRegistry.register(chimney);
		GameRegistry.register(new ItemFurniture(chimney).setRegistryName(chimney.getRegistryName()));
		GameRegistry.register(spiral_stairs);
		GameRegistry.register(new ItemFurniture(spiral_stairs).setRegistryName(spiral_stairs.getRegistryName()));
		GameRegistry.register(bear_rug);
		GameRegistry.register(new ItemFurniture(bear_rug).setRegistryName(bear_rug.getRegistryName()));
		GameRegistry.register(black_board);
		GameRegistry.register(new ItemFurniture(black_board).setRegistryName(black_board.getRegistryName()));
		GameRegistry.register(office_chair);
		GameRegistry.register(new ItemFurniture(office_chair).setRegistryName(office_chair.getRegistryName()));
		GameRegistry.register(green_wheelie_bin);
		GameRegistry.register(new ItemFurniture(green_wheelie_bin).setRegistryName(green_wheelie_bin.getRegistryName()));
		GameRegistry.register(coffee_table);
		GameRegistry.register(new ItemFurniture(coffee_table).setRegistryName(coffee_table.getRegistryName()));
		GameRegistry.register(bottle);
		GameRegistry.register(new ItemFurniture(bottle).setRegistryName(bottle.getRegistryName()));
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
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(blockIn), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + blockIn.getRegistryName(), "inventory"));
	}
}
