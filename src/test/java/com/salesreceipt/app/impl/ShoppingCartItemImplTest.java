package com.salesreceipt.app.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.salesreceipt.app.MenuItem;
import com.salesreceipt.app.TaxCatalog;

/**
 * Tests for {@link ShoppingCartItemImpl}.
 */
public class ShoppingCartItemImplTest
{
	private MenuItem mockMenuItem = mock(MenuItem.class);
	private TaxCatalog mockTaxCatalog = mock(TaxCatalog.class);

	@Before
	public void setUp() throws Exception
	{
		when(mockMenuItem.getQuantity()).thenReturn(2);
		when(mockMenuItem.getPrice()).thenReturn(15.0);
		when(mockMenuItem.getDescription()).thenReturn("test item");

		when(mockTaxCatalog.calculateSalesTax(mockMenuItem)).thenReturn(2.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShoppingCartItemImpl_NullMenuItem()
	{
		new ShoppingCartItemImpl(null, mockTaxCatalog);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShoppingCartItemImpl_NullTaxCatalog()
	{
		new ShoppingCartItemImpl(mockMenuItem, null);
	}

	@Test
	public void testGetPriceAfterTax()
	{
		assertThat(new ShoppingCartItemImpl(mockMenuItem, mockTaxCatalog).getPriceAfterTax(), is(34.2));
	}

	@Test
	public void testGetItemDescription()
	{
		assertThat(new ShoppingCartItemImpl(mockMenuItem, mockTaxCatalog).getItemDescription(), is("2 test item: 34.2"));
	}

	@Test
	public void testGetSalesTax()
	{
		assertThat(new ShoppingCartItemImpl(mockMenuItem, mockTaxCatalog).getSalesTax(), is(4.2));
	}
}
