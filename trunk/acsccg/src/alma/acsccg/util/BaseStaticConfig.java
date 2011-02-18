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
 * who     		when      		what
 * --------		--------		----------------------------------------------
 * atejeda 		2010-00-00  	Created
 * 
 * $Id: BaseStaticConfig.java 171 2011-01-13 14:03:26Z alexis.tejeda $
 */

package alma.acsccg.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Vector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import alma.ACSCCG;
import alma.acsccg.util.xtend.JavaExtension;

/**
 * Static config variables for the generator
 * and methods to get general information
 * @author atejeda
 */
public final class BaseStaticConfig 
{
	
	// Start variables definitions
	
	/**
	 * Base directory of MWE files generation
	 */
	private final static String BASE_MWE_DIR = "acsccg/mwe";
	
	/**
	 * MWE file for java code generation
	 */
	public final static String MWE_JAVA = BASE_MWE_DIR + "/JavaWorkflow.mwe";
	
	/**
	 * MWE file for cpp code generation
	 */
	public final static String MWE_CPP = BASE_MWE_DIR + "/CppWorkflow.mwe";
	
	/**
	 * MWE file for python code generation
	 */
	public final static String MWE_PYTHON = BASE_MWE_DIR + "/PythonWorkflow.mwe";
	
	/**
	 * MWE file for EModules generation of information as an xml
	 */
	public final static String MWE_MODELINFORMATION = BASE_MWE_DIR + "/ModelInformation.mwe";
	
	/**
	 * Static var to represents the tmp OS dir.
	 */
	public final static String TEMP_OS_DIR = System.getProperty("java.io.tmpdir");
	
	/**
	 * Logger Id of the generator
	 */
	public final static String ACSCCG_LOGGER = "cl.alma.acs.ccg"; 
	
	/**
	 * Name of the debug log file configuration
	 */
	public final static String DEBUG_LOG_FILE = "debug.log4j.properties";
	
	/**
	 * Path of the debug lod file configuration
	 */
	public final static String DEBUG_LOG_PATH = TEMP_OS_DIR + File.separator  + DEBUG_LOG_FILE;
	
	/**
	 * Get the ACS license template
	 */
	public final static String ACS_LICENSE_TEMPLATE = "ACSLicense.acs";
	
	/**
	 * Get the svn propset file information
	 */
	public final static String SVN_PROPSET_FILE = "svn.propsetinfo";
	
	/**
	 * Release information, is this automatically updated by svn
	 */
	public final static String RELEASE_VERSION = propsetInfo().get(0).replace("$", "");
	
	/**
	 * User name that has made the last change in the code, is this automatically updated by svn
	 */
	public final static String LAST_CHANGE_BY = propsetInfo().get(1).replace("$", "");
	
	/**
	 * Date release information, is this automatically updated by svn
	 */
	public final static String DATE_VERSION = propsetInfo().get(2).replace("$", "");
	
	
	/**
	 * Wrap limit of words to generate the comments in the code generated
	 */
	public final static int WORD_WRAP_MAX = 10;
	
	// Start methods definitions
	
	/**
	 * Get the acs license
	 * @return the license string
	 */
	public final static String getACSLicense(String licenseType)
	{
		String acsLicense = "-";
		try 
		{
			ClassLoader classLoader = ACSCCG.class.getClassLoader();
			InputStream buildIs = classLoader.getResourceAsStream(ACS_LICENSE_TEMPLATE);
			
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			
			Reader reader = new BufferedReader(new InputStreamReader(buildIs, "UTF-8"));
			
			int n;
            while ((n = reader.read(buffer)) != -1) 
            {
                writer.write(buffer, 0, n);
            }
            //get the build number
            acsLicense = writer.toString();
		}
		catch (Exception e) 
		{
			// if the file is not loaded properly log an error
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.ERROR, "SEVERE - Can't the ACS license type");
		}
		
		// replace the template params
		acsLicense.replaceAll("PROPSET", JavaExtension.getPropSet("Id"));
		acsLicense.replaceAll("COMMENTDATE", JavaExtension.getFullActualDate());
		
		// return types
		if (licenseType.isEmpty() || licenseType.equalsIgnoreCase("txt"))
		{
			// TODO
		}
		else if(licenseType.equalsIgnoreCase("xml"))
		{
			// TODO
		}
		else if(licenseType.equalsIgnoreCase("Makefile"))
		{
			// TODO
		}
		
		return acsLicense;
	}
	
	/**
	 * Get an array of svn propset information
	 * [0] for Revision
	 * [1] for Author
	 * [2] for Date
	 * @return
	 */
	public final static Vector<String> propsetInfo()
	{
		Vector<String> propset = new Vector<String>();
		String propsetString ="nill,nill,nill";
		
		try 
		{
			ClassLoader classLoader = ACSCCG.class.getClassLoader();
			InputStream buildIs = classLoader.getResourceAsStream(SVN_PROPSET_FILE);
			
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			
			Reader reader = new BufferedReader(new InputStreamReader(buildIs, "UTF-8"));
			
			int n;
            while ((n = reader.read(buffer)) != -1) 
            {
                writer.write(buffer, 0, n);
            }
            //get the build number
            propsetString = writer.toString();
		}
		catch (Exception e) 
		{
			// if the file is not loaded properly log an error
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.ERROR, "SEVERE - Can't load the file propset info file");
		}
		
		// replace the template params
		propset.add(propsetString.split(",")[0]);
		propset.add(propsetString.split(",")[1]);
		propset.add(propsetString.split(",")[2]);

		
		return propset;
	}
}








