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
import com.mrz.dyndns.server.warpsuite.WarpSuitePlayer;
import com.mrz.dyndns.server.warpsuite.permissions.NumeralPermissions;
import com.mrz.dyndns.server.warpsuite.util.Util;

public class PlayerManager implements Listener
{
	public PlayerManager(WarpSuite plugin)
	{
		this.plugin = plugin;
		
		String dir = plugin.getDataFolder().getAbsolutePath().toString() + "/players";
		new File(dir).mkdirs();
		
		players = new HashMap<String, WarpSuitePlayer>();
		
		for(Player player : plugin.getServer().getOnlinePlayers())
		{
			addPlayer(player.getName());
		}
	}
	
	private final WarpSuite plugin;
	private final Map<String, WarpSuitePlayer> players;
	
	public void clearPlayers()
	{
		List<String> wsPlayerNames = new ArrayList<String>(players.keySet());
		for(String wsPlayerName : wsPlayerNames)
		{
			removePlayer(wsPlayerName);
		}
		players.clear();
	}
	
	public WarpManager getWarpManager(String player)
	{
		return players.get(player).getWarpManager();
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
		players.put(player, new WarpSuitePlayer(player, plugin));
		Util.Debug("Added player " + player);
	}
	
	private void removePlayer(String player)
	{
		if(players.containsKey(player))
		{
			players.get(player).getConfig().saveCustomConfig();
			players.remove(player);
			Util.Debug("Removed player " + player);
		}
	}
}
