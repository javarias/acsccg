package cl.alma.acs.ccg.vetostrategy;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.xpand2.output.FileHandle;
import org.eclipse.xpand2.output.VetoStrategy;

public class ACSCCGVetoStrategy implements VetoStrategy {

	/** 
	 * This variable is to get the error logs with logfactory of Java.
	 * @see org.eclipse.xpand2.output.NoChangesVetoStrategy#hasVeto(org.eclipse.xpand2.output.FileHandle)
	 */
	private final Log log = LogFactory.getLog(getClass());

	/**
	 * When the workflow is invoked, and if VetoStragy is added to the workflow,this function check
	 * if there are changes in the file to generate.
	 * @param	handle	a Filehandle the file to be generated
	 * @return 	boolean	 
	 * @see		org.eclipse.xpand2.output.VetoStrategy#hasVeto(org.eclipse.xpand2.output.FileHandle)
	 */
	@Override
	public boolean hasVeto(FileHandle handle) {
		return !hasChanges(handle);
	}

	/**
	 * Check if the file have changes, this method is invoked by hasVeto().
	 * @see 	hasVeto
	 * @param 	handle a Filehandle the file to be generated
	 * @return 	boolean
	 */
	public boolean hasChanges(FileHandle h) {
		if (h.getTargetFile().exists()) {
			try {
				InputStream oldIs = new FileInputStream(h.getTargetFile());
				byte[] bytes = getBytes(h);
				try {
					byte[] lbuffer = new byte[bytes.length];
					oldIs.read(lbuffer);
					if (oldIs.read() ==-1) {
						for (int i = 0; i < lbuffer.length; i++) {
							if (lbuffer[i]!=bytes[i])
								return true;
						}
					} else {
						return true;
					}
					return false;
				} finally {
					oldIs.close();
				}
			} catch (Exception e) {
				log.error("Couldn't compare content of file "+h.getTargetFile().getAbsolutePath(), e);
				log.error("File "+h.getTargetFile().getAbsolutePath()+" will not be overwritten");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Get the file bytes.
	 * @see 	hasChanges
	 * @param	handle a Filehandle the file to be generated
	 * @return	byte[]
	 */
	public byte[] getBytes(FileHandle h) {
		if (h.getFileEncoding() != null) {
			try {
				return h.getBuffer().toString().getBytes(h.getFileEncoding());
			} catch (UnsupportedEncodingException e) {
				log.error(e.getMessage(), e);
			}
		}
		return h.getBuffer().toString().getBytes();
	}

}
