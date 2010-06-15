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
		
		try {
			
			Options opt = new Options();
			
			opt.addOption("h", false,"Print help for the generator");
			opt.addOption("m", true, "Specify the model input XMI");
			opt.addOption("p", true, "Specify the profile of the model");
			opt.addOption("o", true, "Specify the output path for the code generated");
			opt.addOption("n", true, "The prefix name of the pacacke to generate");
			
			BasicParser parser = new BasicParser();
			CommandLine cl = parser.parse(opt, args);
			
			if ( cl.hasOption('h') ) {
                HelpFormatter f = new HelpFormatter();
                f.printHelp("OptionsTip", opt);
            }
			
			//args[0]
			modelPath =  "/home/atejeda/Desktop/ACSCodeGeneration/Models/MARS/xmi/MARS.uml";
			//args[1]
			profilePath = "/home/atejeda/Desktop/ACSCodeGeneration/Models/MARS/xmi/AlmaGenerator.profile.uml";
			//args[2]
			outputPath = "/home/atejeda/Desktop/ACSCodeGeneration/Models/MARS/generated/";
		} catch(Exception e) {
			return;
		}
		
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("ACSCodeGenerator");
		System.out.println("");
		
		//Calling to the Java strategy...
		new ContextCodeGeneration(new CodeJavaGeneration(new VOGenerator(modelPath, profilePath, outputPath))).generateACSCode();
		
		//Calling to the Cpp strategy..
		//new ContextCodeGeneration(new CodeCppGeneration(new VOGenerator(modelPath, profilePath, outputPath))).generateACSCode();
		
		//Calling to the Python strategy..
		//new ContextCodeGeneration(new CodePythonGeneration(new VOGenerator(modelPath, profilePath, outputPath))).generateACSCode();
	}
	
	public static void showHelp() {
	}
}


/*
http://commons.apache.org/cli/
http://people.csail.mit.edu/milch/blog/apidocs/common/cmdline/Parser.html
public class OptionsTip {
    public static void main(String args[]) {
        try {
            Options opt = new Options();

            opt.addOption("h", false, "Print help for this application");
            opt.addOption("u", true, "The username to use");
            opt.addOption("dsn", true, "The data source to use");

            BasicParser parser = new BasicParser();
            CommandLine cl = parser.parse(opt, args);

            if ( cl.hasOption('h') ) {
                HelpFormatter f = new HelpFormatter();
                f.printHelp("OptionsTip", opt);
            }
            else {
                System.out.println(cl.getOptionValue("u"));
                System.out.println(cl.getOptionValue("dsn"));
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
 
*/
