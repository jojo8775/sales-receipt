package com.salesreceipt.app.impl;

import static com.salesreceipt.util.ArgumentChecker.roundToTwoDecimalPlace;

import com.salesreceipt.app.MenuItem;
import com.salesreceipt.app.ShoppingCartItem;
import com.salesreceipt.app.TaxCatalog;

/**
 * Implementation of {@link ShoppingCartItem}.
 */
public class ShoppingCartItemImpl implements ShoppingCartItem
{
	private final MenuItem menuItem;
	private double priceAfterTax;
	private double salesTax;

	public ShoppingCartItemImpl(MenuItem menuItem, TaxCatalog taxCatalog)
	{
		this.menuItem = menuItem;
		salesTax = taxCatalog.calculateSalesTax(menuItem);
		priceAfterTax = roundToTwoDecimalPlace(menuItem.getPrice() + salesTax);
		priceAfterTax *= menuItem.getQuantity();
		salesTax *= menuItem.getQuantity();
	}

	public double getPriceAfterTax()
	{
		return priceAfterTax;
	}

	public String getItemDescription()
	{
		return menuItem.getQuantity() + " " + menuItem.getDescription() + ": " + priceAfterTax;
	}

	public double getSalesTax()
	{
		return salesTax;
	}
}
