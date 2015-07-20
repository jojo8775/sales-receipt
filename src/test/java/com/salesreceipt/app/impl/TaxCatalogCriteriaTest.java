package com.salesreceipt.app.impl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.salesreceipt.app.ItemCategory;
import com.salesreceipt.app.impl.TaxCatalogCriteria.Builder;

/**
 * Tests for {@link TaxCatalogCriteria}.
 */
public class TaxCatalogCriteriaTest
{
	private Set<ItemCategory> defaultItemCatagory;
	private double baseSalesTax = 10.0;
	private double importSalesTax = 15.0;
	private TaxCatalogCriteria.Builder builder;

	@Before
	public void setUp() throws Exception
	{
		defaultItemCatagory = new HashSet<ItemCategory>();
		defaultItemCatagory.add(ItemCategory.BOOKS);
		builder = new TaxCatalogCriteria.Builder().baseSalesTax(baseSalesTax).importTax(importSalesTax)
				.taxExemptedItems(defaultItemCatagory);
	}

	@Test
	public void testGetTaxExemptedItems()
	{
		assertThat(defaultItemCatagory, is(builder.build().getTaxExemptedItems()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetTaxExemptedItems_NullArgument()
	{
		builder.taxExemptedItems(null);
	}

	@Test
	public void testGetBaseSalesTax()
	{
		assertThat(baseSalesTax, is(builder.build().getBaseSalesTax()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetBaseSalesTax_NullArgument()
	{
		builder.baseSalesTax(-1.0);
	}

	@Test
	public void testGetImportSalesTax()
	{
		assertThat(importSalesTax, is(builder.build().getImportTax()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetImportSalesTax_NullArgument()
	{
		builder.importTax(-1.0);
	}

	@Test(expected = IllegalStateException.class)
	public void testRequiredAttributes_TaxExemptedItems_NotInitialized()
	{
		new TaxCatalogCriteria.Builder().baseSalesTax(baseSalesTax).importTax(importSalesTax).build();
	}

	@Test(expected = IllegalStateException.class)
	public void testRequiredAttributes_BaseSalesTax_NotInitialized()
	{
		new TaxCatalogCriteria.Builder().taxExemptedItems(defaultItemCatagory).importTax(importSalesTax).build();
	}

	@Test(expected = IllegalStateException.class)
	public void testRequiredAttributes_ImportsalesTax_NotInitialized()
	{
		new TaxCatalogCriteria.Builder().taxExemptedItems(defaultItemCatagory).baseSalesTax(importSalesTax).build();
	}
}
