/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/event/TableEvent.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.kozo.ui.event;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import uk.co.iizuka.kozo.ui.Table;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: TableEvent.java 88174 2011-05-18 09:26:41Z mark@IIZUKA.CO.UK $
 */
public final class TableEvent extends ComponentEvent
{
	// constants --------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	// fields -----------------------------------------------------------------
	
	private int row;
	
	private int column;
	
	// constructors -----------------------------------------------------------
	
	public TableEvent(Table source, int column)
	{
		this(source, -1, column);
	}
	
	public TableEvent(Table source, int row, int column)
	{
		super(source);
		
		this.row = row;
		this.column = column;
	}
	
	// EventObject methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Table getSource()
	{
		return (Table) super.getSource();
	}
	
	// public methods ---------------------------------------------------------
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int hashCode = super.hashCode();
		hashCode = (hashCode * 31) + row;
		hashCode = (hashCode * 31) + column;
		return hashCode;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof TableEvent))
		{
			return false;
		}
		
		TableEvent event = (TableEvent) object;
		
		return super.equals(event)
			&& row == event.row
			&& column == event.column;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[source=" + getSource() + ",row=" + row + ",column=" + column + "]";
	}
	
	// Serializable methods ---------------------------------------------------
	
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		boolean hasRow = (row != -1);
		out.writeBoolean(hasRow);
		
		if (hasRow)
		{
			out.writeInt(row);
		}
		
		out.writeByte(column);
	}
	
	private void readObject(ObjectInputStream in) throws IOException
	{
		boolean hasRow = in.readBoolean();
		row = hasRow ? in.readInt() : -1;
		column = in.readUnsignedByte();
	}
}
