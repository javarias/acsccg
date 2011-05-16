package alma.singleComponent.SCXMLStateMachine;

import java.io.IOException;
import java.io.Serializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.lang.reflect.*;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.apache.commons.scxml.model.CustomAction;
import org.apache.commons.scxml.model.ModelException;
import org.apache.commons.scxml.model.SCXML;
import org.apache.commons.scxml.SCXMLExecutor;
import org.apache.commons.scxml.TriggerEvent;
import org.apache.commons.scxml.io.SCXMLParser;
import org.apache.commons.scxml.Context;
import org.apache.commons.scxml.Evaluator;
import org.apache.commons.scxml.env.jexl.JexlContext;
import org.apache.commons.scxml.env.jexl.JexlEvaluator;
import org.apache.commons.scxml.env.SimpleDispatcher;
import org.apache.commons.scxml.env.SimpleErrorReporter;
import org.apache.commons.scxml.env.SimpleSCXMLListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.scxml.model.Transition;
import org.apache.commons.scxml.model.TransitionTarget;
import org.apache.commons.scxml.SCXMLListener;
import org.apache.commons.scxml.invoke.SimpleSCXMLInvoker;

import java.util.Map;

import org.apache.commons.scxml.invoke.Invoker;
import org.apache.commons.scxml.invoke.InvokerException;

import org.apache.commons.scxml.SCInstance;
import org.apache.commons.scxml.TriggerEvent;
import org.apache.commons.scxml.env.SimpleDispatcher;
import org.apache.commons.scxml.env.SimpleErrorHandler;
import org.apache.commons.scxml.env.SimpleErrorReporter;
import org.apache.commons.scxml.env.SimpleSCXMLListener;


import alma.ACSErrTypeCommon.wrappers.AcsJIllegalStateEventEx;
//import alma.singleComponent.SCXMLStateMachine.JavaInvoker;
import alma.acs.container.ContainerServices;
import alma.acs.genfw.runtime.sm.AcsState;
import alma.acs.genfw.runtime.sm.AcsStateChangeListener;
import alma.acs.genfw.runtime.sm.AcsStateUtil;


public class SCXMLStateMachine
{
	//Action Handler deleted
	
	private List<StateChangeSemaphore> m_stateChangeListeners;
	
	private final Logger m_logger;
	
	private final ThreadPoolExecutor sharedActivityExecutor;
	
	private boolean m_verbose = false;

	//======================================================================
	// State Machine Apache Commons SCXML variables
	//======================================================================
		
	// ErrorHandler interface for SAX error handlers. 
	private ErrorHandler errorHandler;
	// The state machine that will drive the instances of this class.
	private SCXML scxml = null;
	// The instance specific SCXML engine.
	private SCXMLExecutor engine;
	// Listener instance that that will control observable entities in the SCXML model
	//private SimpleSCXMLListener listener = new SimpleSCXMLListener();
	private EntryListener listener = new EntryListener();
	// The log
	private Log log;
	// url to the SCXML document
	private URL urlsc;

	private ThreadFactory threadFactory;

