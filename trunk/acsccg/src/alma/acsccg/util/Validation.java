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
 *  who       		when      			what
 * --------			----------			----------------------------------------------
 * atejeda 			2010-00-00  		Created
 * 
 */
package alma.acsccg.util;

import java.io.File;
import java.util.Vector;

import org.apache.commons.cli.CommandLine;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author atejeda
 */
public class Validation 
{
	//////////////////////////////////////////////////////////////////////////////////////////////
	// Singleton variables and functions
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	private static Validation __instance; 
	Vector<String> __error;
	
	/**
	 * Singleton return as validation instance
	 * @return Validation
	 */
	public static Validation getInstance()
	{
		if(__instance == null)
			__instance = new Validation();
		return __instance;
	}
	
	/**
	 * Constructor
	 */
	private Validation()
	{
		__error = new Vector<String>();
	}
	
	/**
	 * Add a error message to the validations
	 * @param errorMessage
	 */
	public void registerError(String errorMessage)
	{
		// add the message error
		__error.add(errorMessage);
		
		// Add a logger message
		Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.ERROR, errorMessage);	
	}
	
	/**
	 * Return the list of error found
	 * @return Vector<String>
	 */
	public Vector<String> getErrorList()
	{
		return __error;
	}
	
	/**
	 * Return true if there's no errors and false if they are.
	 * @return boolean
	 */
	public boolean noErrors()
	{
		return (__error.size()==0);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	// Static functions to validate the args, path and module
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Arguments validation
	 */
	public static final void validateArgs(CommandLine cl)
	{
		// check the options
		
		// model option
		if(!cl.hasOption('m'))
		{
			Validation.getInstance().registerError("There's no model file path option specified, use -m");
		}
		
		// profile option
		if(!cl.hasOption('p'))
		{
			Validation.getInstance().registerError("There's no profile file path option specified, use -p");
		}
		
		// output option
		if(!cl.hasOption('o'))
		{
			Validation.getInstance().registerError("There's no output path option specified, use -o");
		}
		
		// module option
		if(!cl.hasOption('e'))
		{
			Validation.getInstance().registerError("There's no module name option specified, use -e");
		}
		
		// check if options are empty
		
		// check the model
		if(cl.getOptionValue("m").isEmpty())
		{
			Validation.getInstance().registerError("There's no model file path specified");
		}
		
		// check the profile
		if(cl.getOptionValue("p").isEmpty())
		{
			Validation.getInstance().registerError("There's no profile file path specified");
		}
		
		// check the output
		if(cl.getOptionValue("o").isEmpty())
		{
			Validation.getInstance().registerError("There's no output path specified");
		}
		
		// check the module
		if(cl.getOptionValue("e").isEmpty())
		{
			Validation.getInstance().registerError("There's no module file path specified, use i.e:  -e singleComponent");
		}
	}
	
	/**
	 * Path validation existence
	 */
	public static final void validatePaths(String model, String profile)
	{
		// the creation of variables for the files
		// is not necessary for the validation
		
		// if the model not exists, add an error
		if(!(new File(model).exists()))
		{
			Validation.getInstance().registerError("The model file not exists, check the path");
		}
			
		// if the profile not exists, add an error
		if(!(new File(profile).exists()))
		{
			Validation.getInstance().registerError("The profile file not exists, check the path");
		}
	}
	
	/**
	 * Get the model path from the command line
	 * @param cl
	 * @return
	 */
	public static final String getModelPathArg(CommandLine cl)
	{
		return cl.getOptionValue("m");
	}
	
	/**
	 * Get the profile path from the command line
	 * @param cl
	 * @return
	 */
	public static final String getProfilePathArg(CommandLine cl)
	{
		return cl.getOptionValue("p");
	}
	
	/**
	 * Get the output path from the command line
	 * @param cl
	 * @return
	 */
	public static final String getOutputPathArg(CommandLine cl)
	{
		return cl.getOptionValue("o");
	}
	
	/**
	 * Get the Module name from the command line
	 * @param cl
	 * @return
	 */
	public static final String getEModuleArg(CommandLine cl)
	{
		return cl.getOptionValue("e");
	}
	
	/**
	 * Check if the module exists
	 * @param eModules
	 */
	public static final void checkModuleExistence(Vector<String> eModules, String moduleArg)
	{
		Boolean found = false;
		
		// if the module is not found
		for(String emodule : eModules)
		{
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "EModule found: "+emodule);
			if(emodule.equalsIgnoreCase(moduleArg))
			{	
				found = true;
			}
		}
		// module not found
		// register error
		if(!found)
		{
			Validation.getInstance().registerError("The module not exists in the model, maybe can be a typo error ?");
		}
		else
		{
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Pass, the model exists: "+moduleArg);
		}
	}
	
	/**
	 * Validate if the arguments and paths are ok
	 * return true if everything is ok, if not returns false
	 * @param cl
	 */
	public static final boolean validateAP(CommandLine cl)
	{	
		try
		{
			// validate arguments
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Validating the arguments");
			Validation.validateArgs(cl);
			if(!Validation.getInstance().noErrors())
				return false;
			
			// validate paths
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Validating the paths.");
			Validation.validatePaths(Validation.getModelPathArg(cl),Validation.getProfilePathArg(cl));
			if(!Validation.getInstance().noErrors())
				return false;
			
			return true;
		}
		catch(Exception e)
		{
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.ERROR, "Something is wrong with the models paths and the arguments");	
			return false;
		}
	}
	
}
