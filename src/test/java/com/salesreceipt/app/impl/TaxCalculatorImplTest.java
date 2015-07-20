package com.salesreceipt.app.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.salesreceipt.app.ShoppingCartItem;

/**
 * Tests for {@link TaxCalculatorImpl}.
 */
public class TaxCalculatorImplTest
{
	private TaxCalculatorImpl taxCalculatorImpl = new TaxCalculatorImpl();
	private List<ShoppingCartItem> mockShoppingCartItems;

	@Before
	public void setUp() throws Exception
	{
		mockShoppingCartItems = Arrays.asList(createMockCartItem(), createMockCartItem(), createMockCartItem());
	}

	private ShoppingCartItem createMockCartItem()
	{
		ShoppingCartItem mockShoppingCartItem = mock(ShoppingCartItem.class);
		when(mockShoppingCartItem.getPriceAfterTax()).thenReturn(12.5);
		when(mockShoppingCartItem.getSalesTax()).thenReturn(10.5);
		return mockShoppingCartItem;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddShoppingCartItem_NullShoppingCartItem()
	{
		taxCalculatorImpl.addShoppingCartItem(null);
	}

	@Test
	public void testAddShoppingCartItem()
	{
		taxCalculatorImpl.addShoppingCartItem(mockShoppingCartItems.get(0));
		taxCalculatorImpl.addShoppingCartItem(mockShoppingCartItems.get(1));

		assertThat(taxCalculatorImpl.getTotalSaleTax(), is(21.0));
		assertThat(taxCalculatorImpl.getTotalSalesPrice(), is(25.0));
	}

	@Test
	public void testRemoveShoppingCartItem()
	{
		taxCalculatorImpl.addShoppingCartItem(mockShoppingCartItems.get(0));
		taxCalculatorImpl.addShoppingCartItem(mockShoppingCartItems.get(1));

		taxCalculatorImpl.removeShoppingCartItem(mockShoppingCartItems.get(1));
		taxCalculatorImpl.removeShoppingCartItem(mockShoppingCartItems.get(2));
		
		assertThat(taxCalculatorImpl.getTotalSaleTax(), is(10.5));
		assertThat(taxCalculatorImpl.getTotalSalesPrice(), is(12.5));
	}
}
