package com.metis.book.dto;

import com.metis.book.model.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderShow {
    private Order order;
    private String username;
    private String orderDate;
}
