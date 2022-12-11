package com.metis.book.controller.admin;


import com.metis.book.dto.OrderShow;
import com.metis.book.model.Author;
import com.metis.book.model.Category;
import com.metis.book.model.Language;
import com.metis.book.model.order.OrderTrack;
import com.metis.book.model.user.User;
import com.metis.book.service.IOrderService;
import com.metis.book.service.IOrderTrackService;
import com.metis.book.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/admin/order")
@Slf4j
public class AdminOrderController {
    @Autowired
    IOrderService orderService;
    @Autowired
    IOrderTrackService orderTrackService;
    @GetMapping
    public ModelAndView orderView() {
        ModelAndView mav = new ModelAndView();
        List<OrderShow> orderShows = orderService.getOrderShows();
        mav.addObject("orderShows", orderShows);
        mav.setViewName("admin/order/order.html");
        return mav;
    }
    
    @GetMapping("/edit")
    public ModelAndView viewUpdateOrder(
            ModelAndView mav,
            @RequestParam("orderId") String orderId,
            @RequestParam("username") String username) {
        OrderShow orderShow = orderService.getOrderShowById(Long.parseLong(orderId));

        orderShow.setUsername(username);
        mav.addObject("orderShow", orderShow);
        List<OrderTrack> orderTracks = orderTrackService.getAllOrderTrack();
        mav.addObject("orderTracks", orderTracks);
        mav.setViewName("admin/order/formEditOrder.html");
        return mav;
    }
    @PostMapping("/edit")
    public ModelAndView updateOrder(
            ModelAndView mav,
            @ModelAttribute("orderShow") OrderShow orderShow, BindingResult result) throws ParseException, IOException {
        orderService.updateOrder(orderShow);
        mav.addObject("updateSucceed", true);
        mav.setViewName("redirect:/admin/order/");
        return mav;
    }
}
