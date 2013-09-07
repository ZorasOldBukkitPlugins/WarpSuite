package com.mrz.dyndns.server.warpsuite;

import org.bukkit.plugin.java.JavaPlugin;

import com.mrz.dyndns.server.CommandSystem.CommandSystem;
import com.mrz.dyndns.server.warpsuite.commands.*;
import com.mrz.dyndns.server.warpsuite.listeners.EntityDamageByEntityListener;
import com.mrz.dyndns.server.warpsuite.listeners.PlayerMoveListener;
import com.mrz.dyndns.server.warpsuite.managers.PendingWarpManager;
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
	private boolean usingMultiverse;
	private PendingWarpManager pendingWarpManager;
	
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
		pendingWarpManager = new PendingWarpManager();
		
		getServer().getPluginManager().registerEvents(playerManager, this);
		
		cs.registerCommand("warp set|add", new SetPlayersOwnWarp(this));
		cs.registerCommand("warp", new GoPlayersOwnWarp(this));
		
		if(getServer().getPluginManager().getPlugin("Multiverse-Core") != null)
		{
			getLogger().info("Hooking into Multiverse");
			usingMultiverse = true;
		}
		else
		{
			usingMultiverse = false;
		}
		
		getServer().getScheduler().runTaskTimer(this, playerManager, 600L, 600L);//every 30 seconds
		
		getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
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
	
	public boolean isUsingMultiverse()
	{
		return usingMultiverse;
	}
	
	public PendingWarpManager getPendingWarpManager()
	{
		return pendingWarpManager;
	}
}
