/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlHtmlLabelDehydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.common.xml.stax.filter.EventFilters.not;
import static uk.co.iizuka.common.xml.stax.filter.EventFilters.or;
import static uk.co.iizuka.common.xml.stax.filter.EventFilters.wellFormed;
import static uk.co.iizuka.common.xml.stax.filter.TypeEventFilters.element;
import static uk.co.iizuka.common.xml.stax.transform.EventTransformers.characters;
import static uk.co.iizuka.common.xml.stax.transform.EventTransformers.compose;
import static uk.co.iizuka.common.xml.stax.transform.EventTransformers.identity;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeHtmlFragment;

import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.common.xml.stax.EventTransformer;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.HtmlLabel;
import uk.co.iizuka.kozo.ui.html.HtmlFilters;

/**
 * Hydrator that dehydrates an {@code HtmlLabel} component to an HTML {@code <p/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlHtmlLabelDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see HtmlLabel
 * @param <T>
 *            the HTML label type this visitor can visit
 */
public class HtmlHtmlLabelDehydrator<T extends HtmlLabel> extends HtmlLabelDehydrator<T>
{
	// HtmlLabelDehydrator methods --------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void dehydrateLabelText(T label, HydrationContext context) throws HydrationException, XMLStreamException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		String text = label.getText();
		
		try
		{
			writeHtmlFragment(out, text, getHtmlTransformer());
		}
		catch (XMLStreamException exception)
		{
			// ignore and proceed to write as characters
			
			// TODO: invalid xml is still written out to the point of the error, so it gets partially duplicated
			// when written as characters - need to buffer
			
			super.dehydrateLabelText(label, context);
		}
	}
	
	// protected methods ------------------------------------------------------
	
	protected EventFilter getHtmlElementFilter()
	{
		return HtmlFilters.SAFE;
	}
	
	// private methods --------------------------------------------------------
	
	private EventTransformer getHtmlTransformer()
	{
		// allow non-elements or elements accepted by filter, keeping start/end well-formed
		EventFilter filter = or(
			not(element()),
			wellFormed(getHtmlElementFilter())
		);
		
		// pass-through if accepted by filter, otherwise serialise to characters 
		return compose(filter, identity(), characters());
	}
}
