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
