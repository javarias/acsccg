package alma.singleComponent.SCXMLStateMachine;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.io.Serializable;
import java.io.IOException;
import java.util.concurrent.ThreadFactory;

import org.apache.commons.scxml.SCInstance;
import org.apache.commons.scxml.TriggerEvent;
import org.apache.commons.scxml.model.ModelException;
import org.apache.commons.scxml.invoke.Invoker;
import org.apache.commons.scxml.invoke.InvokerException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import alma.acs.container.ContainerServices;
import alma.acs.genfw.runtime.sm.AcsDoActivity;
import alma.acs.genfw.runtime.sm.AcsStateActionException;


/**
 * Class that implements Apache Commons Invoker Interface that 
 * defines the interaction (bi-directional events pipe) between 
 * the parent State Machine and the activities that we will define
 */

public  class JavaInvoker implements Invoker
{
	 private Log log = LogFactory.getLog(getClass());
     private String parentStateId;
     private SCInstance parentSCInstance; 
     private AcsDoActivity m_doActivity;
     private ThreadFactory _threadFactory;
     public  Thread th;
     private SCXMLStateMachine _stateMachine;

 
     public static MyThreadinitSubsysPass1 myThreadinitSubsysPass1;
     public static MyThreadinitSubsysPass2 myThreadinitSubsysPass2;
     public static MyThreadreinitSubsystem myThreadreinitSubsystem;
     public static MyThreadshutdownSubsysPass1 myThreadshutdownSubsysPass1;
     public static MyThreadshutdownSubsysPass2 myThreadshutdownSubsysPass2;

     public HashMap<String, Integer> map;
     
     public JavaInvoker()
     {
 		map = new HashMap();
    	map.put("MyThreadinitSubsysPass1.java", new Integer(1));
        map.put("MyThreadinitSubsysPass2.java", new Integer(2));
        map.put("MyThreadreinitSubsystem.java", new Integer(3));
        map.put("MyThreadshutdownSubsysPass1.java", new Integer(4));
        map.put("MyThreadshutdownSubsysPass2.java", new Integer(5));

     }     

     /**
      * Set the state ID of the owning state for the <invoke>. Implementations 
      * must use this ID for constructing the event name for the special "done"
      *  event (and optionally, for other event names as well).
      *
      * @param parentStateId - The ID of the parent state.
      */
     public void setParentStateId(final String parentStateId) {
         this.parentStateId = parentStateId;
     }

     /**
      * Set the "context" of the parent state machine, which provides the channel.
      *
      * @param scInstance - The "context" of the parent state machine.
      */
     public void setSCInstance(final SCInstance scInstance) {
         this.parentSCInstance = scInstance;
         _threadFactory = (ThreadFactory) this.parentSCInstance.getRootContext().get("threadFactory");
         _stateMachine = (SCXMLStateMachine) this.parentSCInstance.getRootContext().get("superContext");

     }

     /**
      * Begin this invocation.
      *
      * @param source - The source URI of the activity being invoked.
      * @param params - The <param> values
      */
   public void invoke(final String source, final Map params) throws InvokerException {
  	 String src = getSrcName(source);
    	 switch((Integer)map.get(src)){
    	 	case 1:
    	 		try {
    	 			myThreadinitSubsysPass1 = new MyThreadinitSubsysPass1(this._stateMachine);
    	            //parentSCInstance.getRootContext().set("myThreadinitSubsysPass1", myThreadinitSubsysPass1);
    	            th = _threadFactory.newThread(myThreadinitSubsysPass1);
    	            th.start();
    	            th.join();
    	            myThreadinitSubsysPass1.stopping();
    	 		} catch (Exception e) {
    	 			e.printStackTrace();
    	 		}
    	 		break;
    	 	case 2:
    	 		try {
    	 			myThreadinitSubsysPass2 = new MyThreadinitSubsysPass2(this._stateMachine);
    	            //parentSCInstance.getRootContext().set("myThreadinitSubsysPass1", myThreadinitSubsysPass1);
    	            th = _threadFactory.newThread(myThreadinitSubsysPass2);
    	            th.start();
    	            th.join();
    	            myThreadinitSubsysPass2.stopping();
    	 		} catch (Exception e) {
    	 			e.printStackTrace();
    	 		}
    	 		break;
    	 	case 3:
    	 		try {
    	 			myThreadreinitSubsystem = new MyThreadreinitSubsystem(this._stateMachine);
    	            //parentSCInstance.getRootContext().set("myThreadreinitSubsystem", myThreadreinitSubsystem);
    	 			th = _threadFactory.newThread(myThreadreinitSubsystem);
    	            th.start();
    	            th.join();
    	            myThreadreinitSubsystem.stopping();
    	 		} catch (Exception e) {
    	 			e.printStackTrace();
    	 		}
    	 		break;
    	 	case 4:
    	 		try {
    	 			myThreadshutdownSubsysPass1 = new MyThreadshutdownSubsysPass1(this._stateMachine);
    	            //parentSCInstance.getRootContext().set("myThreadshutdownSubsysPass1", myThreadshutdownSubsysPass1);
    	            th =_threadFactory.newThread(myThreadshutdownSubsysPass1);
    	            th.start();
    	            th.join();
    	            myThreadshutdownSubsysPass1.stopping();
    	 		} catch (Exception e) {
    	 			e.printStackTrace();
    	 		}
    	 		break;
    	 	case 5:
    	 		try {
    	 			myThreadshutdownSubsysPass2 = new MyThreadshutdownSubsysPass2(this._stateMachine);
    	            //parentSCInstance.getRootContext().set("myThreadshutdownSubsysPass2", myThreadshutdownSubsysPass2);
    	            th = _threadFactory.newThread(myThreadshutdownSubsysPass2);
    	            th.start();
    	            th.join();
    	            myThreadshutdownSubsysPass2.stopping();
    	 		} catch (Exception e) {
    	 			e.printStackTrace();
    	 		}
    	 		break;
    	 	}
     }

     /**
      * Forwards the events triggered on the parent state machine on to the invoked activity.
      *
      * @param evts - an array of external events which triggered during the last time quantum
      */
     public void parentEvents(TriggerEvent[] events) throws InvokerException {

     }
     /**
      * Cancel this invocation.
      *
      */
     public void cancel() throws InvokerException {
     }
     
     public String getSrcName(String src) {
    	 String[] tmp = src.split("/");
      	 return tmp[tmp.length-1];
     }
 }






