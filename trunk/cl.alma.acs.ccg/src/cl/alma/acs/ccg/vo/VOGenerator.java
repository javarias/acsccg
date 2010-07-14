package cl.alma.acs.ccg.vo;

public class VOGenerator {

	private String model;
	private String profile;
	private String output;
	private static final String protocol = "file:/";

	public VOGenerator(String model, String profile, String output) {
		
		this.model = model;
		this.profile = profile;
		this.output = output;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
	public String getWellFormedModel() {
		return protocol+this.model;
	}
	
	public String getWellFormedProfile() {
		return protocol+this.profile;
	}
}
