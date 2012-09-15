/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/InputHtmlButtonDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Button;

import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIfNotEmpty;

/**
 * Hydrator that dehydrates a {@code Button} component to an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
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
