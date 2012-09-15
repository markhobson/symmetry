/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/functor/HyperlinkableClosure.java $
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
 */
public interface HyperlinkableClosure<T> extends Closure<T>
{
	// TODO: replace with Hyperlinkable<T>
	
	// types ------------------------------------------------------------------
	
	/**
	 * The HTTP method.
	 */
	enum Method
	{
		GET,
		POST;
	}
	
	// public methods ---------------------------------------------------------
	
	String toHyperlink(T input);
	
	Method getMethod();
	
	boolean isAsynchronous();
}
