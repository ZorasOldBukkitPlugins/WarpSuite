package com.mrz.dyndns.server.warpsuite.permissions;

import org.bukkit.entity.Player;

public enum Permissions
{
	HELP("warpsuite.help"),
	WARP("warpsuite.warp");
	
	private Permissions(String node)
	{
		this.node = node;
	}
	
	private final String node;
	
	public boolean checkNode(Player p)
	{
		return p.hasPermission(node);
	}
}
