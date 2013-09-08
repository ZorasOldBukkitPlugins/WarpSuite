package com.mrz.dyndns.server.warpsuite.commands.user;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

import java.util.List;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.commands.WarpSuiteCommand;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;

public class RemovePlayersOwnWarp extends WarpSuiteCommand
{

	public RemovePlayersOwnWarp(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		if(Permissions.WARP_REMOVE.check(player, true) == false)
		{
			return true;
		}
		
		if(args.size() == 0)
		{
			return false;
		}
		
		String warpName = args.get(0);
		if(player.getWarpManager().warpIsSet(warpName))
		{
			player.getWarpManager().removeWarp(warpName);
			player.sendMessage(POSITIVE_PRIMARY + "Warp \'" + POSITIVE_SECONDARY + warpName + POSITIVE_PRIMARY + "\' has been removed.");
			return true;
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
		return "warp remove|del|delete|clear " + USAGE_ARGUMENT + "[warpName]";
	}

}
