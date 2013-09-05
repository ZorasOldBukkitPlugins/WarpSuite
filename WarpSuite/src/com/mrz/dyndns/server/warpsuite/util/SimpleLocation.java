package com.mrz.dyndns.server.warpsuite.util;

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
	
	public final double X;
	public final double Y;
	public final double Z;
	public final double Yaw;
	public final double Pitch;
}
