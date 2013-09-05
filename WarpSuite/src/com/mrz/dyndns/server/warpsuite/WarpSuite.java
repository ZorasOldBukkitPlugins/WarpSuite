package com.mrz.dyndns.server.warpsuite;

import org.bukkit.plugin.java.JavaPlugin;

import com.mrz.dyndns.server.CommandSystem.CommandSystem;
import com.mrz.dyndns.server.warpsuite.util.Config;
import com.mrz.dyndns.server.warpsuite.util.Util;

public class WarpSuite extends JavaPlugin
{
	private CommandSystem cs;
	
	@Override
	public void onEnable()
	{
		Util.initialize(this);
		Util.setDebugging(true);
		
		cs = new CommandSystem(this);
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		Config.load(getConfig());
		
		
	}
	
	@Override
	public void onDisable()
	{
		cs.close();
	}
}
