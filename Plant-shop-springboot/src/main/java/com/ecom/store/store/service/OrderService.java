package com.ecom.store.store.service;

import java.util.List;

import com.ecom.store.store.domain.Order;
import com.ecom.store.store.domain.Payment;
import com.ecom.store.store.domain.Shipping;
import com.ecom.store.store.domain.ShoppingCart;
import com.ecom.store.store.domain.User;

public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payment payment, User user);
	
	List<Order> findByUser(User user);
	
	Order findOrderWithDetails(Long id);
}
