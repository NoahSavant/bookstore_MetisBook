package com.metis.book.controller.admin;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.dto.AimForm;
import com.metis.book.service.IAimService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminHomeController {

	@Autowired
	IAimService aimService;
	
	@GetMapping
	public ModelAndView home() {
		Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        AimForm aimForm = new AimForm();
        aimForm.setYear(year);
        aimForm.setData(aimService.getAimInYear(year));
        String areaData = aimService.getSalesData(year);
		String pie1Data = aimService.getProductData(year);
		String pie2Data = aimService.getCustomerData(year);
		String cardData = aimService.getAimData(year);
		ModelAndView mav = new ModelAndView();
		mav.addObject("aimForm", aimForm);
		mav.addObject("areaData", areaData);
		mav.addObject("pie1Data", pie1Data);
		mav.addObject("pie2Data", pie2Data);
		mav.addObject("cardData", cardData);
		mav.setViewName("admin/index");
		return mav;
	}
	
	@PostMapping
	public ModelAndView post(@ModelAttribute("aimForm") AimForm aimForm, BindingResult result) {
        aimService.save(aimForm);
        aimForm.setData(aimService.getAimInYear(aimForm.getCustomAreaYear()));
        String areaData = aimService.getSalesData(aimForm.getCustomAreaYear());
		String pie1Data = aimService.getProductData(aimForm.getCustomAreaYear());
		String pie2Data = aimService.getCustomerData(aimForm.getCustomAreaYear());
		String cardData = aimService.getAimData(aimForm.getCustomAreaYear());
		ModelAndView mav = new ModelAndView();
		mav.addObject("aimForm", aimForm);
		mav.addObject("areaData", areaData);
		mav.addObject("pie1Data", pie1Data);
		mav.addObject("pie2Data", pie2Data);
		mav.addObject("cardData", cardData);
		mav.setViewName("admin/index");
		return mav;
	}
}
