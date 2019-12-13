package com.discount.restws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Discount")
public class Discount {

	private double purchaseAmount;
	private double discount;
	private double billedAmount;

	public double getBilledAmount() {
		return billedAmount;
	}

	public void setBilledAmount(double billedAmount) {
		this.billedAmount = billedAmount;
	}

	public double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}
