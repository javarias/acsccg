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
 * atejeda 		2010-00-00  	Created
 * 
 * $Id$
 */

package comodo.cg.util.xtend;

import java.util.Date;
import java.util.Vector;

import comodo.cg.util.BaseStaticConfig;

/**
 * Java extension for xtend functions
 * @author atejeda
 */
public class JavaExtension 
{

	/**
	 * The function return the full actual date
	 * @return String - the actual full date
	 */
	public final static String getFullActualDate()
	{
		return new Date().toString();
	}
	
	/**
	 * The function return the date formated for the comments
	 * @return String - the actual date for the comments
	 */
	@SuppressWarnings("deprecation")
	public final static String getCommentDate()
	{
		Date date = new Date();
		String commentDate;
		commentDate = String.valueOf(date.getYear());
		commentDate += "-";
		commentDate +=  String.valueOf(date.getMonth());
		commentDate += "-";
		commentDate +=  String.valueOf(date.getDate());
		commentDate += " ";
		commentDate += String.valueOf(date.getHours());
		commentDate += ":";
		commentDate += String.valueOf(date.getMinutes());
		commentDate += ":";
		commentDate += String.valueOf(date.getSeconds());
		return commentDate;
	}
	
	/**
	 * Get a list of comment wrapped, 10 word per each line
	 * @return Vector<String> a list of comments wrapped
	 */
	public final static Vector<String> getCommentsWrapped(String comment)
	{
		String tmpLine="";
		int tmpCounter = 0;
		String[] wordArray = comment.split(" ");
		Vector<String> commentsWrapped = new Vector<String>();
		
		for( String word : wordArray)
		{
				tmpLine += " "; 
				tmpLine += word; 
				tmpCounter++;
				
			if(tmpCounter > BaseStaticConfig.WORD_WRAP_MAX)
			{
				commentsWrapped.add(tmpLine);
				tmpLine = "";
				tmpCounter = 0;
			}
		}
		
		return commentsWrapped;
	}
	
	/**
	 * Get the ID string for cvs, svn, etc.. general 
	 * control versions id propset
	 * @return  String
	 */
	public static String getPropSet(String prop)
	{
		//separate in order to not be replaced
		String ppSufix = "$";
		return ppSufix+prop+ppSufix;
	}
	
	/**
	 * Return a string with his first chart uppercased
	 * @param word
	 * @return
	 */
	public static String upperFirstChar(String word)
	{
		return word.substring(0,1).toUpperCase()+word.substring(1);
	}
}
