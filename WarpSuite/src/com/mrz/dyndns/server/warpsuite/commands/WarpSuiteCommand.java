package com.mrz.dyndns.server.warpsuite.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.mrz.dyndns.server.CommandSystem.SimpleCommand;
import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.WarpSuitePlayer;

public abstract class WarpSuiteCommand implements SimpleCommand
{
	public WarpSuiteCommand(WarpSuite plugin)
	{
		this.plugin = plugin;
	}
	
	protected final WarpSuite plugin;
	
	@Override
	public boolean Execute(String commandName, CommandSender sender, List<String> args, List<String> variables)
	{
		if(sender instanceof Player)
		{
			return warpPlayerExecute(commandName, plugin.getPlayerManager().getWarpPlayer(sender.getName()), args, variables);
		}
		else
		{
			return consoleExecute(commandName, Bukkit.getConsoleSender(), args, variables);
		}
	}
	
	protected abstract boolean warpPlayerExecute(String commandName, WarpSuitePlayer player, List<String> args, List<String> variables);
	protected abstract boolean consoleExecute(String commandName, ConsoleCommandSender executor, List<String> args, List<String> variables);
}
