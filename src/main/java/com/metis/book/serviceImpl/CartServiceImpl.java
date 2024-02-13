package com.metis.book.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.model.Book;
import com.metis.book.model.Cart;
import com.metis.book.model.CartItem;
import com.metis.book.model.user.User;
import com.metis.book.repository.BookRepository;
import com.metis.book.repository.CartItemReposirory;
import com.metis.book.repository.CartReposiroty;
import com.metis.book.repository.UserRepository;
import com.metis.book.service.ICartService;
import com.metis.book.utils.AppConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImpl implements ICartService {

    @Autowired
    CartReposiroty cartReposiroty;

    @Autowired
    CartItemReposirory cartItemReposirory;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public Cart getCartByUser(Long userId) {
        User user = userRepository.findById(userId).get();
        if (Objects.isNull(user)) {
            log.error(AppConstant.USER_NOT_FOUND + userId);
            return null;
        }
        Cart cart = cartReposiroty.findByUser(user);
        return cart;
    }

    @Override
    public void addToCart(User user, Long bookId, int quantity) {

        Cart cart = cartReposiroty.findByUser(user);

        if (Objects.isNull(cart)) {
            log.error(AppConstant.CART_NOT_FOUND + user.getId());
            return;
        }

        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem cartItem : cartItems) {
            if (bookId == cartItem.getBook().getId()) {
                // increase if already have cartItem with current book id
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItemReposirory.save(cartItem);
                return;
            }
        }

        // If user's cart not have current book with the id

        Book book = bookRepository.findById(bookId).get();
        if (Objects.isNull(book)) {
            log.error(AppConstant.BOOK_NOT_FOUND + bookId);
            return;
        }
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);
        cartItemReposirory.save(cartItem);
    }

    @Override
    public int getStatus(List<String> cartItems) {
        for (String id : cartItems) {
            CartItem cartItem = cartItemReposirory.findById(Long.parseLong(id)).get();
            if (cartItem.getQuantity() > cartItem.getBook().getInventory().getQuantiy()) {
                return Integer.parseInt(id);
            } else if (cartItem.getBook().getAvailable() == false) {
                return Integer.parseInt(id);
            }
        }
        return 0;
    }

}