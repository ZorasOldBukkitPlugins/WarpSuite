package com.mrz.dyndns.server.warpsuite.permissions;

import org.bukkit.entity.Player;

public enum Permissions
{
	//user
	HELP			("warpsuite.help"),
	WARP			("warpsuite.warp"),
	WARP_SET		("warpsuite.warp.set"),
	WARP_REMOVE		("warpsuite.warp.remove"),
	WARP_LIST		("warpsuite.warp.list"),
	
	//admin
	ADMIN_WARP		("warpsuite.admin.warp"),
	ADMIN_SENDTO	("warpsuite.admin.sendto"),
	ADMIN_TOMY		("warpsuite.admin.tomy"),
	ADMIN_SET		("warpsuite.admin.set"),
	ADMIN_DELETE	("warpsuite.admin.delete"),
	ADMIN_LIST		("warpsuite.admin.list"),
	
	//public
	PUBLIC_WARP		("warpsuite.public.warp"),
	PUBLIC_SET		("warpsuite.public.set"),
	PUBLIC_DELETE	("warpsuite.public.delete"),
	PUBLIC_LIST		("warpsuite.public.list"),
	
	//misc
	WARP_INVITE		("warpsuite.warp.invite"),
	COUNT_INFINITE	("warpsuite.count.infinite"),
	DELAY_BYPASS	("warpsuite.delay.bypass");
	
	
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
