/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/TextArea.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import org.hobsoft.symmetry.ui.traversal.ComponentVisitor;
import org.hobsoft.symmetry.ui.traversal.HierarchicalComponentVisitor.EndVisit;

import static org.hobsoft.symmetry.ui.internal.Preconditions.checkPositive;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class TextArea extends TextBox
{
	// constants --------------------------------------------------------------
	
	public static final String ROWS_PROPERTY = "rows";
	
	private static final int DEFAULT_COLUMNS = 20;
	
	private static final int DEFAULT_ROWS = 3;
	
	// fields -----------------------------------------------------------------
	
	private int rows;
	
	// constructors -----------------------------------------------------------
	
	public TextArea()
	{
		this(DEFAULT_COLUMNS);
	}

	public TextArea(int columns)
	{
		this("", columns);
	}

	public TextArea(String text)
	{
		this(text, DEFAULT_COLUMNS);
	}

	public TextArea(String text, int columns)
	{
		super(text, columns);
		
		setRows(DEFAULT_ROWS);
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return accept(visitor, TextArea.class, this, parameter);
	}

	// public methods ---------------------------------------------------------
	
	public int getRows()
	{
		return rows;
	}
	
	public void setRows(int rows)
	{
		checkPositive(rows, "rows");
		
		int oldRows = this.rows;
		this.rows = rows;
		firePropertyChange(ROWS_PROPERTY, oldRows, rows);
	}
}
