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
package comodo.cg.vo;

import java.io.File;
import java.io.IOException;

/**
 * Value Object for the model and profile
 * @author atejeda
 */
public class VOGenerator 
{

	private String model;
	private String profile;
	private String output;
	private String acspackage;
	
	private final static  String protocol = "file:///";

	/**
	 * Constructor
	 * @param model
	 * @param profile
	 * @param output
	 */
	public VOGenerator(String model, String profile, String output, String acspacakge) 
	{
		this.model = model;
		this.profile = profile;
		this.output = output;
		this.acspackage = acspacakge;
	}
	
	/**
	 * Constructor
	 * @param model
	 * @param profile
	 * @param output
	 */
	public VOGenerator(String model, String profile, String output) 
	{
		this.model = model;
		this.profile = profile;
		this.output = output;
	}
	
	/**
	 * Constructor
	 * @param model
	 * @param profile
	 */
	public VOGenerator(String model, String profile) 
	{
		this.model = model;
		this.profile = profile;
	}

	/**
	 * Return the model path
	 * @return String
	 */
	public String getModel() 
	{
		return model;
	}

	/**
	 * Set the model path
	 * @param model
	 */
	public void setModel(String model) 
	{
		this.model = model;
	}

	/**
	 * Get the profile
	 * @return the profile
	 */
	public String getProfile() 
	{
		return profile;
	}

	/**
	 * Set the profile path
	 * @param profile
	 */
	public void setProfile(String profile) 
	{
		this.profile = profile;
	}

	/**
	 * Get output path
	 * @return String
	 */
	public String getOutput() 
	{
		return output;
	}

	/**
	 * Set the output path
	 * @param output
	 */
	public void setOutput(String output) 
	{
		this.output = output;
	}
	
	/**
	 * Set the package name
	 * @return
	 */
	public String getAcspackage() 
	{
		return acspackage;
	}

	/**
	 * Get the package name
	 * @param acspackage
	 */
	public void setAcspackage(String acspackage) 
	{
		this.acspackage = acspackage;
	}
	
	/**
	 * Get the model formated for internal EMF environment
	 * @return String
	 */
	public String getWellFormedModel() 
	{
		File profileFileObj = new File(this.model);
		/*
	    try {
			System.out.println(profileFileObj.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		try {
			return protocol+profileFileObj.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Get the model formated for internal EMF environment
	 * @return String
	 */
	public String getWellFormedProfile()
	{
		File modelFileObj = new File(this.profile);
		/*
		try {
			System.out.println(modelFileObj.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		try {
			return protocol+modelFileObj.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
