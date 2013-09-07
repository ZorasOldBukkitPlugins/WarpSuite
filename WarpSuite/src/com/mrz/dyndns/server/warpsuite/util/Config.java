package com.mrz.dyndns.server.warpsuite.util;

import org.bukkit.configuration.Configuration;

public final class Config
{
	private Config()
	{
	}
	
	public static void load(Configuration config)
	{
		timer = config.getInt("timer", 10);
		radius = config.getInt("radius", 10);
		cancelOnDamage = config.getBoolean("cancelOnDamage", true);
		timerAndRadius = config.getBoolean("timerAndRadius", true);
		globalWarpsCountTowardsTotal = config.getBoolean("globalWarpsCountTowardsTotal", false);
		defaultMaxWarps = config.getInt("defaultMaxWarps", 50);
		warpInviteTimeout = config.getInt("warpInviteTimeout", 120);
		useMultiverse = config.getBoolean("useMultiverse", true);
	}
	
	public static int timer;
	public static int radius;
	public static boolean cancelOnDamage;
	public static boolean timerAndRadius;
	public static boolean globalWarpsCountTowardsTotal;
	public static int defaultMaxWarps;
	public static int warpInviteTimeout;
	public static boolean useMultiverse;
}
