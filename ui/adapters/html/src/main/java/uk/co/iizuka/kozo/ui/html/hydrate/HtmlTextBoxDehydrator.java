/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTextBoxDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.common.BeanDehydrationUtils.encodePropertyValue;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeClass;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeEnabled;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeName;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeReadOnly;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeAttributeIf;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeAttributeIfNotEmpty;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeId;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.css.CssClassBuilder;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Style;
import uk.co.iizuka.kozo.ui.TextBox;
import uk.co.iizuka.kozo.ui.common.hydrate.NullHierarchicalComponentHydrator;

/**
 * Hydrator that dehydrates a {@code TextBox} component to an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTextBoxDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see TextBox
 * @param <T>
 *            the text box type this visitor can visit
 */
public class HtmlTextBoxDehydrator<T extends TextBox> extends NullHierarchicalComponentHydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T textBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeEmptyElement("input");
			
			writeClass(out, getTextBoxCssClass(textBox));
			writeId(context, textBox);
			writeName(context, textBox);
			out.writeAttribute("type", "text");
			
			String encodedValue = encodePropertyValue(context, textBox, TextBox.TEXT_PROPERTY, textBox.getText());
			writeAttributeIfNotEmpty(out, "value", encodedValue);
			
			out.writeAttribute("size", Integer.toString(textBox.getColumns()));
			
			int maxLength = textBox.getMaxLength();
			writeAttributeIf(out, "maxlength", Integer.toString(maxLength), maxLength < Integer.MAX_VALUE);
			
			writeEnabled(out, textBox);
			writeReadOnly(out, textBox.isReadOnly());
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return SKIP_CHILDREN;
	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getTextBoxCssClass(T textBox)
	{
		CssClassBuilder builder = new CssClassBuilder("textbox");
		
		if (textBox.hasStyle(Style.ERROR))
		{
			builder.append("textbox-error");
		}
		
		return builder;
	}
}
