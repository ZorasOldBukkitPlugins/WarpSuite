package com.mrz.dyndns.server.warpsuite.commands.invites;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

import java.util.List;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.commands.WarpSuiteCommand;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.util.SimpleLocation;

public class AcceptInvite extends WarpSuiteCommand
{

	public AcceptInvite(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		if(Permissions.INVITE_ACCEPT.check(player, true) == false)
		{
			return true;
		}
		
		SimpleLocation sLoc = player.getRequest();
		if(sLoc == null)
		{
			player.sendMessage(NEGATIVE_PRIMARY + "You don\'t have any pending warp requests!");
			return true;
		}
		
		player.warpTo(sLoc, false);
		return true;
	}

	@Override
	public String getUsage()
	{
		return "warp accept";
	}

}
