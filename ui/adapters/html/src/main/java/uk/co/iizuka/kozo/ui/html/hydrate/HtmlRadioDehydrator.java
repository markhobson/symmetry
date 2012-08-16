/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlRadioDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.common.BeanDehydrationUtils.encodeComponent;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeClass;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeEnabled;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeFor;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeMnemonic;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeName;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeSelected;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeText;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeToolTip;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeAttributeIfNotEmpty;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeId;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.css.CssClassBuilder;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.ComponentUtils;
import uk.co.iizuka.kozo.ui.Radio;
import uk.co.iizuka.kozo.ui.ToggleButtonGroup;
import uk.co.iizuka.kozo.ui.common.hydrate.NullHierarchicalComponentHydrator;

/**
 * Hydrator that dehydrates a {@code Radio} component to an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlRadioDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see Radio
 * @param <T>
 *            the component type this visitor can visit
 */
public class HtmlRadioDehydrator<T extends Radio> extends NullHierarchicalComponentHydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T radio, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		String toolTip = radio.getToolTip();
		
		Component groupComponent = ComponentUtils.getAncestor(radio, ToggleButtonGroup.class);
		
		if (groupComponent == null)
		{
			groupComponent = radio;
		}
		
		try
		{
			out.writeEmptyElement("input");
			writeClass(out, getRadioCssClass(radio));
			
			writeId(context, radio);
			writeName(context, groupComponent);
			out.writeAttribute("type", "radio");
			writeAttributeIfNotEmpty(out, "value", encodeComponent(context, radio));
			writeMnemonic(out, radio.getMnemonic());
			writeToolTip(out, toolTip);
			writeEnabled(out, radio);
			writeSelected(out, radio);
			
			String text = radio.getText();
			
			if (text.length() > 0)
			{
				out.writeStartElement("label");
				writeClass(out, getRadioCssClass(radio));
				
				writeFor(context, radio);
				writeToolTip(out, toolTip);
				writeText(out, text, radio.getMnemonic());
				
				// label
				out.writeEndElement();
			}
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return SKIP_CHILDREN;
	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getRadioCssClass(T radio)
	{
		CssClassBuilder builder = new CssClassBuilder("radio");
		
		if (!radio.isEnabled())
		{
			builder.append("radio-disabled");
		}
		
		return builder;
	}
}
