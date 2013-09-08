package com.mrz.dyndns.server.warpsuite.commands;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

import java.util.List;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.managers.WarpManager;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.players.OfflineWarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.util.SimpleLocation;
import com.mrz.dyndns.server.warpsuite.util.Util;

public class GoPlayersWarp extends WarpSuiteCommand
{

	public GoPlayersWarp(WarpSuite plugin, boolean disregardPublicWarp)
	{
		super(plugin);
		this.disregardPublicWarp = disregardPublicWarp;
	}
	
	private final boolean disregardPublicWarp;

	@Override
	public boolean warpPlayerExecute(final WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		if(!Permissions.WARP.check(player) && !Permissions.HELP.check(player) && !Permissions.ADMIN_WARP.check(player))
		{
			return Util.invalidPermissions(player);
		}
		
		if(args.size() == 0)
		{
			if(Permissions.WARP.check(player))
			{
				player.sendMessage(NEGATIVE_PRIMARY + "Invalid usage!" + POSITIVE_PRIMARY + " Correct usage: " + USAGE + "/warp " + USAGE_ARGUMENT + "[warpName]");
			}
			if(Permissions.ADMIN_WARP.check(player))
			{
				player.sendMessage(NEGATIVE_PRIMARY + "Invalid usage!" + POSITIVE_PRIMARY + " Correct usage: " + USAGE + "/warp " + USAGE_ARGUMENT + "[playerName] [warpName]");
			}
			if(Permissions.HELP.check(player))
			{
				player.sendMessage(POSITIVE_PRIMARY + "If you want to view all of the warp commands, issue " + USAGE + "/warp help");
			}
			return true;
		}
		
		if(args.size() == 2)
		{
			//this is an admin command
			if(Permissions.ADMIN_WARP.check(player) == false)
			{
				return Util.invalidPermissions(player);
			}
			
			String targetPlayer = args.get(0);
			OfflineWarpSuitePlayer target = new OfflineWarpSuitePlayer(targetPlayer, plugin);
			WarpManager warpManager = target.getWarpManager();
			if(warpManager == null)
			{
				player.sendMessage(NEGATIVE_PRIMARY + "Player \'" + NEGATIVE_SECONDARY + targetPlayer + NEGATIVE_PRIMARY + "\' either has no warps, or hasn't played on this server before.");
				return true;
			}
			else
			{
				String warpName = args.get(1);
				if(warpManager.warpIsSet(warpName))
				{
					SimpleLocation sLoc = warpManager.loadWarp(warpName);
					return player.warpTo(sLoc, false);
				}
				else
				{
					player.sendMessage(NEGATIVE_PRIMARY + "Player \'" + NEGATIVE_SECONDARY + targetPlayer + NEGATIVE_PRIMARY + "\' does not have a warp called \'" +
							NEGATIVE_SECONDARY + warpName + NEGATIVE_PRIMARY + "\' set!");
					return true;
				}
			}
		}
		
		String warpName = args.get(0);
		if(disregardPublicWarp == false)
		{
			if(plugin.getPublicWarpManager().checkPlayer(player, warpName))
			{
				return player.warpTo(plugin.getPublicWarpManager().loadWarp(warpName), false);
			}
		}
		if(player.getWarpManager().warpIsSet(warpName))
		{
			final SimpleLocation sLoc = player.getWarpManager().loadWarp(warpName);
			return player.warpTo(sLoc, false);
		}
		else
		{
			player.sendMessage(NEGATIVE_PRIMARY + "Warp \'" + NEGATIVE_SECONDARY + warpName + NEGATIVE_PRIMARY + "\' is not set!");
			return true;
		}
	}

	@Override
	public String getUsage()
	{
		//I'll do this myself above (see line (around) 25)
		return null;
	}
}
