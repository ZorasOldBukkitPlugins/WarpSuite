package com.mrz.dyndns.server.warpsuite.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.util.Coloring;
import com.mrz.dyndns.server.warpsuite.util.Config;

public class EntityDamageByEntityListener implements Listener
{
	public EntityDamageByEntityListener(WarpSuite plugin)
	{
		this.plugin = plugin;
	}
	
	private final WarpSuite plugin;
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
	{
		if(event.getEntityType().equals(EntityType.PLAYER))
		{
			String playerName = ((Player) event.getEntity()).getName();
			
			if(event.getDamager().getType().equals(EntityType.PLAYER))
			{
				//playerPVP
				if(Config.cancelOnPvp)
				{
					if(plugin.getPendingWarpManager().isWaitingToTeleport(playerName))
					{
						plugin.getPendingWarpManager().removePlayer(playerName);
						Player p = Bukkit.getPlayer(playerName);
						p.sendMessage(Coloring.NEGATIVE_PRIMARY + "You have been damaged by PVP! You will not be warped...");
						return;
					}
				}
			}
			else
			{
				//damage
				if(Config.cancelOnMobDamage)
				{
					if(plugin.getPendingWarpManager().isWaitingToTeleport(playerName))
					{
						plugin.getPendingWarpManager().removePlayer(playerName);
						Player p = Bukkit.getPlayer(playerName);
						p.sendMessage(Coloring.NEGATIVE_PRIMARY + "You have been damaged by a mob " + Coloring.NEGATIVE_SECONDARY + "a " + 
								event.getDamager().getType().toString().toLowerCase().replace("_", " ") + ", to be precise" + Coloring.NEGATIVE_PRIMARY + "! You will not be warped...");
						return;
					}
				}
			}
		}
	}
}
