package cl.alma.acs.ccg.util;

/**
 * Static config variables for the generator
 * @author atejeda
 */
public final class BaseStaticConfig 
{
	
	//private var for internal usage
	private final static String BASE_MWE_DIR = "cl/alma/acs/ccg/mwe";
	
	public final static String MWE_JAVA = BASE_MWE_DIR + "/JavaWorkflow.mwe";
	
	public final static String MWE_CPP = BASE_MWE_DIR + "/CppWorkflow.mwe";
	
	public final static String MWE_PYTHON = BASE_MWE_DIR + "/PythonWorkflow.mwe";
	
	public final static String MWE_MODELINFORMATION = BASE_MWE_DIR + "/ModelInformation.mwe";
	
	public final static String LO4J_PROPERTIES = "log4j.properties";
}