import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

import cl.alma.acs.ccg.strategy.CodeJavaGeneration;
import cl.alma.acs.ccg.strategy.ContextCodeGeneration;
import cl.alma.acs.ccg.vo.VOGenerator;

public class ACSCCG {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String modelPath, profilePath, outputPath;	
		
		if(args.length < 3) {
			System.out.println("Usage: java -jar ACSCCG model-path profile-path output-path");
			return;
		}
		
		//args[0]
		//modelPath =  "/home/atejeda/Desktop/Models/example6/xmi/NotificationChannel.uml";
		//args[1]
		//profilePath = "/home/atejeda/Desktop/Models/example6/xmi/AlmaGenerator.profile.uml";
		//args[2]
		//outputPath = "/home/atejeda/Desktop/Models/example6/generated/";
		
		modelPath =  args[0];
		profilePath = args[1];
		outputPath = args[2];
			
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("ACSCodeGenerator");
		System.out.println("Compilation 100615");
		System.out.println("");
		System.out.println("");
		System.out.println("ACSCodeGenerator");
		System.out.println("Compilation 100615");
	    System.out.println("http://code.google.com/p/acsccg/");
		System.out.println(" Alexis Tejeda <alexis.tejeda@gmail.com>");
		System.out.println(" Nicolas Troncoso <ntroncos@alma.cl>");
		
		//Calling to the Java strategy...
		new ContextCodeGeneration(new CodeJavaGeneration(new VOGenerator(modelPath, profilePath, outputPath))).generateACSCode();
		
		//Calling to the Cpp strategy..
		//new ContextCodeGeneration(new CodeCppGeneration(new VOGenerator(modelPath, profilePath, outputPath))).generateACSCode();
		
		//Calling to the Python strategy..
		//new ContextCodeGeneration(new CodePythonGeneration(new VOGenerator(modelPath, profilePath, outputPath))).generateACSCode();
	}
}
