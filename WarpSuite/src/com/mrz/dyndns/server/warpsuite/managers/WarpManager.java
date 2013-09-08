package com.mrz.dyndns.server.warpsuite.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;

import com.mrz.dyndns.server.warpsuite.util.MyConfig;
import com.mrz.dyndns.server.warpsuite.util.SimpleLocation;
import com.mrz.dyndns.server.warpsuite.util.Util;

public class WarpManager
{
	public WarpManager(MyConfig config)
	{
		this.config = config;
		warps = new HashMap<String, SimpleLocation>();
	}
	
	private final MyConfig config;
	private final Map<String, SimpleLocation> warps;
	
	public void loadWarpsFromConfig()
	{
		Set<String> warpNames = config.getCustomConfig().getConfigurationSection("Warps").getKeys(false);
		for(String warpName : warpNames)
		{
			warps.put(warpName, loadWarp(warpName));
			Util.Debug("Warp successfully loaded: " + warpName);
		}
	}
	
	public int getAmountOfSetWarps()
	{
		Set<String> warps = getWarpConfigurationSection();
		if(warps == null)
		{
			return 0;
		}
		
		return config.getCustomConfig().getConfigurationSection("Warps").getKeys(false).size();
	}
	
	public boolean warpIsSet(String warpName)
	{
		Set<String> warps = getWarpConfigurationSection();
		if(warps == null)
		{
			return false;
		}
		return getWarpConfigurationSection().contains(warpName.toLowerCase());
	}
	
	public SimpleLocation loadWarp(String warpName)
	{
		warpName = warpName.toLowerCase();
		
		String world = config.getCustomConfig().getString("Warps." + warpName + ".World");
		double x = config.getCustomConfig().getDouble("Warps." + warpName + ".X");
		double y = config.getCustomConfig().getDouble("Warps." + warpName + ".Y");
		double z = config.getCustomConfig().getDouble("Warps." + warpName + ".Z");
		double yaw = config.getCustomConfig().getDouble("Warps." + warpName + ".Yaw");
		double pitch = config.getCustomConfig().getDouble("Warps." + warpName + ".Pitch");
		
		String listingName = config.getCustomConfig().getString("Warps." + warpName + ".ListingName");
		
		SimpleLocation sLoc = new SimpleLocation(world, x, y, z, yaw, pitch);
		sLoc.setListingName(listingName);
		
		return sLoc;
	}
	
	public void setWarp(String warpName, SimpleLocation warp)
	{
		String listingName = warpName;
		warpName = warpName.toLowerCase();
		
		config.getCustomConfig().set("Warps." + warpName + ".World", warp.getWorld());
		config.getCustomConfig().set("Warps." + warpName + ".X", warp.getX());
		config.getCustomConfig().set("Warps." + warpName + ".Y", warp.getY());
		config.getCustomConfig().set("Warps." + warpName + ".Z", warp.getZ());
		config.getCustomConfig().set("Warps." + warpName + ".Yaw", warp.getYaw());
		config.getCustomConfig().set("Warps." + warpName + ".Pitch", warp.getPitch());
		
		config.getCustomConfig().set("Warps." + warpName + ".ListingName", listingName);
		
		config.saveCustomConfig();
	}
	
	public void removeWarp(String warpName)
	{
		config.getCustomConfig().set("Warps." + warpName + ".World", null);
		config.getCustomConfig().set("Warps." + warpName + ".X", null);
		config.getCustomConfig().set("Warps." + warpName + ".Y", null);
		config.getCustomConfig().set("Warps." + warpName + ".Z", null);
		config.getCustomConfig().set("Warps." + warpName + ".Yaw", null);
		config.getCustomConfig().set("Warps." + warpName + ".Pitch", null);

		config.getCustomConfig().set("Warps." + warpName + ".ListingName", null);
		
		config.getCustomConfig().set("Warps." + warpName, null);
		
		config.saveCustomConfig();
	}
	
	public List<String> getWarpList()
	{
		Set<String> warpSet = getWarpConfigurationSection();
		List<String> warpList = new ArrayList<String>();
		for(String warpName : warpSet)
		{
			warpList.add(config.getCustomConfig().getString("Warps." + warpName + ".ListingName"));
		}
		return warpList;
	}
	
	private Set<String> getWarpConfigurationSection()
	{
		ConfigurationSection warps = config.getCustomConfig().getConfigurationSection("Warps");
		if(warps == null)
		{
			return null;
		}
		else
		{
			return warps.getKeys(false);
		}
	}
}
