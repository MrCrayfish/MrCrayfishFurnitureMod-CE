package com.mrcrayfish.furniturece.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FurnitureCrafting 
{
	public static void register()
	{
		GameRegistry.addRecipe(new ItemStack(FurnitureItems.info_pencil), "C  ", " W ", "  W", 'C', new ItemStack(Items.COAL, 1, 0), 'W', Blocks.PLANKS);
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.aquarium), "PPP", "GGG", "PPP", 'P', new ItemStack(Blocks.PLANKS, 1, 5), 'G', Blocks.GLASS_PANE);
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.canvas), "PPP", "PPP", "L L", 'P', new ItemStack(Blocks.PLANKS, 1, 0), 'L', new ItemStack(Blocks.LOG, 1, 0));
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.chimney), " B ", "BBB", "BIB", 'B', Blocks.BRICK_BLOCK, 'I', Blocks.IRON_BARS);
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.spiral_stairs, 4), "IPP", "IPP", "IPP", 'I', Items.IRON_INGOT, 'P', new ItemStack(Blocks.PLANKS, 1, 1));
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.bear_rug), " W ", "WWW", "WWW", 'W', new ItemStack(Blocks.WOOL, 1, 12));
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.black_board), "PPP", "PCP", "PPP", 'P', new ItemStack(Blocks.PLANKS, 1, 0), 'C', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 15));
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.office_chair), "IWI", "WWW", "CIC", 'I', Items.IRON_INGOT, 'W', new ItemStack(Blocks.WOOL, 1, 15), 'C', Blocks.COAL_BLOCK);
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.green_wheelie_bin), "CCC", "CCC", "KCK", 'C', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 13), 'K', Blocks.COAL_BLOCK);
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.coffee_table), "PGP", "L L", 'P', new ItemStack(Blocks.PLANKS, 1, 0), 'G', Blocks.GLASS, 'L', new ItemStack(Blocks.LOG, 1, 0));
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.bottle), " P ", "G G", "GGG", 'P', new ItemStack(Blocks.PLANKS, 1, 0), 'G', Blocks.GLASS);
	}
}
