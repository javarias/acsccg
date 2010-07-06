package cl.alma.acsccg.example2;

import java.util.logging.Logger;
import alma.acs.component.ComponentLifecycle;
import alma.acs.container.ComponentHelper;
import alma.example2.BasicAntennaBasePOATie;
import alma.example2.BasicAntennaBaseOperations;

public class BasicAntennaBaseHelper extends ComponentHelper {
	/**
	 * Passes a logger to the callback object.
	 * @param containerLogger
	 */
	public BasicAntennaBaseHelper(Logger containerLogger) {
		super(containerLogger);
	}

	/**
	 * Gets an instance of the implementation class of the LampAccess component.
	 * @return ComponentLifecycle
	 * @see alma.acs.container.ComponentHelper#_createComponentImpl()
	 */
	protected ComponentLifecycle _createComponentImpl() {
		return new BasicAntennaBase();
	}

	/**
	 * Gets an instance of the POATie class of the LampAccess component.
	 * @return Class
	 * @see alma.acs.container.ComponentHelper#_getPOATieClass()
	 */
	protected Class _getPOATieClass() {
		return BasicAntennaBasePOATie.class;
	}

	/**
	 * Gets an instance of the operations of the LampAccess component.
	 * @return Class
	 * @see alma.acs.container.ComponentHelper#getOperationsInterface()
	 */
	protected Class _getOperationsInterface() {
		return BasicAntennaBaseOperations.class;
	}

}
