/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/editor/AbstractPropertyEditor.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.editor;

import org.hobsoft.symmetry.support.bean.GenericPropertyEditorSupport;
import org.hobsoft.symmetry.ui.Component;

/**
 * 
 * 
 * @author Mark Hobson
 */
public abstract class AbstractPropertyEditor extends GenericPropertyEditorSupport
{
	// fields -----------------------------------------------------------------
	
	private Component component;
	
	// constructors -----------------------------------------------------------
	
	public AbstractPropertyEditor()
	{
		this(null);
	}
	
	public AbstractPropertyEditor(Component component)
	{
		setComponent(component);
	}
	
	// GenericPropertyEditorSupport methods -----------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supportsCustomEditor(Class<?> componentClass)
	{
		return (componentClass == Component.class) ? true : super.supportsCustomEditor(componentClass);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getCustomEditor(Class<?> componentClass)
	{
		return (componentClass == Component.class) ? component : super.getCustomEditor(componentClass);
	}
	
	// protected methods ------------------------------------------------------
	
	protected Component getComponent()
	{
		return component;
	}
	
	protected void setComponent(Component component)
	{
		this.component = component;
	}
}
