/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/state/HtmlTextControlStateCodec.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.state;

import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.state.PropertyValueStateCodec;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.state.StateException;

/**
 * Codec for text input controls in HTML form data sets.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTextControlStateCodec.java 98230 2012-02-02 11:34:34Z mark@IIZUKA.CO.UK $
 */
public class HtmlTextControlStateCodec extends PropertyValueStateCodec<String>
{
	// constructors -----------------------------------------------------------
	
	public HtmlTextControlStateCodec(StateCodec delegate, PropertyDescriptor descriptor)
	{
		super(delegate, descriptor, String.class);
	}
	
	// PropertyValueStateCodec methods ----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String encodePropertyValue(Object bean, String value) throws StateException
	{
		return value.toString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String decodePropertyValue(Object bean, String encodedValue) throws StateException
	{
		return encodedValue;
	}
}
