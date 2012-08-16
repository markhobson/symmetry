/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/InputHtmlButtonDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.xml.XmlUtils.writeAttributeIfNotEmpty;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Button;

/**
 * Hydrator that dehydrates a {@code Button} component to an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: InputHtmlButtonDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see Button
 * @param <T>
 *            the button type this visitor can visit
 */
public class InputHtmlButtonDehydrator<T extends Button> extends AbstractControlHtmlButtonDehydrator<T>
{
	// AbstractControlHtmlButtonDehydrator methods ----------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateButtonStart(T button, HydrationContext context) throws HydrationException,
		XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);

		out.writeEmptyElement("input");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateButtonImage(T button, HydrationContext context) throws HydrationException,
		XMLStreamException
	{
		// input buttons do not support images 
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateButtonText(T button, HydrationContext context) throws HydrationException,
		XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		writeAttributeIfNotEmpty(out, "value", button.getText());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateButtonEnd(T button, HydrationContext context) throws HydrationException,
		XMLStreamException
	{
		// empty element
	}
}
