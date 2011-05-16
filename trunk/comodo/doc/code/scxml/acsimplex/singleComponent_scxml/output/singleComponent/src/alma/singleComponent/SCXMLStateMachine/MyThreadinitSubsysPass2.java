package alma.singleComponent.SCXMLStateMachine;

public class MyThreadinitSubsysPass2 extends Thread {

    private SCXMLStateMachine _stateMachine;

    public MyThreadinitSubsysPass2(SCXMLStateMachine _stateMachine){
    	this._stateMachine = _stateMachine;
    }

    public void run(){ 
   	

    }
    
    public synchronized void stopping() {
    	_stateMachine.fireEvent("initSubsysPass2.done");
    	
    }
}
