package com.salesreceipt.app.impl.state.ca;

import java.util.HashSet;
import java.util.Set;

import com.salesreceipt.app.ItemCategory;
import com.salesreceipt.app.TaxCatalog;
import com.salesreceipt.app.TaxCatalogFactory;
import com.salesreceipt.app.impl.TaxCatalogCriteria;

/**
 * Implementation of {@link TaxCatalogFactory} to create {@link TaxCatalog}
 * configured to California tax.
 */
public class CaliforniaTaxCatalogFactory implements TaxCatalogFactory
{
	public TaxCatalog createTaxcatalog()
	{
		Set<ItemCategory> taxExemptedItems = new HashSet<ItemCategory>(3);
		taxExemptedItems.add(ItemCategory.BOOKS);
		taxExemptedItems.add(ItemCategory.MEDICAL_PRODUCTS);
		taxExemptedItems.add(ItemCategory.FOOD_PRODUCTS);

		TaxCatalogCriteria catalogCriteria = new TaxCatalogCriteria.Builder().baseSalesTax(10.0).importTax(5.0)
				.taxExemptedItems(taxExemptedItems).build();

		return new CaliforniaTaxCatalog(catalogCriteria);
	}
}
