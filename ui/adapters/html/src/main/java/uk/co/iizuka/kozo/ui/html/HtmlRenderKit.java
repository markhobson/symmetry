/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/HtmlRenderKit.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html;

import static uk.co.iizuka.kozo.ui.traversal.ComponentFilters.accept;
import static uk.co.iizuka.kozo.ui.traversal.ComponentFilters.visible;
import static uk.co.iizuka.kozo.ui.traversal.ComponentVisitors.filter;

import java.util.HashMap;
import java.util.Map;

import uk.co.iizuka.common.bean.JdkBeanIntrospector;
import uk.co.iizuka.common.codec.Codec;
import uk.co.iizuka.common.codec.Codecs;
import uk.co.iizuka.kozo.hydrate.DehydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.hydrate.RehydrationContext;
import uk.co.iizuka.kozo.state.Base64StateCodec;
import uk.co.iizuka.kozo.state.StateCodec;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.common.hydrate.ComponentHydrator;
import uk.co.iizuka.kozo.ui.html.hydrate.HtmlComponentHydrator;
import uk.co.iizuka.kozo.ui.html.hydrate.HtmlTreeDehydrator;
import uk.co.iizuka.kozo.ui.html.state.FormHtmlEventStateCodec;
import uk.co.iizuka.kozo.ui.html.state.HtmlFormStateCodec;
import uk.co.iizuka.kozo.ui.html.state.HyperlinkableClosureStateCodec;
import uk.co.iizuka.kozo.ui.model.TreePath;
import uk.co.iizuka.kozo.ui.traversal.ComponentFilter;
import uk.co.iizuka.kozo.util.io.DefaultSerializerFactory;
import uk.co.iizuka.kozo.xml.XmlRenderKit;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: HtmlRenderKit.java 98897 2012-03-01 11:09:38Z mark@IIZUKA.CO.UK $
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
