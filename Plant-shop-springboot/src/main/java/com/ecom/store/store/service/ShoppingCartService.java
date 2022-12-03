package com.ecom.store.store.service;

import com.ecom.store.store.domain.Plant;
import com.ecom.store.store.domain.CartItem;
import com.ecom.store.store.domain.ShoppingCart;
import com.ecom.store.store.domain.User;


public interface ShoppingCartService {

	ShoppingCart getShoppingCart(User user);
	
	int getItemsNumber(User user);
	
	CartItem findCartItemById(Long cartItemId);
	
	CartItem addPlantToShoppingCart(Plant plant, User user, int size, String Size);
		
	void clearShoppingCart(User user);
	
	void updateCartItem(CartItem cartItem, Integer size);

	void removeCartItem(CartItem cartItem);
	
}
