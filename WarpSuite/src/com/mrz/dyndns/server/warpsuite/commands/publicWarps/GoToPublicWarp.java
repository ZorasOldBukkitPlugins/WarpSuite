package com.mrz.dyndns.server.warpsuite.commands.publicWarps;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

import java.util.List;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.commands.WarpSuiteCommand;
import com.mrz.dyndns.server.warpsuite.managers.PublicWarpManager;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.util.SimpleLocation;

public class GoToPublicWarp extends WarpSuiteCommand
{

	public GoToPublicWarp(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		if(Permissions.PUBLIC_WARP.check(player, true) == false)
		{
			return true;
		}
		
		if(args.size() == 0)
		{
			return false;
		}
		
		String warpName = args.get(0);
		PublicWarpManager manager = plugin.getPublicWarpManager();
		if(manager.warpIsSet(warpName) == false)
		{
			player.sendMessage(NEGATIVE_PRIMARY + "There is no public warp called \'" + NEGATIVE_SECONDARY + warpName + NEGATIVE_PRIMARY + "\' set!");
			return true;
		}
		
		SimpleLocation sLoc = manager.loadWarp(warpName);
		player.warpTo(sLoc, false);
		return true;
	}

	@Override
	public String getUsage()
	{
		return "warp public " + USAGE_ARGUMENT + "[warpName]";
	}

}
