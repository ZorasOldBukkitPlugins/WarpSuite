package com.mrz.dyndns.server.warpsuite;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.mrz.dyndns.server.warpsuite.util.Config;
import com.mrz.dyndns.server.warpsuite.util.MyConfig;
import com.mrz.dyndns.server.warpsuite.util.SimpleLocation;
import com.mrz.dyndns.server.warpsuite.util.Util;

public class WarpSuitePlayer
{
	private final String player;
	private final MyConfig config;
	
	private SimpleLocation warpRequest;
	private long timeWhenRequestWasMade;
	
	public WarpSuitePlayer(String player, WarpSuite plugin)
	{
		this.player = player;
		config = new MyConfig("players/" + player, plugin);
	}
	
	public Player toPlayer()
	{
		return Bukkit.getPlayer(player);
	}
	
	public MyConfig getConfig()
	{
		return config;
	}
	
	/**
	 * 
	 * @param sLoc request location
	 * @return false if request coudln't be made because one is already pending
	 */
	public boolean sendRequest(SimpleLocation sLoc)
	{
		if(tryTimeout())
		{
			timeWhenRequestWasMade = Util.getUnixTime();
			warpRequest = sLoc;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 
	 * @return true if there are no pending warp requests, or the request has timed out
	 */
	private boolean tryTimeout()
	{
		if(warpRequest == null || timeWhenRequestWasMade == (Long) null)
		{
			return true;
		}
		
		long timeoutTime = Config.warpInviteTimeout;
		
		return (timeWhenRequestWasMade + timeoutTime > Util.getUnixTime());
	}
}
