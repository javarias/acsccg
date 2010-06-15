package cl.alma.acs.ccg.eclipse.plugin.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ACSCCGWizardPage extends WizardPage {

	private Text outputText;

	private Text modelPath;

	private Text profilePath;

	private ISelection selection;

	private FileDialog filedialog;

	private static final String[] extensionFilter = { "*.uml", "*.UML",
			".uml2", "*.UML2" };

	/**
	 * Constructor for CCGACSWizardPage.
	 * 
	 * @param pageName
	 */
	public ACSCCGWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("Alma Common Software Component Code Generator ");
		setDescription("This wizard creates from a UML model or any model in XMI2.x standard the necessary code to be implemented.");
		this.selection = selection;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {

		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;

		// --Output folder
		Label label = new Label(container, SWT.NULL);
		label.setText("&Output folder container:");

		outputText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		outputText.setLayoutData(gd);
		outputText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});

		Button buttonBrowserOutput = new Button(container, SWT.PUSH);
		buttonBrowserOutput.setText("Browse...");
		buttonBrowserOutput.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowseOutput();
			}
		});

		// --Model folder
		label = new Label(container, SWT.NULL);
		label.setText("&UML Model file path:");

		modelPath = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		modelPath.setLayoutData(gd);
		modelPath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});

		Button buttonBrowserModel = new Button(container, SWT.PUSH);
		buttonBrowserModel.setText("Browse...");
		buttonBrowserModel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowseModel();
			}
		});

		// --Profile folder
		label = new Label(container, SWT.NULL);
		label.setText("&UML Profile file path:");

		profilePath = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		profilePath.setLayoutData(gd);
		profilePath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});

		Button buttonBrowserProfile = new Button(container, SWT.PUSH);
		buttonBrowserProfile.setText("Browse...");
		buttonBrowserProfile.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowseProfile();
			}
		});

		// --Initialize folder
		initialize();
		dialogChanged();
		setControl(container);
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */

	private void initialize() {

		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer)
					container = (IContainer) obj;
				else
					container = ((IResource) obj).getParent();
				outputText.setText(container.getLocationURI().toString());
			}
		}
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */

	private void handleBrowseOutput() {

		DirectoryDialog directoryDialog = new DirectoryDialog(getShell());
		directoryDialog.setMessage("Select the output folder...");
		outputText.setText(directoryDialog.open());
		outputText.setText(filedialog.open());
	}

	private void handleBrowseModel() {
		
		filedialog = new FileDialog(getShell(), SWT.OPEN);
		filedialog.setFilterExtensions(extensionFilter);
		filedialog.setText("Select the UML model");
		modelPath.setText(filedialog.open());
	}

	private void handleBrowseProfile() {
		
		filedialog = new FileDialog(getShell(), SWT.OPEN);
		filedialog.setFilterExtensions(extensionFilter);
		filedialog.setText("Select the UML profile");
		profilePath.setText(filedialog.open());
	}

	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {

		if (outputText.getText().length() == 0) {
			updateStatus("Output folder must be specified");
			return;
		}
		
		//Model
		if (modelPath.getText().length() == 0) {
			updateStatus("UML model path must be specified");
			return;
		}
		
		//Profile
		if (profilePath.getText().length() == 0) {
			updateStatus("UML profile path must be specified");
			return;
		}
		
		updateStatus(null);
	}

	private void updateStatus(String message) {

		setErrorMessage(message);
		setPageComplete(message == null);
	}
	
	public String getModel() {
		return modelPath.getText();
	}
	
	public String getProfile() {
		return profilePath.getText();
	}
	
	public String getOutputFolder() {
		return outputText.getText();
	}
}