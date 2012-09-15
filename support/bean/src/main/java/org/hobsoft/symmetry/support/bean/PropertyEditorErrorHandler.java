/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/PropertyEditorErrorHandler.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

import java.beans.PropertyEditor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: PropertyEditorErrorHandler.java 86783 2011-04-11 18:18:45Z mark@IIZUKA.CO.UK $
 */
public interface PropertyEditorErrorHandler
{
	void handleError(PropertyEditor editor, Throwable exception);
}
