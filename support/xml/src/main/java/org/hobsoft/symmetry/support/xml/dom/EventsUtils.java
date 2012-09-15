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
package org.hobsoft.symmetry.support.xml.dom;

import org.w3c.dom.Node;

/**
 * Provides various utility methods for working with the DOM Events API.
 * 
 * @author Mark Hobson
 * @see <a href="http://www.w3.org/TR/2000/REC-DOM-Level-2-Events-20001113/">Document Object Model (DOM) Level 2 Events
 *      Specification</a>
 */
public final class EventsUtils
{
	// constants --------------------------------------------------------------
	
	/**
	 * The name of the DOM Events feature.
	 */
	public static final String EVENTS_FEATURE = "Events";
	
	/**
	 * The version of the DOM Level 2 Events feature.
	 */
	public static final String EVENTS_VERSION_2_0 = "2.0";
	
	/**
	 * The <code>DOMSubtreeModified</code> mutation event type.
	 */
	public static final String DOM_SUBTREE_MODIFIED_TYPE = "DOMSubtreeModified";
	
	/**
	 * The <code>DOMNodeInserted</code> mutation event type.
	 */
	public static final String DOM_NODE_INSERTED_TYPE = "DOMNodeInserted";
	
	/**
	 * The <code>DOMNodeRemoved</code> mutation event type.
	 */
	public static final String DOM_NODE_REMOVED_TYPE = "DOMNodeRemoved";
	
	/**
	 * The <code>DOMNodeRemovedFromDocument</code> mutation event type.
	 */
	public static final String DOM_NODE_REMOVED_FROM_DOCUMENT_TYPE = "DOMNodeRemovedFromDocument";
	
	/**
	 * The <code>DOMNodeInsertedIntoDocument</code> mutation event type.
	 */
	public static final String DOM_NODE_INSERTED_INTO_DOCUMENT_TYPE = "DOMNodeInsertedIntoDocument";
	
	/**
	 * The <code>DOMAttrModified</code> mutation event type.
	 */
	public static final String DOM_ATTR_MODIFIED_TYPE = "DOMAttrModified";
	
	/**
	 * The <code>DOMCharacterDataModified</code> mutation event type.
	 */
	public static final String DOM_CHARACTER_DATA_MODIFIED_TYPE = "DOMCharacterDataModified";
	
	// constructors -----------------------------------------------------------
	
	private EventsUtils()
	{
		throw new AssertionError();
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Gets whether the specified node's DOM implementation supports Events 2.0.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @return <code>true</code> if the node's DOM implementation supports Events 2.0
	 */
	public static boolean hasEvents2(Node node)
	{
		return NodeUtils.hasFeature(node, EVENTS_FEATURE, EVENTS_VERSION_2_0);
	}
	
	/**
	 * Ensures that the specified node's DOM implementation supports Events 2.0.
	 * 
	 * @param node
	 *            the node whose DOM implementation to check
	 * @throws org.w3c.dom.DOMException
	 *             <code>NOT_SUPPORTED_ERR</code>: if the node's DOM implementation does not support Events 2.0
	 */
	public static void ensureEvents2(Node node)
	{
		NodeUtils.ensureFeature(node, EVENTS_FEATURE, EVENTS_VERSION_2_0);
	}
}
