package com.mrcrayfish.furniturece;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config
{
	public static Configuration config;
	
	public static final String CATEGORY_GENERAL = "general";
	
	public static boolean showTips;
	
	public static void init(File file)
	{
		if (config == null)
		{
			config = new Configuration(file);
		}
		loadConfig();
	}
	
	public static void loadConfig()
	{
		showTips = config.getBoolean("show-tips", CATEGORY_GENERAL, true, "Show tips about furniture in-game (Client Side Only)");
	}
}
