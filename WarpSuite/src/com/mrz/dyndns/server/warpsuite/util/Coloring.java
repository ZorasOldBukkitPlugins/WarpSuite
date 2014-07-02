package com.mrz.dyndns.server.warpsuite.util;

import org.bukkit.ChatColor;

public enum Coloring
{
	POSITIVE_PRIMARY	(ChatColor.DARK_GREEN),
	POSITIVE_SECONDARY	(ChatColor.GREEN),
	NEGATIVE_PRIMARY	(ChatColor.RED),
	NEGATIVE_SECONDARY	(ChatColor.DARK_GRAY),
	WARNING_PRIMARY		(ChatColor.GOLD),
	WARNING_SECONDARY	(ChatColor.YELLOW),
	USAGE				(ChatColor.AQUA),
	USAGE_ARGUMENT		(ChatColor.LIGHT_PURPLE),
	USAGE_SEPERATOR		(ChatColor.DARK_RED),
	USAGE_DESCRIPTION	(ChatColor.GREEN),
	PUBLIC_WARP			(ChatColor.AQUA),
	PRIVATE_WARP		(ChatColor.GRAY);
	
	private Coloring(ChatColor color)
	{
		this.color = color;
	}
	
	private final ChatColor color;
	
	public ChatColor getColor()
	{
		return color;
	}
	
	@Override
	public String toString()
	{
		return color.toString();
	}
}
