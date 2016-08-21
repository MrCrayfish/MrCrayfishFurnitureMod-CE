package com.mrcrayfish.furniturece.achievement;

import com.mrcrayfish.furniturece.init.FurnitureBlocks;
import com.mrcrayfish.furniturece.item.ItemEasterEgg;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class FurnitureAchievements
{
	public static Achievement carpenterApprentice;
	public static Achievement carpenterKing;
	public static Achievement easter_egg_1;
	public static Achievement easter_egg_2;
	public static Achievement easter_egg_3;
	public static Achievement easter_egg_4;
	
	public static AchievementPage page;
	
	public static void loadAchievements()
	{
		carpenterApprentice = new Achievement("achievement.cfmce_apprentice", "cfmce_apprentice", 0, 0, new ItemStack(FurnitureBlocks.canvas), null).setSpecial().registerStat();
		carpenterKing = new Achievement("achievement.cfmce_king", "cfmce_king", 0, 2, new ItemStack(FurnitureBlocks.spiral_stairs), carpenterApprentice).registerStat();
		easter_egg_1 = new Achievement("achievement.cfmce_easter_egg_1", "cfmce_easter_egg_1", 2, 4, ItemEasterEgg.getEasterEgg(255, 42, 0, 255, 255, 255), carpenterKing).registerStat();
		easter_egg_2 = new Achievement("achievement.cfmce_easter_egg_2", "cfmce_easter_egg_2", 2, 6, ItemEasterEgg.getEasterEgg(0, 114, 255, 255, 102, 0), carpenterKing).registerStat();
		easter_egg_3 = new Achievement("achievement.cfmce_easter_egg_3", "cfmce_easter_egg_3", -2, 4, ItemEasterEgg.getEasterEgg(0, 127, 14, 0, 219, 21), carpenterKing).registerStat();
		easter_egg_4 = new Achievement("achievement.cfmce_easter_egg_4", "cfmce_easter_egg_4", -2, 6, ItemEasterEgg.getEasterEgg(0, 0, 0, 255, 0, 182), carpenterKing).registerStat();
		
		page = new AchievementPage("Furniture Mod: CE", carpenterApprentice, carpenterKing, easter_egg_1, easter_egg_2, easter_egg_3, easter_egg_4);
	}
	
	public static void registerPage()
	{
		AchievementPage.registerAchievementPage(page);
	}
}
