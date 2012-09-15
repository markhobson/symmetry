/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/GridVisitorAdapter.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Grid;

/**
 * Adapts a {@code ContainerVisitor} to a {@code GridVisitor}.
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class GridVisitorAdapter<T extends Grid, P, E extends Exception>
	extends DelegatingContainerVisitor<T, P, E>
	implements GridVisitor<T, P, E>
{
	// fields -----------------------------------------------------------------
	
	private final GridVisitor<? super T, P, E> gridDelegate;

	// constructors -----------------------------------------------------------
	
	public GridVisitorAdapter(ContainerVisitor<? super T, P, E> delegate)
	{
		this(delegate, ComponentVisitors.<T, P, E>nullGridVisitor());
	}
	
	public GridVisitorAdapter(ContainerVisitor<? super T, P, E> delegate, GridVisitor<? super T, P, E> gridDelegate)
	{
		super(delegate);
		
		this.gridDelegate = gridDelegate;
	}
	
	// GridVisitor methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitColumns(T grid, P parameter) throws E
	{
		return gridDelegate.visitColumns(grid, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit visitColumn(T grid, P parameter, int columnIndex) throws E
	{
		return gridDelegate.visitColumn(grid, parameter, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitColumns(T grid, P parameter) throws E
	{
		return gridDelegate.endVisitColumns(grid, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRows(T grid, P parameter) throws E
	{
		return gridDelegate.visitRows(grid, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRow(T grid, P parameter, int rowIndex) throws E
	{
		return gridDelegate.visitRow(grid, parameter, rowIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRow(T grid, P parameter, int rowIndex) throws E
	{
		return gridDelegate.endVisitRow(grid, parameter, rowIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRows(T grid, P parameter) throws E
	{
		return gridDelegate.endVisitRows(grid, parameter);
	}
}
