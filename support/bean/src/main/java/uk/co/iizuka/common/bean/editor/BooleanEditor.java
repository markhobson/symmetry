/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/editor/BooleanEditor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean.editor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: BooleanEditor.java 97413 2011-12-30 17:52:58Z mark@IIZUKA.CO.UK $
 */
public class BooleanEditor extends PropertyEditorSupport2
{
	// TODO: add configurable true/false values
	// TODO: make package private?
	
	// constants --------------------------------------------------------------
	
	private static final String TRUE_TEXT = "true";
	
	private static final String FALSE_TEXT = "false";

	// PropertyEditor methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAsText()
	{
		String text;
		
		Object value = getValue();
		
		if (Boolean.TRUE.equals(value))
		{
			text = TRUE_TEXT;
		}
		else if (Boolean.FALSE.equals(value))
		{
			text = FALSE_TEXT;
		}
		else
		{
			text = null;
		}
		
		return text;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAsText(String text)
	{
		Boolean value;
		
		if (text == null)
		{
			value = null;
		}
		else if (TRUE_TEXT.equals(text))
		{
			value = Boolean.TRUE;
		}
		else if (FALSE_TEXT.equals(text))
		{
			value = Boolean.FALSE;
		}
		else
		{
			throw new IllegalArgumentException("Cannot parse boolean: " + text);
		}
		
		setValue(value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getTags()
	{
		return new String[] {TRUE_TEXT, FALSE_TEXT};
	}
}
