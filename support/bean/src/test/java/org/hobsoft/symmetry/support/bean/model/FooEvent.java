/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/model/FooEvent.java $
 * 
 * (c) 2007 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean.model;

import java.util.EventObject;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class FooEvent extends EventObject
{
	// constructors -----------------------------------------------------------
	
	public FooEvent(Object source)
	{
		super(source);
	}
}
