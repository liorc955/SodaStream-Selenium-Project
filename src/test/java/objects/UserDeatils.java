package objects;

public class UserDeatils {
	
	private String firstName;
	private String lastName;
	private String email;
	private String addressOne;
	private String addressTwo;
	private String company;
	private String state;
	private String country;
	private String city;
	private String zip;
	private String phone;
	private String password;
	private CreditCardInformation creditCard;
	
	public UserDeatils(String firstName, 
			String lastName, 
			String email, 
			String addressOne, 
			String addressTwo, 
			String company, 
			String state, 
			String country, 
			String city, 
			String zip, 
			String phone,
			String password, CreditCardInformation creditCard) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setAddressOne(addressOne);
		this.setAddressTwo(addressTwo);
		this.setCompany(company);
		this.setState(state);
		this.setCountry(country);
		this.setCity(city);
		this.setZip(zip);
		this.setPhone(phone);
		this.setPassword(password);
		this.setCreditCard(creditCard);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressOne() {
		return addressOne;
	}

	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}

	public String getAddressTwo() {
		return addressTwo;
	}

	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CreditCardInformation getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCardInformation creditCard) {
		this.creditCard = creditCard;
	}

}
