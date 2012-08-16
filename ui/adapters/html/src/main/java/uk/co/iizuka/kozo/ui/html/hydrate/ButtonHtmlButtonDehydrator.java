/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/ButtonHtmlButtonDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeImage;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeText;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Button;

/**
 * Hydrator that dehydrates a {@code Button} component to an HTML {@code <button/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: ButtonHtmlButtonDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see Button
 * @param <T>
 *            the button type this visitor can visit
 */
public class ButtonHtmlButtonDehydrator<T extends Button> extends AbstractControlHtmlButtonDehydrator<T>
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

		out.writeStartElement("button");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateButtonImage(T button, HydrationContext context) throws HydrationException,
		XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		writeImage(out, button.getImage());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateButtonText(T button, HydrationContext context) throws HydrationException,
		XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		writeText(out, button.getText(), button.getMnemonic());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateButtonEnd(T button, HydrationContext context) throws HydrationException,
		XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);

		// button
		out.writeEndElement();
	}
}
