/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/functor/DefaultHyperlinkableClosure.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.functor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DefaultHyperlinkableClosure.java 94748 2011-10-24 14:57:43Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the input type
 */
final class DefaultHyperlinkableClosure<T> implements HyperlinkableClosure<T>
{
	// constants --------------------------------------------------------------
	
	private static final Method DEFAULT_METHOD = Method.GET;
	
	private static final boolean DEFAULT_ASYNCHRONOUS = false;

	// fields -----------------------------------------------------------------
	
	private final String hyperlink;
	
	private final Method method;
	
	private final boolean asynchronous;
	
	// constructors -----------------------------------------------------------
	
	public DefaultHyperlinkableClosure(String hyperlink)
	{
		this(hyperlink, DEFAULT_METHOD);
	}
	
	public DefaultHyperlinkableClosure(String hyperlink, Method method)
	{
		this(hyperlink, method, DEFAULT_ASYNCHRONOUS);
	}
	
	public DefaultHyperlinkableClosure(String hyperlink, Method method, boolean asynchronous)
	{
		if (hyperlink == null)
		{
			throw new NullPointerException("hyperlink cannot be null");
		}
		
		if (method == null)
		{
			throw new NullPointerException("method cannot be null");
		}
		
		this.hyperlink = hyperlink;
		this.method = method;
		this.asynchronous = asynchronous;
	}

	// Closure methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void apply(T input)
	{
		// TODO: how to visit hyperlink?
	}

	// HyperlinkableClosure methods -------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toHyperlink(T bean)
	{
		return BeanPropertyEvaluator.evaluate(hyperlink, bean);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Method getMethod()
	{
		return method;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAsynchronous()
	{
		return asynchronous;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = hyperlink.hashCode();
		
		hashCode = (hashCode * 31) + method.hashCode();
		hashCode = (hashCode * 31) + (asynchronous ? 1 : 0);
		
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof HyperlinkableClosure))
		{
			return false;
		}
		
		HyperlinkableClosure<?> closure = (HyperlinkableClosure<?>) object;
		
		return hyperlink.equals(closure.toHyperlink(null))
			&& method == closure.getMethod()
			&& asynchronous == closure.isAsynchronous();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[hyperlink=" + hyperlink + ", method=" + method + ", asynchronous="
			+ asynchronous + "]";
	}
}
