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
 * nmigliorini  2010-00-00  	Created
 * atejeda		 2011-29-03     Modificated input params for mwe
 * 
 * $Id$
 */

package alma;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.eclipse.emf.mwe.core.WorkflowRunner;

public class SCXMLCG 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException
	{
		String modelPath=""; 
		String outputPath="";
		
		Options opt = new Options();
		
		try 
		{
			opt.addOption("m", true, "Model file path, i.e.: /my/very/path/to/MyProject.uml");
			opt.addOption("o", true, "The output folder path");
			
			BasicParser parser = new BasicParser();
			CommandLine cl = parser.parse(opt, args);
			
			if
					( (!cl.getOptionValue("m").isEmpty() || !cl.getOptionValue("o").isEmpty()) 
						&&
						(cl.hasOption('m') && cl.hasOption('o')) )
			{
				//parse the paths
				modelPath = cl.getOptionValue("m");
				outputPath = cl.getOptionValue("o");
				
				// Print some information about the paths
				printPaths(modelPath, outputPath);

				generateCode(modelPath, outputPath);
				printFinal();
				
			} else 
				{
					System.out.println("\n ERROR: Syntax is: -m model/path/model/name -o output/path");
				} 
		} 
		
		catch (Exception e)
		{
			System.out.println("\n An Error has been found...");
		}
	}
	
	/**
	 * Print the info about the paths
	 * @param modelPath
	 * @param outputPath
	 */
	public static void printPaths(String modelPath, String outputPath)
	{
		System.out.println("");
		System.out.println("SCXML Code Generator");
		//System.out.println("Compilation " + new Date().toString());
		System.out.println("");
		System.out.println("Model file:\t"+modelPath);
		System.out.println("Output folder:\t"+outputPath);
		System.out.println("");
	}
	
	/**
	 * Print when the generation its done
	 */
	public static void printFinal()
	{
		System.out.println("");
		System.out.println("Done.");
		System.out.println("");
	}
	
	
	/**
	 * Generate code function, see strategy pattern design
	 * this is the function where the workflow are called
	 * @param outputStr 
	 * @param modelStr 
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void generateCode(String modelStr, String outputStr)
	{
		//Calling the workflow file
		String mweFile = StaticConfig.MWE;
		
		//Map the properties to the workflow
		Map properties = new HashMap();
		properties.put("modelFileURI", modelStr);
		properties.put("ouputFolderURI", outputStr);
		
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
	
	public final class StaticConfig 
	{
		//private var for internal usage
		private final static String BASE_MWE_DIR = "alma/scxml/mwe";
		public final static String MWE = BASE_MWE_DIR + "/workflow.mwe";
	}
	
}

