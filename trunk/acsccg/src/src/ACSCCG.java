import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;
import java.util.Scanner;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import cl.alma.acs.ccg.emodule.EModules;
import cl.alma.acs.ccg.strategy.CodeJavaGeneration;
import cl.alma.acs.ccg.strategy.ContextCodeGeneration;
import cl.alma.acs.ccg.util.BaseStaticConfig;
import cl.alma.acs.ccg.util.Validation;
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
		//////////////////////////////////////////////////////////////////////////////////////////////
		// Development setup - only for development proposes
		// File log4properties = new File(BaseStaticConfig.LO4J_PROPERTIES);
		// PropertyConfigurator.configure(log4properties.getAbsolutePath());
		//////////////////////////////////////////////////////////////////////////////////////////////
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		// String paths variables
		// Deprecated
		//////////////////////////////////////////////////////////////////////////////////////////////
		String modelPath = ""; 
		String profilePath = "";
		String outputPath = "";
		String acspackage = "";
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		// Print some info
		//////////////////////////////////////////////////////////////////////////////////////////////
		about();
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		// Setup Command Line Options
		//////////////////////////////////////////////////////////////////////////////////////////////
		CommandLine cl = null;
		Options opt = new Options();
		BasicParser parser = new BasicParser();
		
		// adding the options
		opt.addOption("h", false, "Print help for this application");
		opt.addOption("m", true, "Model file path, i.e.: /my/very/path/to/MyProject.uml");
		opt.addOption("p", true, "Profile UML path, i.e.: /my/very/path/to/AlmaGenerator.profile.uml");
		opt.addOption("o", true, "The output folder path");
		opt.addOption("e", true, "Specify the EModule to generate");
		
		// parse the options
		try 
		{
			 cl = parser.parse(opt, args);
		} 
		catch (Exception e) 
		{
			
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.ERROR, e.getMessage());
			System.out.println("");
			printHelp(opt);
			return;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		// Print the help if has been choosed, or there's no options or args
		//////////////////////////////////////////////////////////////////////////////////////////////
		if(cl.hasOption('h') || cl.getOptions().length == 0)
		{
			printHelp(opt);
			return;
		}	
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		// Program Lifecycle
		//////////////////////////////////////////////////////////////////////////////////////////////
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Start validation");
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		// Validate options, paths, and model
		//////////////////////////////////////////////////////////////////////////////////////////////
		// if not pass the validation, halt
		if(!Validation.validateAP(cl))
		{
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.ERROR, "Program terminated due errors in the validation, see the help and logs");
			System.out.println("");
			printHelp(opt);
			return;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		// Print the arguments and paths
		//////////////////////////////////////////////////////////////////////////////////////////////
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Model:\t"+Validation.getModelPathArg(cl));
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Profile:\t"+Validation.getProfilePathArg(cl));
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Output:\t"+Validation.getOutputPathArg(cl));
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Module:\t"+Validation.getEModuleArg(cl));
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		// Get a list of modules to validate the module existence
		//////////////////////////////////////////////////////////////////////////////////////////////
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Reading the EModules");
		EModules eModules = new EModules(new VOGenerator(Validation.getModelPathArg(cl), Validation.getProfilePathArg(cl)));
		Vector<String> eModulesVector = eModules.getEModules();
		
		// validate the module specified in the argument
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Check if the module exists");
		Validation.checkModuleExistence(eModulesVector, Validation.getEModuleArg(cl));
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		// Code Generation
		//////////////////////////////////////////////////////////////////////////////////////////////
		// if there's any errors, halt
		if(!Validation.getInstance().noErrors())
		{
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.ERROR, "Program terminated due errors in the validation, see the logs");
		}
		
		//Start the code generation	
		
	}
	
	/**
	 * Print help
	 */
	public static void printHelp(Options opt)
	{
		HelpFormatter f = new HelpFormatter();
		f.printHelp("java -jar acsComponentCodeGenerator.jar {options}\n", opt);
		System.out.println("");
	}
	
	/**
	 * Print a little info about the generator.
	 */
	public static void about() 
	{
		System.out.println("");
		System.out.println("ACS Component Code Generator");
		System.out.println("Build " + "20101021");
		System.out.println("");
	    System.out.println("http://code.google.com/p/acsccg/");
		System.out.println("");
	}
		
	/**
	 * Print the info about the paths
	 * @param modelPath
	 * @param profilePath
	 * @param outputPath
	 */
	public static void printPaths(String modelPath, String profilePath, String outputPath, String acspackage)
	{
		System.out.println("");
		System.out.println("ACS Component Code Generator");
		System.out.println("Compilation " + new Date().toString());
		System.out.println("");
		System.out.println("Model file:\t\t"+modelPath);
		System.out.println("Profile file:\t\t"+profilePath);
		System.out.println("Output folder:\t"+outputPath);
		System.out.println("Module:\t\t\t"+acspackage);
		System.out.println("");
	}
	
	/**
	 * Print when the generation its done
	 */
	public static void printFinal()
	{
		System.out.println("Done");
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Done");
	}
}
