/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/layout/GridLayoutData.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.layout;

import static com.google.common.base.Preconditions.checkNotNull;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.google.common.base.Objects;

import static org.hobsoft.symmetry.ui.internal.Preconditions.checkPositive;

/**
 * 
 * 
 * @author Mark Hobson
 */
public final class GridLayoutData
{
	// constants --------------------------------------------------------------
	
	public static final String HORIZONTAL_ALIGNMENT_PROPERTY = "horizontalAlignment";
	
	public static final String VERTICAL_ALIGNMENT_PROPERTY = "verticalAlignment";
	
	public static final String COLUMN_SPAN_PROPERTY = "columnSpan";
	
	private static final HorizontalAlignment DEFAULT_HORIZONTAL_ALIGNMENT = HorizontalAlignment.LEFT;
	
	private static final VerticalAlignment DEFAULT_VERTICAL_ALIGNMENT = VerticalAlignment.MIDDLE;
	
	private static final int DEFAULT_COLUMN_SPAN = 1;

	// fields -----------------------------------------------------------------
	
	private final PropertyChangeSupport support;
	
	private HorizontalAlignment horizontalAlignment;
	
	private VerticalAlignment verticalAlignment;
	
	private int columnSpan;
	
	// constructors -----------------------------------------------------------
	
	public GridLayoutData()
	{
		support = new PropertyChangeSupport(this);
		
		setHorizontalAlignment(DEFAULT_HORIZONTAL_ALIGNMENT);
		setVerticalAlignment(DEFAULT_VERTICAL_ALIGNMENT);
		setColumnSpan(DEFAULT_COLUMN_SPAN);
	}
	
	// public methods ---------------------------------------------------------
	
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		support.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		support.removePropertyChangeListener(listener);
	}
	
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		support.addPropertyChangeListener(propertyName, listener);
	}
	
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		support.removePropertyChangeListener(propertyName, listener);
	}
	
	public HorizontalAlignment getHorizontalAlignment()
	{
		return horizontalAlignment;
	}
	
	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment)
	{
		HorizontalAlignment oldHorizontalAlignment = this.horizontalAlignment;
		this.horizontalAlignment = checkNotNull(horizontalAlignment, "horizontalAlignment cannot be null");
		support.firePropertyChange(HORIZONTAL_ALIGNMENT_PROPERTY, oldHorizontalAlignment, this.horizontalAlignment);
	}
	
	public VerticalAlignment getVerticalAlignment()
	{
		return verticalAlignment;
	}
	
	public void setVerticalAlignment(VerticalAlignment verticalAlignment)
	{
		VerticalAlignment oldVerticalAlignment = this.verticalAlignment;
		this.verticalAlignment = checkNotNull(verticalAlignment, "verticalAlignment cannot be null");
		support.firePropertyChange(VERTICAL_ALIGNMENT_PROPERTY, oldVerticalAlignment, this.verticalAlignment);
	}
	
	public int getColumnSpan()
	{
		return columnSpan;
	}
	
	public void setColumnSpan(int columnSpan)
	{
		int oldColumnSpan = this.columnSpan;
		this.columnSpan = checkPositive(columnSpan, "columnSpan");
		support.firePropertyChange(COLUMN_SPAN_PROPERTY, oldColumnSpan, this.columnSpan);
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return Objects.hashCode(horizontalAlignment, verticalAlignment, columnSpan);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof GridLayoutData))
		{
			return false;
		}
		
		GridLayoutData layoutData = (GridLayoutData) object;
		
		return (horizontalAlignment == layoutData.horizontalAlignment)
			&& (verticalAlignment == layoutData.verticalAlignment)
			&& (columnSpan == layoutData.columnSpan);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return String.format("%s[horizontalAlignment=%s, verticalAlignment=%s, columnSpan=%d]", getClass().getName(),
			horizontalAlignment, verticalAlignment, columnSpan);
	}
}
