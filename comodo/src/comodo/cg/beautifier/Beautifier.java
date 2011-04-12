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
 * atejeda 		2010-03-05  	Created
 * 
 * $Id$
 * 
 */

/**
 * ATTENTION !
 * This class is extended from org.eclipse.xpand2.output.JavaBeautifier
 * you might see first the license of OAW if you want use this class.
 */

package comodo.cg.beautifier;

import org.eclipse.xpand2.output.FileHandle;
import org.eclipse.xpand2.output.JavaBeautifier;
import org.eclipse.xpand2.output.PostProcessor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.mwe.core.resources.ResourceLoaderFactory;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

/**
 * Common beautifier class to format the
 * generated code using the eclipse code
 * formater
 * @author atejeda
 */
public abstract class Beautifier extends JavaBeautifier
{
	
	/** Singleton code formatter instance. */
	private CodeFormatter codeFormatter;

	/**
	 * Path to the config file for the formatter. See
	 * http://www.peterfriese.de/index.php/2007/05/28/formatting-your-code-using-the-eclipse-code-formatter/
	 * for more information on creating the config file.
	 */
	private String configFile;

	private Properties options;

	/**
	 * Formats the file using Eclipse code formatter. The file must have the
	 * extension '.java'.
	 */
	public void beforeWriteAndClose(final FileHandle info) 
	{
		if (info.getTargetFile().getAbsolutePath() != null
				&& 
					(
					info.getTargetFile().getAbsolutePath().endsWith(".java") || 
					info.getTargetFile().getAbsolutePath().endsWith(".idl")  ||
					info.getTargetFile().getAbsolutePath().endsWith(".cpp")  ||
					info.getTargetFile().getAbsolutePath().endsWith(".py")
					)
			) 
		{

			IDocument doc = new Document(info.getBuffer().toString());
			TextEdit edit = getCodeFormatter().format(CodeFormatter.K_COMPILATION_UNIT, doc.get(), 0,
					doc.get().length(), 0, null);

			// check if text formatted successfully
			if (edit != null) 
			{
				try 
				{
					edit.apply(doc);
					info.setBuffer(new StringBuffer(doc.get()));
				} 
				catch (MalformedTreeException e) {
					//log.warn("Error during code formatting. Illegal code edit tree (" + e.getMessage() + ").");
				} 
				catch (BadLocationException e) 
				{
					//log.warn("Error during code formatting. Bad location (" + e.getMessage() + ").");
				}
			} 
			else 
			{
				//log.warn("File " + info.getAbsolutePath()
				//		+ " could not be formatted. Make sure your template produces legal Java code!");
			}
		}
	}
	
	/**
	 * Returns an instance of the Eclipse code formatter. If the user supplied
	 * the path to a config file, this file will be used to configure the code
	 * formatter. Otherwise we use the default options supplied with Xpand.
	 * 
	 * @return a preconfigured instance of the Eclipse code formatter.
	 */
	private CodeFormatter getCodeFormatter() 
	{
		if (codeFormatter == null) 
		{

			if ( configFile == null ) 
			{
				options = new Properties();
				options.put("org.eclipse.jdt.core.compiler.compliance","1.5");
				options.put("org.eclipse.jdt.core.compiler.codegen.targetPlatform","1.5");
				options.put("org.eclipse.jdt.core.compiler.source","1.5");              
				//log.debug("no config file specified; using the default config file supplied with Xpand: org.eclipse.jdt.core.formatterprefs");
			} 
			else 
			{
				options = readConfig(configFile);
			}
			
			// instantiate the formatter
			codeFormatter = ToolFactory.createCodeFormatter(options);
		}
		return codeFormatter;
	}

	/**
	 * Return a Java Properties file representing the options that are in the
	 * specified config file.
	 */
	private Properties readConfig(String filename) 
	{
		BufferedInputStream stream = null;
		BufferedReader reader = null;
		
		try 
		{
			InputStream is = openStream(filename);
			final Properties formatterOptions = new Properties();
			if ( filename.endsWith(".xml")) 
			{
				Pattern pattern = Pattern.compile("<setting id=\"([^\"]*)\" value=\"([^\"]*)\"\\/>");
				reader = new BufferedReader(new InputStreamReader(is));
				for (String line = reader.readLine(); line != null; line = reader.readLine()) 
				{
					Matcher matcher = pattern.matcher(line);
					if ( matcher.matches() ) 
					{
						formatterOptions.put(matcher.group(1), matcher.group(2));
					}
				}
			}
			else 
			{
			   stream = new BufferedInputStream(is);
			   formatterOptions.load(stream);
			}        
			
		    // add some settings for the compiler options
	        // which are not included in the Eclipse code style settings
	        // to make the code formatter working
	        // see https://bugs.eclipse.org/bugs/show_bug.cgi?id=222736
		   
		   if( formatterOptions.get("org.eclipse.jdt.core.compiler.compliance") == null )
			   formatterOptions.put("org.eclipse.jdt.core.compiler.compliance", "1.5");
		   if( formatterOptions.get("org.eclipse.jdt.core.compiler.codegen.targetPlatform") == null )
			   formatterOptions.put("org.eclipse.jdt.core.compiler.codegen.targetPlatform", "1.5");
		   if( formatterOptions.get("org.eclipse.jdt.core.compiler.source") == null )
			   formatterOptions.put("org.eclipse.jdt.core.compiler.source", "1.5");             
			return formatterOptions;
		} 
		catch (IOException e) 
		{
			//log.warn("Problem reading code formatter config file (" + e.getMessage() + ").");
		} 
		finally 
		{
			if (stream != null) 
			{
				try 
				{
					stream.close();
				} 
				catch (IOException e) 
				{
					/* ignore */
				}
			}
			if ( reader != null ) 
			{
			   try 
			   {
				  reader.close();
			   } 
			   catch (IOException e) 
			   {
					/* ignore */
			   }
			}
		}
		return null;
	}

}

