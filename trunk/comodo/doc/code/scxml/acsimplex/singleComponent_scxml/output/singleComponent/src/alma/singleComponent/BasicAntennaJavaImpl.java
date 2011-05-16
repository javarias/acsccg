/*******************************************************************************
 * ALMA - Atacama Large Millimiter Array
 *
 * (c) European Southern Observatory, 2002
 * Copyright by ESO (in the framework of the ALMA collaboration)
 * and Cosylab 2002, All rights reserved
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA
 *
 * "@(#) $Id$"
 *
 * who                 when                             what
 * ----------------    -----------------------------    ------------------------
 * ACSCCG              Tue Apr 19 00:13:28 CLT 2011    Creation of the file
 * 
 */

package alma.singleComponent;

import java.util.logging.Level;
import java.util.logging.Logger;

import alma.acs.exceptions.AcsJException;
import alma.acs.container.ContainerServices;
import alma.acs.component.ComponentLifecycle;
import alma.acs.component.ComponentLifecycleException;

import alma.ACS.*;
import alma.ACS.ComponentStates;

import alma.ACSErrTypeCommon.CouldntPerformActionEx;
import alma.ACSErrTypeCommon.wrappers.AcsJCouldntPerformActionEx;

import alma.singleComponent.*;
import alma.singleComponent.BasicAntennaInterface;
import alma.singleComponent.BasicAntennaInterfaceOperations;
import alma.singleComponent.SCXMLStateMachine.SCXMLStateMachine;


/////PROTECTED REGION ID(singleComponent.BasicAntennaJavaImpl.Imports) ENABLED START
//add your imports here
/////PROTECTED REGION END

/**
 * @see alma.singleComponentCommon
 * @see alma.singleComponent.BasicAntennaInterface
 * @see alma.singleComponent.BasicAntennaInterfaceOperations
 * @see alma.acs.component.ComponentLifecycle
 * @author ACS Component Code Generator
 * @version $Id$
 */
public class BasicAntennaJavaImpl implements ComponentLifecycle, BasicAntennaInterfaceOperations
{
	private ContainerServices m_containerServices;
	private Logger m_logger;

	// Component SCXML State Machine 
	private SCXMLStateMachine m_stateMachine;
	///// PROTECTED REGION ID(singleComponent.BasicAntennaJavaImpl.VariableDefinition) ENABLED START
	//add your variables here
	///// PROTECTED REGION END
	
	/////////////////////////////////////////////////////////////
	// Implementation of ComponentLifecycle
	/////////////////////////////////////////////////////////////
	public void BasicAntennaJavaImpl()
	{
		//call to method that initilaizes component state machine 
		m_stateMachine = new SCXMLStateMachine(m_logger,  m_containerServices.getThreadFactory());
	}
	
	public void initialize(ContainerServices containerServices)
	{
		m_containerServices = containerServices;
		m_logger = m_containerServices.getLogger();
		m_logger.info("initialize() called...");
		
		///// PROTECTED REGION ID(singleComponent.BasicAntennaJavaImpl.VariableInitialization) ENABLED START
		///// PROTECTED REGION END
		
	}
  
		
	public void execute()
	{
		m_logger.info("execute() called...");
		//call to method that initializes the State Machine
		m_stateMachine.initializeStateMachine();
	}
	
	public void cleanUp()
	{
		m_logger.info("cleanUp() called...");
	}
	
	public void aboutToAbort()
	{
		cleanUp();
		m_logger.info("aboutToAbort() called...");
	}
	
	/////////////////////////////////////////////////////////////
	// Implementation of ACSComponent
	/////////////////////////////////////////////////////////////
	
	public ComponentStates componentState()
	{
		return m_containerServices.getComponentStateManager().getCurrentState();
	}
	
	public String name()
	{
		return m_containerServices.getName();
	}
	
	///// PROTECTED REGION ID(singleComponent.BasicAntennaJavaImpl.Methods) ENABLED START
	//add your methods here
	///// PROTECTED REGION END
	
	/////////////////////////////////////////////////////////////
	// Implementation of BasicAntennaInterfaceOperations
	/////////////////////////////////////////////////////////////
	
	/**
	 * @param pos 
	 * @return void
	 */
	public void setAntennaPosition(position pos)
	{
		///// PROTECTED REGION ID(singleComponent.BasicAntennaJavaImpl.setAntennaPosition) ENABLED START
		//TODO
		///// PROTECTED REGION END  
	}
	
	/**
	 * @return position
	 */
	public position getAntennaPosition()
	{
		///// PROTECTED REGION ID(singleComponent.BasicAntennaJavaImpl.getAntennaPosition) ENABLED START
		return new position();
		///// PROTECTED REGION END  
	}
	
}
