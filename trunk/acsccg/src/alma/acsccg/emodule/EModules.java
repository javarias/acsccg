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
package acsccg.emodule;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.mwe.core.WorkflowRunner;

import cl.alma.acs.ccg.util.BaseStaticConfig;
import cl.alma.acs.ccg.vo.VOGenerator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * Class to get the EModules in the model,
 * the EModules are defined by stereotypes in the model
 * @author atejeda
 */
public class EModules 
{
	
	private VOGenerator __voGenerator;
	private Vector<String> __eModulesVector;
	
	/**
	 * Constructor
	 * @param voGenerator
	 */
	public EModules(VOGenerator voGenerator)
	{
		__voGenerator = voGenerator;
		__eModulesVector = new Vector<String>();
	}
	
	/**
	 * Get the EModules as a list, this method
	 * get the name of all  packages with the stereotype 
	 * <<EModules>>
	 */
	public Vector<String> getEModules()
	{
		// run the workflow to get the EModules
		runEmodulesWorkflow();
		
		// read the XML generated by the workflow
		readXMLEmodules();
		
		// return a Vector<String> with the EModules name
		return __eModulesVector;
	}
	
	/**
	 * Workflow runner method to get the information of EModules that
	 * they are present in the model file exported with the stereotype
	 * <<EModule>>
	 */
	//SuppressWarnings added
	@SuppressWarnings("unchecked")
	public void runEmodulesWorkflow()
	{
		String mweFile = BaseStaticConfig.MWE_MODELINFORMATION;
		
		//Map the properties to the workflow
		Map properties = new HashMap();
		properties.put("modelFileURI", __voGenerator.getWellFormedModel());
		properties.put("profileFileURI",__voGenerator.getWellFormedProfile());
		properties.put("ouputFolderURI",BaseStaticConfig.TEMP_OS_DIR);
		System.out.println(BaseStaticConfig.TEMP_OS_DIR);
		
		Map slotContents = new HashMap();
		
		WorkflowRunner wrunner = new WorkflowRunner();
		
		//calling the workflow runner
		try 
		{
			wrunner.run(mweFile ,null, properties, slotContents);
		} 
		catch(Exception e) 
		{
			//System.out.println(e.getMessage());
			 Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.ERROR, "There's a problem reading the modules");
			return;
		}
	}
	
	/**
	 * Read the XML file that contains information of the EModules
	 * in the model with the stereotype <<EModule>>
	 */
	private void readXMLEmodules()
	{
		Document __doc = null;
		String xmURI = BaseStaticConfig.TEMP_OS_DIR + "/EModules.xml";
		
		SAXBuilder parser = new SAXBuilder();
		
		//parse the document
		try 
		{
			__doc = parser.build(xmURI);
		} 
		catch (JDOMException e) 
		{
			//e.printStackTrace();
			 Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.ERROR, "There's a problem reading the modules");
		} 
		catch (IOException e) 
		{
			///e.printStackTrace();
			 Logger.getLogger(BaseStaticConfig.ACSCCG_LOGGER).log(Level.ERROR, "There's a problem reading the modules");
		}
		
		Element root = __doc.getRootElement();
		List<?> eModules = root.getChildren("EModule");
		Iterator<?> i = eModules.iterator();
		
		while (i.hasNext())
		{
			Element e = (Element) i.next();
			String moduleName = e.getText();
			__eModulesVector.add(moduleName);
		}
	}

}
