package com.mrz.dyndns.server.warpsuite.util;

import org.bukkit.Bukkit;

import com.onarandombox.MultiverseCore.MultiverseCore;

public class WorldLoader
{
	private WorldLoader()
	{
	}
	
	private static MultiverseCore mvCore;
	
	static
	{
		mvCore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
	}
	
	public static boolean Load(String worldName)
	{
		return mvCore.getMVWorldManager().loadWorld(worldName);
	}
}
