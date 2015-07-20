package com.salesreceipt.app.impl;

import java.util.Set;

import com.salesreceipt.app.ItemCategory;
import com.salesreceipt.app.MenuItem;

/**
 * Implementation of {@link MenuItem}
 */
public class MenuItemImpl implements MenuItem
{
	private final MenuItemCriteria itemCriteria;

	public MenuItemImpl(MenuItemCriteria itemCriteria)
	{
		this.itemCriteria = itemCriteria;
	}

	public Set<ItemCategory> getCatagories()
	{
		return itemCriteria.getItemCatagories();
	}

	public int getQuantity()
	{
		return itemCriteria.getQuantity();
	}

	public double getPrice()
	{
		return itemCriteria.getPrice();
	}

	public String getDescription()
	{
		return itemCriteria.getDescription();
	}
}
