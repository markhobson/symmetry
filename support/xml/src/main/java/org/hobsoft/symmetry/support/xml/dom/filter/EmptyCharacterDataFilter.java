/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
