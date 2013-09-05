package com.mrz.dyndns.server.warpsuite.managers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		
		playerWarpConfigs = new HashMap<String, WarpManager>();
		
		for(Player player : plugin.getServer().getOnlinePlayers())
		{
			addPlayer(player.getName());
		}
	}
	
	private final WarpSuite plugin;
	private final Map<String, WarpManager> playerWarpConfigs;
	
	public void clearPlayers()
	{
		List<String> players = new ArrayList<String>(playerWarpConfigs.keySet());
		for(String player : players)
		{
			removePlayer(player);
		}
		playerWarpConfigs.clear();
	}
	
	public WarpManager getWarpManager(String player)
	{
		return playerWarpConfigs.get(player);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		addPlayer(event.getPlayer().getName());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		removePlayer(event.getPlayer().getName());
	}
	
	private void addPlayer(String player)
	{
		playerWarpConfigs.put(player, new WarpManager(new MyConfig("players/" + player, plugin)));
		Util.Debug("Added player " + player);
	}
	
	private void removePlayer(String player)
	{
		if(playerWarpConfigs.containsKey(player))
		{
			playerWarpConfigs.get(player).save();
			playerWarpConfigs.remove(player);
			Util.Debug("Removed player " + player);
		}
	}
}
