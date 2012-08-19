/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/EmptyStringPropertyEditor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import java.beans.PropertyEditor;

/**
 * A {@code PropertyEditor} decorator that converts empty strings to null in {@link #setAsText(String)}.
 * 
 * @author Mark Hobson
 * @version $Id: EmptyStringPropertyEditor.java 93028 2011-09-29 14:53:01Z mark@IIZUKA.CO.UK $
 */
class EmptyStringPropertyEditor extends DelegatingPropertyEditor
{
	// constructors -----------------------------------------------------------
	
	public EmptyStringPropertyEditor(PropertyEditor delegate)
	{
		super(delegate);
	}
	
	// PropertyEditor methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAsText(String text)
	{
		if (text != null && text.length() == 0)
		{
			text = null;
		}
		
		super.setAsText(text);
	}
}
