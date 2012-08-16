/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/functor/HyperlinkableClosure.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.functor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: HyperlinkableClosure.java 88711 2011-06-03 15:44:30Z mark@IIZUKA.CO.UK $
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
