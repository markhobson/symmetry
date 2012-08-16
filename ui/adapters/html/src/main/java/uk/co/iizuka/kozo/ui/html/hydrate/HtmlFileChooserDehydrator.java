/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/html/src/main/java/uk/co/iizuka/kozo/ui/html/hydrate/HtmlFileChooserDehydrator.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.html.hydrate;

import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeClass;
import static uk.co.iizuka.kozo.ui.html.HtmlUtils.writeName;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.css.CssClassBuilder;
import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.FileChooser;
import uk.co.iizuka.kozo.ui.common.hydrate.NullHierarchicalComponentHydrator;

/**
 * Hydrator that dehydrates a {@code FileChooser} component to an HTML {@code <input/>} tag.
 * 
 * @author Mark Hobson
 * @version $Id: HtmlFileChooserDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @see FileChooser
 * @param <T>
 *            the file chooser type this visitor can visit
 */
public class HtmlFileChooserDehydrator<T extends FileChooser> extends NullHierarchicalComponentHydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T fileChooser, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeEmptyElement("input");
			writeClass(out, getFileChooserCssClass(fileChooser));
			writeName(context, fileChooser);
			out.writeAttribute("type", "file");
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return SKIP_CHILDREN;
	}
	
	// protected methods ------------------------------------------------------
	
	protected CssClassBuilder getFileChooserCssClass(T fileChooser)
	{
		return new CssClassBuilder("filechooser");
	}
}
