package com.mrz.dyndns.server.warpsuite.commands.admin;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

import java.util.List;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.commands.WarpSuiteCommand;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.util.SimpleLocation;

public class WarpPlayerToMyWarp extends WarpSuiteCommand
{

	public WarpPlayerToMyWarp(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		if(Permissions.ADMIN_TOMY.check(player, true) == false)
		{
			return true;
		}
		
		String targetName = variables.get(0);
		WarpSuitePlayer target = plugin.getPlayerManager().getWarpPlayer(targetName);
		if(target == null)
		{
			player.sendMessage(NEGATIVE_PRIMARY + "\'" + NEGATIVE_SECONDARY + targetName + NEGATIVE_PRIMARY + "\' is not online!");
			return true;
		}
		else
		{
			if(args.size() == 0)
			{
				return false;
			}
			else
			{
				String warpName = args.get(0);
				if(player.getWarpManager().warpIsSet(warpName) == false)
				{
					player.sendMessage(NEGATIVE_PRIMARY + "You do not have a warp called \'" + NEGATIVE_SECONDARY + warpName + NEGATIVE_PRIMARY + "\' set!");
					return true;
				}
				else
				{
					SimpleLocation sLoc = player.getWarpManager().loadWarp(warpName);
					target.warpTo(sLoc, true);
					player.sendMessage(POSITIVE_PRIMARY + "Player \'" + POSITIVE_SECONDARY + targetName + POSITIVE_PRIMARY 
							+ "\' has been sent to warp \'" + POSITIVE_SECONDARY + sLoc.getListingName() + POSITIVE_PRIMARY + "\'");
					return true;
				}
			}
		}
	}

	@Override
	public String getUsage()
	{
		return "warp " + USAGE_ARGUMENT + "[playerName] " + USAGE + "to my " + USAGE_ARGUMENT + "[warpName]";
	}

}
