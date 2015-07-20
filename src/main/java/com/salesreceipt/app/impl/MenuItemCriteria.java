package com.salesreceipt.app.impl;

import static com.salesreceipt.util.ArgumentChecker.rejectIfNullOrEmpty;
import static com.salesreceipt.util.ArgumentChecker.rejectIfNullOrHasNullEntry;
import static com.salesreceipt.util.ArgumentChecker.rejectIfZeroOrNegative;
import static com.salesreceipt.util.ArgumentChecker.roundToTwoDecimalPlace;
import static com.salesreceipt.util.ArgumentChecker.verifyIfGreaterThanZero;
import static com.salesreceipt.util.ArgumentChecker.verifyIfInitialized;

import java.util.HashSet;
import java.util.Set;

import com.salesreceipt.app.ItemCategory;
import com.salesreceipt.app.MenuItem;

/**
 * Represents the required attributes for constructing {@link MenuItem}.
 */
public class MenuItemCriteria
{
	private final Set<ItemCategory> itemCatagories;
	private final int quantity;
	private final double price;
	private final String description;

	private MenuItemCriteria(Builder builder)
	{
		itemCatagories = builder.itemCatagories;
		quantity = builder.quantity;
		price = builder.price;
		description = builder.description;
	}

	/**
	 * @return the itemCatagories which cannot be null or empty. Node: changing
	 *         the returned value will not change the internal state of this
	 *         class.
	 */
	public Set<ItemCategory> getItemCatagories()
	{
		return new HashSet<ItemCategory>(itemCatagories);
	}

	/**
	 * @return the quantity which cannot be zero or negative.
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * @return the price which cannot be negative.
	 */
	public double getPrice()
	{
		return price;
	}

	/**
	 * @return the description which cannot be empty or <code>null</code>
	 */
	public String getDescription()
	{
		return description;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((itemCatagories == null) ? 0 : itemCatagories.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItemCriteria other = (MenuItemCriteria) obj;
		if (description == null)
		{
			if (other.description != null)
				return false;
		}
		else if (!description.equals(other.description))
			return false;
		if (itemCatagories == null)
		{
			if (other.itemCatagories != null)
				return false;
		}
		else if (!itemCatagories.equals(other.itemCatagories))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder2 = new StringBuilder();
		builder2.append("MenuItemCriteria [itemCatagories=").append(itemCatagories).append(", quantity=")
				.append(quantity).append(", price=").append(price).append(", description=").append(description)
				.append("]");
		return builder2.toString();
	}

	/**
	 * Builder class to create {@link MenuItemCriteria}.
	 */
	public static class Builder
	{
		private Set<ItemCategory> itemCatagories;
		private int quantity;
		private double price;
		private String description;

		/**
		 * @param itemCatagories
		 *            represents the collection of {@link ItemCategory} which is
		 *            used to construct {@link MenuItemCriteria}.
		 * @throws IllegalArgumentException
		 *             if {@code itemCatagories} is null or has null entry.
		 * @return Builder class to construct {@link MenuItemCriteria}
		 */
		public Builder itemCatagories(Set<ItemCategory> itemCatagories)
		{
			this.itemCatagories = new HashSet<ItemCategory>(
					rejectIfNullOrHasNullEntry(itemCatagories, "itemCatagories"));
			return this;
		}

		/**
		 * @param quantity
		 *            represents quantity which is used to construct
		 *            {@link MenuItemCriteria}.
		 * @throws IllegalArgumentException
		 *             if {@code quantity} is negative or zero
		 * @return Builder class to construct {@link MenuItemCriteria}
		 */
		public Builder quantity(int quantity)
		{
			this.quantity = rejectIfZeroOrNegative(quantity, "quantity");
			return this;
		}

		/**
		 * @param price
		 *            represents price which is used to construct
		 *            {@link MenuItemCriteria}.
		 * @throws IllegalArgumentException
		 *             if {@code price} is negative or zero
		 * @return Builder class to construct {@link MenuItemCriteria}
		 */
		public Builder price(double price)
		{
			this.price = roundToTwoDecimalPlace(rejectIfZeroOrNegative(price, "price"));
			return this;
		}

		/**
		 * @param description
		 *            represents description which is used to construct
		 *            {@link MenuItemCriteria}.
		 * @throws IllegalArgumentException
		 *             if {@code description} is empty or null
		 * @return Builder class to construct {@link MenuItemCriteria}
		 */
		public Builder description(String description)
		{
			this.description = rejectIfNullOrEmpty(description, "description");
			return this;
		}

		/**
		 * @return a new {@link MenuItemCriteria}.
		 * 
		 * @throws IllegalStateException
		 *             if any of the following condition is satisfied.
		 *             <ul>
		 *             <li>{@code itemCatagories} is not initialized</li>
		 *             <li>{@code description} is not initialized</li>
		 *             <li>{@code quantity} is not initialized</li>
		 *             <li>{@code price} is not initialized</li>
		 *             </ul>
		 */
		public MenuItemCriteria build()
		{
			verifyRequiredArguments();
			return new MenuItemCriteria(this);
		}

		private void verifyRequiredArguments()
		{
			verifyIfInitialized(itemCatagories, "itemCatagories");
			verifyIfInitialized(description, "description");
			verifyIfGreaterThanZero(quantity, "quantity");
			verifyIfGreaterThanZero(price, "price");
		}
	}
}
