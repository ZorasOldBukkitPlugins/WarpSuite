package com.mrz.dyndns.server.warpsuite.util;

import org.bukkit.Bukkit;

import com.onarandombox.MultiverseCore.MultiverseCore;

public class WorldLoader
{
	private WorldLoader()
	{
	}
	
	//TODO: TEST
	
	private static MultiverseCore mvCore;
	
	static
	{
		mvCore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
	}
	
	public static boolean Load(String worldName)
	{
		boolean result = mvCore.getMVWorldManager().loadWorld(worldName);
		if(result)
		{
			Bukkit.getLogger().warning("Failed to load world \'" + worldName + "\'");
		}
		else
		{
			Bukkit.getLogger().info("Loaded world \'" + worldName + "\'");
		}
		return result;
	}
}
