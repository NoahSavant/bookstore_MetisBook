package com.metis.book.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metis.book.model.CartItem;
import com.metis.book.repository.CartItemReposirory;
import com.metis.book.service.ICartItemService;
import com.metis.book.utils.AppConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartItemService implements ICartItemService {

    @Autowired
    CartItemReposirory cartItemReposirory;

    @Override
    public void deleteById(Long itemId) {
        cartItemReposirory.deleteById(itemId);

    }

    @Override
    public void updateItem(Long itemId, int currItemNum) {
        CartItem item = cartItemReposirory.findById(itemId).get();
        if (Objects.isNull(item)) {
            log.info(AppConstant.CART_ITEM_NOT_FOUND + itemId);
        }
        item.setQuantity(currItemNum);
        cartItemReposirory.save(item);
    }

    @Override
    public CartItem getItemById(long itemId) {
        CartItem item = cartItemReposirory.findById(itemId).get();
        if (Objects.isNull(item)) {
            log.info(AppConstant.CART_ITEM_NOT_FOUND + itemId);
        }
        return item;
    }

}