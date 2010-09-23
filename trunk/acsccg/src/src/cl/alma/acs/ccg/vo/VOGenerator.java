package cl.alma.acs.ccg.vo;

/**
 * Value Object for the model and profile
 * @author atejeda
 */
public class VOGenerator 
{

	private String model;
	private String profile;
	private String output;
	private String acspackage;
	
	private static final String protocol = "file:/";

	/**
	 * Constructor
	 * @param model
	 * @param profile
	 * @param output
	 */
	public VOGenerator(String model, String profile, String output, String acspacakge) 
	{
		this.model = model;
		this.profile = profile;
		this.output = output;
		this.acspackage = acspacakge;
	}
	
	/**
	 * Constructor
	 * @param model
	 * @param profile
	 * @param output
	 */
	public VOGenerator(String model, String profile, String output) 
	{
		this.model = model;
		this.profile = profile;
		this.output = output;
	}

	/**
	 * Return the model path
	 * @return String
	 */
	public String getModel() 
	{
		return model;
	}

	/**
	 * Set the model path
	 * @param model
	 */
	public void setModel(String model) 
	{
		this.model = model;
	}

	/**
	 * Get the profile
	 * @return the profile
	 */
	public String getProfile() 
	{
		return profile;
	}

	/**
	 * Set the profile path
	 * @param profile
	 */
	public void setProfile(String profile) 
	{
		this.profile = profile;
	}

	/**
	 * Get output path
	 * @return String
	 */
	public String getOutput() 
	{
		return output;
	}

	/**
	 * Set the output path
	 * @param output
	 */
	public void setOutput(String output) 
	{
		this.output = output;
	}
	
	/**
	 * Set the package name
	 * @return
	 */
	public String getAcspackage() 
	{
		return acspackage;
	}

	/**
	 * Get the package name
	 * @param acspackage
	 */
	public void setAcspackage(String acspackage) 
	{
		this.acspackage = acspackage;
	}
	
	/**
	 * Get the model formated for internal EMF environment
	 * @return String
	 */
	public String getWellFormedModel() 
	{
		return protocol+this.model;
	}
	
	/**
	 * Get the model formated for internal EMF environment
	 * @return String
	 */
	public String getWellFormedProfile()
	{
		return protocol+this.profile;
	}
}
