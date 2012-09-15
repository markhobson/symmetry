/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/GenericPropertyEditor.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

import java.beans.PropertyEditor;

/**
 * 
 * 
 * @author Mark Hobson
 */
public interface GenericPropertyEditor extends PropertyEditor
{
	// TODO: change to: <T> T getCustomEditor(Class<T> componentClass);
	Object getCustomEditor(Class<?> componentClass);
	
	boolean supportsCustomEditor(Class<?> componentClass);
}