	public SCXMLStateMachine(Logger logger, ThreadFactory threadFactory){
		m_stateChangeListeners = new ArrayList<StateChangeSemaphore>();
		this.threadFactory = threadFactory; 
		m_logger = logger;
		// This choice of parameters is copied from the implementation of 
		// Executors.newSingleThreadExecutor which unfortunately hides 
		// in its returned type some methods we need.
		sharedActivityExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),threadFactory);
		setUpStateMachineDoc();
        }
	
	private void setUpStateMachineDoc() {
		// URL instance of the SCXML document
	        CustomAction ca1 = new CustomAction("http://my.custom-actions.domain/CUSTOM", "exitaction", ExitAction.class);
	        CustomAction ca2 = new CustomAction("http://my.custom-actions.domain/CUSTOM", "entryaction", EntryAction.class);
        	List <CustomAction> customActions = new ArrayList <CustomAction> ();
        	customActions.add(ca1);
        	customActions.add(ca2);

		urlsc = this.getClass().getClassLoader().getResource("alma/singleComponent/config/MasterComponent.xml");
		try {
			this.scxml = SCXMLParser.parse(urlsc, errorHandler, customActions);
		}
		catch (IOException ioe) {
			logError(ioe);
	        // logError(ioe);
		} catch (SAXException sae) {
			logError(sae);
		// logError(sae);
		} catch (ModelException me) {
			logError(me);
		// logError(me);
		} catch (Exception e) {
			logError(e);
			e.printStackTrace();
		}
	}

	public void initializeStateMachine() {
		Context rootCtx = new JexlContext();
		Evaluator evaluator = new JexlEvaluator();
		// Engine instance of the SM model
		engine = new SCXMLExecutor(evaluator, new SimpleDispatcher(), new SimpleErrorReporter());
		engine.setStateMachine(this.scxml);
		// ACS variables added to Apache Commons context
		rootCtx.set("threadFactory", (ThreadFactory) this.threadFactory);
		rootCtx.set("superContext", (SCXMLStateMachine) this);
		rootCtx.set("logger", (Logger) m_logger);
		engine.setRootContext(rootCtx);
		engine.registerInvokerClass("java", JavaInvoker.class);
		engine.addListener(scxml, listener);
		// Executing the State Machine Engine
		try {
			 //clase = Class.forName("alma.ACS.MasterComponentImpl.statemachine.JavaInvoker");  
			engine.registerInvokerClass("java", Class.forName("alma.singleComponent.SCXMLStateMachine.JavaInvoker"));
		} catch (ClassNotFoundException cne) {
			logError(cne);
			System.out.println("[SM] ERROR Java invoker class not found " + cne);
		}
		try {
			engine.go();
		} catch (ModelException me) {
			logError(me);
			System.out.println("error init me");

		} catch (Exception e){
			logError(e);
			System.out.println("error init e");
		}
	}

	public synchronized void addAcsStateChangeListener(StateChangeSemaphore listener) {
		m_stateChangeListeners.add(listener);
	}

	//======================================================================
	// delegates incoming events to current state class
	//======================================================================

	public synchronized void initPass1() throws AcsJIllegalStateEventEx {
		fireEvent("initPass1");
	}

	public synchronized void initPass2() throws AcsJIllegalStateEventEx {
		fireEvent("initPass2");
	}

	public synchronized void reinit() throws AcsJIllegalStateEventEx {
		fireEvent("reinit");
	}

	public synchronized void start() throws AcsJIllegalStateEventEx {
		fireEvent("start");
	}

	public synchronized void stop() throws AcsJIllegalStateEventEx {
		fireEvent("stop");
	}

	public synchronized void shutdownPass1() throws AcsJIllegalStateEventEx {
		fireEvent("shutdownPass1");
	}

	public synchronized void shutdownPass2() throws AcsJIllegalStateEventEx {
		fireEvent("shutdownPass2");
	}

	public synchronized void error() throws AcsJIllegalStateEventEx {
		fireEvent("error");
	}

	public void cleanUp() {
		// wake up and terminate the activity worker thread.
		sharedActivityExecutor.shutdownNow();
	}
	
	protected void logError(final Exception exception) {
		m_logger.info("ERROR Exception: " + exception.getMessage());
	}

	public int getCallsToReinit() {
		return ((MyThreadreinitSubsystem) engine.getRootContext().get("myThreadreinitSubsystem")).getCallsToReinit();
	}

	void logTransition(String sourceState, String targetState, String eventName) 
	{
		for (Iterator<StateChangeSemaphore> iter = m_stateChangeListeners.iterator(); iter.hasNext();) {
			StateChangeSemaphore list = iter.next();
			list.stateChangedNotify(sourceState, targetState);
		}
		String msg = "event '" + eventName + "' causes transition from '" + 
		sourceState + "' to '" + targetState + "'.";
		m_logger.info(msg);
	}
	

	 /**
	  * Method that fire a custom State Machine event through a CORBA call defined in a IDL
	  * 
	  * 
	 */
	 public boolean fireEvent(final String event) {
	    //System.out.println("[SM][fireEvent][enter][event: " + event + "][Current state: " +getEngineCurrentState());      
	    TriggerEvent[] evts = {new TriggerEvent(event, TriggerEvent.SIGNAL_EVENT, null)};
	    try {
	         engine.triggerEvents(evts);

	     } catch (ModelException me) {
	        logError(me);
		}
		//System.out.println("[SM][fireEvent][exit][event: " + event + "][Current state: " +getEngineCurrentState());      
	    return engine.getCurrentStatus().isFinal();
	}
	 
	public String getEngineCurrentState() {
	     Set states = engine.getCurrentStatus().getStates();
	     return ((org.apache.commons.scxml.model.State) states.iterator().next()).getId();
	}

		 
	public class EntryListener implements SCXMLListener, Serializable {
		private static final long serialVersionUID = 1L;
		private Log log = LogFactory.getLog(getClass());

		public void onEntry(final TransitionTarget entered){
		}

		public void onTransition(final TransitionTarget from, final TransitionTarget to, final Transition transition){
			synchronized (this){
				logTransition(from.getId(), to.getId(), transition.getEvent());
			}
		}
		
		public void onExit(final TransitionTarget exited){
		}
	} 
}
	