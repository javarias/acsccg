/** 
 * COMODO - Multiplatform Component Code Generator 
 *    (c) European Southern Observatory, 2011 
 *    Copyright by ESO 
 *    All rights reserved 
 * 
 * Author: Alexis Tejeda
 * 
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * $Id$
 * 
 */

package comodo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Vector;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import comodo.cg.cli.EModules;
import comodo.cg.cli.Validation;
import comodo.cg.strategy.CodeJavaGeneration;
import comodo.cg.strategy.ContextCodeGeneration;
import comodo.cg.util.BaseStaticConfig;
import comodo.cg.vo.VOGenerator;

/**
* Component Code Generator Main class
* The start point of an standalone class 
* @name COMODO.java
* @author Alexis Tejeda, alexis.tejeda@gmail.com
* @version $Id$
*/
public class COMODO 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException
	{	
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
		opt.addOption("h", "help", false, "Print help for this application");
		opt.addOption("m", "model",true, "Model file path");
		opt.addOption("p", "profile", true, "Profile UML path");
		opt.addOption("o", "output",true, "The output folder path");
		opt.addOption("e", "emodule", true, "Specify the EModule to generate");
		//opt.addOption("x", "xml", false, "XML file with options m p o e, type help-xml to see an example of the file");
		//opt.addOption("hx", "help-xml", false, "An example of the options xml file");
		opt.addOption("d", "debug", false, "Debug information");
		
		// parse the options
		try 
		{
			 cl = parser.parse(opt, args);
		} 
		catch (Exception e) 
		{
			Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.ERROR, e.getMessage());
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
			Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "Debug logging activated");
			Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "----------------------");
			Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "Build "+readBuildNumber());
			Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, BaseStaticConfig.RELEASE_VERSION);
			Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, BaseStaticConfig.DATE_VERSION);
			Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "----------------------");
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		// Program Lifecycle
		//////////////////////////////////////////////////////////////////////////////////////////////
		Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "Start validation");
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		// Validate options, paths, and model
		//////////////////////////////////////////////////////////////////////////////////////////////
		// if not pass the validation, halt
		if(!Validation.validateAP(cl))
		{
			Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.ERROR, "Program terminated due errors in the validation, see the help and logs");
			System.out.println("");
			printHelp(opt);
			return;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		// Print the arguments and paths
		//////////////////////////////////////////////////////////////////////////////////////////////
		Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "Model selected: "+Validation.getModelPathArg(cl));
		Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "Profile selected: "+Validation.getProfilePathArg(cl));
		Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "Output selected: "+Validation.getOutputPathArg(cl));
		Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "Module selected: "+Validation.getEModuleArg(cl));
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		// Get a list of modules to validate the module existence
		//////////////////////////////////////////////////////////////////////////////////////////////
		Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "Reading the EModules");
		EModules eModules = new EModules(new VOGenerator(Validation.getModelPathArg(cl), Validation.getProfilePathArg(cl)));
		Vector<String> eModulesVector = eModules.getEModules();
		
		// validate the module specified in the argument
		Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "Check if the module exists");
		Validation.checkModuleExistence(eModulesVector, Validation.getEModuleArg(cl));
		
		// if there's any errors, halt
		if(!Validation.getInstance().noErrors())
		{
			Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.ERROR, "Program terminated due errors in the validation, see the logs");
			return;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		// Code Generation
		//////////////////////////////////////////////////////////////////////////////////////////////
		
		VOGenerator voGenerator = new VOGenerator(
				Validation.getModelPathArg(cl),
				Validation.getProfilePathArg(cl),
				Validation.getOutputPathArg(cl),
				Validation.getEModuleArg(cl));
		
		//Calling to the Java strategy...
        new ContextCodeGeneration(new CodeJavaGeneration(voGenerator)).generateACSCode();
        
        Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "Done");
	}
	
	/**
	 * Print help
	 */
	public static void printHelp(Options opt)
	{
		HelpFormatter f = new HelpFormatter();
		f.printHelp("java -jar comodo.jar {options}\n", opt);
		System.out.println("");
	}
	
	/**
	 * Print a little info about the generator.
	 */
	public static void about() 
	{
		System.out.println("");
		Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "COMODO Code Generator");
		Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, BaseStaticConfig.RELEASE_VERSION);
		Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, BaseStaticConfig.DATE_VERSION);
		Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "Build Number: "+readBuildNumber());
		Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "http://code.google.com/p/acsccg/");
	}
	
	/**
	 * Print when the generation is done
	 */
	public static void printFinal()
	{
		Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "Done");
	}
	
	/**
	 * Load the log4 properties file of debug or normal log
	 */
	public static void loadDebuglog4j()
	{
		try
		{
			ClassLoader classLoader = COMODO.class.getClassLoader();
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
			// if the file is not loaded properly log an error
			Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.ERROR, "SEVERE - Can't load the debug properties file - exiting..");
			return;
		}
	}
	
	/**
	 * Get the build number generated by ant
	 * @return A string with the build number
	 */
	public static String readBuildNumber()
	{
		String buildNumber = "-";
		try 
		{
			ClassLoader classLoader = COMODO.class.getClassLoader();
			InputStream buildIs = classLoader.getResourceAsStream("build.num");
			
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			
			Reader reader = new BufferedReader(new InputStreamReader(buildIs, "UTF-8"));
			
			int n;
            while ((n = reader.read(buffer)) != -1) 
            {
                writer.write(buffer, 0, n);
            }
            //get the build number
            buildNumber = writer.toString().split("=")[1].toString();
		}
		catch (Exception e) 
		{
			// if the file is not loaded properly log an error
			Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.ERROR, "SEVERE - Can't load the build number");
		}
		
		return buildNumber;
	}
}
