/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/traversal/DelegatingListBoxVisitor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.traversal;

import org.hobsoft.symmetry.ui.ComboBox;

/**
 * 
 * 
 * @author Mark Hobson
 * @param <T>
 *            the component type this visitor can visit
 * @param <P>
 *            the parameter type this visitor takes
 * @param <E>
 *            the exception type this visitor can throw
 */
public abstract class DelegatingListBoxVisitor<T extends ComboBox<?>, P, E extends Exception>
	extends DelegatingHierarchicalComponentVisitor<T, P, E>
	implements ListBoxVisitor<T, P, E>
{
	// constructors -----------------------------------------------------------
	
	public DelegatingListBoxVisitor(ListBoxVisitor<? super T, P, E> delegate)
	{
		super(delegate);
	}
	
	// ListBoxVisitor methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public HierarchicalComponentVisitor.Visit visitItem(T comboBox, P parameter, int itemIndex) throws E
	{
		return getDelegate(comboBox, parameter).visitItem(comboBox, parameter, itemIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public HierarchicalComponentVisitor.EndVisit endVisitItem(T comboBox, P parameter, int itemIndex) throws E
	{
		return getDelegate(comboBox, parameter).endVisitItem(comboBox, parameter, itemIndex);
	}
	
	// DelegatingHierarchicalComponentVisitor methods -------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListBoxVisitor<? super T, P, E> getDelegate(T comboBox, P parameter)
	{
		return (ListBoxVisitor<? super T, P, E>) super.getDelegate(comboBox, parameter);
	}
}
