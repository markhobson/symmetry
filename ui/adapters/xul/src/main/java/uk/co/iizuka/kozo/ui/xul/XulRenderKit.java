/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/main/java/uk/co/iizuka/kozo/ui/xul/XulRenderKit.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xul;

import uk.co.iizuka.kozo.hydrate.DehydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.hydrate.RehydrationContext;
import uk.co.iizuka.kozo.state.NullStateCodec;
import uk.co.iizuka.kozo.state.StateCodec;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.common.hydrate.ComponentHydrator;
import uk.co.iizuka.kozo.ui.xul.hydrate.XulComponentHydrator;
import uk.co.iizuka.kozo.xml.XmlRenderKit;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: XulRenderKit.java 95658 2011-11-29 15:18:50Z mark@IIZUKA.CO.UK $
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
