package com.mrz.dyndns.server.warpsuite.permissions;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

public enum NumeralPermissions
{
	COUNT("warpsuite.count.");
	
	private NumeralPermissions(String node)
	{
		this.node = node;
	}
	
	private final String node;
	
	public int getAmount(Player p) throws NumberFormatException
	{
		for(PermissionAttachmentInfo perm : p.getEffectivePermissions())
		{
			if(perm.getPermission().startsWith(node))
			{
				String[] permParts = perm.getPermission().split("\\.");
				try {
					return Integer.parseInt(permParts[permParts.length - 1]);
				} 
				catch (NumberFormatException e)
				{
					throw e;
				}
			}
		}
		
		return -1;
	}
}
