/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/state/HtmlCheckboxControlStateCodec.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.state;

import java.beans.PropertyDescriptor;

import org.hobsoft.symmetry.state.PropertyValueStateCodec;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.state.StateException;

/**
 * Codec for checkbox controls in HTML form data sets.
 * 
 * @author Mark Hobson
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
