package com.mrz.dyndns.server.warpsuite;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.mrz.dyndns.server.warpsuite.managers.WarpManager;
import com.mrz.dyndns.server.warpsuite.util.Config;
import com.mrz.dyndns.server.warpsuite.util.MyConfig;
import com.mrz.dyndns.server.warpsuite.util.SimpleLocation;
import com.mrz.dyndns.server.warpsuite.util.Util;

public class WarpSuitePlayer
{
	private final String playerName;
	private final MyConfig config;
	private final WarpManager manager;
	
	private SimpleLocation warpRequest;
	private long timeWhenRequestWasMade = -1;
	
	public WarpSuitePlayer(String playerName, WarpSuite plugin)
	{
		this.playerName = playerName;
		config = new MyConfig("players/" + playerName, plugin);
		manager = new WarpManager(config);
	}
	
	public Player getPlayer()
	{
		return Bukkit.getPlayer(playerName);
	}
	
	public MyConfig getConfig()
	{
		return config;
	}
	
	public SimpleLocation getRequest()
	{
		if(tryTimeout())
		{
			return null;
		}
		else
		{
			return warpRequest;
		}
	}
	
	public void teleport(SimpleLocation sLoc)
	{
		getPlayer().teleport(sLoc.toLocation());
	}
	
	public WarpManager getWarpManager()
	{
		return manager;
	}
	
	public String getName()
	{
		return playerName;
	}
	
	/**
	 * Convenience method for sending messages
	 * @param message Message sent to the player
	 */
	public void sendMessage(String message)
	{
		getPlayer().sendMessage(message); 
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
		if(warpRequest == null || timeWhenRequestWasMade == -1)
		{
			return true;
		}
		
		long timeoutTime = Config.warpInviteTimeout;
		
		if (timeWhenRequestWasMade + timeoutTime > Util.getUnixTime())
		{
			timeWhenRequestWasMade = -1;
			warpRequest = null;
			return true;
		}
		else
		{
			return false;
		}
	}
}
