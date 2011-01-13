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
 * $Id$
 */

package alma.acsccg.util;

import java.io.File;

/**
 * Static config variables for the generator
 * @author atejeda
 */
public final class BaseStaticConfig 
{
	private final static String BASE_MWE_DIR = "acsccg/mwe";
	
	public final static String MWE_JAVA = BASE_MWE_DIR + "/JavaWorkflow.mwe";
	
	public final static String MWE_CPP = BASE_MWE_DIR + "/CppWorkflow.mwe";
	
	public final static String MWE_PYTHON = BASE_MWE_DIR + "/PythonWorkflow.mwe";
	
	public final static String MWE_MODELINFORMATION = BASE_MWE_DIR + "/ModelInformation.mwe";
	
	public final static String TEMP_OS_DIR = System.getProperty("java.io.tmpdir");
	
	public final static String ACSCCG_LOGGER = "cl.alma.acs.ccg"; 
	
	public final static String DEBUG_LOG_FILE = "debug.log4j.properties";
	
	public final static String DEBUG_LOG_PATH = TEMP_OS_DIR + File.separator  + DEBUG_LOG_FILE;
	
	public final static String RELEASE_VERSION = ("$Revision$").replace("$", "");
	
	public final static String DATE_VERSION = ("$Date$").replace("$", "");
	
	public final static String LAST_CHANGE_BY = ("$Author$").replace("$", "");
	
	public final static int WORD_WRAP_MAX = 10;
}

