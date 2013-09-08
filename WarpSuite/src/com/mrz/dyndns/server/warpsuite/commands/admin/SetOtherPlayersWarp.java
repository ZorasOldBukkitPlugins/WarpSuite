package com.mrz.dyndns.server.warpsuite.commands.admin;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

import java.util.List;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.commands.WarpSuiteCommand;
import com.mrz.dyndns.server.warpsuite.managers.WarpManager;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.players.OfflineWarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.util.SimpleLocation;
import com.mrz.dyndns.server.warpsuite.util.Util;

public class SetOtherPlayersWarp extends WarpSuiteCommand
{

	public SetOtherPlayersWarp(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		if(Permissions.ADMIN_SET.check(player, true) == false)
		{
			return true;
		}
		
		if(args.size() == 0)
		{
			return false;
		}
		
		String targetName = variables.get(0);
		OfflineWarpSuitePlayer target = new OfflineWarpSuitePlayer(targetName, plugin);
		String warpName = args.get(0);
		
		if(Util.isValidWarpName(warpName) == false)
		{
			player.sendMessage(NEGATIVE_PRIMARY + "\'" + NEGATIVE_SECONDARY + warpName + NEGATIVE_PRIMARY + "\' is an invalid warp name!");
			return true;
		}
		
		if(target.hasPlayedBefore() == false)
		{
			player.sendMessage(WARNING_PRIMARY + "Note: player \'" + WARNING_SECONDARY + targetName 
					+ WARNING_PRIMARY + "\' has not played on this server before, so a new warp file has been made for him");
		}
		
		WarpManager manager = target.getOrMakeWarpManager();
		
		SimpleLocation sLoc = new SimpleLocation(player.getPlayer().getLocation());
		boolean overWritten = manager.warpIsSet(warpName);
		manager.setWarp(warpName, sLoc);
		player.sendMessage(POSITIVE_PRIMARY + "Warp \'" + POSITIVE_SECONDARY + warpName + POSITIVE_PRIMARY 
				+ "\' has been " + (overWritten ? "overwritten" : "set") + " for player \'" + POSITIVE_SECONDARY + targetName + POSITIVE_PRIMARY + "\'");
		
		return true;
	}

	@Override
	public String getUsage()
	{
		return "warp " + USAGE_ARGUMENT + "[playerName] " + USAGE + "set|add " + USAGE_ARGUMENT + "[warpName]";
	}

}
