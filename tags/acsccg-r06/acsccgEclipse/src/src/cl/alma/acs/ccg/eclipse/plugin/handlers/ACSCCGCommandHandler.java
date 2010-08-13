package cl.alma.acs.ccg.eclipse.plugin.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import cl.alma.acs.ccg.eclipse.plugin.wizards.ACSCCGWizard;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ACSCCGCommandHandler extends AbstractHandler {
	
	IWorkbenchPart part;
	ISelection selection;
	
	/**
	 * The constructor.
	 */
	public ACSCCGCommandHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ACSCCGWizard acsCodeGeneratorWizard = new ACSCCGWizard();
		WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event),acsCodeGeneratorWizard);
		dialog.open();
		return null;
	}
}
