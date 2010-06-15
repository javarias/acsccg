package cl.alma.acs.ccg.strategy;

public class ContextCodeGeneration {
	
	private ICodeGenerationStrategy __strategy;
	
	public ContextCodeGeneration(ICodeGenerationStrategy strategy) {
		setStrategy(strategy);
	}

	public void setStrategy(ICodeGenerationStrategy strategy) {
		__strategy = strategy;
	}

	public ICodeGenerationStrategy getStrategy() {
		return __strategy;
	}
	
	public void generateACSCode() {
		 __strategy.generateCode();
	}

}
