package com.mrz.dyndns.server.warpsuite.commands;

import java.util.List;

import org.bukkit.ChatColor;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.permissions.NumeralPermissions;

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
			player.sendMessage(ChatColor.RED + "You already have the maximum warps you're allowed to set! (" + ChatColor.DARK_GRAY + allowedWarps + ChatColor.RED + ")");
			return true;
		}
		
		
		
		return false;
	}
}
