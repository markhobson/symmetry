/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.ui.event;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.hobsoft.symmetry.ui.Table;

/**
 * 
 * 
 * @author Mark Hobson
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
