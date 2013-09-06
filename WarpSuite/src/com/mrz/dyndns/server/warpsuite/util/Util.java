package com.mrz.dyndns.server.warpsuite.util;

import java.util.logging.Level;

import com.mrz.dyndns.server.warpsuite.WarpSuite;

public final class Util
{
	private Util()
	{	
	}
	
	private static boolean isDebugging = false;
	private static WarpSuite plugin;
	
	public static void initialize(WarpSuite ws)
	{
		plugin = ws;
	}
	
	public static void setDebugging(boolean debugging)
	{
		isDebugging = debugging;
	}
	
	public static void Debug(Object message)
	{
		if(isDebugging == true)
		{
			if(plugin != null)
			{
				plugin.getLogger().log(Level.INFO, "[DEBUG] " + message.toString());
			}
			else
			{
				System.out.println(plugin.getName() + "[INFO] [DEBUG] " + message.toString());
			}
		}
	}
	
	public static long getUnixTime()
	{
		return System.currentTimeMillis() / 1000L;
	}
}
