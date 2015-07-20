package com.salesreceipt.app.impl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.salesreceipt.app.ItemCategory;

public class MenuItemImplTest
{
	private MenuItemCriteria mockMenuItemCriteria;
	private MenuItemImpl menuItemImpl;

	@Before
	public void setUp() throws Exception
	{
		Set<ItemCategory> itemCategories = new HashSet<ItemCategory>();
		itemCategories.add(ItemCategory.BOOKS);

		mockMenuItemCriteria = mock(MenuItemCriteria.class);

		when(mockMenuItemCriteria.getItemCatagories()).thenReturn(itemCategories);
		when(mockMenuItemCriteria.getQuantity()).thenReturn(2);
		when(mockMenuItemCriteria.getPrice()).thenReturn(20.1);
		when(mockMenuItemCriteria.getDescription()).thenReturn("test description");

		menuItemImpl = new MenuItemImpl(mockMenuItemCriteria);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMenuItemImpl_NullValue()
	{
		new MenuItemImpl(null);
	}

	@Test
	public void testGetCatagories()
	{
		assertThat(menuItemImpl.getCatagories(), is(mockMenuItemCriteria.getItemCatagories()));
	}

	@Test
	public void testGetQuantity()
	{
		assertThat(menuItemImpl.getQuantity(), is(mockMenuItemCriteria.getQuantity()));
	}

	@Test
	public void testGetPrice()
	{
		assertThat(menuItemImpl.getPrice(), is(mockMenuItemCriteria.getPrice()));
	}

	@Test
	public void testGetDescription()
	{
		assertThat(menuItemImpl.getDescription(), is(mockMenuItemCriteria.getDescription()));
	}
}
