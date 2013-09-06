package com.mrz.dyndns.server.warpsuite.util;

import org.bukkit.Location;

public class SimpleLocation
{
	public SimpleLocation(double x, double y, double z, double yaw, double pitch)
	{
		this.X = x;
		this.Y = y;
		this.Z = z;
		this.Yaw = yaw;
		this.Pitch = pitch;
	}
	
	public SimpleLocation(Location loc)
	{
		this.X = loc.getX();
		this.Y = loc.getY();
		this.Z = loc.getZ();
		this.Yaw = loc.getYaw();
		this.Pitch = loc.getPitch();
	}
	
	public final double X;
	public final double Y;
	public final double Z;
	public final double Yaw;
	public final double Pitch;
}
