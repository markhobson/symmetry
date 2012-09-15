/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/BeanEditorStrategy.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import java.beans.PropertyDescriptor;

/**
 * 
 * 
 * @author Mark Hobson
 */
public interface BeanEditorStrategy
{
	<T> T newBean(BeanEditor<T> editor);
	
	Object getPropertyValue(Object bean, PropertyDescriptor property);
	
	void setPropertyValue(Object bean, PropertyDescriptor property, Object value);
}
