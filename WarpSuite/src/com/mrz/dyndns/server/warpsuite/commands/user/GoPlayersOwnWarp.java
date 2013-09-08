package com.mrz.dyndns.server.warpsuite.commands.user;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.commands.GoPlayersWarp;

public class GoPlayersOwnWarp extends GoPlayersWarp
{

	public GoPlayersOwnWarp(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean regardPublicWarps()
	{
		return false;
	}
}
