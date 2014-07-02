package com.mrz.dyndns.server.warpsuite.commands.publicWarps;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.commands.GoPlayersWarp;
import com.mrz.dyndns.server.warpsuite.util.Config;

public class GoToPublicWarpIfApplicable extends GoPlayersWarp
{

	public GoToPublicWarpIfApplicable(WarpSuite plugin)
	{
		super(plugin);
	}

	@Override
	public boolean regardPublicWarps()
	{
		return Config.defaultToPublicWarp;
	}

}
