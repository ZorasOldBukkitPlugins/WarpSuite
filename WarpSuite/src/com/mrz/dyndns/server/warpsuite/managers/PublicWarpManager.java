package com.mrz.dyndns.server.warpsuite.managers;

import org.bukkit.command.CommandSender;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.util.MyConfig;

public class PublicWarpManager extends WarpManager
{
	public PublicWarpManager(WarpSuite plugin)
	{
		super(new MyConfig("public", plugin));
	}
	
	public boolean checkPlayer(WarpSuitePlayer player, String warpName)
	{
		return checkPlayer(player.getPlayer(), warpName);
	}
	
	public boolean checkPlayer(CommandSender sender, String warpName)
	{
		if(warpIsSet(warpName) == false)
		{
			return false;
		}
		return sender.hasPermission(Permissions.PUBLIC_BASE.getNode() + warpName) 
				|| sender.hasPermission(Permissions.PUBLIC_BASE.getNode() + "*")
				|| sender.hasPermission(Permissions.WARP_BASE.getNode() + "*")
				|| sender.hasPermission("*");
	}
}
