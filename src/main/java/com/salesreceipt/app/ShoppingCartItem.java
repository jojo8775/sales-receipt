package com.salesreceipt.app;

/**
 * Represents item in a shopping cart ready to be purchased.
 */
public interface ShoppingCartItem
{
	/**
	 * @return the sales price. The returned value cannot be negative.
	 */
	double getPriceAfterTax();

	/**
	 * @return the sales tax. The returned value cannot be negative.
	 */
	double getSalesTax();

	/**
	 * @return the description of the item with sales price. The returned value
	 *         cannot be <code>null</code> or empty.
	 */
	String getItemDescription();
}
