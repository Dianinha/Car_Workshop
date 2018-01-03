package pl.coderslab.classes;

public class Employee extends Person {

	private String note;
	private double costPerHour;
	
	

	public Employee() {
		super();
	}

	public Employee(String name, String surname, String address, int phoneNumber, String note, double costPerHour) {
		super(name, surname, address, phoneNumber);
		this.setNote(note).setCostPerHour(costPerHour);
	}

	public String getNote() {
		return note;
	}

	public Employee setNote(String note) {
		this.note = note;
		return this;
	}

	public double getCostPerHour() {
		return costPerHour;
	}

	public Employee setCostPerHour(double costPerHour) {
		this.costPerHour = costPerHour;
		return this;
	}

	@Override
	public String toString() {
		return getId() + ": " + getName() + " " + getSurname() + " " + getAddress() + " " + getPhoneNumber() + " "
				+ getNote() + " " + getCostPerHour();
	}

}
