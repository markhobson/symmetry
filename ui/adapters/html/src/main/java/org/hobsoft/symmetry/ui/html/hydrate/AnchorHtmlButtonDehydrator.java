/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/AnchorHtmlButtonDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.hydrate.DehydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Button;
import org.hobsoft.symmetry.ui.event.ActionEvent;

import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeHref;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeImage;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeMnemonic;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeText;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeToolTip;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

/**
 * Hydrator that dehydrates a {@code Button} component to an HTML {@code <a/>} tag.
 * 
 * @author Mark Hobson
 * @see Button
 * @param <T>
 *            the button type this visitor can visit
 */
public class AnchorHtmlButtonDehydrator<T extends Button> extends AbstractHtmlButtonDehydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T button, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		boolean enabled = button.isEnabled();
		
		try
		{
			out.writeStartElement("span");
			writeClass(out, getButtonCssClass(button));
			
			// TODO: where to dehydrate button id?
			// TODO: how to dehydrate button name?
			
			out.writeStartElement("a");
			
			if (enabled)
			{
				writeMnemonic(out, button.getMnemonic());
				
				writeToolTip(out, button.getToolTip());
				
				if (button.getActionListenerCount() > 0)
				{
					writeHref((DehydrationContext) context, Button.ACTION_EVENT, new ActionEvent(button));
				}
			}
			
			writeImage(out, button.getImage());
			writeText(out, button.getText(), button.getMnemonic());
			
			// a
			out.writeEndElement();
			
			// span
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}

		return SKIP_CHILDREN;
	}
}
