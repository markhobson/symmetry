/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/hydrate/RehydrationContext.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.hydrate;

import org.hobsoft.symmetry.state.EncodedState;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: RehydrationContext.java 95637 2011-11-29 12:17:44Z mark@IIZUKA.CO.UK $
 */
public class RehydrationContext extends HydrationContext
{
	// TODO: fold into HydrationContext and remove
	// TODO: should encoded state be mutable?
	
	// fields -----------------------------------------------------------------
	
	private EncodedState encodedState;
	
	// constructors -----------------------------------------------------------
	
	public RehydrationContext()
	{
		this.encodedState = new EncodedState();
	}
	
	// public methods ---------------------------------------------------------
	
	public EncodedState getEncodedState()
	{
		return encodedState;
	}
	
	public void setEncodedState(EncodedState encodedState)
	{
		this.encodedState = encodedState;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = super.hashCode();
		hashCode = (hashCode * 31) + encodedState.hashCode();
		
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof RehydrationContext))
		{
			return false;
		}
		
		RehydrationContext context = (RehydrationContext) object;
		
		return super.equals(context)
			&& encodedState.equals(context.getEncodedState());
	}
}
