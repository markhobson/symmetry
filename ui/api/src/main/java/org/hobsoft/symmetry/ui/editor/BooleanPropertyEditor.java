/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/editor/BooleanPropertyEditor.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui.editor;

import org.hobsoft.symmetry.ui.CheckBox;

/**
 * 
 * 
 * @author Mark Hobson
 * @version $Id: BooleanPropertyEditor.java 73508 2010-04-02 15:56:49Z mark@IIZUKA.CO.UK $
 */
public class BooleanPropertyEditor extends AbstractPropertyEditor
{
	// constructors -----------------------------------------------------------
	
	public BooleanPropertyEditor()
	{
		super(new CheckBox());
	}
	
	// AbstractPropertyEditor methods -----------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValue()
	{
		CheckBox checkBox = (CheckBox) getComponent();
		return Boolean.valueOf(checkBox.isSelected());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(Object value)
	{
		if (value != null && !(value instanceof Boolean))
		{
			throw new IllegalArgumentException("Boolean expected: " + value);
		}
		
		CheckBox checkBox = (CheckBox) getComponent();
		checkBox.setSelected(value == Boolean.TRUE);
	}
}
