package com.mrz.dyndns.server.warpsuite.managers;

import com.mrz.dyndns.server.warpsuite.util.MyConfig;

public class WarpManager
{
	public WarpManager(MyConfig config)
	{
		this.config = config;
	}
	
	private final MyConfig config;
	
	public void save()
	{
		config.saveCustomConfig();
	}
	
	
}
