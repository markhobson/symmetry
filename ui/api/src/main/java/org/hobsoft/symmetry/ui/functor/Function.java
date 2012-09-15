/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/functor/Function.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.functor;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the input type
 * @param <U>
 *            the output type
 */
public interface Function<T, U>
{
	U apply(T input);
}
