package com.mrz.dyndns.server.warpsuite.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class ListPrinter
{
	public ListPrinter(List<String> items)
	{
		this.items = items;
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
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(Coloring.POSITIVE_PRIMARY + "Warp List: ");
		for(int ii = 0; ii < items.size(); ii++)
		{
			sb.append(ChatColor.GRAY).append(items.get(ii)).append(Coloring.POSITIVE_PRIMARY).append(", ");
		}
		String warps = sb.substring(0, sb.length() - 2);
		return warps;
	}
}
