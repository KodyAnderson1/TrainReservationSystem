package constants;

public enum TrainMiles {
	
	//all distances recorded in miles
		NOLA_JACK_STOP1(186.1), //first stop between Nola and Chicago, Jackson, MI
		JACK_MEMP_STOP(209.2), //second stop between Nola and Chicago, Jackson, MI to Memphis, TN
		MEMP_CHAMP_STOP(400.4), //third stop between Nola and Chicago, Memphis, TN to Champaign, IL
		CHAMP_CHIC(135.4), //destination arrival to Chicago from Champaign, IL
		NOLA_CHIC(931.1), //Nola to Chicago total miles
		CHIC_INDI_STOP1(183.2), //first stop between Chicago and DC, Indianapolis, IN
		INDI_CINN_STOP2(112.2), //second stop between Chicago and DC, Indianapolis, IN to Cincinnati, OH
		CINN_CTON_STOP3(197.9), //third stop between Chicago and DC, Cincinnati, OH to Charleston, WV
		CTON_CVIL_STOP4(248.3), //forth stop between Chicago and DC, Charleston, WV to Charlottesville, VA
		CVIL_DC_STOP5(116.6), //destination arrival to DC from Charlottesville, VA
		CHIC_DC(758.2), //Chicago to DC total miles
		NOLA_DC(1689.3), //Nola to DC total miles
		twoDay(155.34), //two day ticket distance
		threeDay(310.69), //three day ticket distance
		fourDay(621.37), //four day ticket distance
		sixDay(1242.74), //six day ticket distance
		MEMBERSHIP(49.99),
		INSURANCE(19.99),
		POINTS_CALC(10.0),
		DISCOUNT(0.10);
		
	public final double miles;
	
	TrainMiles(double miles){
		this.miles = miles;
	}

}