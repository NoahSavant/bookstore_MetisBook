package com.metis.book.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.dto.CheckoutForm;
import com.metis.book.model.Book;
import com.metis.book.model.Cart;
import com.metis.book.model.CartItem;
import com.metis.book.security.UserPrincipal;
import com.metis.book.service.ICartItemService;
import com.metis.book.service.ICartService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/cart")
@Slf4j
public class CartController {

	@Autowired
	private ICartService cartService;
	
	@Autowired
	private ICartItemService cartItemService;
	
	@GetMapping
	public ModelAndView viewCartPage(
			ModelAndView mav,
			HttpServletRequest request,
			HttpSession session) {

		checkError(request,session,mav);
		log.info("in cart page");
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		renderObject(mav,userPrincipal.getId());
		mav.setViewName("/client/cart");
		return mav;
	}
	
	private void checkError(HttpServletRequest request, HttpSession session, ModelAndView mav) {
		session = request.getSession();
		String error = "";
		String bookId = "";
		if(Objects.nonNull( session.getAttribute("error"))) {
			error =  session.getAttribute("error").toString();
			session.removeAttribute("error");
		}
		if(Objects.nonNull(session.getAttribute("error-bookId"))) {
			bookId = session.getAttribute("error-bookId").toString();
			session.removeAttribute("error-bookId");
		}
		if(Objects.nonNull(error) && error != "" && error.equals("true")) {
			mav.addObject("notChecked",true);
		}else if(Objects.nonNull(bookId) && bookId !="") {
			Book book = cartItemService.getItemById(Long.parseLong(bookId)).getBook();
			if(book.getAvailable() == false) {
				mav.addObject("notAvailable",book.getTitle());
			}else {
				mav.addObject("insufficient", book.getTitle());
				mav.addObject("inventory", book.getInventory().getQuantiy());
			}
		}
		
	}
	
	@DeleteMapping("/delete")
	@ResponseBody
	public String deleteCartItem(
			@RequestParam("itemId") String itemId) {		
		cartItemService.deleteById(Long.parseLong(itemId));
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
		
		// set cartNum on authenticated user
		Long newCartItemNum = Long.parseLong(userPrincipal.getCartItemNum())-1;
		userPrincipal.setCartItemNum(String.valueOf(newCartItemNum));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "success";

	}
	
	@PostMapping("/update")
	@ResponseBody
	public String updateCartItem(
			@RequestParam("itemId") String itemId,
			@RequestParam("currItemNum") String currItemNum) {
		cartItemService.updateItem(Long.parseLong(itemId),Integer.parseInt(currItemNum));
		return "success";
	}
	
	private void renderObject(ModelAndView mav, Long userId) {
		Cart cart = cartService.getCartByUser(userId);
		List<CartItem> cartItems = cart.getCartItems();
		mav.addObject("checkoutForm",new CheckoutForm());
		mav.addObject("cart",cart);
		mav.addObject("cartItems",cartItems);
	}
}
