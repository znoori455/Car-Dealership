package com.revature.nooriz.projects.project0.car_dealership;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Car implements Serializable {

	private double price;
	private double paymentsMade;
	private double remainingPayments;
	private Customer owner;
	private String carID;
	private String makeModel;
	private String mileage;
	private String year;
	private Map<Customer, Double> offers = new HashMap<Customer, Double>();
	private boolean newSale = false;
	private double monthlyPayments;
	
	
	
	
	
	@Override
	public String toString() {
		return "[carID: " + carID + ", Make/Model: " + makeModel + ", Mileage: " + mileage + ", Year: " + year + "]";
	}
	
	
	public Car(String carID, String makeModel, String mileage, String year) {
		super();
		this.carID = carID;
		this.makeModel = makeModel;
		this.mileage = mileage;
		this.year = year;
	}
	public Car() {

	}
	
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
		remainingPayments = price;	
	}
	public double getPaymentsMade() {
		return paymentsMade;
	}
	public void setPaymentsMade(double paymentsMade) {
		this.paymentsMade = paymentsMade;
		remainingPayments -= paymentsMade;
	}
	public double getRemainingPayments() {
		return remainingPayments;
	}
	public void setRemainingPayments(double remainingPayments) {
		this.remainingPayments = remainingPayments;
	}
	public Customer getOwner() {
		return owner;
	}
	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	public String getMakeModel() {
		return makeModel;
	}
	public void setMakeModel(String makeModel) {
		this.makeModel = makeModel;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Map<Customer, Double> getOffers() {
		return offers;
	}
	public void setOffers(Map<Customer, Double> offers) {
		this.offers = offers;
	}
	public String getCarID() {
		return carID;
	}
	public void setCarID(String carID) {
		this.carID = carID;
	}
	public boolean isNewSale() {
		return newSale;
	}
	public void setNewSale(boolean newSale) {
		this.newSale = newSale;
	}
	public double getMonthlyPayments() {
		return monthlyPayments;
	}
	public void setMonthlyPayments(double monthlyPayments) {
		this.monthlyPayments = monthlyPayments;
	}
	
	
	
	
	public void calculateMonthlyPayments(double months) {
		monthlyPayments = price/months;
	}
	
	
	
	public void printOffers() {
		
		for (Map.Entry<Customer, Double> entry : offers.entrySet()) {
		    System.out.println(entry.getKey() + ", Offer: $" + entry.getValue());
		}
	}
	
	
	public String getNextCarID(CarLot lot) {
		int next = 0;
		for (Car car : lot.getCarLot())
			if (Integer.parseInt(car.getCarID()) > next) {
				next = Integer.parseInt(car.getCarID());
			}
		
		carID = String.valueOf(next + 1);
		return carID;
	}
	
	
	
	
}
