package com.mrz.dyndns.server.warpsuite.util;

import org.bukkit.Location;

import com.mrz.dyndns.server.warpsuite.WarpSuite;

public class SimpleLocation
{
	public SimpleLocation(String world, double x, double y, double z, double yaw, double pitch)
	{
		this.World = world;
		this.X = x;
		this.Y = y;
		this.Z = z;
		this.Yaw = yaw;
		this.Pitch = pitch;
	}
	
	public SimpleLocation(Location loc)
	{
		this.World = loc.getWorld().getName();
		this.X = loc.getX();
		this.Y = loc.getY();
		this.Z = loc.getZ();
		this.Yaw = loc.getYaw();
		this.Pitch = loc.getPitch();
	}
	
	private final String World;
	private final double X;
	private final double Y;
	private final double Z;
	private final double Yaw;
	private final double Pitch;
	
	public boolean tryLoad(WarpSuite plugin, String worldName)
	{
		if(Config.useMultiverse)
		{
			if(plugin.isUsingMultiverse())
			{
				return WorldLoader.Load(worldName);
			}
		}
		
		return plugin.getServer().getWorld(worldName) != null;
	}
	
	public String getWorld()
	{
		return World;
	}

	public double getX()
	{
		return X;
	}

	public double getY()
	{
		return Y;
	}

	public double getZ()
	{
		return Z;
	}

	public double getYaw()
	{
		return Yaw;
	}

	public double getPitch()
	{
		return Pitch;
	}
}
