package com.mrz.dyndns.server.warpsuite.commands;

import java.util.List;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.WarpSuitePlayer;

public class SetPlayersOwnWarp extends WarpSuiteCommand
{
	//warp set|add [warpname]
	public SetPlayersOwnWarp(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean warpPlayerExecute(WarpSuitePlayer player, List<String> args, List<String> variables)
	{
		
		return false;
	}
}
