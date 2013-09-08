package com.mrz.dyndns.server.warpsuite.commands.publicWarps;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.commands.WarpSuiteCommand;
import com.mrz.dyndns.server.warpsuite.managers.PublicWarpManager;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.util.SimpleLocation;

public class SendPlayerToPublicWarp extends WarpSuiteCommand
{

	public SendPlayerToPublicWarp(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		if(Permissions.PUBLIC_SENDTO.check(player, true))
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
		WarpSuitePlayer target = plugin.getPlayerManager().getWarpPlayer(targetName);
		if(target == null)
		{
			sender.sendMessage(NEGATIVE_PRIMARY + "\'" + NEGATIVE_SECONDARY + targetName + NEGATIVE_PRIMARY + "\' is not online!");
			return true;
		}
		
		String warpName = args.get(0);
		PublicWarpManager manager = plugin.getPublicWarpManager();
		if(manager.warpIsSet(warpName) == false)
		{
			sender.sendMessage(NEGATIVE_PRIMARY + "There is no public warp called \'" + NEGATIVE_SECONDARY + warpName + NEGATIVE_PRIMARY + "\' set!");
			return true;
		}
		
		SimpleLocation sLoc = manager.loadWarp(warpName);
		return target.warpTo(sLoc, true);
	}

	@Override
	public String getUsage()
	{
		return "warp " + USAGE_ARGUMENT + "[playerName] " + USAGE + "to public " + USAGE_ARGUMENT + "[warpName]";
	}

}
