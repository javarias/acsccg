/**
 * ACS Component Code Generator - http://code.google.com/p/acsccg/
 * Copyright (C) 2010  Alexis Tejeda, alexis.tejeda@gmail.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 *  who       	when      		what
 * --------	--------		----------------------------------------------
 * atejeda 	2010-00-00  		Created
 * 
 */
package alma;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import alma.acsccg.emodule.EModules;
import alma.acsccg.strategy.CodeJavaGeneration;
import alma.acsccg.strategy.ContextCodeGeneration;
import alma.acsccg.util.BaseStaticConfig;
import alma.acsccg.util.Validation;
import alma.acsccg.vo.VOGenerator;

/**
* Component Code Generator Main class
* The start point of an standalone class 
* @name ACSCCG.java
* @author Alexis Tejeda, alexis.tejeda@gmail.com
* @version 
*/
public class ACSCCG 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException
	{	
		
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "");
		
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
		opt.addOption("m", true, "Model file path");
		opt.addOption("p", true, "Profile UML path");
		opt.addOption("o", true, "The output folder path");
		opt.addOption("e", true, "Specify the EModule to generate");
		opt.addOption("d", false, "Debug information");
		
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
		// Debug information
		//////////////////////////////////////////////////////////////////////////////////////////////
		if(cl.hasOption('d'))
		{
			loadDebuglog4j();
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Debug option activated");
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
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Model selected: "+Validation.getModelPathArg(cl));
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Profile selected: "+Validation.getProfilePathArg(cl));
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Output selected: "+Validation.getOutputPathArg(cl));
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Module selected: "+Validation.getEModuleArg(cl));
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		// Get a list of modules to validate the module existence
		//////////////////////////////////////////////////////////////////////////////////////////////
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Reading the EModules");
		EModules eModules = new EModules(new VOGenerator(Validation.getModelPathArg(cl), Validation.getProfilePathArg(cl)));
		Vector<String> eModulesVector = eModules.getEModules();
		
		// validate the module specified in the argument
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Check if the module exists");
		Validation.checkModuleExistence(eModulesVector, Validation.getEModuleArg(cl));
		
		// if there's any errors, halt
		if(!Validation.getInstance().noErrors())
		{
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.ERROR, "Program terminated due errors in the validation, see the logs");
			return;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		// Code Generation
		//////////////////////////////////////////////////////////////////////////////////////////////
		
		//Calling to the Java strategy...
        new ContextCodeGeneration(
        		new CodeJavaGeneration(
        				new VOGenerator(
        						Validation.getModelPathArg(cl), 
        						Validation.getProfilePathArg(cl), 
        						Validation.getOutputPathArg(cl),
        						Validation.getEModuleArg(cl)
        						)
        				)
        		).generateACSCode();
        Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Done");
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
		System.out.println("Revision: " + BaseStaticConfig.RELEASE_VERSION);
		System.out.println("");
	    System.out.println("http://code.google.com/p/acsccg/");
		System.out.println("");
	}
	
	/**
	 * Print when the generation is done
	 */
	public static void printFinal()
	{
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Done");
	}
	
	/**
	 * Load the log4 properties file of debug or normal log
	 */
	public static void loadDebuglog4j()
	{
		try
		{
			ClassLoader classLoader = ACSCCG.class.getClassLoader();
			InputStream debugIs = classLoader.getResourceAsStream(BaseStaticConfig.DEBUG_LOG_FILE);
			
			File debugFile = new File(BaseStaticConfig.DEBUG_LOG_PATH);
			
			OutputStream debugOs = new FileOutputStream(debugFile);
			
			byte[] buffer = new byte[1024];
			
			int len;
			
			   while((len=debugIs.read(buffer))>0)
			   {
				   debugOs.write(buffer,0,len);
			   }
			   debugOs.close();
			   debugIs.close();

			PropertyConfigurator.configure(debugFile.getAbsolutePath());
		}
		catch (Exception e)
		{
			// if the file is not loaded properly, ends the execution of the program.
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.ERROR, "SEVERE - Can't load the debug properties file - exiting..");
			return;
		}
		
	}
}




























