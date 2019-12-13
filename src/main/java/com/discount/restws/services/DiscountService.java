package com.discount.restws.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.core.env.Environment;

import com.discount.restws.model.Discount;


@Consumes("application/xml,application/json")
@Produces("application/xml,application/json")
@Path("/discountServices")
public interface DiscountService {
	
	@GET
	@Path("/purchaseAmount/{amount}/discount")
	public Discount getDiscount(@PathParam("amount") Double amount, Environment environment);

}
