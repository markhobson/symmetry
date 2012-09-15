/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlTextAreaDehydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.html.hydrate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.hobsoft.symmetry.css.CssClassBuilder;
import org.hobsoft.symmetry.hydrate.HydrationContext;
import org.hobsoft.symmetry.hydrate.HydrationException;
import org.hobsoft.symmetry.ui.Style;
import org.hobsoft.symmetry.ui.TextArea;
import org.hobsoft.symmetry.ui.TextBox;

import static org.hobsoft.symmetry.ui.common.BeanDehydrationUtils.encodePropertyValue;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeClass;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeEnabled;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeName;
import static org.hobsoft.symmetry.ui.html.HtmlUtils.writeReadOnly;
import static org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static org.hobsoft.symmetry.xml.XmlUtils.writeId;

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
