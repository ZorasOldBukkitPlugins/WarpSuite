package com.mrz.dyndns.server.warpsuite.managers;

import org.bukkit.entity.Player;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.util.MyConfig;

public class PublicWarpManager
{
	private static PublicWarpManager instance = null;
	
	public static PublicWarpManager getInstance()
	{
		return instance;
	}
	
	public static void initialize(WarpSuite plugin)
	{
		instance = new PublicWarpManager(plugin);
	}

	private PublicWarpManager(WarpSuite plugin)
	{
		//this.plugin = plugin;
		manager = new WarpManager(new MyConfig("public", plugin));
	}
	
	private final WarpManager manager;
	
	public WarpManager getWarpManager()
	{
		return manager;
	}
	
	public boolean checkPlayer(WarpSuitePlayer player, String warpName)
	{
		if(manager.warpIsSet(warpName) == false)
		{
			return false;
		}
		
		Player p = player.getPlayer();
		return p.hasPermission(Permissions.PUBLIC_BASE.getNode() + warpName) || p.hasPermission(Permissions.PUBLIC_BASE + "*");
	}
}
