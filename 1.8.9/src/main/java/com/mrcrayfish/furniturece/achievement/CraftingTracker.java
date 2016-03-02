package com.mrcrayfish.furniturece.achievement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mrcrayfish.furniturece.block.IModelInfo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

public class CraftingTracker
{
	private static Map<String, List<String>> craftedFurniture;
	
	public static void addCrafting(EntityPlayer player, ItemBlock item)
	{
		if(player.worldObj.isRemote) return;
		
		if(!(item.block instanceof IModelInfo))
		{
			return;
		}
		
		if(craftedFurniture == null)
		{
			craftedFurniture = new HashMap<String, List<String>>();
		}
		
		String uuid = player.getUniqueID().toString();
		
		if(craftedFurniture.get(uuid) == null)
		{
			craftedFurniture.put(uuid, new ArrayList<String>());
		}
		
		String itemName = Item.itemRegistry.getNameForObject(item).toString();
		List<String> crafted = craftedFurniture.get(uuid);
		if(!crafted.contains(itemName))
		{
			crafted.add(itemName);
			if(crafted.size() >= 1)
			{
				player.triggerAchievement(FurnitureAchievements.carpenterApprentice);
			}
			if(crafted.size() >= 10)
			{
				player.triggerAchievement(FurnitureAchievements.carpenterKing);
			}
		}
	}
	
	@SubscribeEvent
	public void onLogout(PlayerLoggedOutEvent e)
	{
		if(craftedFurniture != null)
		{
			craftedFurniture.remove(e.player.getUniqueID().toString());
		}
	}
}
