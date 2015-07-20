package com.salesreceipt.app;

/**
 * Calculates tax for each {@link ShoppingCartItem}.
 */
public interface TaxCatalog
{
	/**
	 * Calculates total tax for the given {@code item}.
	 * 
	 * @param item
	 *            represents a {@link ShoppingCartItem}
	 * @throws IllegalArgumentException
	 *             if {@code item} is <code>null</code>
	 * @return total sales tax. The returned value cannot be negative.
	 */
	double calculateSalesTax(MenuItem item);
}
