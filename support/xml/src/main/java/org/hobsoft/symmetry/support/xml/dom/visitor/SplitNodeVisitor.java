/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/visitor/SplitNodeVisitor.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.visitor;

import org.w3c.dom.Text;

/**
 * A node visitor that splits text nodes at a specified character.
 * 
 * @author Mark Hobson
 */
public class SplitNodeVisitor extends AbstractNodeVisitor
{
	// constants --------------------------------------------------------------
	
	/**
	 * The default character to split text nodes with.
	 */
	private static final char DEFAULT_SPLIT_CHAR = '|';
	
	// fields -----------------------------------------------------------------
	
	/**
	 * The character to split text nodes with.
	 */
	private final char splitChar;
	
	// constructors -----------------------------------------------------------
	
	/**
	 * Creates a new node visitor that splits text nodes at '<code>|</code>' character.
	 */
	public SplitNodeVisitor()
	{
		this(DEFAULT_SPLIT_CHAR);
	}
	
	/**
	 * Creates a new node visitor that splits text nodes at the specified character.
	 * 
	 * @param splitChar
	 *            the character to split text nodes with
	 */
	public SplitNodeVisitor(char splitChar)
	{
		this.splitChar = splitChar;
	}
	
	// NodeVisitor methods ----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean visitText(Text text)
	{
		String data = text.getData();
		
		int offset = data.indexOf(splitChar);
		
		if (offset != -1)
		{
			// split text and remove split char
			text.splitText(offset).deleteData(0, 1);
		}
		
		return true;
	}
}
