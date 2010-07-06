package cl.alma.acsccg.example4;

import java.util.logging.Logger;
import alma.acs.component.ComponentLifecycle;
import alma.acs.container.ComponentHelper;
import alma.example4.TestAntennaBasePOATie;
import alma.example4.TestAntennaBaseOperations;

public class TestAntennaBaseHelper extends ComponentHelper {
	/**
	 * Passes a logger to the callback object.
	 * @param containerLogger
	 */
	public TestAntennaBaseHelper(Logger containerLogger) {
		super(containerLogger);
	}

	/**
	 * Gets an instance of the implementation class of the LampAccess component.
	 * @return ComponentLifecycle
	 * @see alma.acs.container.ComponentHelper#_createComponentImpl()
	 */
	protected ComponentLifecycle _createComponentImpl() {
		return new TestAntennaBase();
	}

	/**
	 * Gets an instance of the POATie class of the LampAccess component.
	 * @return Class
	 * @see alma.acs.container.ComponentHelper#_getPOATieClass()
	 */
	protected Class _getPOATieClass() {
		return TestAntennaBasePOATie.class;
	}

	/**
	 * Gets an instance of the operations of the LampAccess component.
	 * @return Class
	 * @see alma.acs.container.ComponentHelper#getOperationsInterface()
	 */
	protected Class _getOperationsInterface() {
		return TestAntennaBaseOperations.class;
	}

}
