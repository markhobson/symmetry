/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xul/src/main/java/uk/co/iizuka/kozo/ui/xul/hydrate/XulGroupBoxDehydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xul.hydrate;

import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.GroupBox;
import uk.co.iizuka.kozo.ui.common.hydrate.NullHierarchicalComponentHydrator;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: XulGroupBoxDehydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 */
public class XulGroupBoxDehydrator<T extends GroupBox> extends NullHierarchicalComponentHydrator<T>
{
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T groupBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeStartElement("groupbox");
			
			String title = groupBox.getTitle();
			
			if (title.length() > 0)
			{
				out.writeEmptyElement("caption");
				out.writeAttribute("label", title);
			}
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}

		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisit(T groupBox, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);

		try
		{
			// groupbox
			out.writeEndElement();
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
		
		return VISIT_SIBLINGS;
	}
}
