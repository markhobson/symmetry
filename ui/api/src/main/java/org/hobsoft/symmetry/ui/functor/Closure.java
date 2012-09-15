/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/functor/Closure.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.functor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Closure.java 86766 2011-04-11 15:06:48Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the input type
 */
public interface Closure<T>
{
	// TODO: rename to execute to align with Command?
	void apply(T input);
}
