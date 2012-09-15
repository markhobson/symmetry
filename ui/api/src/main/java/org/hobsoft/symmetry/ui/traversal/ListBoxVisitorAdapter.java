/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/ListBoxVisitorAdapter.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.ComboBox;

/**
 * Adapts a {@code HierarchicalComponentVisitor} to a {@code ListBoxVisitor}.
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
class ListBoxVisitorAdapter<T extends ComboBox<?>, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
	implements ListBoxVisitor<T, P, E>
{
	// fields -----------------------------------------------------------------
	
	private final ListBoxVisitor<? super T, P, E> listBoxDelegate;

	// constructors -----------------------------------------------------------
	
	public ListBoxVisitorAdapter(HierarchicalComponentVisitor<? super T, P, E> delegate)
	{
		this(delegate, ComponentVisitors.<T, P, E>nullListBoxVisitor());
	}
	
	public ListBoxVisitorAdapter(HierarchicalComponentVisitor<? super T, P, E> delegate,
		ListBoxVisitor<? super T, P, E> listBoxDelegate)
	{
		super(delegate);
		
		this.listBoxDelegate = listBoxDelegate;
	}
	
	// ListBoxVisitor methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Visit visitItem(T comboBox, P parameter, int itemIndex) throws E
	{
		return listBoxDelegate.visitItem(comboBox, parameter, itemIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndVisit endVisitItem(T comboBox, P parameter, int itemIndex) throws E
	{
		return listBoxDelegate.endVisitItem(comboBox, parameter, itemIndex);
	}
}
