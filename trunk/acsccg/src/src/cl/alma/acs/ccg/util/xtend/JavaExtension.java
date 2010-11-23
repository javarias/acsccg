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
 *  who       			when      			what
 * --------				--------					----------------------------------------------
 * atejeda 			2010-00-00  		Created
 * 
 */

package cl.alma.acs.ccg.util.xtend;

import java.util.Date;
import java.util.List;
import java.util.Vector;

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
	 * Get a list of comment wrapped, 15 word per each line
	 * @return Vector<String> a list of comments wrapped
	 */
	public final static Vector<String> getCommentsWrapped(String comment)
	{
		// set the limits of words
		int wordLimit = 10;
		
		String tmpLine="";
		int tmpCounter = 0;
		String[] wordArray = comment.split(" ");
		Vector<String> commentsWrapped = new Vector<String>();
		
		for( String word : wordArray)
		{
				tmpLine += " "; 
				tmpLine += word; 
				tmpCounter++;
				
			if(tmpCounter > wordLimit)
			{
				commentsWrapped.add(tmpLine);
				tmpLine = "";
				tmpCounter = 0;
			}
		}
		
		return commentsWrapped;
	}
	
}
