package com.mrcrayfish.furniturece.init;

import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.block.Block3DPrinter;
import com.mrcrayfish.furniturece.block.BlockAirVent;
import com.mrcrayfish.furniturece.block.BlockAquarium;
import com.mrcrayfish.furniturece.block.BlockBearRug;
import com.mrcrayfish.furniturece.block.BlockBlackBoard;
import com.mrcrayfish.furniturece.block.BlockBobbleHead;
import com.mrcrayfish.furniturece.block.BlockBookShelf;
import com.mrcrayfish.furniturece.block.BlockBookStack;
import com.mrcrayfish.furniturece.block.BlockBottle;
import com.mrcrayfish.furniturece.block.BlockCanvas;
import com.mrcrayfish.furniturece.block.BlockCeilingFan;
import com.mrcrayfish.furniturece.block.BlockChimney;
import com.mrcrayfish.furniturece.block.BlockCoffeeMachine;
import com.mrcrayfish.furniturece.block.BlockCoffeeTable;
import com.mrcrayfish.furniturece.block.BlockCooler;
import com.mrcrayfish.furniturece.block.BlockCorkBoard;
import com.mrcrayfish.furniturece.block.BlockGreenWheelieBin;
import com.mrcrayfish.furniturece.block.BlockMicrophone;
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
	
	/* Office */
	public static Block cooler;
	public static Block book_stack;
	public static Block ceiling_fan;
	public static Block bookshelf;
	public static Block microphone;
	public static Block air_vent;
	public static Block coffee_machine;
	public static Block three_dimension_printer;
	public static Block bobble_head;
	public static Block cork_board;
	
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
		cooler = new BlockCooler(Material.rock).setUnlocalizedName("cooler");
		book_stack = new BlockBookStack(Material.cloth).setUnlocalizedName("book_stack");
		ceiling_fan = new BlockCeilingFan(Material.wood).setUnlocalizedName("ceiling_fan");
		bookshelf = new BlockBookShelf(Material.wood).setUnlocalizedName("book_shelf");
		microphone = new BlockMicrophone(Material.wood).setUnlocalizedName("microphone");
		air_vent = new BlockAirVent(Material.wood).setUnlocalizedName("air_vent");
		coffee_machine = new BlockCoffeeMachine(Material.wood).setUnlocalizedName("coffee_machine");
		three_dimension_printer = new Block3DPrinter(Material.wood).setUnlocalizedName("three_dimension_printer");
		bobble_head = new BlockBobbleHead(Material.wood).setUnlocalizedName("bobble_head");
		cork_board = new BlockCorkBoard(Material.wood).setUnlocalizedName("cork_board");
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
		GameRegistry.registerBlock(cooler, ItemFurniture.class, cooler.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(book_stack, ItemFurniture.class, book_stack.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(ceiling_fan, ItemFurniture.class, ceiling_fan.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(bookshelf, ItemFurniture.class, bookshelf.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(microphone, ItemFurniture.class, microphone.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(air_vent, ItemFurniture.class, air_vent.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(coffee_machine, ItemFurniture.class, coffee_machine.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(three_dimension_printer, ItemFurniture.class, three_dimension_printer.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(bobble_head, ItemFurniture.class, bobble_head.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(cork_board, ItemFurniture.class, cork_board.getUnlocalizedName().substring(5));
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
		registerRender(cooler);
		registerRender(book_stack);
		registerRender(ceiling_fan);
		registerRender(bookshelf);
		registerRender(microphone);
		registerRender(air_vent);
		registerRender(coffee_machine);
		registerRender(three_dimension_printer);
		registerRender(bobble_head);
		registerRender(cork_board);
	}
	
	private static void registerRender(Block blockIn)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(blockIn), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + blockIn.getUnlocalizedName().substring(5), "inventory"));
	}
}
