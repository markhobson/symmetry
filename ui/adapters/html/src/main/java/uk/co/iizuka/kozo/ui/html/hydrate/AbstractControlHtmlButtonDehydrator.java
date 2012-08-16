/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/AbstractControlHtmlButtonDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.common.BeanDehydrationUtils.encodeState;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeClass;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeEnabled;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeMnemonic;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeName;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeToolTip;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeAttributeIfNotEmpty;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeId;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.hydrate.DehydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Button;
import uk.co.iizuka.kozo.ui.event.ActionEvent;
import uk.co.iizuka.kozo.ui.html.state.HtmlEventStateCodec;

/**
 * Base hydrator for dehydrating a {@code Button} component to an HTML form control.
 * 
 * @author Mark Hobson
 * @version $Id: AbstractControlHtmlButtonDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see Button
 * @param <T>
 *            the button type this visitor can visit
 */
public abstract class AbstractControlHtmlButtonDehydrator<T extends Button> extends AbstractHtmlButtonDehydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T button, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			dehydrateButtonStart(button, context);
			writeId(context, button);
			writeClass(out, getButtonCssClass(button));
			writeName(context, button);
			out.writeAttribute("type", getButtonType(button));
			writeEnabled(out, button);
			
			if (button.isEnabled())
			{
				writeMnemonic(out, button.getMnemonic());
				writeToolTip(out, button.getToolTip());
				writeAttributeIfNotEmpty(out, "onclick", getOnClick(button, (DehydrationContext) context));
			}
			
			dehydrateButtonImage(button, context);
			dehydrateButtonText(button, context);

			dehydrateButtonEnd(button, context);
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
				
		return SKIP_CHILDREN;
	}
	
	// protected methods ------------------------------------------------------
	
	protected abstract void dehydrateButtonStart(T button, HydrationContext context) throws HydrationException,
		XMLStreamException;
	
	protected String getButtonType(T button)
	{
		return "button";
	}
	
	protected String getOnClick(T button, DehydrationContext context) throws HydrationException
	{
		if (button.getActionListenerCount() > 0)
		{
			DehydrationContext subcontext = new DehydrationContext(context);
			subcontext.getOrSet(HtmlEventStateCodec.Parameters.class, new HtmlEventStateCodec.Parameters())
				.setEvent(true);

			return encodeState(subcontext, Button.ACTION_EVENT, new ActionEvent(button));
		}
		
		return null;
	}
	
	protected abstract void dehydrateButtonImage(T button, HydrationContext context) throws HydrationException,
		XMLStreamException;

	protected abstract void dehydrateButtonText(T button, HydrationContext context) throws HydrationException,
		XMLStreamException;
	
	protected abstract void dehydrateButtonEnd(T button, HydrationContext context) throws HydrationException,
		XMLStreamException;
}
