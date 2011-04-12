package comodo.cg.strategy;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.mwe.core.WorkflowRunner;

import comodo.cg.util.BaseStaticConfig;
import comodo.cg.vo.VOGenerator;

public class CodeCDBGeneration implements ICodeGenerationStrategy 
{

private VOGenerator __voGenerator;
	
	/**
	 * Constructor
	 * @param voGenerator
	 */
	public CodeCDBGeneration(VOGenerator voGenerator) 
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
		String mweFile = BaseStaticConfig.MWE_COMODO;
		
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
			Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.INFO, "Generating the code... wait");
			wrunner.run(mweFile ,null, properties, slotContents);
		} 
		catch(Exception e) 
		{
			//System.out.println(e.getMessage());
			Logger.getLogger(BaseStaticConfig.COMODO_LOGGER).log(Level.ERROR, e.getMessage());
			return;
		}
	}

}
