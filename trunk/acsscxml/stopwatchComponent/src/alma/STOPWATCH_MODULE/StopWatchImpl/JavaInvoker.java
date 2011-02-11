package alma.STOPWATCH_MODULE.StopWatchImpl;

import java.util.Map;
import alma.acs.container.ContainerServices;

import org.apache.commons.scxml.SCInstance;
import org.apache.commons.scxml.TriggerEvent;
import org.apache.commons.scxml.model.ModelException;
import org.apache.commons.scxml.invoke.Invoker;
import org.apache.commons.scxml.invoke.InvokerException;

/**
 * Class that implements Apache Commons Invoker Interface that 
 * defines the interaction (bi-directional events pipe) between 
 * the parent State Machine and the activities that we will define
 */

public class JavaInvoker implements Invoker
{
     private String parentStateId;
     
     private SCInstance parentSCInstance;
     public  Thread th;
     public static StartTimer tictac;
     public ContainerServices _containerServices;


     public JavaInvoker()
     {
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
         _containerServices = (ContainerServices) this.parentSCInstance.getRootContext().get("containerServices");
     }

     /**
      * Begin this invocation.
      *
      * @param source - The source URI of the activity being invoked.
      * @param params - The <param> values
      */
     public void invoke(final String source, final Map params) throws InvokerException {
      try {
         tictac = new StartTimer();
         this.parentSCInstance.getRootContext().set("tictac", tictac);
         th = _containerServices.getThreadFactory().newThread(tictac);
         th.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
     }

     /**
      * Forwards the events triggered on the parent state machine on to the invoked activity.
      *
      * @param evts - an array of external events which triggered during the last time quantum
      */
     public void parentEvents(TriggerEvent[] events) throws InvokerException {
        if (events[0].getName().equals("watch.stop") && (parentStateId.equals("running")))  {
           try {
             tictac.stopping();
             th.join();
             parentSCInstance.getExecutor().triggerEvent(new TriggerEvent(parentStateId + ".invoke.done", TriggerEvent.SIGNAL_EVENT));
           } catch (ModelException me) {
              throw new InvokerException(me.getMessage(), me.getCause()); }
             catch (Exception e){
	   }
        }
        if (events[0].getName().equals("watch.split") && parentStateId.equals("running")) {
           try {
               tictac.paussing();
           } catch (Exception e) { 
        }
     }
    }
     /**
      * Cancel this invocation.
      *
      */
     public void cancel() throws InvokerException {
      System.out.println("In Cancel for ID " + parentStateId);
     }
 }

