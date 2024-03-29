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

/**
 * Class that represents an ENode for the
 * topologicalSort 
 * @author atejeda
 */
public class ENodeElement 
{
	public String job;
	public String [] others;
	
	/**
	 * Constructor
	 * @param job
	 * @param other
	 */
	public ENodeElement(String job, String [] other) 
	{
		this.job = job;
		this.others = other;
	}
}
