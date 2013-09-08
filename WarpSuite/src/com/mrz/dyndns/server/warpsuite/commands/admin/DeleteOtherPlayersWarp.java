package com.mrz.dyndns.server.warpsuite.commands.admin;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.commands.WarpSuiteCommand;
import com.mrz.dyndns.server.warpsuite.managers.WarpManager;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.players.OfflineWarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;

public class DeleteOtherPlayersWarp extends WarpSuiteCommand
{

	public DeleteOtherPlayersWarp(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		if(Permissions.ADMIN_REMOVE.check(player, true) == false)
		{
			return true;
		}
		
		return execute(player.getPlayer(), args, variables);
	}
	
	@Override
	public boolean consoleExecute(ConsoleCommandSender sender, List<String> args, List<String> variables)
	{
		return execute(sender, args, variables);
	}
	
	private boolean execute(CommandSender sender, List<String> args, List<String> variables)
	{
		if(args.size() == 0)
		{
			return false;
		}
		
		String targetName = variables.get(0);
		OfflineWarpSuitePlayer target = new OfflineWarpSuitePlayer(targetName, plugin);
		if(target.hasPlayedBefore() == false)
		{
			sender.sendMessage(NEGATIVE_PRIMARY + "Player \'" + NEGATIVE_SECONDARY + targetName + NEGATIVE_PRIMARY + "\' has not played on this server before!");
			return true;
		}
		
		String warpName = args.get(0);
		WarpManager manager = target.getWarpManager();
		if(manager.warpIsSet(warpName) == false)
		{
			sender.sendMessage(NEGATIVE_PRIMARY + "Player \'" + NEGATIVE_SECONDARY + targetName 
					+ NEGATIVE_PRIMARY + "\' does not have a warp called \'" + NEGATIVE_SECONDARY + warpName + NEGATIVE_PRIMARY + "\' set!");
			return true;
		}
		manager.removeWarp(warpName);
		sender.sendMessage(POSITIVE_PRIMARY + "Warp \'" + POSITIVE_SECONDARY + warpName + POSITIVE_PRIMARY + "\' has been removed from player \'" + POSITIVE_SECONDARY + targetName + POSITIVE_PRIMARY + "\'");
		return true;
	}

	@Override
	public String getUsage()
	{
		return "warp " + USAGE_ARGUMENT + "[playerName] " + USAGE + "delete|del|remove|clear " + USAGE_ARGUMENT + "[warpName]";
	}

}
