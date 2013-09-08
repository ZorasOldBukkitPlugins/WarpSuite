package com.mrz.dyndns.server.warpsuite.commands.user;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

import java.util.List;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.commands.WarpSuiteCommand;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.players.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.util.Config;
import com.mrz.dyndns.server.warpsuite.util.ListPrinter;
import com.mrz.dyndns.server.warpsuite.util.Util;

public class ListPlayersOwnWarps extends WarpSuiteCommand
{

	public ListPlayersOwnWarps(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		if(Permissions.WARP_LIST.check(player) == false)
		{
			return Util.invalidPermissions(player);
		}
		
		List<String> warpList = player.getWarpManager().getWarpList();

		ListPrinter lp = new ListPrinter(warpList);
		
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
					player.sendMessage(NEGATIVE_PRIMARY + "\'" + NEGATIVE_SECONDARY + args.get(0) + NEGATIVE_PRIMARY + "\' is an invalid page number!");
					return false;
				}
			}
			
			List<String> subList = lp.getSubList(page);
			int amountOfPages = lp.getAmountOfPages();
			
			player.sendMessage(POSITIVE_PRIMARY + "--------------- Warp List (" + POSITIVE_SECONDARY + page + POSITIVE_PRIMARY + "/" + POSITIVE_SECONDARY + amountOfPages + POSITIVE_PRIMARY + ") --------------");
			for(int ii = 0; ii < subList.size(); ii++)
			{
				player.sendMessage(POSITIVE_SECONDARY + subList.get(ii));
			}
			
			return true;
		}
		else
		{
			player.sendMessage(lp.toString());
			return true;
		}
	}

	@Override
	public String getUsage()
	{
		if(Config.useWarpListPages)
		{
			return "warp list " + USAGE_ARGUMENT + "(page)";
		}
		else
		{
			return "warp list";
		}
	}
}
