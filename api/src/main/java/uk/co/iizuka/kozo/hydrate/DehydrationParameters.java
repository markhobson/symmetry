/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/api/src/main/java/uk/co/iizuka/kozo/hydrate/DehydrationParameters.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.hydrate;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DehydrationParameters.java 95601 2011-11-28 12:55:42Z mark@IIZUKA.CO.UK $
 */
public final class DehydrationParameters
{
	// TODO: remove this and move parameters into respective dehydrators
	
	// fields -----------------------------------------------------------------
	
	private String theme;
	
	private boolean debug;

	// public methods ---------------------------------------------------------
	
	public String getTheme()
	{
		return theme;
	}
	
	public void setTheme(String theme)
	{
		this.theme = theme;
	}
	
	public boolean isDebug()
	{
		return debug;
	}
	
	public void setDebug(boolean debug)
	{
		this.debug = debug;
	}
}
