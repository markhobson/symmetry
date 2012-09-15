/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/Consumable.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

/**
 * 
 * 
 * @author Mark Hobson
 */
public interface Consumable
{
	// TODO: rename to ConsumableEventObject?
	
	boolean isConsumed();
	
	void consume();
}
