/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/DelegatingGridVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.traversal;

import uk.co.iizuka.kozo.ui.Grid;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingGridVisitor.java 100092 2012-03-30 11:49:15Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public abstract class DelegatingGridVisitor<T extends Grid, P, E extends Exception>
	extends DelegatingContainerVisitor<T, P, E>
	implements GridVisitor<T, P, E>
{
	// constructors -----------------------------------------------------------
	
	public DelegatingGridVisitor(GridVisitor<? super T, P, E> delegate)
	{
		super(delegate);
	}

	// GridVisitor methods ----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitColumns(T grid, P parameter) throws E
	{
		return getDelegate(grid, parameter).visitColumns(grid, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit visitColumn(T grid, P parameter, int columnIndex) throws E
	{
		return getDelegate(grid, parameter).visitColumn(grid, parameter, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitColumns(T grid, P parameter) throws E
	{
		return getDelegate(grid, parameter).endVisitColumns(grid, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRows(T grid, P parameter) throws E
	{
		return getDelegate(grid, parameter).visitRows(grid, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HierarchicalComponentVisitor.Visit visitRow(T grid, P parameter, int rowIndex) throws E
	{
		return getDelegate(grid, parameter).visitRow(grid, parameter, rowIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public HierarchicalComponentVisitor.EndVisit endVisitRow(T grid, P parameter, int rowIndex) throws E
	{
		return getDelegate(grid, parameter).endVisitRow(grid, parameter, rowIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRows(T grid, P parameter) throws E
	{
		return getDelegate(grid, parameter).endVisitRows(grid, parameter);
	}
	
	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GridVisitor<? super T, P, E> getDelegate(T grid, P parameter)
	{
		return (GridVisitor<? super T, P, E>) super.getDelegate(grid, parameter);
	}
}
