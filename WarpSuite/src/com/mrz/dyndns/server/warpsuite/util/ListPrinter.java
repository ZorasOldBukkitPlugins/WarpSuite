package com.mrz.dyndns.server.warpsuite.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.mrz.dyndns.server.warpsuite.WarpSuite;
import com.mrz.dyndns.server.warpsuite.managers.PublicWarpManager;

public class ListPrinter
{
	public ListPrinter(CommandSender sender, List<String> items, WarpSuite plugin)
	{
		this.items = new ArrayList<String>();
		for(int ii = 0; ii < this.items.size(); ii++)
		{
			this.items.add(Coloring.PRIVATE_WARP + items.get(ii));
		}
		PublicWarpManager manager = plugin.getPublicWarpManager();
		List<String> publicWarps = manager.getWarpList();
		for(int ii = 0; ii < publicWarps.size(); ii++)
		{
			String publicWarp = publicWarps.get(ii);
			if(manager.checkPlayer(sender, publicWarp))
			{
				this.items.add(Coloring.PUBLIC_WARP + publicWarp);
			}
		}
		listSize = 9;
	}
	
	private final List<String> items;
	
	private int listSize;
	private int pages = -1;
	
	public List<String> getSubList(int page)
	{
		double pagesRaw = 1.0;
		String[] itemArray = items.toArray(new String[items.size()]);
		int length = items.size();
		pagesRaw = length / listSize;
		int pagesRawRemaider = length % listSize;
		int pages = (int) pagesRaw;
		pages++;
		this.pages = pages;
		int currentPage = page;
		
		int numberToDisplay = listSize;
		if (currentPage > pagesRaw)
		{
			numberToDisplay = pagesRawRemaider;
		}
		
		List<String> subList = new ArrayList<String>();
		
		if(page < 0 || page > pages)
		{
			return null;
		}
		
		for(int ii = currentPage * listSize; ii < (currentPage * listSize ) + numberToDisplay; ii++)
		{
//				player.sendMessage(ChatColor.AQUA + itemArray[ii-listSize]);
				subList.add(itemArray[ii-listSize]);
		}
		
		return subList;
	
	}
	
	public int getAmountOfPages()
	{
		return pages;
	}
	
	public void setListSize(int listSize)
	{
		this.listSize = listSize;
	}
	
	public String toString(Coloring color)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(Coloring.POSITIVE_PRIMARY + "Warp List: ");
		for(int ii = 0; ii < items.size(); ii++)
		{
			sb.append(color.toString()).append(items.get(ii)).append(Coloring.POSITIVE_PRIMARY).append(", ");
		}
		String warps = sb.substring(0, sb.length() - 2);
		return warps;
	}
}
