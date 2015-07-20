package com.salesreceipt.app;

import java.util.HashSet;
import java.util.Set;

import com.salesreceipt.app.impl.MenuItemCriteria;
import com.salesreceipt.app.impl.MenuItemImpl;
import com.salesreceipt.app.impl.ShoppingCartItemImpl;
import com.salesreceipt.app.impl.TaxCalculatorImpl;
import com.salesreceipt.app.impl.state.ca.CaliforniaTaxCatalogFactory;

public class SampleReceiptExecuter
{
	public static void main(String[] args)
	{
		Set<ItemCategory> bookItems = new HashSet<ItemCategory>();
		bookItems.add(ItemCategory.BOOKS);

		Set<ItemCategory> basicFoodItems = new HashSet<ItemCategory>();
		basicFoodItems.add(ItemCategory.FOOD_PRODUCTS);

		Set<ItemCategory> importedFoodItems = new HashSet<ItemCategory>();
		importedFoodItems.add(ItemCategory.FOOD_PRODUCTS);
		importedFoodItems.add(ItemCategory.IMPORTED_PRODUCTS);

		Set<ItemCategory> taxableItems = new HashSet<ItemCategory>();
		taxableItems.add(ItemCategory.OTHERS);

		TaxCatalog taxCatalog = new CaliforniaTaxCatalogFactory().createTaxcatalog();

		// input 1
		ShoppingCartItem cartItem1 = new ShoppingCartItemImpl(new MenuItemImpl(new MenuItemCriteria.Builder().price(12.49).description("Book").quantity(1)
				.itemCatagories(bookItems).build()), taxCatalog);
		ShoppingCartItem cartItem2 = new ShoppingCartItemImpl(new MenuItemImpl(new MenuItemCriteria.Builder().price(14.99).description("Music CD").quantity(1)
				.itemCatagories(taxableItems).build()), taxCatalog);
		ShoppingCartItem cartItem3 = new ShoppingCartItemImpl(new MenuItemImpl(new MenuItemCriteria.Builder().price(0.85).description("Chocolate Bar")
				.quantity(1).itemCatagories(basicFoodItems).build()), taxCatalog);
		
		TaxCalculator taxCalculator = new TaxCalculatorImpl();
		taxCalculator.addShoppingCartItem(cartItem1);
		taxCalculator.addShoppingCartItem(cartItem2);
		taxCalculator.addShoppingCartItem(cartItem3);
		taxCalculator.displayReciept();
	}
}
