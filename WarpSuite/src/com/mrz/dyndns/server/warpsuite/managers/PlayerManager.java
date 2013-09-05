package com.mrz.dyndns.server.warpsuite.managers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.util.MyConfig;
import com.mrz.dyndns.server.warpsuite.util.Util;

public class PlayerManager implements Listener
{
	public PlayerManager(WarpSuite plugin)
	{
		this.plugin = plugin;
		
		String dir = plugin.getDataFolder().getAbsolutePath().toString() + "/players";
		new File(dir).mkdirs();
		
		warps = new HashMap<String, MyConfig>();
		
		for(Player player : plugin.getServer().getOnlinePlayers())
		{
			addPlayer(player);
		}
	}
	
	private final WarpSuite plugin;
	private final Map<String, MyConfig> warps;
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		addPlayer(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		removePlayer(event.getPlayer());
	}
	
	private void addPlayer(Player player)
	{
		warps.put(player.getName(), new MyConfig("players/" + player.getName(), plugin));
		Util.Debug("Added player " + player.getName());
	}
	
	private void removePlayer(Player player)
	{
		if(warps.containsKey(player.getName()))
		{
			warps.remove(player.getName());
			Util.Debug("Removed player " + player.getName());
		}
	}
}
