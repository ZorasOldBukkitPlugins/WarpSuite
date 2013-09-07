package com.mrz.dyndns.server.warpsuite.util;

import java.util.ArrayList;
import java.util.List;

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
//		player.sendMessage(ChatColor.YELLOW + "------------------" + ChatColor.GREEN + "Warps" + ChatColor.YELLOW + " - " 
//				+ ChatColor.LIGHT_PURPLE + " page " + currentPage + ChatColor.DARK_PURPLE + " of " + ChatColor.LIGHT_PURPLE + pages + ChatColor.YELLOW + "-----------------");
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
}
