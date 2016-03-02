package com.mrcrayfish.furniturece.init;

import com.mrcrayfish.furniturece.Reference;
import com.mrcrayfish.furniturece.item.ItemEasterEgg;
import com.mrcrayfish.furniturece.item.ItemInfoPencil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FurnitureItems 
{
	public static Item info_pencil, easter_egg;
	
	public static void init()
	{
		info_pencil = new ItemInfoPencil().setUnlocalizedName("item_info_pencil");
		easter_egg = new ItemEasterEgg().setUnlocalizedName("item_easter_egg");
	}

	public static void register()
	{
		GameRegistry.registerItem(info_pencil, info_pencil.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(easter_egg, easter_egg.getUnlocalizedName().substring(5));
	}

	public static void registerRenders()
	{
		registerRender(info_pencil);
		registerRender(easter_egg);
	}

	private static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
