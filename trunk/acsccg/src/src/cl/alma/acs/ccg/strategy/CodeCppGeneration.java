package cl.alma.acs.ccg.strategy;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.mwe.core.WorkflowRunner;

import cl.alma.acs.ccg.vo.VOGenerator;

public class CodeCppGeneration implements ICodeGenerationStrategy { 

	private VOGenerator __voGenerator;
	
	public CodeCppGeneration(VOGenerator voGenerator) {
		__voGenerator = voGenerator;
	}
	
	@SuppressWarnings("unchecked")
	public void generateCode() {
		
		String mweFile = "cl/alma/codegeneration/mwe/JavaWorkflow.mwe";
		
		Map properties = new HashMap();
		properties.put("modelFileURI", __voGenerator.getWellFormedModel());
		properties.put("profileFileURI",__voGenerator.getWellFormedProfile());
		
		Map slotContents = new HashMap();
		
		WorkflowRunner wrunner = new WorkflowRunner();
		try {
			wrunner.run(mweFile ,null, properties, slotContents);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return;
		}
	}
}
