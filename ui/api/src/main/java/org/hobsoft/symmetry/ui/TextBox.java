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

import static com.google.common.base.Strings.nullToEmpty;

/**
 * A component that provides editing for a single-line of text.
 * 
 * @author Mark Hobson
 */
public class TextBox extends Component implements Enableable
{
	// constants --------------------------------------------------------------
	
	/**
	 * JavaBean property name that identifies a change in the textbox's number of columns.
	 */
	public static final String COLUMNS_PROPERTY = "columns";
	
	public static final String MAX_LENGTH_PROPERTY = "maxLength";
	
	public static final String READ_ONLY_PROPERTY = "readOnly";
	
	/**
	 * JavaBean property name that identifies a change in the textbox's text.
	 */
	public static final String TEXT_PROPERTY = "text";
	
	private static final int DEFAULT_COLUMNS = 20;
	
	// TODO: use -1 instead for unlimited?
	private static final int DEFAULT_MAX_LENGTH = Integer.MAX_VALUE;
	
	private static final boolean DEFAULT_READ_ONLY = false;
	
	// fields -----------------------------------------------------------------
	
	/**
	 * The text displayed by this textbox.
	 */
	private String text;
	
	/**
	 * The maximum number of characters displayed by this textbox.
	 */
	private int columns;
	
	private int maxLength;
	
	private boolean enabled;
	
	private boolean readOnly;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new textbox with no text.
	 */
	public TextBox()
	{
		this("");
	}
	
	/**
	 * Creates a new textbox with the specified number of columns.
	 * 
	 * @param columns
	 *            the maximum number of characters displayed by this textbox
	 */
	public TextBox(int columns)
	{
		this("", columns);
	}
	
	/**
	 * Creates a new textbox with the specified text.
	 * 
	 * @param text
	 *            the text displayed by this textbox
	 */
	public TextBox(String text)
	{
		this(text, DEFAULT_COLUMNS);
	}
	
	/**
	 * Creates a new textbox with the specified text and number of columns.
	 * 
	 * @param text
	 *            the text displayed by this textbox
	 * @param columns
	 *            the maximum number of characters displayed by this textbox
	 */
	public TextBox(String text, int columns)
	{
		setText(text);
		setColumns(columns);
		setMaxLength(DEFAULT_MAX_LENGTH);
		setEnabled(true);
		setReadOnly(DEFAULT_READ_ONLY);
	}
	
	// Component methods ------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, E extends Exception> EndVisit accept(ComponentVisitor<P, E> visitor, P parameter) throws E
	{
		return accept(visitor, TextBox.class, this, parameter);
	}

	// public methods ---------------------------------------------------------
	
	/**
	 * Gets the text displayed by this textbox.
	 * 
	 * @return the text displayed by this textbox.
	 */
	public String getText()
	{
		return text;
	}
	
	/**
	 * Sets the text displayed by this textbox.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param text
	 *            the text displayed by this textbox
	 */
	public void setText(String text)
	{
		String oldText = this.text;
		this.text = nullToEmpty(text);
		firePropertyChange(TEXT_PROPERTY, oldText, this.text);
	}
	
	/**
	 * Gets the maximum number of characters displayed by this textbox.
	 * 
	 * @return the maximum number of characters displayed by this textbox
	 */
	public int getColumns()
	{
		return columns;
	}
	
	/**
	 * Sets the maximum number of characters displayed by this textbox.
	 * <p>
	 * This is a JavaBean bound property.
	 * 
	 * @param columns
	 *            the maximum number of characters displayed by this textbox
	 */
	public void setColumns(int columns)
	{
		checkPositive(columns, "columns");
		
		int oldColumns = this.columns;
		this.columns = columns;
		firePropertyChange(COLUMNS_PROPERTY, oldColumns, columns);
	}
	
	public int getMaxLength()
	{
		return maxLength;
	}
	
	public void setMaxLength(int maxLength)
	{
		checkPositive(maxLength, "maxLength");
		
		int oldMaxLength = this.maxLength;
		this.maxLength = maxLength;
		firePropertyChange(MAX_LENGTH_PROPERTY, oldMaxLength, maxLength);
	}
	
	public boolean isReadOnly()
	{
		return readOnly;
	}
	
	public void setReadOnly(boolean readOnly)
	{
		boolean oldReadOnly = this.readOnly;
		this.readOnly = readOnly;
		firePropertyChange(READ_ONLY_PROPERTY, oldReadOnly, this.readOnly);
	}
	
	// Enableable methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled()
	{
		return enabled;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEnabled(boolean enabled)
	{
		boolean oldEnabled = this.enabled;
		
		this.enabled = enabled;
		
		firePropertyChange(ENABLED_PROPERTY, oldEnabled, enabled);
	}
}
