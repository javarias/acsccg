package alma.STOPWATCH_MODULE.StopWatchImpl;

import java.util.logging.Logger;

import java.util.Collection;
import org.apache.commons.logging.Log;
import org.apache.commons.scxml.ErrorReporter;
import org.apache.commons.scxml.EventDispatcher;
import org.apache.commons.scxml.SCInstance;
import org.apache.commons.scxml.SCXMLExpressionException;
import org.apache.commons.scxml.model.Action;
import org.apache.commons.scxml.model.ModelException;

public class ResetDisplay extends Action
{
      /** Serial version UID. */
      private static final long serialVersionUID = 1L;
      /** This is who we say hello to. */
      private String name;
      /** We count callbacks to execute() as part of the test suite. */
     public static int callbacks = 0;
 
      /** Public constructor is needed for the I in SCXML IO. */
      public ResetDisplay() {
          super();
      }
  
      /**
       * Get the name.
       *
       * @return Returns the name.
       */
      public String getName() {
          return name;
      }
  
      /**
       * Set the name.
       *
       * @param name The name to set.
  	     */
      public void setName(String name) {
          this.name = name;
      }
  
      /**
       * Mehod that execute this action instance.
       * 
       * @param evtDispatcher - The EventDispatcher for this execution instance
       * @param errRep - The ErrorReporter to broadcast any errors during execution.
       * @param scInstance - The state machine execution instance information.
       * @param appLog - The application Log.
       * @param derivedEvents - The collection to which any internal events arising from the execution of this action must be added.
       */
      public void execute(final EventDispatcher evtDispatcher, final ErrorReporter errRep, final SCInstance scInstance, final Log appLog, final Collection derivedEvents)
      throws ModelException, SCXMLExpressionException {
        
         ((StartTimer) scInstance.getRootContext().get("tictac")).reset();
         ((Logger) scInstance.getRootContext().get("logger")).info("CustomAction::resetDisplay()");
      }
}
