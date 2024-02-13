
package com.metis.book.service;

import com.metis.book.model.CartItem;

public interface ICartItemService {

    void deleteById(Long itemId);

    void updateItem(Long itemId, int currItemNum);

    CartItem getItemById(long parseLong);

}