/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlPasswordBoxDehydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeClass;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeEnabled;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeName;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeReadOnly;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeAttributeIf;
import static uk.co.iizuka.kozo.xml.XmlUtils.writeId;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.css.CssClassBuilder;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.PasswordBox;
import uk.co.iizuka.kozo.ui.Style;
import uk.co.iizuka.kozo.ui.common.hydrate.NullHierarchicalComponentHydrator;

/**
 * Hydrator that dehydrates a {@code PasswordBox} component to an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlPasswordBoxDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see PasswordBox
 * @param <T>
 *            the password box type this visitor can visit
 */
public class HtmlPasswordBoxDehydrator<T extends PasswordBox> extends NullHierarchicalComponentHydrator<T>
{
	// TODO: extend HtmlTextBoxDehydrator
	
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T passwordBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeEmptyElement("input");
			
			writeClass(out, getPasswordBoxCssClass(passwordBox));
			writeId(context, passwordBox);
			writeName(context, passwordBox);
			out.writeAttribute("type", "password");
			
			// don't write @value for security reasons
			
			out.writeAttribute("size", Integer.toString(passwordBox.getColumns()));
			
			int maxLength = passwordBox.getMaxLength();
			writeAttributeIf(out, "maxlength", Integer.toString(maxLength), maxLength < Integer.MAX_VALUE);
			
			writeEnabled(out, passwordBox);
			writeReadOnly(out, passwordBox.isReadOnly());
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return SKIP_CHILDREN;
	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getPasswordBoxCssClass(T passwordBox)
	{
		CssClassBuilder builder = new CssClassBuilder("passwordbox");
		
		if (passwordBox.hasStyle(Style.ERROR))
		{
			builder.append("passwordbox-error");
		}
		
		return builder;
	}
}
