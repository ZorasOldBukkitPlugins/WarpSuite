package com.mrz.dyndns.server.warpsuite.commands;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.util.Config;
import com.mrz.dyndns.server.warpsuite.util.ListPrinter;

public class WarpHelp extends WarpSuiteCommand
{

	public WarpHelp(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		if(Permissions.HELP.check(player, true) == false)
		{
			return true;
		}
		
		return execute(player.getPlayer(), args, 9);
	}
	
	@Override
	public boolean consoleExecute(ConsoleCommandSender sender, List<String> args, List<String> variables)
	{
		return execute(sender, args, 25);
	}
	
	private boolean execute(CommandSender sender, List<String> args, int listSize)
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
		//TODO: test warp limits
		//TODO: force warp limits
		List<String> helpEntries = new ArrayList<String>();
		helpEntries.add(WARNING_PRIMARY + "Note either brackets or parentheses: " + USAGE_ARGUMENT + "[mandatory arguments] " + WARNING_PRIMARY + "or" + USAGE_ARGUMENT + " (optional arguments)");
		if(check(sender, Permissions.WARP))
		{
			helpEntries.add(USAGE + "/warp " + USAGE_ARGUMENT + "[warpName]" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Sends you to the specified warp");
		}
		if(check(sender, Permissions.WARP_SET))
		{
			helpEntries.add(USAGE + "/warp set " + USAGE_ARGUMENT + "[warpName]" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Sets or overwrites a warp");
		}
		if(check(sender, Permissions.WARP_REMOVE))
		{
			helpEntries.add(USAGE + "/warp remove " + USAGE_ARGUMENT + "[warpName]" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Removes a warp");
		}
		if(check(sender, Permissions.WARP_LIST))
		{
			helpEntries.add(USAGE + "/warp list" + (Config.useWarpListPages ? USAGE_ARGUMENT + " (page)": "") + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Lists your warps");
		}
		if(check(sender, Permissions.ADMIN_WARP))
		{
			helpEntries.add(USAGE + "/warp " + USAGE_ARGUMENT + "[playerName] [warpName]" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Sends you to a player\'s warp");
		}
		if(check(sender, Permissions.ADMIN_SENDTO))
		{
			helpEntries.add(USAGE + "/warp " + USAGE_ARGUMENT + "[playerName]" + USAGE + " to their" + USAGE_ARGUMENT + " [warpName]" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Sends a player to their warp");
		}
		if(check(sender, Permissions.ADMIN_TOMY))
		{
			helpEntries.add(USAGE + "/warp " + USAGE_ARGUMENT + "[playerName]" + USAGE + "to my" + USAGE_ARGUMENT + " [warpName]" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Sends a player to your warp");
		}
		if(check(sender, Permissions.ADMIN_SET))
		{
			helpEntries.add(USAGE + "/warp " + USAGE_ARGUMENT + "[playerName]" + USAGE + " set" + USAGE_ARGUMENT + " [warpName]" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Sets a warp for a player");
		}
		if(check(sender, Permissions.ADMIN_REMOVE))
		{
			helpEntries.add(USAGE + "/warp " + USAGE_ARGUMENT + "[playerName]" + USAGE + " remove" + USAGE_ARGUMENT + " [warpName]" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Removes a player\'s warp");
		}
		if(check(sender, Permissions.ADMIN_LIST))
		{
			helpEntries.add(USAGE + "/warp " + USAGE_ARGUMENT + "[playerName]" + USAGE + " list" + (Config.useWarpListPages ? USAGE_ARGUMENT + " (page)": "") + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Lists a player\'s warps");
		}
		if(check(sender, Permissions.PUBLIC_WARP))
		{
			helpEntries.add(USAGE + "/warp public " + USAGE_ARGUMENT + "[warpName]" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Sends you to a public warp");
		}
		if(check(sender, Permissions.PUBLIC_SET))
		{
			helpEntries.add(USAGE + "/warp set public " + USAGE_ARGUMENT + "[warpName]" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Sets a public warp");
		}
		if(check(sender, Permissions.PUBLIC_REMOVE))
		{
			helpEntries.add(USAGE + "/warp remove public " + USAGE_ARGUMENT + "[warpName]" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Removes a public warp");
		}
		if(check(sender, Permissions.PUBLIC_LIST))
		{
			helpEntries.add(USAGE + "/warp list" + (Config.useWarpListPages ? USAGE_ARGUMENT + " (page)": "") + " public" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Lists public warps");
		}
		if(check(sender, Permissions.PUBLIC_SENDTO))
		{
			helpEntries.add(USAGE + "/warp " + USAGE_ARGUMENT + "[playerName]" + USAGE + " to public" + USAGE_ARGUMENT + " [warpName]" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Sends a player to a public warp");
		}
		if(check(sender, Permissions.INVITE))
		{
			helpEntries.add(USAGE + "/warp invite " + USAGE_ARGUMENT + "[playerName]" + USAGE + " to " + USAGE_ARGUMENT + "[warpName]" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Invites a player to one of your warps");
		}
		if(check(sender, Permissions.INVITE_ACCEPT))
		{
			helpEntries.add(USAGE + "/warp accept" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Accepts a warp request");
		}
		if(check(sender, Permissions.INVITE_DENY))
		{
			helpEntries.add(USAGE + "/warp deny" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Denies a warp request");
		}
		if(check(sender, Permissions.RELOAD))
		{
			helpEntries.add(USAGE + "/warp reload" + USAGE_SEPERATOR + " - " + USAGE_DESCRIPTION + "Reloads the Warp Suite configuration");
		}
		
		ListPrinter lp = new ListPrinter(sender, helpEntries, plugin, false, false);
		lp.setListSize(listSize);
		
		List<String> subList = lp.getSubList(page);
		if(subList == null)
		{
			sender.sendMessage(NEGATIVE_PRIMARY + "Invalid page number! Your page number was either too high or too low");
			return true;
		}
		int amountOfPages = lp.getAmountOfPages();
		
		sender.sendMessage(POSITIVE_PRIMARY + "------------- Warp Help (" + POSITIVE_SECONDARY + page + POSITIVE_PRIMARY + "/" + POSITIVE_SECONDARY + amountOfPages + POSITIVE_PRIMARY + ") ------------");
		for(int ii = 0; ii < subList.size(); ii++)
		{
			sender.sendMessage(subList.get(ii));
		}
		
		return true;
	}
	
	private boolean check(CommandSender sender, Permissions perm)
	{
		return perm.check(sender, false);
	}

	@Override
	public String getUsage()
	{
		return "warp help " + USAGE_ARGUMENT + "(page)";
	}

}
