package com.salesreceipt.app.impl.state.ca;

import static com.salesreceipt.util.ArgumentChecker.rejectIfNull;
import static com.salesreceipt.util.ArgumentChecker.roundToTwoDecimalPlace;

import java.util.Set;

import com.salesreceipt.app.ItemCategory;
import com.salesreceipt.app.MenuItem;
import com.salesreceipt.app.TaxCatalog;
import com.salesreceipt.app.impl.TaxCatalogCriteria;

public class CaliforniaTaxCatalog implements TaxCatalog
{
	private final TaxCatalogCriteria taxCatalogCriteria;
	private final Set<ItemCategory> taxExemptedItems;

	public CaliforniaTaxCatalog(TaxCatalogCriteria taxCatalogCriteria)
	{
		this.taxCatalogCriteria = taxCatalogCriteria;
		this.taxExemptedItems = taxCatalogCriteria.getTaxExemptedItems();
	}

	public double calculateSalesTax(MenuItem menuItem)
	{
		rejectIfNull(menuItem, "menuItem");
		return calculateImportTax(menuItem) + calculateBaseTax(menuItem);
	}

	private double calculateBaseTax(MenuItem menuItem)
	{
		for (ItemCategory singleItemCatagory : menuItem.getCatagories())
		{
			if (taxExemptedItems.contains(singleItemCatagory))
			{
				return 0;
			}
		}

		return roundToTwoDecimalPlace((menuItem.getPrice() * taxCatalogCriteria.getBaseSalesTax()) / 100);
	}

	private double calculateImportTax(MenuItem menuItem)
	{
		if (menuItem.getCatagories().contains(ItemCategory.IMPORTED_PRODUCTS))
		{
			return roundToTwoDecimalPlace((menuItem.getPrice() * taxCatalogCriteria.getImportTax()) / 100);
		}

		return 0;
	}
}
