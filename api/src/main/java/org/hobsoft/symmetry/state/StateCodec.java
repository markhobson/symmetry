/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/state/StateCodec.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.state;

import java.beans.PropertyDescriptor;

/**
 * 
 * 
 * @author Mark Hobson
 */
public interface StateCodec
{
	EncodedState encodeState(State state) throws StateException;
	
	State decodeState(EncodedState encodedState) throws StateException;
	
	String encodeBean(Object bean) throws StateException;
	
	Object decodeBean(String encodedBean) throws StateException;
	
	String encodePropertyValue(PropertyState property) throws StateException;
	
	Object decodePropertyValue(Object bean, PropertyDescriptor descriptor, String encodedValue) throws StateException;
}
