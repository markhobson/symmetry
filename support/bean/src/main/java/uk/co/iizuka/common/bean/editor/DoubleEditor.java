/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/editor/DoubleEditor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean.editor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DoubleEditor.java 97413 2011-12-30 17:52:58Z mark@IIZUKA.CO.UK $
 */
public class DoubleEditor extends PropertyEditorSupport2
{
	// TODO: make package private?
	
	// PropertyEditor methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAsText()
	{
		Object value = getValue();
		
		return (value != null) ? value.toString() : null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAsText(String text)
	{
		Double value = (text != null) ? Double.valueOf(text) : null;
		
		setValue(value);
	}
}
