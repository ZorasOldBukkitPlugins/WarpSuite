package com.mrz.dyndns.server.warpsuite.commands;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

import java.util.List;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.util.SimpleLocation;
import com.mrz.dyndns.server.warpsuite.util.Util;

public class GoPlayersOwnWarp extends WarpSuiteCommand
{

	public GoPlayersOwnWarp(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		if(args.size() == 0)
		{
			player.sendMessage(NEGATIVE_PRIMARY + "Invalid usage!" + POSITIVE_PRIMARY + " Correct usage: " + USAGE + "/warp " + USAGE_ARGUMENT + " [warpName]");
			if(Permissions.HELP.check(player))
			{
				player.sendMessage(POSITIVE_PRIMARY + "Or, if you want to view all of the warp help, issue " + USAGE + "/warp help");
			}
			return true;
		}
		
		String warpName = args.get(0);
		if(player.getWarpManager().warpIsSet(warpName))
		{
			SimpleLocation sLoc = player.getWarpManager().loadWarp(warpName);
			Util.Debug("World name: " + sLoc.getWorld());
			boolean canGoToWorld = sLoc.tryLoad(plugin);
			if(canGoToWorld)
			{
				player.teleport(sLoc);
				return true;
			}
			else
			{
				player.sendMessage(NEGATIVE_PRIMARY + "The world warp \'" + NEGATIVE_SECONDARY + "\' is located in either no longer exists, or isn't loaded");
				return true;
			}
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
		//I'll do this myself above (see line (around) 24)
		return null;
	}

}
