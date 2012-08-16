/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xml-test-support/src/main/java/uk/co/iizuka/kozo/ui/xml/test/hydrate/StubXmlHierarchicalComponentHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xml.test.hydrate;

import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Component;
import uk.co.iizuka.kozo.ui.common.hydrate.NullHierarchicalComponentHydrator;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: StubXmlHierarchicalComponentHydrator.java 98843 2012-02-29 10:01:13Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 */
class StubXmlHierarchicalComponentHydrator<T extends Component> extends NullHierarchicalComponentHydrator<T>
{
	// fields -----------------------------------------------------------------
	
	private final String text;
	
	// constructors -----------------------------------------------------------
	
	public StubXmlHierarchicalComponentHydrator(String text)
	{
		this.text = text;
	}
	
	// HierarchicalComponentVisitor methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visit(T component, HydrationContext context) throws HydrationException
	{
		write("[" + getText() + "]", context);
		
		return VISIT_CHILDREN;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisit(T component, HydrationContext context) throws HydrationException
	{
		write("[/" + getText() + "]", context);

		return VISIT_SIBLINGS;
	}

	// protected methods ------------------------------------------------------
	
	protected final String getText()
	{
		return text;
	}
	
	protected static void write(String text, HydrationContext context) throws HydrationException
	{
		XMLStreamWriter out = context.get(XMLStreamWriter.class);
		
		try
		{
			out.writeCharacters(text);
		}
		catch (XMLStreamException exception)
		{
			throw new HydrationException(exception);
		}
	}
}
