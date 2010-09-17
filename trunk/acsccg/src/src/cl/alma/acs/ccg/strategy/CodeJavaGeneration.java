package cl.alma.acs.ccg.strategy;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.mwe.core.WorkflowRunner;

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
	@Override
	public void generateCode() 
	{
		
		//Calling the workflow file
		String mweFile = "cl/alma/acs/ccg/mwe/JavaWorkflow.mwe";
		
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
