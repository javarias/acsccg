package alma.singleComponent.SCXMLStateMachine;
public class MyThreadshutdownSubsysPass1 extends Thread {

    private SCXMLStateMachine _stateMachine;

    public MyThreadshutdownSubsysPass1(SCXMLStateMachine _stateMachine){
    	this._stateMachine = _stateMachine;
    }
    
    public void run(){ 
    	}
    
    public synchronized void stopping() {
    	_stateMachine.fireEvent("shutdownSubsysPass1.done");
    }
}
