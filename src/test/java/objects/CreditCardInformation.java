package objects;

public class CreditCardInformation {
	
	private String cardNumber;
	private String nameOnCard;
	private String expirationDate;
	private int securityCode;
	
	public CreditCardInformation(String cardNumber, String nameOnCard, String exporationDate, int securityCode) {
		this.setCardNumber(cardNumber);
		this.setNameOnCard(nameOnCard);
		this.setExpirationDate(exporationDate);
		this.setSecurityCode(securityCode);
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber2) {
		this.cardNumber = cardNumber2;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

}
