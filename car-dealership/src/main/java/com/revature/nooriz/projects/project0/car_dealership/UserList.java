package com.revature.nooriz.projects.project0.car_dealership;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserList implements Serializable {
	
	private List<Customer> customers = new ArrayList<>();
	private List<Employee> employees = new ArrayList<>();

	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}
	
	
	public void addCustomer(Customer cust) {
		customers.add(cust);
	}
	
	
	public void addEmployee(Employee emp) {
		employees.add(emp);
	}
	

}
