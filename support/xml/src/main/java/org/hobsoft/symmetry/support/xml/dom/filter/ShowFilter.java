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

import org.hobsoft.symmetry.support.xml.dom.TraversalUtils;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class ShowFilter implements NodeFilter
{
	// fields -----------------------------------------------------------------
	
	private final int whatToShow;
	
	// constructors -----------------------------------------------------------
	
	public ShowFilter(int whatToShow)
	{
		this.whatToShow = whatToShow;
	}
	
	// NodeFilter methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public short acceptNode(Node node)
	{
		return TraversalUtils.isVisible(node, whatToShow) ? FILTER_ACCEPT : FILTER_SKIP;
	}
	
	// Object methods ---------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "show[" + TraversalUtils.getWhatToShowName(whatToShow) + "]";
	}
}
