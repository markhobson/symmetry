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

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MutationEvent;

/**
 * An event listener that delegates mutation events to strongly-typed handler methods.
 * 
 * @author Mark Hobson
 */
public abstract class AbstractMutationEventListener implements EventListener
{
	// EventListener methods --------------------------------------------------
	
	/**
	 * Delegates the specified event to the appropriate method for its type, or does nothing if it is not a mutation
	 * event.
	 * 
	 * @param event
	 *            the event
	 */
	public void handleEvent(Event event)
	{
		String type = event.getType();
		
		if (EventsUtils.DOM_SUBTREE_MODIFIED_TYPE.equals(type))
		{
			handleSubtreeModifiedEvent((MutationEvent) event);
		}
		else if (EventsUtils.DOM_NODE_INSERTED_TYPE.equals(type))
		{
			handleNodeInsertedEvent((MutationEvent) event);
		}
		else if (EventsUtils.DOM_NODE_REMOVED_TYPE.equals(type))
		{
			handleNodeRemovedEvent((MutationEvent) event);
		}
		else if (EventsUtils.DOM_NODE_REMOVED_FROM_DOCUMENT_TYPE.equals(type))
		{
			handleNodeRemovedFromDocumentEvent((MutationEvent) event);
		}
		else if (EventsUtils.DOM_NODE_INSERTED_INTO_DOCUMENT_TYPE.equals(type))
		{
			handleNodeInsertedIntoDocumentEvent((MutationEvent) event);
		}
		else if (EventsUtils.DOM_ATTR_MODIFIED_TYPE.equals(type))
		{
			handleAttrEvent((MutationEvent) event);
		}
		else if (EventsUtils.DOM_CHARACTER_DATA_MODIFIED_TYPE.equals(type))
		{
			handleCharacterDataModifiedEvent((MutationEvent) event);
		}
	}
	
	// protected methods ------------------------------------------------------
	
	/**
	 * Invoked when an event occurs of the type <code>DOMSubtreeModified</code>.
	 * 
	 * @param event
	 *            the mutation event
	 */
	protected void handleSubtreeModifiedEvent(MutationEvent event)
	{
		// no-op
	}
	
	/**
	 * Invoked when an event occurs of the type <code>DOMNodeInserted</code>.
	 * 
	 * @param event
	 *            the mutation event
	 */
	protected void handleNodeInsertedEvent(MutationEvent event)
	{
		// no-op
	}
	
	/**
	 * Invoked when an event occurs of the type <code>DOMNodeRemoved</code>.
	 * 
	 * @param event
	 *            the mutation event
	 */
	protected void handleNodeRemovedEvent(MutationEvent event)
	{
		// no-op
	}
	
	/**
	 * Invoked when an event occurs of the type <code>DOMNodeRemovedFromDocument</code>.
	 * 
	 * @param event
	 *            the mutation event
	 */
	protected void handleNodeRemovedFromDocumentEvent(MutationEvent event)
	{
		// no-op
	}
	
	/**
	 * Invoked when an event occurs of the type <code>DOMNodeInsertedIntoDocument</code>.
	 * 
	 * @param event
	 *            the mutation event
	 */
	protected void handleNodeInsertedIntoDocumentEvent(MutationEvent event)
	{
		// no-op
	}
	
	/**
	 * Invoked when an event occurs of the type <code>DOMAttrModified</code>.
	 * <p>
	 * This method delegates to the appropriate method for the event's <code>attrChange</code> value, but can be
	 * overridden to capture all attribute change events.
	 * </p>
	 * 
	 * @param event
	 *            the mutation event
	 */
	protected void handleAttrEvent(MutationEvent event)
	{
		switch (event.getAttrChange())
		{
			case MutationEvent.MODIFICATION:
				handleAttrModifiedEvent(event);
				break;
				
			case MutationEvent.ADDITION:
				handleAttrAddedEvent(event);
				break;
				
			case MutationEvent.REMOVAL:
				handleAttrRemovedEvent(event);
				break;
				
			default:
				break;
		}
	}
	
	/**
	 * Invoked when an event occurs of the type <code>DOMAttrModified</code> that has an <code>attrChange</code> value
	 * of <code>MODIFICATION</code>.
	 * 
	 * @param event
	 *            the mutation event
	 */
	protected void handleAttrModifiedEvent(MutationEvent event)
	{
		// no-op
	}
	
	/**
	 * Invoked when an event occurs of the type <code>DOMAttrModified</code> that has an <code>attrChange</code> value
	 * of <code>ADDITION</code>.
	 * 
	 * @param event
	 *            the mutation event
	 */
	protected void handleAttrAddedEvent(MutationEvent event)
	{
		// no-op
	}
	
	/**
	 * Invoked when an event occurs of the type <code>DOMAttrModified</code> that has an <code>attrChange</code> value
	 * of <code>REMOVAL</code>.
	 * 
	 * @param event
	 *            the mutation event
	 */
	protected void handleAttrRemovedEvent(MutationEvent event)
	{
		// no-op
	}
	
	/**
	 * Invoked when an event occurs of the type <code>DOMCharacterDataModified</code>.
	 * 
	 * @param event
	 *            the mutation event
	 */
	protected void handleCharacterDataModifiedEvent(MutationEvent event)
	{
		// no-op
	}
}
