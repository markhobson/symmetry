/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/DummyEventListenerProxy.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import java.util.EventListener;
import java.util.EventListenerProxy;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DummyEventListenerProxy.java 87666 2011-05-04 16:48:53Z mark@IIZUKA.CO.UK $
 */
public class DummyEventListenerProxy extends EventListenerProxy
{
	// constructors -----------------------------------------------------------
	
	public DummyEventListenerProxy(EventListener proxy)
	{
		super(proxy);
	}
}
