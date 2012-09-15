/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/GroupBox.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import static com.google.common.base.Strings.nullToEmpty;

import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class GroupBox extends Box
{
	// constants --------------------------------------------------------------
	
	public static final String TITLE_PROPERTY = "title";
	
	// fields -----------------------------------------------------------------
	
	private String title;
	
	// constructors -----------------------------------------------------------
	
	public GroupBox()
	{
		this("");
	}
	
	public GroupBox(String title)
	{
		this(title, EMPTY_COMPONENT_ARRAY);
	}
	
	public GroupBox(String title, Component... children)
	{
		setTitle(title);
		add(children);
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return acceptContainer(visitor, GroupBox.class, this, parameter);
	}
	
	// public methods ---------------------------------------------------------
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		String oldTitle = this.title;
		this.title = nullToEmpty(title);
		firePropertyChange(TITLE_PROPERTY, oldTitle, title);
	}
}
