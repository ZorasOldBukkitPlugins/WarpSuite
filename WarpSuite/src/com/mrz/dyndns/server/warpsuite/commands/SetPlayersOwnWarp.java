package com.mrz.dyndns.server.warpsuite.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.mrz.dyndns.server.CommandSystem.SimpleCommand;
import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.util.Util;

public class SetPlayersOwnWarp extends WarpSuiteCommand
{
	//warp set|add [warpname]
	public SetPlayersOwnWarp(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	protected boolean warpPlayerExecute(String commandName, WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		return false;
	}
}
