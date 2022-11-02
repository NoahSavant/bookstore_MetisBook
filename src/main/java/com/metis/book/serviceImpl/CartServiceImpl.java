package com.metis.book.serviceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.model.Cart;
import com.metis.book.model.user.User;
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
	UserRepository userRepository;
	
	@Override
	public Cart getCartByUser(Long userId) {
		User user = userRepository.findById(userId).get();
		if(Objects.isNull(user)) {
			log.error(AppConstant.USER_NOT_FOUND+userId);
			return null;
		}
		Cart cart = cartReposiroty.findByUser(user);
		return cart;
	}

}
