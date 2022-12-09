package constants;

public enum TrainStops {
	
	  NOLA("New Orleans"),
	  CHIC("Chicago"),
	  DC("Washington D.C.");

	
	public final String stops;
	
	TrainStops(String stops){
		this.stops = stops;
	}
	
}
