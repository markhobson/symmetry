/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/Consumable.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Consumable.java 86783 2011-04-11 18:18:45Z mark@IIZUKA.CO.UK $
 */
public interface Consumable
{
	// TODO: rename to ConsumableEventObject?
	
	boolean isConsumed();
	
	void consume();
}
