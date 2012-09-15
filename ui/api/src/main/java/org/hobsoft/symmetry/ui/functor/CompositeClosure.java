/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/functor/CompositeClosure.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.functor;

import java.util.Arrays;
import java.util.Collection;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: CompositeClosure.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the input type
 */
public final class CompositeClosure<T> implements Closure<T>
{
	// fields -----------------------------------------------------------------
	
	private final Collection<Closure<T>> closures;
	
	// constructors -----------------------------------------------------------
	
	public CompositeClosure(Closure<T>... closures)
	{
		this(Arrays.asList(closures));
	}
	
	public CompositeClosure(Collection<Closure<T>> closures)
	{
		this.closures = closures;
	}
	
	// Closure methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void apply(T input)
	{
		for (Closure<T> closure : closures)
		{
			closure.apply(input);
		}
	}
	
	// public methods ---------------------------------------------------------
	
	public Collection<Closure<T>> getClosures()
	{
		return closures;
	}
}
