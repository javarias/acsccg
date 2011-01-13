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

import java.io.File;

/**
 * Static config variables for the generator
 * @author atejeda
 */
public final class BaseStaticConfig 
{
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
	 * Release information, is this automatically updated by svn
	 */
	public final static String RELEASE_VERSION = ("$Revision: 183$").replace("$", "");
	
	/**
	 * Date release information, is this automatically updated by svn
	 */
	public final static String DATE_VERSION = ("$Date$").replace("$", "");
	
	/**
	 * User name that has made the last change in the code, is this automatically updated by svn
	 */
	public final static String LAST_CHANGE_BY = ("$Author: alexis.tejeda $").replace("$", "");
	
	/**
	 * Wrap limit of words to generate the comments in the code generated
	 */
	public final static int WORD_WRAP_MAX = 10;
}


