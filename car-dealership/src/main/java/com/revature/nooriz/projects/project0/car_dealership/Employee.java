package com.revature.nooriz.projects.project0.car_dealership;

import java.util.Collections;
import java.util.Scanner;

public class Employee extends User {
	
	public Employee(String username, String password) {
		super(username, password);
	}
	
	@Override
	public String toString() {
		return "Employee Name: "+ getUsername();
	}

	@Override
	public void printChoices() {
		System.out.println("Press: 1 to add a car, 2 to remove a car,"
				+ " 3 to manage offers, 4 to view all payments, or 5 to cancel.");
	}
	
	
	public void choices (CarLot lot, UserList users) {
		System.out.println("What would you like to do?");
		printChoices();
		
		Scanner reader = new Scanner(System.in);
		String choice = reader.nextLine();
		
		while (!choice.equals("5")) {
			switch (choice) {
			case "1": 
				lot = addCar(lot);
				System.out.println("Anything else?");
				printChoices();
				choice = reader.nextLine();
				break;
			case "2":
				lot = removeCar(lot);
				System.out.println("Anything else?");
				printChoices();
				choice = reader.nextLine();
				break;
			case "3":
				lot = manageOffers(lot, users);
				System.out.println("Anything else?");
				printChoices();
				choice = reader.nextLine();
				break;
			case "4":
				viewAllPayments(users);
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
	
	
	
	public CarLot addCar(CarLot lot) {
		Car car = new Car();

		Scanner reader = new Scanner(System.in);
		car.setCarID(car.getNextCarID(lot));
		System.out.println("Enter make and model.");
		car.setMakeModel(reader.nextLine());
		System.out.println("Enter mileage.");
		car.setMileage(reader.nextLine());
		System.out.println("Enter year.");
		car.setYear(reader.nextLine());
		
		
		lot.addCar(car);
		return lot;
	}
	
	
	
	public CarLot removeCar(CarLot lot) {
	
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter the car ID.");
		String id = reader.nextLine();
		for (Car car : lot.getCarLot()) {
			if (car.getCarID().equals(id)) {
				lot.removeCar(car);
				return lot;
			}
		}
		System.out.println("Car not found.");
		return lot;
	}
	
	
	
	public CarLot manageOffers (CarLot lot, UserList users) {
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter the car ID.");
		String carId = reader.nextLine();

		for (Car car : lot.getCarLot()) {
			if (car.getCarID().equals(carId)) {
				car.printOffers();
				System.out.println("Type a customer's name to accept an offer, or enter cancel");
				String customer = reader.nextLine();
				
				for (Customer cust : users.getCustomers()) {
					if (cust.getUsername().equalsIgnoreCase(customer)) {
						car.setOwner(cust);
						double price = car.getOffers().get(cust);
						System.out.println("Car sold to " + cust.getUsername()
								+ " for $" + car.getOffers().get(cust) + ".");
						car.getOffers().clear();
						car.setPrice(price);
						cust.getOwnedCars().add(car);
						lot.removeCar(car);
						car.setNewSale(true);
						return lot;
					}
				}
				System.out.println("Car not sold.");
				return lot;
			}
		}
		System.out.println("Car not found");
		return lot;
	}
	
	
	
	public void viewAllPayments (UserList users) {
		for (Customer cust : users.getCustomers()) {
			
			if (!cust.getOwnedCars().isEmpty()) {
				
				for (Car car : cust.getOwnedCars()) {
					System.out.println(cust + ", Car: " + car);
					System.out.println("		 Payments made: $" + car.getPaymentsMade()
					+ " Payments remaining: $" + car.getRemainingPayments());
				}
			}
		}
	}
	

}
