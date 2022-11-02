package com.metis.book.service;

public interface ICartItemService {

	void deleteById(Long itemId);

	void updateItem(Long itemId, int currItemNum);

}
