package com.mrz.dyndns.server.warpsuite;

import org.bukkit.plugin.java.JavaPlugin;

import com.mrz.dyndns.server.CommandSystem.CommandSystem;

public class WarpSuite extends JavaPlugin
{
	private CommandSystem cs;
	
	@Override
	public void onEnable()
	{
		cs = new CommandSystem(this);
	}
	
	@Override
	public void onDisable()
	{
		cs.close();
	}
}
