package com.revature.nooriz.projects.project0.car_dealership;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User {
	
	public Customer(String username, String password) {
		super(username, password);
	}


	private ArrayList<Car> ownedCars = new ArrayList<Car>();
	

	public ArrayList<Car> getOwnedCars() {
		return ownedCars;
	}
	public void setOwnedCars(ArrayList<Car> ownedCars) {
		this.ownedCars = ownedCars;
	}
	
	
	@Override
	public String toString() {
		return "Customer Name: "+ getUsername();
	}
	
	@Override
	public void printChoices() {
		System.out.println("Press: 1 to view cars, 2 to make an offer, "
				+ "3 to view owned cars, 4 to view remaining payments,"
				+ " 5 to make a payment, or 6 to cancel.");
	}
	
	
	
	public void choices(CarLot lot) {
		System.out.println("What would you like to do?");
		printChoices();
		
		Scanner reader = new Scanner(System.in);
		String choice = reader.nextLine();
		
		while (!choice.equals("6")) {
			switch (choice) {
			case "1": 
				viewCars(lot);
				System.out.println("Anything else?");
				printChoices();
				choice = reader.nextLine();
				break;
			case "2":
				makeOffer(lot);
				System.out.println("Anything else?");
				printChoices();
				choice = reader.nextLine();
				break;
			case "3":
				viewOwnedCars();
				System.out.println("Anything else?");
				printChoices();
				choice = reader.nextLine();
				break;
			case "4":
				viewRemainingPayments();
				System.out.println("Anything else?");
				printChoices();
				choice = reader.nextLine();
				break;
			case "5":
				makePayment();
				System.out.println("Anything else?");
				printChoices();
				choice = reader.nextLine();
				break;
			default:
				System.out.println("Error! Not a valid choice.");
				printChoices();
				choice = reader.nextLine();
				break;
			}
		}
		
	}
	
	
	
	public void viewCars(CarLot lot) {
		for (Car car : lot.getCarLot())
		System.out.println(car);
	}
	
	
	
	public void makeOffer (CarLot lot) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter the car ID.");
		String carId = reader.nextLine();
		
		for (Car car : lot.getCarLot()) {
			if (car.getCarID().equals(carId)) {
				System.out.println("How much are you offering?");
				Double offer = reader.nextDouble();
				car.getOffers().put(this, offer);
				return;
			}
		}
		System.out.println("Car not found.");
	}
	
	
	
	
	public void viewOwnedCars() {
		if (ownedCars.isEmpty()) {
			System.out.println("You have not purchased a car yet.");
		} else {
			System.out.println(ownedCars);
		}
		
	}
	
	public void viewRemainingPayments() {
		if (ownedCars.isEmpty()) {
			System.out.println("You have not purchased a car yet.");
			
		} else {
			for (Car car : ownedCars) {
			System.out.println(car + "Payments remaining: $" + car.getRemainingPayments());
			}
		}
		
	}
	
	public void makePayment() {
		if (ownedCars.isEmpty()) {
			System.out.println("You have not purchased a car yet.");
			
			
		} else {
			Scanner reader = new Scanner(System.in);
			System.out.println("Please enter the car ID.");
			String carID = reader.nextLine();
			
			for (Car car : ownedCars) {
				if (car.getCarID().equals(carID)) {
					System.out.println("How much would you like to pay?");
					double amount = reader.nextDouble();
					car.setPaymentsMade( car.getPaymentsMade() + amount );
					return;
				}
			}
			System.out.println("Car not found!");
		}
	}
	
	public void newCar() {
		
		for (Car car : ownedCars) {
			
			if (car.isNewSale()) {
				Scanner reader = new Scanner(System.in);
				System.out.println("Congratulations! Your offer for " +car+ " was accepted.");
				System.out.println("How many months would you like to pay it off in?");
				car.calculateMonthlyPayments(reader.nextDouble());
				System.out.println("Your monthly payments will be $" + car.getMonthlyPayments()
				+ " a month.");
				car.setNewSale(false);
			}
		}
			
	
	}
	

	
	

}
