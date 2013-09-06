package com.mrz.dyndns.server.warpsuite.util;

import org.bukkit.ChatColor;

public enum Coloring
{
	POSITIVE_PRIMARY(ChatColor.DARK_GREEN),
	POSITIVE_SECONDARY(ChatColor.GREEN),
	NEGATIVE_PRIMARY(ChatColor.RED),
	NEGATIVE_SECONDARY(ChatColor.DARK_GRAY);
	
	private Coloring(ChatColor color)
	{
		this.color = color;
	}
	
	private final ChatColor color;
	
	public ChatColor getColor()
	{
		return color;
	}
}
