package com.metis.book.controller.admin;

import com.metis.book.dto.BookForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
@Slf4j
public class AdminOrderController {
    @GetMapping
    public ModelAndView orderView() {
        ModelAndView mav = new ModelAndView();
//        List<BookForm> bookForms = bookService.getBookShows();
//        mav.addObject("books", bookForms);
        mav.setViewName("/admin/order.html");
        return mav;
    }
}
