package cl.alma.acsccg.example4;

import java.util.logging.Logger;
import alma.ACS.ComponentStates;
import alma.acs.component.ComponentLifecycle;
import alma.acs.container.ContainerServices;
import alma.example4.TestAntennaBaseOperations;

/*PROTECTED REGION ID(TestAntenna.ProtectedImports) ENABLED START*/
/* (non-javadoc!)
 * Autogenerated protected region, the imports in this protected area
 * are not generated, it's reserved to special features that they can't be
 * specified in the UML model
 */
/*PROTECTED REGION END*/

import alma.example4.sensor_status_struct;
import alma.example4.antenna_status_struct;
import alma.example4.position_struct;

public class TestAntennaBase extends BasicAntennaBase
		implements
			ComponentLifecycle,
			TestAntennaBaseOperations {

	private ContainerServices m_containerServices;
	private Logger m_logger;

	/*PROTECTED REGION ID(TestAntenna.ProtectedAttributes) ENABLED START*/
	/* (non-javadoc!)
	 * Autogenerated protected region, the imports in this protected area
	 * are not generated, it's reserved for special features that they can't be
	 * specified in the UML model
	 */
	/*PROTECTED REGION END*/

	/* 
	 * Implementation of  ComponentLifecycle
	 */
	public void initialize(ContainerServices containerServices) {
		m_containerServices = containerServices;
		m_logger = m_containerServices.getLogger();
		m_logger.info("initialize() called...");
	}

	public void execute() {
		m_logger.info("execute() called...");
	}

	public void cleanUp() {
		m_logger.info("cleanUp() called...");
	}

	public void aboutToAbort() {
		cleanUp();
		m_logger.info("aboutToAbort() called...");
	}

	/* 
	 * Implementation of ACSComponent
	 */
	public ComponentStates componentState() {
		return m_containerServices.getComponentStateManager().getCurrentState();
	}

	public String name() {
		return m_containerServices.getName();
	}

	/* 
	 * Definition of member TestAntennaOperations
	 */

	public String getTestID() {
		/*PROTECTED REGION ID(TestAntenna.getTestID) ENABLED START*/
		return new String();
		/*PROTECTED REGION END*/
	}

	/*
	 * Override inherited methods
	 */

	@Override
	public void setAntennaPosition(position_struct position) {
		/*PROTECTED REGION ID(TestAntenna.setAntennaPosition) ENABLED START*/

		/*PROTECTED REGION END*/
	}

	@Override
	public position_struct getAntennaPosition() {
		/*PROTECTED REGION ID(TestAntenna.getAntennaPosition) ENABLED START*/
		return new position_struct();
		/*PROTECTED REGION END*/
	}

	/*
	 * Implements the Interface methods.
	 */

	/*PROTECTED REGION ID(TestAntenna.ProtectedRegion) ENABLED START*/
	/* (non-javadoc!)
	 * Autogenerated protected region, the implementations in this protected area
	 * are not generated, it's reserved to special features that they can't be
	 * specified in the UML model
	 */
	/*PROTECTED REGION END*/
}