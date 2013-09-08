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
		String fileName = plugin.getDataFolder().getAbsolutePath() + "/players/" + playerName + ".yml";
		System.out.println("fileName: " + fileName);
		File file = new File(plugin.getDataFolder().getAbsolutePath() + "/players/" + playerName + ".yml");
		if(file.exists())
		{
			return new WarpManager(new MyConfig("players/" + playerName, plugin));
		}
		else
		{
			System.out.println("returning null");
			return null;
		}
	}
}
