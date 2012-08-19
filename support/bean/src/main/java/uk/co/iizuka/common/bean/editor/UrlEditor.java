/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/editor/UrlEditor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean.editor;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: UrlEditor.java 97413 2011-12-30 17:52:58Z mark@IIZUKA.CO.UK $
 */
public class UrlEditor extends PropertyEditorSupport2
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
		setValue(parse(text));
	}
	
	// private methods --------------------------------------------------------
	
	private static URL parse(String text)
	{
		if (text == null)
		{
			return null;
		}
		
		try
		{
			return new URL(text);
		}
		catch (MalformedURLException exception)
		{
			throw new IllegalArgumentException("Cannot parse URL: " + text, exception);
		}
	}
}
