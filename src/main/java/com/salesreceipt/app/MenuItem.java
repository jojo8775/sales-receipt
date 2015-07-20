package com.salesreceipt.app;

import java.util.Set;

/**
 * Represents an menu item such as books, medicine pills and others.
 */
public interface MenuItem
{
	/**
	 * Represents to which category of item it belongs.
	 * 
	 * @return a set of {@link ItemCategory} which cannot be <code>null</code>
	 *         or have <code>null</code> entry. Node: Modifying the returned
	 *         value wont change the internal state of this class.
	 */
	Set<ItemCategory> getCatagories();

	/**
	 * Represents the quantity.
	 * 
	 * @return the returned value cannot be zero or negative.
	 */
	int getQuantity();

	/**
	 * Represents the price.
	 * 
	 * @return the returned value cannot be negative.
	 */
	double getPrice();

	/**
	 * Represents the description of the item.
	 * 
	 * @return the returned value cannot be empty or <code>null</code>
	 */
	String getDescription();
}
