 /*

  * Lecensed to the Apache Software Foundation (ASF) under one or more
  * contributor license agreements.  See the NOTICE file distributed with
  * this work for additional information regarding copyright ownership.
  * The ASF licenses this file to You under the Apache License, Version 2.0
  * (the "License"); you may not use this file except in compliance with
  * the License.  You may obtain a copy of the License at
  *
  *     http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */

package alma.STOPWATCH_MODULE.StopWatchImpl;

import alma.acs.component.ComponentLifecycle;
import alma.acs.component.ComponentLifecycleException;
import alma.acs.container.ContainerServices;
import alma.ACS.ComponentStates;
import alma.STOPWATCH_MODULE.StopWatchOperations;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.io.IOException;
import java.net.URL;

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

/**
 * A SCXML document driven stop watch.
 *
 * Using SCXML makes the StopWatch class simplistic; you are neither
 * managing the stopwatch "lifecycle" nor coding any "transitions",
 * that information is pulled in straight from the behavioral model
 * of the stop watch, which is encapsulated in the SCXML document
 * the constructor points to (which in turn may be generated straight
 * from the UML model).
 */

	public class StopWatchImpl implements StopWatchOperations, ComponentLifecycle, Runnable
	{
		
    /////////////////////////////////////////////////////////////
    // State Machine Apache Commons SCXML variables
    /////////////////////////////////////////////////////////////
		
    // ErrorHandler interface for SAX error handlers. 
    private ErrorHandler errorHandler;
    // The state machine that will drive the instances of this class.
    private SCXML scxml = null;
    // The instance specific SCXML engine.
    private SCXMLExecutor engine;
    // Listener instance that that will control observable entities in the SCXML model
    private SimpleSCXMLListener listener = new SimpleSCXMLListener();
    // The log
    private Log log;
    // url to the SCXML document
    private URL urlsc;

    /////////////////////////////////////////////////////////////
    // ACS component variables
    /////////////////////////////////////////////////////////////

    private ContainerServices _containerServices;
    private Logger _logger;

    /////////////////////////////////////////////////////////////
    // Implementation of StateMachine
    /////////////////////////////////////////////////////////////

    /**
     * Class constructor
     *
     */
    public StopWatchImpl(){
	log = LogFactory.getLog(this.getClass());
	setUpStateMachine();
    }

    /**
     * Set up the custom actions and parses the SCXML document. 
     *
     */
    private void setUpStateMachine() {
        // Creation of custom Actions instances
        CustomAction ca1 = new CustomAction("http://my.custom-actions.domain/CUSTOM1", "resetDisplay", ResetDisplay.class);
        CustomAction ca2 = new CustomAction("http://my.custom-actions.domain/CUSTOM2", "freezeDisplay", FreezeDisplay.class);
        CustomAction ca3 = new CustomAction("http://my.custom-actions.domain/CUSTOM3", "stopTimer", StopTimer.class);
        // List of custom Actions 
        List <CustomAction> customActions = new ArrayList <CustomAction> ();
        customActions.add(ca1);
        customActions.add(ca2);
        customActions.add(ca3);
        // URL instance of the SCXML document
        urlsc = this.getClass().getClassLoader().getResource("alma/STOPWATCH_MODULE/StopWatchImpl/config/stopwatch.xml");
        //parsing the document
        try {
            scxml = SCXMLParser.parse(urlsc, errorHandler, customActions);
            }
        catch (IOException ioe) {
            logError(ioe);
        } catch (SAXException sae) {
            logError(sae);
        } catch (ModelException me) {
            logError(me);
        } catch (Exception e) {
        }
    }

    
    /**
     * Instantiate and initialize the underlying executor instance.
     *
     * @param stateMachine The state machine
     * @param rootCtx The root context
     * @param evaluator The expression evaluator
     */
    
    private void initializeStateMachine(final SCXML stateMachine, final Context rootCtx, final Evaluator evaluator) {
    	// Engine instance of the SM model
    	engine = new SCXMLExecutor(evaluator, new SimpleDispatcher(), new SimpleErrorReporter());
        engine.setStateMachine(stateMachine);
        // ACS variables added to Apache Commons context
	    rootCtx.set("containerServices", (ContainerServices) _containerServices);
	    rootCtx.set("logger", (Logger) _logger);
        engine.setRootContext(rootCtx);
        engine.addListener(stateMachine, listener);
	    engine.registerInvokerClass("java", JavaInvoker.class);
        // Executing the State Machine Engine 
	    try {
            engine.go();
            } catch (ModelException me) {
            } catch (Exception e){
               e.printStackTrace();
        }
    }

    /**
    * Utility method for logging error.
    *
    * @param exception The exception leading to this error condition.
    */
    protected void logError(final Exception exception) {
        if (log.isErrorEnabled()) {
            log.error(exception.getMessage(), exception);
        }
    }

    /////////////////////////////////////////////////////////////
    // Implementation of ComponentLifecycle
    /////////////////////////////////////////////////////////////
    
    /**
     * Initialize the component. Is executed by the container after creating an instance of the component.
     *
     * @param containerServices 
     */
    public void initialize(ContainerServices containerServices) throws ComponentLifecycleException {
         _containerServices = containerServices;
         _logger = _containerServices.getLogger();
         _logger.info("initialize() called...");
    }
    
    /**
     * Execute the component. Is called by the container after initialize() has returned.
     *
     */
    public void execute() {
         _logger.info("execute() called...");
         // Calling the method that initialize the SM
         initializeStateMachine(scxml, new JexlContext(), new JexlEvaluator());
    }
    
    /**
     * This method will be called by the container when the component is getting dismissed by the ACS Manager 
     *
     */
    public void cleanUp() {
         _logger.info("cleanUp() called");
    }
    
    /**
     * This method may be called asynchronously when the container must shut down without waiting for proper termination
     * 
     */
    public void aboutToAbort() {
         cleanUp();
         _logger.info("managed to abort...");
    }

    /////////////////////////////////////////////////////////////
    // Implementation of ACSComponent
    /////////////////////////////////////////////////////////////
    
    /**
     * Method that allows container manage the component is a default way
     * 
     */
    public ComponentStates componentState() {
    	return _containerServices.getComponentStateManager().getCurrentState();
    }
    
    /**
     * Method that returns components name
     * 
     */
    public String name() {
    	return _containerServices.getName();
    }

    public void run() {
	_logger.info("run...");
    }

    /////////////////////////////////////////////////////////////
    // CORBA Call Methods (defined in IDL)
    /////////////////////////////////////////////////////////////

    /**
     * Method that returns the time displayed by the stopwatch
     * 
     */
    public String getDisplay() {  
        return ((StartTimer) engine.getRootContext().get("tictac")).getDisplay();
    }

    /**
     * Method that returns the State Machine current state
     * 
     */
    public String getCurrentState() {
        Set states = engine.getCurrentStatus().getStates();
        return ((org.apache.commons.scxml.model.State) states.iterator().next()).getId();
    }

    /**
     * Method that fire a custom State Machine event through a CORBA call defined in a IDL
     * 
     */
    public boolean fireEvent(final String event) {
         TriggerEvent[] evts = {new TriggerEvent(event, TriggerEvent.SIGNAL_EVENT, null)};
         try {
             engine.triggerEvents(evts);
         } catch (ModelException me) {
             logError(me);
         }
         return engine.getCurrentStatus().isFinal();
     }

    /**
     * Method that fire the "watch.start" event of the state machine model
     * 
     */
    public boolean start() {
         TriggerEvent[] evts = {new TriggerEvent("watch.start", TriggerEvent.SIGNAL_EVENT, null)};
         try {
             engine.triggerEvents(evts);
         } catch (ModelException me) {
             logError(me);
         }
         return engine.getCurrentStatus().isFinal();
     }

    /**
     * Method that fire the "watch.stop" event of the state machine model
     * 
     */
    public boolean stop() {
         TriggerEvent[] evts = {new TriggerEvent("watch.stop", TriggerEvent.SIGNAL_EVENT, null)};
         try {
             engine.triggerEvents(evts);
         } catch (ModelException me) {
             logError(me);
         }
         return engine.getCurrentStatus().isFinal();
     }

    /**
     * Method that fire the "watch.split" event of the state machine model
     * 
     */
    public boolean split() {
         TriggerEvent[] evts = {new TriggerEvent("watch.split", TriggerEvent.SIGNAL_EVENT, null)};
         try {
             engine.triggerEvents(evts);
         } catch (ModelException me) {
             logError(me);
         }
         return engine.getCurrentStatus().isFinal();
     }

    /**
     * Method that fire the "unsplit" event of the state machine model
     * 
     */
     public boolean unsplit() {
         TriggerEvent[] evts = {new TriggerEvent("watch.unsplit", TriggerEvent.SIGNAL_EVENT, null)};
         try {
             engine.triggerEvents(evts);
         } catch (ModelException me) {
             logError(me);
         }
         return engine.getCurrentStatus().isFinal();
     }
     
     /**
      * Method that fire the "watch.reset" event of the state machine model
      * 
      */
     public boolean reset() {
         TriggerEvent[] evts = {new TriggerEvent("watch.reset", TriggerEvent.SIGNAL_EVENT, null)};
         try {
             engine.triggerEvents(evts);
         } catch (ModelException me) {
             logError(me);
         }
         return engine.getCurrentStatus().isFinal();
     }
}
