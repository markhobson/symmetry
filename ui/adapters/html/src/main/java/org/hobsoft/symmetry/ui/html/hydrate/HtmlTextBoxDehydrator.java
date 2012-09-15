/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTextBoxDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.css.CssClassBuilder;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Style;
import org.hobsoft.symmetry.ui.TextBox;
import org.hobsoft.symmetry.ui.common.hydrate.NullHierarchicalComponentHydrator;

import static org.hobsoft.symmetry.ui.common.BeanDehydrationUtils.encodePropertyValue;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeEnabled;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeName;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeReadOnly;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIf;
import static org.hobsoft.symmetry.xml.XmlUtils.writeAttributeIfNotEmpty;
import static org.hobsoft.symmetry.xml.XmlUtils.writeId;

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
