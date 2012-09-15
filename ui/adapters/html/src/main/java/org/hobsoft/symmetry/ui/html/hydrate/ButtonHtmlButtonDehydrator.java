/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/ButtonHtmlButtonDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Button;

import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeImage;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeText;

/**
 * Hydrator that dehydrates a {@code Button} component to an HTML {@code <button/>} tag.
 * 
 * @author Mark Hobson
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
