package com.metis.book.controller.admin;

import com.metis.book.dto.BookForm;
import com.metis.book.dto.OrderShow;
import com.metis.book.model.user.User;
import com.metis.book.service.IOrderService;
import com.metis.book.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
@Slf4j
public class AdminOrderController {
    @Autowired
    IOrderService orderService;
    @Autowired
    IUserService userService;
    @GetMapping
    public ModelAndView orderView() {
        ModelAndView mav = new ModelAndView();
        List<OrderShow> orderShows = orderService.getOrderShows();
        mav.addObject("orderShows", orderShows);
        mav.setViewName("/admin/order.html");
        return mav;
    }
}
