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


package alma.acsccg.util.xtend;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.uml2.uml.Property;

/**
 * Lots of sorting functions
 * @author atejeda
 */
public class ElementSort 
{
	/**
	 * Get a list of non repeated elements
	 * @param elementList
	 * @return
	 */
	public static List<Property> getNREList(List<Property> elementList)
	{
		List<Property>  nreList = new ArrayList<Property> (elementList);
		nreList.removeAll(nreList);
	
		//add the first element into the list
		if(!elementList.isEmpty())
			nreList.add(elementList.get(0));

		for(int i=0; i<elementList.size();i++)
		{
			for(int j=0; j<nreList.size(); j++)
			{
				boolean exists = false;
				exists = nreList.get(j).getName().equals(elementList.get(i).getName());
				if(!exists) nreList.add(elementList.get(i));
			}
		}	

		return nreList;
	}
}
