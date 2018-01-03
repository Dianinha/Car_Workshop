package pl.coderslab.classes;

import java.time.LocalDate;

public class Vehicle {

	private int id;
	private String model;
	private String brand;
	private int productionYear;
	private String registrationNumber;
	private LocalDate nextReview;
	private Customer owner;
	
	

	public Vehicle() {
		super();
	}

	public Vehicle(String model, String brand, int productionYear, String registrationNumber, LocalDate nextReview) {
		super();
		this.id = 0;
		this.setModel(model).setBrand(brand).setProductionYear(productionYear).setRegistrationNumber(registrationNumber)
				.setNextReview(nextReview);
	}
	
	

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public Vehicle setModel(String model) {
		this.model = model;
		return this;
	}

	public String getBrand() {
		return brand;
	}

	public Vehicle setBrand(String brand) {
		this.brand = brand;
		return this;
	}

	public int getProductionYear() {
		return productionYear;
	}

	public Vehicle setProductionYear(int productionYear) {
		this.productionYear = productionYear;
		return this;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public Vehicle setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
		return this;
	}

	public LocalDate getNextReview() {
		return nextReview;
	}

	public Vehicle setNextReview(LocalDate nextReview) {
		this.nextReview = nextReview;
		return this;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", model=" + model + ", brand=" + brand + ", productionYear=" + productionYear
				+ ", registrationNumber=" + registrationNumber + ", nextReview=" + nextReview + "]";
	}

}
