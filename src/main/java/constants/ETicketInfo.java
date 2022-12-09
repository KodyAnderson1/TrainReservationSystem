package constants;

public enum ETicketInfo {
	baseTicketPrice(89.99),
	sleeperPercentageIncrease(.30),
	LuxuryPercentageIncrease(.65),
	pricePerMile(.10),
	ExpressPercentageIncrease(.15);
	
	public double price;
	
	ETicketInfo(double price) { this.price = price; }
	
	public void setPrice(double price) { this.price = price; }
}
