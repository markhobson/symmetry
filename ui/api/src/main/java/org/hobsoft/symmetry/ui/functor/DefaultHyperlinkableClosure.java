/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.ui.functor;

/**
 * 
 * 
 * @author Mark Hobson
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
