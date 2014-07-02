package com.mrz.dyndns.server.warpsuite.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.util.Coloring;
import com.mrz.dyndns.server.warpsuite.util.Util;
import com.mrz.dyndns.server.warpsuite.zorascommandsystem.bukkitcompat.CSBukkitCommand;

public abstract class WarpSuiteCommand implements CSBukkitCommand
{
	public WarpSuiteCommand(WarpSuite plugin)
	{
		this.plugin = plugin;
	}
	
	protected final WarpSuite plugin;
	
	@Override
	//public boolean Execute(String commandName, CommandSender sender, List<String> args, List<String> variables)
	public boolean execute(final CommandSender sender, final Player player, String cmdName, String[] preArgs_array, String[] args_array)
	{
		List<String> preArgs = new ArrayList<String>(Arrays.asList(preArgs_array));
		List<String> args = new ArrayList<String>(Arrays.asList(args_array));

		boolean result;
		if(sender instanceof Player)
		{
			result =  warpPlayerExecute(plugin.getPlayerManager().getWarpPlayer(sender.getName()), args, preArgs);
		}
		else
		{
			result = consoleExecute(Bukkit.getConsoleSender(), args, preArgs);
		}
		
		if(result == false)
		{
			sender.sendMessage(Coloring.NEGATIVE_PRIMARY + "Invalid usage!" + Coloring.POSITIVE_PRIMARY + " Correct usage: " + Coloring.USAGE + "/" + getUsage());
		}
		
		return result;
	}
	
	public boolean consoleExecute(ConsoleCommandSender sender, List<String> args, List<String> variables)
	{
		return Util.mustBePlayer(sender);
	}

	public abstract boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables);
	public abstract String getUsage();
}
