package kmerrill285.trewrite.config;

public class TerrariaModConfiguration {

	public boolean stacked_dimensions = false;
	
	public TerrariaModConfiguration(boolean stacked_dimensions) {
		this.stacked_dimensions = stacked_dimensions;
	}
	
	public boolean stacked_dimensions() {
		return this.stacked_dimensions;
	}
	
}
