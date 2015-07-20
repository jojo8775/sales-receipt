package com.salesreceipt.app.impl.state.ca;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.salesreceipt.app.ItemCategory;
import com.salesreceipt.app.MenuItem;
import com.salesreceipt.app.impl.TaxCatalogCriteria;

public class CaliforniaTaxCatalogTest extends TestCase
{
	private TaxCatalogCriteria mockCatalogCriteria;
	private CaliforniaTaxCatalog californiaTaxCatalog;
	private MenuItem mockMenuItem;
	
	@Before
	public void setUp()
	{
		mockMenuItem = mock(MenuItem.class);
		
		Set<ItemCategory> taxExecptCatagory = new HashSet<ItemCategory>();
		taxExecptCatagory.add(ItemCategory.BOOKS);
		
		mockCatalogCriteria = mock(TaxCatalogCriteria.class);
		when(mockCatalogCriteria.getTaxExemptedItems()).thenReturn(taxExecptCatagory);
		
		californiaTaxCatalog = new CaliforniaTaxCatalog(mockCatalogCriteria);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCaliforniaTaxCatalog_NullCriteria()
	{
		new CaliforniaTaxCatalog(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculatsalesTax_NullCriteria()
	{
		new CaliforniaTaxCatalog(mockCatalogCriteria).calculateSalesTax(null);
	}
	
	@Test
	public void testCalculateSalesTax_WithoutBaseTaxButWithImportTax()
	{
		Set<ItemCategory> itemCatagoryWithImportTax = new HashSet<ItemCategory>();
		itemCatagoryWithImportTax.add(ItemCategory.BOOKS);
		itemCatagoryWithImportTax.add(ItemCategory.IMPORTED_PRODUCTS);
		
		when(mockMenuItem.getCatagories()).thenReturn(itemCatagoryWithImportTax);
		when(mockMenuItem.getPrice()).thenReturn(10.0);
		
		when(mockCatalogCriteria.getImportTax()).thenReturn(10.0);

		assertEquals(1.0, californiaTaxCatalog.calculateSalesTax(mockMenuItem));
	}
}
