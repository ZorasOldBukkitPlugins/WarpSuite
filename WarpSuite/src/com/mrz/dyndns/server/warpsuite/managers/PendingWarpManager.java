package com.mrz.dyndns.server.warpsuite.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

public class PendingWarpManager
{
	public PendingWarpManager()
	{
		playersWaitingToTeleport = new HashMap<UUID, Integer>();
	}
	
	private final Map<UUID, Integer> playersWaitingToTeleport;
	
	public void addPlayer(UUID player, int id)
	{
		playersWaitingToTeleport.put(player, id);
	}
	
	public void removePlayer(UUID player)
	{
		Bukkit.getScheduler().cancelTask(playersWaitingToTeleport.get(player));
		playersWaitingToTeleport.remove(player);
	}
	
	public boolean isWaitingToTeleport(UUID player)
	{
		if(Bukkit.getPlayer(player) == null)
		{
			removePlayer(player);
			return false;
		}
		
		return playersWaitingToTeleport.containsKey(player);
	}
}
