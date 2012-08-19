/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/DefaultPropertyEditorErrorHandler.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import java.beans.PropertyEditor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DefaultPropertyEditorErrorHandler.java 97399 2011-12-30 11:55:45Z mark@IIZUKA.CO.UK $
 */
public class DefaultPropertyEditorErrorHandler implements PropertyEditorErrorHandler
{
	// constants --------------------------------------------------------------
	
	private static final Logger LOG = Logger.getLogger(DefaultPropertyEditorErrorHandler.class.getName());
	
	// PropertyEditorErrorHandler methods -------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handleError(PropertyEditor editor, Throwable exception)
	{
		if (editor instanceof PropertyEditorErrorHandler)
		{
			((PropertyEditorErrorHandler) editor).handleError(editor, exception);
		}
		else
		{
			// TODO: handle better
			LOG.log(Level.WARNING, "Unhandled property editor error", exception);
		}
	}
}
