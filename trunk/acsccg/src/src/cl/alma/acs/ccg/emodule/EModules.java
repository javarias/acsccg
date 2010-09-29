package cl.alma.acs.ccg.emodule;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
		runWorkflow();
		
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
	private void runWorkflow()
	{
		String mweFile = BaseStaticConfig.MWE_MODELINFORMATION;
		
		//Map the properties to the workflow
		Map properties = new HashMap();
		properties.put("modelFileURI", __voGenerator.getWellFormedModel());
		properties.put("profileFileURI",__voGenerator.getWellFormedProfile());
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
	
	/**
	 * Read the XML file that contains information of the EModules
	 * in the model with the stereotype <<EModule>>
	 */
	private void readXMLEmodules()
	{
		Document __doc = null;
		String xmURI = __voGenerator.getOutput() + "/tmp/EModules.xml";
		
		SAXBuilder parser = new SAXBuilder();
		
		//parse the document
		try 
		{
			__doc = parser.build(xmURI);
		} 
		catch (JDOMException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
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