package com.metis.book.service;

import java.util.List;

import com.metis.book.model.Cart;
import com.metis.book.model.user.User;

public interface ICartService {

    Cart getCartByUser(Long userId);

    void addToCart(User user, Long bookId, int quantity);

    int getStatus(List<String> cartItems);

}
