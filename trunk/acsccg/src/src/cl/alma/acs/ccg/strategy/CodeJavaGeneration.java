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
 *  who       			when      			what
 * --------				--------					----------------------------------------------
 * atejeda 			2010-00-00  		Created
 * 
 */
package cl.alma.acs.ccg.strategy;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.mwe.core.WorkflowRunner;

import cl.alma.acs.ccg.util.BaseStaticConfig;
import cl.alma.acs.ccg.vo.VOGenerator;

/**
 * This class implement the Strategy design pattern of the generator
 * and execute the workflow for the generation.
 * @author atejeda
 */
public class CodeJavaGeneration  implements ICodeGenerationStrategy{
	
	private VOGenerator __voGenerator;
	
	/**
	 * Constructor
	 * @param voGenerator
	 */
	public CodeJavaGeneration(VOGenerator voGenerator) 
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
			wrunner.run(mweFile ,null, properties, slotContents);
		} 
		catch(Exception e) 
		{
			System.out.println(e.getMessage());
			return;
		}
	}
}
