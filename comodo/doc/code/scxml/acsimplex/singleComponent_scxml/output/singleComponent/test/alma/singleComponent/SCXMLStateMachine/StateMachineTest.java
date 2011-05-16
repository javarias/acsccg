/*
 *    ALMA - Atacama Large Millimiter Array
 *    (c) European Southern Observatory, 2002
 *    Copyright by ESO (in the framework of the ALMA collaboration),
 *    All rights reserved
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public
 *    License along with this library; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 *    MA 02111-1307  USA
 */
package alma.singleComponent.SCXMLStateMachine;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PipedInputStream;
import java.io.PrintStream;
import java.util.logging.Logger;

import junit.framework.TestCase;

import alma.singleComponent.SCXMLStateMachine.StateChangeSemaphore;
import alma.ACSErrTypeCommon.wrappers.AcsJIllegalStateEventEx;
import alma.acs.concurrent.DaemonThreadFactory;
import alma.acs.genfw.runtime.sm.AcsState;
import alma.acs.genfw.runtime.sm.AcsStateActionException;
import alma.acs.genfw.runtime.sm.AcsStateChangeListener;
import alma.acs.genfw.runtime.sm.AcsStateUtil;
import alma.acs.logging.AcsLogger;


/**
 * Tests the state machine from <code>alma.ACS.MasterComponentImpl.statemachine</code>
 * without the Master component on top of it. No running ACS needed.
 *  
 * @author lmartinez cmorales
 * created May 13, 2011 
 */
public class StateMachineTest extends TestCase 
{
    private SCXMLStateMachine m_context;
    //private DummyActionImpl m_actionImpl;
	private Logger m_logger;
	
	private MyStateChangeSemaphore m_sync;
	FileOutputStream fos;
	static int i = 0;
	
	
    public void setUp() {
    	i++;
    	try {
		fos = new FileOutputStream("log"+i+".txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	PrintStream ps = new PrintStream(fos);
    	System.setOut(ps);
    	m_logger = AcsLogger.createUnconfiguredLogger("StateMachineTest", null);
    	//m_actionImpl = new DummyActionImpl(m_logger);
        m_context = new SCXMLStateMachine( m_logger, new DaemonThreadFactory());
        m_sync = new MyStateChangeSemaphore(m_logger);
        m_context.addAcsStateChangeListener(m_sync);
	m_context.initializeStateMachine();
    }

	/**
	 * @see alma.ACS.MasterComponentImpl.statemachine.AcsStateChangeListenerstateChangedNotify(alma.ACS.MasterComponentImpl.statemachine.AcsState[], alma.ACS.MasterComponentImpl.statemachine.AcsState[])
	 */
	
    public void testLegalLifecycle() throws Exception 
	{
	String actualPath = m_context.getEngineCurrentState();
	final int n = 1;
	m_logger.info("============ starting testLegalLifecycle with " + n + " iterations ===========");
	for (int i=0; i< n; i++) {
	        assertStateHierarchy("Available::Offline::Shutdown");
	        m_logger.info("---> Event initPass1 will be sent.");
	        m_sync.reset();	
	        m_context.initPass1();
	        m_sync.waitForStateChanges(2);
	        assertStateHierarchy("Available::Offline::PreInitialized");
	        m_logger.info("---> Event initPass2 will be sent.");
	        m_sync.reset();

	        m_context.initPass2();
	        m_sync.waitForStateChanges(2); 
	        assertStateHierarchy("Available::Online");
	        
	        m_logger.info("---> Event start will be sent.");
	        m_context.start();
	        assertStateHierarchy("Available::Operational");

	        m_logger.info("---> Event start will be sent again.");
	        m_context.start();
	        assertStateHierarchy("Available::Operational");

	        m_logger.info("---> Event stop will be sent.");
	        m_context.stop();
	        assertStateHierarchy("Available::Online");
	        
	        m_logger.info("---> Event shutdownPass1 will be sent.");
	        m_sync.reset();
	        m_context.shutdownPass1();
	        m_sync.waitForStateChanges(2); 
	        assertStateHierarchy("Available::Offline::Preshutdown");
	        
	        m_logger.info("---> Event shutdownPass2 will be sent.");
	        m_sync.reset();
	        m_context.shutdownPass2();
	        m_sync.waitForStateChanges(2); 
	        assertStateHierarchy("Available::Offline::Shutdown");

		}        
        m_logger.info("============ testLegalLifecycle successful! ===========\n");
	}

	private void assertStateHierarchy(String expectedPath) {
		String actualPath = m_context.getEngineCurrentState();
		assertEquals("current state is not as expected!", expectedPath, actualPath);
	}

    private static class MyStateChangeSemaphore extends StateChangeSemaphore{
	    	MyStateChangeSemaphore(Logger logger) {
    		super(logger);
    	}
	
    }
}
