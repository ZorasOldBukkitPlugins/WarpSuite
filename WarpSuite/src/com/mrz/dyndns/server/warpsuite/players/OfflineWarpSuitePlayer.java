package com.mrz.dyndns.server.warpsuite.players;

import java.io.File;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.managers.WarpManager;
import com.mrz.dyndns.server.warpsuite.util.MyConfig;

public class OfflineWarpSuitePlayer
{
	public OfflineWarpSuitePlayer(String playerName, WarpSuite plugin)
	{
		this.playerName = playerName;
		this.plugin = plugin;
	}
	
	private final String playerName;
	private final WarpSuite plugin;
	
	public WarpManager getWarpManager()
	{
		String fileName = plugin.getDataFolder().getAbsolutePath() + System.getProperty("file.separator") + "players" + System.getProperty("file.separator") + playerName + ".yml";
		File file = new File(fileName);
		if(file.exists())
		{
			return new WarpManager(new MyConfig("players/" + playerName, plugin));
		}
		else
		{
			return null;
		}
	}
}