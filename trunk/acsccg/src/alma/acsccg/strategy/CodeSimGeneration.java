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
 * atejeda 		2011-05-03  	Created
 * 
 * $Id$
 */

package alma.acsccg.strategy;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.mwe.core.WorkflowRunner;

import alma.acsccg.util.BaseStaticConfig;
import alma.acsccg.vo.VOGenerator;

public class CodeSimGeneration implements ICodeGenerationStrategy 
{
private VOGenerator __voGenerator;
	
	/**
	 * Constructor
	 * @param voGenerator
	 */
	public CodeSimGeneration(VOGenerator voGenerator) 
	{
		__voGenerator = voGenerator;
		
	}

	/**
	 * Generate code function, see strategy pattern design
	 * this is the function where the workflow are called
	 */
	@SuppressWarnings("unchecked")
	public void generateCode() 
	{
		
		//Calling the workflow file
		String mweFile = BaseStaticConfig.MWE_JAVA;
		
		//Map the properties to the workflow
		Map properties = new HashMap();
		properties.put("modelFileURI", __voGenerator.getWellFormedModel());
		properties.put("profileFileURI",__voGenerator.getWellFormedProfile());
		properties.put("acsPackage",__voGenerator.getAcspackage());
		properties.put("ouputFolderURI",__voGenerator.getOutput());
		
		Map slotContents = new HashMap();
		
		WorkflowRunner wrunner = new WorkflowRunner();
		
		//calling the workflow runner
		try 
		{
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.INFO, "Generating the code... wait");
			wrunner.run(mweFile ,null, properties, slotContents);
		} 
		catch(Exception e) 
		{
			//System.out.println(e.getMessage());
			Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.ERROR, e.getMessage());
			return;
		}
	}
}
