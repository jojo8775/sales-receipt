package com.salesreceipt.app.impl;

import static com.salesreceipt.util.ArgumentChecker.rejectIfNegative;
import static com.salesreceipt.util.ArgumentChecker.rejectIfNullOrHasNullEntry;
import static com.salesreceipt.util.ArgumentChecker.roundToTwoDecimalPlace;
import static com.salesreceipt.util.ArgumentChecker.verifyIfGreaterThanZero;
import static com.salesreceipt.util.ArgumentChecker.verifyIfInitialized;

import java.util.HashSet;
import java.util.Set;

import com.salesreceipt.app.ItemCategory;
import com.salesreceipt.app.TaxCatalog;

/**
 * Represents required arguments to create {@link TaxCatalog}.
 */
public class TaxCatalogCriteria
{
	private final Set<ItemCategory> taxExemptedItems;
	private final double baseSalesTax;
	private final double importTax;

	private TaxCatalogCriteria(Builder builder)
	{
		taxExemptedItems = builder.taxExemptedItems;
		baseSalesTax = builder.baseSalesTax;
		importTax = builder.importTax;
	}

	/**
	 * @return the taxExemptedItems which cannot be null or have null entry.
	 *         Note: changing the returned value wont change the internal state
	 *         of this class.
	 */
	public Set<ItemCategory> getTaxExemptedItems()
	{
		return new HashSet<ItemCategory>(taxExemptedItems);
	}

	/**
	 * @return the baseSalesTax which cannot be negative.
	 */
	public double getBaseSalesTax()
	{
		return baseSalesTax;
	}

	/**
	 * @return the importTax which cannot be negative.
	 */
	public double getImportTax()
	{
		return importTax;
	}

	@Override
	public String toString()
	{
		StringBuilder builder2 = new StringBuilder();
		builder2.append("TaxCatalogCriteria [taxExemptedItems=").append(taxExemptedItems).append(", baseSalesTax=")
				.append(baseSalesTax).append(", importTax=").append(importTax).append("]");
		return builder2.toString();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(baseSalesTax);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(importTax);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((taxExemptedItems == null) ? 0 : taxExemptedItems.hashCode());
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
		TaxCatalogCriteria other = (TaxCatalogCriteria) obj;
		if (Double.doubleToLongBits(baseSalesTax) != Double.doubleToLongBits(other.baseSalesTax))
			return false;
		if (Double.doubleToLongBits(importTax) != Double.doubleToLongBits(other.importTax))
			return false;
		if (taxExemptedItems == null)
		{
			if (other.taxExemptedItems != null)
				return false;
		}
		else if (!taxExemptedItems.equals(other.taxExemptedItems))
			return false;
		return true;
	}

	/**
	 * Represents the builder class for {@link TaxCatalogCriteria}.
	 */
	public static class Builder
	{
		private Set<ItemCategory> taxExemptedItems;
		private double baseSalesTax;
		private double importTax;

		/**
		 * @param taxExemptedItems
		 *            represents collection of {@link ItemCategory} which are
		 *            tax exempted.
		 * @throws IllegalArgumentException
		 *             if {@code taxExemptedItems} is null or has null entry.
		 * @return the Builder which is used to create
		 *         {@link TaxCatalogCriteria}.
		 */
		public Builder taxExemptedItems(Set<ItemCategory> taxExemptedItems)
		{
			this.taxExemptedItems = new HashSet<ItemCategory>(rejectIfNullOrHasNullEntry(taxExemptedItems,
					"taxExemptedItems"));
			return this;
		}

		/**
		 * @param baseSalesTax
		 *            represents base sales tax rate which is used to create
		 *            {@link TaxCatalogCriteria}.
		 * @throws IllegalArgumentException
		 *             if {@code baseSalesTax} is negative
		 * @return the Builder which is used to create
		 *         {@link TaxCatalogCriteria}.
		 */
		public Builder baseSalesTax(double baseSalesTax)
		{
			this.baseSalesTax = roundToTwoDecimalPlace(rejectIfNegative(baseSalesTax, "baseSalesTax"));
			return this;
		}

		/**
		 * @param importTax
		 *            represents importTax sales tax rate which is used to
		 *            create {@link TaxCatalogCriteria}.
		 * @throws IllegalArgumentException
		 *             if {@code importTax} is negative
		 * @return the Builder which is used to create
		 *         {@link TaxCatalogCriteria}.
		 */
		public Builder importTax(double importTax)
		{
			this.importTax = roundToTwoDecimalPlace(rejectIfNegative(importTax, "importTax"));
			return this;
		}

		/**
		 * @return a new {@link TaxCatalogCriteria}.
		 * @throws IllegalStateException
		 *             if any of the following condition is satisfied.
		 *             <ul>
		 *             <li>{@code taxExemptedItems} is not initialized</li>
		 *             <li>{@code baseSalesTax} is not initialized</li>
		 *             <li>{@code importTax} is not initialized</li>
		 *             </ul>
		 */
		public TaxCatalogCriteria build()
		{
			verifyRequiredArguments();
			return new TaxCatalogCriteria(this);
		}

		private void verifyRequiredArguments()
		{
			verifyIfInitialized(taxExemptedItems, "taxExemptedItems");
			verifyIfGreaterThanZero(baseSalesTax, "baseSalesTax");
			verifyIfGreaterThanZero(importTax, "importTax");
		}
	}
}
