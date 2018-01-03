package pl.coderslab.classes;

public class Person {

	private int id;
	private String name;
	private String surname;
	private String address;
	private int phoneNumber;
	
	

	public Person() {
		super();
		this.id=0;
	}

	public Person(String name, String surname, String address, int phoneNumber) {
		super();
		this.id = 0;
		this.setName(name).setSurname(surname).setAddress(address).setPhoneNumber(phoneNumber);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Person setName(String name) {
		this.name = name;
		return this;
	}

	public String getSurname() {
		return surname;
	}

	public Person setSurname(String surname) {
		this.surname = surname;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Person setAddress(String address) {
		this.address = address;
		return this;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public Person setPhoneNumber(int phoneNumber) {
		if (phoneNumber>0 && phoneNumber<=999999999) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		else {
			this.phoneNumber = 0;
			return this;
		}
		
	}


}
