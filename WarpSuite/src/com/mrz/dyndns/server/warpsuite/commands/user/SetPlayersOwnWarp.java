package com.mrz.dyndns.server.warpsuite.commands.user;

import java.util.List;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.commands.WarpSuiteCommand;
import com.mrz.dyndns.server.warpsuite.permissions.NumeralPermissions;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.util.Config;
import com.mrz.dyndns.server.warpsuite.util.SimpleLocation;
import com.mrz.dyndns.server.warpsuite.util.Util;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

public class SetPlayersOwnWarp extends WarpSuiteCommand
{
	//warp set|add [warpname]
	public SetPlayersOwnWarp(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		if(args.size() == 0)
		{
			return false;
		}
		
		if(Permissions.WARP_SET.check(player, true) == false)
		{
			return true;
		}
		
		int allowedWarps = NumeralPermissions.COUNT.getAmount(player);
		int warpsOwned = player.getWarpManager().getAmountOfSetWarps();
		if(Config.globalWarpsCountTowardsTotal)
		{
			warpsOwned += plugin.getPublicWarpManager().getWarpList().size();
		}
		
		if(allowedWarps != -1 && warpsOwned >= allowedWarps)
		{
			player.sendMessage(NEGATIVE_PRIMARY + "You already have the maximum warps you're allowed to set! (" + NEGATIVE_SECONDARY + allowedWarps + NEGATIVE_PRIMARY + ")");
			return true;
		}
		
		String warpName = args.get(0);
		if(!Util.isValidWarpName(warpName))
		{
			player.sendMessage(NEGATIVE_PRIMARY + "\'" + NEGATIVE_SECONDARY + warpName + NEGATIVE_PRIMARY + "\' is an invalid warp name!");
			return true;
		}
		if(plugin.getPublicWarpManager().checkPlayer(player, warpName))
		{
			player.sendMessage(WARNING_PRIMARY + "Note: Your warp (" + WARNING_SECONDARY + warpName + WARNING_PRIMARY + ") has the same name as public warp \'" 
					+ WARNING_SECONDARY + warpName + WARNING_PRIMARY + "\'. To go to YOUR warp and not the public warp, use the command " + USAGE 
					+ "/warp my " + USAGE_ARGUMENT + warpName);
		}
		boolean overwritten = player.getWarpManager().warpIsSet(warpName);
		player.getWarpManager().setWarp(warpName, new SimpleLocation(player.getPlayer().getLocation()));
		player.sendMessage(POSITIVE_PRIMARY + "Warp \'" + POSITIVE_SECONDARY + warpName + POSITIVE_PRIMARY + "\' has been " + (overwritten ? "overwritten" : "set") + ".");
		
		return true;
	}

	@Override
	public String getUsage()
	{
		return "warp set|add " + USAGE_ARGUMENT + "[warpName]";
	}
}
