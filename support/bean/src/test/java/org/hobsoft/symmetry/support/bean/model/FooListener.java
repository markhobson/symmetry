/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/test/java/uk/co/iizuka/common/bean/model/FooListener.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean.model;

import java.util.EventListener;

/**
 * 
 * 
 * @author Mark Hobson
 */
public interface FooListener extends EventListener
{
	void foo1(FooEvent event);
	
	void foo2(FooEvent event);
}
