package com.discount.restws.controller;

import javax.ws.rs.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.discount.restws.model.Discount;
import com.discount.restws.services.DiscountService;

@RestController
public class DiscountController {

	@Autowired
	private DiscountService discountService;
	
	@Autowired
	private Environment env;

	@GetMapping(path = "/discountService/income/{income}/discount")
	public Discount getDiscount(@PathVariable double income) {
		if (income == 0.0)
			throw new BadRequestException("Purchase amount cannot be empty or 0.");
		return discountService.getDiscount(income, env);
	}

}