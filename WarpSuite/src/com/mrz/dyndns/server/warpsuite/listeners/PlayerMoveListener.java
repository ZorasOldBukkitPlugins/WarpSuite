package com.mrz.dyndns.server.warpsuite.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.util.Coloring;
import com.mrz.dyndns.server.warpsuite.util.Config;

public class PlayerMoveListener implements Listener
{
	public PlayerMoveListener(WarpSuite plugin)
	{
		this.plugin = plugin;
	}
	
	private final WarpSuite plugin;
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		if(Config.cancelOnMove)
		{
			Player player = event.getPlayer();
			if(plugin.getPendingWarpManager().isWaitingToTeleport(player.getUniqueId()))
			{
				player.sendMessage(Coloring.NEGATIVE_PRIMARY + "You moved! You will not be warped...");
				plugin.getPendingWarpManager().removePlayer(player.getUniqueId());
			}
		}
	}
}
