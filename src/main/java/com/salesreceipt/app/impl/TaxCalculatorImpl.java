package com.salesreceipt.app.impl;

import static com.salesreceipt.util.ArgumentChecker.rejectIfNull;
import static com.salesreceipt.util.ArgumentChecker.roundToTwoDecimalPlace;

import java.util.ArrayList;
import java.util.List;

import com.salesreceipt.app.ShoppingCartItem;
import com.salesreceipt.app.TaxCalculator;

/**
 * Implementation of {@link TaxCalculator}.
 */
public class TaxCalculatorImpl implements TaxCalculator
{
	private List<ShoppingCartItem> cartItems = new ArrayList<ShoppingCartItem>();
	private double totalPriceAfterTax = roundToTwoDecimalPlace(0);
	private double totalSalesTax = roundToTwoDecimalPlace(0);

	public void addShoppingCartItem(ShoppingCartItem singleItem)
	{
		cartItems.add(rejectIfNull(singleItem, "singleItem"));
		totalPriceAfterTax += singleItem.getPriceAfterTax();
		totalSalesTax += singleItem.getSalesTax();
	}

	public void removeShoppingCartItem(ShoppingCartItem singleItem)
	{
		if (cartItems.remove(rejectIfNull(singleItem, "singleItem")))
		{
			totalPriceAfterTax -= singleItem.getPriceAfterTax();
			totalSalesTax -= singleItem.getSalesTax();
		}
	}

	public void displayReciept()
	{
		for (ShoppingCartItem singleCartItem : cartItems)
		{
			System.out.println(singleCartItem.getItemDescription());
		}

		System.out.println("Sales Taxes: " + totalSalesTax);
		System.out.println("Total: " + totalPriceAfterTax);
	}

	/**
	 * this methods is intended to be used for junit test only
	 */
	double getTotalSalesPrice()
	{
		return totalPriceAfterTax;
	}

	/**
	 * this methods is intended to be used for junit test only
	 */
	double getTotalSaleTax()
	{
		return totalSalesTax;
	}
}
