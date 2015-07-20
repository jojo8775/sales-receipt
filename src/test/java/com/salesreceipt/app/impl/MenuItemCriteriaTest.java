package com.salesreceipt.app.impl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.salesreceipt.app.ItemCategory;

/**
 * Tests for {@link MenuItemCriteria}.
 */
public class MenuItemCriteriaTest
{
	private int quantity = 2;
	private double price = 10.2;
	private String description = "Test description";
	private Set<ItemCategory> itemCategory;
	private MenuItemCriteria.Builder builder;

	@Before
	public void setUp() throws Exception
	{
		itemCategory = new HashSet<ItemCategory>();
		itemCategory.add(ItemCategory.BOOKS);

		builder = new MenuItemCriteria.Builder().itemCatagories(itemCategory).description(description)
				.quantity(quantity).price(price);
	}

	@Test
	public void testGetItemCatagories()
	{
		assertThat(builder.build().getItemCatagories(), is(itemCategory));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetItemCatagories_NullValue()
	{
		builder.itemCatagories(null);
	}

	@Test
	public void testGetQuantity()
	{
		assertThat(builder.build().getQuantity(), is(quantity));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetQuantity_ZeroValue()
	{
		builder.quantity(0);
	}

	@Test
	public void testGetPrice()
	{
		assertThat(builder.build().getPrice(), is(price));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPrice_NegativeValue()
	{
		builder.price(-1);
	}

	@Test
	public void testGetDescription()
	{
		assertThat(builder.build().getDescription(), is(description));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetDescription_Empty()
	{
		builder.description("");
	}

	@Test(expected = IllegalStateException.class)
	public void testRequiredArgument_ItemCatagores_NotInitialized()
	{
		new MenuItemCriteria.Builder().description(description).quantity(quantity).price(price).build();
	}

	@Test(expected = IllegalStateException.class)
	public void testRequiredArgument_ItemDescription_NotInitialized()
	{
		new MenuItemCriteria.Builder().itemCatagories(itemCategory).quantity(quantity).price(price).build();
	}

	@Test(expected = IllegalStateException.class)
	public void testRequiredArgument_ItemQuantity_NotInitialized()
	{
		new MenuItemCriteria.Builder().itemCatagories(itemCategory).description(description).price(price).build();
	}
}
