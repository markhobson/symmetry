/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/web/src/main/java/uk/co/iizuka/kozo/http/KozoServletConfig.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.http;

import org.hobsoft.symmetry.PeerManager;
import org.hobsoft.symmetry.hydrate.ComponentRenderKit;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class KozoServletConfig
{
	// TODO: think of more generic way to configure themes
	
	// fields -----------------------------------------------------------------
	
	private PeerManager peerManager;
	
	private ComponentRenderKit<?> renderKit;
	
	private boolean resolveState;
	
	private boolean debug;
	
	private String theme;
	
	// constructors -----------------------------------------------------------
	
	public KozoServletConfig(PeerManager peerManager, ComponentRenderKit<?> renderKit)
	{
		this.peerManager = peerManager;
		this.renderKit = renderKit;
		resolveState = false;
		debug = false;
		theme = null;
	}
	
	// public methods ---------------------------------------------------------
	
	public PeerManager getPeerManager()
	{
		return peerManager;
	}
	
	public ComponentRenderKit<?> getComponentRenderKit()
	{
		return renderKit;
	}
	
	public boolean isResolveState()
	{
		return resolveState;
	}
	
	public void setResolveState(boolean resolveState)
	{
		this.resolveState = resolveState;
	}
	
	public boolean isDebug()
	{
		return debug;
	}
	
	public void setDebug(boolean debug)
	{
		this.debug = debug;
	}
	
	public String getTheme()
	{
		return theme;
	}
	
	public void setTheme(String theme)
	{
		this.theme = theme;
	}
}
