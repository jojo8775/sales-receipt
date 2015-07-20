package com.salesreceipt.app.impl.state.ca;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
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

public class CaliforniaTaxCatalogTest
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
	public void testcaliforniaTaxCatalog_NullCriteria()
	{
		new CaliforniaTaxCatalog(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateSalesTax_NullCriteria()
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

		assertThat(1.0, is(californiaTaxCatalog.calculateSalesTax(mockMenuItem)));
	}

	@Test
	public void testCalculateSalesTax_WithoutBaseTaxAndWithoutImportTax()
	{
		Set<ItemCategory> itemCatagoryWithImportTax = new HashSet<ItemCategory>();
		itemCatagoryWithImportTax.add(ItemCategory.BOOKS);

		when(mockMenuItem.getCatagories()).thenReturn(itemCatagoryWithImportTax);
		when(mockMenuItem.getPrice()).thenReturn(10.0);

		when(mockCatalogCriteria.getImportTax()).thenReturn(10.0);

		assertThat(0.0, is(californiaTaxCatalog.calculateSalesTax(mockMenuItem)));
	}

	@Test
	public void testCalculateSalesTax_WithBaseTaxAndWithoutImportTax()
	{
		Set<ItemCategory> itemCatagoryWithImportTax = new HashSet<ItemCategory>();
		itemCatagoryWithImportTax.add(ItemCategory.OTHERS);

		when(mockMenuItem.getCatagories()).thenReturn(itemCatagoryWithImportTax);
		when(mockMenuItem.getPrice()).thenReturn(10.0);

		when(mockCatalogCriteria.getBaseSalesTax()).thenReturn(20.0);

		assertThat(2.0, is(californiaTaxCatalog.calculateSalesTax(mockMenuItem)));
	}

	@Test
	public void testCalculateSalesTax_WithBaseTaxAndImportTax()
	{
		Set<ItemCategory> itemCatagoryWithImportTax = new HashSet<ItemCategory>();
		itemCatagoryWithImportTax.add(ItemCategory.OTHERS);
		itemCatagoryWithImportTax.add(ItemCategory.IMPORTED_PRODUCTS);

		when(mockMenuItem.getCatagories()).thenReturn(itemCatagoryWithImportTax);
		when(mockMenuItem.getPrice()).thenReturn(10.0);

		when(mockCatalogCriteria.getBaseSalesTax()).thenReturn(20.0);
		when(mockCatalogCriteria.getImportTax()).thenReturn(10.0);

		assertThat(3.0, is(californiaTaxCatalog.calculateSalesTax(mockMenuItem)));
	}
}
