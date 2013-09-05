package com.mrz.dyndns.server.warpsuite;

import org.bukkit.plugin.java.JavaPlugin;

import com.mrz.dyndns.server.CommandSystem.CommandSystem;
import com.mrz.dyndns.server.warpsuite.managers.PlayerManager;
import com.mrz.dyndns.server.warpsuite.managers.WarpManager;
import com.mrz.dyndns.server.warpsuite.util.Config;
import com.mrz.dyndns.server.warpsuite.util.MyConfig;
import com.mrz.dyndns.server.warpsuite.util.Util;

public class WarpSuite extends JavaPlugin
{
	private CommandSystem cs;
	private PlayerManager playerManager;
	private WarpManager publicWarpManager;
	
	@Override
	public void onEnable()
	{
		Util.initialize(this);
		Util.setDebugging(true);
		
		cs = new CommandSystem(this);
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		Config.load(getConfig());
		
		playerManager = new PlayerManager(this);
		publicWarpManager = new WarpManager(new MyConfig("public", this));
		
		getServer().getPluginManager().registerEvents(playerManager, this);
	}
	
	@Override
	public void onDisable()
	{
		playerManager.clearPlayers();
		cs.close();
	}
	
	//you will never need it
	public WarpManager getPublicWarpManager()
	{
		return publicWarpManager;
	}
	
	public PlayerManager getPlayerManager()
	{
		return playerManager;
	}
}
