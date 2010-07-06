package cl.alma.acsccg.example4;

import java.util.logging.Logger;
import alma.acs.component.ComponentLifecycle;
import alma.acs.container.ComponentHelper;
import alma.example4.AlcatelAntennaBasePOATie;
import alma.example4.AlcatelAntennaBaseOperations;

public class AlcatelAntennaBaseHelper extends ComponentHelper {
	/**
	 * Passes a logger to the callback object.
	 * @param containerLogger
	 */
	public AlcatelAntennaBaseHelper(Logger containerLogger) {
		super(containerLogger);
	}

	/**
	 * Gets an instance of the implementation class of the LampAccess component.
	 * @return ComponentLifecycle
	 * @see alma.acs.container.ComponentHelper#_createComponentImpl()
	 */
	protected ComponentLifecycle _createComponentImpl() {
		return new AlcatelAntennaBase();
	}

	/**
	 * Gets an instance of the POATie class of the LampAccess component.
	 * @return Class
	 * @see alma.acs.container.ComponentHelper#_getPOATieClass()
	 */
	protected Class _getPOATieClass() {
		return AlcatelAntennaBasePOATie.class;
	}

	/**
	 * Gets an instance of the operations of the LampAccess component.
	 * @return Class
	 * @see alma.acs.container.ComponentHelper#getOperationsInterface()
	 */
	protected Class _getOperationsInterface() {
		return AlcatelAntennaBaseOperations.class;
	}

}
