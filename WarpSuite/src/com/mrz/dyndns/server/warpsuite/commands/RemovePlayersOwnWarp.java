package com.mrz.dyndns.server.warpsuite.commands;

import static com.mrz.dyndns.server.warpsuite.util.Coloring.*;

import java.util.List;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.WarpSuitePlayer;

public class RemovePlayersOwnWarp extends WarpSuiteCommand
{

	public RemovePlayersOwnWarp(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		return false;
	}

	@Override
	public String getUsage()
	{
		return "warp remove|del|delete|clear " + USAGE_ARGUMENT + "[warpName]";
	}

}
