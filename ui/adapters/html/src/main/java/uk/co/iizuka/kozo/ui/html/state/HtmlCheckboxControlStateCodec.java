/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/state/HtmlCheckboxControlStateCodec.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.state;

import java.beans.PropertyDescriptor;

import uk.co.iizuka.kozo.state.PropertyValueStateCodec;
import uk.co.iizuka.kozo.state.StateCodec;
import uk.co.iizuka.kozo.state.StateException;

/**
 * Codec for checkbox controls in HTML form data sets.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlCheckboxControlStateCodec.java 98230 2012-02-02 11:34:34Z mark@IIZUKA.CO.UK $
 */
public class HtmlCheckboxControlStateCodec extends PropertyValueStateCodec<Boolean>
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_ON_VALUE = "on";
	
	// fields -----------------------------------------------------------------
	
	private final String onValue;
	
	// constructors -----------------------------------------------------------
	
	public HtmlCheckboxControlStateCodec(StateCodec delegate, PropertyDescriptor descriptor)
	{
		this(delegate, descriptor, DEFAULT_ON_VALUE);
	}
	
	public HtmlCheckboxControlStateCodec(StateCodec delegate, PropertyDescriptor descriptor, String onValue)
	{
		super(delegate, descriptor, Boolean.class);
		
		this.onValue = onValue;
	}
	
	// PropertyValueStateCodec methods ----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String encodePropertyValue(Object bean, Boolean value) throws StateException
	{
		return Boolean.TRUE.equals(value) ? onValue : null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Boolean decodePropertyValue(Object bean, String encodedValue) throws StateException
	{
		return onValue.equals(encodedValue);
	}
}
