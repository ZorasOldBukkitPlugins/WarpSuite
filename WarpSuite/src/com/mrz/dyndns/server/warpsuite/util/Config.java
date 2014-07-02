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
		cancelOnMobDamage = config.getBoolean("cancelOn.mobDamage", true);
		cancelOnMove = config.getBoolean("cancelOn.move", true);
		cancelOnPvp = config.getBoolean("cancelOn.playerPVP", true);
		globalWarpsCountTowardsTotal = config.getBoolean("globalWarpsCountTowardsTotal", false);
		defaultMaxWarps = config.getInt("defaultMaxWarps", 50);
		warpInviteTimeout = config.getInt("warpInviteTimeout", 120);
		useMultiverse = config.getBoolean("useMultiverse", true);
		useWarpListPages = config.getBoolean("warpListPages", true);
		defaultToPublicWarp = config.getBoolean("defaultToPublicWarp", true);
		forceWarpCommandOverride = config.getBoolean("forceCommandOverride.warp", false);
		forceDelwarpCommandOverride = config.getBoolean("forceCommandOverride.delwarp", false);
		forceSetwarpCommandOverride = config.getBoolean("forceCommandOverride.setwarp", false);
	}
	
	public static int timer;
	public static int radius;
	public static boolean cancelOnMobDamage;
	public static boolean globalWarpsCountTowardsTotal;
	public static int defaultMaxWarps;
	public static int warpInviteTimeout;
	public static boolean useMultiverse;
	public static boolean cancelOnMove;
	public static boolean cancelOnPvp;
	public static boolean useWarpListPages;
	public static boolean defaultToPublicWarp;
	public static boolean forceWarpCommandOverride;
	public static boolean forceDelwarpCommandOverride;
	public static boolean forceSetwarpCommandOverride;
}
