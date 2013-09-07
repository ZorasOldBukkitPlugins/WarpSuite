package com.mrz.dyndns.server.warpsuite.commands.user;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

import java.util.List;

import org.bukkit.ChatColor;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.commands.WarpSuiteCommand;
import com.mrz.dyndns.server.warpsuite.permissions.Permissions;
import com.mrz.dyndns.server.warpsuite.util.Coloring;
import com.mrz.dyndns.server.warpsuite.util.Config;
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
		
		if(Config.useWarpListPages)
		{
			
			return true;
		}
		else
		{
			StringBuilder sb = new StringBuilder();
			sb.append(Coloring.POSITIVE_PRIMARY + "Warp List: ");
			for(int ii = 0; ii < warpList.size(); ii++)
			{
				sb.append(ChatColor.GRAY).append(warpList.get(ii)).append(Coloring.POSITIVE_PRIMARY).append(", ");
			}
			String warps = sb.substring(0, sb.length() - 2);
			player.sendMessage(warps);
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
