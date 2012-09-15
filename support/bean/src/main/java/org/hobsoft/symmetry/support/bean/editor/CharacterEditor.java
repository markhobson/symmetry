/*
 * $HeadURL: https://svn.iizuka.co.uk/common/bean/tags/0.4.0-beta-1/src/main/java/uk/co/iizuka/common/bean/editor/CharacterEditor.java $
 * 
 * (c) 2011 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.bean.editor;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class CharacterEditor extends PropertyEditorSupport2
{
	// TODO: make package private?
	
	// PropertyEditor methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAsText()
	{
		Object value = getValue();
		
		return (value != null) ? value.toString() : null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAsText(String text)
	{
		Character value;
		
		if (text == null)
		{
			value = null;
		}
		else if (text.length() == 1)
		{
			value = Character.valueOf(text.charAt(0));
		}
		else
		{
			throw new IllegalArgumentException("Cannot parse character: " + text);
		}
		
		setValue(value);
	}
}
