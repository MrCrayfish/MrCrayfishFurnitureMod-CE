package com.mrcrayfish.furniturece.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FurnitureCrafting 
{
	public static void register()
	{
		GameRegistry.addRecipe(new ItemStack(FurnitureItems.info_pencil), "C  ", " W ", "  W", 'C', new ItemStack(Items.coal, 1, 0), 'W', Blocks.planks);
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.aquarium), "PPP", "GGG", "PPP", 'P', new ItemStack(Blocks.planks, 1, 5), 'G', Blocks.glass_pane);
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.canvas), "PPP", "PPP", "L L", 'P', new ItemStack(Blocks.planks, 1, 0), 'L', new ItemStack(Blocks.log, 1, 0));
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.chimney), " B ", "BBB", "BIB", 'B', Blocks.brick_block, 'I', Blocks.iron_bars);
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.spiral_stairs, 4), "IPP", "IPP", "IPP", 'I', Items.iron_ingot, 'P', new ItemStack(Blocks.planks, 1, 1));
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.bear_rug), " W ", "WWW", "WWW", 'W', new ItemStack(Blocks.wool, 1, 12));
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.black_board), "PPP", "PCP", "PPP", 'P', new ItemStack(Blocks.planks, 1, 0), 'C', new ItemStack(Blocks.stained_hardened_clay, 1, 15));
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.office_chair), "IWI", "WWW", "CIC", 'I', Items.iron_ingot, 'W', new ItemStack(Blocks.wool, 1, 15), 'C', Blocks.coal_block);
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.green_wheelie_bin), "CCC", "CCC", "KCK", 'C', new ItemStack(Blocks.stained_hardened_clay, 1, 13), 'K', Blocks.coal_block);
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.coffee_table), "PGP", "L L", 'P', new ItemStack(Blocks.planks, 1, 0), 'G', Blocks.glass, 'L', new ItemStack(Blocks.log, 1, 0));
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.bottle), " P ", "G G", "GGG", 'P', new ItemStack(Blocks.planks, 1, 0), 'G', Blocks.glass);
	}
}
