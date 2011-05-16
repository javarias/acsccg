package alma.singleComponent.SCXMLStateMachine;
public class MyThreadreinitSubsystem extends Thread {
	
	private int callsToReinit;
	
    private SCXMLStateMachine _stateMachine;

    public MyThreadreinitSubsystem(SCXMLStateMachine _stateMachine){
    	this._stateMachine = _stateMachine;
    }
    
    public void run(){ 
    	callsToReinit++;
    	}
    
    public synchronized void stopping() {
    	_stateMachine.fireEvent("reinitSubsystem.done");
    }
    
	public int getCallsToReinit() {
		return callsToReinit;
	}
}
