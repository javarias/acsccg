import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.log4j.PropertyConfigurator;

import cl.alma.acs.ccg.emodule.EModules;
import cl.alma.acs.ccg.strategy.CodeJavaGeneration;
import cl.alma.acs.ccg.strategy.ContextCodeGeneration;
import cl.alma.acs.ccg.util.BaseStaticConfig;
import cl.alma.acs.ccg.vo.VOGenerator;

/**
 * Main class of the generator
 * @author atejeda
 */
public class ACSCCG 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException
	{
	
		File log4properties = new File(BaseStaticConfig.LO4J_PROPERTIES);

		PropertyConfigurator.configure(log4properties.getAbsolutePath());
		
		//string paths containers
		String modelPath; 
		String profilePath;
		String outputPath;
		String acspackage;
		
		//set up options
		Options opt = new Options();;	
		try 
		{
			opt.addOption("h", false, "Print help for this application");
			opt.addOption("a", false, "About this application");
			opt.addOption("m", true, "Model file path, i.e.: /my/very/path/to/MyProject.uml");
			opt.addOption("p", true, "Profile UML path, i.e.: /my/very/path/to/AlmaGenerator.profile.uml");
			opt.addOption("o", true, "The output folder path");
			opt.addOption("v", true, "Set true to get the log info, i.e. -v= true");
			opt.addOption("d", true, "True if want to clean the output folder.. ! BEWARE ! will erase all from folder an parent folder !");
			opt.addOption("c", true, "the output code language, values are (default) java , cpp, python - for now only java is supported");
			
			BasicParser parser = new BasicParser();
			CommandLine cl = parser.parse(opt, args);
			
			//print help
			if ( cl.hasOption('h') ) 
			{
				about();
				HelpFormatter f = new HelpFormatter();
				f.printHelp("java -jar acsComponentCodeGenerator.jar  [options]\n", opt);
				
			} 
			else if
					( 
						(!cl.getOptionValue("m").isEmpty() || !cl.getOptionValue("p").isEmpty() || !cl.getOptionValue("o").isEmpty()) 
						&&
						(cl.hasOption('m') && cl.hasOption('p') && cl.hasOption('o')) 
					)
			{
				
				//parse the paths
				modelPath = cl.getOptionValue("m");
				profilePath = cl.getOptionValue("p");
				outputPath = cl.getOptionValue("o");
				
				// Print some information about the paths
				printPaths(modelPath,profilePath,outputPath);
				
				// EModules info
				// --------------------------------------------------------------------------------------
				EModules eModules = new EModules(new VOGenerator(modelPath, profilePath, outputPath));
				Vector<String> eModulesVector = eModules.getEModules();
				eModulesInfo(eModulesVector);
				// --------------------------------------------------------------------------------------
				
				acspackage = eModulesVector.toArray()[3].toString();
				
				//Calling to the Java strategy...
				new ContextCodeGeneration(new CodeJavaGeneration(new VOGenerator(modelPath, profilePath, outputPath, acspackage))).generateACSCode();
				
				//Calling to the Cpp strategy..
				//new ContextCodeGeneration(new CodeCppGeneration(new VOGenerator(modelPath, profilePath, outputPath))).generateACSCode();
				
				//Calling to the Python strategy..
				//new ContextCodeGeneration(new CodePythonGeneration(new VOGenerator(modelPath, profilePath, outputPath))).generateACSCode();
				
				printFinal();
				
			} 
		} 
		
		catch (Exception e)
		{
			//if anything goes wront, print the help
			about();
			HelpFormatter f = new HelpFormatter();
			f.printHelp("java -jar acsComponentCodeGenerator.jar [options]\n", opt);
			System.out.println("\n An Error has been found, see the help...");
			System.out.println("\t\trun: java -jar acsComponentCodeGenerator.jar -h\n");
		}
	}
	
	/**
	 * Print a little info about the generator...
	 */
	public static void about() 
	{
		System.out.println("");
		System.out.println("ACS Component Code Generator");
		System.out.println("Compilation " + new Date().toString());
		System.out.println("");
	    System.out.println("http://code.google.com/p/acsccg/");
		System.out.println("");
	}
	
	/**
	 * Print the Emodules found.
	 * @param eModules
	 */
	public static void eModulesInfo(Vector<String> eModules)
	{
		System.out.println("");
		System.out.println(" The generator has found "+eModules.size()+" EModules");
		System.out.println(" --------------------------------------------------------------------------------------");
		
		for(int i=0;i < eModules.size(); i++ )
		{
			System.out.println(" ["+i+"] "+eModules.toArray()[i]);
		}
		
		System.out.println("");
	}
	
	/**
	 * Print the info about the paths
	 * @param modelPath
	 * @param profilePath
	 * @param outputPath
	 */
	public static void printPaths(String modelPath, String profilePath, String outputPath)
	{
		System.out.println("");
		System.out.println("ACS Component Code Generator");
		System.out.println("Compilation " + new Date().toString());
		System.out.println("");
		System.out.println("Model file:\t\t"+modelPath);
		System.out.println("Profile file:\t\t"+profilePath);
		System.out.println("Output folder:\t"+outputPath);
		System.out.println("");
	}
	
	/**
	 * Print when the generation its done
	 */
	public static void printFinal()
	{
		System.out.println("Done.");
	}
}
