package alma.singleComponent.SCXMLStateMachine;

public class MyThreadinitSubsysPass1 extends Thread{
	
    private SCXMLStateMachine _stateMachine;

    public MyThreadinitSubsysPass1(SCXMLStateMachine _stateMachine){
    	this._stateMachine = _stateMachine;
    }

    public void run(){ 

    }
    
    public synchronized void stopping() {
    	_stateMachine.fireEvent("initSubsysPass1.done");
    }

}
