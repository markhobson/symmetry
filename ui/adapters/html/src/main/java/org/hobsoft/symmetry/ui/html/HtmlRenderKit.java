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
package org.hobsoft.symmetry.ui.html;

import java.util.HashMap;
import java.util.Map;

import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.hydrate.RehydrationContext;
import org.hobsoft.symmetry.state.Base64StateCodec;
import org.hobsoft.symmetry.state.StateCodec;
import org.hobsoft.symmetry.support.bean.JdkBeanIntrospector;
import org.hobsoft.symmetry.support.codec.Codec;
import org.hobsoft.symmetry.support.codec.Codecs;
import org.hobsoft.symmetry.ui.Component;
import org.hobsoft.symmetry.ui.common.hydrate.ComponentHydrator;
import org.hobsoft.symmetry.ui.html.hydrate.HtmlComponentHydrator;
import org.hobsoft.symmetry.ui.html.hydrate.HtmlTreeDehydrator;
import org.hobsoft.symmetry.ui.html.state.FormHtmlEventStateCodec;
import org.hobsoft.symmetry.ui.html.state.HtmlFormStateCodec;
import org.hobsoft.symmetry.ui.html.state.HyperlinkableClosureStateCodec;
import org.hobsoft.symmetry.ui.model.TreePath;
import org.hobsoft.symmetry.ui.traversal.ComponentFilter;
import org.hobsoft.symmetry.util.io.DefaultSerializerFactory;
import org.hobsoft.symmetry.xml.XmlRenderKit;

import static org.hobsoft.symmetry.ui.traversal.ComponentFilters.accept;
import static org.hobsoft.symmetry.ui.traversal.ComponentFilters.visible;
import static org.hobsoft.symmetry.ui.traversal.ComponentVisitors.filter;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class HtmlRenderKit extends XmlRenderKit<Component>
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_CONTENT_TYPE = "text/html";
	
	// fields -----------------------------------------------------------------
	
	private final ComponentHydrator hydrator;
	
	// constructors -----------------------------------------------------------
	
	public HtmlRenderKit()
	{
		this(DEFAULT_CONTENT_TYPE);
	}
	
	public HtmlRenderKit(String contentType)
	{
		this(contentType, new HtmlComponentHydrator());
	}
	
	public HtmlRenderKit(String contentType, ComponentHydrator hydrator)
	{
		super(Component.class, contentType);
		
		this.hydrator = hydrator;
	}
	
	// AbstractRenderKit methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preDehydrate(Component component, DehydrationContext context) throws HydrationException
	{
		super.preDehydrate(component, context);
		
		HtmlDocument document = new HtmlDocument();
		document.addExternalCss("kozo/themes/blue/stylesheet.css");
		// TODO: reinstate when alternate stylesheets are supported again
//		stylesheets.put("Kozo Green", "kozo/themes/green/stylesheet.css");
		document.addExternalJavaScript("kozo/themes/common/javascript.js");
		document.addHttpMetadata("Content-Type", getContentType());
		context.set(HtmlDocument.class, document);
		
		HtmlTreeDehydrator.Parameters treeParameters = new HtmlTreeDehydrator.Parameters();
		treeParameters.setCollapseImageUri("kozo/themes/common/images/collapse.png");
		treeParameters.setExpandImageUri("kozo/themes/common/images/expand.png");
		context.set(HtmlTreeDehydrator.Parameters.class, treeParameters);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected StateCodec createStateCodec(Component component, HydrationContext context)
	{
		IntegerIdComponentVisitor idVisitor = new IntegerIdComponentVisitor();
		component.accept(idVisitor, null);
		Map<Component, Integer> idsByComponent = idVisitor.getIdsByComponent();
		Codec<Object, Integer> componentCodec = Codecs.forMap(new HashMap<Object, Integer>(idsByComponent));
		
		DefaultSerializerFactory serializerFactory = new DefaultSerializerFactory();
		serializerFactory.setSerializer(TreePath.class, new TreePathSerializer());
		
		StateCodec stateCodec = new Base64StateCodec(componentCodec, new JdkBeanIntrospector(), serializerFactory);
//		StateCodec stateCodec = new ClassicStateCodec(componentCodec, new JdkBeanIntrospector());
		stateCodec = new HtmlFormStateCodec(stateCodec);
		stateCodec = new FormHtmlEventStateCodec(stateCodec);
		stateCodec = new HyperlinkableClosureStateCodec(stateCodec);
		
		return stateCodec;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateImpl(Component component, DehydrationContext context) throws HydrationException
	{
		component.accept(filter(hydrator, getDehydrationFilter()), context);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void rehydrateImpl(Component component, RehydrationContext context) throws HydrationException
	{
		component.accept(filter(hydrator, getRehydrationFilter()), context);
	}
	
	// private methods --------------------------------------------------------
	
	private ComponentFilter getDehydrationFilter()
	{
		return visible();
	}
	
	private ComponentFilter getRehydrationFilter()
	{
		return accept();
	}
}
