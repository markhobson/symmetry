/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/GenericPropertyEditorSupport.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean;

import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class GenericPropertyEditorSupport extends PropertyEditorSupport implements GenericPropertyEditor
{
	// constructors -----------------------------------------------------------
	
	public GenericPropertyEditorSupport()
	{
		super();
	}
	
	public GenericPropertyEditorSupport(Object source)
	{
		super(source);
	}
	
	// GenericPropertyEditor methods ------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getCustomEditor(Class<?> componentClass)
	{
		return (componentClass == Component.class) ? super.getCustomEditor() : null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supportsCustomEditor(Class<?> componentClass)
	{
		return (componentClass == Component.class) ? super.supportsCustomEditor() : false;
	}
}
