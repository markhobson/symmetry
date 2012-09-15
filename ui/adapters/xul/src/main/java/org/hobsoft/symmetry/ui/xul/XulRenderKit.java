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
package org.hobsoft.symmetry.ui.xul;

import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.RehydrationContext;
import org.hobsoft.symmetry.state.NullStateCodec;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrator;
import org.hobsoft.symmetry.ui.xul.hydrate.XulComponentHydrator;
import org.hobsoft.symmetry.xml.XmlRenderKit;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class XulRenderKit extends XmlRenderKit<Component>
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_CONTENT_TYPE = "application/vnd.mozilla.xul+xml";
	
	// fields -----------------------------------------------------------------
	
	private final ComponentHydrator hydrator;
	
	// constructors -----------------------------------------------------------
	
	public XulRenderKit()
	{
		this(DEFAULT_CONTENT_TYPE);
	}
	
	public XulRenderKit(String contentType)
	{
		this(contentType, new XulComponentHydrator());
	}
	
	public XulRenderKit(String contentType, ComponentHydrator hydrator)
	{
		super(Component.class, contentType);
		
		this.hydrator = hydrator;
	}
	
	// AbstractRenderKit methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected StateCodec createStateCodec(Component component, HydrationContext context)
	{
		// TODO: design state codec for XUL
		return new NullStateCodec();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateImpl(Component component, DehydrationContext context) throws HydrationException
	{
		component.accept(hydrator, context);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void rehydrateImpl(Component component, RehydrationContext context) throws HydrationException
	{
		component.accept(hydrator, context);
	}
}
