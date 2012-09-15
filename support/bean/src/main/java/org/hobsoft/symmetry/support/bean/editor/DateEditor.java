/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/editor/DateEditor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean.editor;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class DateEditor extends PropertyEditorSupport2
{
	// TODO: make package private?
	
	// fields -----------------------------------------------------------------
	
	private final DateFormat formatter;
	
	// constructors -----------------------------------------------------------
	
	public DateEditor(DateFormat formatter)
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
	
	public DateFormat getDateFormat()
	{
		return formatter;
	}
	
	// private methods --------------------------------------------------------
	
	private Date parse(String text)
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
			throw new IllegalArgumentException("Cannot parse date: " + text, exception);
		}
	}
}
