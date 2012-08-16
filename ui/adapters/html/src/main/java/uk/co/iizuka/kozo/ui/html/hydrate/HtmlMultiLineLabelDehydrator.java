/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlMultiLineLabelDehydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.MultiLineLabel;

/**
 * Hydrator that dehydrates a {@code MultiLineLabel} component to an HTML {@code <p/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlMultiLineLabelDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see MultiLineLabel
 * @param <T>
 *            the multi-line label type this visitor can visit
 */
public class HtmlMultiLineLabelDehydrator<T extends MultiLineLabel> extends HtmlLabelDehydrator<T>
{
	// constants --------------------------------------------------------------
	
	private static final String NEWLINE_PATTERN = "\r\n|[\n\r]";

	// HtmlLabelDehydrator methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateLabelText(T label, HydrationContext context) throws HydrationException, XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		String text = label.getText();

		boolean first = true;
		
		for (String line : text.split(NEWLINE_PATTERN, -1))
		{
			if (!first)
			{
				out.writeEmptyElement("br");
			}
			
			out.writeCharacters(line);
			
			first = false;
		}
	}
}
