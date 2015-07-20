package com.salesreceipt.app.impl.state.ca;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.salesreceipt.app.ItemCategory;
import com.salesreceipt.app.impl.TaxCatalogCriteria;

/**
 * Tests for {@link CaliforniaTaxCatalogFactory}.
 */
public class CaliforniaTaxCatalogFactoryTest
{
	@Test
	public void testCreateTaxcatalog()
	{
		Set<ItemCategory> taxExemptedItems = new HashSet<ItemCategory>(3);
		taxExemptedItems.add(ItemCategory.BOOKS);
		taxExemptedItems.add(ItemCategory.MEDICAL_PRODUCTS);
		taxExemptedItems.add(ItemCategory.FOOD_PRODUCTS);

		TaxCatalogCriteria catalogCriteria = new TaxCatalogCriteria.Builder().baseSalesTax(10.0).importTax(5.0)
				.taxExemptedItems(taxExemptedItems).build();

		CaliforniaTaxCatalog taxCatalog = new CaliforniaTaxCatalog(catalogCriteria);
		
		assertEquals(taxCatalog, new CaliforniaTaxCatalogFactory().createTaxcatalog());
	}
}
