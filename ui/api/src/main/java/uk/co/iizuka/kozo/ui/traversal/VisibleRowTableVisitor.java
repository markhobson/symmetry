/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/VisibleRowTableVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit.VISIT_SIBLINGS;
import static uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.Visit.SKIP_CHILDREN;

import uk.co.iizuka.kozo.ui.Table;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: VisibleRowTableVisitor.java 100647 2012-04-23 10:04:20Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class VisibleRowTableVisitor<T extends Table, P, E extends Exception> extends DelegatingTableVisitor<T, P, E>
{
	// constructors -----------------------------------------------------------
	
	public VisibleRowTableVisitor(TableVisitor<? super T, P, E> delegate)
	{
		super(delegate);
	}
	
	// TableVisitor methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRow(T table, P parameter, int rowIndex) throws E
	{
		if (!isRowVisible(table, rowIndex))
		{
			return SKIP_CHILDREN;
		}
		
		return super.visitRow(table, parameter, rowIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRow(T table, P parameter, int rowIndex) throws E
	{
		// TODO: skip siblings once last visible row encountered
		
		if (!isRowVisible(table, rowIndex))
		{
			return VISIT_SIBLINGS;
		}

		return super.endVisitRow(table, parameter, rowIndex);
	}
	
	// private methods --------------------------------------------------------
	
	private static boolean isRowVisible(Table table, int row)
	{
		int pageSize = table.getVisibleRowCount();
		
		if (pageSize == 0)
		{
			return true;
		}
		
		int firstRow = table.getFirstVisibleRowIndex();
		int lastRow = firstRow + pageSize - 1;
		
		return (row >= firstRow && row <= lastRow);
	}
}
