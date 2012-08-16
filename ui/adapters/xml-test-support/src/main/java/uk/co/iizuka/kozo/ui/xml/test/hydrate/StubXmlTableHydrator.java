/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/adapters/xml-test-support/src/main/java/uk/co/iizuka/kozo/ui/xml/test/hydrate/StubXmlTableHydrator.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.xml.test.hydrate;

import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.VISIT_CHILDREN;

import uk.co.iizuka.kozo.hydrate.HydrationContext;
import uk.co.iizuka.kozo.hydrate.HydrationException;
import uk.co.iizuka.kozo.ui.Table;
import uk.co.iizuka.kozo.ui.traversal.TableVisitor;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: StubXmlTableHydrator.java 100088 2012-03-30 10:37:50Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 */
class StubXmlTableHydrator<T extends Table>
	extends StubXmlHierarchicalComponentHydrator<T>
	implements TableVisitor<T, HydrationContext, HydrationException>
{
	// fields -----------------------------------------------------------------
	
	private final boolean stubCells;
	
	// constructors -----------------------------------------------------------
	
	public StubXmlTableHydrator(String text, boolean stubCells)
	{
		super(text);
		
		this.stubCells = stubCells;
	}
	
	// TableVisitor methods ---------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeader(T table, HydrationContext context) throws HydrationException
	{
		write("[" + getText() + ".header]", context);

		return stubCells ? VISIT_CHILDREN : SKIP_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeaderCell(T table, HydrationContext context, int columnIndex) throws HydrationException
	{
		write("[" + getText() + ".headerCell]", context);
		
		return VISIT_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeaderCell(T table, HydrationContext context, int columnIndex) throws HydrationException
	{
		write("[/" + getText() + ".headerCell]", context);

		return VISIT_SIBLINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeader(T table, HydrationContext context) throws HydrationException
	{
		write("[/" + getText() + ".header]", context);
		
		return VISIT_SIBLINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitBody(T table, HydrationContext context) throws HydrationException
	{
		write("[" + getText() + ".body]", context);
		
		return VISIT_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRow(T table, HydrationContext context, int rowIndex) throws HydrationException
	{
		write("[" + getText() + ".row]", context);

		return stubCells ? VISIT_CHILDREN : SKIP_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitCell(T table, HydrationContext context, int rowIndex, int columnIndex) throws HydrationException
	{
		write("[" + getText() + ".cell]", context);
		
		return VISIT_CHILDREN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitCell(T table, HydrationContext context, int rowIndex, int columnIndex)
		throws HydrationException
	{
		write("[/" + getText() + ".cell]", context);
		
		return VISIT_SIBLINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRow(T table, HydrationContext context, int rowIndex) throws HydrationException
	{
		write("[/" + getText() + ".row]", context);

		return VISIT_SIBLINGS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitBody(T table, HydrationContext context) throws HydrationException
	{
		write("[/" + getText() + ".body]", context);

		return VISIT_SIBLINGS;
	}
}
