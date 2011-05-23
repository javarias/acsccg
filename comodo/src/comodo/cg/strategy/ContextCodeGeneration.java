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

package comodo.cg.strategy;

/**
 * Context code generation
 * see the strategy desing pattern
 * @author atejeda
 */
public class ContextCodeGeneration 
{
	private ICodeGenerationStrategy __strategy;
	
	/**
	 * Constructor
	 * by default set the strategy to use
	 * @param strategy
	 */
	public ContextCodeGeneration(ICodeGenerationStrategy strategy)
	{
		setStrategy(strategy);
	}

	/**
	 * Set the startegy to use (ICodeGenerationStrategy)
	 * (java, python or c++ startegy)
	 * @see ICodeGenerationStrategy
	 * @param strategy
	 */
	public void setStrategy(ICodeGenerationStrategy strategy) 
	{
		__strategy = strategy;
	}

	/**
	 * Return the strategy interface object (java, python or c++ startegy)
	 * @return ICodeGenerationStrategy
	 * @see ICodeGenerationStrategy
	 */
	public ICodeGenerationStrategy getStrategy() 
	{
		return __strategy;
	}
	
	/**
	 * Function that generates the code
	 */
	public void generateACSCode() 
	{
		 __strategy.generateCode();
	}

}
