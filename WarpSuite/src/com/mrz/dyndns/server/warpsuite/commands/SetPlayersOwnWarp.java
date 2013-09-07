package com.mrz.dyndns.server.warpsuite.commands;

import java.util.List;

import org.bukkit.ChatColor;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.permissions.NumeralPermissions;
import com.mrz.dyndns.server.warpsuite.util.Coloring;
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
		int allowedWarps = NumeralPermissions.COUNT.getAmount(player.getPlayer());
		int warpsOwned = player.getWarpManager().getAmountOfSetWarps();
		
		if(allowedWarps != -1 && warpsOwned >= allowedWarps)
		{
			player.sendMessage(NEGATIVE_PRIMARY + "You already have the maximum warps you're allowed to set! (" + NEGATIVE_SECONDARY + allowedWarps + NEGATIVE_PRIMARY + ")");
			return true;
		}
		
		String warpName = args.get(0);
		if(player.getWarpManager().warpIsSet(warpName))
		{
			player.sendMessage(POSITIVE_PRIMARY + "Warp \'" + POSITIVE_SECONDARY + "");
		}
		
		return false;
	}
}
