package com.mrz.dyndns.server.warpsuite.commands.invites;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

import java.util.List;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.commands.WarpSuiteCommand;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;

public class DenyInvite extends WarpSuiteCommand
{

	public DenyInvite(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		if(Permissions.INVITE_DENY.check(player, true) == false)
		{
			return true;
		}
		
		boolean result = player.clearRequest();
		if(result)
		{
			player.sendMessage(POSITIVE_PRIMARY + "Request denied");
			return true;
		}
		else
		{
			player.sendMessage(NEGATIVE_PRIMARY + "You don\'t have any pending warp requests!");
			return true;
		}
	}

	@Override
	public String getUsage()
	{
		return "warp deny";
	}

}
