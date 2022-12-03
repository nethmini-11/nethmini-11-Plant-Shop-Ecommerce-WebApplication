package com.ecom.store.store;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ecom.store.store.service.UserService;

@Component
public class StoreAppStartupRunner implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		userService.createUser("seller", "seller", "seller@seller.com", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));	
	}
}

