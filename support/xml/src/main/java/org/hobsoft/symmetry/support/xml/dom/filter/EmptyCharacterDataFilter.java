/*
 * $HeadURL: https://svn.iizuka.co.uk/common/xml/main/tags/0.12.0-beta-1/src/main/java/uk/co/iizuka/common/xml/dom/filter/EmptyCharacterDataFilter.java $
 * 
 * (c) 2006 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.support.xml.dom.filter;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

/**
 * A <code>NodeFilter</code> that accepts <code>CharacterData</code> nodes with no content and skips everything else.
 * 
 * @author Mark Hobson
 */
public class EmptyCharacterDataFilter implements NodeFilter
{
	// NodeFilter methods -----------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public short acceptNode(Node node)
	{
		if (!(node instanceof CharacterData))
		{
			return FILTER_SKIP;
		}
		
		CharacterData charData = (CharacterData) node;
		int length = charData.getLength();
		
		return (length == 0) ? FILTER_ACCEPT : FILTER_SKIP;
	}
}
