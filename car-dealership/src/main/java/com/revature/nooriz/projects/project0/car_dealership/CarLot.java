package com.revature.nooriz.projects.project0.car_dealership;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CarLot implements Serializable{
	
	private List<Car> carLot = new LinkedList<Car>();

	public List<Car> getCarLot() {
		return carLot;
	}
	public void setCarLot(LinkedList<Car> carLot) {
		this.carLot = carLot;
	}
	
	public void addCar(Car a) {
		carLot.add(a);
	}
	
	public void removeCar(Car a) {
		carLot.remove(a);
	}

}
