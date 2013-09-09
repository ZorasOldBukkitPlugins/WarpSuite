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
import com.mrz.dyndns.server.warpsuite.util.Config;
import com.mrz.dyndns.server.warpsuite.util.ListPrinter;

public class ListOtherPlayersWarps extends WarpSuiteCommand
{

	public ListOtherPlayersWarps(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		if(Permissions.ADMIN_LIST.check(player, true) == false)
		{
			return true;
		}
		
		return execute(player.getPlayer(), args, variables, 9);
	}
	
	@Override
	public boolean consoleExecute(ConsoleCommandSender sender, List<String> args, List<String> variables)
	{
		return execute(sender, args, variables, 25);
	}
	
	private boolean execute(CommandSender sender, List<String> args, List<String> variables, int listSize)
	{
		String targetName = variables.get(0);
		OfflineWarpSuitePlayer target = new OfflineWarpSuitePlayer(targetName, plugin);
		WarpManager manager = target.getWarpManager();
		if(manager == null)
		{
			sender.sendMessage(NEGATIVE_PRIMARY + "\'" + NEGATIVE_SECONDARY + targetName + NEGATIVE_PRIMARY + "\' has never played on this server before!");
			return true;
		}
		List<String> warpList = manager.getWarpList();
		if(warpList == null)
		{
			sender.sendMessage(POSITIVE_PRIMARY + "No warps to list.");
			return true;
		}
		ListPrinter lp = new ListPrinter(sender, warpList, plugin, false, false);
		lp.setListSize(listSize);
		
		if(Config.useWarpListPages)
		{
			int page;
			if(args.size() == 0)
			{
				page = 1;
			}
			else
			{
				try
				{
					page = Integer.parseInt(args.get(0));
				}
				catch (NumberFormatException e)
				{
					sender.sendMessage(NEGATIVE_PRIMARY + "\'" + NEGATIVE_SECONDARY + args.get(0) + NEGATIVE_PRIMARY + "\' is an invalid page number!");
					return false;
				}
			}
			
			List<String> subList = lp.getSubList(page);
			if(subList == null)
			{
				sender.sendMessage(NEGATIVE_PRIMARY + "Invalid page number! Your page number was either too high or too low");
				return true;
			}
			int amountOfPages = lp.getAmountOfPages();
			
			sender.sendMessage(POSITIVE_PRIMARY + "---------- " + POSITIVE_SECONDARY + targetName + (targetName.endsWith("s") ? "\'" : "\'s") + POSITIVE_PRIMARY + " warp List (" + POSITIVE_SECONDARY + page + POSITIVE_PRIMARY + "/" + POSITIVE_SECONDARY + amountOfPages + POSITIVE_PRIMARY + ") ---------");
			for(int ii = 0; ii < subList.size(); ii++)
			{
				sender.sendMessage(subList.get(ii));
			}
		}
		else
		{
			sender.sendMessage(lp.toString(PRIVATE_WARP));
		}
		
		return true;
	}

	@Override
	public String getUsage()
	{
		if(Config.useWarpListPages)
		{
			return "warp " + USAGE_ARGUMENT + "[playerName]" + USAGE + " list " + USAGE_ARGUMENT + "(page)";
		}
		else
		{
			return "warp " + USAGE_ARGUMENT + "[playerName]" + USAGE + " list";
		}
	}

}
