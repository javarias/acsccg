/** 
 * COMODO - Multiplatform Component Code Generator 
 *    (c) European Southern Observatory, 2011 
 *    Copyright by ESO 
 *    All rights reserved 
 * 
 * Author: Alexis Tejeda
 * 
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * $Id$
 * 
 */

package comodo.cg.strategy;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.mwe.core.WorkflowRunner;

import comodo.cg.vo.VOGenerator;

public class CodePythonGeneration  implements ICodeGenerationStrategy 
{

	private VOGenerator __voGenerator;
	
	public CodePythonGeneration(VOGenerator voGenerator) {
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

