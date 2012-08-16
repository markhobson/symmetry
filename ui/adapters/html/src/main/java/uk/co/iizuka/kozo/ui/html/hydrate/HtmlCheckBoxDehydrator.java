/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlCheckBoxDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.common.BeanDehydrationUtils.encodePropertyValue;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeClass;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeEnabled;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeFor;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeMnemonic;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeName;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeSelected;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeText;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeToolTip;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeAttributeIfNotEqual;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeId;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.css.CssClassBuilder;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.CheckBox;
import uk.co.iizuka.kozo.ui.Selectable;
import uk.co.iizuka.kozo.ui.common.hydrate.NullHierarchicalComponentHydrator;

/**
 * Hydrator that dehydrates a {@code CheckBox} component to an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlCheckBoxDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see CheckBox
 * @param <T>
 *            the check box type this visitor can visit
 */
public class HtmlCheckBoxDehydrator<T extends CheckBox> extends NullHierarchicalComponentHydrator<T>
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_VALUE = "on";

	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T checkBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		String toolTip = checkBox.getToolTip();

		try
		{
			out.writeEmptyElement("input");
			writeClass(out, getCheckBoxCssClass(checkBox));
			
			writeId(context, checkBox);
			writeName(context, checkBox);
			
			String encodedValue = encodePropertyValue(context, checkBox, Selectable.SELECTED_PROPERTY,
				checkBox.isSelected());
			writeAttributeIfNotEqual(out, "value", encodedValue, DEFAULT_VALUE);
			
			out.writeAttribute("type", "checkbox");
			writeMnemonic(out, checkBox.getMnemonic());
			writeToolTip(out, toolTip);
			writeEnabled(out, checkBox);
			// TODO: should this be handled by writePropertyValue? 
			writeSelected(out, checkBox);
	
			// TODO: reimplement EnableClosure using property bindings
//			if (checkBox.getActionListenerCount() > 0)
//			{
//				EnableClosure enableClosure = ComponentUtils.getClosure(checkBox.getActionListeners(),
//					EnableClosure.class);
//				
//				if (enableClosure != null)
//				{
//					Enableable enableable = enableClosure.getEnableable();
//					String enableableId = XMLUtils.getId(context, enableable);
//					
//					out.writeAttribute("onchange", "document.getElementById('" + enableableId
//						+ "').disabled = !this.checked");
//				}
//			}
			
			String text = checkBox.getText();
			
			if (text.length() > 0)
			{
				out.writeStartElement("label");
				writeClass(out, getCheckBoxCssClass(checkBox));
				
				writeFor(context, checkBox);
				writeToolTip(out, toolTip);
				writeText(out, text, checkBox.getMnemonic());
				
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
	
	protected CssClassBuilder getCheckBoxCssClass(T checkBox)
	{
		CssClassBuilder builder = new CssClassBuilder("checkbox");
		
		if (!checkBox.isEnabled())
		{
			builder.append("checkbox-disabled");
		}
		
		return builder;
	}
}
