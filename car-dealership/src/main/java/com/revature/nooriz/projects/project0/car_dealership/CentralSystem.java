package com.revature.nooriz.projects.project0.car_dealership;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class CentralSystem {
	
	final static Logger logger = Logger.getLogger(CentralSystem.class);
	
	public void start(CarLot lot, UserList users) {
		Scanner reader = new Scanner(System.in);
		String choice = "";
		
		while (!choice.equals("3")) {
			System.out.println("Welcome! Press 1 to login, 2 to create an account, "
					+ "or 3 to terminate application");
			choice = reader.nextLine();
			switch (choice) {
			
			case "1":
				login(lot, users);
				break;
				
			case "2": 
				accountRegister(users);
				break;
				
			case "3":
				save(lot, users);	
				break;
				
			default:
				System.out.println("Invalid choice.");
				break;
			}
		}		
		//save(lot, users);	
	}
	

	//Login: Case 1 for start
	public void login(CarLot lot, UserList users) {
		Scanner reader = new Scanner(System.in);
		String username, password, type;
		System.out.println("Please enter 1 to login as a customer, 2 for employee, "
				+ "or 3 to cancel.");
		type = reader.nextLine();
		
		
		while (!type.equals("3")) {
			

			switch (type) {
			case "1": //customer
				
				System.out.println("Please enter your username.");
				username = reader.nextLine();
				System.out.println("Please enter your password.");
				password = reader.nextLine();
				
				for (Customer cust : users.getCustomers()) {
					if (cust.getUsername().equals(username) && cust.getPassword().equals(password)) {
						cust.newCar();
						cust.choices(lot);
						return;
					}
				}
				System.out.println("Not a valid customer.");
				return;
				
			case "2": //employee
				System.out.println("Please enter your username.");
				username = reader.nextLine();
				System.out.println("Please enter your password.");
				password = reader.nextLine();
				
				for (Employee emp : users.getEmployees()) {
					if (emp.getUsername().equals(username) && emp.getPassword().equals(password)) {
						emp.choices(lot, users);
						return;
					}
				}
				System.out.println("Not a valid employee.");
				return;
				
			case "3": //cancel
				return;
				
			default: //wrong input
				System.out.println("Not a valid response. "
						+ "Please enter 1 to login as a customer, 2 for employee, or 3 to cancel.");
				type = reader.nextLine();
				break;
			}
		}
	}
	
	
	//Account registration: case 2 for start
	public void accountRegister(UserList users) {
		
		Scanner reader = new Scanner(System.in);
		String username, password, type;
		System.out.println("Please enter a username for your account.");
		username = reader.nextLine();
		System.out.println("Please enter a password for your account.");
		password = reader.nextLine();
		System.out.println("What type of account would you like to create?"); 
		System.out.println("Press 1 for customer, 2 for employee, or 3 to cancel.");
		type = reader.nextLine();
		
		while (!type.equals("3")) {
			
			switch (type) {
			
			case "1": //new customer account
				Customer cust = new Customer(username, password);
				users.addCustomer(cust);
				return;
				
			case "2": //new employee account
				System.out.println("Action must be approved by an employee.");
				if (approveNewEmployee(users)) {
					Employee emp = new Employee(username, password);
					users.addEmployee(emp);
					return;
				}
				return;
				
			default:
				System.out.println("Not a valid response. "
						+ "Press 1 for customer, 2 for employee, or 3 to cancel.");
				type = reader.nextLine();
				break;
			}
		}	
	}
	
	
	//called from accountRegister case 2
	public boolean approveNewEmployee(UserList users) {
		Scanner reader = new Scanner(System.in);
		String username, password;
		
		System.out.println("Please enter your username.");
		username = reader.nextLine();
		System.out.println("Please enter your password.");
		password = reader.nextLine();
		
		for (Employee emp : users.getEmployees()) {
			if (emp.getUsername().equals(username) && emp.getPassword().equals(password)) {
				return true;
			} 
		}
		
		return false;
	}
	
	
	
	public void save(CarLot lot, UserList users) {
		Dealership dealer = new Dealership(lot, users);

		try {
			FileOutputStream fos = new FileOutputStream("saved.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dealer);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
	         System.out.println("Serialization failed.");
		}
		
	}
	
	
	
	public void load() {
		Dealership carDealer;
		try {
	         FileInputStream fis = new FileInputStream("saved.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         carDealer = (Dealership) ois.readObject();
	         ois.close();
	         start(carDealer.getLot(), carDealer.getUsers());
	      } catch (FileNotFoundException e) {
		         e.printStackTrace();
		         logger.error("Deserialization failed. File not found.", e);
		         return;
		  } catch (IOException e) {
			  e.printStackTrace();
			  logger.error("Deserialization failed.", e);
			  return;
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	         logger.error("Deserialization failed. Class not found.", e);
	         return;
	      }
	}
	

}
