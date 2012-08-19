/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/PropertyChangeSupport2.java $
 * 
 * (c) 2012 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package uk.co.iizuka.common.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

/**
 * Provides support for implementing bound JavaBeans.
 * <p>
 * This class differs from {@code PropertyChangeSupport} in that property change events are not fired when old and new
 * values are both {@code null}.
 * 
 * @author Mark Hobson
 * @version $Id: PropertyChangeSupport2.java 97518 2012-01-04 16:28:52Z mark@IIZUKA.CO.UK $
 * @see PropertyChangeSupport
 * @see <a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4763463">Bug 4763463</a>
 */
public class PropertyChangeSupport2 extends PropertyChangeSupport
{
	// constructors -----------------------------------------------------------
	
	public PropertyChangeSupport2(Object sourceBean)
	{
		super(sourceBean);
	}

	// PropertyChangeSupport methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void firePropertyChange(PropertyChangeEvent event)
	{
		if (event.getOldValue() != null || event.getNewValue() != null)
		{
			super.firePropertyChange(event);
		}
	}
}
