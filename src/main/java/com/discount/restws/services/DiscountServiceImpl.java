package com.discount.restws.services;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.discount.restws.model.Discount;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Override
	public Discount getDiscount(Double purchaseAmount, Environment env) {
		// TODO Auto-generated method stub

		if (purchaseAmount == null || purchaseAmount == 0.0) {
			throw new BadRequestException("Purchase amount cannot be null.");
		}

		double amount = purchaseAmount.doubleValue();

		String slabs[] = env.getProperty("discount.slabs").split(";");
		String rates[] = env.getProperty("discount.rates").split(";");
		double discount = 0;
		double remainingIncome = amount;

		for (int i = 1; i < rates.length && remainingIncome > 0; i++) {

			double currentSlab = Double.parseDouble(slabs[i]);
			double lastSlab = Double.parseDouble(slabs[i - 1]);
			double currentRateOfDiscount = (Integer.parseInt(rates[i - 1]) * 0.01);
			double diff = Math.min(currentSlab - lastSlab, remainingIncome);

			// Core Logic
			discount += diff * currentRateOfDiscount;
			remainingIncome -= diff;
		}

		discount += remainingIncome * (Double.parseDouble(rates[rates.length - 1]) * 0.01);

		// Response Creation
		Discount finalDiscount = new Discount();
		finalDiscount.setPurchaseAmount(amount);
		finalDiscount.setDiscount(discount);
		finalDiscount.setBilledAmount(amount - discount);

		//return Response.ok(finalDiscount).build();
		return finalDiscount;
	}

}
