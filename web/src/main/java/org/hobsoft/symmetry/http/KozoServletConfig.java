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
