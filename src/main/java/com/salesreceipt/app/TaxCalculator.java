package com.salesreceipt.app;

/**
 * Represents a shopping cart with {@link ShoppingCartItem}, which are ready to
 * be purchased.
 */
public interface TaxCalculator
{
	/**
	 * Adds {@link ShoppingCartItem} to the shopping cart.
	 * 
	 * @throws IllegalArgumentException
	 *             if {@code item} is <code>null</code>.
	 */
	void addShoppingCartItem(ShoppingCartItem item);

	/**
	 * removed {@link ShoppingCartItem} from the shopping cart if exists.
	 * 
	 * @throws IllegalArgumentException
	 *             if {@code item} is <code>null</code>.
	 */
	void removeShoppingCartItem(ShoppingCartItem item);

	/**
	 * Displays all {@link ShoppingCartItem} information.
	 */
	void displayReciept();
}
