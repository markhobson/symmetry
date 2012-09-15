/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/editor/StringPropertyEditor.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.editor;

import org.hobsoft.symmetry.ui.TextBox;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: StringPropertyEditor.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public class StringPropertyEditor extends AbstractPropertyEditor
{
	// constructors -----------------------------------------------------------
	
	public StringPropertyEditor()
	{
		super(new TextBox(40));
	}
	
	// AbstractPropertyEditor methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValue()
	{
		return getTextBox().getText();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(Object value)
	{
		getTextBox().setText((String) value);
	}
	
	// protected methods ------------------------------------------------------
	
	protected TextBox getTextBox()
	{
		return (TextBox) getComponent();
	}
}
