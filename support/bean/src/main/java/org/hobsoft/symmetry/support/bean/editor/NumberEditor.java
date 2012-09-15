/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/editor/NumberEditor.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean.editor;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class NumberEditor extends PropertyEditorSupport2
{
	// fields -----------------------------------------------------------------
	
	private final NumberFormat formatter;
	
	// constructors -----------------------------------------------------------
	
	public NumberEditor(NumberFormat formatter)
	{
		this.formatter = formatter;
	}
	
	// PropertyEditor methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAsText()
	{
		Object value = getValue();
		
		return (value != null) ? formatter.format(value) : null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAsText(String text)
	{
		setValue(parse(text));
	}
	
	// public methods ---------------------------------------------------------
	
	public NumberFormat getNumberFormat()
	{
		return formatter;
	}
	
	// private methods --------------------------------------------------------
	
	private Number parse(String text)
	{
		if (text == null)
		{
			return null;
		}
		
		try
		{
			return formatter.parse(text);
		}
		catch (ParseException exception)
		{
			throw new IllegalArgumentException("Cannot parse number: " + text, exception);
		}
	}
}
