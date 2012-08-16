/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/functor/Function.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.functor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: Function.java 86838 2011-04-12 14:32:18Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the input type
 * @param <U>
 *            the output type
 */
public interface Function<T, U>
{
	U apply(T input);
}
