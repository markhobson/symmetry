/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/DelegatingTableVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.Table;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: DelegatingTableVisitor.java 100087 2012-03-30 10:31:08Z mark@IIZUKA.CO.UK $
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public abstract class DelegatingTableVisitor<T extends Table, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
	implements TableVisitor<T, P, E>
{
	// constructors -----------------------------------------------------------
	
	public DelegatingTableVisitor(TableVisitor<? super T, P, E> delegate)
	{
		super(delegate);
	}
	
	// TableVisitor methods ---------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeader(T table, P parameter) throws E
	{
		return getDelegate(table, parameter).visitHeader(table, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitHeaderCell(T table, P parameter, int columnIndex) throws E
	{
		return getDelegate(table, parameter).visitHeaderCell(table, parameter, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeaderCell(T table, P parameter, int columnIndex) throws E
	{
		return getDelegate(table, parameter).endVisitHeaderCell(table, parameter, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitHeader(T table, P parameter) throws E
	{
		return getDelegate(table, parameter).endVisitHeader(table, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitBody(T table, P parameter) throws E
	{
		return getDelegate(table, parameter).visitBody(table, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitRow(T table, P parameter, int rowIndex) throws E
	{
		return getDelegate(table, parameter).visitRow(table, parameter, rowIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitCell(T table, P parameter, int rowIndex, int columnIndex) throws E
	{
		return getDelegate(table, parameter).visitCell(table, parameter, rowIndex, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitCell(T table, P parameter, int rowIndex, int columnIndex) throws E
	{
		return getDelegate(table, parameter).endVisitCell(table, parameter, rowIndex, columnIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitRow(T table, P parameter, int rowIndex) throws E
	{
		return getDelegate(table, parameter).endVisitRow(table, parameter, rowIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitBody(T table, P parameter) throws E
	{
		return getDelegate(table, parameter).endVisitBody(table, parameter);
	}
	
	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TableVisitor<? super T, P, E> getDelegate(T table, P parameter)
	{
		return (TableVisitor<? super T, P, E>) super.getDelegate(table, parameter);
	}
}
