package com.metis.book.service;

import com.metis.book.model.Cart;

public interface ICartService {

	Cart getCartByUser(Long userId);

}
