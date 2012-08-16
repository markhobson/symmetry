/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/TextArea.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui;

import static uk.co.iizuka.kozo.ui.internal.Preconditions.checkPositive;

import uk.co.iizuka.kozo.ui.traversal.ComponentVisitor;
import uk.co.iizuka.kozo.ui.traversal.HierarchicalComponentVisitor.EndVisit;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TextArea.java 99057 2012-03-08 12:29:24Z mark@IIZUKA.CO.UK $
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
