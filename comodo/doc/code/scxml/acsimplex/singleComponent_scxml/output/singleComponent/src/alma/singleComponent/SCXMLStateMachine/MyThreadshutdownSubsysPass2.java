package alma.singleComponent.SCXMLStateMachine;
public class MyThreadshutdownSubsysPass2 extends Thread {

    private SCXMLStateMachine _stateMachine;

    public MyThreadshutdownSubsysPass2(SCXMLStateMachine _stateMachine){
    	this._stateMachine = _stateMachine;
    }
    
    public void run(){ 
    }
    
    public synchronized void stopping() {
    	_stateMachine.fireEvent("shutdownSubsysPass2.done");
    }
}
