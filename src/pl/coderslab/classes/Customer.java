package pl.coderslab.classes;

import java.time.LocalDate;

public class Customer extends Person {

	private LocalDate birthday;
	

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String surname, String address, int phoneNumber, LocalDate birthsday) {
		super(name, surname, address, phoneNumber);
		this.birthday = birthsday;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthsday) {
		this.birthday = birthsday;
	}

	@Override
	public String toString() {
		return getId() + ": " + getName() + " " + getSurname() + " " + getAddress() + " " + getPhoneNumber() + " "
				+ getBirthday() ;
	}
	
	

}
