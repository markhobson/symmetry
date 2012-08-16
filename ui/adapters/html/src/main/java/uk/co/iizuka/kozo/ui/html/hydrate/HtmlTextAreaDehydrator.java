/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTextAreaDehydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.common.BeanDehydrationUtils.encodePropertyValue;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeClass;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeEnabled;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeName;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeReadOnly;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeId;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.css.CssClassBuilder;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Style;
import uk.co.iizuka.kozo.ui.TextArea;
import uk.co.iizuka.kozo.ui.TextBox;

/**
 * Hydrator that dehydrates a {@code TextArea} component to an HTML {@code <textarea/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlTextAreaDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see TextArea
 * @param <T>
 *            the text area type this visitor can visit
 */
public class HtmlTextAreaDehydrator<T extends TextArea> extends HtmlTextBoxDehydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T textArea, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		int rows = textArea.getRows();
		
		try
		{
			out.writeStartElement("textarea");
			
			writeClass(out, getTextBoxCssClass(textArea));
			writeId(context, textArea);
			writeName(context, textArea);
			out.writeAttribute("cols", Integer.toString(textArea.getColumns()));
			out.writeAttribute("rows", Integer.toString(rows));
			writeEnabled(out, textArea);
			writeReadOnly(out, textArea.isReadOnly());
			
			String encodedValue = encodePropertyValue(context, textArea, TextBox.TEXT_PROPERTY, textArea.getText());
			out.writeCharacters(encodedValue);
			
			// textarea
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return SKIP_CHILDREN;
	}
	
	// HtmlTextBoxDehydrator methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CssClassBuilder getTextBoxCssClass(T textArea)
	{
		CssClassBuilder builder = new CssClassBuilder("textarea");
		
		if (textArea.hasStyle(Style.ERROR))
		{
			builder.append("textarea-error");
		}
		
		return builder;
	}
}
