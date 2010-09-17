package cl.alma.acs.ccg.strategy;

/**
 * Context code generation
 * see the strategy desing pattern
 * @author atejeda
 */
public class ContextCodeGeneration 
{
	private ICodeGenerationStrategy __strategy;
	
	/**
	 * Constructor
	 * by default set the strategy to use
	 * @param strategy
	 */
	public ContextCodeGeneration(ICodeGenerationStrategy strategy)
	{
		setStrategy(strategy);
	}

	/**
	 * Set the startegy to use (ICodeGenerationStrategy)
	 * (java, python or c++ startegy)
	 * @see ICodeGenerationStrategy
	 * @param strategy
	 */
	public void setStrategy(ICodeGenerationStrategy strategy) 
	{
		__strategy = strategy;
	}

	/**
	 * Return the strategy interface object (java, python or c++ startegy)
	 * @return ICodeGenerationStrategy
	 * @see ICodeGenerationStrategy
	 */
	public ICodeGenerationStrategy getStrategy() 
	{
		return __strategy;
	}
	
	/**
	 * Function that generates the code
	 */
	public void generateACSCode() 
	{
		 __strategy.generateCode();
	}

}
