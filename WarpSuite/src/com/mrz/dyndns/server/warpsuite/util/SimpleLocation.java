package com.mrz.dyndns.server.warpsuite.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.mrz.dyndns.server.warpsuite.WarpSuite;

public class SimpleLocation
{
	public SimpleLocation(String world, double x, double y, double z, double yaw, double pitch)
	{
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	public SimpleLocation(Location loc)
	{
		this.world = loc.getWorld().getName();
		this.x = loc.getX();
		this.y = loc.getY();
		this.z = loc.getZ();
		this.yaw = loc.getYaw();
		this.pitch = loc.getPitch();
	}
	
	private final String world;
	private final double x;
	private final double y;
	private final double z;
	private final double yaw;
	private final double pitch;
	
	private String listingName;
	
	public boolean tryLoad(WarpSuite plugin)
	{
		if(Config.useMultiverse)
		{
			if(plugin.isUsingMultiverse())
			{
				return WorldLoader.Load(world);
			}
		}
		
		return plugin.getServer().getWorld(world) != null;
	}
	
	public Location toLocation()
	{
		return new Location(Bukkit.getWorld(world), x, y, z, (float)yaw, (float)pitch);
	}
	
	public String getWorld()
	{
		return world;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getZ()
	{
		return z;
	}

	public double getYaw()
	{
		return yaw;
	}

	public double getPitch()
	{
		return pitch;
	}

	public String getListingName()
	{
		return listingName;
	}

	public void setListingName(String listingName)
	{
		this.listingName = listingName;
	}
}
