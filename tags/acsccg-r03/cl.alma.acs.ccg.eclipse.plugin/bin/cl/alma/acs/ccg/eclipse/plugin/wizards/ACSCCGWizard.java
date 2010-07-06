package cl.alma.acs.ccg.eclipse.plugin.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

/**
 * This is a sample new wizard. Its role is to create a new file resource in the
 * provided container. If the container resource (a folder or a project) is
 * selected in the workspace when the wizard is opened, it will accept it as the
 * target container. The wizard creates one file with the extension "mpe". If a
 * sample multi-page editor (also available as a template) is registered for the
 * same extension, it will be able to open it.
 */

public class ACSCCGWizard extends Wizard implements INewWizard {

	private ACSCCGWizardPage acsCodeGeneratorMainPage;
	private ISelection selection;
	protected IWorkbench workbench;

	/**
	 * Constructor for ACSCodeGeneratorWizard.
	 */
	public ACSCCGWizard() {
		super();
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(null, "icons/logo.gif"));
	}

	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		acsCodeGeneratorMainPage = new ACSCCGWizardPage(selection);
		addPage(acsCodeGeneratorMainPage);
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We
	 * will create an operation and run it using wizard as execution context.
	 */
	public boolean performFinish() {

		 String model = acsCodeGeneratorMainPage.getModel();
		 String profile = acsCodeGeneratorMainPage.getProfile();
		 String output = acsCodeGeneratorMainPage.getOutputFolder();
		 
		 System.out.println(model);
		 System.out.println(profile);
		 System.out.println(output);
		 
		//Java
		return true;
	}

	/**
	 * The worker method. It will find the container, create the file if missing
	 * or just replace its contents, and open the editor on the newly created
	 * file.
	 */

	@SuppressWarnings("unused")
	private void doFinish(String containerName, String fileName,
			IProgressMonitor monitor) throws CoreException {
		
		monitor.worked(1);
	}

	/**
	 * We will initialize file contents with a sample text.
	 */

	@SuppressWarnings("unused")
	private void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR,
				"cl.alma.acs.codegenerator.eclipse.plugin", IStatus.OK,
				message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if we can initialize
	 * from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}