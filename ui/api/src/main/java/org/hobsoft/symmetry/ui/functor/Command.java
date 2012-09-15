/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/functor/Command.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.functor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Command.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 */
public abstract class Command implements Closure<Object>
{
	// Closure methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void apply(Object input)
	{
		execute();
	}
	
	// public methods ---------------------------------------------------------
	
	public abstract void execute();
}
