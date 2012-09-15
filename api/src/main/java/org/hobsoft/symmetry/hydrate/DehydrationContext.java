/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/hydrate/DehydrationContext.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.hydrate;

import java.io.OutputStream;
import java.util.Locale;

import org.hobsoft.symmetry.state.State;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DehydrationContext.java 95640 2011-11-29 12:28:54Z mark@IIZUKA.CO.UK $
 */
public class DehydrationContext extends HydrationContext
{
	// TODO: fold into HydrationContext and remove
	
	// fields -----------------------------------------------------------------
	
	private final State state;
	
	private final Locale locale;
	
	private final OutputStream out;
	
	// constructors -----------------------------------------------------------
	
	public DehydrationContext(DehydrationContext context)
	{
		this(context.getState(), context.getLocale(), context.getOutputStream());
		
		setAll(context.getAll());
	}
	
	public DehydrationContext(State state, Locale locale, OutputStream out)
	{
		this.locale = locale;
		this.state = state;
		this.out = out;
	}
	
	// public methods ---------------------------------------------------------
	
	public State getState()
	{
		return state;
	}
	
	public Locale getLocale()
	{
		return locale;
	}
	
	public OutputStream getOutputStream()
	{
		return out;
	}
}
