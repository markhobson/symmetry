/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/TableVisitorAdapter.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Table;

/**
 * Adapts a {@code HierarchicalComponentVisitor} to a {@code TableVisitor}.
 * 
 * @author Mark Hobson
 * @version $Id: TableVisitorAdapter.java 100087 2012-03-30 10:31:08Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class TableVisitorAdapter<T extends Table, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
	implements TableVisitor<T, P, E>
{
	// fields -----------------------------------------------------------------
	
	private final TableVisitor<? super T, P, E> tableDelegate;

	// constructors -----------------------------------------------------------
	
	public TableVisitorAdapter(HierarchicalComponentVisitor<? super T, P, E> delegate)
	{
		this(delegate, ComponentVisitors.<T, P, E>nullTableVisitor());
	}
	
	public TableVisitorAdapter(HierarchicalComponentVisitor<? super T, P, E> delegate,
		TableVisitor<? super T, P, E> tableDelegate)
	{
		super(delegate);
		
		this.tableDelegate = tableDelegate;
	}
	
	// TableVisitor methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeader(T table, P parameter) throws E
	{
		return tableDelegate.visitHeader(table, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeaderCell(T table, P parameter, int columnIndex) throws E
	{
		return tableDelegate.visitHeaderCell(table, parameter, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeaderCell(T table, P parameter, int columnIndex) throws E
	{
		return tableDelegate.endVisitHeaderCell(table, parameter, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeader(T table, P parameter) throws E
	{
		return tableDelegate.endVisitHeader(table, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitBody(T table, P parameter) throws E
	{
		return tableDelegate.visitBody(table, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRow(T table, P parameter, int rowIndex) throws E
	{
		return tableDelegate.visitRow(table, parameter, rowIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitCell(T table, P parameter, int rowIndex, int columnIndex) throws E
	{
		return tableDelegate.visitCell(table, parameter, rowIndex, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitCell(T table, P parameter, int rowIndex, int columnIndex) throws E
	{
		return tableDelegate.endVisitCell(table, parameter, rowIndex, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRow(T table, P parameter, int rowIndex) throws E
	{
		return tableDelegate.endVisitRow(table, parameter, rowIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitBody(T table, P parameter) throws E
	{
		return tableDelegate.endVisitBody(table, parameter);
	}
}
