/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlHtmlLabelDehydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.support.xml.stax.EventTransformer;
import org.hobsoft.symmetry.ui.HtmlLabel;
import org.hobsoft.symmetry.ui.html.HtmlFilters;

import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.not;
import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.or;
import static org.hobsoft.symmetry.support.xml.stax.filter.EventFilters.wellFormed;
import static org.hobsoft.symmetry.support.xml.stax.filter.TypeEventFilters.element;
import static org.hobsoft.symmetry.support.xml.stax.transform.EventTransformers.characters;
import static org.hobsoft.symmetry.support.xml.stax.transform.EventTransformers.compose;
import static org.hobsoft.symmetry.support.xml.stax.transform.EventTransformers.identity;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeHtmlFragment;

/**
 * Hydrator that dehydrates an {@code HtmlLabel} component to an HTML {@code <p/>} tag.
 * 
 * @author Mark Hobson
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
