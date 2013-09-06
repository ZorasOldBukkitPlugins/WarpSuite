package com.mrz.dyndns.server.warpsuite.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mrz.dyndns.server.CommandSystem.SimpleCommand;
import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.util.Util;

public class SetPlayersOwnWarp extends WarpSuiteCommand
{
	public SetPlayersOwnWarp(WarpSuite plugin)
	{
		super(plugin);
	}

	//warp set|add [warpname]
	@Override
	public boolean Execute(String commandName, CommandSender sender, List<String> args, List<String> variables)
	{
		if(!(sender instanceof Player))
		{
			return Util.mustBePlayer(sender);
		}
		
		String warpName = args.get(0);
		WarpSuitePlayer player = plugin.getPlayerManager().getWarpPlayer(sender.getName());
		
		return false;
	}

}
