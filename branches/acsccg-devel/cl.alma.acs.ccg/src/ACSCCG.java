import java.io.FileNotFoundException;

import org.eclipse.emf.mwe.utils.DirectoryCleaner;

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
		Options opt = new Options();;	
		
		try {
			
			opt.addOption("h", false, "     Print help for this application");
			opt.addOption("a", false, "     About this application");
			opt.addOption("m", true, "Model file path, i.e.: /my/very/path/to/MyProject.uml");
			opt.addOption("p", true, "  Profile UML path, i.e.: /my/very/path/to/AlmaGenerator.profile.uml");
			opt.addOption("o", true, "The data source to use");
			opt.addOption("c", true, " True if want to clean the output folder.. ! BEWARE ! will erase all from folder an parent folder !");
			
			BasicParser parser = new BasicParser();
			CommandLine cl = parser.parse(opt, args);
			
			if ( cl.hasOption('h') ) {
				about();
				HelpFormatter f = new HelpFormatter();
				f.printHelp("java -jar ACSCCG.jar -m /my/very/path/to/MyProject.uml -p  /my/very/path/to/Alma.profile.uml -o /user/home/MyProject/generated/folder [options]\n", opt);
				
			} else if( 
					(!cl.getOptionValue("m").isEmpty() || 
					!cl.getOptionValue("p").isEmpty() || 
					!cl.getOptionValue("o").isEmpty()) 
					&&
					(cl.hasOption('m') && 
					cl.hasOption('p') && 
					cl.hasOption('o')) ){
				
				modelPath = cl.getOptionValue("m");
				profilePath = cl.getOptionValue("p");
				outputPath = cl.getOptionValue("o");
				
				System.out.println("Model:"+cl.getOptionValue("m"));
				System.out.println("Profile: "+cl.getOptionValue("p"));
				System.out.println("Output folder: "+cl.getOptionValue("o"));
				System.out.println("");
				System.out.println("Generating the code...");
				System.out.println("");
				
				//Calling to the Java strategy...
				new ContextCodeGeneration(new CodeJavaGeneration(new VOGenerator(modelPath, profilePath, outputPath))).generateACSCode();
				
				//Calling to the Cpp strategy..
				//new ContextCodeGeneration(new CodeCppGeneration(new VOGenerator(modelPath, profilePath, outputPath))).generateACSCode();
				
				//Calling to the Python strategy..
				//new ContextCodeGeneration(new CodePythonGeneration(new VOGenerator(modelPath, profilePath, outputPath))).generateACSCode();
	
			} 
		} catch (ParseException e) {
			about();
			HelpFormatter f = new HelpFormatter();
			f.printHelp("java -jar ACSCCG.jar -model /my/very/path/to/MyProject.uml -profile  /my/very/path/to/Alma.profile.uml -output /user/home/MyProject/generated/folder [options]\n", opt);
			System.out.println("\n An Error has been found, see the help...");
			System.out.println("                                run: java -jar ACSCCG.jar -h");
		}
		
	}
	
	/*
	 *  Print a little info about the generator...
	 */
	public static void about() {
		System.out.println("");
		System.out.println("ACSCodeGenerator");
		System.out.println("Compilation 100615");
		System.out.println("");
	    System.out.println("http://code.google.com/p/acsccg/");
		System.out.println("- Alexis Tejeda <alexis.tejeda@gmail.com>");
		System.out.println("- Nicolas Troncoso <ntroncos@alma.cl>");
		System.out.println("");
	}
}
